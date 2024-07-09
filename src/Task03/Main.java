/**
 * Project Name: cw_StudentActionManagementSystem
 * File Name: Main.java
 * Description: This has the main functionality of the program.
 * Author: Punjitha Bandara - w2083155
 * Start Date: June 23, 2024
 */

package Task03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static int studentCount = 0; // Holds the count of registered students
    private static final Student[] students = new Student[100]; // Array to store student objects, with a maximum of 100 students

    /**
     * The main method that initiates the program.
     */
    public static void main(String[] args) {
        menu();
    }

    /**
     * Displays the main menu and handles user input for various functionalities.
     * Continuously displays the menu until a valid choice is made.
     * Handles invalid inputs gracefully by prompting the user to try again.
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
            System.out.println("8. More options");
            System.out.println();
            System.out.println("Enter your choice");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Invalid input, please try again");
                continue;
            }finally {
               scanner.nextLine(); // Consume the newline left-over
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
                case 8:
                    moreOptions(scanner);
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
        System.out.println("Available seats: " + (students.length - studentCount));
    }

    /**
     * Registers a new student if there is an available seat.
     * Prompts the user for a student ID and name, checks for ID uniqueness, and adds the student to the array.
     * @param scanner Scanner object for reading user input.
     */
    private static void registerStudent(Scanner scanner) {
        System.out.println("\nStudent Registration\n");
        if (100 > studentCount) {
            String studentID = studentIDInput(scanner);
            for (int i = 0; i < studentCount; i++) {
                if (students[i].getID().equals(studentID)) {
                    System.err.println("Student ID already exists.");
                    return;
                }
            }
            System.out.println("Enter student name: ");
            String studentName = scanner.nextLine();
            students[studentCount] = new Student(studentID, studentName);
            studentCount++;
        } else {
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
        if(studentCount > 0){
            System.out.println("\nDelete a Registered Student\n");
            String studentID = studentIDInput(scanner);
            for (int i = 0; i < studentCount; i++) {
                if (studentID.equals(students[i].getID())) {
                    studentCount--;
                    for (int j = 0; j < studentCount; j++) {
                        students[j] = students[j + 1];
                    }
                    students[studentCount] = null;
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
            for (int i = 0; i < studentCount; i++) {
                if (studentID.equals(students[i].getID())) {
                    System.out.println("Student ID: " + studentID + " Student Name: " + students[i].getName());
                    return;
                }
            }
            System.err.println("Student ID not found.");
        }else {
            System.err.println("\nThere is no student details available to find with student ID.");
        }
    }

    /**
     * Saves the details of all registered students to a file named "StudentDetails.txt".
     */
    private static void saveToFile(){
        if (studentCount > 0){
            System.out.println("\nSave Student Details to StudentDetails.txt File\n");
            try {
                FileWriter file = new FileWriter("StudentDetails.txt");
                for (int i = 0; i < studentCount; i++) {
                    file.write(students[i].getID() + "," + students[i].getName() + students[i].studentModuleMark() + "\n");
                }
                System.out.println("Student details saved.");
                file.close();
            } catch (IOException e) {
                System.err.println("Error while writing to the file");
                //e.printStackTrace();
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
                for (int i = 0; i < students.length; i++) {
                    if (students[i] == null) {
                        students[i] = new Student(data[0], data[1]);
                        Module[] module = new Module[3];
                        module[0] = new Module(Double.parseDouble(data[2]));
                        module[1] = new Module(Double.parseDouble(data[3]));
                        module[2] = new Module(Double.parseDouble(data[4]));
                        students[i].setModule(module);
                        studentCount++;
                        break;
                    }
                }
            }
            System.out.println("Student details loaded from the file.");
            fileReader.close();
        }catch(IOException e){
            System.err.println("Error while reading from file");
            //e.printStackTrace();
        }
    }

    /**
     * Sorts and displays the list of students based on their names in alphabetical order.
     */
    private static void sortStudents(){
        if (studentCount > 0) {
            System.out.println("\nView the list of students sorted in alphabetical order\n");
            Student[] namesSorted = new Student[studentCount];
            System.arraycopy(students, 0, namesSorted, 0, studentCount);
            for (int i = 0; i < namesSorted.length ; i++) {
                for (int j = i + 1; j < namesSorted.length; j++) {
                    if (namesSorted[i].getName().compareToIgnoreCase(namesSorted[j].getName()) > 0) {
                        Student temp = namesSorted[i];
                        namesSorted[i] = namesSorted[j];
                        namesSorted[j] = temp;
                    }
                }
            }
            for (int i = 0; i < studentCount; i++) {
                System.out.println((i + 1) + ". " + namesSorted[i].getID() + " " + namesSorted[i].getName());
            }
        }else{
            System.err.println("There is no student details available to view.");
        }
    }

    /**
     * Displays more options for the user to perform additional actions.
     * Continuously displays the options until a valid choice is made.
     * Handles invalid inputs gracefully by prompting the user to try again.
     * @param scanner Scanner object for reading user input.
     */
    private static void moreOptions(Scanner scanner){
        String choice;

        while(true) {
            System.out.println("\nMore Options\n");
            System.out.println("a. Add/Update student name");
            System.out.println("b. Module marks 1, 2 and 3");
            System.out.println("c. Generate a summary of the system");
            System.out.println("d. Generate a report with list of students");
            System.out.println();
            System.out.println("Enter your choice");
            try {
                choice = scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Invalid input, please try again");
                System.out.println();
                continue;
            }

            switch (choice) {
                case "a":
                    addName(scanner);
                    break;
                case "b":
                    addModuleMarks(scanner);
                    break;
                case "c":
                    generateSummary();
                    break;
                case "d":
                    generateReport();
                    break;
                default:
                    System.err.println("Invalid choice. Try again");
                    continue;
            }
            break;
        }
    }

    /**
     * Adds or updates the name of a student based on the provided student ID.
     * Prompts the user for a student ID and new name, then updates the student's name if the ID is found.
     * @param scanner Scanner object for reading user input.
     */
    private static void addName(Scanner scanner){
        if (studentCount > 0){
            System.out.println("\nAdd/Update Student Name\n");
            String studentID = studentIDInput(scanner);
            for (int i = 0; i < studentCount; i++) {
                if (studentID.equals(students[i].getID())) {
                    System.out.println("Enter student name: ");
                    students[i].setName(scanner.nextLine());
                    System.out.println("New student name updated.");
                    return;
                }
            }
            System.err.println("Student ID not found.");
        }else {
            System.err.println("There is no student details available add/update name with student ID.");
        }
    }

    /**
     * Adds module marks for a specific student identified by their student ID.
     * Prompts the user for a student ID and module marks, then updates the student's module marks if the ID is found.
     * Ensures that the entered marks are within the valid range (0 to 100).
     * @param scanner Scanner object for reading user input.
     */
    private static void addModuleMarks(Scanner scanner){
        if (studentCount > 0){
            System.out.println("\nAdd Module Marks\n");
            String studentID = studentIDInput(scanner);
            for (int i = 0; i < studentCount; i++) {
                if (studentID.equals(students[i].getID())) {
                    System.out.println("Student ID: " + studentID + "  Student Name: " + students[i].getName());
                    Module[] modules = new Module[3];
                    for (int j = 0; j < 3; j++) {
                        double mark;
                        while (true){
                            System.out.print("Enter module " + (j + 1) + " mark: ");
                            try {
                                mark = scanner.nextDouble();
                            } catch (InputMismatchException e) {
                                System.err.println("Invalid input, please enter a valid mark");
                                continue;
                            }finally {
                                scanner.nextLine();
                            }
                            if (mark > 100 || mark < 0) {
                                System.err.println("Invalid mark range, please enter a valid mark");
                                continue;
                            }
                            break;
                        }
                        modules[j] = new Module(mark);
                    }
                    students[i].setModule(modules);
                    System.out.println("Module marks added.");
                    return;
                }
            }
            System.err.println("Student ID not found.");
        }else {
            System.err.println("There is no student details available to add module marks.");
        }
    }

    /**
     * Generates a summary of the system, displaying the total number of students and the number of students who have passed all modules.
     * A student is considered to have passed if they score more than 40 marks in all modules.
     */
    private static void generateSummary(){
        if (studentCount > 0) {
            System.out.println("\nSummary of the System\n");
            System.out.println("* The total student registrations - " + studentCount);

            int passStudents = 0;
            for (int i = 0; i < studentCount; i++) {
                passStudents += students[i].countModulePassStudent();
            }
            System.out.println("* Total no of student scored more than 40 marks in all modules - " + passStudents);
        }else{
            System.err.println("There is no student details available to generate a summary.");
        }
    }

    /**
     * Generates a detailed report of all students, sorted by their average marks in descending order.
     * The report includes the student's ID, name, marks for each module, total marks, average marks, and grade.
     * The grade is determined based on the student's average marks.
     */
    private static void generateReport(){
        if (studentCount > 0) {
            System.out.println("\nReport of students\n");
            Student[] avgSorted = new Student[studentCount];
            System.arraycopy(students, 0, avgSorted, 0, studentCount);
            for (int i = 0; i < avgSorted.length ; i++) {
                for (int j = i + 1; j < avgSorted.length; j++) {
                    if (avgSorted[i].averageMarks() < avgSorted[j].averageMarks()) {
                        Student temp = avgSorted[i];
                        avgSorted[i] = avgSorted[j];
                        avgSorted[j] = temp;
                    }
                }
            }
            System.out.printf("%s | %-9s | %-25s | %s | %s | %s | %s | %-7s | %-11s | %n","Place", "Student ID", "Student Name", "Module 1 Marks", "Module 2 Marks", "Module 3 Marks", "Total Marks", "Average", "Grade");
            System.out.println();
            for (int i = 0; i < studentCount; i++) {
                System.out.print(i+1);
                avgSorted[i].report();
            }
            System.out.printf("%n %82s %n","-----End of the report-----");
        }else{
            System.err.println("There is no student details available to generate a report.");
        }
    }
}