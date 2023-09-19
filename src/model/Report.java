package model;

/**
 *
 * @author Dell
 */
public class Report {

    private int id;
    private String courseName;
    private int totalCourse;

    public Report() {
    }

    public Report(int id, String courseName, int totalCourse) {
        this.id = id;
        this.courseName = courseName;
        this.totalCourse = totalCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTotalCourse() {
        return totalCourse;
    }

    public void setTotalCourse(int totalCourse) {
        this.totalCourse = totalCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
