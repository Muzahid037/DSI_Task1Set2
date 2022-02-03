import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentsController {
    StudentsDetails studentsDetails;

    public StudentsController() {
        {
            studentsDetails = studentsDetails.getInstance();
        }
    }

    public void addStudent(Student student,int classIndex) {
        studentsDetails.getStudentsList(classIndex).add(student);
    }

    public void editStudent(Student student) {
//        if (getStudentElementIndex(student.getStudent_id()) >= 0) {
//            studentsDetails.getStudentsList().set(getStudentElementIndex(
//                    student.getStudent_id()), student);
//        }
    }


    public void delete_student(Integer student_id) {
//        if (getStudentElementIndex(student_id) >= 0) {
//            studentsDetails.getStudentsList().remove(getStudentElementIndex(
//                    student_id));
//        }
    }

//    public int getStudentElementIndex(Integer student_id) {
//        for(Student s: studentsDetails.getStudentsList()) {
//            if(s.getStudent_id().equals(student_id)) {
//                return studentsDetails.getStudentsList().indexOf(s);
//            }
//        }
//        return -1;
//    }


//    public Student getStudent(Integer student_id) {
//        return studentsDetails.getStudentsList().get(student_id);
//    }

//    public List<Student> getStudents() {
//        return studentsDetails.getStudentsList();
//    }

        public ArrayList<Student> getStudents(int classIndex) {
        return studentsDetails.getStudentsList(classIndex);
    }
//    public ArrayList<Student> getStudentsByClass(int classIndex) {
//        return studentsDetails.getStudentsListByClass(classIndex);
//    }


}
