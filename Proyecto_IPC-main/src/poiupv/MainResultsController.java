/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.net.URL;
import java.time.LocalDate;
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
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

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
    private VBox sidebar;
    @FXML
    private SidebarController sidebarController;
    @FXML
    private DatePicker datePicker;
    
    private ArrayList<Session> l;
    private ObservableList<Session> data = null;
    User user;
    Session session;
    Navegacion navegation;
    @FXML
    private Label birthLabel;
    @FXML
    private Label currentSessionLabel;
    @FXML
    private ListView<Session> listView;
    @FXML
    private TableView<Session> tableView;
    private TableColumn<Session, String> date;
    @FXML
    private TableColumn<Session, String> timestamp;
    @FXML
    private TableColumn<Session, String> hits;
    @FXML
    private TableColumn<Session, String> faults;
    @FXML
    private TableColumn<Session, String> dateT;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sidebarController.initialize(url, rb);
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
        l = new ArrayList<Session>();
        data = FXCollections.observableArrayList(l);;
        loadResults();
        currentSessionLabel.setText("Current session:\t" + MainLogOutController.hits + " hits\t" + 
                                      MainLogOutController.faults + " faults");
        
        datePicker.setDayCellFactory((DatePicker picker) -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) > 0 );
                } 
            };
        });
        tableView.widthProperty().addListener((obs, oldv, newv) -> {            
            hits.setMinWidth((double)newv*0.2);
            hits.setMaxWidth((double)newv*0.2);
            faults.setMinWidth((double)newv*0.2);            
            faults.setMaxWidth((double)newv*0.2);
            dateT.setMinWidth((double)newv*0.25);           
            dateT.setMaxWidth((double)newv*0.25);  
            timestamp.setMinWidth((double)newv*0.35);           
            timestamp.setMaxWidth((double)newv*0.35);           
        });
        primaryStage.heightProperty().addListener((obs, oldv, newv)-> {
            System.out.println("prin: "+ newv);;
            calcSize((double)newv);
        });
        calcSize(primaryStage.getHeight());
        
        calcSideBar(primaryStage.getWidth());
        primaryStage.widthProperty().addListener((obs, oldv, newv)->{
            calcSideBar((double) newv);
        });
    }
    public void updateSidebar(double w)
    {
        sidebarController.clearSidebar(w);
        sidebarController.boldResultsButton(w);
    }
    
    private void setupTable()
    {
        hits.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getHits() + ""));
        faults.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFaults()+ ""));
        timestamp.setCellValueFactory(param ->
        {
            String hour = "";
            String minute = "";
            String second = "";
            if((int) (param.getValue().getTimeStamp().getHour() / 10) == 0) hour = "0";
            if((int) (param.getValue().getTimeStamp().getMinute() / 10) == 0) minute = "0";
            if((int) (param.getValue().getTimeStamp().getSecond() / 10) == 0) second = "0";

            return new SimpleStringProperty(hour + param.getValue().getTimeStamp().getHour() + ":" + minute + param.getValue().getTimeStamp().getMinute() + ":" + second + param.getValue().getTimeStamp().getSecond());
                });
        dateT.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLocalDate()+ ""));
    }
    
    
    @FXML
    public void loadResults()
    {
        tableView.getItems().clear();
        try{
            List<Session> sessions = user.getSessions();
            System.out.println(sessions.get(0).toString());
            for (Session sess: sessions) {
                if ((datePicker.getValue() != null && (sess.getLocalDate().isAfter(datePicker.getValue()) ||sess.getLocalDate().isEqual(datePicker.getValue()) )) || datePicker.getValue() == null) {
                    data.add(sess);
                }
            }
            tableView.setItems(data);
        }
        catch(NullPointerException e)
        {}
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
    
    private void calcSize (double newv){    
        if((double)newv < 400){
            Font fontLa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12);
            birthLabel.setFont(fontLa);
            datePicker.setStyle("-fx-font-size: "+(12));
        }  else if((double)newv >= 400 && (double)newv <530){
            double act = 530-(double)newv;
            int stageDif = 530-375;
            double per = 1 - (act/stageDif);
            int fontLaDif = 16 - 12;
            Font fontLa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12 + (fontLaDif*per));
            birthLabel.setFont(fontLa);
            datePicker.setStyle("-fx-font-size: "+(12.0 + (fontLaDif*per)));
        }else{
            Font fontLa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 16);            
            birthLabel.setFont(fontLa);
            datePicker.setStyle("-fx-font-size: "+ 16);      
        }
    }

}
