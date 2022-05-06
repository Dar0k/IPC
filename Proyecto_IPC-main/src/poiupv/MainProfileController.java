/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Navegacion;
import model.User;

/**
 * FXML Controller class
 *
 * @author DROCPER
 */
public class MainProfileController implements Initializable {

    private Stage primaryStage;    
    
    private BooleanProperty validPassword;
    private BooleanProperty validReenterPassword;
    private BooleanProperty validEmail;
    private BooleanProperty validAge;
    
    
    @FXML
    private Circle avaPrin;
    @FXML
    private Circle ava1;
    @FXML
    private Circle ava2;
    @FXML
    private Circle ava3;
    @FXML
    private Circle ava4;
    @FXML
    private Circle ava5;
    @FXML
    private Button chooseImageButton1;
    @FXML
    private DatePicker agePicker;
    @FXML
    private Label ageError;
    @FXML
    private TextField emailField;
    @FXML
    private Label emailError;
    @FXML
    private TextField passwordTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private StackPane passwordEye;
    @FXML
    private Line passwordEyeLine;
    @FXML
    private Label passwordError;
    @FXML
    private TextField reenterPasswordTextField;
    @FXML
    private PasswordField reenterPasswordField;
    @FXML
    private StackPane reenterPasswordEye;
    @FXML
    private Line reenterPasswordEyeLine;
    @FXML
    private Label reenterPasswordError;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private VBox aux;
    @FXML
    private SidebarController sidebarController;
    

    Navegacion navegation;
    User user; 
    Circle sel;    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        sidebarController.initialize(url, rb);
        updateSidebar();
        
        try {
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
        
        
        validPassword = new SimpleBooleanProperty();
        validReenterPassword = new SimpleBooleanProperty();
        validEmail = new SimpleBooleanProperty();
        validAge = new SimpleBooleanProperty();
        
        validPassword.setValue(Boolean.FALSE);
        validReenterPassword.setValue(Boolean.FALSE);
        validEmail.setValue(Boolean.FALSE);
        validAge.setValue(Boolean.FALSE);
        
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
        
        BooleanBinding validFields = Bindings.and(validReenterPassword, validPassword).and(validEmail).and(validAge);
        
        saveButton.disableProperty().bind(Bindings.not(validFields));        
        saveButton.setOnAction( (event)->{  
            try {saveCheck();}
            catch(Exception e){}
        });
        cancelButton.setOnAction( (event)->{  
            try {cancelCheck();}
            catch(Exception e){}
        });
    }
    
    private void saveCheck() throws Exception
    {
        // open dialog box to confirm that wants to save
        //update DB    
    }
    
    private void cancelCheck() throws Exception
    {
        //open dialog box to that wont saev anything
        //reput the correct data    
    }
    
    public void initStage(Stage stage)
    {
        primaryStage = stage;
        primaryStage.setTitle("Profile");
        sidebarController.primaryStage = primaryStage;
    }
    
    public void updateSidebar()
    {
        sidebarController.clearSidebar();
        sidebarController.boldProfileButton();
    }

    @FXML
    private void handleChooseImage(ActionEvent event) throws Exception{
        FileChooser fileChoose = new FileChooser();
        File recordsDir = new File(System.getProperty("user.Desktop"), "src/resources");
        System.out.println(recordsDir);
        if (! recordsDir.exists()) {
            recordsDir.mkdirs();
        }
        fileChoose.setInitialDirectory(recordsDir);
        FileChooser.ExtensionFilter fileExtensions = 
        new FileChooser.ExtensionFilter(
        "Images", "*.jpeg", "*.png", "*.jpg");

        fileChoose.getExtensionFilters().add(fileExtensions);
        fileChoose.setTitle("Open File");  
        File file = fileChoose.showOpenDialog(primaryStage);
        System.out.print(file);
        String imageUrl = file.toURI().toURL().toExternalForm();
        Image image = new Image(imageUrl);
        avaPrin.setFill(new ImagePattern(image));
        sel.setStroke(Color.TRANSPARENT);    
    }
    
    @FXML
    private void handleSelectAvatar(MouseEvent event) {
        Circle source = (Circle)event.getSource();
        sel.setStroke(Color.TRANSPARENT);
        sel = source;
        source.setStroke(Color.BLACK);  
        source.setStrokeWidth(1.5);
        avaPrin.setFill(source.getFill());  
    }    

    @FXML
    private void handleEyeCicked(MouseEvent event) {
        Node source = (Node)event.getSource();
        if (source.getId().equals(passwordEye.getId())) {
            boolean temp = passwordEyeLine.isVisible();
            passwordEyeLine.setVisible(!temp);
            passwordField.setVisible(!temp);
            aux.requestFocus();
            passwordField.setVisible(!temp);
            passwordTextField.setVisible(temp);
        } else {
            boolean temp = reenterPasswordEyeLine.isVisible();
            reenterPasswordEyeLine.setVisible(!temp);
            reenterPasswordField.setVisible(!temp);
            aux.requestFocus();
            reenterPasswordField.setVisible(!temp);
            reenterPasswordTextField.setVisible(temp);
        }  
    }
    
    public void checkEditPassword(String pas){
        if(!User.checkPassword(pas)){
            passwordError.setVisible(true);
            validPassword.setValue(Boolean.FALSE);
            if (pas.length() < 8 || pas.length() > 20){
                passwordError.setText("Must contain between 8 and 20 characters");
            }else if (!pas.matches("^.*[A-Z].*$")){
                passwordError.setText("Must contain at least one uppercase");
            }else if (!pas.matches("^.*[0-9].*$")){
                passwordError.setText("Must contain at least one diggit");
            }else if (!pas.matches("^.*[a-z].*$")) {
                passwordError.setText("Must contain at least one lowercase");
            }else if (pas.contains(" ")){
                passwordError.setText("Can not contain any blank spaces");
            }else {
                passwordError.setText("Must contain at least one of these characters: !@#$%&*&*()-+=");
            }            
        }else {
            passwordError.setVisible(false);
            validPassword.setValue(Boolean.TRUE); 
        }
    }
    
    public void checkEditReenterPassword(String pas, String rePas){
        if (!pas.equals(rePas) ){
            reenterPasswordError.setVisible(true);
            reenterPasswordError.setText("Password is not the same");
            validReenterPassword.setValue(Boolean.FALSE);            
        }else {
            reenterPasswordError.setVisible(false);
            validReenterPassword.setValue(Boolean.TRUE);
        }   
    }
    
    public void checkEditEmail(String str){
        if(!User.checkEmail(str)){
            emailError.setVisible(true);
            emailError.setText("Email error");
            validEmail.setValue(Boolean.FALSE); 
        }else {
            emailError.setVisible(false);
            validEmail.setValue(Boolean.TRUE); 
        }        
    }
    
    public void checkEditAge(LocalDate dtp){
        if(dtp.plusYears(12).isAfter(LocalDate.now())){
            ageError.setVisible(true);
            ageError.setText("Must be at least 12 years old");
            validAge.setValue(Boolean.FALSE); 
        } else {
            ageError.setVisible(false);
            validAge.setValue(Boolean.TRUE); 
        }
    }    
}
