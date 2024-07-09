/**
 * Project Name: cw_StudentActionManagementSystem
 * File Name: Student.java
 * Description: This has the Student class related methods for the program.
 * Author: Punjitha Bandara - w2083155
 * Start Date: July 05, 2024
 */

package Task03;

public class Student {
    private String name;
    private final String ID;
    private Module[] module;

    /**
     * Constructs a new Student with the specified ID and name.
     * Initializes the module array with default values.
     * @param ID The unique identifier for the student.
     * @param name The name of the student.
     */
    public Student(String ID, String name){
        this.name = name;
        this.ID = ID;
        this.module = new Module[3];
        module[0] = new Module(00.0);
        module[1] = new Module(00.0);
        module[2] = new Module(00.0);
    }

    /**
     * Returns the student's name.
     * @return The name of the student.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the student's ID.
     * @return The unique ID of the student.
     */
    public String getID(){
        return this.ID;
    }

    /**
     * Sets the student's modules with the given array of Module objects.
     * @param module An array of Module objects representing the student's marks in different modules.
     */
    public void setModule(Module[] module){
        this.module = module;
    }

    /**
     * Generates a comma-separated string of the student's marks in each module.
     * @return A string representation of the student's marks in each module, separated by commas.
     */
    public String studentModuleMark(){
        String[] studentMarks = new String[3];
        for (int i = 0; i < 3; i++) {
                studentMarks[i] = "," + this.module[i].getMarks();
        }
        return studentMarks[0] + studentMarks[1] + studentMarks[2];
    }

    /**
     * Sets the student's name.
     * @param name The name of the student.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Determines if the student has passed all modules.
     * A student is considered to have passed a module if their marks are greater than 40.
     * @return 1 if the student has passed all modules, 0 otherwise.
     */
    public int countModulePassStudent(){
        if (module[0].getMarks() > 40 && this.module[1].getMarks() > 40 && this.module[2].getMarks() > 40){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * Calculates the total marks obtained by the student.
     * This method sums the marks obtained in each module to find the total score.
     * @return The total marks obtained by the student.
     */
    public Double totalMarks(){
        Double totalMarks = 0.0;
        for(Module marks : this.module){
            totalMarks += marks.getMarks();
        }
        return totalMarks;
    }

    /**
     * Calculates the average marks obtained by the student.
     * This method divides the total marks by the number of modules to find the average score.
     * @return The average marks obtained by the student.
     */
    public Double averageMarks(){
        return totalMarks() / 3;
    }

    /**
     * Determines the grade of the student based on their average marks.
     * Grades are assigned as follows: Distinction (80+), Merit (70-79), Pass (40-69), and Fail (<40).
     * @return The grade of the student as a string.
     */
    public String grade(){
        Double averageMarks = averageMarks();

        if(averageMarks >= 80){
            return "Distinction";
        }
        else if(averageMarks >= 70){
            return "Merit";
        }
        else if(averageMarks >= 40){
            return "Pass";
        }else {
            return "Fail";
        }
    }

    /**
     * Prints a detailed report of the student's performance.
     * The report includes the student's ID, name, marks for each module, total marks, average marks, and grade.
     * This method formats the information into a readable string suitable for console output or file storage.
     */
    public void report(){
        System.out.printf("%-4s | %-10s | %-25s | %05.2f  %-7s | %05.2f  %-7s | %05.2f  %-7s | %06.2f  %-3s | %06.2f %s | %-11s | %n","", this.ID,this.name,this.module[0].getMarks(),"",this.module[1].getMarks(),"",this.module[2].getMarks(),"",totalMarks(),"",averageMarks(),"",grade());
    }

}
