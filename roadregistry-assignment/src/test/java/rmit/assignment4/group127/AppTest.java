package rmit.assignment4.group127;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    // Tests for addPerson
    // Person is under 18, cannot change address
    @Test
    public void testUpdatePersonalDetails_Under18() {
        Person p = new Person("11$%a#d@AA", "Duncan", "Lee", "13|Swanston St|Melbourne|Victoria|Australia", "01-11-2010");
        p.addPerson();
        assertFalse(p.updatePersonalDetails("11$%a#d@AA", "Duncan", "Lee", "14|Street St|Geelong|Victoria|Australia", "01-11-2010"),
                "Should return false as client is under 18 and is attempting to change address.");
    }

    // Tests for addDemeritPoint
    // Valid usage of addDemeritPoint, over 21 no suspension
    @Test
    public void testAddDemeritPoint_ValidData() {
        Person p = new Person("99$%a#d@AC", "Paul", "Smith", "13|Swanston St|Melbourne|Victoria|Australia", "15-11-1990");
        assertEquals("Success", p.addDemeritPoints("21-01-2025", 1));
    }

    @Test
    public void testAddDemeritPoint_InvalidDateFormat() {
        Person p = new Person("98$%a#d@AD", "Lee", "White", "21|1234 Rd|Williams Landing|Victoria|Australia", "23-12-1993");
        assertEquals("Failed", p.addDemeritPoints("2025-01-21", 1));
    }

    @Test
    public void testAddDemeritPoint_OutOfRange() {
        Person p = new Person("97$%a#d@AE", "Jane", "Doe", "99|Bourke St|Melbourne|Victoria|Australia", "30-12-2000");
        assertEquals("Failed", p.addDemeritPoints("21-01-2025", 7));
    }

    @Test
    public void testAddDemerit_SuspendOver21() {
        Person p = new Person("97$%a#d@AF", "Joe", "Manny", "101|Fake St|Docklands|Victoria|Australia", "02-04-1987");
        p.addDemeritPoints("10-01-2025", 6);
        p.addDemeritPoints("21-05-2025", 6);
        p.addDemeritPoints("25-12-2025", 1); // Should suspend Joe Manny (97$%a#d@AF)
        assertTrue(p.getIsSuspended(), "Success");
    }

    @Test
    public void testAddDemerit_SuspendUnder21() {
        Person p = new Person("11$%a#d@AG", "Jesse", "Jones", "101|Fake St|Docklands|Victoria|Australia", "02-04-2005");
        p.addDemeritPoints("14-03-2025", 6);
        p.addDemeritPoints("21-06-2025", 1); // Should suspend Jesse Jones (11$%a#d@AG)
        assertTrue(p.getIsSuspended(), "Success");
    }
}
