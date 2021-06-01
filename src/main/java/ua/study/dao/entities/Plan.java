package ua.study.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "plan")
public class Plan {
    @Id
    @Column
    @Setter @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;
    @Column
    @Setter @Getter
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="emergency_id")
    private Emergency emergency;
    @Column
    @Setter @Getter
    private String steps;
}
