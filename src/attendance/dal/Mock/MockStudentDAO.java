package attendance.dal.Mock;

import attendance.be.Student;
import java.util.ArrayList;
import java.util.List;

public final class MockStudentDAO {

    List<Student> allStudents = new ArrayList<>();

    public MockStudentDAO() {
        createAbsentStudents();
        
    }

    

    public void createAbsentStudents() {
        allStudents.add(new Student(1, "Radoslav Backovsky", 24.7));
        allStudents.add(new Student(2, "Anne Luong", 34.5));
        allStudents.add(new Student(2, "Louise Lauenborg", 58));
        allStudents.add(new Student(2, "Martin Emil Wøbbe", 67.01));
        allStudents.add(new Student(2, "Martin Houmark", 90));
        allStudents.add(new Student(2, "Dmitri Pankov", 25.7));
        allStudents.add(new Student(2, "Rocío Tapia López", 90));
        allStudents.add(new Student(2, "Tienesh Kanagarajsen", 90));
        allStudents.add(new Student(2, "Nadia Miteva", 90));
        allStudents.add(new Student(2, "Mario Ampudia", 3.75));
        allStudents.add(new Student(2, "Christian Hansen", 90));
        allStudents.add(new Student(2, "Armand Németh", 90));
        allStudents.add(new Student(2, "Filip Wojciechowski", 90));
        allStudents.add(new Student(2, "Michael Pedersen", 40));
        allStudents.add(new Student(2, "Abdiqafar Abas", 63.7));

    }
    
    public List<Student> getStudents() {
        return allStudents;
    }

}
