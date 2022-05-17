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
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DROCPER
 */
public class MapController implements Initializable {
    private Group zoomGroup;
    private Stage primaryStage;
    @FXML
    private ToggleGroup group1;
    @FXML
    private Label questionLabel;
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
    private BorderPane map;
    @FXML
    private ToggleButton dot;
    @FXML
    private ToggleButton line;
    @FXML
    private ToggleButton arc;
    @FXML
    private ToggleButton text;
    @FXML
    private ToggleButton eraser;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Region reg1;
    @FXML
    private Button trash;
    @FXML
    private Region reg2;
    @FXML
    private Button quest;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private ImageView map1;
    @FXML
    private Button zoomInButton;
    @FXML
    private Button zoomOutButton;
    @FXML
    private Button fullscreen;
    private double zoomVal = 1;
    @FXML
    private VBox vboxQuest;
    @FXML
    private SplitPane splitPane;
    private Node nodeQuest;
    private boolean questActive = true;
    private double[] dividerPos;
    private String buttonSelection = "";
    private Line linePainting;
    private Circle circle;
    private double startXArc;
    private TextField textField;
    private boolean textDone = true;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    
    public void initStage(Stage stage)
    {
        primaryStage = stage;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        map1.setVisible(true);
        Group contentGroup = new Group();
        zoomGroup = new Group();
        contentGroup.getChildren().add(zoomGroup);
        zoomGroup.getChildren().add(map_scrollpane.getContent());
        map_scrollpane.setContent(contentGroup);
        
        
    }
    
    void zoomIn(ActionEvent event) {
        //================================================
        // el incremento del zoom dependerá de los parametros del 
        // slider y del resultado esperado
        /*double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal += 0.1);*/
    }

    void zoomOut(ActionEvent event) {
        /*double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal + -0.1);*/
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
    protected RadioButton getRad(int select)
    {
        switch(select)
        {
            case 0:
                return radioButtonA;
            case 1:
                return radioButtonB;
            case 2:
                return radioButtonC;
            case 3:
                return radioButtonD;
            default:
                return null;
        }
        
    }



    @FXML
    private void mapMouseRelease(MouseEvent event) {
    }


    @FXML
    private void muestraPosicion(MouseEvent event) {
    }

    @FXML
    private void handleDot(ActionEvent event) {
        buttonSelection = (buttonSelection.equals("dot")) ? "" : "dot"; 
    }

    @FXML
    private void handleLine(ActionEvent event) {
        buttonSelection = (buttonSelection.equals("line")) ? "" : "line"; 
    }

    @FXML
    private void handleArc(ActionEvent event) {
        buttonSelection = (buttonSelection.equals("arc")) ? "" : "arc"; 

    }

    @FXML
    private void handleText(ActionEvent event) {
        buttonSelection = (buttonSelection.equals("text")) ? "" : "text"; 

    }

    @FXML
    private void handleEraser(ActionEvent event) {
        buttonSelection = (buttonSelection.equals("eraser")) ? "" : "eraser"; 
    }

    @FXML
    private void handleColorPick(ActionEvent event) {
        
    }

    @FXML
    private void handleTrash(ActionEvent event) {
        zoomGroup.getChildren().retainAll(zoomGroup.getChildren().get(0));
    }

    @FXML
    private void handleZoomInButton(ActionEvent event) {
        
        if(zoomVal < 2.5)
        {
            zoomVal +=0.1;
            zoom(zoomVal);
        }
    }

    @FXML
    private void handleZoomOutButton(ActionEvent event) {
        
        if(zoomVal > 0.2)
        {
            zoomVal -= 0.1;
            zoom(zoomVal);
        }
        
    }

    @FXML
    private void handleFullScreen(ActionEvent event) {
    }

    @FXML
    private void handleQuest(ActionEvent event) {
        if(questActive)
        {
            dividerPos = splitPane.getDividerPositions();
            nodeQuest = splitPane.getItems().get(1);
            splitPane.getItems().remove(nodeQuest);
            questActive = false;
        }
        else
        {
            splitPane.getItems().add(nodeQuest);
            questActive = true;
            splitPane.setDividerPositions(dividerPos);
            
        }
    }

    @FXML
    private void handleMouseDrag(MouseEvent event) {
        switch(buttonSelection)
        {
            case "dot":
                break;
            case "arc":
                double radio = Math.abs(event.getX() - startXArc);
                circle.setRadius(radio);
                break;
            case "line":
                linePainting.setEndX(event.getX());
                linePainting.setEndY(event.getY());
                event.consume();
                
                break;
            case "text":
                break;
            default:
                break;
                
        }
        
        
        
        
    }

    @FXML
    private void handleMapMousePress(MouseEvent event) {
        
        switch(buttonSelection)
        {
            case "dot":
                Circle dot = new Circle(event.getX(), event.getY(), 10);
                zoomGroup.getChildren().add(dot);
                dot.setOnMousePressed(c -> removeElement(dot));
                break;
            case "arc":
                circle = new Circle(event.getX(), event.getY(), 10);
                circle.setStroke(Color.RED);
                circle.setFill(Color.TRANSPARENT);
                startXArc = event.getX();
                zoomGroup.getChildren().add(circle);
                circle.setOnMousePressed(c -> removeElement(circle));
                break;
            case "line":
                linePainting = new Line(event.getX(), event.getY(), event.getX(), event.getY());
                linePainting.setStrokeWidth(10);
                zoomGroup.getChildren().add(linePainting);
                linePainting.setOnMousePressed(c -> removeElement(linePainting));
                linePainting.setOnContextMenuRequested(eventContext -> {
                    ContextMenu menuContext = new ContextMenu();
                    MenuItem deleteItem = new MenuItem("Delete");
                    menuContext.getItems().add(deleteItem);
                    
                    deleteItem.setOnAction(eventMenu -> 
                    {
                        zoomGroup.getChildren().remove((Node)eventContext.getSource());
                        eventMenu.consume();
                    });
                    menuContext.show(linePainting, event.getSceneX(), event.getSceneY());
                    eventContext.consume();
                });
                break;
            case "text":
                if(textDone)
                {
                    textField = new TextField();
                    zoomGroup.getChildren().add(textField);
                    textField.setLayoutX(event.getX());
                    textField.setLayoutY(event.getY());
                    textField.requestFocus();
                    textField.setOnMousePressed(c -> removeElement(textField));
                    textField.setOnAction(eventC ->{
                        Text label = new Text(textField.getText());
                        label.setX(textField.getLayoutX());
                        label.setY(textField.getLayoutY());
                        label.setStyle("-fx-font-familly:Gafatar; -fx-font-size:40;");
                        zoomGroup.getChildren().add(label);
                        zoomGroup.getChildren().remove(textField);
                        eventC.consume();
                        textDone = true;
                        
                        label.setOnMousePressed(c -> removeElement(label));
                        
                        

                    });
                    textDone = false;
                }
                break;
            
                
            default:
                break;
                
        }
        System.out.println(buttonSelection);
        
        


    }
    
    private void removeElement(Object node)
    {
        if(buttonSelection.equals("eraser"))
        {
            zoomGroup.getChildren().remove(node);
        }
    }

    
    
}
