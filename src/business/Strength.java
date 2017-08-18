package business;

public class Strength extends Exercise{

    private String reps;
    private String weight;
    private String sets;

    public Strength(String name, String reps, String weight, String sets) {
        super(name);
        this.reps = reps;
        this.weight = weight;
        this.sets = sets;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    @Override
    public String toString() {
        return name + "\n"+
                "Weight: " + weight + '\n' +
                "Sets: " + sets + '\n' +
                "Repetitions: " + reps + "\n";
    }
}
