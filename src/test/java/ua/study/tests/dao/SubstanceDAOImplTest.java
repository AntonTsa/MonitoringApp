package ua.study.tests.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import ua.study.dao.SubstanceDAO;
import ua.study.dao.SubstanceDAOImpl;
import ua.study.entity.Sensor;
import ua.study.entity.Substance;
import ua.study.entity.enums.Habitat;
import ua.study.util.Util;

import java.sql.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class SubstanceDAOImplTest {
    private final Util utilMock = Mockito.mock(Util.class);
    private final Connection connectionMock = Mockito.mock(Connection.class);
    private final PreparedStatement preparedStatementMock = Mockito.mock(PreparedStatement.class);
    private final Statement statementMock = Mockito.mock(Statement.class);
    private final ResultSet resultSetMock = Mockito.mock(ResultSet.class);
    private final Substance substanceMock = Mockito.mock(Substance.class);
    private final Sensor sensorMock = Mockito.mock(Sensor.class);

    @Before
    public void setup() throws SQLException {
        when(substanceMock.getSubstanceId()).thenReturn(1L);
        when(substanceMock.getName()).thenReturn("Cesium");
        when(substanceMock.getHabitat()).thenReturn(Habitat.AIR);
        when(substanceMock.getAmount()).thenReturn(0.1);
        when(substanceMock.getDate()).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(substanceMock.getNote()).thenReturn(" ");
        when(substanceMock.getSensor()).thenReturn(sensorMock);
        doNothing().when(substanceMock).setSubstanceId(anyLong());
        doNothing().when(substanceMock).setName(anyString());
        doNothing().when(substanceMock).setHabitat(ArgumentMatchers.<Habitat>any());
        doNothing().when(substanceMock).setAmount(anyDouble());
        doNothing().when(substanceMock).setNote(anyString());
        doNothing().when(substanceMock).setSensor(ArgumentMatchers.<Sensor>any());

        when(sensorMock.getSensorId()).thenReturn(1L);
        doNothing().when(sensorMock).setSensorId(anyLong());

        when(utilMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        doNothing().when(preparedStatementMock).setLong(anyInt(), anyLong());
        doNothing().when(preparedStatementMock).setString(anyInt(), anyString());
        doNothing().when(preparedStatementMock).setDouble(anyInt(), anyDouble());
        doNothing().when(preparedStatementMock).setTimestamp(anyInt(), ArgumentMatchers.<Timestamp>any());
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatementMock).close();
        doNothing().when(connectionMock).close();
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(resultSetMock.getLong(eq("substance_id"))).thenReturn(1L);
        when(resultSetMock.getString(eq("substance_name"))).thenReturn("Cesium");
        when(resultSetMock.getString(eq("habitat"))).thenReturn("AIR");
        when(resultSetMock.getDouble(eq("amount"))).thenReturn(0.1);
        when(resultSetMock.getTimestamp(eq("date_time"))).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(resultSetMock.getString(eq("note"))).thenReturn("");
        when(resultSetMock.getLong(eq("sensor_id"))).thenReturn(1L);
        doNothing().when(resultSetMock).close();
        doNothing().when(statementMock).close();
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
    }

    @Test
    public void testAddWithNoExceptions() throws SQLException {
        SubstanceDAO instance = new SubstanceDAOImpl(utilMock);
        instance.add(substanceMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(2)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(3)).setString(anyInt(), anyString());
        verify(preparedStatementMock, times(1)).setTimestamp(anyInt(), ArgumentMatchers.<Timestamp>any());
        verify(preparedStatementMock, times(1)).setDouble(anyInt(), anyDouble());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }

    @Test
    public void testGetAllWithNoExceptions() throws SQLException {
        SubstanceDAO instance = new SubstanceDAOImpl(utilMock);
        instance.getAll();

        verify(utilMock, times(3)).getConnection();
        verify(connectionMock, times(1)).createStatement();
        verify(statementMock, times(1)).executeQuery(anyString());
        verify(resultSetMock, times(2)).next();
        verify(resultSetMock, times(5)).getLong(anyString());
        verify(resultSetMock, times(7)).getString(anyString());
        verify(resultSetMock, times(1)).getDouble(anyString());
        verify(resultSetMock, times(1)).getTimestamp(anyString());
        verify(resultSetMock, times(3)).close();
        verify(statementMock, times(1)).close();
        verify(connectionMock, times(3)).close();
    }

    @Test
    public void testGetByIdWithNoExceptions() throws SQLException {
        SubstanceDAO instance = new SubstanceDAOImpl(utilMock);
        instance.getById(1L);

        verify(utilMock, times(3)).getConnection();
        verify(connectionMock, times(3)).prepareStatement(anyString());
        verify(preparedStatementMock, times(3)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(3)).executeQuery();
        verify(resultSetMock, times(5)).getLong(anyString());
        verify(resultSetMock, times(7)).getString(anyString());
        verify(resultSetMock, times(1)).getDouble(anyString());
        verify(resultSetMock, times(1)).getTimestamp(anyString());
        verify(preparedStatementMock, times(3)).executeUpdate();
        verify(resultSetMock, times(3)).close();
        verify(preparedStatementMock, times(3)).close();
        verify(connectionMock, times(3)).close();
    }

    @Test
    public void testDeleteWithNoExceptions() throws SQLException {
        SubstanceDAO instance = new SubstanceDAOImpl(utilMock);
        instance.delete(substanceMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(1)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }
}
