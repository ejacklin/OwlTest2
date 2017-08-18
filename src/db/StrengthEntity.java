package db;

import javax.persistence.*;

/**
 * Created by Erin on 8/17/2017.
 */
@Entity
@Table(name = "strength", schema = "owl_schema", catalog = "")
public class StrengthEntity {
    private int strengthId;
    private Integer reps;
    private Integer sets;
    private Integer weight;
    private int exerciseId;
    private ExerciseEntity exerciseByExerciseId;

    @Id
    @Column(name = "strength_id", nullable = false)
    public int getStrengthId() {
        return strengthId;
    }

    public void setStrengthId(int strengthId) {
        this.strengthId = strengthId;
    }

    @Basic
    @Column(name = "reps", nullable = true)
    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    @Basic
    @Column(name = "sets", nullable = true)
    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    @Basic
    @Column(name = "weight", nullable = true)
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "exercise_id", nullable = false, updatable = false, insertable = false)
    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StrengthEntity that = (StrengthEntity) o;

        if (strengthId != that.strengthId) return false;
        if (exerciseId != that.exerciseId) return false;
        if (reps != null ? !reps.equals(that.reps) : that.reps != null) return false;
        if (sets != null ? !sets.equals(that.sets) : that.sets != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = strengthId;
        result = 31 * result + (reps != null ? reps.hashCode() : 0);
        result = 31 * result + (sets != null ? sets.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + exerciseId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "exercise_id", referencedColumnName = "exercise_id", nullable = false)
    public ExerciseEntity getExerciseByExerciseId() {
        return exerciseByExerciseId;
    }

    public void setExerciseByExerciseId(ExerciseEntity exerciseByExerciseId) {
        this.exerciseByExerciseId = exerciseByExerciseId;
    }
}
