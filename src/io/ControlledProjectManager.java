package io;

import humanResources.*;

import java.util.Collection;

public class ControlledProjectManager extends ProjectsManager {
    protected Source<EmployeeGroup> source;

    public ControlledProjectManager() {
        super();
    }

    public ControlledProjectManager(Node<EmployeeGroup> head) {
        super(head);
    }

    @Override
    public void addGroup(EmployeeGroup group) throws AlreadyAddedException {
        super.addGroup(group);
    }

    @Override
    public boolean removeGroup(String name) {
        return super.removeGroup(name);
    }

    @Override
    public int removeGroup(EmployeeGroup group) {
        return super.removeGroup(group);
    }

    @Override
    public boolean add(EmployeeGroup group) {
        return super.add(group);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
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
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return super.retainAll(c);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public EmployeeGroup set(int index, EmployeeGroup element) {
        return super.set(index, element);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        super.add(index, element);
    }

    @Override
    public EmployeeGroup remove(int index) {
        return super.remove(index);
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
