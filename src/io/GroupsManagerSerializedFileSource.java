package io;

import humanResources.EmployeeGroup;

import java.io.*;
import java.nio.file.*;

public class GroupsManagerSerializedFileSource extends GroupsManagerFileSource {
    public GroupsManagerSerializedFileSource(String path) {
        super(path);
    }

    @Override
    public void load(Object o) {
        File file;
        ObjectInputStream in;
        try {
            file = new File(getPath(), ((EmployeeGroup) o).getName() + ".bin");
            in = new ObjectInputStream(new FileInputStream(file));
            o = in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void store(Object o) {
        File file;
        ObjectOutputStream out;
        try {
            file = new File(getPath(), ((EmployeeGroup)o).getName() + ".bin");
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(o);
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
        File file;
        ObjectOutputStream out;
        try {
            file = new File(getPath(), ((EmployeeGroup)o).getName() + ".bin");
            file.createNewFile();
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(o);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
