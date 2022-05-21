/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
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
    private BooleanProperty modified;
    
    
    @FXML
    private Circle avaPrin;
    @FXML
    private Label username;
    @FXML
    private Button chooseImageButton;
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
    private VBox sidebar;
    @FXML
    private SidebarController sidebarController;
    @FXML
    private Label birthLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label rePasswordLabel;
    @FXML
    private VBox fieldVb;
    @FXML
    private HBox avaHbox;
    

    Navegacion navegation;
    User user; 
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        sidebarController.initialize(url, rb);     
        
        try {
            navegation = Navegacion.getSingletonNavegacion();
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(SignUpDefController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        validPassword = new SimpleBooleanProperty();
        validReenterPassword = new SimpleBooleanProperty();
        validEmail = new SimpleBooleanProperty();
        validAge = new SimpleBooleanProperty();
        modified = new SimpleBooleanProperty();
        
        validPassword.setValue(Boolean.FALSE);
        validReenterPassword.setValue(Boolean.FALSE);
        validEmail.setValue(Boolean.FALSE);
        validAge.setValue(Boolean.FALSE);
        modified.setValue(Boolean.FALSE);
        
        passwordField.textProperty().addListener((observable, oldValue, newValue)->{             
            checkEditPassword(newValue); 
            checkEditReenterPassword(newValue, reenterPasswordField.getText()); 
            checkEditReenterPassword(newValue, reenterPasswordTextField.getText());
            if(modified.getValue() == false){
                modified.setValue(Boolean.TRUE);
            }
        });        
        passwordTextField.textProperty().addListener((observable, oldValue, newValue)->{
            checkEditPassword(newValue); 
            checkEditReenterPassword(newValue, reenterPasswordField.getText());
            checkEditReenterPassword(newValue, reenterPasswordTextField.getText());
            if(modified.getValue() == false){
                modified.setValue(Boolean.TRUE);
            }
        });
        
        reenterPasswordField.textProperty().addListener((observable, oldValue, newValue)->{
            checkEditReenterPassword(passwordField.getText(), newValue);
            if(modified.getValue() == false){
                modified.setValue(Boolean.TRUE);
            }
        });
        reenterPasswordTextField.textProperty().addListener((observable, oldValue, newValue)->{
            checkEditReenterPassword(passwordTextField.getText(), newValue);
            if(modified.getValue() == false){
                modified.setValue(Boolean.TRUE);
            }
        });
        
        emailField.textProperty().addListener((observable, oldValue, newValue)->{
            checkEditEmail(newValue);
            if(modified.getValue() == false){
                modified.setValue(Boolean.TRUE);
            }
        });
        
        agePicker.valueProperty().addListener((observable, oldValue, newValue)->{
            checkEditAge(newValue);
            if(modified.getValue() == false){
                modified.setValue(Boolean.TRUE);
            }
        });      
        
        avaPrin.fillProperty().addListener((observable, oldValue, newValue)->{
            if(modified.getValue() == false){
                modified.setValue(Boolean.TRUE);
            }
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
        
        modified.addListener((obs, oldVal, newVal) -> {
            sidebarController.testButton.setDisable(newVal);
            sidebarController.resultsButton.setDisable(newVal);
            sidebarController.profileButton.setDisable(newVal);
            sidebarController.logOutButton.setDisable(newVal);
        });
        
        BooleanBinding validFields = Bindings.and(validReenterPassword, validPassword).and(validEmail).and(validAge).and(modified);
        
        saveButton.disableProperty().bind(Bindings.not(validFields));        
        saveButton.setOnAction( (event)->{  
            try {saveCheck();}
            catch(Exception e){}
        });
        
        cancelButton.disableProperty().bind(Bindings.not(modified));
        cancelButton.setOnAction( (event)->{  
            try {cancelCheck();}
            catch(Exception e){}
        });
    }
    
    private void saveCheck() 
    {
        // open dialog box to confirm that wants to save
        Alert alert = new Alert(AlertType.CONFIRMATION);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Cancel");
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image("file:src/resources/navegacion.png"));
        alert.getDialogPane().getStylesheets().add(getClass().getResource("../resources/alerts.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("customAlert");
        alert.setTitle("Save");
        alert.setHeaderText("Are you sure you want to save?");
        alert.setContentText("Your profile information will be updated but you can't undo the action. Are you sure you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("OK");
            ImagePattern imPa = (ImagePattern) avaPrin.getFill();
            Image image = imPa.getImage();
            try{
                user.setAvatar(image);
                user.setBirthdate(agePicker.getValue());
                user.setEmail(emailField.getText());
                user.setPassword(passwordField.getText());
                initStage(primaryStage, user);

            }
            catch(NavegacionDAOException e)
            {
                Alert alertErr = new Alert(Alert.AlertType.ERROR);
                //CAMBIAR TIPO DE DIALOGO?
                ((Button) alertErr.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");

                Stage alertStageErr = (Stage) alertErr.getDialogPane().getScene().getWindow();
                alertStageErr.getIcons().add(new Image("file:src/resources/navegacion.png"));
                alertErr.getDialogPane().getStylesheets().add(getClass().getResource("../resources/alerts.css").toExternalForm());
                alertErr.getDialogPane().getStyleClass().add("customAlert");
                alertErr.setTitle("Exception Dialog");
                alertErr.setHeaderText("An error has occurred");
                alertErr.setContentText("An unexcpected error has occurred when trying to save the new changes into the database. Please try again");


                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String exceptionText = sw.toString();

                Label label = new Label("Exception:");
                TextArea textArea = new TextArea(exceptionText);
                textArea.setEditable(false);
                textArea.setWrapText(true);
                textArea.setMaxWidth(Double.MAX_VALUE);
                textArea.setMaxHeight(Double.MAX_VALUE);
                GridPane.setVgrow(textArea, Priority.ALWAYS);
                GridPane.setHgrow(textArea, Priority.ALWAYS);
                GridPane expContent = new GridPane();
                expContent.setMaxWidth(Double.MAX_VALUE);
                expContent.add(label,0,0);
                expContent.add(textArea, 0, 1);

                alertErr.getDialogPane().setExpandableContent(expContent);
                alertErr.showAndWait();
                
            }
            
            
            
        } else {
            System.out.println("CANCEL");
        }   
    }
    
    private void cancelCheck() 
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Cancel");
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image("file:src/resources/navegacion.png"));
        alert.getDialogPane().getStylesheets().add(getClass().getResource("../resources/alerts.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("customAlert");
        alert.setTitle("Cancelation");
        alert.setHeaderText("Are you sure you want to cancel?");
        alert.setContentText("All your modifications will be discarted. Are you sure you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("OK");
            initStage(primaryStage, user);
        } else {
            System.out.println("CANCEL");
        }
    }
    
    public void initStage(Stage stage, User us)
    {
        primaryStage = stage;
        primaryStage.setTitle("Profile");
        sidebarController.primaryStage = primaryStage;
        user = us;
        sidebarController.setUser(user);
        Image im = us.getAvatar();
        ImagePattern imPa = new ImagePattern(im);
        avaPrin.setFill(imPa);
        username.setText(us.getNickName());
        passwordField.setText(us.getPassword());
        reenterPasswordField.setText(us.getPassword());
        emailField.setText(us.getEmail());
        agePicker.setValue(us.getBirthdate());
        modified.setValue(Boolean.FALSE);       
        
        primaryStage.heightProperty().addListener((obs, oldv, newv)-> {
            calcSize((double)newv);
        });
        calcSize(primaryStage.getHeight());
        
        primaryStage.widthProperty().addListener((obs, oldv, newv)->{
            System.out.println("prin: "+ newv);
            System.out.println("aux: "+ aux.getWidth());
            calcSideBar((double) newv);
        });
        calcSideBar(primaryStage.getWidth());
    }
    
    public void updateSidebar(double w)
    {
        sidebarController.clearSidebar(w);
        sidebarController.boldProfileButton(w);
    }

    @FXML
    private void handleChooseImage(ActionEvent event) {
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
        
        try{
            if(file != null)
            {
                String imageUrl = file.toURI().toURL().toExternalForm();
                Image image = new Image(imageUrl);
                avaPrin.setFill(new ImagePattern(image));
            }
        }
        catch(MalformedURLException e){
            Alert alertErr = new Alert(Alert.AlertType.ERROR);

            ((Button) alertErr.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");

            Stage alertStageErr = (Stage) alertErr.getDialogPane().getScene().getWindow();
            alertStageErr.getIcons().add(new Image("file:src/resources/navegacion.png"));
            alertErr.getDialogPane().getStylesheets().add(getClass().getResource("../resources/alerts.css").toExternalForm());
            alertErr.getDialogPane().getStyleClass().add("customAlert");
            alertErr.setTitle("Exception Dialog");
            alertErr.setHeaderText("An error has occurred");
            alertErr.setContentText("The image selected could not be opened. Please try again");


            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("Exception:");
            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);
            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label,0,0);
            expContent.add(textArea, 0, 1);

            alertErr.getDialogPane().setExpandableContent(expContent);
            alertErr.showAndWait();
        }
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
    
    private void calcSize (double newv){    
        if((double)newv < 400){
            Font fontUs = Font.font("Serif", FontWeight.NORMAL, FontPosture.REGULAR, 30);
            Font fontLa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12);
            Font fontEr = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12);
            Font fontBu = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 14);
            Font fontBuIm = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12);
            
            passwordLabel.setFont(fontLa);
            rePasswordLabel.setFont(fontLa);
            birthLabel.setFont(fontLa);
            emailLabel.setFont(fontLa);
            passwordField.setFont(fontLa);
            passwordTextField.setFont(fontLa);
            reenterPasswordField.setFont(fontLa);
            reenterPasswordTextField.setFont(fontLa);
            emailField.setFont(fontLa);
            agePicker.setStyle("-fx-font-size: "+(12));
            passwordError.setFont(fontEr);
            reenterPasswordError.setFont(fontEr);
            emailError.setFont(fontEr);
            ageError.setFont(fontEr);
            username.setFont(fontUs);
            avaPrin.setRadius(30);
            aux.setSpacing(5.0);            
            cancelButton.setFont(fontBu);
            saveButton.setFont(fontBu);
            chooseImageButton.setFont(fontBu);
            avaHbox.setSpacing(0);
        }  else if((double)newv >= 400 && (double)newv <530){
            double act = 530-(double)newv;
            int stageDif = 530-375;
            double per = 1 - (act/stageDif);
            int radDif = 60-30;
            int spaceDif = 12-5;
            int fontUsDif = 50 - 30;
            int fontLaDif = 16 - 12;
            int fontErDif = 14 - 12;
            int spaceLaDif = 10 - 0;
            int buDif = 18-14;
            int buImDif = 16-12;
            int spaceAvDif = 15-0;   
            
            Font fontUs = Font.font("Serif", FontWeight.NORMAL, FontPosture.REGULAR, 30 + (fontUsDif*per));
            Font fontLa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12 + (fontLaDif*per));
            Font fontEr = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12 + (fontErDif*per));
            Font fontBu = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 14 + (buDif*per));
            Font fontBuIm = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12 + (buImDif*per));          
            
            fieldVb.setSpacing(0 + (spaceLaDif*per));
            passwordLabel.setFont(fontLa);
            rePasswordLabel.setFont(fontLa);
            birthLabel.setFont(fontLa);
            emailLabel.setFont(fontLa);
            passwordField.setFont(fontLa);
            passwordTextField.setFont(fontLa);
            reenterPasswordField.setFont(fontLa);
            reenterPasswordTextField.setFont(fontLa);
            emailField.setFont(fontLa);
            agePicker.setStyle("-fx-font-size: "+(12.0 + (fontLaDif*per)));
            passwordError.setFont(fontEr);
            reenterPasswordError.setFont(fontEr);
            emailError.setFont(fontEr);
            ageError.setFont(fontEr);          
            username.setFont(fontUs);
            avaPrin.setRadius(30 + (radDif * per));
            aux.setSpacing(5 + (spaceDif * per));
            cancelButton.setFont(fontBu);
            saveButton.setFont(fontBu);
            chooseImageButton.setFont(fontBuIm);
            avaHbox.setSpacing(5 + (spaceAvDif*per));
        }else{
            Font fontUs = Font.font("Serif", FontWeight.NORMAL, FontPosture.REGULAR, 50);
            Font fontLa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 16);
            Font fontEr = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 14);
            Font fontBu = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 18);
            Font fontBuIm = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 16);
            
            fieldVb.setSpacing(10);
            passwordLabel.setFont(fontLa);
            rePasswordLabel.setFont(fontLa);
            birthLabel.setFont(fontLa);
            emailLabel.setFont(fontLa);
            passwordField.setFont(fontLa);
            passwordTextField.setFont(fontLa);
            reenterPasswordField.setFont(fontLa);
            reenterPasswordTextField.setFont(fontLa);
            emailField.setFont(fontLa);
            agePicker.setStyle("-fx-font-size: "+ 16);
            passwordError.setFont(fontEr);
            reenterPasswordError.setFont(fontEr);
            emailError.setFont(fontEr);
            ageError.setFont(fontEr);            
            username.setFont(fontUs);
            avaPrin.setRadius(60);
            aux.setSpacing(12);
            cancelButton.setFont(fontBu);
            saveButton.setFont(fontBu);
            chooseImageButton.setFont(fontBuIm);
            avaHbox.setSpacing(15);
        }
    }
    
    public void calcSideBar (double w) {           
        if(w < 1000){
            sidebar.setMinWidth( w * 0.25 );
            sidebar.setMaxWidth( w * 0.25 );
            updateSidebar(w * 0.25);
        }else{
            sidebar.setMinWidth(250);
            sidebar.setMaxWidth(250);
            updateSidebar(250);
        }     
    }   
}
