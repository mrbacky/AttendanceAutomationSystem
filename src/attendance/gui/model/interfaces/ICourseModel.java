/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.model.interfaces;

import attendance.be.Course;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author rado
 */
public interface ICourseModel {

    List<Course> loadAllCourses(int userId);

}
