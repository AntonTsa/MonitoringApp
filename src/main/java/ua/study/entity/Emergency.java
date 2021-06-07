package ua.study.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

public class Emergency {
    @Setter @Getter
    private Long emergencyId;
    @Setter @Getter
    private String name;
    @Setter @Getter
    private Place place;
    @Setter @Getter
    private String description;
    @Setter @Getter
    private Timestamp timestamp;

    public Emergency() {

    }

    public Emergency(String name, Place place, String description, Timestamp timestamp) {
        this.name = name;
        this.place = place;
        this.description = description;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emergency emergency = (Emergency) o;
        return emergencyId.equals(emergency.emergencyId) &&
                name.equals(emergency.name) &&
                place.equals(emergency.place) &&
                description.equals(emergency.description) &&
                timestamp.equals(emergency.timestamp);
    }

    @Override
    public String toString() {
        return "Emergency{" +
                "emergencyId=" + emergencyId +
                ", name='" + name + '\'' +
                ", place=" + place +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
