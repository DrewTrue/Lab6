package io;

import humanResources.*;

import java.util.Collection;

public class ControlledProject extends Project {
    protected boolean isChanged;

    public ControlledProject(String name) {
        super(name);
    }

    public ControlledProject(String name, Employee[] employees) {
        super(name, employees);
    }

    //todo здесь и далее, результат операции в super записываешь в переменную и работаешь с ней
    @Override
    public boolean removeEmployee(String firstName, String secondName){
        if(super.removeEmployee(firstName,secondName)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public void setName(String name){
        super.setName(name);
        isChanged = true;
    }

    @Override
    public Employee set(int index, Employee element) {
        Employee oldEmployee = super.set(index, element);
        if(oldEmployee != null)
            isChanged = true;
        return oldEmployee;
    }

    @Override
    public void add(int index, Employee element) {
        super.add(index, element);
        isChanged = true;
    }

    @Override
    public Employee remove(int index){
        Employee removedEmployee = super.remove(index);
        if(removedEmployee != null)
            isChanged = true;
        return removedEmployee;
    }

    @Override
    public boolean add(Employee employee) {
        if(super.add(employee)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if(super.remove(o)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        if(super.addAll(c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        if(super.addAll(index, c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if(super.removeAll(c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if(super.retainAll(c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public void clear(){
        super.clear();
        isChanged = true;
    }
}
