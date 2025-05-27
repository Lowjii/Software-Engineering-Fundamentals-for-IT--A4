import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    void testAddPerson_ValidData() {
        Person p = new Person("56$%a#d@AB", "Anna", "Smith", "32|Main St|Melbourne|Victoria|Australia", "15-11-1990");
        assertTrue(p.addPerson(), "Should return true for valid input");
    }

    @Test
    void testAddPerson_InvalidIDTooShort() {
        Person p = new Person("56$d@AB", "Tom", "Lee", "32|Main St|Melbourne|Victoria|Australia", "15-11-1990");
        assertFalse(p.addPerson(), "Should return false when ID is too short");
    }

    @Test
    void testAddPerson_InvalidIDNoSpecials() {
        Person p = new Person("56abcd12AB", "Lily", "Chan", "32|Main St|Melbourne|Victoria|Australia", "15-11-1990");
        assertFalse(p.addPerson(), "Should return false for ID with no special characters");
    }

    @Test
    void testAddPerson_InvalidAddressState() {
        Person p = new Person("56$%a#d@AB", "Jake", "Wilson", "15|Elm St|Geelong|NSW|Australia", "15-11-1990");
        assertFalse(p.addPerson(), "Should return false for address not in Victoria");
    }

    @Test
    void testAddPerson_InvalidBirthdateFormat() {
        Person p = new Person("56$%a#d@AB", "Nina", "Jones", "45|Main Rd|Ballarat|Victoria|Australia", "1990-11-15");
        assertFalse(p.addPerson(), "Should return false for incorrect birthdate format");
    }
}
