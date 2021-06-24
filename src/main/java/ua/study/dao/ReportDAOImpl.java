package ua.study.dao;

import ua.study.entity.Report;
import ua.study.entity.enums.ReportStatus;
import ua.study.entity.enums.ReportType;
import ua.study.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAOImpl implements ReportDAO {
    private final Util util;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public ReportDAOImpl(Util util) {
        this.util = util;
    }

    @Override
    public void add(Report report) throws SQLException {
        String sql = "INSERT INTO report(report_id, type, content, updated, status) value(?, ?, ?, ?, ?)";
        try{
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(2, report.getReportType().name());
            preparedStatement.setString(3, report.getContent());
            preparedStatement.setTimestamp(4, report.getUpdated());
            preparedStatement.setString(5, report.getStatus().name());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public List<Report> getAll() throws SQLException {
        String sql = "SELECT report_id, type, content, updated, status FROM report";

        List<Report> reportList = new ArrayList<>();

        try {
            connection = util.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Report report = setReport();

                reportList.add(report);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

        return reportList;
    }

    @Override
    public List<Report> getAllByType(ReportType type) throws SQLException {
        String sql = "SELECT report_id, type, content, updated, status FROM report WHERE type = ?";

        List<Report> reportList = new ArrayList<>();

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, type.name());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Report report = setReport();

                reportList.add(report);
            }

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return reportList;
    }

    private Report setReport() throws SQLException {
        Report report = new Report();

        report.setReportId(resultSet.getLong("report_id"));
        report.setReportType((ReportType) resultSet.getObject("type"));
        report.setContent(resultSet.getString("content"));
        report.setUpdated(resultSet.getTimestamp("updated"));
        report.setStatus((ReportStatus) resultSet.getObject("status"));

        return report;
    }

    @Override
    public Report getById(Long id) throws SQLException {
        String sql = "SELECT report_id, type, content, updated, status FROM report WHERE report_id = ?";

        Report report = null;

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            report = setReport();

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return report;
    }

    @Override
    public void update(Report report) throws SQLException {
        String sql = "UPDATE report SET type=?, content=?, updated=?, status=? WHERE report_id=?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, report.getReportType().name());
            preparedStatement.setString(2, report.getContent());
            preparedStatement.setTimestamp(3, report.getUpdated());
            preparedStatement.setString(4, report.getStatus().name());
            preparedStatement.setLong(5, report.getReportId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public void delete(Report report) throws SQLException {
        String sql = "DELETE FROM report WHERE report_id = ?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, report.getReportId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }
}
