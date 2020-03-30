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

    public List<CourseCal> getCourseCals() {//9:00 - 11:30
        return courseCalList;
    }

    private void createCourseCalObjects() {
        courseCalList.add(new CourseCal(0, "SDE", LocalDateTime.now().with(LocalTime.of(9, 0)), LocalDateTime.now().with(LocalTime.of(11, 30)), CourseCal.StatusType.UNREGISTERED));
        courseCalList.add(new CourseCal(0, "SCO", LocalDateTime.now().with(LocalTime.of(12, 0)), LocalDateTime.now().with(LocalTime.of(13, 30)), CourseCal.StatusType.UNREGISTERED));
        courseCalList.add(new CourseCal(0, "ITO", LocalDateTime.now().with(LocalTime.of(14, 0)), LocalDateTime.now().with(LocalTime.of(15, 30)), CourseCal.StatusType.ABSENT));

    }

}
