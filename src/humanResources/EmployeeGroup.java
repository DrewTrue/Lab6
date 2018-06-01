package humanResources;

import java.time.*;
import java.util.*;

//todo убери дубли. Методы List первичные
public interface EmployeeGroup extends List<Employee> {
    void addEmployee(Employee employee) throws AlreadyAddedException;
    Employee[] getEmployeesSortedBySalary();
    Employee[] getEmployees(); //toString() from List
    int employeeQuantity(); //size() from List
    boolean removeEmployee(String firstName, String secondName);
    boolean removeEmployee(Employee employee); //remove() from List
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
