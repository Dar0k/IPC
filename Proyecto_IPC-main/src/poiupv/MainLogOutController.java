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
        
        calcFontSize(aux.getWidth());
        calcButtonSize(aux.getHeight(), aux.getWidth());
        aux.widthProperty().addListener((obs, oldv, newv)-> {
           calcFontSize(aux.getWidth());
           calcButtonSize(aux.getHeight(), aux.getWidth());
        });
        aux.heightProperty().addListener((obs, oldv, newv)-> {
           calcFontSize(aux.getWidth());
           calcButtonSize(aux.getHeight(), aux.getWidth());
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
    
    private void calcFontSize(double newv){
        System.out.println(newv);
        double temp = (16*(double)newv)/276;
        Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, temp);
        label.setFont(font);
    }
    
    private void calcButtonSize(double h, double w){
        double maxSpacing = 35;
        double minSpacing = 15;
        double maxHeight = 650;
        double minHeight = 375;
        double maxWidth = 800;
        double minWidth = 400;
        double width = w;
        double height = h;
        if(width < minWidth || height < minHeight) {
            Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 15);
            confirmateLogOutButton.setFont(font);
            aux.setSpacing(minSpacing);
        }else if (width > maxWidth || height > maxHeight){
            Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 30);
            confirmateLogOutButton.setFont(font);
            aux.setSpacing(maxSpacing);
        }else{
            double difSpacing = maxSpacing - minSpacing;
            double difW = maxWidth-minWidth;
            double difH = maxHeight-minHeight;
            double temp = difW + difH;
            double sizeAct = (maxWidth - width) + (maxHeight - height);
            double per = 1- (sizeAct/temp);
            double difTextSize = 30-15;
            Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 15 + difTextSize*per);
            confirmateLogOutButton.setFont(font);
            aux.setSpacing(minSpacing + (difSpacing*per));
        }    
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
