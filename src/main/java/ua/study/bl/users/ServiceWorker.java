package ua.study.bl.users;

import ua.study.bl.service.device.Device;
import ua.study.dao.*;
import ua.study.entity.Place;
import ua.study.entity.Report;
import ua.study.entity.Sensor;
import ua.study.entity.User;
import ua.study.entity.enums.ReportType;
import ua.study.entity.enums.SensorStatus;
import ua.study.util.Util;

import java.sql.SQLException;
import java.util.List;

public class ServiceWorker extends User {
    private SensorDAO sensorDAO;
    private PlaceDAO placeDAO;
    private ReportDAO reportDAO;

    public ServiceWorker() {
        this.sensorDAO = new SensorDAOImpl(new Util());
        this.placeDAO = new PlaceDAOImpl(new Util());
        this.reportDAO = new ReportDAOImpl(new Util());
    }

    public void setSensorDAO(SensorDAO sensorDAO) {
        this.sensorDAO = sensorDAO;
    }

    public void setPlaceDAO(PlaceDAO placeDAO) {
        this.placeDAO = placeDAO;
    }

    public void setReportDAO(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    public void createPlace(String region, String district, String object) {
        Place place = new Place();

        place.setRegion(region);
        place.setDistrict(district);
        place.setObject(object);

        addPlaceToDB(place);
    }

    private void addPlaceToDB(Place place) {
        try {
            placeDAO.add(place);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRegion(Long id, String region) {
        Place place = getPlaceById(id);
        place.setRegion(region);
        updatePlace(place);
    }

    public void updateDistrict(Long id, String district) {
        Place place = getPlaceById(id);
        place.setDistrict(district);
        updatePlace(place);
    }

    private void updatePlace(Place place) {
        try {
            placeDAO.update(place);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void updateObject(Long id, String object) {
        Place place = getPlaceById(id);
        place.setObject(object);
        updatePlace(place);
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

    public void deletePlace(Long id) {
        try {
            Place place = placeDAO.getById(id);
            placeDAO.delete(place);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Place> getAllPlace() {
        List<Place> placeList = null;
        try {
            placeList = placeDAO.getAll();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return placeList;
    }

    public void createDevice(String name, String address, Long place_id) {
        Sensor device = new Sensor();

        device.setName(name);
        device.setAddress(address);
        device.setPlace(getPlaceById(place_id));
        device.setStatus(SensorStatus.NEW);

        addSensorToDB(device);
    }

    private void addSensorToDB(Sensor sensor) {
        try {
            sensorDAO.add(sensor);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void updateSensorName(Long id, String name) {
        Sensor sensor = getSensorById(id);
        sensor.setName(name);
        updateSensor(sensor);
    }

    private void updateSensor(Sensor sensor) {
        try {
            sensorDAO.update(sensor);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void updateSensorPlace(Long sensorId, Long placeId) {
        Sensor sensor = getSensorById(sensorId);
        sensor.setPlace(getPlaceById(placeId));
        updateSensor(sensor);
    }

    public void updateSensorAddress(Long id, String address) {
        Sensor sensor = getSensorById(id);
        sensor.setAddress(address);
        updateSensor(sensor);
    }

    public void updateSensorStatus(Long id, SensorStatus sensorStatus){
        Sensor sensor = getSensorById(id);
        sensor.setStatus(sensorStatus);
        updateSensor(sensor);
    }

    private Sensor getSensorById(Long id) {
        Sensor sensor = null;
        try {
            sensor = sensorDAO.getById(id);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return sensor;
    }

    public void deleteSensor(Long id) {
        Sensor sensor = getSensorById(id);
        try {
            sensorDAO.delete(sensor);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<Sensor> getAllSensors() {
        List<Sensor> sensorList = null;
        try {
            sensorList =sensorDAO.getAll();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return sensorList;
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return reportList;
    }

}
