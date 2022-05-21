/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
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
    @FXML
    private Label logInLabel;
    
    Navegacion navegation;
    @FXML
    private VBox side;
    private double width;
    private double height;

    public void initStage(Stage stage)
    {
        primaryStage = stage;
        width = primaryStage.getWidth();
        height = primaryStage.getHeight();
        primaryStage.widthProperty().addListener((obs, oldv, newv) -> {
             width = (double) newv;     
             setSide(width, height);
        });
        primaryStage.heightProperty().addListener((obs, oldv, newv) ->{
             height = (double) newv;
             setSide(width, height);
        
        });
        
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(420);
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
        SignUpDefController signUpContr = myLoader.<SignUpDefController>getController();
        signUpContr.initStage(primaryStage);
        primaryStage.getScene().setRoot(root);    }

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
            try{
                FXMLLoader myLoader = new FXMLLoader(getClass().getResource("../view/MainTest.fxml"));
                Parent root = (Parent) myLoader.load();
                MainTestController mtCtrl = myLoader.<MainTestController>getController();
                mtCtrl.initStage(primaryStage, user);
                primaryStage.getScene().setRoot(root);
                throw new Exception("error");
            }
            catch(Exception e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                
                
                ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");
                
                 Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image("file:src/resources/navegacion.png"));
                alert.getDialogPane().getStylesheets().add(getClass().getResource("../resources/alerts.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("customAlert");
                alert.setTitle("Exception Dialog");
                alert.setHeaderText("An error has occurred");
                alert.setContentText("An error has occurred");
                
                
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
                
                alert.getDialogPane().setExpandableContent(expContent);
                alert.showAndWait();
            }
            
        }

    }    

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        KeyCode keyPressed = event.getCode();
        try{
        if(keyPressed == KeyCode.ENTER) handleEnterPressed(new ActionEvent());
        }
        catch(Exception e) {}
    }

    private void setSide(double w, double h)
    {
        System.out.println("w: " + w + "\th: " + h);
        if(w > 650){
            side.setMinWidth( w * 0.3 );
            side.setMaxWidth( w * 0.3 );
            //logInLabel.setPadding(new Insets(0,0,w*0.05,0));
            logInLabel.setFont(new Font(logInLabel.getFont().getName(), 40 + (int)w*0.0025));
                    
        }else{
            side.setMinWidth(180);
            side.setMaxWidth(180);
            logInLabel.setFont(new Font(logInLabel.getFont().getName(), 40));
            //logInLabel.setPadding(new Insets(0,0,0,0));
        if(h > 500)
        {
            System.out.println(25+ h*0.05);
            side.setSpacing(15+ h*0.045);
        }
        else{
            side.setSpacing(15);
            
        }
        }
    }
}
