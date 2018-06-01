package io;

import humanResources.*;
import java.util.Collection;

//todo в основном используй foreach и не создавай массив
//todo для создания COntrolled... используй фабрику, ссылку на которую передаст сама фабрика при создании Менеджера
public class ControlledDepartmentManager extends DepartmentsManager{
    protected Source<EmployeeGroup> source;
    private EmployeeFactory factory,

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
    public void addGroup(EmployeeGroup group) throws AlreadyAddedException {
        ControlledDepartment controlledDepartment = new ControlledDepartment(group.getName(), (Employee[]) group.toArray());
        source.create(controlledDepartment);
        super.addGroup(controlledDepartment);
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
        ControlledDepartment controlledDepartment = new ControlledDepartment(group.getName(), (Employee[]) group.toArray());
        return source.create(controlledDepartment) && super.add(controlledDepartment);
    }

    @Override
    public boolean remove(Object o) {
        return source.delete((EmployeeGroup) o) && super.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        ControlledDepartment controlledDepartment;

        for (EmployeeGroup group : c) {
            controlledDepartment = new ControlledDepartment(group.getName(), (Employee[]) group.toArray());
            source.create(controlledDepartment);
        }

        return super.addAll(c);
    }

    //todo Здесь и далее коллекции в массив не переделываешь, а бегаешь по ним foreach-ем
    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        EmployeeGroup[] groups = (EmployeeGroup[]) c.toArray();
        ControlledDepartment controlledDepartment;

        for (int i = index; i < groups.length; i++) {
            controlledDepartment = new ControlledDepartment(groups[i].getName(), (Employee[]) groups[i].toArray());
            source.create(controlledDepartment);
        }

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
        //todo foreach-ем по c и если contains, то remove
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
        for (EmployeeGroup group : this) {
            source.delete(group);
        }

        super.clear();
    }

    @Override
    public EmployeeGroup set(int index, EmployeeGroup element) {
        ControlledDepartment controlledDepartment = new ControlledDepartment(element.getName(), (Employee[]) element.toArray());
        source.create(controlledDepartment);
        source.delete(get(index));
        return super.set(index, element);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        ControlledDepartment controlledDepartment = new ControlledDepartment(element.getName(), (Employee[]) element.toArray());
        source.create(controlledDepartment);
        super.add(index, controlledDepartment);
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
