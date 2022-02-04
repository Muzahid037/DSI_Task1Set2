import java.util.*;


public class Main {
    StudentsDetails studentsDetails;

    public Main() {
        {
            studentsDetails = studentsDetails.getInstance();
        }
    }

    private void showDashboard() {
        System.out.println("----------------------------------------------\n");
        System.out.println("Press 1-Add a Student");
        System.out.println("Press 2-Edit a Student");
        System.out.println("Press 3-Delete a Student");
        System.out.println("Press 4-See the list of students individually");
        System.out.println("Press 5-See overall info");
        System.out.println("Press 6-Exit");
        System.out.println("\n----------------------------------------------");
    }

    private void addStudent() {

        Scanner scannerObj = new Scanner(System.in);

        System.out.println("Enter className-8/9/10");
        Integer className = Integer.parseInt(scannerObj.nextLine());
        Integer classIndex = studentsDetails.getClassIndex(className);
        if (classIndex == -1) {
            System.out.println("Enter valid class");
            return;
        }

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
            }
            subjectToBeTought.put(subjects[i], isTought);
        }
        Student std = new Student(studentId, className, studentName, subjectToBeTought,
                0.0, 0, 0.0);

        studentsDetails.addStudent(std, classIndex);
        System.out.println("Student Added Successfully");
    }

    private void editStudent() {
        Scanner scannerObj = new Scanner(System.in);

        System.out.println("Enter className-8/9/10");
        Integer className = Integer.parseInt(scannerObj.nextLine());
        Integer classIndex = studentsDetails.getClassIndex(className);
        if (classIndex == -1) {
            System.out.println("Enter valid class");
            return;
        }

        System.out.println("Enter Student Id");
        Integer studentId = Integer.parseInt(scannerObj.nextLine());


        ArrayList<Student> studentsByClass = studentsDetails.getStudentsList(classIndex);

        for (Student std : studentsByClass) {
            if (std.studentId == studentId) {

                System.out.println("Enter How Many days you taught " + std.studentName + "?");
                Integer dayTaught = Integer.parseInt(scannerObj.nextLine());

                HashMap<String, Boolean> subjectTought = std.subjectToBeTought;

                Double totalMarks = 0.0, totalEarning = 0.0;
                Integer numberOfSubjectTought = 0;
                for (Map.Entry mapElement : subjectTought.entrySet()) {
                    String subject = (String) mapElement.getKey();
                    boolean isTaught = (Boolean) mapElement.getValue();
                    if (isTaught == true) {
                        numberOfSubjectTought++;
                        totalEarning += (dayTaught * 1.0);
                        System.out.println("Enter marks got in " + subject + " by " + std.studentName + "?");
                        Double marks = Double.parseDouble(scannerObj.nextLine());
                        totalMarks += marks;
                    }
                }

                std.totalEarning = totalEarning;
                std.avgMarks = (totalMarks / numberOfSubjectTought);
                std.totalDayTought += dayTaught;

                studentsDetails.setTotalDayTaughtByClass(classIndex, dayTaught);
                studentsDetails.setTotalEarningByClass(classIndex, totalEarning);
                studentsDetails.setTotalMarkXmCountAllStd(numberOfSubjectTought, totalMarks);
            }
        }

    }

    private void deleteStudent() {
        Scanner scannerObj = new Scanner(System.in);

        System.out.println("Enter className-8/9/10");
        Integer className = Integer.parseInt(scannerObj.nextLine());
        Integer classIndex = studentsDetails.getClassIndex(className);
        if (classIndex == -1) {
            System.out.println("Enter valid class");
            return;
        }
        System.out.println("Enter Student Id");
        Integer studentId = Integer.parseInt(scannerObj.nextLine());

        Integer isDeleted = studentsDetails.removeStudent(studentId, classIndex);
        if (isDeleted ==1) {
            System.out.println("The Student was deleted successfully!");
        } else {
            System.out.println("There is no student with id: " + studentId);
        }
    }

    private void showStudentList() {
        Scanner scannerObj = new Scanner(System.in);

        System.out.println("Enter className-8/9/10");
        Integer className = Integer.parseInt(scannerObj.nextLine());
        Integer classIndex = studentsDetails.getClassIndex(className);
        if (classIndex == -1) {
            System.out.println("Enter valid class");
            return;
        }

        ArrayList<Student> studentsByClass = studentsDetails.getStudentsList(classIndex);

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

    private void showDayTaughtByClass() {
        Integer dayTaughtClass8 = studentsDetails.getTotalDayTaughtByClass(0);
        Integer dayTaughtClass9 = studentsDetails.getTotalDayTaughtByClass(1);
        Integer dayTaughtClass10 = studentsDetails.getTotalDayTaughtByClass(2);
        System.out.println("Class-8 has been taught for <" + dayTaughtClass8 + "> days");
        System.out.println("Class-9 has been taught for <" + dayTaughtClass9 + "> days");
        System.out.println("Class-10 has been taught for <" + dayTaughtClass10 + "> days");
    }

    private void showDayTaughtAllClass() {
        Integer dayTaughtClass8 = studentsDetails.getTotalDayTaughtByClass(0);
        Integer dayTaughtClass9 = studentsDetails.getTotalDayTaughtByClass(1);
        Integer dayTaughtClass10 = studentsDetails.getTotalDayTaughtByClass(2);
        Integer dayTaughtAllClass = dayTaughtClass8 + dayTaughtClass9 + dayTaughtClass10;
        System.out.println("All Classes has been taught for <" + dayTaughtAllClass + "> days");
    }

    private void showEarningByClass() {
        Integer earningFromClass8 = studentsDetails.getTotalDayTaughtByClass(0);
        Integer earningFromClass9 = studentsDetails.getTotalDayTaughtByClass(1);
        Integer earningFromClass10 = studentsDetails.getTotalDayTaughtByClass(2);
        System.out.println("Earning from Class-8: " + earningFromClass8);
        System.out.println("Earning from Class-9: " + earningFromClass9);
        System.out.println("Earning from Class-10: " + earningFromClass10);
    }

    private void showAvgMarksOfAllStd() {
        Double avgMarksOfAllStd = studentsDetails.getAvgMarksAllStd();
        System.out.println("Average Marks of All Students: " + avgMarksOfAllStd);
    }

    public void showOverAllInfo() {
        showDayTaughtByClass();
        showDayTaughtAllClass();
        showEarningByClass();
        showAvgMarksOfAllStd();
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
                    System.out.println("3"); ///dlt
                    mainInstance.deleteStudent();
                    break;
                case 4:
//                    System.out.println("4"); ///std list
                    mainInstance.showStudentList();
                    break;
                case 5:
//                    System.out.println("show ovr all info");
                    mainInstance.showOverAllInfo();
                    break;

                case 6:
                    exit = 1;
                    break;

                default:
                    break;
            }
        }

    }
}
