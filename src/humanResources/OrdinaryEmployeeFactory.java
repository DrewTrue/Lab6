package humanResources;

public class OrdinaryEmployeeFactory extends EmployeeFactory {
    @Override
    public EmployeeGroup createDepartment(String name) {
        return new Department(name);
    }

    @Override
    public EmployeeGroup createProject(String name) {
        return new Project(name);
    }

    @Override
    public GroupsManager createDepartmentManager(String name) {
        return new DepartmentsManager(name);
    }

    @Override
    public GroupsManager createProjectManager() {
        return new ProjectsManager();
    }

    @Override
    public EmployeeGroup createDepartment(String name, int size) {
        return new Department(name, size);
    }

    @Override
    public GroupsManager createDepartmentManager(String name, int size) {
        return new DepartmentsManager(name, size);
    }

    @Override
    public GroupsManager createProjectManager(Node<EmployeeGroup> head) {
        return new ProjectsManager(head);
    }

    @Override
    public EmployeeGroup createDepartment(String name, Employee[] employees) {
        return new Department(name, employees);
    }

    @Override
    public EmployeeGroup createProject(String name, Employee[] employees) {
        return new Project(name, employees);
    }

    @Override
    public GroupsManager createDepartmentManager(String name, EmployeeGroup[] groups) {
        return new DepartmentsManager(name, groups);
    }
}
