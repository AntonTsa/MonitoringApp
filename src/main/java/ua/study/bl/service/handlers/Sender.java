package ua.study.bl.service.handlers;

import ua.study.dao.ReportDAO;
import ua.study.dao.ReportDAOImpl;
import ua.study.entity.Report;
import ua.study.util.Util;

import java.sql.SQLException;

public class Sender implements Handler{
    private Handler next;
    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(Request request) {
        send(request.getReport());
        if (next != null) {
            next.handle(request);
        }
    }
    public static void send(Report report) {
        try {
            Util util = new Util();
            ReportDAO reportDAO = new ReportDAOImpl(util);
            reportDAO.add(report);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
