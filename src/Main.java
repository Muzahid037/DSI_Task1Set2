import java.util.*;


public class Main {

    private Integer getClassIndex(Integer className) {
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

        System.out.println("Enter className-8/9/10");
        Integer className = Integer.parseInt(scannerObj.nextLine());

        System.out.println("Enter Student Id");
        Integer studentId = Integer.parseInt(scannerObj.nextLine());

        System.out.println("Enter Student Name");
        String studentName = scannerObj.nextLine();

        Integer classIndex = getClassIndex(className);
        if (classIndex == -1) {
            System.out.println("Enter valid class");
            return;
        }


        HashMap<String, Boolean> subjectToBeTought = new HashMap<String, Boolean>(); ///math,eng,ban
        String[] subjects = {"Math", "Eng", "Ban"};
        for (Integer i = 0; i < subjects.length; i++) {
            System.out.println("Do you teach " + subjects[i] + "? Press 1-YES, 2-NO");
            Integer yesOrNo = Integer.parseInt(scannerObj.nextLine());
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
        Integer className = Integer.parseInt(scannerObj.nextLine());
        Integer classIndex = getClassIndex(className);
        if (classIndex == -1) {
            System.out.println("Enter valid class");
            return;
        }

        System.out.println("Enter Student Id");
        Integer studentId = Integer.parseInt(scannerObj.nextLine());

        StudentsController stdController = new StudentsController();
        ArrayList<Student> studentsByClass = stdController.getStudents(classIndex);

        for (Student std : studentsByClass) {
            if (std.studentId == studentId) {
//                System.out.println(std.studentId + " " + std.studentName);

                System.out.println("Enter How Many days you taught " + std.studentName + "?");
                Integer dayTaught = Integer.parseInt(scannerObj.nextLine());
                std.totalDayTought += dayTaught;

                HashMap<String, Boolean> subjectTought = std.subjectToBeTought;

                Double totalMarks = 0.0;
                Integer numberOfSubjectTought = 0;
                for (Map.Entry mapElement : subjectTought.entrySet()) {
                    String subject = (String) mapElement.getKey();
                    boolean isTaught = (Boolean) mapElement.getValue();
                    if (isTaught == true) {
                        numberOfSubjectTought++;
                        std.totalEarning += (dayTaught * 1.0);
                        System.out.println("Enter marks got in " + subject + " by " + std.studentName + "?");
                        Double marks = Double.parseDouble(scannerObj.nextLine());
                        totalMarks += marks;
                    }
                }
                std.avgMarks = (totalMarks / numberOfSubjectTought);

            }
        }

    }

    private void showStudentList() {
        Scanner scannerObj = new Scanner(System.in);

        System.out.println("Enter className-8/9/10");
        Integer className = Integer.parseInt(scannerObj.nextLine());
        Integer classIndex = getClassIndex(className);
        if (classIndex == -1) {
            System.out.println("Enter valid class");
            return;
        }

        StudentsController stdController = new StudentsController();
        ArrayList<Student> studentsByClass = stdController.getStudents(classIndex);

        if (studentsByClass.size() > 0) {

            Formatter formatter = new Formatter();
            Integer idMaxLength = 7;
            String idFormat = "%-" + idMaxLength + "." + idMaxLength + "s";

            Integer nameMaxLength = 22;
            String nameFormat = "%-" + nameMaxLength + "." + nameMaxLength + "s";

            Integer earningMaxLength = 15;
            String earningFormat = "%-" + earningMaxLength + "." + earningMaxLength + "s";

            Integer avgMarksMaxLength = 15;
            String avgMarksFormat = "%-" + avgMarksMaxLength + "." + avgMarksMaxLength + "s";

            System.out.println(formatter.format(idFormat + nameFormat + earningFormat + avgMarksFormat,
                    "Id", "Name", "Earning", "Avg-Marks"));
            for (Student s : studentsByClass) {
                formatter = new Formatter();
                System.out.println(formatter.format(idFormat + nameFormat + earningFormat + avgMarksFormat,
                        s.studentId, s.studentName, s.totalEarning, s.avgMarks));
            }

            System.out.println("Wanna see details any of the above student? Press 1-YES, 2-NO");
            Integer isWanted = Integer.parseInt(scannerObj.nextLine());
            if (isWanted == 1) {
                System.out.println("Enter Student Id");
                Integer specificStudentId = Integer.parseInt(scannerObj.nextLine());
                Integer specificClassName = new Integer(8);
                String specificStudentName = new String("");
                HashMap<String, Boolean> specificSubjectTought = new HashMap<>();
                Double specificAvgMarks = new Double(0.0);
                Integer specificTotalDayTought = new Integer(0);
                ;
                Double specificTotalEarning = new Double(0.0);
                Boolean isExistStd = false;
                for (Student std : studentsByClass) {
                    if (std.studentId == specificStudentId) {
                        isExistStd = true;
                        specificClassName = std.className;
                        specificStudentName = std.studentName;
                        specificSubjectTought = std.subjectToBeTought;
                        specificAvgMarks = std.avgMarks;
                        specificTotalDayTought = std.totalDayTought;
                        specificTotalEarning = std.totalEarning;

//                        System.out.println(std.studentId + " " + std.className + " " +
//                                std.avgMarks + " " + std.totalDayTought + " " + std.totalEarning);
                    }
                }
                if (isExistStd == true) {

                    formatter = new Formatter();

                    Integer classNameMaxLength = 6;
                    String classNameFormat = "%-" + classNameMaxLength + "." + classNameMaxLength + "s";

                    Integer subjectMaxLength = 15;
                    String subjectFormat = "%-" + subjectMaxLength + "." + subjectMaxLength + "s";

                    Integer dayTaughtMaxLength = 20;
                    String dayTaughtFormat = "%-" + dayTaughtMaxLength + "." + dayTaughtMaxLength + "s";


                    System.out.println(formatter.format(idFormat + classNameFormat + nameFormat + subjectFormat +
                                    avgMarksFormat + dayTaughtFormat + earningFormat, "Id", "Class",
                            "Name", "Subject", "Avg-Marks", "Day-Taught", "Earning"));

                    formatter = new Formatter();
                    String taughtSubjects = new String("");
                    for (Map.Entry mapElement : specificSubjectTought.entrySet()) {
                        String subject = (String) mapElement.getKey();
                        boolean isTaught = (Boolean) mapElement.getValue();
                        if (isTaught == true) {
                            taughtSubjects += subject + " ";
                        }
                    }

                    System.out.println(formatter.format(idFormat + classNameFormat + nameFormat + subjectFormat +
                                    avgMarksFormat + dayTaughtFormat + earningFormat, specificStudentId,
                            specificClassName, specificStudentName, taughtSubjects, specificAvgMarks,
                            specificTotalDayTought, specificTotalEarning));

                } else {
                    System.out.println("There is no student with Id-" + specificStudentId);
                }
            }

        } else {
            System.out.println("There is no student in this class");
        }

    }

    public static void main(String[] args) {

        Main mainInstance = new Main();

        Integer exit = 0;
        while (exit == 0) {

            mainInstance.showDashboard();

            Scanner scannerObj = new Scanner(System.in);
            Integer choice = scannerObj.nextInt();

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
