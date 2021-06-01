package ua.study.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @Column(name = "sensor_id")
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sensorId;
    @Column
    @Setter @Getter
    @ManyToOne (optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "sensor_id")
    private Place sensorPlace;
    @Column
    @Setter @Getter
    private String address;
    @Column
    @Setter @Getter
    private String status;


}
