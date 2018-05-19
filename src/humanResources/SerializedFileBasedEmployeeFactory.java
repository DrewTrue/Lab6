package humanResources;

import io.*;

public class SerializedFileBasedEmployeeFactory extends EmployeeFactory {
    private GroupsManagerSerializedFileSource source;

    public SerializedFileBasedEmployeeFactory(String path){
        source = new GroupsManagerSerializedFileSource(path);
    }

    public GroupsManagerSerializedFileSource getSource() {
        return source;
    }

    public void setSource(GroupsManagerSerializedFileSource source) {
        this.source = source;
    }

    @Override
    public EmployeeGroup createDepartment(String name) {
        ControlledDepartment controlledDepartment = new ControlledDepartment(name);
        source.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public EmployeeGroup createProject(String name) {
        ControlledProject controlledProject = new ControlledProject(name);
        source.create(controlledProject);
        return controlledProject;
    }

    @Override
    public GroupsManager createDepartmentManager(String name) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name);
        source.create(controlledDepartmentManager);
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createProjectManager() {
        ControlledProjectManager controlledProjectManager = new ControlledProjectManager();
        source.create(controlledProjectManager);
        return controlledProjectManager;
    }

    @Override
    public EmployeeGroup createDepartment(String name, int size) {
        ControlledDepartment controlledDepartment = new ControlledDepartment(name, size);
        source.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public GroupsManager createDepartmentManager(String name, int size) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, size);
        source.create(controlledDepartmentManager);
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createProjectManager(Node<EmployeeGroup> head) {
        ControlledProjectManager controlledProjectManager = new ControlledProjectManager(head);
        source.create(controlledProjectManager);
        return controlledProjectManager;
    }

    @Override
    public EmployeeGroup createDepartment(String name, Employee[] employees) {
        ControlledDepartment controlledDepartment = new ControlledDepartment(name, employees);
        source.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public EmployeeGroup createProject(String name, Employee[] employees) {
        ControlledProject controlledProject = new ControlledProject(name, employees);
        source.create(controlledProject);
        return controlledProject;
    }

    @Override
    public GroupsManager createDepartmentManager(String name, EmployeeGroup[] groups) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, groups);
        source.create(controlledDepartmentManager);
        return controlledDepartmentManager;
    }
}