package ua.study.dao;

import ua.study.entity.NormalSubstance;
import ua.study.entity.enums.Habitat;
import ua.study.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NormalSubstanceDAOImpl implements NormalSubstanceDAO {
    private final Util util;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public NormalSubstanceDAOImpl(Util util) {
        this.util = util;
    }

    @Override
    public void add(NormalSubstance normalSubstance) throws SQLException {
        String sql = "INSERT INTO normal_substance(normal_substance_id, normal_substance_name, min_amount, max_amount, habitat, place_id) value(?, ?, ?, ?, ?, ?)";
        try{
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, normalSubstance.getName());
            preparedStatement.setDouble(2, normalSubstance.getMinAmount());
            preparedStatement.setDouble(3, normalSubstance.getMaxAmount());
            preparedStatement.setString(4, normalSubstance.getHabitat().name());
            preparedStatement.setLong(5, normalSubstance.getPlace().getPlaceId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public List<NormalSubstance> getAll() throws SQLException {
        String sql = "SELECT normal_substance_id, normal_substance_name, min_amount, max_amount, habitat, place_id FROM normal_substance";

        List<NormalSubstance> normalSubstanceList = new ArrayList<>();

        try {
            connection = util.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                NormalSubstance normalSubstance = setNormalSubstance();

                normalSubstanceList.add(normalSubstance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

        return normalSubstanceList;
    }



    private NormalSubstance setNormalSubstance() throws SQLException {
        NormalSubstance normalSubstance = new NormalSubstance();

        normalSubstance.setNormalSubstanceId(resultSet.getLong("normal_substance_id"));
        normalSubstance.setName(resultSet.getString("normal_substance_name"));
        normalSubstance.setMinAmount(resultSet.getDouble("min_amount"));
        normalSubstance.setMaxAmount(resultSet.getDouble("max_amount"));
        normalSubstance.setHabitat((Habitat) resultSet.getObject("habitat"));
        normalSubstance.setPlace(new PlaceDAOImpl(util).getById(resultSet.getLong("place_id")));

        return normalSubstance;
    }

    @Override
    public NormalSubstance getById(Long id) throws SQLException {
        String sql = "SELECT normal_substance_id, normal_substance_name, min_amount, max_amount, habitat, place_id FROM normal_substance WHERE normal_substance_id = ?";

        NormalSubstance normalSubstance = null;

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            normalSubstance = setNormalSubstance();

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return normalSubstance;
    }

    @Override
    public void update(NormalSubstance normalSubstance) throws SQLException {
        String sql = "UPDATE normal_substance SET normal_substance_name=?, min_amount=?, max_amount=?, habitat=?, place_id=? WHERE normal_substance_id=?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, normalSubstance.getName());
            preparedStatement.setDouble(2, normalSubstance.getMinAmount());
            preparedStatement.setDouble(3, normalSubstance.getMaxAmount());
            preparedStatement.setString(4, normalSubstance.getHabitat().name());
            preparedStatement.setLong(5, normalSubstance.getPlace().getPlaceId());
            preparedStatement.setLong(6, normalSubstance.getNormalSubstanceId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public void delete(NormalSubstance normalSubstance) throws SQLException {
        String sql = "DELETE FROM normal_substance WHERE normal_substance_id = ?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, normalSubstance.getNormalSubstanceId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }
}
