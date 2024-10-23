package unitTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class AddPrescriptionTest {

    // Test case for adding valid prescriptions with correct data
    @Test
    public void testAddPrescription_ValidData1() {
        // Creating valid Prescription objects
        Prescription presc1 = new Prescription(1, "John", "Smith", "123 Elm Street, Springfield, VIC 1234, Australia",
                -5.00f, 1.50f, 90f, new Date(), "Dr. Gregory");

        Prescription presc2 = new Prescription(2, "Alice", "Wright", "456 Oak Avenue, Sydney, NSW 5678, Australia",
                3.25f, -2.00f, 45f, new Date(), "Dr. Lawrence");

        // Asserting that both valid prescriptions are successfully added
        assertTrue(presc1.addPrescription(), "Valid prescription should return true.");
        assertTrue(presc2.addPrescription(), "Valid prescription should return true.");
    }

    // Test case for adding invalid prescriptions with incorrect data
    @Test
    public void testAddPrescription_InvalidData1() {
        // Invalid because first name is less than 4 characters
        Prescription presc1 = new Prescription(1, "Jo", "Smith", "123 Elm Street, Springfield, VIC 1234, Australia",
                -5.00f, 1.50f, 90f, new Date(), "Dr. Gregory");

        // Invalid because address length is less than 20 characters
        Prescription presc2 = new Prescription(2, "Alice", "Wright", "Sydney", 3.25f, -2.00f, 45f, new Date(), "Dr. Lawrence");

        // Asserting that invalid prescriptions are not added
        assertFalse(presc1.addPrescription(), "Invalid prescription should return false.");
        assertFalse(presc2.addPrescription(), "Invalid prescription should return false.");
    }

    // Test case for invalid sphere values
    @Test
    public void testAddPrescription_InvalidSphereValue() {
        // Invalid because sphere value is less than -20
        Prescription presc1 = new Prescription(1, "John", "Doeo", "123 Elm Street, Springfield, VIC 1234, Australia",
                -25.00f, 1.50f, 90f, new Date(), "Dr. Gregory");

        // Invalid because sphere value is greater than 20
        Prescription presc2 = new Prescription(2, "Jane", "Doe0", "456 Oak Avenue, Sydney, NSW 5678, Australia",
                21.00f, -2.00f, 45f, new Date(), "Dr. Lawrence");

        // Asserting that prescriptions with invalid sphere values are not added
        assertFalse(presc1.addPrescription(), "Invalid sphere value should return false.");
        assertFalse(presc2.addPrescription(), "Invalid sphere value should return false.");
    }

    // Test case for invalid optometrist name length
    @Test
    public void testAddPrescription_InvalidOptometristNameLength() {
        // Invalid because optometrist name is less than 8 characters
        Prescription presc1 = new Prescription(1, "John", "Smith", "123 Elm Street, Springfield, VIC 1234, Australia",
                -5.00f, 1.50f, 90f, new Date(), "Greg");

        // Invalid because optometrist name exceeds 25 characters
        Prescription presc2 = new Prescription(2, "Alice", "Wright", "456 Oak Avenue, Sydney, NSW 5678, Australia",
                3.25f, -2.00f, 45f, new Date(), "Dr. Gregory Lawrence Winfield");

        // Asserting that prescriptions with invalid optometrist name lengths are not added
        assertFalse(presc1.addPrescription(), "Invalid optometrist name length should return false.");
        assertFalse(presc2.addPrescription(), "Invalid optometrist name length should return false.");
    }

    // Test case for invalid address length
    @Test
    public void testAddPrescription_InvalidAddressLength() {
        // Invalid because address has less than 20 characters
        Prescription presc1 = new Prescription(1, "Emily", "Thompson", "New York, USA",
        		-3.50f, 2.00f, 70f, new Date(), "Dr. Albert");

        // Invalid because address has less than 20 characters
        Prescription presc2 = new Prescription(2, "Tommy", "Nelson", "Short Street, USA 9",
                0.00f, -3.00f, 60f, new Date(), "Dr. Gregory");

        // Asserting that prescriptions with invalid address lengths are not added
        assertFalse(presc1.addPrescription(), "Invalid address length should return false.");
        assertFalse(presc2.addPrescription(), "Invalid address should return false.");
    }

    // Test case for invalid axis values
    @Test
    public void testAddPrescription_InvalidAxisValue() {
        // Invalid because axis value is less than 0
        Prescription presc1 = new Prescription(1, "John", "Doe", "456 Oak Avenue, Sydney, NSW 5678, Australia",
                -5.00f, 1.50f, -1f, new Date(), "Dr. Mark");
 
        // Invalid because axis value is greater than 180
        Prescription presc2 = new Prescription(2, "Jane", "Doe", "123 Pine Street, Melbourne, VIC 4321, Australia",
                2.50f, 0.50f, 200f, new Date(), "Dr. Susan");

        // Asserting that prescriptions with invalid axis values are not added
        assertFalse(presc1.addPrescription(), "Invalid axis value should return false.");
        assertFalse(presc2.addPrescription(), "Invalid axis value should return false.");
    }

    // Test case for valid address length
    @Test
    public void testAddPrescription_ValidAddressLength() {
        // Valid prescription with an exact 20-character address
        Prescription presc1 = new Prescription(1, "Kevin", "Davis", "ABC Road, Texas 123456, USA",
                0.00f, -1.50f, 75f, new Date(), "Dr. Patrick");

        // Valid prescription with longer address
        Prescription presc2 = new Prescription(2, "Melissa", "Clark", "9999 Maple Drive, Richmond, BC V7C 3T7, Canada",
                -1.00f, 2.25f, 30f, new Date(), "Dr. Emma");

        // Asserting that valid prescriptions with proper address lengths are added successfully
        assertTrue(presc1.addPrescription(), "Valid prescription with exact address length should return true.");
        assertTrue(presc2.addPrescription(), "Valid prescription with long address should return true.");
    }

    // Test case for invalid name lengths
    @Test
    public void testAddPrescription_InvalidNameLength() {
        // Invalid because first name exceeds 15 characters
        Prescription presc1 = new Prescription(1, "ChristopherJohn", "Lee", "456 Oak Avenue, Sydney, NSW 5678, Australia",
                -5.00f, 1.50f, 90f, new Date(), "Dr. Joseph");

        // Invalid because last name is less than 4 characters
        Prescription presc2 = new Prescription(2, "Sara", "Li", "123 Pine Street, Melbourne, VIC 4321, Australia",
                3.25f, 0.00f, 45f, new Date(), "Dr. Albert");

        // Asserting that prescriptions with invalid name lengths are not added
        assertFalse(presc1.addPrescription(), "Invalid first name length should return false.");
        assertFalse(presc2.addPrescription(), "Invalid last name length should return false.");
    }
}
