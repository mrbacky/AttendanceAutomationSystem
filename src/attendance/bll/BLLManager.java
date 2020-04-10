package attendance.bll;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.User;
import attendance.bll.util.LogicException;
import attendance.dal.DalException;
import attendance.dal.IDALFacade;
import java.time.LocalDate;
import java.util.List;

public class BLLManager implements IBLLFacade {

    private final IDALFacade dalFacade;

    public BLLManager(IDALFacade dalFacade) {
        this.dalFacade = dalFacade;
    }

    @Override
    public User getUser(String username, String password) throws LogicException {
        try {
            //hash password here. create a tool in a utility folder and call method from there.
            return dalFacade.getUser(username, password);
        } catch (DalException ex) {
            throw new LogicException(ex.getMessage());
        }
    }

    @Override
    public List<Course> getCourses(User user) {
        return dalFacade.getCourses(user);
    }

    @Override
    public List<Lesson> getLessonsForToday(User student, LocalDate current) {
        return dalFacade.getLessonsForToday(student, current);
    }

    @Override
    public void createRecord(User student, Lesson lessonToUpdate) {
        dalFacade.createRecord(student, lessonToUpdate);
    }

    @Override
    public List<Lesson> getAttendanceRecordsForAllCourses(User student) {
        return dalFacade.getAttendanceRecordsForAllCourses(student);
    }

    @Override
    public List<Lesson> getAttendanceRecordsForACourse(User student, Course course) {
        return dalFacade.getAttendanceRecordsForACourse(student, course);
    }

}