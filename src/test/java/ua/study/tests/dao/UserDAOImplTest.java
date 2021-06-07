package ua.study.tests.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.study.dao.UserDAO;
import ua.study.dao.UserDAOImpl;
import ua.study.entity.User;
import ua.study.util.Util;

import java.sql.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserDAOImplTest {
    private final Util utilMock = Mockito.mock(Util.class);
    private final Connection connectionMock = Mockito.mock(Connection.class);
    private final PreparedStatement preparedStatementMock = Mockito.mock(PreparedStatement.class);
    private final Statement statementMock = Mockito.mock(Statement.class);
    private final ResultSet resultSetMock = Mockito.mock(ResultSet.class);
    private final User userMock = Mockito.mock(User.class);

    @Before
    public void setup() throws SQLException {
        when(userMock.getUserId()).thenReturn(1L);
        when(userMock.getFullName()).thenReturn("Anton");
        when(userMock.getLogin()).thenReturn("admin");
        when(userMock.getPassword()).thenReturn("adminP");
        doNothing().when(userMock).setUserId(anyLong());
        doNothing().when(userMock).setFullName(anyString());
        doNothing().when(userMock).setLogin(anyString());
        doNothing().when(userMock).setPassword(anyString());

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
        when(resultSetMock.getLong(eq("usr_id"))).thenReturn(1L);
        when(resultSetMock.getString(eq("full_name"))).thenReturn("Anton");
        when(resultSetMock.getString(eq("login"))).thenReturn("admin");
        when(resultSetMock.getString(eq("password"))).thenReturn("admin");
        doNothing().when(resultSetMock).close();
        doNothing().when(statementMock).close();
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
    }

    @Test
    public void testAddWithNoExceptions() throws SQLException {
        UserDAO instance = new UserDAOImpl(utilMock);
        instance.add(userMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(3)).setString(anyInt(), anyString());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }

    @Test
    public void testGetAllWithNoExceptions() throws SQLException {
        UserDAO instance = new UserDAOImpl(utilMock);
        instance.getAll();

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).createStatement();
        verify(statementMock, times(1)).executeQuery(anyString());
        verify(resultSetMock, times(2)).next();
        verify(resultSetMock, times(1)).getLong(anyString());
        verify(resultSetMock, times(3)).getString(anyString());
        verify(resultSetMock, times(1)).close();
        verify(statementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }

    @Test
    public void testGetByIdWithNoExceptions() throws SQLException {
        UserDAO instance = new UserDAOImpl(utilMock);
        instance.getById(1L);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(1)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).executeQuery();
        verify(resultSetMock, times(1)).getLong(anyString());
        verify(resultSetMock, times(3)).getString(anyString());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(resultSetMock, times(1)).close();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }

    @Test
    public void testUpdateWithNoExceptions() throws SQLException {
        UserDAO instance = new UserDAOImpl(utilMock);
        instance.update(userMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(3)).setString(anyInt(), anyString());
        verify(preparedStatementMock, times(1)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }

    @Test
    public void testDeleteWithNoExceptions() throws SQLException {
        UserDAO instance = new UserDAOImpl(utilMock);
        instance.delete(userMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(1)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }
}
