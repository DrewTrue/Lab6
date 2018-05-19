package io;

import humanResources.EmployeeGroup;

import java.io.*;
import java.nio.file.*;

public class GroupsManagerSerializedFileSource extends GroupsManagerFileSource {
    public GroupsManagerSerializedFileSource(String path) {
        super(path);
    }

    @Override
    public void load(EmployeeGroup employeeGroup) {
        File file;
        ObjectInputStream in;
        try {
            file = new File(getPath(), employeeGroup.getName() + ".bin");
            in = new ObjectInputStream(new FileInputStream(file));
            employeeGroup = (EmployeeGroup) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void store(EmployeeGroup employeeGroup) {
        File file;
        ObjectOutputStream out;
        try {
            file = new File(getPath(), employeeGroup.getName() + ".bin");
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(employeeGroup);
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
        File file;
        ObjectOutputStream out;
        try {
            file = new File(getPath(), employeeGroup.getName() + ".bin");
            file.createNewFile();
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(employeeGroup);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
