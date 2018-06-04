package io;

import humanResources.*;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class GroupsManagerTextFileSource extends GroupsManagerFileSource{
    public GroupsManagerTextFileSource(String path) {
        super(path);
    }

    @Override
    public void load(EmployeeGroup employeeGroup) {
        Scanner in = new Scanner(getPath() + employeeGroup.getName() + ".txt");

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
        employeeGroup.clear(); //todo проще сделать так
        while(!in.nextLine().equals("")){
            firstName = in.nextLine();
            secondName = in.nextLine();
            employee = new StaffEmployee(firstName, secondName);
            employee.setSalary(in.nextInt());
            employee.setJobTitle(JobTitlesEnum.valueOf(in.nextLine()));
            employee.setBonus(in.nextInt());
            if(employee.getBonus() == 0) {
                try {
                    employeeGroup.add(employee);
                } catch (AlreadyAddedException e) {
                    e.printStackTrace();
                }
            } else {
                employee.setBonus(in.nextInt());
                travelsQuantity = in.nextInt();
                ((StaffEmployee) employee).setTravelsQuantity(travelsQuantity);
                for (int i = 0; i < travelsQuantity; i++) {
                    beginTravel = LocalDate.ofEpochDay(in.nextLong());
                    endTravel = LocalDate.ofEpochDay(in.nextLong());
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
    public void store(EmployeeGroup employeeGroup) {
        PrintWriter out;
        Employee[] employee;
        BusinessTravel[] businessTravels;
        File file = new File(getPath(), employeeGroup.getName() + ".txt");

        try{
            out = new PrintWriter(file);
            employee = (Employee[]) employeeGroup.toArray();

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
                    out.println(((StaffEmployee) anEmployee).size());

                    businessTravels = (BusinessTravel[]) ((StaffEmployee) anEmployee).toArray();

                    for (BusinessTravel businessTravel : businessTravels) {
                        out.println(businessTravel.getBeginTravel());
                        out.println(businessTravel.getEndTravel());
                        out.println(businessTravel.getCompensation());
                        out.println(businessTravel.getDescription());
                        out.println(businessTravel.getDestination());
                    }
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(EmployeeGroup employeeGroup) {
        String path = getPath() + employeeGroup.getName() + ".txt";

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
        try{
            File file = new File(getPath(), employeeGroup.getName() + ".txt");
            if (!file.createNewFile())
                return false;
            store(employeeGroup);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
