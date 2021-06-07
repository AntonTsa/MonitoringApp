package ua.study.tests.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import ua.study.dao.SensorDAO;
import ua.study.dao.SensorDAOImpl;
import ua.study.entity.Place;
import ua.study.entity.Sensor;
import ua.study.entity.enums.SensorStatus;
import ua.study.util.Util;

import java.sql.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class SensorDAOImplTest {
    private final Util utilMock = Mockito.mock(Util.class);
    private final Connection connectionMock = Mockito.mock(Connection.class);
    private final PreparedStatement preparedStatementMock = Mockito.mock(PreparedStatement.class);
    private final Statement statementMock = Mockito.mock(Statement.class);
    private final ResultSet resultSetMock = Mockito.mock(ResultSet.class);
    private final Sensor sensorMock = Mockito.mock(Sensor.class);
    private final Place placeMock = Mockito.mock(Place.class);

    @Before
    public void setup() throws SQLException {
        when(sensorMock.getSensorId()).thenReturn(1L);
        when(sensorMock.getName()).thenReturn("Sensor1");
        when(sensorMock.getPlace()).thenReturn(placeMock);
        when(sensorMock.getAddress()).thenReturn("255.255.1.1");
        when(sensorMock.getStatus()).thenReturn(SensorStatus.NEW);
        doNothing().when(sensorMock).setSensorId(anyLong());
        doNothing().when(sensorMock).setName(anyString());
        doNothing().when(sensorMock).setPlace(ArgumentMatchers.<Place>any());
        doNothing().when(sensorMock).setAddress(anyString());
        doNothing().when(sensorMock).setStatus(ArgumentMatchers.<SensorStatus>any());

        when(placeMock.getPlaceId()).thenReturn(1L);
        doNothing().when(placeMock).setPlaceId(anyLong());

        when(utilMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        doNothing().when(preparedStatementMock).setLong(anyInt(), anyLong());
        doNothing().when(preparedStatementMock).setString(anyInt(), anyString());
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatementMock).close();
        doNothing().when(connectionMock).close();
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(resultSetMock.getLong(eq("sensor_id"))).thenReturn(1L);
        when(resultSetMock.getString(eq("sensor_name"))).thenReturn("Sensor1");
        when(resultSetMock.getLong(eq("place_id"))).thenReturn(1L);
        when(resultSetMock.getString(eq("address"))).thenReturn("255.255.1.1");
        when(resultSetMock.getString(eq("status"))).thenReturn("NEW");
        doNothing().when(resultSetMock).close();
        doNothing().when(statementMock).close();
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
    }

    @Test
    public void testAddWithNoExceptions() throws SQLException {
        SensorDAO instance = new SensorDAOImpl(utilMock);
        instance.add(sensorMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(1)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(3)).setString(anyInt(), anyString());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }

    @Test
    public void testGetAllWithNoExceptions() throws SQLException {
        SensorDAO instance = new SensorDAOImpl(utilMock);
        instance.getAll();

        verify(utilMock, times(2)).getConnection();
        verify(connectionMock, times(1)).createStatement();
        verify(statementMock, times(1)).executeQuery(anyString());
        verify(resultSetMock, times(2)).next();
        verify(resultSetMock, times(3)).getLong(anyString());
        verify(resultSetMock, times(5)).getString(anyString());
        verify(resultSetMock, times(2)).close();
        verify(statementMock, times(1)).close();
        verify(connectionMock, times(2)).close();
    }

    @Test
    public void testGetByIdWithNoExceptions() throws SQLException {
        SensorDAO instance = new SensorDAOImpl(utilMock);
        instance.getById(1L);

        verify(utilMock, times(2)).getConnection();
        verify(connectionMock, times(2)).prepareStatement(anyString());
        verify(preparedStatementMock, times(2)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(2)).executeQuery();
        verify(resultSetMock, times(3)).getLong(anyString());
        verify(resultSetMock, times(5)).getString(anyString());
        verify(preparedStatementMock, times(2)).executeUpdate();
        verify(resultSetMock, times(2)).close();
        verify(preparedStatementMock, times(2)).close();
        verify(connectionMock, times(2)).close();
    }

    @Test
    public void testUpdateWithNoExceptions() throws SQLException {
        SensorDAO instance = new SensorDAOImpl(utilMock);
        instance.update(sensorMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(3)).setString(anyInt(), anyString());
        verify(preparedStatementMock, times(2)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }

    @Test
    public void testDeleteWithNoExceptions() throws SQLException {
        SensorDAO instance = new SensorDAOImpl(utilMock);
        instance.delete(sensorMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(1)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }
}
