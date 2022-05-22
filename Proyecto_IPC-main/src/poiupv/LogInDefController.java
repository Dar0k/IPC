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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
    @FXML
    private Label uLabel;
    @FXML
    private Label pLabel;
    @FXML
    private ImageView eyeIm;

    public void initStage(Stage stage)
    {
        primaryStage = stage;
        width = primaryStage.getWidth();
        height = primaryStage.getHeight();
        primaryStage.widthProperty().addListener((obs, oldv, newv) -> {
             width = (double) newv;     
             //setSide(width, height);
             calcWSide(width);
        });
        primaryStage.heightProperty().addListener((obs, oldv, newv) ->{
             height = (double) newv;
             //setSide(width, height);
             calcHSide(height);        
        });
        primaryStage.setMinHeight(410);
        primaryStage.setMinWidth(420);
        
        calcWSide(600);
        calcHSide(450);
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
    private void handleSignUpClick(MouseEvent event) {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("../view/SignUpDef.fxml"));
        try{
            Parent root = (Parent) myLoader.load();
            SignUpDefController signUpContr = myLoader.<SignUpDefController>getController();
            signUpContr.initStage(primaryStage);
            primaryStage.getScene().setRoot(root);
        }
        catch(IOException e )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                
                
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");

            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/resources/navegacion.png"));
            alert.getDialogPane().getStylesheets().add(getClass().getResource("../view/alerts.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("customAlert");
            alert.setTitle("Exception Dialog");
            alert.setHeaderText("An error has occurred");
            alert.setContentText("An unexpected error has occurred when loading the scene. Please try again");


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

    @FXML
    private void handleEnterPressed(ActionEvent event)  {           
            
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
                
            }
            catch(IOException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                
                
                ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");
                
                 Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image("file:src/resources/navegacion.png"));
                alert.getDialogPane().getStylesheets().add(getClass().getResource("../view/alerts.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("customAlert");
                alert.setTitle("Exception Dialog");
                alert.setHeaderText("An error has occurred");
                alert.setContentText("An unexpected error has occurred when loading the scene. Please try again");
                
                
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
        if(keyPressed == KeyCode.ENTER) handleEnterPressed(new ActionEvent());       
    }
    
    public void calcWSide (double w) {
        System.out.println("width: " + w);   
       
        if(w<600){
            side.setPadding(new Insets(side.getPadding().getTop(),20,side.getPadding().getBottom(),20));
            side.setMinWidth(200);
            side.setMaxWidth(200);
            enterButton.setPrefWidth(100);
        }else if (w>=600 && w<1200){
            double act = 1200-w;
            int stageDif = 1200-600;
            double per = 1 - (act/stageDif);
            double horPad = 80-20;
            double buDif = 150-100;
            
            side.setPadding(new Insets(side.getPadding().getTop(), 25+(horPad*per), side.getPadding().getBottom(), 25+(horPad*per)));
            side.setMinWidth(w * 0.333);
            side.setMaxWidth(w * 0.333);
            enterButton.setPrefWidth(100 + (buDif*per));
        }else{
            side.setPadding(new Insets(side.getPadding().getTop(), 80, side.getPadding().getBottom(), 80));
            side.setMinWidth(w * 0.333);
            side.setMaxWidth(w * 0.333); 
            enterButton.setPrefWidth(150);
        }
    }
    
    public void calcHSide(double h){
        System.out.println("height: " + h);   
        if(h<475){
            Font fontTi = Font.font("Serif", FontWeight.NORMAL, FontPosture.REGULAR, 43);
            Font fontLa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12);
            Font fontTe = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12);
            Font fontEr = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12);
            Font fontSi = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 17);
            Font fontBu = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 15);
           
            logInLabel.setFont(fontTi);
            uLabel.setFont(fontLa);
            pLabel.setFont(fontLa);
            usernameField.setFont(fontTe);
            passwordField.setFont(fontTe);
            passwordTextField.setFont(fontTe);
            usernameError.setFont(fontEr);
            passwordError.setFont(fontEr);
            signUpLink.setFont(fontSi);
            enterButton.setFont(fontBu);
            side.setPadding(new Insets(25,side.getPadding().getRight(),25,side.getPadding().getLeft()));  
            side.setSpacing(25);
            eyeIm.setFitWidth(30);
            eyeIm.setFitHeight(16);
            passwordEyeLine.setStartX(30);
        }else if (h>=475 && h<700){
            double act = 700-h;
            int stageDif = 700-475;
            double per = 1 - (act/stageDif);
            double verPad = 80-25;
            double spaDif = 50-25;
            double fontTiDif = 60-43;
            double fontLaDif = 20-12;
            double fontTeDif = 20-12;
            double fontErDif = 20-12;
            double fontSiDif = 25-17;
            double fontBuDif = 25-17;
            double eyeXDif = 40-30;
            double eyeYDif = 20-16;
            double eyeLinDif = 40-30;
            
            Font fontTi = Font.font("Serif", FontWeight.NORMAL, FontPosture.REGULAR, 43 + (fontTiDif*per));
            Font fontLa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12 + (fontLaDif*per));
            Font fontTe = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12 + (fontTeDif*per));
            Font fontEr = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12 + (fontErDif*per));
            Font fontSi = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 17 + (fontSiDif*per));
            Font fontBu = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 15 + (fontBuDif*per));            
            
            logInLabel.setFont(fontTi);
            uLabel.setFont(fontLa);
            pLabel.setFont(fontLa);
            usernameField.setFont(fontTe);
            passwordField.setFont(fontTe);
            passwordTextField.setFont(fontTe);
            usernameError.setFont(fontEr);
            passwordError.setFont(fontEr);
            signUpLink.setFont(fontSi);
            enterButton.setFont(fontBu);            
            side.setPadding(new Insets( 25 + (verPad*per) , side.getPadding().getRight(), 25 + (verPad*per) , side.getPadding().getLeft()));
            side.setSpacing(25 + (spaDif*per));
            eyeIm.setFitWidth(30 + (eyeXDif*per));
            eyeIm.setFitHeight(16 + (eyeYDif*per));
            passwordEyeLine.setStartX(30 + (eyeLinDif*per));
        }else{
            Font fontTi = Font.font("Serif", FontWeight.NORMAL, FontPosture.REGULAR, 60);
            Font fontLa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 20);
            Font fontTe = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 20);
            Font fontEr = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 20);
            Font fontSi = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 25);
            Font fontBu = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 25);
           
            logInLabel.setFont(fontTi);
            uLabel.setFont(fontLa);
            pLabel.setFont(fontLa);
            usernameField.setFont(fontTe);
            passwordField.setFont(fontTe);
            passwordTextField.setFont(fontTe);
            usernameError.setFont(fontEr);
            passwordError.setFont(fontEr);
            signUpLink.setFont(fontSi);
            enterButton.setFont(fontBu);
            eyeIm.setFitWidth(40);
            eyeIm.setFitHeight(20);
            passwordEyeLine.setStartX(40);           
            side.setPadding(new Insets(80, side.getPadding().getRight(), 80, side.getPadding().getLeft()));
            side.setSpacing(50);
            
        }
    }
}
