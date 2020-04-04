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
    private List<Student> students;

    public LogicManager() {
        dalFacade = new DalManager();
        calculator = new AbsencePercentageCalculator();
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
    public List<Student> calculateAbsencePercentage(Course course, LocalDateTime current) {
        int conductedLesson = dalFacade.getNumberOfConductedLessons(course, current);

        students = dalFacade.getNumberOfAbsentLessons(course);

        for (Student s : students) {
            s.setAbsencePercentage(calculator.calculatePercentage(s.getAbsenceCount(), conductedLesson));
            System.out.println("#: " + s.getAbsenceCount());
            System.out.println("%: " + s.getAbsencePercentage());
            System.out.println("!: " + conductedLesson);
        }
        return students;
    }

    @Override
    public int studentsEnrolledInCourse() {
        //Make sure the method above is called first, otherwise the list will be empty.
        System.out.println(students.size());
        return students.size();
    }
}
