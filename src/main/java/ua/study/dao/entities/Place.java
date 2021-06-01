package ua.study.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "place")
public class Place {
    @Id
    @Setter @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long placeId;
    @Column
    @Getter @Setter
    private String region;
    @Column
    @Getter @Setter
    private String district;
    @Column
    @Getter @Setter
    private String object;

    @OneToMany(mappedBy = "measurementPlace", fetch = FetchType.EAGER)
    private Collection<Measurement> measurements;

    @OneToMany(mappedBy = "normalSubstancePlace", fetch = FetchType.EAGER)
    private Collection<NormalSubstance> normalSubstances;

    @OneToMany(mappedBy = "sensorPlace", fetch = FetchType.EAGER)
    private Collection<Sensor> sensors;
}
