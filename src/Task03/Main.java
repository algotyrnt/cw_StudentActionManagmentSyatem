package Task03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static int studentCount = 0;
    private static final Student[] students = new Student[100];

    public static void main(String[] args) {
        menu();
    }

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
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Invalid input, please try again");
                System.out.println();
                continue;
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

    private static void checkAvailableSeats(){
        System.out.println("\nSeat Availability\n");
        System.out.println("Available seats: " + (students.length - studentCount));
    }

    private static void registerStudent(Scanner scanner) {
        System.out.println("\nStudent Registration\n");
        if (100 > studentCount) {
            while (true) {
                System.out.println("Enter student ID: ");
                String studentID = scanner.next();
                scanner.nextLine();
                if (!(studentID.startsWith("w") && studentID.length() == 8)) {
                    System.err.println("Student ID must start with 'w' and contains 8 characters");
                    continue;
                }
                for (Student student : students) {
                    if(student != null && studentID.equals(student.getID())){
                        System.err.println("Student ID already exists.");
                        return;

                    }
                }
                for (int i = 0; i < students.length; i++) {
                    if (students[i] == null) {
                        System.out.println("Enter student name: ");
                        String studentName = scanner.nextLine();
                        students[i] = new Student(studentID, studentName);
                        studentCount++;
                        break;
                    }
                }
                break;
            }
        } else {
            System.out.println("There are no more seats available");
        }
    }

    private static void deleteStudent(Scanner scanner){
        System.out.println("\nDelete a Registered Student\n");
        if(studentCount > 0){
            System.out.println("Enter student ID: ");
            String studentID = scanner.next();
            for (int i = 0; i < students.length; i++) {
                if (students[i] != null && studentID.equals(students[i].getID())) {
                    students[i] = null;
                    studentCount--;
                    System.out.println("Student ID: " + studentID + " deleted.");
                    break;
                }
            }
        }else{
            System.err.println("There is no student details available to delete.");
        }
    }

    private static void findStudent(Scanner scanner){
        if (studentCount > 0){
            System.out.println("\nFind a Registered Student\n");
            System.out.println("Enter student ID: ");
            String studentID = scanner.next();
            for (Student student : students) {
                if (student != null && studentID.equals(student.getID())) {
                    System.out.println("Student ID: " + studentID);
                    System.out.println("Student Name: " + student.getName());
                    return;
                }
            }
            System.err.println("Student ID not found.");

        }else {
            System.err.println("There is no student details available to find with student ID.");
        }
    }

    private static void saveToFile(){
        if (studentCount > 0){
            System.out.println("\nSave Student Details to StudentDetails.txt File\n");
            try {
                FileWriter file = new FileWriter("StudentDetails.txt");
                for (Student studentDetail : students) {
                    if (studentDetail != null) {
                        file.write(studentDetail.getID() + "," + studentDetail.getName() + studentDetail.studentModuleMark() + "\n");
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
            e.printStackTrace();
        }
    }

    private static void sortStudents(){
        System.out.println("\nView the list of students sorted in alphabetical order\n");
        if (studentCount > 0) {
            Student[] namesSorted = new Student[studentCount];
            int x = 0;
            for (Student student : students) {
                if (student != null) {
                    namesSorted[x] = student;
                    x++;
                }
            }
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

    private static void addName(Scanner scanner){
        if (studentCount > 0){
            System.out.println("\nAdd/Update Student Name\n");
            System.out.println("Enter student ID: ");
            String studentID = scanner.nextLine();
            for (Student student : students) {
                if (student != null && studentID.equals(student.getID())) {
                    System.out.println("Enter student name: ");
                    String studentName = scanner.nextLine();
                    student.setName(studentName);
                    System.out.println("New student name updated.");
                    return;
                }
            }
            System.err.println("Student ID not found.");

        }else {
            System.err.println("There is no student details available add/update name with student ID.");
        }
    }

    private static void addModuleMarks(Scanner scanner){
        if (studentCount > 0){
            System.out.println("\nAdd Module Marks\n");
            System.out.println("Enter student ID: ");
            String studentID = scanner.nextLine();
            for (Student student : students) {
                if (student != null && studentID.equals(student.getID())) {
                    System.out.println("Student ID: " + studentID + "  Student Name: " + student.getName());
                    Module[] modules = new Module[3];
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Enter module " + (i + 1) + " mark: ");
                        double mark;
                        while (true){
                            try {
                                mark = scanner.nextDouble();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                System.err.println("Invalid input, please enter a valid mark");
                                continue;
                            }
                            break;
                        }
                        modules[i] = new Module(mark);
                        student.setModule(modules);
                    }
                    return;
                }
            }
            System.err.println("Student ID not found.");
        }else {
            System.err.println("There is no student details available to add module marks.");
        }
    }

    private static void generateSummary(){
        System.out.println("\nSummary of the System\n");
        System.out.println("* The total student registrations - " + studentCount);

        int passStudents = 0;
        for (Student student : students) {
            if (student != null) {
                passStudents += student.countModulePassStudent();
            }
        }
        System.out.println("* Total no of student scored more than 40 marks in all modules - " + passStudents);
    }

    private static void generateReport(){

    }
}