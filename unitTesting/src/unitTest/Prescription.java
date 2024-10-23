package unitTest;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Prescription { 
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
//    private String[] remarkTypes = { "Client", "Optometrist" };
    private ArrayList<String> postRemarks = new ArrayList<>();

    // Constructor to initialize prescription details
    public Prescription(int prescID, String firstName, String lastName, String address, float sphere, float cylinder, float axis,Date examinationDate, String optometrist) {
        this.prescID = prescID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
    }

    // Method to add prescription details to the file
    public boolean addPrescription() {
        // Condition 1: Check first and last name length
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
            return false;
        }

        // Condition 2: Check address length
        if (address.length() < 20) {
        	System.out.println(address+" : "+address.length());
            return false;
        }

        // Condition 3: Validate sphere, cylinder, and axis values
        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
//        	System.out.println("hello"+sphere+"  "+cylinder+ "  "+  axis);
            return false;
        }
        System.out.println("Optometrist Name: " + optometrist + " | Length: " + optometrist.length());

        // Condition 4: Validate optometrist name length
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false;
        }

        // Writing prescription data to the file
        try (FileWriter writer = new FileWriter("C:/Users/dell/Desktop/Temp/java/presc.txt", true)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            String examinationDateStr = sdf.format(examinationDate);

            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Sphere: " + sphere + "\n");
            writer.write("Cylinder: " + cylinder + "\n");
            writer.write("Axis: " + axis + "\n");
            writer.write("Examination Date: " + examinationDateStr + "\n");
            writer.write("Optometrist: " + optometrist + "\n");
            writer.write("---------------------------------\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to add a remark to the prescription
    public boolean addRemark(String remark, String category) {  
        // Condition 1: Check remark length (min 6 words, max 20 words)
        String[] words = remark.split("\\s+");
        if (words.length < 6 || words.length > 20 || !Character.isUpperCase(remark.charAt(0))) {
            return false;
        }

        // Condition 2: Check if remark belongs to the valid categories
        if (!category.equalsIgnoreCase("Client") && !category.equalsIgnoreCase("Optometrist")) {
            return false;
        }

        // Ensure a prescription has no more than 2 remarks
        if (postRemarks.size() >= 2) {
            return false; 
        }

        // Writing remark to the file
        try (FileWriter writer = new FileWriter("C:/Users/dell/Desktop/Temp/java/remark.txt", true)) {
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("Category: " + category + "\n");
            writer.write("Remark: " + remark + "\n");
            writer.write("---------------------------------\n");
            postRemarks.add(remark);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
