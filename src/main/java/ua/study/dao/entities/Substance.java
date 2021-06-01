package ua.study.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "substance")
public class Substance {
    @Id
    @Column
    @Setter @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long substanceId;
    @Column
    @Setter @Getter
    private String name;
    @Column
    @Setter @Getter
    private Habitat habitat;
    @Column
    @Setter @Getter
    private Double amount;
    @Column
    @Setter @Getter
    private Date date;
    @Column
    @Setter @Getter
    private String note;
}
