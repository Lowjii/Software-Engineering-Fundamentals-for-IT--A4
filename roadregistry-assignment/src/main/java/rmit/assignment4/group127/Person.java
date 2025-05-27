package rmit.assignment4.group127;
import java.io.*;
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

    public boolean addPerson(String firstName, String lastName, String address, String birthdate, boolean isSuspended) throws FileNotFoundException {
//        This code reads the data.txt tile - no searching
//        String fileData;
//        FileInputStream fileInput = null;
//        Scanner fileScnr = null;
//        fileInput = new FileInputStream("roadregistry-assignment/src/main/data.txt");
//        fileScnr = new Scanner(fileInput);
//        fileData = fileScnr.nextLine();
//        System.out.println(fileData);
        personID = "1";
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthdate = birthdate;
        this.isSuspended = isSuspended;
        String dataOut = personID + "-" + this.firstName + "-" + this.lastName + "-" + this.address + "-" + this.birthdate + "-";
        dataOut += this.isSuspended ? "true" :  "false";
        FileOutputStream fileWrite = new FileOutputStream("roadregistry-assignment/src/main/data.txt", true);
        PrintWriter write = new PrintWriter(fileWrite);
        write.println(dataOut);
        write.close();

        return true;
    }
    public boolean updatePersonalDetails () {
        return true;
    }
    public String addDemeritPoints () {
        return "";
    }
}
