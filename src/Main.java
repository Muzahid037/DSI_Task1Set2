import java.io.*;
import java.util.*;

public class Main {
    static StudentsController StudentsController;
    private static String FileName = "StudentsDetails.txt";
    private static final Scanner scannerObj = new Scanner(System.in);

    public Main() throws IOException, ClassNotFoundException {
        {
            StudentsController = StudentsController.getInstance();

            File f = new File(FileName);
            if(f.exists() && !f.isDirectory()) {
                readFile();
            }
        }
    }

    private void readFile() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(FileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        StudentsController = (StudentsController) ois.readObject();
        fin.close();
    }

    private void writeFile(){
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(FileName);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(StudentsController);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
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

    private void showAvgMarksOfAllStd() {
        Double avgMarksOfAllStd = StudentsController.getAvgMarksAllStd();
        System.out.println("Average Marks of All Students: " + avgMarksOfAllStd);
    }

    private void showEarningByClass() {
        Double earningFromClass8 = StudentsController.getTotalEarningByClass(0);
        Double earningFromClass9 = StudentsController.getTotalEarningByClass(1);
        Double earningFromClass10 = StudentsController.getTotalEarningByClass(2);
        System.out.println("Earning from Class-8 g: " + earningFromClass8);
        System.out.println("Earning from Class-9: " + earningFromClass9);
        System.out.println("Earning from Class-10: " + earningFromClass10);
    }

    private void showDayTaughtAllClass(Integer dayTaughtAllClass) {
        System.out.println("All Classes has been taught for <" + dayTaughtAllClass + "> days");
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

    public void showOverAllInfo() {
        System.out.println("+------------Overall information------------+");
        Integer dayTaughtAllClass = showDayTaughtByClass();
        showDayTaughtAllClass(dayTaughtAllClass);
        showEarningByClass();
        showAvgMarksOfAllStd();
        System.out.println("+-------------------------------------------+");
    }

    private Integer showStudentListBySpecificID(ArrayList<Student> studentsByClass, Integer specificStudentId) {

        Integer specificClassName = new Integer(8);
        String specificStudentName = new String("");
        HashMap<String, Boolean> specificSubjectTought = new HashMap<>();
        Double specificAvgMarks = new Double(0.0);
        Integer specificTotalDayTought = new Integer(0);
        Double specificTotalEarning = new Double(0.0);
        Boolean isExistStd = false;

        for (Student std : studentsByClass) {
//            System.out.println("specificStudentId:"+specificStudentId+" std.getStudentId():"+std.getStudentId());
            if (std.getStudentId().equals(specificStudentId)) {
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
            //------------------------For Showing data in rowxcolumn-------------------------//
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
            return 1;
        }
        return 0;
    }

    private ArrayList<Student> showStudentListByClassName() {

        System.out.println("Enter a className(8/9/10) to see the List of the Students");
        Integer className = Integer.parseInt(scannerObj.nextLine());
        Integer classIndex = StudentsController.getClassIndex(className);

        if (classIndex == -1) {
            return null;
        }
        ArrayList<Student> studentsByClass = StudentsController.getStudentsListByClass(classIndex);
        if (studentsByClass.size() > 0) {
            //------------------------For Showing data in rowxcolumn-------------------------//
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


    private void showStudentList() {
        try {
            ArrayList<Student> studentsByClass = showStudentListByClassName();
            if (studentsByClass == null) {
                System.out.println("Enter valid class");
            } else if (studentsByClass.size() > 0) {
                System.out.println("Wanna see details any of the above student? Press 1-YES, 2-NO");
                Integer isWanted = Integer.parseInt(scannerObj.nextLine());
                if (isWanted == 1) {
                    System.out.println("Enter Student Id");
                    Integer specificStudentId = Integer.parseInt(scannerObj.nextLine());
                    Integer isExistStd = showStudentListBySpecificID(studentsByClass, specificStudentId);
                    if (isExistStd != 1) {
                        System.out.println("There is no student with Id-" + specificStudentId);
                    }
                }
            } else {
                System.out.println("There is no student in this class");
            }
        } catch (Exception e) {
            System.out.println("Enter valid Input");
        }
    }

    private void deleteStudent() {
        try {
            ArrayList<Student> studentsByClass = showStudentListByClassName();
            if (studentsByClass == null) {
                System.out.println("Enter valid class");
            } else if (studentsByClass.size() > 0) {
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
            } else {
                System.out.println("There is no student in this Class.");
            }
        } catch (Exception e) {
            System.out.println("Enter valid Input");
        }
    }

    private void editStudent() {

        try {
            ArrayList<Student> studentsByClass = showStudentListByClassName();
            if (studentsByClass == null) {
                System.out.println("Enter valid class");
            } else if (studentsByClass.size() > 0) {
                Integer className = studentsByClass.get(0).getClassName();
                Integer classIndex = StudentsController.getClassIndex(className);

                System.out.println("Enter Student Id");
                Integer studentId = Integer.parseInt(scannerObj.nextLine());

                Boolean isStdIdExist = new Boolean(false);

                for (Student std : studentsByClass) {
                    if (std.getStudentId().equals(studentId)) {

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
            } else {
                System.out.println("There is no student in this Class.");
            }
        } catch (Exception e) {
            System.out.println("Enter valid Input");
        }
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
                        " was added successfully in Class:" + newStudent.getClassName());
            }
        } catch (Exception e) {
            System.out.println("Enter valid Input");
        }
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


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Main mainInstance = new Main();
        showMenuboard();
        Integer exit = new Integer(0);

        while (exit == 0) {
            try {
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

        mainInstance.writeFile();

    }
}
