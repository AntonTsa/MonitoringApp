package ua.study.bl.service.handlers;


import ua.study.dao.NormalSubstanceDAO;
import ua.study.dao.NormalSubstanceDAOImpl;
import ua.study.entity.NormalSubstance;
import ua.study.entity.Substance;
import ua.study.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Comparator implements Handler{
    private Handler next;
    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(Request request) {
        request.setSubstanceList(getSubstances(request.getSubstanceList()));
        if (next != null){
            next.handle(request);
        }
    }

    public List<Substance> getSubstances(List<Substance> substanceList) {
        for (Substance substance : substanceList) {
            substance.setNote(makeNote(substance));
        }
        return substanceList;
    }

    private String makeNote(Substance substance) {
        Integer result = compareToNormalSubstance(substance);
        switch (result){
            case 1: {
                return "More, than normal limits!";
            }
            case -1: {
                return "Less than normal limits";
            }
            case 0: {
                return "Within normal limits";
            }
            default:{
                return "No such normal limits";
            }
        }
    }

    private Integer compareToNormalSubstance(Substance substance) {
        Integer result = null;
        try {
            for (NormalSubstance normalSubstance: getNormalSubstances()) {
                if (substance.getName().equals(normalSubstance.getName())) {
                    result = compareNumbers(normalSubstance.getMinAmount(), normalSubstance.getMaxAmount(), substance.getAmount());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private List<NormalSubstance> getNormalSubstances() throws SQLException {
        Util util = new Util();
        NormalSubstanceDAO normalSubstanceDAO = new NormalSubstanceDAOImpl(util);
        return normalSubstanceDAO.getAll();
    }

    private int compareNumbers(double minAmount, double maxAmount, double amount) {
        if (amount >= minAmount && amount<= maxAmount) {
            return 0;
        } else if (amount < maxAmount) {
            return -1;
        } else {
            return 1;
        }
    }
}
