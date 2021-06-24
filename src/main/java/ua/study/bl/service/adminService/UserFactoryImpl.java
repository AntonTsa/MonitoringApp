package ua.study.bl.service.adminService;

import ua.study.bl.users.Admin;
import ua.study.bl.users.LabWorker;
import ua.study.bl.users.SEMSWorker;
import ua.study.bl.users.ServiceWorker;
import ua.study.entity.User;
import ua.study.entity.enums.Role;

import java.util.NoSuchElementException;

public class UserFactoryImpl implements UserFactory{

    @Override
    public User createUser(Role role) {
        switch(role) {
            case ADMIN: {
                return new Admin();
            }
            case LAB_WORKER: {
                return new LabWorker();
            }
            case SEMS_WORKER: {
                return new SEMSWorker();
            }
            case SERVICE_WORKER: {
                return new ServiceWorker();
            }
            default: {
                throw new NoSuchElementException("No such role");
            }
        }
    }
}
