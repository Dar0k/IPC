/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.Session;
import model.Navegacion;
import javafx.scene.control.DatePicker;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<Session> tableResults;
    @FXML
    public TableColumn<Session, Integer> hits;
    @FXML
    public TableColumn<Session, Integer> faults;
    @FXML
    public TableColumn<Session, LocalDateTime> timestamp;

    User user;
    Session session;
    Navegacion navegation;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sidebarController.initialize(url, rb);
        updateSidebar();
        setupTable();
    }
    
    public void initStage(Stage stage, User us)
    {
        try {            
            navegation = Navegacion.getSingletonNavegacion();
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(LogInDefController.class.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage = stage;
        primaryStage.setTitle("Results");
        sidebarController.primaryStage = primaryStage;
        user = us;
        sidebarController.setUser(user);
        loadResults();
    }
    
    public void updateSidebar()
    {
        sidebarController.clearSidebar();
        sidebarController.boldResultsButton();
    }
    
    private void setupTable()
    {
        hits.setCellValueFactory(new PropertyValueFactory<>("hits"));
        faults.setCellValueFactory(new PropertyValueFactory<>("faults"));
        timestamp.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));
    }
    
    public void loadResults()
    {
        tableResults.getItems().clear();
        List<Session> sessions = user.getSessions();
        for (Session sess: sessions) {
            if ((datePicker.getValue() != null && sess.getLocalDate().isAfter(datePicker.getValue())) || datePicker.getValue() == null) {
                tableResults.getItems().add(sess);
            }
        }
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
