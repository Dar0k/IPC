/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DROCPER
 */
public class MainTestController implements Initializable {
    
    private Stage primaryStage;
    private Scene prevScene;
    private String prevTitle;
    @FXML
    private Button testButton;
    @FXML
    private Button resultButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button continueButton;
    @FXML
    private Button randomButton;
    @FXML
    private Button listButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initStage(Stage stage)
    {
        primaryStage = stage;
        prevScene = stage.getScene();
        prevTitle = stage.getTitle();
        primaryStage.setTitle("MAIN");
    }

    @FXML
    private void handleResult(ActionEvent event) {
    }

    @FXML
    private void handleProfile(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainProfile.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        MainProfileController profileController = loader.<MainProfileController>getController();
        profileController.initStage(primaryStage);
        primaryStage.setScene(scene);
    }

    @FXML
    private void handleLogOut(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainLogOut.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        MainLogOutController logOutController = loader.<MainLogOutController>getController();
        logOutController.initStage(primaryStage);
        primaryStage.setScene(scene);
    }

    @FXML
    private void handleContinue(ActionEvent event) {
    }

    @FXML
    private void handleRandomAction(ActionEvent event) {
    }

    @FXML
    private void handleList(ActionEvent event) {
    }
    
}
