package model;

/**
 *
 * @author Dell
 */
public class CourseOfStudent {

    private int id;
    private int semester;
    private String courseName;

    public CourseOfStudent(int id, int semester, String courseName) {
        this.id = id;
        this.semester = semester;
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
