package ua.study.tests.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import ua.study.dao.EmergencyDAO;
import ua.study.dao.EmergencyDAOImpl;
import ua.study.entity.Emergency;
import ua.study.entity.Place;
import ua.study.util.Util;

import java.sql.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class EmergencyDAOImplTest {
    private final Util utilMock = Mockito.mock(Util.class);
    private final Connection connectionMock = Mockito.mock(Connection.class);
    private final PreparedStatement preparedStatementMock = Mockito.mock(PreparedStatement.class);
    private final Statement statementMock = Mockito.mock(Statement.class);
    private final ResultSet resultSetMock = Mockito.mock(ResultSet.class);
    private final Emergency emergencyMock = Mockito.mock(Emergency.class);
    private final Place placeMock = Mockito.mock(Place.class);

    @Before
    public void setup() throws SQLException {
        when(emergencyMock.getEmergencyId()).thenReturn(1L);
        when(emergencyMock.getName()).thenReturn("EmName");
        when(emergencyMock.getPlace()).thenReturn(placeMock);
        doNothing().when(emergencyMock).setEmergencyId(anyLong());
        doNothing().when(emergencyMock).setName(anyString());
        doNothing().when(emergencyMock).setPlace(ArgumentMatchers.<Place>any());

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
        when(resultSetMock.getLong(eq("emergency_id"))).thenReturn(1L);
        when(resultSetMock.getString(eq("emergency_name"))).thenReturn("EmName");
        when(resultSetMock.getLong(eq("place_id"))).thenReturn(1L);
        doNothing().when(resultSetMock).close();
        doNothing().when(statementMock).close();
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
    }

    @Test
    public void testAddWithNoExceptions() throws SQLException {
        EmergencyDAO instance = new EmergencyDAOImpl(utilMock);
        instance.add(emergencyMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(2)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).setString(anyInt(), anyString());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }

    @Test
    public void testGetAllWithNoExceptions() throws SQLException {
        EmergencyDAO instance = new EmergencyDAOImpl(utilMock);
        instance.getAll();

        verify(utilMock, times(2)).getConnection();
        verify(connectionMock, times(1)).createStatement();
        verify(statementMock, times(1)).executeQuery(anyString());
        verify(resultSetMock, times(2)).next();
        verify(resultSetMock, times(3)).getLong(anyString());
        verify(resultSetMock, times(4)).getString(anyString());
        verify(resultSetMock, times(2)).close();
        verify(statementMock, times(1)).close();
        verify(connectionMock, times(2)).close();
    }

    @Test
    public void testGetByIdWithNoExceptions() throws SQLException {
        EmergencyDAO instance = new EmergencyDAOImpl(utilMock);
        instance.getById(1L);

        verify(utilMock, times(2)).getConnection();
        verify(connectionMock, times(2)).prepareStatement(anyString());
        verify(preparedStatementMock, times(2)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(2)).executeQuery();
        verify(resultSetMock, times(3)).getLong(anyString());
        verify(resultSetMock, times(4)).getString(anyString());
        verify(preparedStatementMock, times(2)).executeUpdate();
        verify(resultSetMock, times(2)).close();
        verify(preparedStatementMock, times(2)).close();
        verify(connectionMock, times(2)).close();
    }

    @Test
    public void testUpdateWithNoExceptions() throws SQLException {
        EmergencyDAO instance = new EmergencyDAOImpl(utilMock);
        instance.update(emergencyMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(1)).setString(anyInt(), anyString());
        verify(preparedStatementMock, times(2)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }

    @Test
    public void testDeleteWithNoExceptions() throws SQLException {
        EmergencyDAO instance = new EmergencyDAOImpl(utilMock);
        instance.delete(emergencyMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(1)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }
}
