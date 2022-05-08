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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

/**
 * FXML Controller class
 *
 * @author DROCPER
 */
public class MainResultsController implements Initializable {
    
    private Stage primaryStage;
    private Scene prevScene;
    private String prevTitle;
    
    @FXML
    private Button continueButton;
    @FXML
    private Button randomButton;
    @FXML
    private Button listButton;
    @FXML
    private VBox sidebar;
    @FXML
    private SidebarController sidebarController;

    User user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sidebarController.initialize(url, rb);
        updateSidebar();
    }
    
    public void initStage(Stage stage, User us)
    {
        primaryStage = stage;
        primaryStage.setTitle("Results");
        sidebarController.primaryStage = primaryStage;
        user = us;
        sidebarController.setUser(user);
    }
    
    public void updateSidebar()
    {
        sidebarController.clearSidebar();
        sidebarController.boldResultsButton();
    }
    

    @FXML
    private void handleContinue(ActionEvent event) {
    }

    @FXML
    private void handleRandom(ActionEvent event) {
    }

    @FXML
    private void handleList(ActionEvent event) {
    }
    
}
