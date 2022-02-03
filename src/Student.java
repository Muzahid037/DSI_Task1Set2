import java.util.HashMap;
public class Student {
    Integer studentId;
    Integer className; ///8,9,10
    String studentName;
    HashMap<String, Boolean> subjectToBeTought; ///math,eng,ban
    Double avgMarks;
    Integer totalDayTought;
    Double totalEarning;

    public Student(Integer studentId,Integer className,String studentName, HashMap<String, Boolean> subjectToBeTought,
                   Double avgMarks,Integer totalDayTought,Double totalEarning)
    {
        this.studentId=studentId;
        this.className=className;
        this.studentName=studentName;
        this.subjectToBeTought=subjectToBeTought;
        this.avgMarks=avgMarks;
        this.totalDayTought=totalDayTought;
        this.totalEarning=totalEarning;
    }
    public Integer getStudentId()
    {
        return this.studentId;
    }
}
