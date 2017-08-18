package business;

public class Cardio extends Exercise{

    private String time;
    private String distance;

    public Cardio(String name, String time, String distance) {
        super(name);
        this.time = time;
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return name + "\n"+
                "Time: " + time + '\n' +
                "Distance: " + distance + '\n';
    }
}
