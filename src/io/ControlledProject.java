package io;

import humanResources.*;

import java.util.Collection;

public class ControlledProject extends Project {
    protected boolean isChanged;

    private final static boolean DEFAULT_ISCHANGED = false;

    public ControlledProject(String name) {
        super(name);
        isChanged = DEFAULT_ISCHANGED;
    }

    public ControlledProject(String name, Employee[] employees) {
        super(name, employees);
        isChanged = DEFAULT_ISCHANGED;
    }

    @Override
    public void addEmployee(Employee employee) throws AlreadyAddedException{
        super.addEmployee(employee);
        isChanged = true;
    }

    //todo здесь и далее, результат операции в super записываешь в переменную и работаешь с ней
    @Override
    public boolean removeEmployee(String firstName, String secondName){
        if(super.removeEmployee(firstName,secondName))
            return isChanged = true;
        return isChanged;
    }

    @Override
    public boolean removeEmployee(Employee employee){
        if(super.removeEmployee(employee))
            return isChanged = true;
        return isChanged;
    }

    @Override
    public void setName(String name){
        super.setName(name);
        isChanged = true;
    }

    @Override
    public Employee set(int index, Employee element) {
        if(super.set(index,element) != null)
            isChanged = true;
        return super.set(index, element);
    }

    @Override
    public void add(int index, Employee element) {
        super.add(index, element);
        isChanged = true;
    }

    @Override
    public Employee remove(int index){
        if(super.remove(index) != null)
            isChanged = true;
        return super.remove(index);
    }

    @Override
    public boolean add(Employee employee) {
        if(super.add(employee))
            return isChanged = true;
        return isChanged;
    }

    @Override
    public boolean remove(Object o) {
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
        if(super.retainAll(c))
            return isChanged = true;
        return isChanged;
    }

    @Override
    public void clear(){
        super.clear();
        isChanged = true;
    }
}
