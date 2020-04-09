package attendance.bll;

import attendance.be.Course;
import attendance.be.Student;

/**
 *
 * @author annem
 */
public class ObserverEvent {

    Course course;
    Student student;

    public ObserverEvent(Course course) {
        this.course = course;
    }

    public ObserverEvent(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
