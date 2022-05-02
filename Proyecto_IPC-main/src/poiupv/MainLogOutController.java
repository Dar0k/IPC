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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DROCPER
 */
public class MainLogOutController implements Initializable {
    
    private Stage primaryStage;
    private String prevTitle;
    private Scene prevScene;
    @FXML
    private Button testButton;
    @FXML
    private Button resultButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Button confirmateLogOutButton;

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
    }
    

    @FXML
    private void handleTestButton(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainTest.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        MainTestController mainTCtrl = loader.<MainTestController>getController();
        mainTCtrl.initStage(primaryStage);
        primaryStage.setScene(scene);
        
    }

    @FXML
    //TO COMPLETE
    private void handleResultButton(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainTest.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        MainTestController mainTCtrl = loader.<MainTestController>getController();
        mainTCtrl.initStage(primaryStage);
        primaryStage.setScene(scene);
    }

    @FXML
    private void handleProfileButton(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainProfile.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        MainProfileController mainPController = loader.<MainProfileController>getController();
        mainPController.initStage(primaryStage);
        primaryStage.setScene(scene);
    }
    

    @FXML
    private void handleConfirmateLogOut(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LogInDef.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        LogInDefController mainTCtrl = loader.<LogInDefController>getController();
        mainTCtrl.initStage(primaryStage);
        primaryStage.setScene(scene);
    }
    
}
