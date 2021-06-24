package ua.study.bl.service.device;

import ua.study.dao.SubstanceDAO;
import ua.study.dao.SubstanceDAOImpl;
import ua.study.entity.Sensor;
import ua.study.entity.Substance;
import ua.study.entity.enums.Habitat;
import ua.study.bl.service.SubstanceService;
import ua.study.util.Util;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Device {
    private final List<Substance> substanceLocalList;
    private final Observer observer;
    private Sensor sensor;

    public Device(Sensor sensor) {
        this.substanceLocalList = new ArrayList<>();
        observer = new SubstanceService();
        this.sensor = sensor;
    }

    public void addSubstanceToLocal(String name, Habitat habitat, Double amount) {
        Substance substance = new Substance();

        substance.setNote(name);
        substance.setHabitat(habitat);
        substance.setAmount(amount);
        substance.setDate(new Timestamp(System.currentTimeMillis()));
        substance.setSensor(sensor);

        substanceLocalList.add(substance);

        if (substanceLocalList.size() == 5) {
            sendSubstanceLocalList();
            observer.start(sensor.getSensorId());
        }
    }

    public void sendSubstanceLocalList() {
        SubstanceDAO substanceDAO = new SubstanceDAOImpl(new Util());
        try {
            for (Substance substance : this.substanceLocalList) {
                substanceDAO.add(substance);
                this.substanceLocalList.remove(substance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
