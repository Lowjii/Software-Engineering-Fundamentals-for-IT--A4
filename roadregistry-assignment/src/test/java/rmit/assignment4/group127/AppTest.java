package rmit.assignment4.group127;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

// JUnit 4 Version
// import org.junit.Test;
// import static org.junit.Assert.assertTrue;
// import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple App.
 */
public class AppTest {
    // Tests for addPerson method
    // Valid ID
    @Test
    public void testAddPerson_ValidData() {
        Person p = new Person("56$%a#d@AB", "Anna", "Smith", "32|Main St|Melbourne|Victoria|Australia", "15-11-1990");
        assertTrue(p.addPerson(), "Should return true for valid input");
    }
    // Test: ID length is too short
    @Test
    public void testAddPerson_InvalidIDTooShort() {
        Person p = new Person("56$d@AB", "Tom", "Lee", "32|Main St|Melbourne|Victoria|Australia", "15-11-1990");
        assertFalse(p.addPerson(), "Should return false when ID is too short");
    }
    // Test: ID has no special characters
    @Test
    public void testAddPerson_InvalidIDNoSpecials() {
        Person p = new Person("56abcd12AB", "Lily", "Chan", "32|Main St|Melbourne|Victoria|Australia", "15-11-1990");
        assertFalse(p.addPerson(), "Should return false for ID with no special characters");
    }
    // Test: Address not in Victoria
    @Test
    public void testAddPerson_InvalidAddressState() {
        Person p = new Person("56$%a#d@AB", "Jake", "Wilson", "15|Elm St|Geelong|NSW|Australia", "15-11-1990");
        assertFalse(p.addPerson(), "Should return false for address not in Victoria");
    }
    // Test: DOB format is incorrect
    @Test
    public void testAddPerson_InvalidBirthdateFormat() {
        Person p = new Person("56$%a#d@AB", "Nina", "Jones", "45|Main Rd|Ballarat|Victoria|Australia", "1990-11-15");
        assertFalse(p.addPerson(), "Should return false for incorrect birthdate format");
    }
}
