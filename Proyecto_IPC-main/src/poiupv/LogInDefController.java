/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.Navegacion;
import model.User;


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
    private Label passwordError;
    @FXML
    private Label signUpLink;
    @FXML
    private Button enterButton;    
    @FXML
    private Line passwordEyeLine;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private HBox aux;
    
    Navegacion navegation;
   

    public void initStage(Stage stage)
    {
        primaryStage = stage;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        try {            
            navegation = Navegacion.getSingletonNavegacion();
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(LogInDefController.class.getName()).log(Level.SEVERE, null, ex);
        }     
        passwordField.textProperty().addListener((obs, oldVal, newVal) -> {
            passwordTextField.setText(newVal);
        });
        passwordTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            passwordField.setText(newVal);
        });
    }
    @FXML
    private void handleEyeCicked(MouseEvent event) {
        boolean temp = passwordEyeLine.isVisible();
        passwordEyeLine.setVisible(!temp);
        passwordField.setVisible(!temp);
        aux.requestFocus();
        passwordField.setVisible(!temp);
        passwordTextField.setVisible(temp);
    }

    @FXML
    private void handleSignUpClick(MouseEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("../view/SignUpDef.fxml"));
        Parent root = (Parent) myLoader.load();
        Scene scene = new Scene(root);
        SignUpDefController signUpContr = myLoader.<SignUpDefController>getController();
        signUpContr.initStage(primaryStage);
        primaryStage.setScene(scene);      
    }

    @FXML
    private void handleEnterPressed(ActionEvent event) throws Exception {
        if (!navegation.exitsNickName(usernameField.getText())){
           usernameError.setVisible(true);
           passwordError.setVisible(true);
        }
        User user = navegation.loginUser(usernameField.getText(), passwordField.getText());
        if(user == null) //CHECK IF INPUT DATA IS CORRECT
        {
           passwordError.setVisible(true);          
        }
        else
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("../view/MainTest.fxml"));
            Parent root = (Parent) myLoader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);            
        }
    }    

    
}
