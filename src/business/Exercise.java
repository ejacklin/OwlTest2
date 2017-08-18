package business;

import java.io.Serializable;

public class Exercise implements Serializable{

    public String name;

    public String type;



    public Exercise(){
        name = "";

    }

    public Exercise(String name){
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
