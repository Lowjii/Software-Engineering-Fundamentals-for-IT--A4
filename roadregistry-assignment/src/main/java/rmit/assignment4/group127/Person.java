package rmit.assignment4.group127;
import java.io.*;
import java.text.ParseException;
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;

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
        this.demeritPoints = new HashMap<>();
        this.isSuspended = false;
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
            System.out.println("Invalid Date must follow dd-mm-yyyy.");
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
    // Changing personal details will not change demerit points or suspension status
    public boolean updatePersonalDetails(String newID, String newFirstName, String newLastName, String newAddress, String newBirthdate) {
        try {
            // Checks birthdate format (must be DD-MM-YYYY)
            if (!newBirthdate.matches("\\d{2}-\\d{2}-\\d{4}")) {
                return false;
            }

            // Checks if birthdate is being changed
            boolean birthdayChanging = !this.birthdate.equals(newBirthdate);

            // Checks if any other personal detail is being changed
            boolean otherDetailsChanging =
                    !this.personID.equals(newID) ||
                            !this.firstName.equals(newFirstName) ||
                            !this.lastName.equals(newLastName) ||
                            !this.address.equals(newAddress);

            // Condition 1: Under 18 cannot change address
            int age = getAge();
            if (age < 18 && !this.address.equals(newAddress)) {
                System.out.println("Invalid Change: Cannot change address, user is under 18 years old.");
                return false;
            }

            // Condition 2: If birthday is changed, no other detail can change
            if (birthdayChanging && otherDetailsChanging) {
                return false;
            }

            // Condition 3: If ID starts with even number, it cannot change
            char firstChar = this.personID.charAt(0);
            if (Character.isDigit(firstChar) && (firstChar - '0') % 2 == 0 && !this.personID.equals(newID)) {
                return false;
            }

            // Passed all checks — apply the updates
            this.personID = newID;
            this.firstName = newFirstName;
            this.lastName = newLastName;
            this.address = newAddress;
            this.birthdate = newBirthdate;

            // After all the fields are updated and before return true
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("person_data.txt"))) {
                writer.write(this.personID + "|" + this.firstName + "|" + this.lastName + "|" + this.address + "|" + this.birthdate);
            } catch (IOException e) {
                return false;
            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // Below is the addDemeritPoints method: WIP
    public String addDemeritPoints (String offenseDateStr, int points) {
        // Call helper method to load existing data from demerit_points.txt file
        loadDemeritPointsFromFile();

        // Condition 1: Validate date format
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        // Set lenient to false to enforce format
        sdf.setLenient(false);
        // Instantiate offenseDate
        Date offenseDate;
        try {
            offenseDate = sdf.parse(offenseDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid: Date must follow dd-mm-yyyy.");
            return "Failed"; // Invalid date format
        }

        // Condition 2: Validate points range
        if (points < 1 || points > 6) {
            System.out.println("Invalid: Points are out of range.");
            return "Failed"; // Points are out of range
        }

        // Add offense to temp map to calculate suspension status
        HashMap<Date, Integer> tempPoints = new HashMap<>(demeritPoints);
        tempPoints.put(offenseDate, points);

        // Calculate cutoff date two years before offenseDate
        Calendar cal = Calendar.getInstance();
        cal.setTime(offenseDate);
        cal.add(Calendar.YEAR, -2);
        Date twoYearsAgo = cal.getTime();

        // Sum points within last two years
        int totalPoints = 0;
        for (Date d : tempPoints.keySet()) {
            if (!d.before(twoYearsAgo) && !d.after(offenseDate)) {
                totalPoints += tempPoints.get(d);
            }
        }

        // Condition 3: Determine suspension based on age and demerit points
        // Call getAge() helper method to get driver age
        int age = getAge();
        if ((age < 21 & totalPoints > 6) || (age >= 21 && totalPoints > 12)) {
            isSuspended = true;
        } else {
            isSuspended = false;
        }

        // Write offense to TXT file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("demerit_points.txt", true))) {
            writer.write(personID + "|" + offenseDateStr + "|" + points + "|" + "Suspended: " + isSuspended);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed";
        }

        // Update internal map
        demeritPoints.put(offenseDate, points);
        return "Success";
    }

    // Helper method: get age of drivers (utilised for the updatePersonalDetails method and addDemeritPoints method)
    public int getAge() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        // Enforce DOB format
        sdf.setLenient(false);
        try {
            Date birthDate = sdf.parse(birthdate);
            Calendar birthCal = Calendar.getInstance();
            birthCal.setTime(birthDate);

            Calendar today = Calendar.getInstance();

            int age = today.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
            // If birthday hasn't occurred yet this year, subtract 1
            if (today.get(Calendar.DAY_OF_YEAR) < birthCal.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }
            return age;
        } catch (ParseException e) {
            // If DOB invalid, return 0
            return 0;
        }
    }

    // Helper method: load demerit points from TXT file to update and set suspension status
    public void loadDemeritPointsFromFile() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try (BufferedReader reader = new BufferedReader(new FileReader("demerit_points.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4 && parts[0].equals(this.personID)) {
                    Date date = sdf.parse(parts[1]);
                    int points = Integer.parseInt(parts[2]);
                    demeritPoints.put(date, points);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // Getter for suspension status
    public boolean getIsSuspended() {
        System.out.println(isSuspended);
        return isSuspended;
    }
}
