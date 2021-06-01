package ua.study.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "normalSubstance")
public class NormalSubstance {
    @Id
    @Column(name = "normal_substance_id")
    @Setter @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long normalSubstanceId;
    @Column
    @Setter @Getter
    private String name;
    @Column
    @Setter @Getter
    private Double minAmount;
    @Column
    @Setter @Getter
    private Double maxAmount;
    @Column
    @Setter @Getter
    private Habitat habitat;
    @Column
    @Setter @Getter
    @ManyToOne (optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "normal_substance_id")
    private Place normalSubstancePlace;
}
