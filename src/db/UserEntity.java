package db;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Erin on 8/17/2017.
 */
@Entity
@Table(name = "user", schema = "owl_schema", catalog = "")
public class UserEntity {
    private int userId;
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer weight;
    private String gender;
    private String password;
    private String username;
    private String email;
    private Collection<SessionEntity> sessionsByUserId;
    private Collection<WorkoutEntity> workoutsByUserId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
    @Column(name = "gender", nullable = true, length = 10)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByUserId",cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<SessionEntity> getSessionsByUserId() {
        return sessionsByUserId;
    }

    public void setSessionsByUserId(Collection<SessionEntity> sessionsByUserId) {
        this.sessionsByUserId = sessionsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId",cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<WorkoutEntity> getWorkoutsByUserId() {
        return workoutsByUserId;
    }

    public void setWorkoutsByUserId(Collection<WorkoutEntity> workoutsByUserId) {
        this.workoutsByUserId = workoutsByUserId;
    }
}
