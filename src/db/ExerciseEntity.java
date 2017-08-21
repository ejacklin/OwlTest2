package db;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Erin on 8/17/2017.
 */
@Entity
@Table(name = "exercise", schema = "owl_schema", catalog = "")
public class ExerciseEntity {
    private int exerciseId;
    private int workoutId;
    private String type;
    private String name;
    private Collection<CardioEntity> cardiosByExerciseId;
    private WorkoutEntity workoutByWorkoutId;
    private Collection<StrengthEntity> strengthsByExerciseId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id", nullable = false)
    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    @Basic
    @Column(name = "workout_id", nullable = false, updatable = false, insertable = false)
    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 8)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseEntity that = (ExerciseEntity) o;

        if (exerciseId != that.exerciseId) return false;
        if (workoutId != that.workoutId) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = exerciseId;
        result = 31 * result + workoutId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "exerciseByExerciseId",cascade = CascadeType.ALL)
    public Collection<CardioEntity> getCardiosByExerciseId() {
        return cardiosByExerciseId;
    }

    public void setCardiosByExerciseId(Collection<CardioEntity> cardiosByExerciseId) {
        this.cardiosByExerciseId = cardiosByExerciseId;
    }

    @ManyToOne
    @JoinColumn(name = "workout_id", referencedColumnName = "workout_id", nullable = false)
    public WorkoutEntity getWorkoutByWorkoutId() {
        return workoutByWorkoutId;
    }

    public void setWorkoutByWorkoutId(WorkoutEntity workoutByWorkoutId) {
        this.workoutByWorkoutId = workoutByWorkoutId;
    }

    @OneToMany(mappedBy = "exerciseByExerciseId",cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<StrengthEntity> getStrengthsByExerciseId() {
        return strengthsByExerciseId;
    }

    public void setStrengthsByExerciseId(Collection<StrengthEntity> strengthsByExerciseId) {
        this.strengthsByExerciseId = strengthsByExerciseId;
    }
}
