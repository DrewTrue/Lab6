package humanResources;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.ListIterator;

//todo аналогично департаменту
public class Project implements EmployeeGroup, Serializable {
    private String name;
    private int size;
    private LinkedList<Employee> list;

    private final static int DEFAULT_SIZE = 0;

    public Project(String name){
        this.name = name;
        this.size = DEFAULT_SIZE;
        this.list = new LinkedList<>();
    }

    public Project(String name, Employee[] employees){
        this.name = name;
        this.size = employees.length;
        for (Employee employee : employees) {
            list.add(employee);
        }
    }

    @Override
    public int getPartTimeEmployeesQuantity(){
        int quantity = 0;
        for(Employee employee : this) {
            if(employee instanceof PartTimeEmployee) {
                quantity++;
            }
        }
        return quantity;
    }

    @Override
    public int getStaffEmployeesQuantity(){
        int quantity = 0;
        for(Employee employee : this){
            if(employee instanceof StaffEmployee){
                quantity++;
            }
        }
        return quantity;
    }

    @Override
    public int getCurrentTravellersQuantity(){
        int quantity = 0;
        for(Employee employee : this){
            if(((StaffEmployee)employee).isTravelNow())
                quantity++;
        }
        return quantity;
    }

    @Override
    public Employee[] getCurrentTravellers(LocalDate beginTravelMark, LocalDate endTravelMark){
        Employee[] newEmployees = new Employee[getStaffEmployeesQuantity()];
        int counter = 0;
        for(Employee employee : this){
            if(((StaffEmployee)employee).getTravelDaysFromTimeLapse(beginTravelMark, endTravelMark) > 0) {
                newEmployees[counter] = employee;
                counter++;
            }
        }
        return newEmployees;
    }

    @Override
    public Employee[] getEmployeesSortedBySalary() {
        Employee[] employees = new Employee[size];
        if(size > 1) {
            Arrays.sort(employees, ((Comparator<Employee>) Employee::compareTo).reversed());

            /*Arrays.sort(employees, new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return o1.compareTo(o2);
                }
            }.reversed());*/

            return employees;
        }
        return employees;
    }

    @Override
    public boolean removeEmployee(String firstName, String secondName){
        for(Employee employee : this){
            if(employee.getFirstName().equals(firstName)
                    && employee.getSecondName().equals(secondName)
                    && list.remove(employee)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Employee getEmployee(String firstName, String secondName){
        for(Employee employee : this){
            if(employee.getFirstName().equals(firstName) && employee.getSecondName().equals(secondName)){
                return employee;
            }
        }
        return null;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public Employee mostValuableEmployee(){
        return getEmployeesSortedBySalary()[0];
    }

    private void swapEmployee(Employee[] employees, int i, int j) {
        Employee template = employees[i];
        employees[i] = employees[j];
        employees[j] = template;
    }

    private void quickSort(Employee[] employees, int begin, int end) {
        int i = begin, j = end, pivot = employees[(begin + end) / 2].getSalary();
        do {
            while (employees[i].getSalary() > pivot && i < end) i++;
            while (employees[j].getSalary() < pivot && j > begin) j--;
            if (i <= j) {
                swapEmployee(employees, i, j);
                i++;
                j--;
            }
        }
        while (i <= j);
        if (begin < j) quickSort(employees, begin, j);
        if (i < end) quickSort(employees, i, end);
    }

    @Override
    public Employee get(int index) {
        if(index > -1 && index < size) {
            Employee[] employees = (Employee[]) toArray();
            return employees[index];
        }

        return null;
    }

    @Override
    public Employee set(int index, Employee element) {
        return list.set(index, element);
    }

    @Override
    public void add(int index, Employee element) {
        list.add(index, element);
    }

    @Override
    public Employee remove(int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        Employee[] employees = (Employee[]) toArray();
        for(int i = 0; i < size; i++){
            if(employees[i].equals(o))
                return i;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Employee[] employees = (Employee[]) toArray();
        for(int i = size - 1; i > -1; i--){
            if(employees[i].equals(o))
                return i;
        }

        return -1;
    }

    @Override
    public ListIterator<Employee> listIterator() {
        return new LinkedList<Employee>().listIterator();
    }

    @Override
    public ListIterator<Employee> listIterator(int index) {
        return new LinkedList<Employee>().listIterator(index);
    }

    @Override
    public List<Employee> subList(int fromIndex, int toIndex) {
        Employee[] employees = (Employee[]) toArray();
        if(fromIndex < toIndex && fromIndex >= 0 && toIndex <= size) {
            Project project = new Project(name);
            project.addAll(Arrays.asList(employees).subList(fromIndex, toIndex + 1));
            return project;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return list.contains((Employee) o);
    }

    @Override
    public Iterator<Employee> iterator() {
        return new LinkedList<Employee>().iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Employee[] employees = (Employee[]) toArray();
        if(a.length < employees.length)
            a = (T[]) new Employee[employees.length];

        System.arraycopy(employees, 0, a,0, size);

        return a;
    }

    @Override
    public boolean add(Employee employee) {
        return list.add(employee);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove((Employee) o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj instanceof Project && name == ((Project) obj).name && size == ((Project) obj).size) {
            Employee[] employee = (Employee[]) toArray();
            Employee[] employeeObject = (Employee[]) ((Project) obj).toArray();
            for(int i = 0; i < size; i++) {
                if(employee[i].equals(employeeObject[i]))
                    return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Project ").append(name).append(": ").append(size).append("\n");
        Employee[] employees = (Employee[]) toArray();
        for(int i = 0; i < size; i++){
            result.append(employees[i].toString()).append("\n");
        }
        return result.toString();
    }

    @Override
    public int hashCode() {
        Employee[] employees = (Employee[]) toArray();
        return name.hashCode() ^ size ^ employees.hashCode();
    }
}