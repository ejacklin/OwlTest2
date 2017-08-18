package business;

import java.io.Serializable;

public class Session implements Serializable {

    public String date;
    public Workout workout;

    public Session(){
        date = "";
        workout = null;

    }

    public Session(String date, Workout workout){
        this.date = date;
        this.workout = workout;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}
