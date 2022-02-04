import java.util.ArrayList;

public class StudentsDetails {
    private static StudentsDetails StudentsDetails = null;
    private ArrayList<Student>[] studentsList;
    private ArrayList<Integer> totalDayTaughtAllClass;
    private ArrayList<Double> totalEarningAllClass;
    Integer totalExamCount;
    Double totalMarksCount;

    private StudentsDetails() {
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


    public static StudentsDetails getInstance() {
        if (StudentsDetails != null) {
            return StudentsDetails;
        }
        StudentsDetails = new StudentsDetails();
        return StudentsDetails;
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

//    private Student getStudentIndexByClass(ArrayList<Student> StudentsListByClass,
//                                           Integer studentID) {
//
//        return -1;
//    }

    public Integer removeStudent(Integer studentID, Integer classIndex) {
//        ArrayList<Student> StudentsListByClass = studentsList[classIndex];
//        Student indexOfRemoveAbleStd = getStudentIndexByClass(StudentsListByClass, studentID);
//        System.out.println("Ashci "+indexOfRemoveAbleStd);
//        studentsList[classIndex].remove(indexOfRemoveAbleStd);
//        return indexOfRemoveAbleStd;
        Student b = null;
        Integer isDeleted=0;
        for (Student std : studentsList[classIndex]) {
            if (std.studentId.equals(studentID)) {
                b = std;
                isDeleted=1;
                break;
            }
        }
        studentsList[classIndex].remove(b);
        return isDeleted;
    }

    public ArrayList<Student> getStudentsList(Integer classIndex) {
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
