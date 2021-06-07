package ua.study.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Place {
    @Setter @Getter
    private Long placeId;
    @Getter @Setter
    private String region;
    @Getter @Setter
    private String district;
    @Getter @Setter
    private String object;

    public Place() {

    }

    public Place(String region, String district, String object) {
        this.region = region;
        this.district = district;
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return placeId.equals(place.placeId) &&
                region.equals(place.region) &&
                district.equals(place.district) &&
                object.equals(place.object);
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeId=" + placeId +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", object='" + object + '\'' +
                '}';
    }
}
