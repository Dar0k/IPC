/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import javafx.scene.text.Font;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DROCPER
 */
public class LogInDefController implements Initializable {

    private Stage primaryStage;
    @FXML
    private Label usernameError;
    @FXML
    private ImageView passwordShowEye;
    @FXML
    private Label passwordError;
    @FXML
    private Label signUpLink;
    @FXML
    private Button enterButton;
    private boolean passwordShow = false;
    @FXML
    private Line passwordEyeLine;
    private PasswordField userPassword;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField userPasswordField;

    public void initStage(Stage stage)
    {
        primaryStage = stage;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleSignUpHoverExit(MouseEvent event) {
        signUpLink.setFont(new Font(15));
    }

    @FXML
    private void handleSignUpHover(MouseEvent event) {
       signUpLink.setFont(new Font(17));
    }

    @FXML
    private void handleSignUpClick(MouseEvent event) {
    }

    @FXML
    private void handleEnterHoverExit(MouseEvent event) {
        enterButton.setPrefSize(98, 31);
    }

    @FXML
    private void handleEnterHover(MouseEvent event) {
        enterButton.setPrefSize(100, 33);
    }

    @FXML
    private void handleEnterPressed(ActionEvent event) throws Exception {
        if(!checkLogin(usernameField, userPasswordField)) //CHECK IF INPUT DATA IS CORRECT
        {
          passwordError.setVisible(true);
          usernameError.setVisible(true);
        }
        else
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("MainTestController.fxml"));
            Parent root = (Parent) myLoader.load();
        }
    }

    @FXML
    private void handlePasswordEyeHoverExit(MouseEvent event) {
    }

    @FXML
    private void handlePasswordEyeHover(MouseEvent event) {
    }

    @FXML
    private void handlePasswordEyeClicked(MouseEvent event) {
        if(!passwordShow)
        {
            passwordShow = true;
            passwordEyeLine.setVisible(true);
            
        }
        else
        {
            
            passwordEyeLine.setVisible(false);
        }
    }
    private boolean checkLogin(TextField user, PasswordField password )
    {
        return true;
    }
    
}
