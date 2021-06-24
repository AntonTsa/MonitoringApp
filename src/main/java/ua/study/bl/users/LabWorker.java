package ua.study.bl.users;

import ua.study.dao.*;
import ua.study.entity.Emergency;
import ua.study.entity.Place;
import ua.study.entity.Report;
import ua.study.entity.User;
import ua.study.entity.enums.ReportStatus;
import ua.study.entity.enums.ReportType;
import ua.study.util.Util;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class LabWorker extends User{
    private EmergencyDAO emergencyDAO;
    private ReportDAO reportDAO;
    private PlaceDAO placeDAO;

    public LabWorker() {
        this.emergencyDAO = new EmergencyDAOImpl(new Util());
        this.reportDAO = new ReportDAOImpl(new Util());
        this.placeDAO = new PlaceDAOImpl(new Util());
    }

    public void setEmergencyDAO(EmergencyDAO emergencyDAO) {
        this.emergencyDAO = emergencyDAO;
    }

    public void setReportDAO(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    public void setPlaceDAO(PlaceDAO placeDAO) {
        this.placeDAO = placeDAO;
    }

    public Emergency createEmergency(String name, Long placeId, String description) {
        Emergency emergency = new Emergency();

        emergency.setName(name);
        emergency.setPlace(getPlaceById(placeId));
        emergency.setDescription(description);
        emergency.setTimestamp(new Timestamp(System.currentTimeMillis()));

        addEmergencyToDB(emergency);
        return emergency;
    }

    private void addEmergencyToDB(Emergency emergency) {
        try {
            emergencyDAO.add(emergency);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public Place getPlaceById(Long id) {
        Place place = null;
        try {
            place = placeDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return place;
    }

    public void updateEmergencyName(Long id, String name) {
        Emergency emergency = getEmergencyById(id);
        emergency.setName(name);
        updateEmergency(emergency);
    }

    private void updateEmergency(Emergency emergency) {
        try {
            emergencyDAO.update(emergency);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void updateEmergencyPlace(Long id, Long placeId) {
        Emergency emergency = getEmergencyById(id);
        emergency.setPlace(getPlaceById(placeId));
        updateEmergency(emergency);
    }

    public void updateEmergencyDescription(Long id, String description) {
        Emergency emergency = getEmergencyById(id);
        emergency.setDescription(description);
        updateEmergency(emergency);
    }

    public void updateEmergencyTimestamp(Long id, Timestamp timestamp) {
        Emergency emergency = getEmergencyById(id);
        emergency.setTimestamp(timestamp);
        updateEmergency(emergency);
    }

    private Emergency getEmergencyById(Long id) {
        Emergency emergency = null;

        try {
            emergency = emergencyDAO.getById(id);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return emergency;
    }

    public void deleteEmergency(Long id) {
        try {
            emergencyDAO.delete(emergencyDAO.getById(id));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<Emergency> getAllEmergencies() {
        List<Emergency> emergencyList = null;

        try {
            emergencyList = emergencyDAO.getAll();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return emergencyList;
    }

    public Report getReportById(Long id) {
        Report report = null;

        try {
            report = reportDAO.getById(id);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return report;
    }

    public List<Report> getAllReportsByReportType(ReportType reportType){
        List<Report> reportList = null;

        try {
            reportList = reportDAO.getAll();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return reportList;
    }

    public void createReport(String content) {
        Report report = new Report();

        report.setReportType(ReportType.EMERGENCY_REPORT);
        report.setContent(content);
        report.setStatus(ReportStatus.NEW);
        report.setUpdated(new Timestamp(System.currentTimeMillis()));
    }

}
