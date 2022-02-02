import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

    private void showDashboard() {
        System.out.println("Press 1-Add a Student");
        System.out.println("Press 2-Edit a Student");
        System.out.println("Press 3-Delete a Student");
        System.out.println("Press 4-See the list of students individually");
        System.out.println("Press 5-See overall info");
        System.out.println("Press 6-Exit");
    }

    private void addStudent() {
        Scanner scannerObj = new Scanner(System.in);

        System.out.println("Enter Student Id");
        int studentId;
        studentId = scannerObj.nextInt();

        scannerObj.nextLine();

        System.out.println("Enter Student Name");
        String studentName = scannerObj.nextLine();

        System.out.println("Enter className-8/9/10");
        int className; ///8,9,10
        className = scannerObj.nextInt();

        HashMap<String, Boolean> subjectToBeTought = new HashMap<String, Boolean>(); ///math,eng,ban
        String[] subjects = {"Math", "Eng", "Ban"};
        for (int i = 0; i < subjects.length; i++) {
            System.out.println("Do you teach " + subjects[i] + "? Press 1-YES, 2-NO");
            int yesOrNo = scannerObj.nextInt();
            boolean isTought = false;
            if (yesOrNo == 1) {
                isTought = true;
            }
            subjectToBeTought.put(subjects[i], isTought);
        }
        Student std = new Student(studentId, className, studentName, subjectToBeTought,
                0, 0, 0);
        StudentsController stdController = new StudentsController();
        stdController.addStudent(std);
    }

    private void showStudentList() {
//        System.out.println("Ashci");
        Scanner scannerObj = new Scanner(System.in);

        System.out.println("Students");
        StudentsController stdController = new StudentsController();
        ArrayList<Student> studentsList = stdController.getStudents();


        Formatter formatter = new Formatter();

        int idMaxLength = 7;
        String idFormat = "%-" + idMaxLength + "." + idMaxLength + "s";

        int nameMaxLength = 25;
        String nameFormat = "%-" + nameMaxLength + "." + nameMaxLength + "s";

        int earningMaxLength = 15;
        String earningFormat = "%-" + earningMaxLength + "." + earningMaxLength + "s";

        int avgMarksMaxLength = 15;
        String avgMarksFormat = "%-" + avgMarksMaxLength + "." + avgMarksMaxLength + "s";

        System.out.println(formatter.format(idFormat + nameFormat + earningFormat + avgMarksFormat, "Id", "Name", "Earning", "Avg-Marks"));
        for (Student s : studentsList) {
            formatter = new Formatter();
            System.out.println(formatter.format(idFormat + nameFormat + earningFormat + avgMarksFormat, s.studentId,
                    s.studentName, s.totalEarning, s.avgMarks));
        }

    }

    public static void main(String[] args) {

        Main mainInstance = new Main();

        int exit = 0;
        while (exit == 0) {

            mainInstance.showDashboard();

            Scanner scannerObj = new Scanner(System.in);
            int choice = scannerObj.nextInt();

            switch (choice) {
                case 1:
                    mainInstance.addStudent();
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 3:
                    System.out.println("3");
                    break;
                case 4:
                    System.out.println("4");
                    mainInstance.showStudentList();
                    break;
                case 5:
                    System.out.println("5");
                    break;

                default:
                    exit = 1;
                    break;
            }
        }

    }
}
