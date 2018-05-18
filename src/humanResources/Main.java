package humanResources;

import java.io.*;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] values = {1, 2, 4, 5, 6};
        PrintWriter out;
        try{
            out = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")));
            for(int i = 0; i < values.length; i++){
                out.println(values[i]);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader in;
        try{
            in = new BufferedReader(new FileReader("out.txt"));
            for(int i = 0; i < values.length; i++){
                System.out.println(in.readLine());
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Employee employee = new StaffEmployee("asd","sad");
        System.out.println(employee.getClass().getSimpleName());
        File file = new File("d:/", "skr.txt");
        file.createNewFile();
        out = new PrintWriter(file);
        out.println("hey");
        out.close();

    }
}
