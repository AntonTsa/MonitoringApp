package ua.study.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @Column(name = "measurement_id")
    @Setter @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measurementId;
    @Column
    @Setter @Getter
    @ManyToOne (optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "measurement_id")
    private Place measurementPlace;
    @Column
    @Setter @Getter
    @ManyToOne
    private List<Substance> substancesList;

}
