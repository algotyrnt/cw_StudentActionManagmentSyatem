import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        menu();
    }

    private static void menu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu");
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
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                checkAvailableSeats();
                break;
            case 2:
                registerStudent();
                break;
            case 3:
                deleteStudent();
                break;
            case 4:
                findStudent();
                break;
            case 5:
                saveToFile();
                break;
            case 6:
                loadFromFile();
                break;
            case 7:
                sortStudents();
                break;
            default:
                System.out.println("Invalid choice. Try again");
                System.out.println();
                menu();
        }
    }
    private static void checkAvailableSeats(){
        System.out.println("Available seats: 100");
    }
    private static void registerStudent(){

    }
    private static void deleteStudent(){

    }
    private static void findStudent(){

    }
    private static void saveToFile(){

    }
    private static void loadFromFile(){

    }
    private static void sortStudents(){

    }
}