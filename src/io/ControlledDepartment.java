package io;

import humanResources.*;

import java.util.Collection;

public class ControlledDepartment extends Department{
    protected boolean isChanged;

    private final static boolean DEFAULT_ISCHANGED = false;

    public ControlledDepartment(String name) {
        super(name);
        isChanged = DEFAULT_ISCHANGED;
    }

    public ControlledDepartment(String name, int size) {
        super(name, size);
        isChanged = DEFAULT_ISCHANGED;
    }

    public ControlledDepartment(String name, Employee[] employees) {
        super(name, employees);
        isChanged = DEFAULT_ISCHANGED;
    }

    protected boolean isChanged(){
        return isChanged;
    }

    @Override
    public void add(int index, Employee element){
        super.add(index,element);
        //isChanged = !DEFAULT_ISCHANGED; super mind
        isChanged = true;
    }

    @Override
    public Employee set(int index, Employee element){
        Employee oldEmployee = super.set(index, element);
        if(oldEmployee != null)
            isChanged = true;

        return oldEmployee;
    }
    //todo здесь и далее, результат операции в super записываешь в переменную и работаешь с ней
    @Override
    public Employee remove(int index){
        if(super.remove(index) != null)
            isChanged = true;
        return super.remove(index);
    }

    @Override
    public boolean add(Employee employee){
        if(super.add(employee))
            return isChanged = true;
        return isChanged;
    }

    @Override
    public boolean remove(Object o){
        if(super.remove(o))
            return isChanged = true;
        return isChanged;
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        if(super.addAll(c))
            return isChanged = true;
        return isChanged;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        if(super.addAll(index, c))
            return isChanged = true;
        return isChanged;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if(super.removeAll(c))
            return isChanged = true;
        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if(super.removeAll(c))
            return isChanged = true;
        return isChanged;
    }

    @Override
    public void clear(){
        super.clear();
        isChanged = true;
    }

    @Override
    public void addEmployee(Employee employee) throws AlreadyAddedException {
        super.addEmployee(employee);
        isChanged = true;
    }

    @Override
    public boolean removeEmployee(String firstName, String secondName){
        if(super.removeEmployee(firstName,secondName))
            return isChanged = true;
        return isChanged;
    }

    @Override
    public boolean removeEmployee(Employee employee) {
        if(super.removeEmployee(employee))
            return isChanged = true;
        return isChanged;
    }

    @Override
    public int removeAll(JobTitlesEnum jobTitle){
        if(super.removeAll(jobTitle) != 0)
            isChanged = true;
        return super.removeAll(jobTitle);
    }

    @Override
    public void setName(String name){
        super.setName(name);
        isChanged = true;
    }
}
