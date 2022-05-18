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
import model.Navegacion;
import model.Problem;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author DROCPER
 */
public class TestListController implements Initializable {
    
    private Stage primaryStage;
    private Scene prevScene;
    private String prevTitle;
    
    @FXML
    private VBox sidebar;
    @FXML
    private SidebarController sidebarController;
    @FXML
    private ListView problemsList;
    @FXML
    public Label problemDescription;
    
    private ArrayList<Problem> problemsArrayList;
    
    User user;
    Navegacion navegation;
    
    ObservableList problems;
    @FXML
    private Button cancelButton;
    @FXML
    private Button SelectButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sidebarController.initialize(url, rb);
        
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
        loadProblems();
        calcSideBar(primaryStage.getWidth());
        primaryStage.widthProperty().addListener((obs, oldv, newv)->{
            calcSideBar((double) newv);
        });
    }
    
    public void updateSidebar(double w)
    {        
        sidebarController.clearSidebar(w);
        sidebarController.boldTestButton(w);
    }
    
    public void loadProblems()
    {
        try {
            navegation = Navegacion.getSingletonNavegacion();
        } catch (Exception e) {
            System.out.println(e);
        }
        int count = 1;
        problemsArrayList = new ArrayList<Problem>();
        for (Problem problem: navegation.getProblems()) {
            problemsArrayList.add(problem);
            problemsList.getItems().add("Problem " + count);
            count++;
        }
        problemsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                SelectButton.setDisable(false);
                int index = problemsList.getSelectionModel().getSelectedIndex();
                problemDescription.setText(problemsArrayList.get(index).getText());
            }
        });
    }

    @FXML
    private void handleCancelButton(ActionEvent event) throws Exception {
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainTest.fxml"));
        Parent root = (Parent) loader.load();
        MainTestController mTCtrl = loader.<MainTestController>getController();
        mTCtrl.initStage(primaryStage, user);
        primaryStage.getScene().setRoot(root);
        primaryStage.show();
        
        
        
    }

    @FXML
    private void handleSelectButton(ActionEvent event) throws Exception {
        System.out.println("test");
        int index = problemsList.getSelectionModel().getSelectedIndex();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Test.fxml"));
        Parent root = (Parent) loader.load();
        TestController mTCtrl = loader.<TestController>getController();
        mTCtrl.initStage(primaryStage, user, problemsArrayList.get(index), index+1);
        primaryStage.getScene().setRoot(root);
        primaryStage.show();
    }
    
    public void calcSideBar (double w) {
        System.out.println("main: " + w);            
        if(w < 1000){
            sidebar.setMinWidth( w * 0.25 );
            sidebar.setMaxWidth( w * 0.25 );
            updateSidebar(w * 0.25);
        }else{
            sidebar.setMinWidth(250);
            sidebar.setMaxWidth(250);
            updateSidebar(250);
        }     
    }

}
