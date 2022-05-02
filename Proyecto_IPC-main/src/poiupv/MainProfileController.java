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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DROCPER
 */
public class MainProfileController implements Initializable {

    private Stage primaryStage;
    @FXML
    private Button testButton;
    @FXML
    private Button resultButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Button chooseImageButton;
    @FXML
    private DatePicker agePicker;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private ImageView showPassword;
    @FXML
    private PasswordField repeatPasswordField;
    @FXML
    private ImageView handleShowPassword;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;

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
        primaryStage.setTitle("Profile");
    }

    @FXML
    private void handleTest(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainTest.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        MainTestController mainTCtrl = loader.<MainTestController>getController();
        mainTCtrl.initStage(primaryStage);
        primaryStage.setScene(scene);
    }

    @FXML
    private void handleResult(ActionEvent event) throws Exception{
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainTest.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        MainTestController mainTCtrl = loader.<MainTestController>getController();
        mainTCtrl.initStage(primaryStage);
        primaryStage.setScene(scene);*/
    }

    @FXML
    private void handleLogOut(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainLogOut.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        MainLogOutController logOutController = loader.<MainLogOutController>getController();
        logOutController.initStage(primaryStage);
        primaryStage.setScene(scene);
    }

    @FXML
    private void handleChooseImage(ActionEvent event) {
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
    }

    @FXML
    private void handleSaveButton(ActionEvent event) {
    }

    @FXML
    private void handlePasswordField(ActionEvent event) {
    }

    @FXML
    private void handleRepeatPassword(ActionEvent event) {
    }
    
}
