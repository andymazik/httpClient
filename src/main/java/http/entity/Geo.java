package http.entity;

public class Geo {
    public String lat;
    public String lng;

    @Override
    public String toString() {
        return "Geo{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
