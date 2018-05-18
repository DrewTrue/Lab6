package io;

import humanResources.*;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.Scanner;

public class GroupsManagerTextFileSource extends GroupsManagerFileSource{
    private final static String DEFAULT_END_OF = "end of creating";

    public GroupsManagerTextFileSource(String path) {
        super(path);
    }

    @Override
    public void load(Object o) {
        Scanner in = new Scanner(getPath() + ((EmployeeGroup)o).getName() + ".txt");

        Employee employee;
        BusinessTravel businessTravel;
        String firstName;
        String secondName;
        int compensation;
        LocalDate beginTravel;
        LocalDate endTravel;
        String description;
        String destination;

        while(!in.nextLine().equals("")){
            firstName = in.nextLine();
            secondName = in.nextLine();
            employee = new StaffEmployee(firstName, secondName);
            employee.setSalary(in.nextInt());
            employee.setJobTitle(JobTitlesEnum.valueOf(in.nextLine()));
            employee.setBonus(in.nextInt());
            if(employee.getBonus() == 0) {
                try {
                    ((EmployeeGroup) o).addEmployee(employee);
                } catch (AlreadyAddedException e) {
                    e.printStackTrace();
                }
            } else {
                employee.setBonus(in.nextInt());
                ((StaffEmployee) employee).setTravelsQuantity(in.nextInt());
                while (!in.next().equals(DEFAULT_END_OF)) {
                    beginTravel = LocalDate.parse(in.nextLine());
                    endTravel = LocalDate.parse(in.nextLine());
                    compensation = in.nextInt();
                    description = in.nextLine();
                    destination = in.nextLine();
                    businessTravel = new BusinessTravel(destination, beginTravel, endTravel, compensation, description);
                    ((StaffEmployee) employee).add(businessTravel);
                }
            }
        }
    }

    @Override
    public void store(Object o) {
        PrintWriter out;
        Employee[] employee;
        BusinessTravel[] businessTravels;
        File file = new File(getPath(), ((EmployeeGroup)o).getName() + ".txt");

        try{
            out = new PrintWriter(file);
            employee = ((EmployeeGroup)o).getEmployees();

            for (Employee anEmployee : employee) {
                out.println(anEmployee.getClass().getSimpleName());
                out.println(anEmployee.getFirstName());
                out.println(anEmployee.getSecondName());
                out.println(anEmployee.getJobTitle());
                out.println(anEmployee.getSalary());

                if (anEmployee instanceof PartTimeEmployee) {
                    out.println((anEmployee).getBonus());
                }

                if (anEmployee instanceof StaffEmployee) {
                    out.println((anEmployee).getBonus());
                    out.println(((StaffEmployee) anEmployee).getTravelsQuantity());

                    businessTravels = ((StaffEmployee) anEmployee).getTravels();

                    for (BusinessTravel businessTravel : businessTravels) {
                        out.println(businessTravel.getBeginTravel());
                        out.println(businessTravel.getEndTravel());
                        out.println(businessTravel.getCompensation());
                        out.println(businessTravel.getDescription());
                        out.println(businessTravel.getDestination());
                    }
                    out.println(DEFAULT_END_OF);
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Object o) {
        String path = getPath() + ((EmployeeGroup)o).getName() + ".txt";

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
        PrintWriter out;
        Employee[] employee;
        BusinessTravel[] businessTravels;
        File file = new File(getPath(), ((EmployeeGroup)o).getName() + ".txt");

        try{
            out = new PrintWriter(file);
            employee = ((EmployeeGroup)o).getEmployees();

            for (Employee anEmployee : employee) {
                out.println(anEmployee.getClass().getSimpleName());
                out.println(anEmployee.getFirstName() + " " + anEmployee.getSecondName());
                out.println(anEmployee.getJobTitle() + " " + anEmployee.getSalary());

                if (anEmployee instanceof PartTimeEmployee) {
                    out.println((anEmployee).getBonus());
                }

                if (anEmployee instanceof StaffEmployee) {
                    out.println((anEmployee).getBonus());
                    out.println(((StaffEmployee) anEmployee).getTravelsQuantity());

                    businessTravels = ((StaffEmployee) anEmployee).getTravels();

                    for (BusinessTravel businessTravel : businessTravels) {
                        out.println(businessTravel.getBeginTravel());
                        out.println(businessTravel.getEndTravel());
                        out.println(businessTravel.getCompensation());
                        out.println(businessTravel.getDescription());
                        out.println(businessTravel.getDestination());
                    }
                    out.println(DEFAULT_END_OF);
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
