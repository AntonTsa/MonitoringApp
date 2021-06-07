package ua.study.dao;

import ua.study.entity.Plan;
import ua.study.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDAOImpl implements PlanDAO {
    private final Util util;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public PlanDAOImpl(Util util) {
        this.util = util;
    }

    @Override
    public void add(Plan plan) throws SQLException {
        String sql = "INSERT INTO plan(plan_id, steps, emergency_id) value(?, ?, ?)";
        try{
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, plan.getSteps());
            preparedStatement.setLong(2, plan.getEmergency().getEmergencyId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public List<Plan> getAll() throws SQLException {
        String sql = "SELECT plan_id, steps, emergency_id FROM plan";

        List<Plan> planList = new ArrayList<>();

        try {
            connection = util.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Plan plan = setPlan();

                planList.add(plan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

        return planList;
    }

    private Plan setPlan() throws SQLException {
        Plan plan = new Plan();

        plan.setPlanId(resultSet.getLong("plan_id"));
        plan.setSteps(resultSet.getString("steps"));
        plan.setEmergency(new EmergencyDAOImpl(util).getById(resultSet.getLong("emergency_id")));

        return plan;
    }

    @Override
    public Plan getById(Long id) throws SQLException {
        String sql = "SELECT plan_id, steps, emergency_id FROM plan WHERE plan_id = ?";

        Plan plan = null;

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            plan = setPlan();

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return plan;
    }

    @Override
    public void update(Plan plan) throws SQLException {
        String sql = "UPDATE plan SET steps=?, emergency_id=? WHERE plan_id=?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, plan.getSteps());
            preparedStatement.setLong(2, plan.getEmergency().getEmergencyId());
            preparedStatement.setLong(3, plan.getPlanId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public void delete(Plan plan) throws SQLException {
        String sql = "DELETE FROM plan WHERE plan_id = ?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, plan.getPlanId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }
}
