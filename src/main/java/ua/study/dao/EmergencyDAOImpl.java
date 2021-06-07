package ua.study.dao;

import ua.study.entity.Emergency;
import ua.study.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmergencyDAOImpl implements EmergencyDAO {
    private final Util util;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public EmergencyDAOImpl(Util util) {
        this.util = util;
    }

    @Override
    public void add(Emergency emergency) throws SQLException {
        String sql = "INSERT INTO emergency(emergency_id, emergency_name, place_id, description, date_time) value(?, ?, ?, ?, ?)";
        try{
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, emergency.getEmergencyId());
            preparedStatement.setString(2, emergency.getName());
            preparedStatement.setLong(3, emergency.getPlace().getPlaceId());
            preparedStatement.setString(4, emergency.getDescription());
            preparedStatement.setTimestamp(5, emergency.getTimestamp());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public List<Emergency> getAll() throws SQLException {
        String sql = "SELECT emergency_id, emergency_name, place_id, description, date_time FROM emergency";

        List<Emergency> emergencyList = new ArrayList<>();

        try {
            connection = util.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Emergency emergency = setEmergency();

                emergencyList.add(emergency);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

        return emergencyList;
    }

    private Emergency setEmergency() throws SQLException {
        Emergency emergency = new Emergency();

        emergency.setEmergencyId(resultSet.getLong("emergency_id"));
        emergency.setName(resultSet.getString("emergency_name"));
        emergency.setPlace(new PlaceDAOImpl(util).getById(resultSet.getLong("place_id")));
        emergency.setTimestamp(resultSet.getTimestamp("date_time"));

        return emergency;
    }

    @Override
    public Emergency getById(Long id) throws SQLException {
        String sql = "SELECT emergency_id, emergency_name, place_id, description, date_time FROM emergency WHERE emergency_id = ?";

        Emergency emergency = null;

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            emergency = setEmergency();

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return emergency;
    }

    @Override
    public void update(Emergency emergency) throws SQLException {
        String sql = "UPDATE emergency SET emergency_name=?, place_id=?, description=?, date_time=?  WHERE emergency_id=?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, emergency.getName());
            preparedStatement.setLong(2, emergency.getPlace().getPlaceId());
            preparedStatement.setString(3, emergency.getDescription());
            preparedStatement.setTimestamp(4, emergency.getTimestamp());
            preparedStatement.setLong(5, emergency.getEmergencyId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public void delete(Emergency emergency) throws SQLException {
        String sql = "DELETE FROM emergency WHERE emergency_id = ?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, emergency.getEmergencyId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }
}
