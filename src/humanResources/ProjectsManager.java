package humanResources;

import java.time.LocalDate;
import java.util.*;
import java.util.ListIterator;

//todo аналогично департмент менеджеру
public class ProjectsManager implements GroupsManager{
    private LinkedList<EmployeeGroup> list;
    private int size;

    private static final int DEFAULT_SIZE = 1;

    public ProjectsManager(){
        this(new Node<>(null));
    }

    public ProjectsManager(Node<EmployeeGroup> head){
        list.add(head.getValue());
        this.size = DEFAULT_SIZE;
    }

    @Override
    public int getPartTimeEmployeesQuantity(){
        int quantity = 0;
        for(EmployeeGroup group : this) {
            quantity += group.getPartTimeEmployeesQuantity();
        }
        return quantity;
    }

    @Override
    public int getStaffEmployeesQuantity(){
        int quantity = 0;
        for(EmployeeGroup group : this){
            quantity += group.getStaffEmployeesQuantity();
        }
        return quantity;
    }

    @Override
    public int getCurrentTravellersQuantity(){
        int quantity = 0;
        for(EmployeeGroup group : this) {
            quantity += getCurrentTravellersQuantity();
        }
        return quantity;
    }

    @Override
    public Employee[] getCurrentTravellers(LocalDate beginTravelMark, LocalDate endTravelMark){
        Employee[] newEmployees = new Employee[getStaffEmployeesQuantity()];
        Employee[] employees;
        int counter = 0;
        for(EmployeeGroup group : this) {
            employees = group.getCurrentTravellers(beginTravelMark, endTravelMark);
            for (Employee employee : employees) {
                if (((StaffEmployee) employee).getTravelDaysFromTimeLapse(beginTravelMark, endTravelMark) > 0)
                    newEmployees[counter] = employee;
            }
        }
        return newEmployees;
    }

    @Override
    public EmployeeGroup getEmployeeGroup(String name) {;
        for (EmployeeGroup group : this) {
            if (group != null && group.getName().equals(name))
                return group;
        }
        return null;
    }

    @Override
    public Employee mostValuableEmployee() {
        int maxSalary = 0, index = 0;
        Employee[] employee;
        EmployeeGroup[] groups = (EmployeeGroup[]) toArray();
        for (int i = 0; i < size; i++) {
            if (groups[i] != null) {
                employee = groups[i].getEmployeesSortedBySalary();
                if (employee[0] != null && employee[0].getSalary() > maxSalary) {
                    maxSalary = employee[0].getSalary();
                    index = i;
                }
            }
        }
        employee = groups[index].getEmployeesSortedBySalary();
        return employee[0];
    }

    @Override
    public EmployeeGroup getEmployeesGroup(String firstName, String secondName) {
        Employee[] employees;
        for (EmployeeGroup group : this) {
            if (group != null) {
                employees = (Employee[]) group.toArray();
                for(Employee employee : employees) {
                    if (employee != null && (employee.getFirstName().equals(firstName)
                            && employee.getSecondName().equals(secondName)))
                        return group;
                }
            }
        }
        return null;
    }

    @Override
    public int employeesQuantity(JobTitlesEnum jobTitle) {
        int quantity = 0;
        Employee[] employees;
        for (EmployeeGroup group : this) {
            if(group != null) {
                employees = (Employee[]) group.toArray();
                for(Employee employee : employees){
                    if(employee.getJobTitle().equals(jobTitle))
                        quantity++;
                }
            }
        }
        return quantity;
    }

    @Override
    public int employeesQuantity() {
        int quantity = 0;
        for (EmployeeGroup group : this) {
            if(group != null) {
                quantity += group.size();
            }
        }
        return quantity;
    }

    @Override
    public boolean removeGroup(String name) {
        for(EmployeeGroup group : this){
            if(group.getName().equals(name))
                return list.remove(group);
        }
        return false;
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
        return list.contains((EmployeeGroup) o);
    }

    @Override
    public Iterator<EmployeeGroup> iterator() {
        return new LinkedList<EmployeeGroup>().iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        EmployeeGroup[] employeeGroups = (EmployeeGroup[]) toArray();

        if(a.length < size)
            a = (T[]) new EmployeeGroup[size];

        System.arraycopy(employeeGroups, 0, a, 0, size);

        return null;
    }

    @Override
    public boolean add(EmployeeGroup group) throws AlreadyAddedException {
        return list.add(group);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove((EmployeeGroup) o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
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
    public EmployeeGroup get(int index) {
        EmployeeGroup[] employeeGroups = (EmployeeGroup[]) toArray();
        for(int i = 0; i < size; i++){
            if(i == index)
                return employeeGroups[i];
        }
        return null;
    }

    @Override
    public EmployeeGroup set(int index, EmployeeGroup element) {
        return list.set(index, element);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        list.add(index, element);
    }

    @Override
    public EmployeeGroup remove(int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        EmployeeGroup[] employeeGroups = (EmployeeGroup[]) toArray();

        for(int i = 0; i < size; i++){
            if(employeeGroups[i].equals(o))
                return i;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        EmployeeGroup[] employeeGroups = (EmployeeGroup[]) toArray();

        for(int i = size - 1; i > -1; i--){
            if(employeeGroups[i].equals(o))
                return i;
        }

        return -1;
    }

    @Override
    public ListIterator<EmployeeGroup> listIterator() {
        return new LinkedList<EmployeeGroup>().listIterator();
    }

    @Override
    public ListIterator<EmployeeGroup> listIterator(int index) {
        return new LinkedList<EmployeeGroup>().listIterator(index);
    }

    @Override
    public List<EmployeeGroup> subList(int fromIndex, int toIndex) {
        EmployeeGroup[] groups = (EmployeeGroup[]) toArray();

        if(fromIndex < toIndex && fromIndex >= 0 && toIndex <= size) {
            ProjectsManager projectsManager = new ProjectsManager();
            projectsManager.addAll(Arrays.asList(groups).subList(fromIndex, toIndex + 1));
            return projectsManager;
        }

        return null;
    }
}
