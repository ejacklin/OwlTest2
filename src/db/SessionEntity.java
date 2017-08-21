package db;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Erin on 8/17/2017.
 */
@Entity
@Table(name = "session", schema = "owl_schema", catalog = "")
public class SessionEntity {
    private int sessionId;
    private int userId;
    private Date date;
    private int workoutId;
    private UserEntity userByUserId;
    private WorkoutEntity workoutByWorkoutId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id", nullable = false)
    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Basic
    @Column(name = "user_id", nullable = false, updatable = false, insertable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "workout_id", nullable = false, updatable = false, insertable = false)
    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionEntity that = (SessionEntity) o;

        if (sessionId != that.sessionId) return false;
        if (userId != that.userId) return false;
        if (workoutId != that.workoutId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionId;
        result = 31 * result + userId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + workoutId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "workout_id", referencedColumnName = "workout_id", nullable = false)
    public WorkoutEntity getWorkoutByWorkoutId() {
        return workoutByWorkoutId;
    }

    public void setWorkoutByWorkoutId(WorkoutEntity workoutByWorkoutId) {
        this.workoutByWorkoutId = workoutByWorkoutId;
    }
}
