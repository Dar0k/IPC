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
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.User;

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
    private Button confirmateLogOutButton;
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
        primaryStage.setTitle("Log Out");
        sidebarController.primaryStage = primaryStage;
        user = us;
        sidebarController.setUser(user);
    }
    
    public void updateSidebar()
    {
        sidebarController.clearSidebar();
        sidebarController.boldLogOutButton();
    }
  
    @FXML
    private void handleConfirmateLogOut(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LogInDef.fxml"));
        Parent root = (Parent) loader.load();
        LogInDefController mainTCtrl = loader.<LogInDefController>getController();
        mainTCtrl.initStage(primaryStage);
        primaryStage.getScene().setRoot(root);
    }
    
}
