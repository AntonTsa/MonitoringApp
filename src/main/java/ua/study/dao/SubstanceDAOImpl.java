package ua.study.dao;

import ua.study.entity.Substance;
import ua.study.entity.enums.Habitat;
import ua.study.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubstanceDAOImpl implements SubstanceDAO {

    private final Util util;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public SubstanceDAOImpl(Util util) {
        this.util = util;
    }

    @Override
    public void add(Substance substance) throws SQLException {
        String sql = "INSERT INTO substance(substance_id, substance_name, habitat, amount, date_time, note, sensor_id) value(?, ?, ?, ?, ?, ?, ?)";
        try{
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, substance.getName());
            preparedStatement.setString(2, substance.getHabitat().name());
            preparedStatement.setDouble(3, substance.getAmount());
            preparedStatement.setTimestamp(4, substance.getDate());
            preparedStatement.setString(5, substance.getNote());
            preparedStatement.setLong(6, substance.getSensor().getSensorId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public List<Substance> getAll() throws SQLException {
        String sql = "SELECT substance_id, substance_name, habitat, amount, date_time, note, sensor_id FROM substance";

        List<Substance> substanceList = new ArrayList<>();

        try {
            connection = util.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Substance substance = setSubstance();

                substanceList.add(substance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

        return substanceList;
    }

    @Override
    public List<Substance> getAllBySensorId(Long id) throws SQLException {
        String sql = "SELECT substance_id, substance_name, habitat, amount, date_time, note, sensor_id FROM substance WHERE sensor_id = ?";

        List<Substance> substanceList = new ArrayList<>();

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Substance substance = setSubstance();

                substanceList.add(substance);
            }
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return substanceList;
    }

    private Substance setSubstance() throws SQLException {
        Substance substance = new Substance();

        substance.setSubstanceId(resultSet.getLong("substance_id"));
        substance.setName(resultSet.getString("substance_name"));
        substance.setHabitat((Habitat) resultSet.getObject("habitat"));
        substance.setAmount(resultSet.getDouble("amount"));
        substance.setDate(resultSet.getTimestamp("date_time"));
        substance.setNote(resultSet.getString("note"));
        substance.setSensor(new SensorDAOImpl(util).getById(resultSet.getLong("sensor_id")));

        return substance;
    }

    @Override
    public Substance getById(Long id) throws SQLException {
        String sql = "SELECT substance_id, substance_name, habitat, amount, date_time, note, sensor_id FROM substance WHERE substance_id = ?";

        Substance substance = null;

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            substance = setSubstance();

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return substance;
    }

    @Override
    public void delete(Substance substance) throws SQLException {
        String sql = "DELETE FROM usr WHERE usr_id = ?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, substance.getSubstanceId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }
}
