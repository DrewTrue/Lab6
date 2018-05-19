package humanResources;

public abstract class EmployeeFactory {
    public abstract EmployeeGroup createDepartment(String name);
    public abstract EmployeeGroup createProject(String name);

    public abstract GroupsManager createDepartmentManager(String name);
    public abstract GroupsManager createProjectManager();

    public abstract EmployeeGroup createDepartment(String name, int size);

    public abstract GroupsManager createDepartmentManager(String name, int size);
    public abstract GroupsManager createProjectManager(Node<EmployeeGroup> head);

    public abstract EmployeeGroup createDepartment(String name, Employee[] employees);
    public abstract EmployeeGroup createProject(String name, Employee[] employees);

    public abstract GroupsManager createDepartmentManager(String name, EmployeeGroup[] groups);

    public static EmployeeFactory getEmployeeFactory(OrdersFactoryTypesEnumeration type, String path){
        switch (type) {
            case ORDINARY_GROUPS_FACTORY:
                return new OrdinaryEmployeeFactory();
            case TEXT_FILE_BASED_GROUPS_FACTORY:
                return new TextFileBasedEmployeeFactory(path);
            case BINARY_FILE_BASED_GROUPS_FACTORY:
                return new BinaryFileBasedEmployeeFactory(path);
            case SERIALIZED_FILE_BASED_GROUPS_FACTORY:
                return new SerializedFileBasedEmployeeFactory(path);
            case SOCKET_BASED_GROUPS_FACTORY:
                return null;
        }

        return null;
    }
}
