package attendance.dal.Mock;

import attendance.be.CourseCal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author rado
 */
public class MockCourseCalDAO {

    private List<CourseCal> courseCalList = FXCollections.observableArrayList();

    public MockCourseCalDAO() {
        createCourseCalObjects();

    }

    public List<CourseCal> getCourseCals() {
        return courseCalList;
    }

    private void createCourseCalObjects() {
        courseCalList.add(new CourseCal(14, "SDE", "9:00", "11:30"));
        courseCalList.add(new CourseCal(14, "SCO", "12:00", "13:30"));
        

    }

}
