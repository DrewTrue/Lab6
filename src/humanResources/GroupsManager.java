package humanResources;

import java.time.LocalDate;
import java.util.List;

//todo убери дубли. Методы List первичные
public interface GroupsManager extends List<EmployeeGroup>{
    int employeesQuantity();
    int groupsQuantity(); //size() from List
    void addGroup(EmployeeGroup groupable); //add() from List,
    EmployeeGroup getEmployeeGroup(String name);
    EmployeeGroup[] getEmployeesGroups(); //toArray() from List
    int employeesQuantity(JobTitlesEnum jobTitle);
    EmployeeGroup getEmployeesGroup(String firstName, String secondName);
    Employee mostValuableEmployee();
    boolean removeGroup(String groupName);
    int removeGroup(EmployeeGroup group); //remove() from List

    int getPartTimeEmployeesQuantity();
    int getStaffEmployeesQuantity();
    int getCurrentTravellersQuantity();
    Employee[] getCurrentTravellers(LocalDate beginTravelMark, LocalDate endTravelMark);
}
