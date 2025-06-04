package rmit.assignment4.group127;

import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException {
    // These are just raw temporary tests, you can add some in here for now

        // Test: Valid
        Person test1 = new Person("56$d@12dAB", "Bob", "Smith", "32|Main St|Melbourne|Victoria|Australia", "15-11-1990");
        //test1.addPerson();

        // Test: Get age method
        // System.out.println(test1.getAge());

        // Test: Adding demerit points
        test1.addDemeritPoints("4-06-2025", 2);

        // Test: Adding demerit points (update)
        test1.addDemeritPoints("5-07-2025", 2);

        // Test: Adding demerit points (suspension)
        test1.addDemeritPoints("10-07-2025", 4);

        // Test: Adding demerit points (suspension)
        test1.addDemeritPoints("11-07-2025", 6);

        // Test: Update details
        //test1.updatePersonalDetails("56$d@12dAB", "Bob", "Mary-Smith", "16|Test St|Melbourne|Victoria|Australia", "15-11-1990");

        // Test: ID too short
        // Person test2 = new Person("56$d@AB", "Tom", "Lee", "32|Main St|Melbourne|Victoria|Australia", "15-11-1990");
        // test2.addPerson();

        // Test: isSuspended
        // test1.getIsSuspended();
    }
}
