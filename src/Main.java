import java.io.IOException;
import java.util.*;


public class Main {
    StudentsController StudentsController;
    private static Scanner scannerObj = new Scanner(System.in);
    private Integer specificStudentId;

    public Main() {
        {
            StudentsController = StudentsController.getInstance();
        }
    }

    private static void showMenuboard() {
        clearScreen();
        System.out.println("+----------------------------------------------+");
        System.out.println("Press 1-Add a Student");
        System.out.println("Press 2-Edit a Student");
        System.out.println("Press 3-Delete a Student");
        System.out.println("Press 4-See the list of students individually");
        System.out.println("Press 5-See overall info");
        System.out.println("Press 6-Exit");
        System.out.println("+----------------------------------------------+");
    }

    private Student createStudent(Integer className) {
        System.out.println("Enter Student Id");
        Integer studentId = Integer.parseInt(scannerObj.nextLine());

        System.out.println("Enter Student Name");
        String studentName = scannerObj.nextLine();

        HashMap<String, Boolean> subjectToBeTought = new HashMap<String, Boolean>(); ///math,eng,ban
        String[] subjects = {"Math", "Eng", "Ban"};
        for (Integer i = 0; i < subjects.length; i++) {
            System.out.println("Do you teach " + subjects[i] + "? Press 1-YES, 2-NO");
            Integer yesOrNo = Integer.parseInt(scannerObj.nextLine());
            boolean isTought = false;
            if (yesOrNo == 1) {
                isTought = true;
            } else if (yesOrNo != 2) {
                System.out.println("Enter valid input");
                return null;
            }
            subjectToBeTought.put(subjects[i], isTought);
        }
        Student std = new Student(studentId, className, studentName, subjectToBeTought,
                0.0, 0, 0.0);

        return std;
    }

    private void addStudent() {

        try {
            System.out.println("Enter className-8/9/10");
            Integer className = Integer.parseInt(scannerObj.nextLine());
            Integer classIndex = StudentsController.getClassIndex(className);
            if (classIndex == -1) {
                System.out.println("Enter valid class");
                return;
            }

            Student newStudent = createStudent(className);
            if (newStudent != null) {
                StudentsController.addStudent(newStudent, classIndex);
                System.out.println("Student with ID:" + newStudent.getStudentId() +
                        " was added successfully from Class:" + newStudent.getClassName());
            }

        } catch (Exception e) {
            System.out.println("Enter valid Input");
        }
    }

    private void editStudent() {

        ArrayList<Student> studentsByClass = showStudentListByClassName();
        if (studentsByClass.size() > 0) {

            try {
//                System.out.println("Enter className(8/9/10) to edit a student");
//                Integer className = Integer.parseInt(scannerObj.nextLine());
                Integer className = studentsByClass.get(0).getClassName();
                Integer classIndex = StudentsController.getClassIndex(className);

                System.out.println("Enter Student Id");
                Integer studentId = Integer.parseInt(scannerObj.nextLine());


//                ArrayList<Student> studentsByClass = StudentsController.getStudentsListByClass(classIndex);

                Boolean isStdIdExist = new Boolean(false);
                for (Student std : studentsByClass) {
                    if (std.getStudentId() == studentId) {

                        System.out.println("Enter How Many days you taught " + std.getStudentName() + "?");
                        Integer dayTaught = Integer.parseInt(scannerObj.nextLine());

                        HashMap<String, Boolean> subjectTought = std.getSubjectToBeTought();

                        Double totalMarks = 0.0, totalEarning = 0.0;
                        Integer numberOfSubjectTought = 0;
                        for (Map.Entry mapElement : subjectTought.entrySet()) {
                            String subject = (String) mapElement.getKey();
                            boolean isTaught = (Boolean) mapElement.getValue();
                            if (isTaught == true) {
                                numberOfSubjectTought++;
                                totalEarning += (dayTaught * 1.0);
                                System.out.println("Enter marks got in " + subject + " by " + std.getStudentName() + "?");
                                Double marks = Double.parseDouble(scannerObj.nextLine());
                                totalMarks += marks;
                            }
                        }

                        std.setTotalEarning(totalEarning);
                        std.setAvgMarks(totalMarks / numberOfSubjectTought);
                        std.setTotalDayTought(dayTaught);

                        StudentsController.setTotalDayTaughtByClass(classIndex, dayTaught);
                        StudentsController.setTotalEarningByClass(classIndex, totalEarning);
                        StudentsController.setTotalMarkXmCountAllStd(numberOfSubjectTought, totalMarks);

                        isStdIdExist = true;

                        break;
                    }
                }
                if (isStdIdExist) {
                    System.out.println("Student with ID:" + studentId + " was edited successfully from Class:" + className);
                } else {
                    System.out.println("No student found for Edit with Student ID: " + studentId + "in Class:" + className);
                }
            } catch (Exception e) {
                System.out.println("Enter valid Input");
            }

        } else {
            System.out.println("There is no student in this Class.");
        }

    }

    private void deleteStudent() {

        ArrayList<Student> studentsByClass = showStudentListByClassName();
        if (studentsByClass.size() > 0) {
            try {
//                System.out.println("Enter className(8/9/10) to delete a Student");
//                Integer className = Integer.parseInt(scannerObj.nextLine());
                Integer className = studentsByClass.get(0).getClassName();
                Integer classIndex = StudentsController.getClassIndex(className);

                System.out.println("Enter Student Id");
                Integer studentId = Integer.parseInt(scannerObj.nextLine());

                Integer isDeleted = StudentsController.removeStudent(studentId, classIndex);

                if (isDeleted == 1) {
                    System.out.println("Student with ID:" + studentId + " was deleted successfully from Class:" + className);
                } else {
                    System.out.println("No student found for Edit with Student ID: " + studentId + "in Class:" + className);
                }
            } catch (Exception e) {
                System.out.println("Enter valid Input");
            }
        } else {
            System.out.println("There is no student in this Class.");
        }
    }

    private ArrayList<Student> showStudentListByClassName() {

        System.out.println("Enter a className(8/9/10) to see the List of the Students");
        Integer className = Integer.parseInt(scannerObj.nextLine());
        Integer classIndex = StudentsController.getClassIndex(className);

        if (classIndex == -1) {
            System.out.println("Enter valid class");
            return null;
        }
        ArrayList<Student> studentsByClass = StudentsController.getStudentsListByClass(classIndex);

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

            System.out.println("+---------------------------------------------------------+");
            System.out.println(formatter.format(idFormat + nameFormat + earningFormat + avgMarksFormat,
                    "Id", "Name", "Earning", "Avg-Marks"));
            for (Student s : studentsByClass) {
                formatter = new Formatter();
                System.out.println(formatter.format(idFormat + nameFormat + earningFormat + avgMarksFormat,
                        s.getStudentId(), s.getStudentName(), s.getTotalEarning(), s.getAvgMarks()));
            }
            System.out.println("+---------------------------------------------------------+");
        }
        return studentsByClass;
    }

    private Integer showStudentListBySpecificID(ArrayList<Student> studentsByClass) {
        System.out.println("Wanna see details any of the above student? Press 1-YES, 2-NO");
        Integer isWanted = Integer.parseInt(scannerObj.nextLine());

        Integer specificStudentId = new Integer(0);
        if (isWanted == 1) {

            System.out.println("Enter Student Id");
            specificStudentId = Integer.parseInt(scannerObj.nextLine());
            Integer specificClassName = new Integer(8);
            String specificStudentName = new String("");
            HashMap<String, Boolean> specificSubjectTought = new HashMap<>();
            double specificAvgMarks = new Double(0.0);
            Integer specificTotalDayTought = new Integer(0);
            Double specificTotalEarning = new Double(0.0);
            Boolean isExistStd = false;

            for (Student std : studentsByClass) {
                if (std.getStudentId() == specificStudentId) {
                    isExistStd = true;
                    specificClassName = std.getClassName();
                    specificStudentName = std.getStudentName();
                    specificSubjectTought = std.getSubjectToBeTought();
                    specificAvgMarks = std.getAvgMarks();
                    specificTotalDayTought = std.getTotalDayTought();
                    specificTotalEarning = std.getTotalEarning();
                }
            }
            if (isExistStd == true) {

                Formatter formatter = new Formatter();

                Integer idMaxLength = 7;
                String idFormat = "%-" + idMaxLength + "." + idMaxLength + "s";

                Integer nameMaxLength = 22;
                String nameFormat = "%-" + nameMaxLength + "." + nameMaxLength + "s";

                Integer classNameMaxLength = 6;
                String classNameFormat = "%-" + classNameMaxLength + "." + classNameMaxLength + "s";

                Integer subjectMaxLength = 15;
                String subjectFormat = "%-" + subjectMaxLength + "." + subjectMaxLength + "s";

                Integer avgMarksMaxLength = 15;
                String avgMarksFormat = "%-" + avgMarksMaxLength + "." + avgMarksMaxLength + "s";

                Integer dayTaughtMaxLength = 20;
                String dayTaughtFormat = "%-" + dayTaughtMaxLength + "." + dayTaughtMaxLength + "s";

                Integer earningMaxLength = 15;
                String earningFormat = "%-" + earningMaxLength + "." + earningMaxLength + "s";


                System.out.println("+--------------------------------------------------------------------------------------------------+");
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
                System.out.println("+--------------------------------------------------------------------------------------------------+");

                return -1; ///return -1 so that it can be identified that there is a student with that ID
            } else {
                return specificStudentId;  ///return the actual inputed ID to print transaction (we need the id to show)
            }
        }
        return 0; ///return 0 to identify that user not choose to see a specific student
    }

    private void showStudentList() {
        try {

            ArrayList<Student> studentsByClass = showStudentListByClassName();

            if (studentsByClass.size() > 0) {
                Integer specificStudentId = showStudentListBySpecificID(studentsByClass);
                if (specificStudentId != 0 && specificStudentId != -1) {
                    System.out.println("There is no student with Id-" + specificStudentId);
                }
            } else {
                System.out.println("There is no student in this class");
            }
        } catch (Exception e) {
            System.out.println("Enter valid Input");
        }
    }

    private Integer showDayTaughtByClass() {
        Integer dayTaughtClass8 = StudentsController.getTotalDayTaughtByClass(0);
        Integer dayTaughtClass9 = StudentsController.getTotalDayTaughtByClass(1);
        Integer dayTaughtClass10 = StudentsController.getTotalDayTaughtByClass(2);
        System.out.println("Class-8 has been taught for <" + dayTaughtClass8 + "> days");
        System.out.println("Class-9 has been taught for <" + dayTaughtClass9 + "> days");
        System.out.println("Class-10 has been taught for <" + dayTaughtClass10 + "> days");
        return dayTaughtClass8 + dayTaughtClass9 + dayTaughtClass10;
    }

    private void showDayTaughtAllClass(Integer dayTaughtAllClass) {
        System.out.println("All Classes has been taught for <" + dayTaughtAllClass + "> days");
    }

    private void showEarningByClass() {
        Integer earningFromClass8 = StudentsController.getTotalDayTaughtByClass(0);
        Integer earningFromClass9 = StudentsController.getTotalDayTaughtByClass(1);
        Integer earningFromClass10 = StudentsController.getTotalDayTaughtByClass(2);
        System.out.println("Earning from Class-8: " + earningFromClass8);
        System.out.println("Earning from Class-9: " + earningFromClass9);
        System.out.println("Earning from Class-10: " + earningFromClass10);
    }

    private void showAvgMarksOfAllStd() {
        double avgMarksOfAllStd = StudentsController.getAvgMarksAllStd();
        System.out.println("Average Marks of All Students: " + avgMarksOfAllStd);


    }

    public void showOverAllInfo() {
        Integer dayTaughtAllClass = showDayTaughtByClass();
        showDayTaughtAllClass(dayTaughtAllClass);
        showEarningByClass();
        showAvgMarksOfAllStd();
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

    private static void pressAnyKeyToMenu() {
        System.out.println("\nPress any key to go Main Menu...");
        try {
            System.in.read();
            scannerObj.nextLine();
            showMenuboard();
        } catch (Exception e) {
        }
    }

//    private Integer showStudentListByClassBeforeOperation() {
//        ArrayList<Student> studentsByClass = showStudentListByClassName();
//        return studentsByClass.size();
//    }

    public static void main(String[] args) {

        Main mainInstance = new Main();
        showMenuboard();

        Integer exit = new Integer(0);
        while (exit == 0) {

            try {
                Scanner scannerObj = new Scanner(System.in);
                System.out.println("Enter Your Choice");
                Integer choice = Integer.parseInt(scannerObj.nextLine());
                clearScreen();

                switch (choice) {
                    case 1:
                        mainInstance.addStudent();
                        break;
                    case 2:
                        mainInstance.editStudent();
                        break;
                    case 3:
                        mainInstance.deleteStudent();
                        break;
                    case 4:
                        mainInstance.showStudentList();
                        break;
                    case 5:
                        mainInstance.showOverAllInfo();
                        break;
                    case 6:
                        exit = 1;
                        break;
                    default:
                        break;
                }
                if (exit != 1) {
                    pressAnyKeyToMenu();
                }
            } catch (Exception e) {
                System.out.println("Enter valid Input");
            }
        }
    }
}
