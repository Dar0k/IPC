/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.Navegacion;

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
        /*try {
            // TODO
            navegation = Navegacion.getSingletonNavegacion();
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(SignUpDefController.class.getName()).log(Level.SEVERE, null, ex);
        }           
        sel = ava1;
        Image im1 = new Image("https://th.bing.com/th/id/OIP.EQBqKixnFE0c6UryfIgS-AHaGQ?pid=ImgDet&rs=1");
        Image im2 = new Image("https://th.bing.com/th/id/OIP.AaMb_876cSr06RW4AJ3XyAHaGk?pid=ImgDet&w=500&h=444&rs=1");
        Image im3 = new Image("https://th.bing.com/th/id/OIP.XnD-Oh_qsK18OPXdbLWh7gHaFw?pid=ImgDet&rs=1");
        Image im4 = new Image("https://th.bing.com/th/id/OIP.ErgU0tyLu4W4Mv5t6nbdbAHaGV?pid=ImgDet&w=741&h=634&rs=1");
        Image im5 = new Image("https://thumbs.dreamstime.com/b/american-eagle-isolated-close-up-portrait-36632728.jpg");
        avaPrin.setFill(new ImagePattern(im1));          
        ava1.setFill(new ImagePattern(im1));
        ava2.setFill(new ImagePattern(im2));
        ava3.setFill(new ImagePattern(im3));
        ava4.setFill(new ImagePattern(im4));
        ava5.setFill(new ImagePattern(im5));     
        
        
        validUsername = new SimpleBooleanProperty();
        validPassword = new SimpleBooleanProperty();
        validReenterPassword = new SimpleBooleanProperty();
        validEmail = new SimpleBooleanProperty();
        validAge = new SimpleBooleanProperty();
        
        validUsername.setValue(Boolean.FALSE);
        validPassword.setValue(Boolean.FALSE);
        validReenterPassword.setValue(Boolean.FALSE);
        validEmail.setValue(Boolean.FALSE);
        validAge.setValue(Boolean.FALSE);
        
        usernameField.textProperty().addListener((observable, oldValue, newValue)->{
            checkEditUsername(newValue);
        });
        
        passwordField.textProperty().addListener((observable, oldValue, newValue)->{             
            checkEditPassword(newValue); 
            checkEditReenterPassword(newValue, reenterPasswordField.getText()); 
            checkEditReenterPassword(newValue, reenterPasswordTextField.getText());              
        });        
        passwordTextField.textProperty().addListener((observable, oldValue, newValue)->{
            checkEditPassword(newValue); 
            checkEditReenterPassword(newValue, reenterPasswordField.getText());
            checkEditReenterPassword(newValue, reenterPasswordTextField.getText()); 
        });
        
        reenterPasswordField.textProperty().addListener((observable, oldValue, newValue)->{
            checkEditReenterPassword(passwordField.getText(), newValue); 
        });
        reenterPasswordTextField.textProperty().addListener((observable, oldValue, newValue)->{
            checkEditReenterPassword(passwordTextField.getText(), newValue); 
        });
        
        emailField.textProperty().addListener((observable, oldValue, newValue)->{
            checkEditEmail(newValue); 
        });
        
        agePicker.valueProperty().addListener((observable, oldValue, newValue)->{
            checkEditAge(newValue); 
        });         
        
        passwordField.textProperty().addListener((obs, oldVal, newVal) -> {
            passwordTextField.setText(newVal);
        });
        passwordTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            passwordField.setText(newVal);
        });
        reenterPasswordField.textProperty().addListener((obs, oldVal, newVal) -> {
            reenterPasswordTextField.setText(newVal);
        });
        reenterPasswordTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            reenterPasswordField.setText(newVal);
        });
        
        BooleanBinding validFields = Bindings.and(validUsername, validPassword).and(validReenterPassword).and(validEmail).and(validAge);
        
        signUpButton.disableProperty().bind(Bindings.not(validFields));        
        signUpButton.setOnAction( (event)->{  
            try {signUpCheck();}
            catch(Exception e){}
        });*/
    }
    public void initStage(Stage stage)
    {
        primaryStage = stage;
        primaryStage.setTitle("Profile");
    }

    

    @FXML
    private void handleChooseImage(ActionEvent event) {
    }


    @FXML
    private void handlePasswordField(ActionEvent event) {
    }

    @FXML
    private void handleRepeatPassword(ActionEvent event) {
    }
    
    @FXML
    private void handleCancelButton(ActionEvent event) {
    }

    @FXML
    private void handleSaveButton(ActionEvent event) {
    }
    
    @FXML
    private void OnMouseHoverExited(MouseEvent event) {
        Button source = (Button)event.getSource();
        if(source.equals(profileButton)){
            source.setStyle(" -fx-font-weight: bold; -fx-background-color: transparent;");
        }else{
            source.setStyle("-fx-background-color: transparent; -fx-background-radius: 0;");
        }
    }

    @FXML
    private void OnMouseHoverEnter(MouseEvent event) {
        Button source = (Button)event.getSource();
        if(source.equals(profileButton)){
            source.setStyle("-fx-background-color: rgb(200,200,200,0.5); -fx-background-radius: 50; -fx-font-weight: bold;");
        }else{
            source.setStyle("-fx-background-color: rgb(200,200,200,0.5); -fx-background-radius: 50;");
        }
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
}
