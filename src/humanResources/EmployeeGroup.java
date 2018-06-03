package humanResources;

import java.time.*;
import java.util.*;

//todo убери дубли. Методы List первичные
public interface EmployeeGroup extends List<Employee> {
    Employee[] getEmployeesSortedBySalary();
    boolean removeEmployee(String firstName, String secondName);
    Employee getEmployee(String firstName, String secondName);
    String getName();
    void setName(String name);
    Employee mostValuableEmployee();
    String toString();
    boolean equals(Object obj);
    int hashCode();

    int getPartTimeEmployeesQuantity();
    int getStaffEmployeesQuantity();
    int getCurrentTravellersQuantity();
    Employee[] getCurrentTravellers(LocalDate beginTravelMark, LocalDate endTravelMark);
}
