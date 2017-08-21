package db;

import business.*;

import javax.persistence.EntityManager;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.Date;
import java.util.List;
import java.sql.Date;
import java.util.Objects;

/**
 * Created by Erin on 8/14/2017.
 */
public class UserDB {

    private static EntityManager em;
    private ArrayList<Session> dSessions = new ArrayList<>();
    private ArrayList<Exercise> dExercises = new ArrayList<>();
    private ArrayList<Workout> dWorkouts = new ArrayList<>();


    public static UserEntity getUserById(Integer id){
        em = DBUtil.getEmFactory().createEntityManager();
        try{
            return  em.find(UserEntity.class, id);
        }finally {
            em.close();
        }
    }


    public UserEntity findWithUsername(String username) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List userEntities = em.createQuery(
                "SELECT u FROM UserEntity u WHERE u.username LIKE :username")
                .setParameter("username",username)
                .setMaxResults(10)
                .getResultList();
        em.close();
        return (UserEntity) userEntities.get(0);
    }


    public User GetDBResponse(UserEntity userEntity){
        Collection<WorkoutEntity> workoutEntities = userEntity.getWorkoutsByUserId();
        Collection<SessionEntity> sessionEntities = userEntity.getSessionsByUserId();

        for(WorkoutEntity w : workoutEntities){
            Collection<ExerciseEntity> exerciseEntities = w.getExercisesByWorkoutId();
            for(ExerciseEntity e : exerciseEntities){

                Collection<CardioEntity> cardios = e.getCardiosByExerciseId();
                    for( CardioEntity c : cardios){
                        e.setType("cardio");
                        dExercises.add(new Cardio(e.getName(),Integer.toString(c.getTime()),
                                Integer.toString(c.getDistance())));
                    }
                    Collection<StrengthEntity> strengths = e.getStrengthsByExerciseId();
                    for(StrengthEntity st : strengths){
                        e.setType("strength");
                        dExercises.add(new Strength(e.getName(),Integer.toString(st.getReps()),
                                Integer.toString(st.getWeight()),Integer.toString(st.getSets())));
                    }
            }
            Workout workout = new Workout(w.getName(),dExercises);
            dWorkouts.add(workout);

        }
        for(SessionEntity s : sessionEntities){
            WorkoutEntity workoutByWorkoutId = s.getWorkoutByWorkoutId();
            for(Workout workout : dWorkouts) {
                if (Objects.equals(workout.getName(), workoutByWorkoutId.getName())) {
                    DateFormat format_date = new SimpleDateFormat("EEE, MMM d, ''yy");
                    dSessions.add(new Session(format_date.format(s.getDate()), workout));
                    break;
                }
            }
        }

        return new User(userEntity.getUsername(),userEntity.getPassword(),userEntity.getEmail(),dSessions,dWorkouts);
    }

    public void RemoveFromDB(String username){
        em = DBUtil.getEmFactory().createEntityManager();
        UserEntity userEntity = findWithUsername(username);
        userEntity = em.merge(userEntity);
        em.getTransaction().begin();
        em.remove(userEntity);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }


    public void InsertIntoDB(UserEntity userEntity){

        em = DBUtil.getEmFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(userEntity);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    public UserEntity CreateUserEntity(User user){
        UserEntity newEntity = new UserEntity();
        ArrayList<WorkoutEntity> workoutEntities = new ArrayList<>();
        ArrayList<SessionEntity> sessionEntities = new ArrayList<>();
        Collection<ExerciseEntity> exerciseEntities = new ArrayList<>();


        newEntity.setEmail(user.getEmail());
        newEntity.setPassword(user.getPassword());
        newEntity.setUsername(user.getUserName());
        ArrayList<Workout> workouts = user.getWorkouts();


        for(Workout w : workouts){
            WorkoutEntity workoutEntity = new WorkoutEntity();
            ArrayList<Exercise> exercises = w.getExercises();
            for(Exercise e: exercises){
                ExerciseEntity exerciseEntity = new ExerciseEntity();
                ArrayList<CardioEntity> cardioEntities = new ArrayList<>();
                ArrayList<StrengthEntity> strengthEntities = new ArrayList<>();

                exerciseEntity.setName(e.getName());

                if(e instanceof business.Cardio){
                    CardioEntity cardioEntity = new CardioEntity();
                    cardioEntity.setDistance(Integer.parseInt(((Cardio) e).getDistance()));
                    cardioEntity.setTime(Integer.parseInt(((Cardio) e).getTime()));
                    cardioEntities.add(cardioEntity);
                    exerciseEntity.setCardiosByExerciseId(cardioEntities);
                }else if(e instanceof Strength){
                    StrengthEntity strengthEntity = new StrengthEntity();
                    strengthEntity.setReps(Integer.parseInt(((Strength) e).getReps()));
                    strengthEntity.setSets(Integer.parseInt(((Strength) e).getSets()));
                    strengthEntity.setWeight(Integer.parseInt(((Strength) e).getWeight()));
                    strengthEntities.add(strengthEntity);
                    exerciseEntity.setStrengthsByExerciseId(strengthEntities);
                }
                exerciseEntities.add(exerciseEntity);
            }
            workoutEntity.setName(w.getName());
            workoutEntity.setExercisesByWorkoutId(exerciseEntities);
            workoutEntities.add(workoutEntity);
        }

        ArrayList<Session> sessions = user.getSessions();
        for(Session s : sessions){
            Workout workout = s.getWorkout();
            String name = workout.getName();
            WorkoutEntity sessionWorkout = new WorkoutEntity();
            for(WorkoutEntity we : workoutEntities){
                if(Objects.equals(we.getName(), name)){
                    sessionWorkout = we;
                    break;
                }
            }

            SessionEntity sessionEntity = new SessionEntity();
            sessionEntity.setWorkoutByWorkoutId(sessionWorkout);
            java.util.Date date = null;
            try {
                date = new SimpleDateFormat("EEE, MMM d, ''yy").parse(s.getDate());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                sessionEntity.setDate(sqlDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            sessionEntities.add(sessionEntity);
        }

        newEntity.setWorkoutsByUserId(workoutEntities);
        newEntity.setSessionsByUserId(sessionEntities);
        return newEntity;
    }

    public static UserEntity CreateDenarys(){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("dtargaryen@gmail.com");
        userEntity.setUsername("dtargaryen");
        userEntity.setPassword("drogon");
        userEntity.setFirstName("Denarys");
        userEntity.setLastName("Targaryen");
        userEntity.setAge(14);
        userEntity.setWeight(115);
        userEntity.setGender("female");


        SessionEntity sessionEntity = new SessionEntity();
        java.util.Date date = new java.util.Date();
        SimpleDateFormat df = new SimpleDateFormat("EEE, MMM d, ''yy");
        Date sqlDate = null;
//        try {
//            sqlDate = new Date(df.parse("02-04-2015").getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        sessionEntity.setDate(sqlDate);
        ArrayList<StrengthEntity> strengthEntities = new ArrayList<>();
        ArrayList<CardioEntity> cardioEntities = new ArrayList<>();
        ArrayList<ExerciseEntity> exerciseEntities = new ArrayList<>();
        ArrayList<WorkoutEntity> workoutEntities = new ArrayList<>();

        WorkoutEntity workoutEntity = new WorkoutEntity();
        workoutEntity.setName("Dragon Training");
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setName("Riding Lifts");
        exerciseEntity.setWorkoutByWorkoutId(workoutEntity);


        StrengthEntity strengthEntity = new StrengthEntity();
        strengthEntity.setWeight(25);
        strengthEntity.setReps(12);
        strengthEntity.setSets(4);
        strengthEntity.setExerciseByExerciseId(exerciseEntity);
        strengthEntities.add(strengthEntity);

        exerciseEntity.setStrengthsByExerciseId(strengthEntities);

        ExerciseEntity exerciseEntity2 = new ExerciseEntity();
        exerciseEntity2.setName("Flying");
        CardioEntity cardioEntity = new CardioEntity();
        cardioEntity.setTime(90);
        cardioEntity.setDistance(100);
        cardioEntity.setExerciseByExerciseId(exerciseEntity2);

        cardioEntities.add(cardioEntity);
        exerciseEntity2.setCardiosByExerciseId(cardioEntities);
        exerciseEntity2.setWorkoutByWorkoutId(workoutEntity);

        exerciseEntities.add(exerciseEntity);
        exerciseEntities.add(exerciseEntity2);
        workoutEntity.setName("Slayin'");
        workoutEntity.setExercisesByWorkoutId(exerciseEntities);

        workoutEntity.setUserByUserId(userEntity);

        workoutEntities.add(workoutEntity);
        userEntity.setWorkoutsByUserId(workoutEntities);

        return userEntity;
    }
    
    public static void main(String[] args){
        UserDB userDB = new UserDB();
        UserEntity user = userDB.findWithUsername("tlanister");
        User u = userDB.GetDBResponse(user);
        System.out.println(u.getUserName());

        UserEntity newUserEntity = new UserEntity();
        newUserEntity = userDB.CreateUserEntity(u);

//        userDB.RemoveFromDB("dtargaryen");


        UserEntity userEntity = UserDB.CreateDenarys();

        userDB.InsertIntoDB(userEntity);



    }
}
