import java.util.ArrayList;

public class StudentsController {
    private static StudentsController StudentsController = null;
    private ArrayList<Student>[] studentsList;
    private ArrayList<Integer> totalDayTaughtAllClass;
    private ArrayList<Double> totalEarningAllClass;
    Integer totalExamCount;
    Double totalMarksCount;

    private StudentsController() {
        studentsList = new ArrayList[3];
        totalDayTaughtAllClass = new ArrayList<>(3);
        totalEarningAllClass = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            studentsList[i] = new ArrayList<Student>();
            totalDayTaughtAllClass.add(0);
            totalEarningAllClass.add(0.0);
        }

        totalExamCount = 0;
        totalMarksCount = 0.0;
    }


    public static StudentsController getInstance() {
        if (StudentsController != null) {
            return StudentsController;
        }
        StudentsController = new StudentsController();
        return StudentsController;
    }

    public void addStudent(Student student, Integer classIndex) {

        studentsList[classIndex].add(student);
    }

    public Integer getClassIndex(Integer className) {
        Integer classIndex = -1;
        if (className == 8) {
            classIndex = 0;
        } else if (className == 9) {
            classIndex = 1;
        } else if (className == 10) {
            classIndex = 2;
        }
        return classIndex;
    }


    public Integer removeStudent(Integer studentID, Integer classIndex) {

        Student deleteAbleStd = null;
        Integer isDeleted=0;
        for (Student std : studentsList[classIndex]) {
            if (std.getStudentId().equals(studentID)) {
                deleteAbleStd = std;
                isDeleted=1;
                break;
            }
        }
        studentsList[classIndex].remove(deleteAbleStd);
        return isDeleted;
    }

    public ArrayList<Student> getStudentsListByClass(Integer classIndex) {
        return studentsList[classIndex];
    }

    public void setTotalDayTaughtByClass(Integer classIndex, Integer dayTaught) {
        Integer totalDayTaughtByClass = getTotalDayTaughtByClass(classIndex);
        totalDayTaughtAllClass.set(classIndex, totalDayTaughtByClass + dayTaught);
    }

    public Integer getTotalDayTaughtByClass(Integer classIndex) {
        return totalDayTaughtAllClass.get(classIndex);
    }

    public void setTotalEarningByClass(Integer classIndex, Double earning) {
        Double totalEarningByClass = getTotalEarningByClass(classIndex);
        totalEarningAllClass.set(classIndex, totalEarningByClass + earning);
    }

    public Double getTotalEarningByClass(Integer classIndex) {
        return totalEarningAllClass.get(classIndex);
    }


    public void setTotalMarkXmCountAllStd(Integer numberOfSubjectTought, Double totalMarks) {
        totalExamCount += numberOfSubjectTought;
        totalMarksCount += totalMarks;
    }

    public Double getAvgMarksAllStd() {
        if (totalExamCount == 0) return 0.0;
        else return totalMarksCount / totalExamCount;
    }


}
