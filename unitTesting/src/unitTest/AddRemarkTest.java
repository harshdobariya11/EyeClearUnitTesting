package unitTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class AddRemarkTest {

    // Test adding valid remarks for both Client and Optometrist categories
    @Test
    public void testAddRemark_ValidData2() {
        // Create a Prescription object for testing
        Prescription presc1 = new Prescription(1, "Emily", "Thompson", "456 Oak Avenue, Sydney, NSW 5678, Australia", 
                                               -2.00f, 2.50f, 30f, new Date(), "Dr. Albert");
        // Add a valid remark for the "Client" category
        assertTrue(presc1.addRemark("Client is happy with the new glasses.", "Client"), 
                   "Valid remark should return true.");
        
        // Create another Prescription object for testing
        Prescription presc2 = new Prescription(2, "Tommy", "Nelson", "123 Pine Street, Melbourne, VIC 4321, Australia", 
                                               0.00f, -3.00f, 60f, new Date(), "Dr. Gregory");
        // Add a valid remark for the "Optometrist" category
        assertTrue(presc2.addRemark("Optometrist recommended a re-examination after 6 months.", "Optometrist"), 
                   "Valid remark should return true.");
    }

    // Test adding a remark that starts with a lowercase letter (should be invalid)
    @Test
    public void testAddRemark_FirstCharacterNotUppercase() {
        // Create a Prescription object for testing
        Prescription presc1 = new Prescription(1, "Emily", "Thompson", "456 Oak Avenue, Sydney, NSW 5678, Australia", 
                                               -2.00f, 2.50f, 30f, new Date(), "Dr. Albert");
        // Attempt to add a remark starting with a lowercase letter (should fail)
        assertFalse(presc1.addRemark("the client was satisfied with the service.", "Client"), 
                    "Remark starting with lowercase should return false.");

        // Create another Prescription object for testing
        Prescription presc2 = new Prescription(2, "Tommy", "Nelson", "123 Pine Street, Melbourne, VIC 4321, Australia", 
                                               0.00f, -3.00f, 60f, new Date(), "Dr. Gregory");
        // Attempt to add a remark starting with a lowercase letter (should fail)
        assertFalse(presc2.addRemark("optometrist recommended a new prescription.", "Optometrist"), 
                    "Remark starting with lowercase should return false.");
    }

    // Test adding a remark with less than 6 words (should fail) and exactly 6 words (should pass)
    @Test
    public void testAddRemark_InvalidWordCount() {
        // Create a Prescription object for testing
        Prescription presc1 = new Prescription(1, "Emily", "Thompson", "456 Oak Avenue, Sydney, NSW 5678, Australia", 
                                               -2.00f, 2.50f, 30f, new Date(), "Dr. Albert");
        // Attempt to add a remark with less than 6 words (should fail)
        assertFalse(presc1.addRemark("Good glasses.", "Client"), 
                    "Remark with less than 6 words should return false.");

        // Create another Prescription object for testing
        Prescription presc2 = new Prescription(2, "Tommy", "Nelson", "123 Pine Street, Melbourne, VIC 4321, Australia", 
                                               0.00f, -3.00f, 60f, new Date(), "Dr. Gregory");
        // Add a remark with exactly 6 words (should pass)
        assertTrue(presc2.addRemark("Client is happy with the service.", "Client"), 
                   "Remark with exactly 6 words should return true.");
    }

    // Test adding a remark that exceeds the 20-word limit (should fail) and exactly 20 words (should pass)
    @Test
    public void testAddRemark_RemarkExceedsWordLimit() {
        // Create a Prescription object for testing
        Prescription presc1 = new Prescription(1, "Emily", "Thompson", "456 Oak Avenue, Sydney, NSW 5678, Australia", 
                                               -2.00f, 2.50f, 30f, new Date(), "Dr. Albert");
        // Attempt to add a remark with more than 20 words (should fail)
        assertFalse(presc1.addRemark("This is a long remark that exceeds the twenty-word limit for remarks in the system, so it should not be accepted.", "Optometrist"), 
                    "Remark with more than 20 words should return false.");

        // Create another Prescription object for testing
        Prescription presc2 = new Prescription(2, "Tommy", "Nelson", "123 Pine Street, Melbourne, VIC 4321, Australia", 
                                               0.00f, -3.00f, 60f, new Date(), "Dr. Gregory");
        // Add a remark with exactly 20 words (should pass)
        assertTrue(presc2.addRemark("The optometrist has recommended a new prescription and suggests the client to come for a follow-up session in six months.", "Optometrist"), 
                   "Remark with exactly 20 words should return true.");
    }
    
    // Test adding more than the limit of two remarks 
    @Test
    public void testAddRemark_MoreThanTwoRemarks_Client() {
        // Create a Prescription object for testing
        Prescription prescription = new Prescription(1, "Alice", "Johnson", "123 Main Street, Springfield, IL 62704, USA",
                -1.50f, 1.75f, 90f, new Date(), "Dr. Gregory");

        // Add first valid remark
        String remark1 = "Client noted some discomfort during the initial wearing period.";
        assertTrue(prescription.addRemark(remark1, "Client"), "First client remark should be added successfully.");

        // Add second valid remark
        String remark2 = "Client mentioned improvement after the first week of use.";
        assertTrue(prescription.addRemark(remark2, "Client"), "Second client remark should be added successfully.");

        // Attempt to add a third remark 
        String remark3 = "Client reported a minor issue with the frame fitting.";
        assertFalse(prescription.addRemark(remark3, "Client"), "Third client remark should not be allowed.");
        
        // Create another Prescription object for testing
        Prescription prescription_2 = new Prescription(2, "Tommy", "Nelson", "123 Pine Street, Melbourne, VIC 4321, Australia", 
                0.00f, -1.00f, 60f, new Date(), "Dr. Gregory");
        
        // Add a valid remark
        String remark_1 = "Client noted some discomfort during the initial wearing period.";
        assertTrue(prescription_2.addRemark(remark_1, "Client"), "First client remark should be added successfully.");

        // Add a valid remark
        String remark_2 = "The optometrist recommended a new prescription.";
        assertTrue(prescription_2.addRemark(remark_2, "Optometrist"), "Optometrist remark should be added successfully.");

        // Attempt to add a third remark for the "Client" category (should fail)
        String remark_3 = "The client submitted the new prescription quickly.";
        assertFalse(prescription_2.addRemark(remark_3, "Client"), "Third client remark should not be allowed.");
    }
    
    // Test rejecting remarks from unsupported categories (like "Support" and "Delivery")
    @Test
    public void testAddRemark_MultipleCategories() {
        // Create a Prescription object for testing
        Prescription presc1 = new Prescription(3, "John", "Doe", "789 Elm Street, Seattle, WA 98101, USA",
                -0.75f, 2.00f, 45f, new Date(), "Dr. Anderson");

        // Attempt to add a remark with an invalid category ("Support")
        String remark1 = "Client mentioned feeling strain while using the new prescription glasses.";
        assertFalse(presc1.addRemark(remark1, "Support"), "Support remark should not be allowed.");

        // Create another Prescription object for testing
        Prescription presc2 = new Prescription(4, "John", "Doe", "789 Elm Street, Seattle, WA 98101, USA",
                -0.75f, 2.00f, 45f, new Date(), "Dr. Anderson");
        
        // Attempt to add a remark with an invalid category ("Delivery")
        String remark2 = "Optometrist suggested adjusting the lens curvature for better fit.";
        assertFalse(presc2.addRemark(remark2, "Delivery"), "Delivery remark should not be allowed.");
    }
}
