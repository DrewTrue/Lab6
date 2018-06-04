package humanResources;

import io.*;

//todo пример реализации фабрики
public class TextFileBasedEmployeeFactory extends EmployeeFactory {
    private String path;

    public TextFileBasedEmployeeFactory(String path){
        this.path = path;
    }

    //todo 3 метода getPath & setPath
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

    //todo это пример реализации
    @Override
    public GroupsManager createDepartmentManager(String name) {
        //
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, this);
        controlledDepartmentManager.setSource( new GroupsManagerTextFileSource(path));
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createProjectManager() {
        ControlledProjectManager controlledProjectManager = new ControlledProjectManager(this);
        controlledProjectManager.setSource( new GroupsManagerTextFileSource(path));
        return controlledProjectManager;
    }

    @Override
    public EmployeeGroup createDepartment(String name, int size) {
        return new ControlledDepartment(name, size);
    }

    @Override
    public GroupsManager createDepartmentManager(String name, int size) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, size, this);
        controlledDepartmentManager.setSource( new GroupsManagerTextFileSource(path));
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createProjectManager(Node<EmployeeGroup> head) {
        ControlledProjectManager controlledProjectManager = new ControlledProjectManager(head, this);
        controlledProjectManager.setSource( new GroupsManagerTextFileSource(path));
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
        controlledDepartmentManager.setSource( new GroupsManagerTextFileSource(path));
        return controlledDepartmentManager;
    }
}
