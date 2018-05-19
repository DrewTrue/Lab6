package io;

import humanResources.EmployeeGroup;

public abstract class GroupsManagerFileSource implements FileSource{
    private String path;

    public GroupsManagerFileSource(String path){
        this.path = path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

    public abstract void load(EmployeeGroup employeeGroup);

    public abstract void store(EmployeeGroup employeeGroup);

    public abstract boolean delete(EmployeeGroup employeeGroup);

    public abstract boolean create(EmployeeGroup employeeGroup);
}
