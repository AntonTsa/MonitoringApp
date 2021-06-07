package ua.study.entity;

import lombok.Getter;
import lombok.Setter;
import ua.study.entity.enums.SensorStatus;

public class Sensor {
    @Getter @Setter
    private Long sensorId;
    @Setter @Getter
    private String name;
    @Setter @Getter
    private Place place;
    @Setter @Getter
    private String address;
    @Setter @Getter
    private SensorStatus status;

    public Sensor() {

    }

    public Sensor(String name, Place place, String address, SensorStatus status) {
        this.name = name;
        this.place = place;
        this.address = address;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return sensorId.equals(sensor.sensorId) &&
                name.equals(sensor.name) &&
                place.equals(sensor.place) &&
                address.equals(sensor.address) &&
                status == sensor.status;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorId=" + sensorId +
                ", name='" + name + '\'' +
                ", place=" + place +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}
