import java.util.ArrayList;
import java.util.Scanner;

/**
 * CodeAlpha Internship - Task 1: Student Grade Tracker
 * Author: [Your Name]
 * Description: A console-based Java application to manage student grades,
 *              calculate statistics, and display a summary report.
 */
public class StudentGradeTracker {

    // ─── Inner class to represent a Student ───────────────────────────────────
    static class Student {
        private String name;
        private ArrayList<String> subjects;   // subject names
        private ArrayList<Double> grades;     // marks per subject (same index)

        public Student(String name) {
            this.name     = name;
            this.subjects = new ArrayList<>();
            this.grades   = new ArrayList<>();
        }

        /** Add a subject mark. Duplicate subject names are allowed (e.g. re-tests). */
        public void addGrade(String subject, double grade) {
            subjects.add(subject);
            grades.add(grade);
        }

        public double getAverage() {
            if (grades.isEmpty()) return 0;
            double sum = 0;
            for (double g : grades) sum += g;
            return sum / grades.size();   // total marks ÷ number of subjects
        }

        public double getHighest() {
            if (grades.isEmpty()) return 0;
            double max = grades.get(0);
            for (double g : grades) if (g > max) max = g;
            return max;
        }

        public double getLowest() {
            if (grades.isEmpty()) return 0;
            double min = grades.get(0);
            for (double g : grades) if (g < min) min = g;
            return min;
        }

        public String getLetterGrade() {
            double avg = getAverage();
            if (avg >= 90) return "A";
            else if (avg >= 80) return "B";
            else if (avg >= 70) return "C";
            else if (avg >= 60) return "D";
            else return "F";
        }

        public String getName()               { return name; }
        public ArrayList<Double> getGrades()  { return grades; }
        public ArrayList<String> getSubjects(){ return subjects; }
    }

    // ─── Helper: print a separator line ───────────────────────────────────────
    static void printLine() {
        System.out.println("═".repeat(60));
    }

    // ─── Display main menu ────────────────────────────────────────────────────
    static void printMenu() {
        System.out.println();
        System.out.println("     STUDENT GRADE TRACKER — MENU     ");
        System.out.println("  1. Add New Student                  ");
        System.out.println("  2. Add Grade to Student             ");
        System.out.println("  3. View Student Report              ");
        System.out.println("  4. View All Students Summary        ");
        System.out.println("  5. Class Statistics                 ");
        System.out.println("  6. Exit                             ");
        System.out.print("  Enter your choice: ");
    }

    // ─── Find student by name ─────────────────────────────────────────────────
    static Student findStudent(ArrayList<Student> students, String name) {
        for (Student s : students)
            if (s.getName().equalsIgnoreCase(name)) return s;
        return null;
    }

    // ─── Display a single student's detailed report ───────────────────────────
    static void printStudentReport(Student s) {
        printLine();
        System.out.println("  REPORT FOR: " + s.getName().toUpperCase());
        printLine();
        if (s.getGrades().isEmpty()) {
            System.out.println("  No marks entered yet.");
        } else {
            System.out.printf("  %-20s %-10s%n", "Subject", "Mark");
            System.out.println("  " + "─".repeat(30));
            for (int i = 0; i < s.getSubjects().size(); i++) {
                System.out.printf("  %-20s %.1f%n",
                        s.getSubjects().get(i), s.getGrades().get(i));
            }
            System.out.println("  " + "─".repeat(30));
            System.out.printf("  %-20s %.2f  ← Average Mark%n", "AVERAGE", s.getAverage());
            System.out.printf("  %-20s %.2f%n", "Highest Mark", s.getHighest());
            System.out.printf("  %-20s %.2f%n", "Lowest Mark",  s.getLowest());
            System.out.println("  Letter Grade  : " + s.getLetterGrade());
        }
        printLine();
    }

    // ─── Display summary table of all students ────────────────────────────────
    static void printAllStudents(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("  No students added yet.");
            return;
        }
        printLine();
        System.out.printf("  %-20s %-10s %-10s %-10s %-6s%n",
                "Name", "Average", "Highest", "Lowest", "Grade");
        printLine();
        for (Student s : students) {
            System.out.printf("  %-20s %-10.2f %-10.2f %-10.2f %-6s%n",
                    s.getName(), s.getAverage(), s.getHighest(),
                    s.getLowest(), s.getLetterGrade());
        }
        printLine();
    }

    // ─── Class-wide statistics ────────────────────────────────────────────────
    static void printClassStats(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("  No students to compute statistics for.");
            return;
        }
        double classTotal = 0;
        double classHigh  = Double.MIN_VALUE;
        double classLow   = Double.MAX_VALUE;
        String topStudent = "";
        String lowStudent = "";

        for (Student s : students) {
            double avg = s.getAverage();
            classTotal += avg;
            if (avg > classHigh) { classHigh = avg; topStudent = s.getName(); }
            if (avg < classLow)  { classLow  = avg; lowStudent = s.getName(); }
        }

        printLine();
        System.out.println("  CLASS STATISTICS");
        printLine();
        System.out.printf("  Total Students  : %d%n", students.size());
        System.out.printf("  Class Average   : %.2f%n", classTotal / students.size());
        System.out.printf("  Top Performer   : %s (%.2f)%n", topStudent, classHigh);
        System.out.printf("  Needs Support   : %s (%.2f)%n", lowStudent, classLow);
        printLine();
    }

    // ─── MAIN ─────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("\n  Welcome to the CodeAlpha Student Grade Tracker!");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": // Add student
                    System.out.print("  Enter student name: ");
                    String newName = sc.nextLine().trim();
                    if (newName.isEmpty()) {
                        System.out.println("  Name cannot be empty.");
                    } else if (findStudent(students, newName) != null) {
                        System.out.println("  Student already exists.");
                    } else {
                        students.add(new Student(newName));
                        System.out.println("  ✅ Student '" + newName + "' added.");
                    }
                    break;

                case "2": // Add subject marks
                    System.out.print("  Enter student name: ");
                    String gradeName = sc.nextLine().trim();
                    Student gradeStudent = findStudent(students, gradeName);
                    if (gradeStudent == null) {
                        System.out.println("  Student not found.");
                    } else {
                        System.out.print("  How many subjects? ");
                        try {
                            int numSubjects = Integer.parseInt(sc.nextLine().trim());
                            if (numSubjects <= 0) {
                                System.out.println("  Number must be greater than 0.");
                                break;
                            }
                            for (int i = 1; i <= numSubjects; i++) {
                                System.out.print("  Subject " + i + " name : ");
                                String subjectName = sc.nextLine().trim();
                                if (subjectName.isEmpty()) subjectName = "Subject " + i;

                                System.out.print("  Mark for " + subjectName + " (0–100): ");
                                try {
                                    double mark = Double.parseDouble(sc.nextLine().trim());
                                    if (mark < 0 || mark > 100) {
                                        System.out.println("  ⚠ Mark must be 0–100. Skipping this subject.");
                                    } else {
                                        gradeStudent.addGrade(subjectName, mark);
                                        System.out.println("  ✅ Added: " + subjectName + " → " + mark);
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("  Invalid mark. Skipping this subject.");
                                }
                            }
                            // Show average immediately after all subjects entered
                            System.out.println();
                            System.out.printf("  📊 Average mark for %s = %.2f (%s)%n",
                                    gradeStudent.getName(),
                                    gradeStudent.getAverage(),
                                    gradeStudent.getLetterGrade());
                        } catch (NumberFormatException e) {
                            System.out.println("  Invalid number.");
                        }
                    }
                    break;

                case "3": // View single student
                    System.out.print("  Enter student name: ");
                    String reportName = sc.nextLine().trim();
                    Student reportStudent = findStudent(students, reportName);
                    if (reportStudent == null) {
                        System.out.println("  Student not found.");
                    } else {
                        printStudentReport(reportStudent);
                    }
                    break;

                case "4": // View all students
                    printAllStudents(students);
                    break;

                case "5": // Class statistics
                    printClassStats(students);
                    break;

                case "6": // Exit
                    System.out.println("\n  Thank you for using Student Grade Tracker. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("  Invalid choice. Please select 1–6.");
            }
        }
        sc.close();
    }
}
