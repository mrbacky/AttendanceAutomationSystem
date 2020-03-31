/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.dal.Mock;

import attendance.be.Course;
import java.util.List;
import javafx.collections.FXCollections;

/**
 *
 * @author mac
 */
public class MockCourseDAO {
    
     private List<Course> courseList = FXCollections.observableArrayList();
     
      public MockCourseDAO() {
        
         createCourses();
       
        System.out.println("Courses:" + courseList);
    }

    public List<Course> getCourses() {
        return courseList;
    }

   

   

   private void createCourses() {
      courseList.add(new Course(2, "SCO"));
      courseList.add(new Course(5, "SDE"));

  }

     
    
}
