package db;

import javax.persistence.*;

/**
 * Created by Erin on 8/17/2017.
 */
@Entity
@Table(name = "cardio", schema = "owl_schema", catalog = "")
public class CardioEntity {
    private int cardioId;
    private Integer distance;
    private Integer time;
    private int exerciseId;
    private ExerciseEntity exerciseByExerciseId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardio_id", nullable = false)
    public int getCardioId() {
        return cardioId;
    }

    public void setCardioId(int cardioId) {
        this.cardioId = cardioId;
    }

    @Basic
    @Column(name = "distance", nullable = true)
    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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

        CardioEntity that = (CardioEntity) o;

        if (cardioId != that.cardioId) return false;
        if (exerciseId != that.exerciseId) return false;
        if (distance != null ? !distance.equals(that.distance) : that.distance != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cardioId;
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + exerciseId;
        return result;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_id", referencedColumnName = "exercise_id", nullable = false )
    public ExerciseEntity getExerciseByExerciseId() {
        return exerciseByExerciseId;
    }

    public void setExerciseByExerciseId(ExerciseEntity exerciseByExerciseId) {
        this.exerciseByExerciseId = exerciseByExerciseId;
    }
}
