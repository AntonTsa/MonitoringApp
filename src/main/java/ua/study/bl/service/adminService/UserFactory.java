package ua.study.bl.service.adminService;

import ua.study.entity.User;
import ua.study.entity.enums.Role;

public interface UserFactory {
    User createUser(Role role);
}
