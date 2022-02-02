import java.util.ArrayList;

public class StudentsDetails {
    private static StudentsDetails StudentsDetails = null;
    private ArrayList<Student> studentsList;

    private StudentsDetails() {
        studentsList = new ArrayList<Student>();
    }

    public static StudentsDetails getInstance() {
        if (StudentsDetails != null) {
            return StudentsDetails;
        }
        StudentsDetails = new StudentsDetails();
        return StudentsDetails;
    }

    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }

}
