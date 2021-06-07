package ua.study.entity;

import lombok.Getter;
import lombok.Setter;
import ua.study.entity.enums.Habitat;

public class NormalSubstance {
    @Setter @Getter
    private Long normalSubstanceId;
    @Setter @Getter
    private String name;
    @Setter @Getter
    private Double minAmount;
    @Setter @Getter
    private Double maxAmount;
    @Setter @Getter
    private Habitat habitat;
    @Setter @Getter
    private Place place;

    public NormalSubstance() {

    }

    public NormalSubstance(String name, Double minAmount, Double maxAmount, Habitat habitat, Place place) {
        this.name = name;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.habitat = habitat;
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalSubstance that = (NormalSubstance) o;
        return normalSubstanceId.equals(that.normalSubstanceId) &&
                name.equals(that.name) &&
                minAmount.equals(that.minAmount) &&
                maxAmount.equals(that.maxAmount) &&
                habitat == that.habitat &&
                place.equals(that.place);
    }

    @Override
    public String toString() {
        return "NormalSubstance{" +
                "normalSubstanceId=" + normalSubstanceId +
                ", name='" + name + '\'' +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", habitat=" + habitat +
                ", normalSubstancePlace=" + place +
                '}';
    }
}
