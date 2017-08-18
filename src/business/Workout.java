package business;

import java.io.Serializable;
import java.util.ArrayList;

public class Workout implements Serializable {

    private String name;
    public ArrayList<Exercise> exercises;

    public Workout(){
        name = "";
        exercises = new ArrayList<Exercise>();
    }
    public Workout(String name, ArrayList<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return exercises.toString();
    }
}
