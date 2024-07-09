/**
 * Project Name: cw_StudentActionManagementSystem
 * File Name: Student.java
 * Description: This has the Module class related methods for the program.
 * Author: Punjitha Bandara - w2083155
 * Start Date: July 05, 2024
 */

package Task03;

public class Module {
    private final Double marks;

    /**
     * Constructs a new Module with the specified mark.
     * @param marks The mark of the module.
     */
    public Module(Double marks){
        this.marks = marks;
    }

    /**
     * Returns the mark of the module.
     * @return The mark of this module.
     */
    public Double getMarks() {
        return marks;
    }


}
