package controller;

import common.Library;
import java.util.ArrayList;
import java.util.Collections;
import model.CourseOfStudent;
import model.Report;
import model.Student;
import view.Menu;
import view.Validation;

/**
 *
 * @author Dell
 */
public class StudentManagement extends Menu {

    static String[] mc = {"Create", "Find and Sort", "Update/Delete", "Report", "Exit"};
    Library l;
    ArrayList<Student> studentList;
    ArrayList<CourseOfStudent> courseOfStudentslist;
    Student student;

    public StudentManagement() {
        super("PROGRAMMING", mc);
        l = new Library();
        studentList = new ArrayList<>();
        courseOfStudentslist = new ArrayList<>();
        student = new Student();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                createStudent();
                break;
            case 2:
                findSort();
                break;
            case 3:
                updateDelete();
                break;
            case 4:
                report();
                break;
            case 5:
                System.exit(0);
        }
    }

    public void report() {
        if (studentList == null) {
            System.err.println("List empty");
            return;
        }
        ArrayList<Report> list_Rp = new ArrayList<>();
        for (CourseOfStudent cs : courseOfStudentslist) {
            int total = 0;
            int id = cs.getId();
            String courseName = cs.getCourseName();
            if (checkReport(list_Rp, id, courseName, total)) {
                list_Rp.add(new Report(id, courseName, total + 1));
            } else {
                for (Report r : list_Rp) {
                    if (cs.getId() == r.getId() && r.getCourseName().equalsIgnoreCase(courseName)) {
                        total++;
                        r.setId(id);
                        r.setCourseName(courseName);
                        r.setTotalCourse(total + 1);
                    }

                }
            }
        }
        for (int i = 0; i < list_Rp.size(); i++) {
            System.out.println("Id:" + list_Rp.get(i).getId() + "  - Course: " + list_Rp.get(i).getCourseName() + " - Total: " + list_Rp.get(i).getTotalCourse());
        }
    }

    public void createStudent() {
        String name;
        int id = l.getInt("Enter Student Id", 1, 1000);
        if (!checkID(studentList, id)) {
            name = l.getString("Enter Student name: ");
            studentList.add(new Student(id, name));
        }
        int semester = l.getInt("Enter semester", 1, 10);
        String courseName = l.getString("Enter courseName: ");
        courseOfStudentslist.add(new CourseOfStudent(id, semester, courseName));
    }

    public void displayStudent(ArrayList<Student> list_s) {
        for (Student s : list_s) {
            System.out.println("Id: " + s.getId() + " - Name: " + s.getName());
            for (CourseOfStudent cs : courseOfStudentslist) {
                if (s.getId() == cs.getId()) {
                    System.out.println("Semester: " + cs.getSemester() + " - courseName: " + cs.getCourseName());
                }
            }
        }
    }

    public void findSort() {
        if (studentList == null) {
            System.err.println("List empty");
            return;
        }
        ArrayList<Student> list_ByName = listByName(studentList);
        if (list_ByName.isEmpty()) {
            System.err.println("Not exist");
        } else {
            Collections.sort(list_ByName);
            displayStudent(list_ByName);
        }
    }

    public ArrayList<Student> listByName(ArrayList<Student> list_s) {
        ArrayList<Student> list_Found = new ArrayList<>();
        String name = l.getString("Enter name to search: ");
        for (Student student : list_s) {
            if (student.getName().contains(name)) {
                list_Found.add(student);
            }
        }
        return list_Found;
    }

    public void updateDelete() {
        if (studentList == null) {
            System.err.println("List empty");
            return;
        }

        int id = l.getInt("Enter id to search", 1, 1000);
        ArrayList<Student> list_ById = listById(studentList, id);
        ArrayList<CourseOfStudent> list_ById_cs = listByIdCS(courseOfStudentslist, id);

        if (list_ById.isEmpty() || list_ById_cs.isEmpty()) {
            System.err.println("Not exist");
        } else {
            System.out.println("Do you want to update or delete?");
            System.out.println("1. Update");
            System.out.println("2. Delete");
            Student s = list_ById.get(0);
            CourseOfStudent cs = list_ById_cs.get(0);
            int c = l.getInt("Enter choice: ", 1, 2);
            switch (c) {
                case 1:
                    //Update
                    s.setId(id);
                    s.setName(l.getString("Enter name: "));
                    cs.setId(id);
                    cs.setSemester(l.getInt("Enter Semester ", 1, 10));
                    cs.setCourseName(l.getString("Enter Course name: "));
                    System.out.println("Update Succcess");
                    break;
                case 2:
                    //Remove
                    courseOfStudentslist.remove(cs);
                    studentList.remove(s);
                    System.out.println("Delete success");
                    break;
                default:
            }
        }
    }

    public ArrayList<CourseOfStudent> listByIdCS(ArrayList<CourseOfStudent> list_s, int id) {
        ArrayList<CourseOfStudent> list_Found = new ArrayList<>();

        for (CourseOfStudent Student : list_s) {
            if (Student.getId() == id) {
                list_Found.add(Student);
            }
        }
        return list_Found;
    }

    public ArrayList<Student> listById(ArrayList<Student> list_s, int id) {
        ArrayList<Student> list_Found = new ArrayList<>();

        for (Student Student : list_s) {
            if (Student.getId() == id) {
                list_Found.add(Student);
            }
        }
        return list_Found;
    }

    public Student GetById(int id) {
        for (Student st : studentList) {
            if (st.getId() == id) {
                return st;
            }
        }
        return null;
    }

    public boolean checkReport(ArrayList<Report> list_Rp, int id, String courseName, int total) {
        for (Report rp : list_Rp) {
            if (id == rp.getId() && courseName.equalsIgnoreCase(rp.getCourseName()) && total == rp.getTotalCourse()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkID(ArrayList<Student> list, int id) {
        if (list.isEmpty()) {
            return false;
        } else {
            for (Student s : studentList) {
                if (s.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }
}
