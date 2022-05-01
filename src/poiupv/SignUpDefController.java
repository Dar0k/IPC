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
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DROCPER
 */
public class SignUpDefController implements Initializable {
    
    private Stage primaryStage;
    private Scene prevScene;
    private String prevTitle;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private PasswordField repeatPasswordInput;
    @FXML
    private DatePicker agePicker;
    @FXML
    private Button chooseImageButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button nextButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initStage(Stage stage)
    {
        prevScene = stage.getScene();
        prevTitle = stage.getTitle();
        primaryStage = stage;
        primaryStage.setTitle("SIGN UP");
    }

    @FXML
    private void handleChooseImage(ActionEvent event) {
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        primaryStage.setScene(prevScene);
        primaryStage.setTitle(prevTitle);
    }

    @FXML
    private void handleNextButton(ActionEvent event) {
    }
    
}
