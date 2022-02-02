import java.util.HashMap;
public class Student {
    Integer studentId;
    int className; ///8,9,10
    String studentName;
    HashMap<String, Boolean> subjectToBeTought; ///math,eng,ban
    double avgMarks;

    int totalDayTought;
    double totalEarning;

    public Student(int studentId,int className,String studentName, HashMap<String, Boolean> subjectToBeTought,
                   double avgMarks,int totalDayTought,double totalEarning)
    {
        this.studentId=studentId;
        this.className=className;
        this.studentName=studentName;
        this.subjectToBeTought=subjectToBeTought;
        this.avgMarks=avgMarks;
        this.totalDayTought=totalDayTought;
        this.totalEarning=totalEarning;
    }
    public Integer getStudent_id()
    {
        return studentId;
    }
}
