/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.model.interfaces;

import attendance.be.Course;
import attendance.be.User;
import javafx.collections.ObservableList;

/**
 *
 * @author rado
 */
public interface ICourseModel {

    void loadAllCourses(User user);
    
    ObservableList<Course> getObservableCourseList();
    

}
