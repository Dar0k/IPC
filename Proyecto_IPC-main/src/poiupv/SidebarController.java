/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import model.User;

/**
 * FXML Controller class
 *
 * @author DROCPER
 */
public class SidebarController implements Initializable {

    public Stage primaryStage;
    private String prevTitle;
    private Scene prevScene;
    @FXML
    protected Button testButton;
    @FXML
    protected Button resultsButton;
    @FXML
    protected Button profileButton;
    @FXML
    protected Button logOutButton;
    @FXML
    private VBox sb;
    
    User user;
    double minW = 105;
    double maxW = 250;
    double difW = maxW-minW;
    double maxSize = 30;
    double minSize = 15;
    double difSize = maxSize-minSize;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
   /* public void initStage(Stage stage, User us){
        pri
    }*/
    protected void setUser(User us){
        user = us;
    }
    
    protected void clearSidebar(double w) {
        double sizeAct = maxW - w;
        double per = 1-(sizeAct/difW);
        Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, minSize + (difSize*per));
        testButton.setFont(font);
        testButton.setMinWidth(w-(w*0.15));
        testButton.setMaxWidth(w-(w*0.15));
        resultsButton.setFont(font);
        resultsButton.setMinWidth(w-(w*0.15));
        resultsButton.setMaxWidth(w-(w*0.15));
        profileButton.setFont(font);
        profileButton.setMinWidth(w-(w*0.15));
        profileButton.setMaxWidth(w-(w*0.15));
        logOutButton.setFont(font);
        logOutButton.setMinWidth(w-(w*0.15));
        logOutButton.setMaxWidth(w-(w*0.15));
        
    }
    
    protected void boldTestButton(double w) {;
        double sizeAct = maxW - w;
        double per = 1-(sizeAct/difW);        
        Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, minSize + (difSize*per));
        testButton.setFont(font);
        testButton.setMinWidth(w-(w*0.15));
        testButton.setMaxWidth(w-(w*0.15));
    }
    
    protected void boldResultsButton(double w) {
        double sizeAct = maxW - w;
        double per = 1-(sizeAct/difW);
        Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, minSize + (difSize*per));
        resultsButton.setFont(font);
        resultsButton.setMinWidth(w-(w*0.15));
        resultsButton.setMaxWidth(w-(w*0.15));
    }
    
    protected void boldProfileButton(double w) {
        double sizeAct = maxW - w;
        double per = 1-(sizeAct/difW);
        Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, minSize + (difSize*per));
        profileButton.setFont(font);
        profileButton.setMinWidth(w-(w*0.15));
        profileButton.setMaxWidth(w-(w*0.15));
    }
    
    protected void boldLogOutButton(double w) {
        double sizeAct = maxW - w;
        double per = 1-(sizeAct/difW);
        Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, minSize + (difSize*per));
        logOutButton.setFont(font);
        logOutButton.setMinWidth(w-(w*0.15));
        logOutButton.setMaxWidth(w-(w*0.15));
    }

    @FXML
    private void handleTest(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainTest.fxml"));
        try{
            Parent root = (Parent) loader.load();
            MainTestController mTCtrl = loader.<MainTestController>getController();
            mTCtrl.initStage(primaryStage, user);
            primaryStage.getScene().setRoot(root);
            primaryStage.show();
        }
        catch(IOException e)
        {
            Alert alertErr = new Alert(Alert.AlertType.ERROR);

            ((Button) alertErr.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");

            Stage alertStageErr = (Stage) alertErr.getDialogPane().getScene().getWindow();
            alertStageErr.getIcons().add(new Image("file:src/resources/navegacion.png"));
            alertErr.getDialogPane().getStylesheets().add(getClass().getResource("../resources/alerts.css").toExternalForm());
            alertErr.getDialogPane().getStyleClass().add("customAlert");
            alertErr.setTitle("Exception Dialog");
            alertErr.setHeaderText("An error has occurred");
            alertErr.setContentText("An unexpected error occurred trying to load the main test scene. Please try again");


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
    //TO COMPLETE
    private void handleResult(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainResults.fxml"));
        try{
            Parent root = (Parent) loader.load();
            MainResultsController mRCtrl = loader.<MainResultsController>getController();
            mRCtrl.initStage(primaryStage, user);
            primaryStage.getScene().setRoot(root);
            primaryStage.show();
            
        }
        catch(IOException e)
        {
            Alert alertErr = new Alert(Alert.AlertType.ERROR);

            ((Button) alertErr.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");

            Stage alertStageErr = (Stage) alertErr.getDialogPane().getScene().getWindow();
            alertStageErr.getIcons().add(new Image("file:src/resources/navegacion.png"));
            alertErr.getDialogPane().getStylesheets().add(getClass().getResource("../resources/alerts.css").toExternalForm());
            alertErr.getDialogPane().getStyleClass().add("customAlert");
            alertErr.setTitle("Exception Dialog");
            alertErr.setHeaderText("An error has occurred");
            alertErr.setContentText("An unexpected error occurred trying to load the results. Please try again");


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
    private void handleProfile(ActionEvent event)  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainProfile.fxml"));
        try{
            Parent root = (Parent) loader.load();
            MainProfileController mPCtrl = loader.<MainProfileController>getController();
            mPCtrl.initStage(primaryStage, user);
            primaryStage.getScene().setRoot(root);
            primaryStage.show();
        }
        catch(IOException e)
        {
            Alert alertErr = new Alert(Alert.AlertType.ERROR);

            ((Button) alertErr.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");

            Stage alertStageErr = (Stage) alertErr.getDialogPane().getScene().getWindow();
            alertStageErr.getIcons().add(new Image("file:src/resources/navegacion.png"));
            alertErr.getDialogPane().getStylesheets().add(getClass().getResource("../resources/alerts.css").toExternalForm());
            alertErr.getDialogPane().getStyleClass().add("customAlert");
            alertErr.setTitle("Exception Dialog");
            alertErr.setHeaderText("An error has occurred");
            alertErr.setContentText("An unexpected error occurred trying to load the profile. Please try again");


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
    private void handleLogOut(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainLogOut.fxml"));
        try{
            Parent root = (Parent) loader.load();
            MainLogOutController mLCtrl = loader.<MainLogOutController>getController();
            mLCtrl.initStage(primaryStage, user);
            primaryStage.getScene().setRoot(root);
            primaryStage.show();
        }
        catch(IOException e)
        {
            Alert alertErr = new Alert(Alert.AlertType.ERROR);

            ((Button) alertErr.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");

            Stage alertStageErr = (Stage) alertErr.getDialogPane().getScene().getWindow();
            alertStageErr.getIcons().add(new Image("file:src/resources/navegacion.png"));
            alertErr.getDialogPane().getStylesheets().add(getClass().getResource("../resources/alerts.css").toExternalForm());
            alertErr.getDialogPane().getStyleClass().add("customAlert");
            alertErr.setTitle("Exception Dialog");
            alertErr.setHeaderText("An error has occurred");
            alertErr.setContentText("An unexpected error occurred trying to load the log out scene. Please try again");


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

    private void OnMouseHoverExited(MouseEvent event) {
        Button source = (Button) event.getSource();
        if (source.equals(logOutButton) || source.equals(testButton) || source.equals(resultsButton)|| source.equals(profileButton)) {
            source.setStyle("-fx-background-color: transparent; -fx-background-radius: 50");
        }
    }

    @FXML
    private void OnMouseHoverEnter(MouseEvent event) {
        Button source = (Button) event.getSource();
        if (source.equals(logOutButton) || source.equals(testButton) || source.equals(resultsButton)|| source.equals(profileButton)) {
            source.setStyle("-fx-background-color: rgb(200,200,200,0.4); -fx-background-radius: 50");
        }
    }

}
