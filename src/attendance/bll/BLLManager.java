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
    public List<Course> getCourses(int userId) {
        return dalFacade.getCourses(userId);
    }

    @Override
    public List<Lesson> getLessonsForToday(int userId, LocalDate current) {
        return dalFacade.getLessonsForToday(userId, current);
    }

    @Override
    public void createRecord(int userId, Lesson lessonToUpdate) {
        dalFacade.createRecord(userId, lessonToUpdate);
    }

    @Override
    public List<Lesson> getAttendanceRecordsForAllCourses(int userId) {
        return dalFacade.getAttendanceRecordsForAllCourses(userId);
    }

    @Override
    public List<Lesson> getAttendanceRecordsForACourse(int userId, int courseId) {
        return dalFacade.getAttendanceRecordsForACourse(userId, courseId);
    }

}