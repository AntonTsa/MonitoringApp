package ua.study.tests.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import ua.study.dao.PlanDAO;
import ua.study.dao.PlanDAOImpl;
import ua.study.entity.Emergency;
import ua.study.entity.Plan;
import ua.study.util.Util;

import java.sql.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PlanDAOImplTest {
    private final Util utilMock = Mockito.mock(Util.class);
    private final Connection connectionMock = Mockito.mock(Connection.class);
    private final PreparedStatement preparedStatementMock = Mockito.mock(PreparedStatement.class);
    private final Statement statementMock = Mockito.mock(Statement.class);
    private final ResultSet resultSetMock = Mockito.mock(ResultSet.class);
    private final Plan planMock = Mockito.mock(Plan.class);
    private final Emergency emergencyMock = Mockito.mock(Emergency.class);

    @Before
    public void setup() throws SQLException {
        when(planMock.getPlanId()).thenReturn(1L);
        when(planMock.getSteps()).thenReturn("BlaBlaBla");
        when(planMock.getEmergency()).thenReturn(emergencyMock);
        doNothing().when(planMock).setPlanId(anyLong());
        doNothing().when(planMock).setSteps(anyString());
        doNothing().when(planMock).setEmergency(ArgumentMatchers.<Emergency>any());

        when(emergencyMock.getEmergencyId()).thenReturn(1L);
        doNothing().when(emergencyMock).setEmergencyId(anyLong());

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
        when(resultSetMock.getLong(eq("plan_id"))).thenReturn(1L);
        when(resultSetMock.getString(eq("steps"))).thenReturn("BlaBlaBla");
        when(resultSetMock.getLong(eq("emergency_id"))).thenReturn(1L);
        doNothing().when(resultSetMock).close();
        doNothing().when(statementMock).close();
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
    }

    @Test
    public void testAddWithNoExceptions() throws SQLException {
        PlanDAO instance = new PlanDAOImpl(utilMock);
        instance.add(planMock);

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
        PlanDAO instance = new PlanDAOImpl(utilMock);
        instance.getAll();

        verify(utilMock, times(3)).getConnection();
        verify(connectionMock, times(1)).createStatement();
        verify(statementMock, times(1)).executeQuery(anyString());
        verify(resultSetMock, times(2)).next();
        verify(resultSetMock, times(5)).getLong(anyString());
        verify(resultSetMock, times(5)).getString(anyString());
        verify(resultSetMock, times(3)).close();
        verify(statementMock, times(1)).close();
        verify(connectionMock, times(3)).close();
    }

    @Test
    public void testGetByIdWithNoExceptions() throws SQLException {
        PlanDAO instance = new PlanDAOImpl(utilMock);
        instance.getById(1L);

        verify(utilMock, times(3)).getConnection();
        verify(connectionMock, times(3)).prepareStatement(anyString());
        verify(preparedStatementMock, times(3)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(3)).executeQuery();
        verify(resultSetMock, times(5)).getLong(anyString());
        verify(resultSetMock, times(5)).getString(anyString());
        verify(preparedStatementMock, times(3)).executeUpdate();
        verify(resultSetMock, times(3)).close();
        verify(preparedStatementMock, times(3)).close();
        verify(connectionMock, times(3)).close();
    }

    @Test
    public void testUpdateWithNoExceptions() throws SQLException {
        PlanDAO instance = new PlanDAOImpl(utilMock);
        instance.update(planMock);

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
        PlanDAO instance = new PlanDAOImpl(utilMock);
        instance.delete(planMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(1)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }
}
