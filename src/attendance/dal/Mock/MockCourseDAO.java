package attendance.dal.Mock;

import attendance.be.Course;
import attendance.be.Student;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author rado
 */
public class MockCourseDAO {

//    private final ListProperty<Student> scoStudents = new SimpleListProperty<>();
//    private final ListProperty<Student> sdeStudents = new SimpleListProperty<>();

//    public ObservableList getSdeStudents() {
//        return sdeStudents.get();
//    }
//
//    public void setSdeStudents(ObservableList value) {
//        sdeStudents.set(value);
//    }
//
//    public ListProperty sdeStudentsProperty() {
//        return sdeStudents;
//    }
//
//    public ObservableList getScoStudents() {
//        return scoStudents.get();
//    }
//
//    public void setScoStudents(ObservableList value) {
//        scoStudents.set(value);
//    }
//
//    public ListProperty scoStudentsProperty() {
//        return scoStudents;
//    }

    private List<Student> scoStudents = FXCollections.observableArrayList();
    private List<Student> sdeStudents = FXCollections.observableArrayList();
    private List<Course> courseList = FXCollections.observableArrayList();

    public MockCourseDAO() {
//        createCourses();
        createSCOstudents();
        createSDEstudents();
        System.out.println("coursesss in mock classss    " + courseList);
    }

    public List<Course> getCourses() {
        return courseList;
    }

    void createSCOstudents() {
        scoStudents.add(new Student(88, "Rado", 15));
        scoStudents.add(new Student(88, "John", 3));
        scoStudents.add(new Student(88, "Mike", 99));

    }

    void createSDEstudents() {
        scoStudents.add(new Student(88, "Rado", 15));
        scoStudents.add(new Student(88, "Kim", 3));
        scoStudents.add(new Student(88, "Kanye", 99));
        scoStudents.add(new Student(88, "Tvoj Tatko", 99));

    }

//    private void createCourses() {
//        courseList.add(new Course(2, "SCO", scoStudents));
//        courseList.add(new Course(5, "SDE", sdeStudents));
//
//    }

}
