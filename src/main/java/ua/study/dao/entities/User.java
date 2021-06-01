package ua.study.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="usr")
public class User {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userId;
    @Getter @Setter
    @Column
    private String fullName;
    @Getter @Setter
    @Column
    private String login;
    @Getter @Setter
    @Column
    private String password;



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
