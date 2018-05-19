package humanResources;

import io.*;

public class BinaryFileBasedEmployeeFactory extends EmployeeFactory {
    private GroupsManagerBinaryFileSource source;

    public BinaryFileBasedEmployeeFactory(String path){
        source = new GroupsManagerBinaryFileSource(path);
    }

    public GroupsManagerBinaryFileSource getSource() {
        return source;
    }

    public void setSource(GroupsManagerBinaryFileSource source) {
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
        EmployeeGroup[] groups = controlledDepartmentManager.getEmployeesGroups();

        for (EmployeeGroup group : groups) {
            source.create(group);
        }

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
