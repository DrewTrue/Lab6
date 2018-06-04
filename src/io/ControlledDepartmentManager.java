package io;

import humanResources.*;
import java.util.Collection;

//todo в основном используй foreach и не создавай массив
//todo для создания COntrolled... используй фабрику, ссылку на которую передаст сама фабрика при создании Менеджера
public class ControlledDepartmentManager extends DepartmentsManager{
    protected Source<EmployeeGroup> source;
    private EmployeeFactory factory;

    public ControlledDepartmentManager(String name, EmployeeFactory factory) {
        super(name);
        this.factory = factory;
    }

    public ControlledDepartmentManager(String name, int size, EmployeeFactory factory) {
        super(name, size);
        this.factory = factory;
    }

    public ControlledDepartmentManager(String name, EmployeeGroup[] groups, EmployeeFactory factory) {
        super(name, groups);
        this.factory = factory;
    }

    @Override
    public boolean removeGroup(String name) {
        return source.delete(getEmployeeGroup(name)) && super.removeGroup(name);
    }

    @Override
    public boolean add(EmployeeGroup group) {
        EmployeeGroup employeeGroup = factory.createDepartment(group.getName(), (Employee[]) group.toArray());
        return source.create(employeeGroup) && super.add(employeeGroup);
    }

    @Override
    public boolean remove(Object o) {
        return source.delete((EmployeeGroup) o) && super.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        EmployeeGroup employeeGroup;
        for (EmployeeGroup group : c) {
            employeeGroup = factory.createDepartment(group.getName(), (Employee[]) group.toArray());
            source.create(employeeGroup);
        }
        return super.addAll(c);
    }

    //todo Здесь и далее коллекции в массив не переделываешь, а бегаешь по ним foreach-ем
    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        EmployeeGroup employeeGroup;
        for (EmployeeGroup group : c) {
            employeeGroup = factory.createDepartment(group.getName(), (Employee[]) group.toArray());
            source.create(employeeGroup);
        }
        return super.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object item : c) {
            source.delete((EmployeeGroup) item);
        }
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        //todo foreach-ем по c и если contains, то remove
        for(Object item : c) {
            if(!this.contains(item)){
                source.delete((EmployeeGroup) item);
            }
        }
        return super.retainAll(c);
    }

    @Override
    public void clear() {
        for (EmployeeGroup group : this) {
            source.delete(group);
        }
        super.clear();
    }

    @Override
    public EmployeeGroup set(int index, EmployeeGroup element) {
        EmployeeGroup employeeGroup = factory.createDepartment(element.getName(), (Employee[]) element.toArray());
        source.create(employeeGroup);
        source.delete(get(index));
        return super.set(index, employeeGroup);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        EmployeeGroup employeeGroup = factory.createDepartment(element.getName(), (Employee[]) element.toArray());
        source.create(employeeGroup);
        super.add(index, employeeGroup);
    }

    @Override
    public EmployeeGroup remove(int index) {
        if(source.delete(get(index)))
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
        for (EmployeeGroup group : this) {
            source.load(group);
        }
    }

    public void store(){
        for (EmployeeGroup group : this) {
            if (((ControlledDepartment) group).isChanged)
                source.store(group);
        }
    }
}
