package attendance.dal.Mock;

import attendance.be.Course;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;

/**
 *
 * @author rado
 */
public class MockCourseDAO {

    private List<Course> courseList = FXCollections.observableArrayList();

    public MockCourseDAO() {
        createCourses();
        System.out.println("coursesss in mock classss    " + courseList);
    }

    public List<Course> getCourses() {
        return courseList;
    }

    private void createCourses() {
        courseList.add(new Course("SDR2.B.20", LocalDate.EPOCH, LocalDate.EPOCH));
        courseList.add(new Course("SCO2.B.20", LocalDate.EPOCH, LocalDate.EPOCH));
        courseList.add(new Course("ITO2.B.20", LocalDate.EPOCH, LocalDate.EPOCH));
        courseList.add(new Course("DBOS.AB.20", LocalDate.EPOCH, LocalDate.EPOCH));

    }

}
