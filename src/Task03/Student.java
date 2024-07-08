package Task03;

public class Student {
    private String name;
    private final String ID;
    private Module[] module;

    public Student(String ID, String name){
        this.name = name;
        this.ID = ID;
        this.module = new Module[3];
        module[0] = new Module(0.0);
        module[1] = new Module(0.0);
        module[2] = new Module(0.0);
    }

    public String getName(){
        return this.name;
    }

    public String getID(){
        return this.ID;
    }

    public void setModule(Module[] module){
        this.module = module;
    }

    public String studentModuleMark(){
        String[] studentMarks = new String[3];
        for (int i = 0; i < 3; i++) {
                studentMarks[i] = "," + this.module[i].getMarks();
        }
        return studentMarks[0] + studentMarks[1] + studentMarks[2];
    }

    public void setName(String name){
        this.name = name;
    }

    public int countModulePassStudent(){
        if (module[0].getMarks() > 40 && this.module[1].getMarks() > 40 && this.module[2].getMarks() > 40){
            return 1;
        }else {
            return 0;
        }
    }

    public Double totalMarks(){
        Double totalMarks = 0.0;
        for(Module marks : this.module){
            totalMarks += marks.getMarks();
        }
        return totalMarks;
    }

    public Double averageMarks(){
        return totalMarks() / 3;
    }

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

}
