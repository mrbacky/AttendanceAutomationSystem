package attendance.bll;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import attendance.bll.util.AbsencePercentageCalculator;
import attendance.dal.DalException;
import attendance.dal.DalFacade;
import attendance.dal.DalManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class LogicManager implements LogicFacade {

    private final DalFacade dalFacade;
    private final AbsencePercentageCalculator calculator;

    public LogicManager() {
        dalFacade = new DalManager();
        calculator = new AbsencePercentageCalculator();
    }

    public User auth(String insertedUsername, String password) {
        return dalFacade.auth(insertedUsername, password);
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
    public void markAttendance(User currentUser, String currentTask) {
        LocalTime loc = java.time.LocalTime.now();
        dalFacade.markAttendance(currentUser, currentTask, loc);
    }

    @Override
    public List<Student> getAbsentStudents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public List<Student> calculateAbsencePercentage(int courseId, LocalDateTime current) {
        int conductedLesson = dalFacade.getNumberOfConductedLessons(courseId, current);

        List<Student> students = dalFacade.getNumberOfAbsentLessons(courseId);

        for (Student s : students) {
            s.setAbsence(calculator.calculatePercentage(s.getAbsence(), conductedLesson));
        }
        return students;
    }
}
