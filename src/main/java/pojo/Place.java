package pojo;

import org.springframework.stereotype.Component;

/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
public class Place {

    private double lat;
    private double lng;

    public Place() {}

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
