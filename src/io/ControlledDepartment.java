package io;

import humanResources.*;

import java.util.Collection;

public class ControlledDepartment extends Department{
    protected boolean isChanged;

    public ControlledDepartment(String name) {
        super(name);
    }

    public ControlledDepartment(String name, int size) {
        super(name, size);
    }

    public ControlledDepartment(String name, Employee[] employees) {
        super(name, employees);
    }

    protected boolean isChanged(){
        return isChanged;
    }

    @Override
    public void add(int index, Employee element){
        super.add(index,element);
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
        Employee removedEmployee = super.remove(index);
        if(removedEmployee != null)
            isChanged = true;
        return removedEmployee;
    }

    @Override
    public boolean add(Employee employee){
        if(super.add(employee)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o){
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
        if(super.removeAll(c)) {
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

    @Override
    public boolean removeEmployee(String firstName, String secondName){
        if(super.removeEmployee(firstName,secondName)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public int removeAll(JobTitlesEnum jobTitle){
        int removedEmployeesQuantity = super.removeAll(jobTitle);
        if(removedEmployeesQuantity != 0)
            isChanged = true;
        return removedEmployeesQuantity;
    }

    @Override
    public void setName(String name){
        super.setName(name);
        isChanged = true;
    }
}
