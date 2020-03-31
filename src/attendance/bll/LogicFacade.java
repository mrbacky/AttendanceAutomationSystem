package attendance.bll;

import attendance.be.Course;
import attendance.be.Student;
import attendance.be.User;
import java.time.LocalDate;
import java.util.List;

public interface LogicFacade {

    User auth(String insertedUsername, String password);

    User getUser(String username, String password);

    public void markAttendance(User currentUser, String currentTask);
    
    List<Student> getAbsentStudents();
    
    double calculateAbsence(Course selectedCourse ,int lessonsAttended, int lessonsToDate, LocalDate currentDay);
    
    public List<Course> getCourses(int userId);
    

}
