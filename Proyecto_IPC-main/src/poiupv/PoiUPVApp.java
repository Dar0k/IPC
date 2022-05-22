/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Answer;
import model.Problem;
import model.User;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 *
 * @author jose
 */
public class PoiUPVApp extends Application {
    
    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LogInDef.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Map.fxml"));
        try{
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../view/alerts.css").toExternalForm());
            LogInDefController logInCtrl = loader.<LogInDefController>getController();
            //MapController logInCtrl = loader.<MapController>getController();
            Problem problem = new Problem("Problem", new Answer("Answer1", false), new Answer("Answer2", false), new Answer("Answer3", false), new Answer("Answer4", true));

            //logInCtrl.initStage(stage,null, problem, 1);
            stage.getIcons().add(new Image("file:src/resources/navegacion.png"));
            stage.setHeight(460);
            stage.setWidth(600);
            logInCtrl.initStage(stage);
            stage.setTitle("Log in");
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e)
        {
            Alert alertErr = new Alert(Alert.AlertType.ERROR);

            ((Button) alertErr.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");

            Stage alertStageErr = (Stage) alertErr.getDialogPane().getScene().getWindow();
            alertStageErr.getIcons().add(new Image("file:src/resources/navegacion.png"));
            alertErr.getDialogPane().getStylesheets().add(getClass().getResource("../view/alerts.css").toExternalForm());
            alertErr.getDialogPane().getStyleClass().add("customAlert");
            alertErr.setTitle("Exception Dialog");
            alertErr.setHeaderText("An error has occurred");
            alertErr.setContentText("An unexpected error occurred trying to load the application. Please try again");


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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
