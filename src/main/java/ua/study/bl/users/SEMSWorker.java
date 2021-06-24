package ua.study.bl.users;

import ua.study.dao.*;
import ua.study.entity.*;
import ua.study.entity.enums.Habitat;
import ua.study.entity.enums.ReportStatus;
import ua.study.entity.enums.ReportType;
import ua.study.util.Util;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class SEMSWorker extends User {
    private PlanDAO planDAO;
    private NormalSubstanceDAO normalSubstanceDAO;
    private ReportDAO reportDAO;
    private EmergencyDAO emergencyDAO;
    private PlaceDAO placeDAO;

    public SEMSWorker() {
        this.planDAO = new PlanDAOImpl(new Util());
        this.normalSubstanceDAO = new NormalSubstanceDAOImpl(new Util());
        this.reportDAO = new ReportDAOImpl(new Util());
        this.emergencyDAO = new EmergencyDAOImpl(new Util());
        this.placeDAO = new PlaceDAOImpl(new Util());
    }

    public void setPlanDAO(PlanDAO planDAO) {
        this.planDAO = planDAO;
    }

    public void setNormalSubstanceDAO(NormalSubstanceDAO normalSubstanceDAO) {
        this.normalSubstanceDAO = normalSubstanceDAO;
    }

    public void setReportDAO(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    public void setEmergencyDAO(EmergencyDAO emergencyDAO) {
        this.emergencyDAO = emergencyDAO;
    }

    public void setPlaceDAO(PlaceDAO placeDAO) {
        this.placeDAO = placeDAO;
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

        report.setReportType(ReportType.FUTURE_PLANS_REPORT);
        report.setContent(content);
        report.setStatus(ReportStatus.NEW);
        report.setUpdated(new Timestamp(System.currentTimeMillis()));
    }

    public void createPlan(String steps, Long emergencyId) {
        Plan plan = new Plan();

        plan.setSteps(steps);
        plan.setEmergency(getEmergencyById(emergencyId));
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

    public void updatePlanSteps(Long id, String steps) {
        Plan plan = getPlanById(id);
        plan.setSteps(steps);
        updatePlan(plan);
    }

    private void updatePlan(Plan plan) {
        try {
            planDAO.update(plan);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public Plan getPlanById(Long id) {
        Plan plan = null;
        try {
             plan = planDAO.getById(id);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return plan;
    }

    public void updatePlanSteps(Long id, Long emergencyId) {
        Plan plan = getPlanById(id);
        plan.setEmergency(getEmergencyById(emergencyId));
        updatePlan(plan);
    }

    public void deletePlan(Plan plan) {
        try {
            planDAO.delete(plan);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<Plan> getAllPlans() {
        List<Plan> planList = null;

        try {
            planList = planDAO.getAll();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return planList;
    }

    public void createNormalSubstance(String name, Double minAmount, Double maxAmount, Habitat habitat, Long placeId) {
        NormalSubstance normalSubstance = new NormalSubstance();

        normalSubstance.setName(name);
        normalSubstance.setMinAmount(minAmount);
        normalSubstance.setMaxAmount(maxAmount);
        normalSubstance.setHabitat(habitat);
        normalSubstance.setPlace(getPlaceById(placeId));

        addNormalSubstanceToDB(normalSubstance);
    }

    private void addNormalSubstanceToDB(NormalSubstance normalSubstance) {
        try {
            normalSubstanceDAO.add(normalSubstance);
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

    public void updateNormalSubstanceName(Long id, String name) {
        NormalSubstance normalSubstance = getNormalSubstanceById(id);
        normalSubstance.setName(name);
        updateNormalSubstance(normalSubstance);
    }

    private void updateNormalSubstance(NormalSubstance normalSubstance) {
        try {
            normalSubstanceDAO.update(normalSubstance);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private NormalSubstance getNormalSubstanceById(Long id) {
        NormalSubstance normalSubstance = null;

        try {
            normalSubstance = normalSubstanceDAO.getById(id);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return normalSubstance;
    }

    public void updateNormalSubstanceMinAmount(Long id, Double minAmount) {
        NormalSubstance normalSubstance = getNormalSubstanceById(id);
        normalSubstance.setMinAmount(minAmount);
        updateNormalSubstance(normalSubstance);
    }

    public void updateNormalSubstanceMaxAmount(Long id, Double maxAmount) {
        NormalSubstance normalSubstance = getNormalSubstanceById(id);
        normalSubstance.setMaxAmount(maxAmount);
        updateNormalSubstance(normalSubstance);
    }

    public void updateNormalSubstanceHabitat(Long id, Habitat habitat) {
        NormalSubstance normalSubstance = getNormalSubstanceById(id);
        normalSubstance.setHabitat(habitat);
        updateNormalSubstance(normalSubstance);
    }

    public void updateNormalSubstancePlace(Long id, Long placeId) {
        NormalSubstance normalSubstance = getNormalSubstanceById(id);
        normalSubstance.setPlace(getPlaceById(placeId));
        updateNormalSubstance(normalSubstance);
    }

    public void deleteNormalSubstance(Long id) {
        try {
            normalSubstanceDAO.delete(normalSubstanceDAO.getById(id));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<NormalSubstance> getAllNormalSubstances() {
        List<NormalSubstance> normalSubstanceList = null;

        try {
            normalSubstanceList = normalSubstanceDAO.getAll();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return normalSubstanceList;
    }
}
