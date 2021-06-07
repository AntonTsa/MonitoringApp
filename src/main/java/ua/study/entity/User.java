package ua.study.entity;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter @Setter
    private Long userId;
    @Getter @Setter
    private String fullName;
    @Getter @Setter
    private String login;
    @Getter @Setter
    private String password;

    public User() {

    }

    public User(String fullName, String login, String password) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId) &&
                fullName.equals(user.fullName) &&
                login.equals(user.login) &&
                password.equals(user.password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
