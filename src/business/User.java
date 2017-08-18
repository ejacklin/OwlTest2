package business;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String userName;
    private String password;
    private String email;
    private ArrayList<Workout> workouts;
    private ArrayList<Session> sessions;

    public User(){
        userName = "";
        password = "";
        email = "";
        workouts = new ArrayList<>();
        sessions = new ArrayList<>();
    }

    public User(String userName, String password){

        this.userName = userName;
        this.password = password;
        email = "";
        workouts = new ArrayList<Workout>();
        sessions = new ArrayList<Session>();

    }


    public User(String userName, String password, String email, ArrayList sessions, ArrayList workouts){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.sessions = sessions;
        this.workouts = workouts;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }

    public void addWorkout(Workout workout){
        this.workouts.add(workout);
    }

    public void addSession(Session session){
        this.sessions.add(session);
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    //Helper function to retrieve workout from name
    public Workout getWorkout(String name) {
        for (Workout workout_temp : workouts) {
            if (workout_temp.getName().equals(name)) {
                return workout_temp;
            }

        }

        return null;
    }

    //Helper function to get session data by workout type for the view.jsp and graph logic
    public ArrayList<String> getSessionDate(String name) {

        ArrayList<String> dates = null;
        if (sessions != null) {
            dates = new ArrayList<>();
            for (Session session_temp : sessions) {
                Workout workout = session_temp.getWorkout();
                if(workout != null) {
                    ArrayList<Exercise> exercises = workout.getExercises();
                    for (Exercise exercise_temp : exercises) {
                        if (exercise_temp.getName().equals(name)) {
                            dates.add(session_temp.getDate());
                        }
                    }
                }


            }
        }
        else {
            return null;
        }
        return dates;
    }
    public ArrayList<String> getUserExercises() {

        ArrayList<String> exerciseList = null;
        if (workouts != null) {
            exerciseList = new ArrayList<String>();
            for (Workout workout_temp : workouts) {
                    ArrayList<Exercise> exercises = workout_temp.getExercises();
                    for (Exercise exercise_temp : exercises) {
                        if (!exerciseList.contains(exercise_temp.getName())) {
                            exerciseList.add(exercise_temp.getName());
                        }
                    }



            }
        }

        return exerciseList;
    }
    public ArrayList<Exercise> getSessionExercises(String name) {

        ArrayList<Exercise> exerciseList = null;
        if(sessions != null){
            exerciseList = new ArrayList<>();
            for (Session session_temp : sessions) {
                Workout workout = session_temp.getWorkout();
                if(workout != null) {
                    ArrayList<Exercise> exercises = workout.getExercises();
                    for (Exercise exercise_temp : exercises) {
                        if (exercise_temp.getName().equals(name)) {
                            exerciseList.add(exercise_temp);
                        }
                    }
                }


            }
        }
        else {
            return null;
        }
        return exerciseList;
    }
}
