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
        test1.addPerson();

        // Test: ID too short
        // Person test2 = new Person("56$d@AB", "Tom", "Lee", "32|Main St|Melbourne|Victoria|Australia", "15-11-1990");
        // test2.addPerson();
    }
}
