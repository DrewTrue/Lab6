package humanResources;

import java.time.LocalDate;
import java.util.*;
import java.util.ListIterator;

//todo реализауй итератор
//todo после итреатора ты можешь проходиться по элементам департамента с помощью foreach
//todo не получай массив сотрудников, и используй foreach-и
public class DepartmentsManager implements GroupsManager{
    private String name;
    private EmployeeGroup[] groups;
    private int size;

    private final static int DEFAULT_SIZE = 8;

    public DepartmentsManager(String name) {
        this(name, new EmployeeGroup[DEFAULT_SIZE]);
    }

    public DepartmentsManager(String name, int size) {
        if(size < 0)
            throw new NegativeSizeException();
        this.size = size;
        this.name = name;
        this.groups = new EmployeeGroup[size];
    }

    public DepartmentsManager(String name, EmployeeGroup[] groups) {
        this.size = groups.length;
        this.name = name;
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public int getPartTimeEmployeesQuantity(){
        int quantity = 0;
        for(EmployeeGroup group : this) {
            quantity += group.getPartTimeEmployeesQuantity(); //todo исправь аналогично следующие 3 метода
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
            quantity += group.getCurrentTravellersQuantity();
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
                newEmployees[counter] = employee;
                counter++;
            }
        }
        return newEmployees;
    }

    @Override
    public EmployeeGroup getEmployeeGroup(String name) {
        for (EmployeeGroup group : this) {
            if (group != null && group.getName().equals(name))
                return group;
        }
        return null;
    }

    @Override
    public Employee mostValuableEmployee() {
        int maxSalary = 0, index = 0;
        Employee[] employees;
        for (int i = 0; i < size; i++) {
            if (groups[i] != null) {
                employees = groups[i].getEmployeesSortedBySalary();
                if (employees[0] != null & employees[0].getSalary() > maxSalary) {
                    maxSalary = employees[0].getSalary();
                    index = i;
                }
            }
        }
        employees = groups[index].getEmployeesSortedBySalary();
        return employees[0];
    }

    @Override
    public EmployeeGroup getEmployeesGroup(String firstName, String secondName) {
        Employee[] employees;
        for (EmployeeGroup group : this) {
            if (group != null) {
                employees = (Employee[]) group.toArray();
                for (Employee employee : employees) {
                    if (employee != null & (employee.getFirstName().equals(firstName)
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
        for (int i = 0; i < size; i++) {
            if(groups[i] != null) {
                for(int j = 0; j < groups[i].toArray().length; j++){
                    employees = (Employee[]) groups[i].toArray();
                    if(employees[j].getJobTitle().equals(jobTitle))
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
        for (int i = 0; i < size; i++) {
            if (groups[i] != null & groups[i].getName().equals(name)) {
                if(i < groups.length - 1)
                    System.arraycopy(groups, i + 1, groups, i, size - i - 1);
                groups[size - 1] = null;
                size--;
                return true;
            }
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
        for(EmployeeGroup group : this){
            if(o.equals(group))
                return true;
        }

        return false;
    }

    @Override
    public Iterator<EmployeeGroup> iterator() {
        return new LinkedList<EmployeeGroup>().iterator();
    }

    @Override
    public Object[] toArray() {
        return groups;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if(a.length < size)
            a = (T[]) new EmployeeGroup[size];

        System.arraycopy(groups, 0, a, 0, size);

        return null;
    }

    @Override
    public boolean add(EmployeeGroup group) throws AlreadyAddedException{
        EmployeeGroup[] groupsHelper = (EmployeeGroup[]) toArray();
        for (EmployeeGroup aGroupsHelper : groupsHelper) {
            if (group.equals(aGroupsHelper))
                throw new AlreadyAddedException();
        }
        if(group == null)
            return false;
        if (size == groups.length) {
            EmployeeGroup[] groups = new EmployeeGroup[this.groups.length * 2];
            System.arraycopy(this.groups, 0, groups,0, size);
            this.groups = groups;
        }
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] == null) {
                groups[i] = group;
                size++;
                break;
            }
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (groups[i].equals(o)) {
                if (i < groups.length - 1)
                    System.arraycopy(groups, i + 1, groups, i, size - i - 1);
                groups[size - 1] = null;
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int counter = 0;
        for (Object group : c) {
            for (EmployeeGroup employeeGroup : this) {
                if (group.equals(employeeGroup))
                    counter++;
            }
        }

        return counter == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        for (EmployeeGroup anEmployeesCollection : c) {
            try {
                this.add(anEmployeesCollection);
            } catch (AlreadyAddedException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        if (size == this.groups.length) {
            EmployeeGroup[] employeeGroups = new EmployeeGroup[this.groups.length * 2];
            System.arraycopy(this.groups,0, employeeGroups,0, size);
            this.groups = employeeGroups;

        }

        EmployeeGroup[] groupsHelper = new EmployeeGroup[groups.length];

        if(index > -1 && index < groups.length){
            System.arraycopy(this.groups, 0, groupsHelper, 0, index - 1);
            System.arraycopy(c, 0, groupsHelper, index, c.size());
            System.arraycopy(this.groups, index, groupsHelper, index + c.size(), size - index);
            this.groups = groupsHelper;
            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int counter = 0;
        for (Object group : c) {
            if (remove(group))
                counter++;
        }
        return counter > 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int counter = 0;
        for (Object item : c) {
            if(!this.contains(item)) {
                this.remove(item);
                counter++;
            }
        }
        return counter == 0;
    }

    @Override
    public void clear() {
        for(int i = 0; i < size; i++){
            groups[i] = null;
        }
        size = 0;
    }

    @Override
    public EmployeeGroup get(int index) {
        if(index > -1 && index < groups.length)
            return groups[index];
        return null;
    }

    @Override
    public EmployeeGroup set(int index, EmployeeGroup element) {
        if(index > -1 && index < groups.length)
            return groups[index] = element;
        return null;
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        if(element == null)
            return;

        if (size == this.groups.length) {
            EmployeeGroup[] employeeGroups = new EmployeeGroup[this.groups.length * 2];
            System.arraycopy(this.groups,0,employeeGroups,0,size);
            this.groups = employeeGroups;

        }

        EmployeeGroup[] employeeGroupsHelper = new EmployeeGroup[groups.length];

        if(index > -1 && index < groups.length){
            System.arraycopy(this.groups, 0, employeeGroupsHelper, 0, index - 1);
            employeeGroupsHelper[index] = element;
            System.arraycopy(this.groups, index, employeeGroupsHelper, index + 1, size - index);
            this.groups = employeeGroupsHelper;
        }
    }

    @Override
    public EmployeeGroup remove(int index) {
        if (index < groups.length - 1) {
            System.arraycopy(groups, index + 1, groups, index, size - index - 1);
            groups[size - 1] = null;
            size--;
            return groups[index];
        }

        return null;
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < size; i++){
            if(groups[i].equals(o))
                return i;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = size - 1; i > -1; i--){
            if(groups[i].equals(o))
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
        if(fromIndex < toIndex && fromIndex >= 0 && toIndex <= size) {
            DepartmentsManager departmentsManager = new DepartmentsManager(name, toIndex - fromIndex);
            departmentsManager.addAll(Arrays.asList(groups).subList(fromIndex, toIndex + 1));
            return departmentsManager;
        }
        return null;
    }
}