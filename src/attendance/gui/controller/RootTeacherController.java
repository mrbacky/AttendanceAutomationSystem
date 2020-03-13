
package attendance.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class RootTeacherController implements Initializable {

    @FXML
    private HBox buttonBar;
    @FXML
    private JFXButton btnDashboard;
    @FXML
    private JFXButton btmStudentAttendance;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private AnchorPane attachable;

    
    private final String DashboardModule = "src/attendance/gui/view/TeacherDashboard.fxml";
    private final String AttendanceModule = "src/attendance/gui/view/TeacherStudentAttendance.fxml";
    
    
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showModule(DashboardModule);
    }

    private void showModule(String urlToShow) {
        try {
            File file = new File(urlToShow);
            URL url = file.toURI().toURL();
            attachable.getChildren().clear();
            attachable.getChildren().add(FXMLLoader.load(url));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    private void showDashboard(ActionEvent event) {
        showModule(DashboardModule);
    }

    @FXML
    private void showStudentAttendance(ActionEvent event) {
        showModule(AttendanceModule);

    }

    @FXML
    private void handleLogout(ActionEvent event) {
    }

}
