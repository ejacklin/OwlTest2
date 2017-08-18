package db;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Erin on 8/17/2017.
 */
@Entity
@Table(name = "workout", schema = "owl_schema", catalog = "")
public class WorkoutEntity {
    private int workoutId;
    private String name;
    private int userId;
    private Collection<ExerciseEntity> exercisesByWorkoutId;
    private Collection<SessionEntity> sessionsByWorkoutId;
    private UserEntity userByUserId;

    @Id
    @Column(name = "workout_id", nullable = false)
    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "user_id", nullable = false, updatable = false, insertable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkoutEntity that = (WorkoutEntity) o;

        if (workoutId != that.workoutId) return false;
        if (userId != that.userId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = workoutId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }

    @OneToMany(mappedBy = "workoutByWorkoutId")
    public Collection<ExerciseEntity> getExercisesByWorkoutId() {
        return exercisesByWorkoutId;
    }

    public void setExercisesByWorkoutId(Collection<ExerciseEntity> exercisesByWorkoutId) {
        this.exercisesByWorkoutId = exercisesByWorkoutId;
    }

    @OneToMany(mappedBy = "workoutByWorkoutId")
    public Collection<SessionEntity> getSessionsByWorkoutId() {
        return sessionsByWorkoutId;
    }

    public void setSessionsByWorkoutId(Collection<SessionEntity> sessionsByWorkoutId) {
        this.sessionsByWorkoutId = sessionsByWorkoutId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
