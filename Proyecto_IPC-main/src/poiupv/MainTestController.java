/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.net.URL;
import java.util.List;
import java.util.Random;
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
import model.Navegacion;
import model.Problem;
import model.User;

/**
 * FXML Controller class
 *
 * @author DROCPER
 */
public class MainTestController implements Initializable {
    
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
        prevScene = stage.getScene();
        prevTitle = stage.getTitle();
        primaryStage.setTitle("MAIN");
        sidebarController.primaryStage = primaryStage;
        user = us;
        sidebarController.setUser(user);
        primaryStage.setMinHeight(375);
        primaryStage.setMinWidth(420);
        
        
    }
    
    public void updateSidebar()
    {        
        sidebarController.clearSidebar();
        sidebarController.boldTestButton();
    }
    

    @FXML
    private void handleContinue(ActionEvent event) {
    }

    @FXML
    private void handleRandom(ActionEvent event) throws Exception {
        Navegacion navegation = Navegacion.getSingletonNavegacion();
        List<Problem> problemas = navegation.getProblems();
        System.out.println(problemas.size());
        Random generator = new Random();
        int index = generator.nextInt(1000) % (problemas.size() -1);
        Problem p = problemas.get(index);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Test.fxml"));        
        Parent root = (Parent) loader.load();
        TestController controller = loader.<TestController>getController();
        System.out.println(user);
        controller.initStage(primaryStage, user, p, index+1);
        primaryStage.getScene().setRoot(root);
     }

    @FXML
    private void handleList(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TestList.fxml"));
        Parent root = (Parent) loader.load();
        TestListController mTCtrl = loader.<TestListController>getController();
        mTCtrl.initStage(primaryStage, user);
        primaryStage.getScene().setRoot(root);
        primaryStage.show();
    }
    
}
