/**
 * Project Name: cw_StudentActionManagementSystem
 * File Name: Student.java
 * Description: This has the Student class related methods for the program.
 * Author: Punjitha Bandara - w2083155
 * Start Date: July 05, 2024
 */

package Task02;

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

}
