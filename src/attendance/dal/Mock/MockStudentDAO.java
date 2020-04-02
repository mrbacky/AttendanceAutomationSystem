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
        allStudents.add(new Student(1, "Radoslav Backovsky", 84,4));
        allStudents.add(new Student(2, "Anne Luong", 34,14));
        allStudents.add(new Student(2, "Louise Lauenborg", 58,6));
        allStudents.add(new Student(2, "Martin Emil Wøbbe", 67,8));
        allStudents.add(new Student(2, "Martin Houmark", 90,6));
        allStudents.add(new Student(2, "Dmitri Pankov", 25,6));
        allStudents.add(new Student(2, "Rocío Tapia López", 90,7));
        allStudents.add(new Student(2, "Tienesh Kanagarajsen", 90,6));
        allStudents.add(new Student(2, "Nadia Miteva", 90,3));
        allStudents.add(new Student(2, "Mario Ampudia", 3,18));
        allStudents.add(new Student(2, "Christian Hansen", 90,2));
        allStudents.add(new Student(2, "Armand Németh", 90,2));
        allStudents.add(new Student(2, "Filip Wojciechowski", 90,2));
        allStudents.add(new Student(2, "Michael Pedersen", 40,6));
        allStudents.add(new Student(2, "Abdiqafar Abas", 63,5));

    }
    
    public List<Student> getStudents() {
        return allStudents;
    }

}
