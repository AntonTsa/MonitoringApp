package ua.study.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "emergency")
public class Emergency {
    @Id
    @Column(name = "emergency_id")
    @Setter @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emergencyId;
    @Column
    @Setter @Getter
    private String name;
    @Column
    @Setter @Getter
    private String description;
    @Column
    @Setter @Getter
    private Date date;
    @OneToOne (optional=false, mappedBy="emergency")
    private Plan plan;
}
