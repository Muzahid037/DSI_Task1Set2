import java.util.*;


public class Main {

    private int getClassIndex(int className) {
        int classIndex = -1;
        if (className == 8) {
            classIndex = 0;
        } else if (className == 9) {
            classIndex = 1;
        } else if (className == 10) {
            classIndex = 2;
        }
        return classIndex;
    }

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
        int studentId = scannerObj.nextInt();

        scannerObj.nextLine();

        System.out.println("Enter Student Name");
        String studentName = scannerObj.nextLine();

        System.out.println("Enter className-8/9/10");
        int className = scannerObj.nextInt();///8,9,10
        int classIndex = getClassIndex(className);
        if (classIndex == -1) {
            System.out.println("Enter valid class");
            return;
        }


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
                0.0, 0, 0.0);
        StudentsController stdController = new StudentsController();
        stdController.addStudent(std, classIndex);
        System.out.println("Student Added Successfully");
    }

    private void editStudent() {
        Scanner scannerObj = new Scanner(System.in);

        System.out.println("Enter className-8/9/10");
        int className = scannerObj.nextInt();
        int classIndex = getClassIndex(className);
        if (classIndex == -1) {
            System.out.println("Enter valid class");
            return;
        }

        System.out.println("Enter Student Id");
        int studentId = scannerObj.nextInt();

        StudentsController stdController = new StudentsController();
        ArrayList<Student> studentsByClass = stdController.getStudents(classIndex);

        for (Student std : studentsByClass) {
            if (std.studentId == studentId) {
//                System.out.println(std.studentId + " " + std.studentName);

                System.out.println("Enter How Many days you taught " + std.studentName + "?");
                int dayTaught = scannerObj.nextInt();
                std.totalDayTought += dayTaught;

                HashMap<String, Boolean> subjectTought = std.subjectToBeTought;

                double totalMarks = 0.0;
                int numberOfSubjectTought = 0;
                for (Map.Entry mapElement : subjectTought.entrySet()) {
                    String subject = (String) mapElement.getKey();
                    boolean isTaught = (Boolean) mapElement.getValue();
                    if (isTaught == true) {
                        numberOfSubjectTought++;
                        std.totalEarning += (dayTaught * 1.0);
                        System.out.println("Enter marks got in" + subject + " by him/r?");
                        double marks = scannerObj.nextDouble();
                        totalMarks += marks;
                    }
//                    System.out.println(subject + " : " + isTaught);
                }
                std.avgMarks = (totalMarks / numberOfSubjectTought);

            }
        }

    }

    private void showStudentList() {
//        System.out.println("Ashci");
        Scanner scannerObj = new Scanner(System.in);


        System.out.println("Enter className-8/9/10");
        int className = scannerObj.nextInt();
        int classIndex = getClassIndex(className);
        if (classIndex == -1) {
            System.out.println("Enter valid class");
            return;
        }

        System.out.println("Enter Student Id");
        int studentId = scannerObj.nextInt();

        StudentsController stdController = new StudentsController();
        ArrayList<Student> studentsByClass = stdController.getStudents(classIndex);


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
        for (Student s : studentsByClass) {
            formatter = new Formatter();
            System.out.println(formatter.format(idFormat + nameFormat + earningFormat + avgMarksFormat, s.studentId,
                    s.studentName, s.totalEarning, s.avgMarks));
        }


//
//        System.out.println("Students");
//        StudentsController stdController = new StudentsController();
//
//        ArrayList<Student> studentsList = stdController.getStudents();
//
//
//        Formatter formatter = new Formatter();
//
//        int idMaxLength = 7;
//        String idFormat = "%-" + idMaxLength + "." + idMaxLength + "s";
//
//        int nameMaxLength = 25;
//        String nameFormat = "%-" + nameMaxLength + "." + nameMaxLength + "s";
//
//        int earningMaxLength = 15;
//        String earningFormat = "%-" + earningMaxLength + "." + earningMaxLength + "s";
//
//        int avgMarksMaxLength = 15;
//        String avgMarksFormat = "%-" + avgMarksMaxLength + "." + avgMarksMaxLength + "s";
//
//        System.out.println(formatter.format(idFormat + nameFormat + earningFormat + avgMarksFormat, "Id", "Name", "Earning", "Avg-Marks"));
//        for (Student s : studentsList) {
//            formatter = new Formatter();
//            System.out.println(formatter.format(idFormat + nameFormat + earningFormat + avgMarksFormat, s.studentId,
//                    s.studentName, s.totalEarning, s.avgMarks));
//        }

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
                    mainInstance.editStudent();
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
