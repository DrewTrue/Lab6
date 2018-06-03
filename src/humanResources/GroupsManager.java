package humanResources;

import java.time.LocalDate;
import java.util.List;

//todo убери дубли. Методы List первичные
public interface GroupsManager extends List<EmployeeGroup>{
    int employeesQuantity();
    EmployeeGroup getEmployeeGroup(String name);
    int employeesQuantity(JobTitlesEnum jobTitle);
    EmployeeGroup getEmployeesGroup(String firstName, String secondName);
    Employee mostValuableEmployee();
    boolean removeGroup(String groupName);

    int getPartTimeEmployeesQuantity();
    int getStaffEmployeesQuantity();
    int getCurrentTravellersQuantity();
    Employee[] getCurrentTravellers(LocalDate beginTravelMark, LocalDate endTravelMark);
}
