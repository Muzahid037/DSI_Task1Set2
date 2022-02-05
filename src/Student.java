import java.util.HashMap;
public class Student {
    private Integer studentId;
    private Integer className; ///8,9,10
    private String studentName;
    private HashMap<String, Boolean> subjectToBeTought; ///math,eng,ban
    private Double avgMarks;
    private Integer totalDayTought;
    private Double totalEarning;

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

    public Integer getClassName() {
        return className;
    }

    public String getStudentName() {
        return studentName;
    }

    public HashMap<String, Boolean> getSubjectToBeTought() {
        return subjectToBeTought;
    }

    public Double getAvgMarks() {
        return avgMarks;
    }

    public void setAvgMarks(Double avgMarks) {
        this.avgMarks = avgMarks;
    }

    public Integer getTotalDayTought() {
        return totalDayTought;
    }

    public void setTotalDayTought(Integer totalDayTought) {
        this.totalDayTought = totalDayTought;
    }

    public Double getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(Double totalEarning) {
        this.totalEarning = totalEarning;
    }
}
