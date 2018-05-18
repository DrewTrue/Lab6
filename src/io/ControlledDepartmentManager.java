package io;

import humanResources.*;
import java.util.Collection;

public class ControlledDepartmentManager extends DepartmentsManager{
    protected Source<EmployeeGroup> source;

    public ControlledDepartmentManager(String name) {
        super(name);
    }

    public ControlledDepartmentManager(String name, int size) {
        super(name, size);
    }

    public ControlledDepartmentManager(String name, EmployeeGroup[] groups) {
        super(name, groups);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void addGroup(EmployeeGroup group) throws AlreadyAddedException {
        super.addGroup(group);
    }

    @Override
    public boolean removeGroup(String name) {
        return source.delete(getEmployeeGroup(name)) && super.removeGroup(name);
    }

    @Override
    public int removeGroup(EmployeeGroup group) {
        if(source.delete(group))
            return super.removeGroup(group);
        return 0;
    }

    @Override
    public boolean add(EmployeeGroup group) {
        //EmployeeGroup employeeGroup = group;
        source.create(group);
        return super.add(group);
    }

    @Override
    public boolean remove(Object o) {
        return source.delete((EmployeeGroup) o) && super.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {


        return super.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        EmployeeGroup[] employeeGroups = (EmployeeGroup[]) c.toArray();

        for (EmployeeGroup employeeGroup : employeeGroups) {
            source.delete(employeeGroup);
        }

        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        EmployeeGroup[] collectionGroups = (EmployeeGroup[]) c.toArray();
        EmployeeGroup[] employeeGroups = getEmployeesGroups();

        for(int j = 0; j < employeeGroups.length; j++) {
            for (int i = 0; i < collectionGroups.length; i++) {
                if (!collectionGroups[i].contains(employeeGroups[j]))
                    source.delete(employeeGroups[i]);
            }
        }

        return super.retainAll(c);
    }

    @Override
    public void clear() {
        EmployeeGroup[] groups = getEmployeesGroups();

        for(int i = 0; i < groups.length; i++){
            source.delete(groups[i]);
        }

        super.clear();
    }

    @Override
    public EmployeeGroup set(int index, EmployeeGroup element) {
        source.create(element);
        return super.set(index, element);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        source.create(element);
        super.add(index, element);
    }

    @Override
    public EmployeeGroup remove(int index) {
        EmployeeGroup[] groups = getEmployeesGroups();
        if(source.delete(groups[index]))
            return super.remove(index);
        return null;
    }

    public Source<EmployeeGroup> getSource() {
        return source;
    }

    public void setSource(Source<EmployeeGroup> source) {
        this.source = source;
    }

    public void load(){
        EmployeeGroup[] groups = getEmployeesGroups();

        for (EmployeeGroup group : groups) {
            source.load(group);
        }
    }

    public void store(){
        EmployeeGroup[] groups = getEmployeesGroups();

        for (EmployeeGroup group : groups) {
            if (((ControlledDepartment) group).isChanged)
                source.store(group);
        }
    }
}
