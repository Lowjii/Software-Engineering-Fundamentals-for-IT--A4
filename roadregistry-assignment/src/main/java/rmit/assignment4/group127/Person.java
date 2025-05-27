package rmit.assignment4.group127;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Date;
import java.io.FileInputStream;
import java.util.Scanner;

public class Person {
    private String personID;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;
    private HashMap<Date, Integer> demeritPoints;
    private boolean isSuspended;

    public boolean addPerson() throws FileNotFoundException {
//        This code reads the data.txt tile - no searching
//        String fileData;
//        FileInputStream fileInput = null;
//        Scanner fileScnr = null;
//        fileInput = new FileInputStream("roadregistry-assignment/src/main/data.txt");
//        fileScnr = new Scanner(fileInput);
//        fileData = fileScnr.nextLine();
//        System.out.println(fileData);
        return true;
    }
    public boolean updatePersonalDetails () {
        return true;
    }
    public String addDemeritPoints () {
        return "";
    }
}
