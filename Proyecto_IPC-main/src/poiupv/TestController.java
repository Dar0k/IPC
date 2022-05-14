/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Answer;
import model.Problem;
import model.User;

/**
 * FXML Controller class
 *
 * @author robyandy
 */
public class TestController implements Initializable {

    private Group zoomGroup;
    private Stage primaryStage;
    private Scene previousScene;
    private String previousTitle;
    private User user;
    private Problem problem;
    private ListView<Poi> map_listview;
    
    private Slider zoom_slider;
    private MenuButton map_pin;
    private MenuItem pin_info;
    
    
    @FXML
    private Label questionNumber;
    @FXML
    private Label questionLabel;
    @FXML
    private VBox vboxRadio;
    @FXML
    private RadioButton radioButtonA;
    @FXML
    private ToggleGroup rad;
    @FXML
    private RadioButton radioButtonB;
    @FXML
    private RadioButton radioButtonC;
    @FXML
    private RadioButton radioButtonD;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private ImageView map;
    @FXML
    private Button goBackButton;
    @FXML
    private Button sendButton;
    @FXML
    private SidebarController sidebarController;
    @FXML
    private VBox sidebar; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //==========================================================
        // inicializamos el slider y enlazamos con el zoom
        
        sidebarController.initialize(url, rb);
        updateSidebar();
        
        //=========================================================================
        //Envuelva el contenido de scrollpane en un grupo para que 
        //ScrollPane vuelva a calcular las barras de desplazamiento tras el escalado
        Group contentGroup = new Group();
        zoomGroup = new Group();
        contentGroup.getChildren().add(zoomGroup);
        zoomGroup.getChildren().add(map_scrollpane.getContent());
        map_scrollpane.setContent(contentGroup);
    }
    
    public void updateSidebar()
    {
        sidebarController.clearSidebar();
        sidebarController.boldTestButton();
    }
    
    void initStage(Stage stage, User us, Problem prob, int number)
    {
        primaryStage = stage;
        previousScene = stage.getScene();
        previousTitle = stage.getTitle();
        user = us;
        problem = prob;
        questionLabel.setText(problem.getText());
        List<Answer> list = problem.getAnswers();
        RadioButton [] buttons = {radioButtonA, radioButtonB, radioButtonC, radioButtonD};
        List<Integer> pool = new ArrayList<Integer>();
        pool.add(0);
        pool.add(1);
        pool.add(2);
        pool.add(3);
        Random generator = new Random();
        
        for(int i = 4; i > 1; i--)
        {
            int random = generator.nextInt(1000);
            buttons[i-1].setText(list.get(pool.remove(random % i)).getText());
        }
        buttons[0].setText(list.get(pool.get(0)).getText());
        
        questionNumber.setText("Problem " + number);
        
        /*for(int i = 0; i < 4; i++)
        {
            buttons[i].textProperty().bind(map.getRad(i).textProperty());
            
        }
        
        for(int i = 0; i < 4; i++)
        {
            buttons[i].selectedProperty().bind(map.getRad(i).selectedProperty());
            
        }*/
    }

    void zoomIn(ActionEvent event) {
        //================================================
        // el incremento del zoom dependerá de los parametros del 
        // slider y del resultado esperado
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal += 0.1);
    }

    void zoomOut(ActionEvent event) {
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal + -0.1);
    }
    
    // esta funcion es invocada al cambiar el value del slider zoom_slider
    private void zoom(double scaleValue) {
        //===================================================
        //guardamos los valores del scroll antes del escalado
        double scrollH = map_scrollpane.getHvalue();
        double scrollV = map_scrollpane.getVvalue();
        //===================================================
        // escalamos el zoomGroup en X e Y con el valor de entrada
        zoomGroup.setScaleX(scaleValue);
        zoomGroup.setScaleY(scaleValue);
        //===================================================
        // recuperamos el valor del scroll antes del escalado
        map_scrollpane.setHvalue(scrollH);
        map_scrollpane.setVvalue(scrollV);
    }

    void listClicked(MouseEvent event) {
        Poi itemSelected = map_listview.getSelectionModel().getSelectedItem();

        // Animación del scroll hasta la posicion del item seleccionado
        double mapWidth = zoomGroup.getBoundsInLocal().getWidth();
        double mapHeight = zoomGroup.getBoundsInLocal().getHeight();
        double scrollH = itemSelected.getPosition().getX() / mapWidth;
        double scrollV = itemSelected.getPosition().getY() / mapHeight;
        final Timeline timeline = new Timeline();
        final KeyValue kv1 = new KeyValue(map_scrollpane.hvalueProperty(), scrollH);
        final KeyValue kv2 = new KeyValue(map_scrollpane.vvalueProperty(), scrollV);
        final KeyFrame kf = new KeyFrame(Duration.millis(500), kv1, kv2);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        // movemos el objto map_pin hasta la posicion del POI
        double pinW = map_pin.getBoundsInLocal().getWidth();
        double pinH = map_pin.getBoundsInLocal().getHeight();
        map_pin.setLayoutX(itemSelected.getPosition().getX());
        map_pin.setLayoutY(itemSelected.getPosition().getY());
        pin_info.setText(itemSelected.getDescription());
        map_pin.setVisible(true);
    }
    
    private void cerrarAplicacion(ActionEvent event) {
        ((Stage)zoom_slider.getScene().getWindow()).close();
    }

    @FXML
    private void mapMouseRelease(MouseEvent event) {
        map.setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void mapMousePress(MouseEvent event) {
        map.setCursor(Cursor.MOVE);
        
    }

    @FXML
    private void muestraPosicion(MouseEvent event) {
    }

    @FXML
    private void handleGoBackButton(ActionEvent event) throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Go Back");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("The test will not be submitted and all progress will be lost");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainTest.fxml"));
            Parent root = (Parent) loader.load();
            MainTestController controller = loader.<MainTestController>getController();
            controller.initStage(primaryStage, user);
            primaryStage.getScene().setRoot(root);
            
        } else {
            System.out.println("CANCEL");
        }
    }

    @FXML
    private void handleSendButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Send");
        alert.setHeaderText("Are you sure you want to send");
        alert.setContentText("");
        Optional<ButtonType> result = alert.showAndWait();
    }      
}
