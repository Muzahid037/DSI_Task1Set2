import java.util.ArrayList;

public class StudentsDetails {
    private static StudentsDetails StudentsDetails = null;
    private ArrayList<Student>[] studentsList;

    private StudentsDetails() {
        studentsList = new ArrayList[3];
//        studentsList = new ArrayList<Student>();
        for (int i = 0; i < 3; i++) {
            studentsList[i] = new ArrayList<Student>();
        }
    }

    public static StudentsDetails getInstance() {
        if (StudentsDetails != null) {
            return StudentsDetails;
        }
        StudentsDetails = new StudentsDetails();
        return StudentsDetails;
    }

    public ArrayList<Student> getStudentsList(int classIndex) {
        return studentsList[classIndex];
    }

//    public ArrayList<Student> getStudentsListByClass(int classIndex) {
//        return studentsList[classIndex];
//    }

}
