package io;

import humanResources.*;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;

public class GroupsManagerBinaryFileSource extends GroupsManagerFileSource {
    public GroupsManagerBinaryFileSource(String path) {
        super(path);
    }

    @Override
    public void load(EmployeeGroup employeeGroup) {
        DataInputStream in = null;
        String path = getPath() + employeeGroup.getName() + ".txt";

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
        int travelsQuantity;

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
                        employeeGroup.addEmployee(employee);
                    } catch (AlreadyAddedException e) {
                        e.printStackTrace();
                    }
                } else {
                    employee.setBonus(in.read());
                    travelsQuantity = in.read();
                    ((StaffEmployee) employee).setTravelsQuantity(travelsQuantity);
                    for(int i = 0; i < travelsQuantity; i++) {
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
    public void store(EmployeeGroup employeeGroup) {
        Employee[] employee;
        BusinessTravel[] businessTravels;
        File file = new File(getPath(), employeeGroup.getName() + ".bin");

        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            employee = employeeGroup.getEmployees();

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
                }
            }
            assert out != null;
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(EmployeeGroup employeeGroup) {
        String path = getPath() + employeeGroup.getName() + ".bin";

        try {
            Files.delete(Paths.get(path));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean create(EmployeeGroup employeeGroup) {
        Employee[] employee;
        BusinessTravel[] businessTravels;
        File file;
        DataOutputStream out = null;

        try{
            file = new File(getPath(), employeeGroup.getName() + ".bin");
            file.createNewFile();

            out = new DataOutputStream(new FileOutputStream(file));
            employee = employeeGroup.getEmployees();

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
