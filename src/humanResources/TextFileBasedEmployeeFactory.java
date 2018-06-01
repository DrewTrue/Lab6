package humanResources;

import io.*;

//todo пример реализации фабрики
public class TextFileBasedEmployeeFactory extends EmployeeFactory {
    private String path;

    public TextFileBasedEmployeeFactory(String path){
        this.path = path;
    }

    //todo 3 метода getPath & setPath

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
        ControlledProjectManager controlledProjectManager = new ControlledProjectManager();
        EmployeeGroup[] groups = controlledProjectManager.getEmployeesGroups();

        for (EmployeeGroup group : groups) {
            source.create(group);
        }

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
        EmployeeGroup[] groups = controlledDepartmentManager.getEmployeesGroups();

        for (EmployeeGroup group : groups) {
            source.create(group);
        }

        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createProjectManager(Node<EmployeeGroup> head) {
        ControlledProjectManager controlledProjectManager = new ControlledProjectManager(head);
        EmployeeGroup[] groups = controlledProjectManager.getEmployeesGroups();

        for (EmployeeGroup group : groups) {
            source.create(group);
        }
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

        for (EmployeeGroup group : groups) {
            source.create(group);
        }

        return controlledDepartmentManager;
    }
}
