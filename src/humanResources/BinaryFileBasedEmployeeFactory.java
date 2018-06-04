package humanResources;

import io.*;

public class BinaryFileBasedEmployeeFactory extends EmployeeFactory {
    private String path;

    public BinaryFileBasedEmployeeFactory(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public EmployeeGroup createDepartment(String name) {
        return new ControlledDepartment(name);
    }

    @Override
    public EmployeeGroup createProject(String name) {
        return new ControlledProject(name);
    }

    @Override
    public GroupsManager createDepartmentManager(String name) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, this);
        controlledDepartmentManager.setSource( new GroupsManagerBinaryFileSource(path));
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createProjectManager() {
        ControlledProjectManager controlledProjectManager = new ControlledProjectManager(this);
        controlledProjectManager.setSource( new GroupsManagerBinaryFileSource(path));
        return controlledProjectManager;
    }

    @Override
    public EmployeeGroup createDepartment(String name, int size) {
        return new ControlledDepartment(name, size);
    }

    @Override
    public GroupsManager createDepartmentManager(String name, int size) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, size, this);
        controlledDepartmentManager.setSource( new GroupsManagerBinaryFileSource(path));
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createProjectManager(Node<EmployeeGroup> head) {
        ControlledProjectManager controlledProjectManager = new ControlledProjectManager(head, this);
        controlledProjectManager.setSource( new GroupsManagerBinaryFileSource(path));
        return controlledProjectManager;
    }

    @Override
    public EmployeeGroup createDepartment(String name, Employee[] employees) {
        return new ControlledDepartment(name, employees);
    }

    @Override
    public EmployeeGroup createProject(String name, Employee[] employees) {
        return new ControlledProject(name, employees);
    }

    @Override
    public GroupsManager createDepartmentManager(String name, EmployeeGroup[] groups) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, groups, this);
        controlledDepartmentManager.setSource( new GroupsManagerBinaryFileSource(path));
        return controlledDepartmentManager;
    }
}
