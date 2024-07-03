import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static int studentCount = 0;
    private static final String[][] studentDetails = new String[100][2];

    public static void main(String[] args) {
        menu();
    }

    private static void menu(){
        Scanner scanner = new Scanner(System.in);
        int choice;

        while(true) {
            System.out.println("\nMenu");
            System.out.println();
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
                System.out.println("Invalid choice, please try again");
                System.out.println();
                scanner.next();
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
                default:
                    System.out.println("Invalid choice. Try again");
                    System.out.println();
                    continue;
            }
            break;
        }
    }

    private static void checkAvailableSeats(){
        System.out.println("Available seats: " + (studentDetails.length - studentCount));
    }

    private static void registerStudent(Scanner scanner){
        if (studentDetails.length >= studentCount) {
            while(true) {
                System.out.println("Enter student ID: ");
                String studentID = scanner.next();
                scanner.nextLine();
                if (!(studentID.startsWith("w") && studentID.length() == 8)){
                    System.out.println("Student ID must start with 'w' and contains 8 characters");
                    continue;
                }
                for (int i = 0; i < studentDetails.length; i++) {
                    if (studentID.equals(studentDetails[i][0]) ){
                        System.out.println("Student ID already exists.");
                        break;
                    }else if (studentDetails[i][0] == null) {
                        System.out.println("Enter student name: ");
                        String studentName = scanner.nextLine();
                        studentDetails[i][0] = studentID;
                        studentDetails[i][1] = studentName;
                        studentCount++;
                        break;
                    }
                }
                break;
            }
        }else{
            System.out.println("There are no more seats available");
        }

    }

    private static void deleteStudent(Scanner scanner){
        System.out.println("Enter student ID: ");
        String studentID = scanner.next();
        scanner.nextLine();
        for (int i = 0; i < studentDetails.length; i++) {
            if (studentID.equals(studentDetails[i][0]) ){
                studentDetails[i][0] = null;
                studentDetails[i][1] = null;
                studentCount--;
                System.out.println("Student ID: " + studentID + " deleted.");
                break;
            }
        }
    }

    private static void findStudent(Scanner scanner){
        System.out.println("Enter student ID: ");
        String studentID = scanner.next();
        scanner.nextLine();
        for (String[] studentDetail : studentDetails) {
            if (studentID.equals(studentDetail[0])) {
                System.out.println("Student ID: " + studentID);
                System.out.println("Student Name: " + studentDetail[1]);
                break;
            }
        }
    }

    private static void saveToFile(){
        try{
            FileWriter file = new FileWriter("StudentDetails.txt");
            for (String[] studentDetail : studentDetails) {
                if (studentDetail[0] != null) {
                    file.write(studentDetail[0] + "," + studentDetail[1] + "\n");
                }
            }
            file.close();
        }catch (IOException e){
            System.out.println("Error while writing to the file");
            e.printStackTrace();
        }
        System.out.println("Student details saved.");
    }

    private static void loadFromFile(){
        try{
            File file = new File("StudentDetails.txt");
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine()){
                String[] data = fileReader.nextLine().split(",");
                if (!(data[0] == null)){
                    for (int i = 0; i < studentDetails.length; i++) {
                        if (studentDetails[i][0] == null) {
                            studentDetails[i][0] = data[0];
                            studentDetails[i][1] = data[1];
                            studentCount++;
                            break;
                        }
                    }
                }
            }
            fileReader.close();
        }catch(IOException e){
            System.out.println("Error while reading from file");
            e.printStackTrace();
        }
    }

    private static void sortStudents(){

    }
}