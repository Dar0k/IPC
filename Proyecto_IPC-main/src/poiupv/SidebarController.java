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
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
    protected void clearSidebar() {
        Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 15);
        testButton.setFont(font);
        resultsButton.setFont(font);
        profileButton.setFont(font);
        logOutButton.setFont(font);
    }
    
    protected void boldTestButton() {
        Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 15);
        testButton.setFont(font);
    }
    
    protected void boldResultsButton() {
        Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 15);
        resultsButton.setFont(font);
    }
    
    protected void boldProfileButton() {
        Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 15);
        profileButton.setFont(font);
    }
    
    protected void boldLogOutButton() {
        Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 15);
        logOutButton.setFont(font);
    }

    @FXML
    private void handleTest(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainTest.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        MainTestController mTCtrl = loader.<MainTestController>getController();
        mTCtrl.initStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    //TO COMPLETE
    private void handleResult(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainResults.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        MainResultsController mRCtrl = loader.<MainResultsController>getController();
        mRCtrl.initStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void handleProfile(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainProfile.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        MainProfileController mPCtrl = loader.<MainProfileController>getController();
        mPCtrl.initStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void handleLogOut(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainLogOut.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        MainLogOutController mLCtrl = loader.<MainLogOutController>getController();
        mLCtrl.initStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
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
