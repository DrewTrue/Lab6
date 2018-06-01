package humanResources;

import java.io.Serializable;

public class PartTimeEmployee extends Employee implements Serializable {
    public PartTimeEmployee(String firstName, String secondName,  int salary){
        this(firstName, secondName, JobTitlesEnum.NONE, salary);
    }

    public PartTimeEmployee(String firstName, String secondName, JobTitlesEnum jobTitle, int salary){
        super(firstName, secondName, jobTitle, salary);
    }

    public int getBonus(){
        return 0;
    }

    public void setBonus(int bonus){ }
}
