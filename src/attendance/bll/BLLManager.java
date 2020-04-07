package attendance.bll;

import attendance.bll.util.LogicException;
import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import attendance.bll.util.AbsencePercentageCalculator;
import attendance.dal.DalException;
import attendance.dal.DALManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import attendance.dal.IDALFacade;

public class BLLManager implements IBLLFacade {

    private final IDALFacade dalFacade;
    private final AbsencePercentageCalculator calculator;
    private List<Student> students;

    public BLLManager(IDALFacade dalFacade) {
        this.dalFacade = dalFacade;
        System.out.println("dalfacade obj: " + dalFacade);
        calculator = new AbsencePercentageCalculator();
    }

    @Override
    public User getUser(String username, String password) throws LogicException {
        try {
            User newUser = dalFacade.getUser(username, password);
            System.out.println("user in BLLManager: " + newUser.getName() + newUser.getId() + newUser);
            //hash password here. create a tool in a utility folder and call method from there.
            return newUser;
        } catch (DalException ex) {
            throw new LogicException(ex.getMessage() + "this ?");
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

    @Override
    public List<Lesson> getAttendanceRecordsForAllCourses(int userId) {
        return dalFacade.getAttendanceRecordsForAllCourses(userId);
    }

    @Override
    public List<Lesson> getAttendanceRecordsForACourse(int userId, int courseId) {
        return dalFacade.getAttendanceRecordsForACourse(userId, courseId);
    }
}
