import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Person {
    private String personID;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;

    public Person(String personID, String firstName, String lastName, String address, String birthdate) {
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthdate = birthdate;
    }

    public boolean addPerson() {
        // Validate personID
        if (personID == null || personID.length() != 10) return false;

        String firstTwo = personID.substring(0, 2);
        String middle = personID.substring(2, 8);
        String lastTwo = personID.substring(8);

        if (!firstTwo.matches("[2-9]{2}")) return false;

        int specialCount = 0;
        for (char c : middle.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) specialCount++;
        }
        if (specialCount < 2) return false;

        if (!lastTwo.matches("[A-Z]{2}")) return false;

        // Validate address: must be in Victoria
        if (!address.matches("^\\d+\\|[^|]+\\|[^|]+\\|Victoria\\|[^|]+$")) return false;

        // Validate birthdate: DD-MM-YYYY
        if (!birthdate.matches("^\\d{2}-\\d{2}-\\d{4}$")) return false;

        // Append data to TXT file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("person_data.txt", true))) {
            writer.write(String.join("|", personID, firstName, lastName, address, birthdate));
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
