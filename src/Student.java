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

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getClassName() {
        return className;
    }

    public void setClassName(Integer className) {
        this.className = className;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public HashMap<String, Boolean> getSubjectToBeTought() {
        return subjectToBeTought;
    }

    public void setSubjectToBeTought(HashMap<String, Boolean> subjectToBeTought) {
        this.subjectToBeTought = subjectToBeTought;
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
