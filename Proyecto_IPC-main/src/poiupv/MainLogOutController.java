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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
    @FXML
    private Label label;
    @FXML
    private VBox aux;
    
    User user;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sidebarController.initialize(url, rb);
        updateSidebar();
        aux.widthProperty().addListener((obs, oldv, newv)-> {
            System.out.println(newv);
            /*if((double)newv < 350 ){
                Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 16);
                label.setFont(font);
            }else if((double)newv>700){
                Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 42);
                label.setFont(font);
            }else{
                Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 24);
                label.setFont(font);
            }*/
            double temp = (16*(double)newv)/276;
            
            Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, temp);
            label.setFont(font);
            //279  -> 16
            //466  -> 24
            //841  -> 42
        });
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
