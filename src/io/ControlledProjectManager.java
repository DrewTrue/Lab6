package io;

import humanResources.*;

import java.util.Collection;

//todo та же фигня, что и в COntrolledDepartment Manager
public class ControlledProjectManager extends ProjectsManager {
    protected Source<EmployeeGroup> source;
    private EmployeeFactory factory;

    public ControlledProjectManager(EmployeeFactory factory) {
        super();
        this.factory = factory;
    }

    public ControlledProjectManager(Node<EmployeeGroup> head, EmployeeFactory factory) {
        super(head);
        this.factory = factory;
    }

    @Override
    public boolean removeGroup(String name) {
        return source.delete(getEmployeeGroup(name)) && super.removeGroup(name);
    }

    @Override
    public boolean add(EmployeeGroup group) {
        EmployeeGroup employeeGroup = factory.createProject(group.getName(), (Employee[]) group.toArray());
        return source.create(employeeGroup) && super.add(employeeGroup);
    }

    @Override
    public boolean remove(Object o) {
        return source.delete((EmployeeGroup) o) && super.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        EmployeeGroup employeeGroup;

        for (EmployeeGroup group : this) {
            employeeGroup = factory.createProject(group.getName(), (Employee[]) group.toArray());
            source.create(employeeGroup);
        }
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        EmployeeGroup employeeGroup;
        for (EmployeeGroup group : this) {
            employeeGroup = factory.createProject(group.getName(), (Employee[]) group.toArray());
            source.create(employeeGroup);
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
        EmployeeGroup employeeGroup = factory.createProject(element.getName(), (Employee[]) element.toArray());
        source.create(employeeGroup);
        source.delete(get(index));
        return super.set(index, employeeGroup);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        EmployeeGroup employeeGroup = factory.createProject(element.getName(), (Employee[]) element.toArray());
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
