package ua.study.entity;

import lombok.Getter;
import lombok.Setter;
import ua.study.entity.enums.Habitat;

import java.sql.Timestamp;

public class Substance {
    @Setter @Getter
    private Long substanceId;
    @Setter @Getter
    private String name;
    @Setter @Getter
    private Habitat habitat;
    @Setter @Getter
    private Double amount;
    @Setter @Getter
    private Timestamp date;
    @Setter @Getter
    private String note;
    @Setter @Getter
    private Sensor sensor;


    public Substance() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Substance substance = (Substance) o;
        return substanceId.equals(substance.substanceId) &&
                name.equals(substance.name) &&
                habitat == substance.habitat &&
                amount.equals(substance.amount) &&
                date.equals(substance.date) &&
                note.equals(substance.note);
    }

    @Override
    public String toString() {
        return "Substance{" +
                "substanceId=" + substanceId +
                ", name='" + name + '\'' +
                ", habitat=" + habitat +
                ", amount=" + amount +
                ", date=" + date +
                ", note='" + note + '\'' +
                '}';
    }
}
