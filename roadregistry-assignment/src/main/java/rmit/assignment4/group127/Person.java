package rmit.assignment4.group127;
import java.io.*;
import java.util.HashMap;
import java.util.Date;
import java.io.FileInputStream;
import java.util.Scanner;

// Instantiate
public class Person {
    private String personID;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;
    private HashMap<Date, Integer> demeritPoints; // A variable that hold the demerit points with the offense day
    private boolean isSuspended;

    // Contructor
    public Person(String personID, String firstName, String lastName, String address, String birthdate) {
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthdate = birthdate;
        this.demeritPoints = demeritPoints;
        this.isSuspended = isSuspended;
    }

    // Below is the addPerson method: Complete
    public boolean addPerson() {
        // Validate personID

        // If ID is not equal to than 10
        if (personID == null || personID.length() != 10) {
            System.out.println("Invalid personID: must be exactly 10 characters.");
            return false;
        }

        // Seperating ID for validation purposes
        String firstTwo = personID.substring(0, 2);
        String middle = personID.substring(2, 8);
        String lastTwo = personID.substring(8);

        // First 2 characters must be in between 2 and 9
        if (!firstTwo.matches("[2-9]{2}")) {
            System.out.println("Invalid personID: First 2 characters must be numbers between 2-9.");
            return false;
        }

        // Ensures ID has at least 2 special characters between characters 3 - 8
        int specialCount = 0;
        for (char c : middle.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) specialCount++;
        }
        if (specialCount < 2) {
            System.out.println("Invalid personID: Must have at least 2 special characters.");
            return false;
        }

        // Ensures last 2 characters are uppercase letters
        if (!lastTwo.matches("[A-Z]{2}")) {
            System.out.println("Invalid personID: Last 2 characters must be an uppercase letter.");
            return false;
        }

        // Validate address: must be in Victoria
        if (!address.matches("^\\d+\\|[^|]+\\|[^|]+\\|Victoria\\|[^|]+$")) {
            System.out.println("Invalid Address: Not located in Victoria.");
            return false;
        }

        // Validate birthdate: DD-MM-YYYY
        if (!birthdate.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
            System.out.println("Invalid Date.");
            return false;
        }

        // Append data to person_data TXT file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("person_data.txt", true))) {
            writer.write(String.join("|", personID, firstName, lastName, address, birthdate));
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Below is the updatePersonalDetails method: WIP
    public boolean updatePersonalDetails () {

        return true;
    }

    // Below is the addDemeritPoints method: WIP
    public String addDemeritPoints () {
        return "";
    }
}
