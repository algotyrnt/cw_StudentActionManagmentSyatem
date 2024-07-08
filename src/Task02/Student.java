package Task02;

public class Student {
    private String name;
    private final String ID;
    private Module[] module;

    public Student(String ID, String name){
        this.name = name;
        this.ID = ID;
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
        if(this.module != null){
            for (int i = 0; i < 3; i++) {
                studentMarks[i] = "," + this.module[i].getMarks();
            }
        }else{
            for (int i = 0; i < 3; i++) {
                studentMarks[i] = "," + "0.0";
            }
        }
        return studentMarks[0] + studentMarks[1] + studentMarks[2];
    }

    public void setName(String name){
        this.name = name;
    }

}
