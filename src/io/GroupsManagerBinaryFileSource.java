package io;

import humanResources.*;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;

public class GroupsManagerBinaryFileSource extends GroupsManagerFileSource {
    private final static String DEFAULT_END_OF = "end of creating";

    public GroupsManagerBinaryFileSource(String path) {
        super(path);
    }

    @Override
    public void load(Object o) {
        DataInputStream in = null;
        String path = getPath() + ((EmployeeGroup)o).getName() + ".txt";

        try {
            in = new DataInputStream(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Employee employee;
        BusinessTravel businessTravel;
        String firstName;
        String secondName;
        int compensation;
        LocalDate beginTravel;
        LocalDate endTravel;
        String description;
        String destination;

        try {
            assert in != null;
            while (!in.readUTF().equals("")) {
                firstName = in.readUTF();
                secondName = in.readUTF();
                employee = new StaffEmployee(firstName, secondName);
                employee.setSalary(in.read());
                employee.setJobTitle(JobTitlesEnum.valueOf(in.readUTF()));
                employee.setBonus(in.read());
                if (employee.getBonus() == 0) {
                    try {
                        ((EmployeeGroup) o).addEmployee(employee);
                    } catch (AlreadyAddedException e) {
                        e.printStackTrace();
                    }
                } else {
                    employee.setBonus(in.read());
                    ((StaffEmployee) employee).setTravelsQuantity(in.read());
                    while (!in.readUTF().equals(DEFAULT_END_OF)) {
                        beginTravel = LocalDate.parse(in.readUTF());
                        endTravel = LocalDate.parse(in.readUTF());
                        compensation = in.read();
                        description = in.readUTF();
                        destination = in.readUTF();
                        businessTravel = new BusinessTravel(destination, beginTravel, endTravel, compensation, description);
                        ((StaffEmployee) employee).add(businessTravel);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(Object o) {
        Employee[] employee;
        BusinessTravel[] businessTravels;
        File file = new File(getPath(), ((EmployeeGroup)o).getName() + ".bin");

        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            employee = ((EmployeeGroup)o).getEmployees();

            for (Employee anEmployee : employee) {
                assert out != null;
                out.writeUTF(anEmployee.getClass().getSimpleName());
                out.writeUTF(anEmployee.getFirstName());
                out.writeUTF(anEmployee.getSecondName());
                out.writeUTF(anEmployee.getJobTitle().toString());
                out.write(anEmployee.getSalary());

                if (anEmployee instanceof PartTimeEmployee) {
                    out.write((anEmployee).getBonus());
                }

                if (anEmployee instanceof StaffEmployee) {
                    out.write((anEmployee).getBonus());
                    out.write(((StaffEmployee) anEmployee).getTravelsQuantity());

                    businessTravels = ((StaffEmployee) anEmployee).getTravels();

                    for (BusinessTravel businessTravel : businessTravels) {
                        out.writeUTF(businessTravel.getBeginTravel().toString());
                        out.writeUTF(businessTravel.getEndTravel().toString());
                        out.write(businessTravel.getCompensation());
                        out.writeUTF(businessTravel.getDescription());
                        out.writeUTF(businessTravel.getDestination());
                    }
                    out.writeUTF(DEFAULT_END_OF);
                }
            }
            assert out != null;
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Object o) {
        String path = getPath() + ((EmployeeGroup)o).getName() + ".bin";

        try {
            Files.delete(Paths.get(path));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean create(Object o) {
        Employee[] employee;
        BusinessTravel[] businessTravels;
        File file;
        DataOutputStream out = null;

        try{
            file = new File(getPath(), ((EmployeeGroup)o).getName() + ".bin");
            file.createNewFile();

            out = new DataOutputStream(new FileOutputStream(file));
            employee = ((EmployeeGroup)o).getEmployees();

            for (Employee anEmployee : employee) {
                assert out != null;
                out.writeUTF(anEmployee.getClass().getSimpleName());
                out.writeUTF(anEmployee.getFirstName() + " " + anEmployee.getSecondName());
                out.writeUTF(anEmployee.getJobTitle() + " " + anEmployee.getSalary());

                if (anEmployee instanceof PartTimeEmployee) {
                    out.write((anEmployee).getBonus());
                }

                if (anEmployee instanceof StaffEmployee) {
                    out.writeUTF((anEmployee).getBonus() + " " + ((StaffEmployee) anEmployee).getTravelsQuantity());

                    businessTravels = ((StaffEmployee) anEmployee).getTravels();

                    for (BusinessTravel businessTravel : businessTravels) {
                        out.writeUTF(businessTravel.getBeginTravel().toString());
                        out.writeUTF(businessTravel.getEndTravel().toString());
                        out.write(businessTravel.getCompensation());
                        out.writeUTF(businessTravel.getDescription());
                        out.writeUTF(businessTravel.getDestination());
                    }
                    out.writeUTF(DEFAULT_END_OF);
                }
            }
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
