package ua.study.dao;

import ua.study.entity.Sensor;
import ua.study.entity.enums.SensorStatus;
import ua.study.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SensorDAOImpl implements SensorDAO {
    private final Util util;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public SensorDAOImpl(Util util) {
        this.util = util;
    }

    @Override
    public void add(Sensor sensor) throws SQLException {
        String sql = "INSERT INTO sensor(sensor_id, sensor_name, place_id, address, status) value(?, ?, ?, ?, ?)";
        try{
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, sensor.getName());
            preparedStatement.setLong(2, sensor.getPlace().getPlaceId());
            preparedStatement.setString(3, sensor.getAddress());
            preparedStatement.setString(4, sensor.getStatus().name());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public List<Sensor> getAll() throws SQLException {
        String sql = "SELECT sensor_id, sensor_name, place_id, address, status FROM sensor";

        List<Sensor> sensorList = new ArrayList<>();

        try {
            connection = util.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Sensor sensor = setSensor();

                sensorList.add(sensor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

        return sensorList;
    }

    private Sensor setSensor() throws SQLException {
        Sensor sensor = new Sensor();

        sensor.setSensorId(resultSet.getLong("sensor_id"));
        sensor.setName(resultSet.getString("sensor_name"));
        sensor.setPlace(new PlaceDAOImpl(util).getById(resultSet.getLong("place_id")));
        sensor.setAddress(resultSet.getString("address"));
        sensor.setStatus((SensorStatus) resultSet.getObject("status"));

        return sensor;
    }

    @Override
    public Sensor getById(Long id) throws SQLException {
        String sql = "SELECT sensor_id, sensor_name, place_id, address, status FROM sensor WHERE sensor_id = ?";

        Sensor sensor = null;

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            sensor = setSensor();

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return sensor;
    }

    @Override
    public void update(Sensor sensor) throws SQLException {
        String sql = "UPDATE sensor SET sensor_name=?, place_id=?, address=?, status=? WHERE sensor_id=?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, sensor.getName());
            preparedStatement.setLong(2, sensor.getPlace().getPlaceId());
            preparedStatement.setString(3, sensor.getAddress());
            preparedStatement.setString(4, sensor.getStatus().name());
            preparedStatement.setLong(5, sensor.getSensorId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public void delete(Sensor sensor) throws SQLException {
        String sql = "DELETE FROM sensor WHERE sensor_id = ?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, sensor.getSensorId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }
}
