package attendance.gui.controller;

import attendance.be.User;
import attendance.gui.model.ModelCreator;
import attendance.gui.model.ModelException;
import attendance.gui.model.interfaces.ICourseModel;
import attendance.gui.model.interfaces.IRecordModel;
import attendance.gui.model.interfaces.IStudentModel;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RootTeacherController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private HBox hBoxNavigationBar;
    @FXML
    private JFXButton btnDashboard;
    @FXML
    private Label lblName;
    @FXML
    private JFXButton btnLogOut;

    private final String DashboardModule = "/attendance/gui/view/TeacherDashboardModule.fxml";
    private final String LoginPage = "/attendance/gui/view/Login.fxml";

    private User user;
    private ICourseModel courseModel;
    private IRecordModel recordModel;
    private IStudentModel studentModel;

    public RootTeacherController() throws Exception {
        recordModel = ModelCreator.getInstance().getRecordModel();
        studentModel = ModelCreator.getInstance().getStudentModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void injectModel(ICourseModel courseModel) {
        this.courseModel = courseModel;
    }

    void setUser(User currentUser) {
        this.user = currentUser;
        lblName.setText(user.getName());
    }

    void initializeView() {
        showModule(DashboardModule);
    }

    private void showModule(String MODULE) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MODULE));
            Parent moduleRoot = fxmlLoader.load();

            TeacherDashboardController controller = fxmlLoader.getController();
            controller.setUser(user);
            controller.injectModels(courseModel, studentModel, recordModel);
            controller.initializeView();

            borderPane.setCenter(moduleRoot);
            ///name of pane where you want to put the fxml.
        } catch (IOException ex) {
            Logger.getLogger(RootTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showDashboard(ActionEvent event) {
        showModule(DashboardModule);
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        if (!recordModel.getRecordList().isEmpty()) {
            recordModel.stopObserving();
        };

        if (!studentModel.getStudentList().isEmpty()) {
            studentModel.stopObserving();
        };

        Stage logOutStage;
        logOutStage = (Stage) btnLogOut.getScene().getWindow();
        logOutStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(LoginPage));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
