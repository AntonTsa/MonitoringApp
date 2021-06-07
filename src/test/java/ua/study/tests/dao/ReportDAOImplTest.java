package ua.study.tests.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import ua.study.dao.ReportDAO;
import ua.study.dao.ReportDAOImpl;
import ua.study.entity.Report;
import ua.study.entity.enums.ReportStatus;
import ua.study.entity.enums.ReportType;
import ua.study.util.Util;

import java.sql.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ReportDAOImplTest {
    private final Util utilMock = Mockito.mock(Util.class);
    private final Connection connectionMock = Mockito.mock(Connection.class);
    private final PreparedStatement preparedStatementMock = Mockito.mock(PreparedStatement.class);
    private final Statement statementMock = Mockito.mock(Statement.class);
    private final ResultSet resultSetMock = Mockito.mock(ResultSet.class);
    private final Report reportMock = Mockito.mock(Report.class);

    @Before
    public void setup() throws SQLException {
        when(reportMock.getReportId()).thenReturn(1L);
        when(reportMock.getReportType()).thenReturn(ReportType.ENVIRONMENT_REPORT);
        when(reportMock.getContent()).thenReturn("BlaBlaBla");
        when(reportMock.getUpdated()).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(reportMock.getStatus()).thenReturn(ReportStatus.NEW);
        doNothing().when(reportMock).setReportId(anyLong());
        doNothing().when(reportMock).setReportType(ArgumentMatchers.<ReportType>any());
        doNothing().when(reportMock).setContent(anyString());
        doNothing().when(reportMock).setUpdated(ArgumentMatchers.<Timestamp>any());
        doNothing().when(reportMock).setStatus(ArgumentMatchers.<ReportStatus>any());

        when(utilMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        doNothing().when(preparedStatementMock).setLong(anyInt(), anyLong());
        doNothing().when(preparedStatementMock).setString(anyInt(), anyString());
        doNothing().when(preparedStatementMock).setTimestamp(anyInt(), ArgumentMatchers.<Timestamp>any());
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatementMock).close();
        doNothing().when(connectionMock).close();
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(resultSetMock.getLong(eq("report_id"))).thenReturn(1L);
        when(resultSetMock.getString(eq("type"))).thenReturn("ENVIRONMENT_REPORT");
        when(resultSetMock.getString(eq("content"))).thenReturn("BlaBlaBla");
        when(resultSetMock.getTimestamp(eq("updated"))).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(resultSetMock.getString(eq("status"))).thenReturn("NEW");
        doNothing().when(resultSetMock).close();
        doNothing().when(statementMock).close();
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
    }

    @Test
    public void testAddWithNoExceptions() throws SQLException {
        ReportDAO instance = new ReportDAOImpl(utilMock);
        instance.add(reportMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(3)).setString(anyInt(), anyString());
        verify(preparedStatementMock, times(1)).setTimestamp(anyInt(), ArgumentMatchers.<Timestamp>any());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }

    @Test
    public void testGetAllWithNoExceptions() throws SQLException {
        ReportDAO instance = new ReportDAOImpl(utilMock);
        instance.getAll();

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).createStatement();
        verify(statementMock, times(1)).executeQuery(anyString());
        verify(resultSetMock, times(2)).next();
        verify(resultSetMock, times(1)).getLong(anyString());
        verify(resultSetMock, times(1)).getString(anyString());
        verify(resultSetMock, times(1)).getTimestamp(anyString());
        verify(resultSetMock, times(1)).close();
        verify(statementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }

    @Test
    public void testGetByIdWithNoExceptions() throws SQLException {
        ReportDAO instance = new ReportDAOImpl(utilMock);
        instance.getById(1L);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(1)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).executeQuery();
        verify(resultSetMock, times(1)).getLong(anyString());
        verify(resultSetMock, times(1)).getString(anyString());
        verify(resultSetMock, times(1)).getTimestamp(anyString());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(resultSetMock, times(1)).close();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }

    @Test
    public void testUpdateWithNoExceptions() throws SQLException {
        ReportDAO instance = new ReportDAOImpl(utilMock);
        instance.update(reportMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(3)).setString(anyInt(), anyString());
        verify(preparedStatementMock, times(1)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).setTimestamp(anyInt(), ArgumentMatchers.<Timestamp>any());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }

    @Test
    public void testDeleteWithNoExceptions() throws SQLException {
        ReportDAO instance = new ReportDAOImpl(utilMock);
        instance.delete(reportMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(1)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }
}
