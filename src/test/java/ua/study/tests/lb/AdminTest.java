package ua.study.tests.lb;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import ua.study.bl.users.Admin;
import ua.study.dao.UserDAOImpl;
import ua.study.entity.User;
import ua.study.entity.enums.Role;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class AdminTest {
    private final UserDAOImpl userDAOMock = Mockito.mock(UserDAOImpl.class);
    private final Admin admin = new Admin();
    private final List<User> usersList = new ArrayList<>();

    @SneakyThrows
    @Before
    public void setup() {
        usersList.add(new User("A", "root", "root", Role.ADMIN));
        usersList.add(new User("B", "user", "user", Role.SEMS_WORKER));

        admin.setUserDAO(userDAOMock);
        doNothing().when(userDAOMock).add(ArgumentMatchers.<User>any());
        doNothing().when(userDAOMock).delete(ArgumentMatchers.<User>any());
        when(userDAOMock.getById(anyLong())).thenReturn(new User("Anton", "root", "root", Role.ADMIN));
        when(userDAOMock.getAll()).thenReturn(usersList);
        doNothing().when(userDAOMock).update(ArgumentMatchers.<User>any());
    }

    @Test
    public void createUserTestNoException() {
        User user = admin.createUser("Anton", "root", "root", Role.ADMIN);
        assertEquals("Anton", user.getFullName());
        assertEquals("root", user.getLogin());
        assertEquals("root", user.getPassword());
        assertEquals(Role.ADMIN, user.getRole());
    }
    @Test
    public void updateUserFullNameTest() {
        User user = admin.updateUserFullName(1L, "Vlad");
        assertEquals("Vlad", user.getFullName());
    }
    @Test
    public void updateUserLoginTest() {
        User user = admin.updateUserLogin(1L, "log");
        assertEquals("log", user.getLogin());
    }
    @Test
    public void updateUserPasswordTest() {
        User user = admin.updateUserPassword(1L, "pass");
        assertEquals("pass", user.getPassword());
    }

    @Test
    public void updateUserRoleTest() {
        User user = admin.updateUserRole(1L, Role.SERVICE_WORKER);
        assertEquals(Role.SERVICE_WORKER, user.getRole());
    }

    @Test
    public void getAllUsersTest() {
        List<User> userList = admin.getAllUsers();
        assertEquals(usersList, userList);
    }
}
