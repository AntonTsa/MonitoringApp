package ua.study.tests.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import ua.study.dao.NormalSubstanceDAO;
import ua.study.dao.NormalSubstanceDAOImpl;
import ua.study.entity.Place;
import ua.study.entity.NormalSubstance;
import ua.study.entity.enums.Habitat;
import ua.study.util.Util;

import java.sql.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class NormalSubstanceDAOImplTest {
    private final Util utilMock = Mockito.mock(Util.class);
    private final Connection connectionMock = Mockito.mock(Connection.class);
    private final PreparedStatement preparedStatementMock = Mockito.mock(PreparedStatement.class);
    private final Statement statementMock = Mockito.mock(Statement.class);
    private final ResultSet resultSetMock = Mockito.mock(ResultSet.class);
    private final NormalSubstance normalSubstanceMock = Mockito.mock(NormalSubstance.class);
    private final Place placeMock = Mockito.mock(Place.class);

    @Before
    public void setup() throws SQLException {
        when(normalSubstanceMock.getNormalSubstanceId()).thenReturn(1L);
        when(normalSubstanceMock.getName()).thenReturn("Cesium");
        when(normalSubstanceMock.getMinAmount()).thenReturn(0.078);
        when(normalSubstanceMock.getMaxAmount()).thenReturn(0.081);
        when(normalSubstanceMock.getHabitat()).thenReturn(Habitat.AIR);
        when(normalSubstanceMock.getPlace()).thenReturn(placeMock);
        doNothing().when(normalSubstanceMock).setNormalSubstanceId(anyLong());
        doNothing().when(normalSubstanceMock).setName(anyString());
        doNothing().when(normalSubstanceMock).setMinAmount(anyDouble());
        doNothing().when(normalSubstanceMock).setMaxAmount(anyDouble());
        doNothing().when(normalSubstanceMock).setHabitat(ArgumentMatchers.<Habitat>any());
        doNothing().when(normalSubstanceMock).setPlace(ArgumentMatchers.<Place>any());

        when(placeMock.getPlaceId()).thenReturn(1L);
        doNothing().when(placeMock).setPlaceId(anyLong());

        when(utilMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        doNothing().when(preparedStatementMock).setLong(anyInt(), anyLong());
        doNothing().when(preparedStatementMock).setString(anyInt(), anyString());
        doNothing().when(preparedStatementMock).setDouble(anyInt(), anyDouble());
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatementMock).close();
        doNothing().when(connectionMock).close();
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(resultSetMock.getLong(eq("normalSubstance_id"))).thenReturn(1L);
        when(resultSetMock.getString(eq("normalSubstance_name"))).thenReturn("Cesium");
        when(resultSetMock.getDouble(eq("min_amount"))).thenReturn(0.078);
        when(resultSetMock.getDouble(eq("max_amount"))).thenReturn(0.081);
        when(resultSetMock.getString(eq("habitat"))).thenReturn("AIR");
        when(resultSetMock.getLong(eq("place_id"))).thenReturn(1L);
        doNothing().when(resultSetMock).close();
        doNothing().when(statementMock).close();
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
    }

    @Test
    public void testAddWithNoExceptions() throws SQLException {
        NormalSubstanceDAO instance = new NormalSubstanceDAOImpl(utilMock);
        instance.add(normalSubstanceMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(2)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(2)).setString(anyInt(), anyString());
        verify(preparedStatementMock, times(2)).setDouble(anyInt(), anyDouble());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }

    @Test
    public void testGetAllWithNoExceptions() throws SQLException {
        NormalSubstanceDAO instance = new NormalSubstanceDAOImpl(utilMock);
        instance.getAll();

        verify(utilMock, times(2)).getConnection();
        verify(connectionMock, times(1)).createStatement();
        verify(statementMock, times(1)).executeQuery(anyString());
        verify(resultSetMock, times(2)).next();
        verify(resultSetMock, times(3)).getLong(anyString());
        verify(resultSetMock, times(4)).getString(anyString());
        verify(resultSetMock, times(2)).getDouble(anyString());
        verify(resultSetMock, times(2)).close();
        verify(statementMock, times(1)).close();
        verify(connectionMock, times(2)).close();
    }

    @Test
    public void testGetByIdWithNoExceptions() throws SQLException {
        NormalSubstanceDAO instance = new NormalSubstanceDAOImpl(utilMock);
        instance.getById(1L);

        verify(utilMock, times(2)).getConnection();
        verify(connectionMock, times(2)).prepareStatement(anyString());
        verify(preparedStatementMock, times(2)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(2)).executeQuery();
        verify(resultSetMock, times(3)).getLong(anyString());
        verify(resultSetMock, times(4)).getString(anyString());
        verify(resultSetMock, times(2)).getDouble(anyString());
        verify(preparedStatementMock, times(2)).executeUpdate();
        verify(resultSetMock, times(2)).close();
        verify(preparedStatementMock, times(2)).close();
        verify(connectionMock, times(2)).close();
    }

    @Test
    public void testDeleteWithNoExceptions() throws SQLException {
        NormalSubstanceDAO instance = new NormalSubstanceDAOImpl(utilMock);
        instance.delete(normalSubstanceMock);

        verify(utilMock, times(1)).getConnection();
        verify(connectionMock, times(1)).prepareStatement(anyString());
        verify(preparedStatementMock, times(1)).setLong(anyInt(), anyLong());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(preparedStatementMock, times(1)).close();
        verify(connectionMock, times(1)).close();
    }
}
