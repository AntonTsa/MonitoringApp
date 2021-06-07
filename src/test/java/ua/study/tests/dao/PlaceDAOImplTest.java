package ua.study.tests.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.study.dao.PlaceDAO;
import ua.study.dao.PlaceDAOImpl;
import ua.study.entity.Place;
import ua.study.util.Util;

import java.sql.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PlaceDAOImplTest {
    private final Util utilMock = Mockito.mock(Util.class);
    private final Connection connectionMock = Mockito.mock(Connection.class);
    private final PreparedStatement preparedStatementMock = Mockito.mock(PreparedStatement.class);
    private final Statement statementMock = Mockito.mock(Statement.class);
    private final ResultSet resultSetMock = Mockito.mock(ResultSet.class);
    private final Place placeMock = Mockito.mock(Place.class);

    @Before
    public void setup() throws SQLException {
        when(placeMock.getPlaceId()).thenReturn(1L);
        when(placeMock.getRegion()).thenReturn("Kyiv");
        when(placeMock.getDistrict()).thenReturn("Vyshgorod");
        when(placeMock.getObject()).thenReturn("Chornobyl");
        doNothing().when(placeMock).setPlaceId(anyLong());
        doNothing().when(placeMock).setRegion(anyString());
        doNothing().when(placeMock).setDistrict(anyString());
        doNothing().when(placeMock).setObject(anyString());

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
        when(resultSetMock.getLong(eq("place_id"))).thenReturn(1L);
        when(resultSetMock.getString(eq("region"))).thenReturn("Kyiv");
        when(resultSetMock.getString(eq("district"))).thenReturn("Vyshgorod");
        when(resultSetMock.getString(eq("object"))).thenReturn("Chornobyl");
        doNothing().when(resultSetMock).close();
        doNothing().when(statementMock).close();
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
    }

    @Test
    public void testAddWithNoExceptions() throws SQLException {
        PlaceDAO instance = new PlaceDAOImpl(utilMock);
        instance.add(placeMock);



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
        PlaceDAO instance = new PlaceDAOImpl(utilMock);
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
        PlaceDAO instance = new PlaceDAOImpl(utilMock);
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
        PlaceDAO instance = new PlaceDAOImpl(utilMock);
        instance.update(placeMock);

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
        PlaceDAO instance = new PlaceDAOImpl(utilMock);
        instance.delete(placeMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(1)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }
}
