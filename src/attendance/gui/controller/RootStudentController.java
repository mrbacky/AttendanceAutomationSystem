package attendance.gui.controller;

import attendance.be.User;
import attendance.gui.model.ModelCreator;
import attendance.gui.model.interfaces.ICourseModel;
import attendance.gui.model.interfaces.ILessonModel;
import attendance.gui.model.interfaces.IRecordModel;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RootStudentController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private HBox hBoxNavigationBar;
    @FXML
    private JFXButton btnToday;
    @FXML
    private JFXButton btnOverview;
    @FXML
    private Label lblHello;
    @FXML
    private JFXButton btnLogOut;
    @FXML
    private AnchorPane attachable;

    private final String TODAY_MODULE = "/attendance/gui/view/StudentTodayModule.fxml";
    private final String OVERVIEW_MODULE = "/attendance/gui/view/StudentOverviewModule.fxml";
    private final String LOGIN_VIEW = "/attendance/gui/view/Login.fxml";

    private User user;
    private ICourseModel courseModel;
    private final ILessonModel lessonModel;
    private final IRecordModel recordModel;

    public RootStudentController() {
        lessonModel = ModelCreator.getInstance().getLessonModel();
        recordModel = ModelCreator.getInstance().getRecordModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void injectModel(ICourseModel courseModel) {
        this.courseModel = courseModel;
    }

    public void setUser(User currentUser) {
        this.user = currentUser;
        lblHello.setText(user.getName());
        showModule(TODAY_MODULE);
    }

    public void showModule(String MODULE) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MODULE));
            Parent moduleRoot = fxmlLoader.load();

            if (MODULE.equals(TODAY_MODULE)) {
                StudentTodayController controller = fxmlLoader.getController();
                controller.setUser(user);
                controller.injectModel(lessonModel);
                controller.initializeTodayModule();
            }
            if (MODULE.equals(OVERVIEW_MODULE)) {
                StudentOverviewController controller = fxmlLoader.getController();
                controller.setUser(user);
                controller.injectModels(courseModel, recordModel);
                controller.initializeOverviewModule();
            }
            borderPane.setCenter(moduleRoot);
        } catch (IOException ex) {
            Logger.getLogger(RootStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showToday(ActionEvent event) {
        showModule(TODAY_MODULE);
    }

    @FXML
    private void showOverview(ActionEvent event) {
        showModule(OVERVIEW_MODULE);
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        Stage logOutStage;
        logOutStage = (Stage) btnLogOut.getScene().getWindow();
        logOutStage.close();

        URL url = getClass().getResource(LOGIN_VIEW);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
