/**
 * Project Name: cw_StudentActionManagementSystem
 * File Name: Main.java
 * Description: This has the main functionality of the program.
 * Author: Punjitha Bandara - w2083155
 * Start Date: June 23, 2024
 */

package Task01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static int studentCount = 0; // Holds the count of registered students
    private static final String[][] studentDetails = new String[100][2]; // 2D Array to store student details

    /**
     * Main method to run the program
     */
    public static void main(String[] args) {
        menu();
    }

    /**
     * Menu method to display the menu and get the user input
     */
    private static void menu(){
        Scanner scanner = new Scanner(System.in);
        int choice;

        while(true) {
            System.out.println("\nMenu\n");
            System.out.println("1. Check available seats");
            System.out.println("2. Register student (with ID)");
            System.out.println("3. Delete student");
            System.out.println("4. Find student (with student ID)");
            System.out.println("5. Store student details into a file");
            System.out.println("6. Load student details from a file to the system");
            System.out.println("7. View the list of students based on their names");
            System.out.println();
            System.out.println("Enter your choice");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Invalid input, please try again");
                System.out.println();
                continue;
            }finally {
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    checkAvailableSeats();
                    menu();
                    break;
                case 2:
                    registerStudent(scanner);
                    menu();
                    break;
                case 3:
                    deleteStudent(scanner);
                    menu();
                    break;
                case 4:
                    findStudent(scanner);
                    menu();
                    break;
                case 5:
                    saveToFile();
                    menu();
                    break;
                case 6:
                    loadFromFile();
                    menu();
                    break;
                case 7:
                    sortStudents();
                    menu();
                    break;
                default:
                    System.err.println("Invalid choice. Try again");
                    continue;
            }
            break;
        }
    }

    /**
     * Checks and displays the number of available seats.
     * Calculates available seats by subtracting the current student count from the total capacity.
     */
    private static void checkAvailableSeats(){
        System.out.println("\nSeat Availability\n");
        System.out.println("Available seats: " + (studentDetails.length - studentCount));
    }

    /**
     * Registers a new student if there is an available seat.
     * Prompts the user for a student ID and name, checks for ID uniqueness, and adds the student to the array.
     * @param scanner Scanner object for reading user input.
     */
    private static void registerStudent(Scanner scanner){
        System.out.println("\nStudent Registration\n");
        if (studentDetails.length > studentCount) {
            String studentID = studentIDInput(scanner);
            for (String[] studentDetail : studentDetails) {
                if (studentID.equals(studentDetail[0])) {
                    System.err.println("Student ID already exists.");
                    return;
                }
            }
            for (int i = 0; i < studentDetails.length; i++) {
                if (studentDetails[i][0] == null) {
                    System.out.println("Enter student name: ");
                    String studentName = scanner.nextLine();
                    studentDetails[i][0] = studentID;
                    studentDetails[i][1] = studentName;
                    studentCount++;
                    break;
                }
            }
        }else{
            System.out.println("There are no more seats available");
        }
    }

    /**
     * Prompts the user for a student ID, ensuring it starts with 'w' and contains 8 characters.
     * @param scanner Scanner object for reading user input.
     * @return The validated student ID.
     */
    private static String studentIDInput(Scanner scanner){
        while (true) {
            System.out.println("Enter student ID: ");
            String studentID = scanner.nextLine();
            if (!(studentID.startsWith("w") && studentID.length() == 8)) {
                System.err.println("Invalid ID, student ID must start with 'w' and contains 8 characters");
            }else {
                return studentID;
            }
        }
    }

    /**
     * Deletes a student from the system based on the provided student ID.
     * @param scanner Scanner object for reading user input.
     */
    private static void deleteStudent(Scanner scanner){
        System.out.println("\nDelete a Registered Student\n");
        if(studentCount > 0){
            String studentID = studentIDInput(scanner);
            for (int i = 0; i < studentDetails.length; i++) {
                if (studentID.equals(studentDetails[i][0])) {
                    studentDetails[i][0] = null;
                    studentDetails[i][1] = null;
                    studentCount--;
                    System.out.println("Student ID: " + studentID + " deleted.");
                    break;
                }
            }
        }else{
            System.err.println("There is no student details available to delete.");
        }
    }

    /**
     * Finds and displays a student's details based on the provided student ID.
     * @param scanner Scanner object for reading user input.
     */
    private static void findStudent(Scanner scanner){
        if (studentCount > 0){
            System.out.println("\nFind a Registered Student\n");
            String studentID = studentIDInput(scanner);
            for (String[] studentDetail : studentDetails) {
                if (studentID.equals(studentDetail[0])) {
                    System.out.println("Student ID: " + studentID);
                    System.out.println("Student Name: " + studentDetail[1]);
                    break;
                } else {
                    System.err.println("Student ID not found.");
                }
            }
        }else {
            System.err.println("There is no student details available to find with student ID.");
        }
    }

    /**
     * Saves the details of all registered students to a file named "StudentDetails.txt".
     */
    private static void saveToFile(){
        System.out.println("\nSave Student Details to StudentDetails.txt File\n");
        if (studentCount > 0){
            try {
                FileWriter file = new FileWriter("StudentDetails.txt");
                for (String[] studentDetail : studentDetails) {
                    if (studentDetail[0] != null) {
                        file.write(studentDetail[0] + "," + studentDetail[1] + "\n");
                    }
                }
                System.out.println("Student details saved.");
                file.close();
            } catch (IOException e) {
                System.err.println("Error while writing to the file");
                e.printStackTrace();
            }
        }else{
            System.err.println("There is no student details available to save to StudentDetails.txt file.");
        }
    }

    /**
     * Loads student details from a file named "StudentDetails.txt" and adds them to the system.
     */
    private static void loadFromFile(){
        System.out.println("\nLoad Student Details from StudentDetails.txt File\n");
        try{
            File file = new File("StudentDetails.txt");
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine()){
                String[] data = fileReader.nextLine().split(",");
                System.out.println(data[0] + " " + data[1]);
                for (int i = 0; i < studentDetails.length; i++) {
                    if (studentDetails[i][0] == null) {
                        studentDetails[i][0] = data[0];
                        studentDetails[i][1] = data[1];
                        studentCount++;
                        System.out.println("Student details loaded from the file.");
                        break;
                    }
                }
            }
            fileReader.close();
        }catch(IOException e){
            System.err.println("Error while reading from file");
            e.printStackTrace();
        }
    }

    /**
     * Sorts and displays the list of students based on their names in alphabetical order.
     */
    private static void sortStudents(){
        System.out.println("\nView the list of students sorted in alphabetical order\n");
        if (studentCount == 1){
            System.out.println((1) + ". " + studentDetails[0][0] + " " + studentDetails[0][1]);
        }else if (studentCount > 2) {
            String[][] namesSorted = studentDetails;
            for (int i = 0; i < studentCount - 1; i++) {
                for (int j = i + 1; j < studentCount; j++) {
                    if (studentDetails[i][1].compareToIgnoreCase(studentDetails[j][1]) > 0) {
                        namesSorted[i] = studentDetails[j];
                        namesSorted[j] = studentDetails[i];
                    } else {
                        namesSorted[i] = studentDetails[i];
                    }
                }
            }
            for (int i = 0; i < namesSorted.length; i++) {
                System.out.println((i + 1) + ". " + namesSorted[i][0] + " " + namesSorted[i][1]);
            }
        }else{
            System.err.println("There is no student details available to view.");
        }
    }
}