/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import attendance.gui.controller.DashboardController;
import attendance.gui.controller.TodayController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Attendance extends Application {

    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("EASV Attendance");
        showLogin();
    }

    /**
     * Initializes the root layout.
     */
    public void showLogin() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Attendance.class.getResource("/attendance/gui/view/Login.fxml"));
            Parent login = loader.load();

            // Show the scene containing the root layout.
            //Scene rootLayoutScene = new Scene(rootLayout);
            Scene loginScene = new Scene(login);
            primaryStage.setScene(loginScene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
