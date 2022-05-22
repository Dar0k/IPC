/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.PopupWindow;
import javafx.util.Duration;
import model.Answer;
import model.Problem;
import model.User;

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
    private TextArea questionLabel;
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
    private boolean isFullscreen = false;
    private Node nodeTests;
    private Node nodeButtons;
    @FXML
    private ImageView transportador;
    private double startTransX;
    private double startTransY;
    private double baseX;
    private double baseY;
    private User user;
    private Problem problem;
    @FXML
    private Label questionNumber;
    @FXML
    private ImageView fullscreenImageView;
    @FXML
    private Button goBackButton;
    @FXML
    private Button sendButton;
    @FXML
    private Slider sizeSlider;
    @FXML
    private Region reg11;
    @FXML
    private ToggleGroup group1111;
    private int selection;
    private int [] selectionArr;
    private boolean controlKeyDown;
    @FXML
    private ToggleButton coordinates;
    @FXML
    private ToggleButton bucket;
    @FXML
    private ToggleButton protractor;
    private ArrayList<RadioButton> buttonsT;
    private static final String SQUARE_BUBBLE =
            "M24 1h-24v16.981h4v5.019l7-5.019h13z";
    
    private ArrayList <Circle> cList;
    private ArrayList <Tooltip>tList;
    private ScrollBar scrollBarv;
    @FXML
    private Circle sizeProf;

    private Line lineX;
    private Line lineY;
    private ArrayList<Line> lineXArr;
    private ArrayList<Line> lineYArr;
    @FXML
    private HBox hbButton;
    private boolean eraserCondition;
    private boolean arcCondition;
    @FXML
    private RadioButton radioButtonA1;
    private Font fontTemp;

    /**
     * Initializes the controller class.
     */
    
     void initStage(Stage stage, User us, Problem prob, int number)
    {
        primaryStage = stage;
        user = us;
        problem = prob;
        questionLabel.setText(problem.getText());
        List<Answer> list = problem.getAnswers();
        RadioButton [] buttons = {radioButtonA, radioButtonB, radioButtonC, radioButtonD};
        buttonsT = new ArrayList<RadioButton>(); buttonsT.add(radioButtonA); buttonsT.add(radioButtonB); buttonsT.add(radioButtonC); buttonsT.add(radioButtonD);
        List<Integer> pool = new ArrayList<Integer>();
        pool.add(0);
        pool.add(1);
        pool.add(2);
        pool.add(3);
        Random generator = new Random();
        int temp;
        selectionArr = new int [4];
        for(int i = 4; i > 1; i--)
        {
            int random = generator.nextInt(1000);
            temp = pool.remove(random % i);
            buttons[i-1].setText(list.get(temp).getText());
            selectionArr[i-1] = temp;
        }
        temp = pool.get(0);
        selectionArr[0] = temp;
        buttons[0].setText(list.get(temp).getText());
        questionNumber.setText("Problem " + number);
        
        cList = new ArrayList<Circle>();
        tList = new ArrayList<Tooltip>();
        
        lineXArr = new ArrayList<Line>();
        lineYArr = new ArrayList<Line>();
        
        fontTemp = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12);
        
        vboxQuest.heightProperty().addListener((obs, oldv, newv)-> {
            calcHSize((double)newv);
        });
        calcHSize(360);
        vboxQuest.widthProperty().addListener((obs, oldv, newv)-> {
            calcWSize((double)newv);
        });
        calcWSize(170);
        try{
            handleQuest(new ActionEvent());
            handleQuest(new ActionEvent());

        }
        catch(Exception e)
        {
            System.err.println("Error loading fullscreenImage");
        }
        
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
        colorPicker.setValue(Color.BLACK);
        sizeSlider.setMin(5);
        sizeSlider.setValue(25);
        sizeProf.radiusProperty().bind(Bindings.divide(sizeSlider.valueProperty(), 4));        
        
        rad.selectedToggleProperty().addListener((obs, oldv, newv) -> {
            sendButton.setDisable(false);            
        });
        
        toolTip();  
        
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


    @FXML
    private void mapMouseRelease(MouseEvent event) {
        map_scrollpane.setPannable(true);
        map_scrollpane.getScene().setCursor(Cursor.DEFAULT);
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
        sizeProf.setFill(colorPicker.getValue());
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
    private void handleQuest(ActionEvent event) throws Exception {
        if(questActive)
        {
            dividerPos = splitPane.getDividerPositions();
            nodeQuest = splitPane.getItems().get(1);
            splitPane.getItems().remove(nodeQuest);
            questActive = false;
            String url = "." + File.separator + "src" + File.separator + "resources" + File.separator + "shortscreen.png";
            fullscreenImageView.setImage(new Image(new FileInputStream(url)));
        }
        else
        {
            splitPane.getItems().add(nodeQuest);
            questActive = true;
            splitPane.setDividerPositions(dividerPos);
            String url = "." + File.separator + "src" + File.separator + "resources" + File.separator + "fullscreen.png";    
            fullscreenImageView.setImage(new Image(new FileInputStream(url)));           
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
        map_scrollpane.setPannable(false);
        arcCondition = true;
        switch(buttonSelection)
        {
            case "dot":
                Circle dot = new Circle(event.getX(), event.getY(), sizeSlider.getValue());
                dot.setStroke(colorPicker.getValue());
                dot.setFill(colorPicker.getValue());
                dot.setStrokeWidth(0);
                zoomGroup.getChildren().add(dot);
                dot.setOnMousePressed(c -> {removeElement(dot); map_scrollpane.setPannable(false);});
                /*dot.setOnMouseExited(c -> {
                    if(buttonSelection.equals("coords")) { 
                        map_scrollpane.getScene().setCursor(Cursor.DEFAULT);
                        dotTooltip(dot, false);
                    }
                    map_scrollpane.setPannable(true);                   
                });
                dot.setOnMouseEntered(c -> {
                    if(buttonSelection.equals("coords")) {
                        map_scrollpane.getScene().setCursor(Cursor.HAND);                        
                        dotTooltip(dot, true);                  
                    }
                });*/
                break;
                
            case "arc":
                circle = new Circle(event.getX(), event.getY(), sizeSlider.getValue());
                circle.setStrokeWidth(sizeSlider.getValue());
                circle.setStroke(colorPicker.getValue());
                circle.setFill(Color.TRANSPARENT);
                startXArc = event.getX();
                zoomGroup.getChildren().add(1, circle);
                Circle tempC = circle;
                circle.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me)
                    {
                        
                        handleMapMousePress(me);
                        if(eraserCondition) removeElement(tempC);
                        eraserCondition = false;
                        
                    }
                    
                });
                break;
            case "line":
                linePainting = new Line(event.getX(), event.getY(), event.getX(), event.getY());
                linePainting.setStrokeWidth(sizeSlider.getValue());
                linePainting.setFill(colorPicker.getValue());
                linePainting.setStroke(colorPicker.getValue());
                zoomGroup.getChildren().add(linePainting);
                Line temp = linePainting;
                linePainting.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me)
                    {
                        handleMapMousePress(me);
                        System.out.println(eraserCondition);
                        if(eraserCondition) removeElement(temp);
                        map_scrollpane.setPannable(false);
                        eraserCondition = false;
                    }
                    
                });
                linePainting.setOnMouseExited(c -> {map_scrollpane.setPannable(true);});
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
                        label.setStyle("-fx-font-familly:Gafatar; -fx-font-size:" + sizeSlider.getValue() + ";");
                        label.setStroke(colorPicker.getValue());
                        label.setFill(colorPicker.getValue());
                        zoomGroup.getChildren().add(label);
                        zoomGroup.getChildren().remove(textField);
                        eventC.consume();
                        textDone = true;
                        
                        label.setOnMousePressed(c -> removeElement(label));
                        
                        

                    });
                    textDone = false;
                }
                break;
            case "eraser":
                
            case "cubo":
                eraserCondition = true;
                break;
            
            case "coords":
                Line lineY = new Line(event.getX(), 0, event.getX(), 5760);
                lineY.setStrokeWidth(3);                
                lineY.setFill(colorPicker.getValue());
                lineY.setStroke(colorPicker.getValue());
                Line lineX = new Line(0, event.getY(), 8960, event.getY());
                lineX.setStrokeWidth(3);
                lineX.setFill(colorPicker.getValue());
                lineX.setStroke(colorPicker.getValue());
                lineX.strokeProperty().bindBidirectional(lineY.strokeProperty());
                zoomGroup.getChildren().add(lineX);
                zoomGroup.getChildren().add(lineY);
                lineXArr.add(lineX);
                lineYArr.add(lineY);
                
                lineX.setOnMouseClicked(c -> removeLine(lineX, true));
                lineY.setOnMouseClicked(c -> removeLine(lineY, false));

                break;             
                
            default:
                map_scrollpane.setPannable(true);
                map_scrollpane.getScene().setCursor(Cursor.MOVE);
                break;                
        }
        System.out.println(sizeSlider.getValue());
    }
    
    private void removeElement(Node node)
    {
        if(buttonSelection.equals("eraser"))
        {
            zoomGroup.getChildren().remove(node);
            

        }
        if(buttonSelection.equals("cubo"))
        {
            int index = zoomGroup.getChildren().indexOf(node);
            Node n =  zoomGroup.getChildren().get(index);
            
            
            if(n instanceof Shape)
            {
                
                Shape s = (Shape) n;
                if(s.getFill().equals(Color.TRANSPARENT))
                {
                    s.setStroke(colorPicker.getValue());
                }
                else
                {
                    s.setStroke(colorPicker.getValue());
                    s.setFill(colorPicker.getValue());
                }
                zoomGroup.getChildren().set(index, s);

                
            }                        
        }
    }
// rgba(132, 81, 43, 0.75)
    private void removeLine(Line line, boolean isX)
    {
        if(buttonSelection.equals("eraser"))
        {
            int index;
            Line temp;
            if(isX)
            {
                index = lineXArr.indexOf(line);
                zoomGroup.getChildren().remove(line);
                temp = lineYArr.get(index);
                zoomGroup.getChildren().remove(temp);                
            }
            else{
                index = lineYArr.indexOf(line);
                zoomGroup.getChildren().remove(line);
                temp = lineXArr.get(index);
                zoomGroup.getChildren().remove(temp);                
            }
            
        }if(buttonSelection.equals("cubo")){
            line.setStroke(colorPicker.getValue());            
        }       
    }

    
    @FXML
    private void handleTransportador(ActionEvent event) {
        transportador.setTranslateX(map_scrollpane.getHvalue()*8960);
        transportador.setTranslateY(map_scrollpane.getVvalue()*5760);
        transportador.setVisible(!transportador.isVisible());
    }

    @FXML
    private void handleTranspMouseRelease(MouseEvent event) {
        map_scrollpane.getScene().setCursor(Cursor.DEFAULT);
        map_scrollpane.setPannable(true);
        
    }

    @FXML
    private void handleTranspMouseDrag(MouseEvent event) {
        map_scrollpane.setPannable(false);
        double despX = event.getSceneX() - startTransX;
        double despY = event.getSceneY() - startTransY;
        transportador.setTranslateX(baseX + despX *(1/zoomVal));
        transportador.setTranslateY(baseY + despY * (1/zoomVal));
    }

    @FXML
    private void handleTranspMousePress(MouseEvent event) {
        
        startTransX = event.getSceneX();
        startTransY = event.getSceneY();
        baseX = transportador.getTranslateX();
        baseY = transportador.getTranslateY();
        map_scrollpane.getScene().setCursor(Cursor.CROSSHAIR);
        event.consume();             
    }

    @FXML
    private void handleCambiarColor(ActionEvent event) {
        buttonSelection = (buttonSelection.equals("cubo")) ? "" : "cubo";
        
    }

    @FXML
    private void handleCoordenadas(ActionEvent event) {
        buttonSelection = (buttonSelection.equals("coords")) ? "" : "coords";
    }

    @FXML
    private void handleGoBackButton(ActionEvent event)  {
        if(goBackButton.getText().equals("Back")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Cancel");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Accept");
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/resources/navegacion.png"));
            alert.getDialogPane().getStylesheets().add(getClass().getResource("../view/alerts.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("customAlert");
            alert.setTitle("Go back");
            alert.setHeaderText("Are you sure you want to leave the test?");
            alert.setContentText("All your progress will be lost and you will not be able come back. Are you sure you want to continue?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.CANCEL){
                System.out.println("OK");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainTest.fxml"));
                try{
                    Parent root = (Parent) loader.load();
                    MainTestController mTCtrl = loader.<MainTestController>getController();
                    mTCtrl.initStage(primaryStage, user);
                    primaryStage.getScene().setRoot(root);
                    primaryStage.show();
                }
                catch(IOException e)
                {
                Alert alertErr = new Alert(Alert.AlertType.ERROR);

                ((Button) alertErr.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");

                Stage alertStageErr = (Stage) alertErr.getDialogPane().getScene().getWindow();
                alertStageErr.getIcons().add(new Image("file:src/resources/navegacion.png"));
                alertErr.getDialogPane().getStylesheets().add(getClass().getResource("../view/alerts.css").toExternalForm());
                alertErr.getDialogPane().getStyleClass().add("customAlert");
                alertErr.setTitle("Exception Dialog");
                alertErr.setHeaderText("An error has occurred");
                alertErr.setContentText("An unexpected error has occurred when loading the scene. Please try again");


                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String exceptionText = sw.toString();

                Label label = new Label("Exception:");
                TextArea textArea = new TextArea(exceptionText);
                textArea.setEditable(false);
                textArea.setWrapText(true);
                textArea.setMaxWidth(Double.MAX_VALUE);
                textArea.setMaxHeight(Double.MAX_VALUE);
                GridPane.setVgrow(textArea, Priority.ALWAYS);
                GridPane.setHgrow(textArea, Priority.ALWAYS);
                GridPane expContent = new GridPane();
                expContent.setMaxWidth(Double.MAX_VALUE);
                expContent.add(label,0,0);
                expContent.add(textArea, 0, 1);

                alertErr.getDialogPane().setExpandableContent(expContent);
                alertErr.showAndWait();
                }
            } else {
                System.out.println("CANCEL");
            } 
        }else{                
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainTest.fxml"));
            try{
                Parent root = (Parent) loader.load();
                MainTestController mTCtrl = loader.<MainTestController>getController();
                mTCtrl.initStage(primaryStage, user);
                primaryStage.getScene().setRoot(root);
                primaryStage.show();
            }
            catch(IOException e)
            {
                Alert alertErr = new Alert(Alert.AlertType.ERROR);

                ((Button) alertErr.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");

                Stage alertStageErr = (Stage) alertErr.getDialogPane().getScene().getWindow();
                alertStageErr.getIcons().add(new Image("file:src/resources/navegacion.png"));
                alertErr.getDialogPane().getStylesheets().add(getClass().getResource("../view/alerts.css").toExternalForm());
                alertErr.getDialogPane().getStyleClass().add("customAlert");
                alertErr.setTitle("Exception Dialog");
                alertErr.setHeaderText("An error has occurred");
                alertErr.setContentText("An unexpected error has occurred when loading the scene. Please try again");


                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String exceptionText = sw.toString();

                Label label = new Label("Exception:");
                TextArea textArea = new TextArea(exceptionText);
                textArea.setEditable(false);
                textArea.setWrapText(true);
                textArea.setMaxWidth(Double.MAX_VALUE);
                textArea.setMaxHeight(Double.MAX_VALUE);
                GridPane.setVgrow(textArea, Priority.ALWAYS);
                GridPane.setHgrow(textArea, Priority.ALWAYS);
                GridPane expContent = new GridPane();
                expContent.setMaxWidth(Double.MAX_VALUE);
                expContent.add(label,0,0);
                expContent.add(textArea, 0, 1);

                alertErr.getDialogPane().setExpandableContent(expContent);
                alertErr.showAndWait();
            }        
        }          
    }

    @FXML
    private void handleSendButton(ActionEvent event) {
        if(sendButton.getText().equals("Send")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Cancel");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Accept");
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/resources/navegacion.png"));
            alert.getDialogPane().getStylesheets().add(getClass().getResource("../view/alerts.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("customAlert");
            alert.setTitle("Send");
            alert.setHeaderText("Are you sure you want to send your answer?");
            alert.setContentText("Your answer will be submitted and you will not be able come back. Are you sure you want to continue?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.CANCEL){
                boolean tempB = false; int i;            
                for(i = 0; i<4 && !tempB; i++){
                    if (problem.getAnswers().get(selectionArr[i]).getValidity()) {
                        i--; tempB =true;
                    };
                }            
                RadioButton temp = radioButtonA; 
                if(radioButtonA.isSelected()) {selection = 0; temp = radioButtonA;}
                else if(radioButtonB.isSelected()) {selection = 1; temp = radioButtonB;}
                else if(radioButtonC.isSelected()) {selection = 2; temp = radioButtonC;}
                else if(radioButtonD.isSelected()) {selection = 3; temp = radioButtonD;}
                boolean correct = problem.getAnswers().get(selectionArr[selection]).getValidity();
                System.out.println(correct);
                if(correct) {
                    MainLogOutController.hits++;
                    temp.setStyle("-fx-border-color: green; -fx-border-width: 0 0 2 0; -fx-padding: 3");
                }
                else { 
                    MainLogOutController.faults++;
                    temp.setStyle("-fx-border-color: red; -fx-border-width: 0 0 2 0; -fx-padding: 3");
                    buttonsT.get(i).setStyle("-fx-border-color: green; -fx-border-width: 0 0 2 0; -fx-padding: 3");
                }
                for(i = 0; i<4; i++){
                    buttonsT.get(i).setDisable(true);
                }
                sendButton.setText("Stats");
                goBackButton.setText("Tests");                
            }else {
                System.out.println("CANCEL");
            }     
        }else{            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainResults.fxml"));
            try{
                Parent root = (Parent) loader.load();
                MainResultsController mRCtrl = loader.<MainResultsController>getController();
                mRCtrl.initStage(primaryStage, user);
                primaryStage.getScene().setRoot(root);
                primaryStage.show();
            }
            catch(IOException e)
            {
                Alert alertErr = new Alert(Alert.AlertType.ERROR);

                ((Button) alertErr.getDialogPane().lookupButton(ButtonType.OK)).setText("Accept");

                Stage alertStageErr = (Stage) alertErr.getDialogPane().getScene().getWindow();
                alertStageErr.getIcons().add(new Image("file:src/resources/navegacion.png"));
                alertErr.getDialogPane().getStylesheets().add(getClass().getResource("../view/alerts.css").toExternalForm());
                alertErr.getDialogPane().getStyleClass().add("customAlert");
                alertErr.setTitle("Exception Dialog");
                alertErr.setHeaderText("An error has occurred");
                alertErr.setContentText("An unexpected error has occurred when loading the scene. Please try again");


                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String exceptionText = sw.toString();

                Label label = new Label("Exception:");
                TextArea textArea = new TextArea(exceptionText);
                textArea.setEditable(false);
                textArea.setWrapText(true);
                textArea.setMaxWidth(Double.MAX_VALUE);
                textArea.setMaxHeight(Double.MAX_VALUE);
                GridPane.setVgrow(textArea, Priority.ALWAYS);
                GridPane.setHgrow(textArea, Priority.ALWAYS);
                GridPane expContent = new GridPane();
                expContent.setMaxWidth(Double.MAX_VALUE);
                expContent.add(label,0,0);
                expContent.add(textArea, 0, 1);

                alertErr.getDialogPane().setExpandableContent(expContent);
                alertErr.showAndWait();
            }                
        }              
    }
   
    private void toolTip(){
        Tooltip dotTip = new Tooltip("dot");
        dotTip.setShowDelay(Duration.millis(500));
        Tooltip lineTip = new Tooltip("line");
        lineTip.setShowDelay(Duration.millis(500));
        Tooltip arcTip = new Tooltip("arc");
        arcTip.setShowDelay(Duration.millis(500));
        Tooltip textTip = new Tooltip("text");
        textTip.setShowDelay(Duration.millis(500));
        Tooltip eraserTip = new Tooltip("eraser");
        eraserTip.setShowDelay(Duration.millis(500));
        Tooltip coordTip = new Tooltip("coordinates");
        coordTip.setShowDelay(Duration.millis(500));
        Tooltip bucketTip = new Tooltip("fill color");
        bucketTip.setShowDelay(Duration.millis(500));
        Tooltip colorTip = new Tooltip("color picker");
        colorTip.setShowDelay(Duration.millis(500));
        Tooltip protractorTip = new Tooltip("protractor");
        protractorTip.setShowDelay(Duration.millis(500));
        Tooltip trashTip = new Tooltip("clear everything");
        trashTip.setShowDelay(Duration.millis(500));
        
        dot.setTooltip(dotTip);
        line.setTooltip(lineTip);
        arc.setTooltip(arcTip);
        text.setTooltip(textTip);
        eraser.setTooltip(eraserTip);
        coordinates.setTooltip(coordTip);
        bucket.setTooltip(bucketTip);
        colorPicker.setTooltip(colorTip);
        protractor.setTooltip(protractorTip);
        trash.setTooltip(trashTip);
    }

    private void calcHSize(double newv){
        double temp = primaryStage.getHeight();
        System.out.println("stage: " + temp);
        System.out.println("vbox: " + newv);
        System.out.println("row: " + questionLabel.getPrefRowCount());
        
            

        if((double)newv < 350){
            Font fontRa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12);
            Font fontLa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12);
            Font fontTe = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12);
            Font fontBu = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12);            
            fontTemp = fontTe;
            
            questionNumber.setFont(fontLa);
            questionLabel.setFont(fontTe);
            radioButtonA1.setFont(fontRa);  
            radioButtonA.setFont(fontRa);
            radioButtonB.setFont(fontRa);
            radioButtonC.setFont(fontRa);
            radioButtonD.setFont(fontRa);
            goBackButton.setFont(fontBu);
            sendButton.setFont(fontBu);     
        }  else if((double)newv >= 350 && (double)newv <450){
            double act = 450-(double)newv;
            int stageDif = 450-350;
            double per = 1 - (act/stageDif);
            double raDif = 16-12;
            double laDif = 16-12;
            double teDif = 18-12;
            double buDif = 16-12;
            
            Font fontRa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12 + (raDif*per));
            Font fontLa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12 + (laDif*per));
            Font fontTe = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12 + (teDif*per));
            Font fontBu = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12 + (buDif*per)); 
            fontTemp = fontTe;
            
            questionNumber.setFont(fontLa);
            questionLabel.setFont(fontTe);
            radioButtonA1.setFont(fontRa);
            radioButtonA.setFont(fontRa);
            radioButtonB.setFont(fontRa);
            radioButtonC.setFont(fontRa);
            radioButtonD.setFont(fontRa);
            goBackButton.setFont(fontBu);
            sendButton.setFont(fontBu);        
        }else{
            Font fontRa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 16);
            Font fontLa = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 16);
            Font fontTe = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 18);
            Font fontBu = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 16); 
            fontTemp = fontTe;
            
            questionNumber.setFont(fontLa);
            questionLabel.setFont(fontTe);
            radioButtonA1.setFont(fontRa);
            radioButtonA.setFont(fontRa);
            radioButtonB.setFont(fontRa);
            radioButtonC.setFont(fontRa);
            radioButtonD.setFont(fontRa);
            goBackButton.setFont(fontBu);
            sendButton.setFont(fontBu);         
        }
    }
    
    private void calcWSize(double newv){
        System.out.println("vbox H: " + newv);
        System.out.println("row: " + questionLabel.getPrefRowCount());
        if((double)newv < 250){
            goBackButton.setPrefWidth(newv*0.4);
            sendButton.setPrefWidth(newv*0.4);
            hbButton.setSpacing(10);
        }  else if((double)newv >= 250 && (double)newv <380){
            double act = 380-(double)newv;
            int stageDif = 380-250;
            double per = 1 - (act/stageDif);
            double spacDif = 35-10;
            
            hbButton.setSpacing(10+spacDif*per);
            goBackButton.setPrefWidth(100);
            sendButton.setPrefWidth(100);
        }else{
            hbButton.setSpacing(35);
            goBackButton.setPrefWidth(100);
            sendButton.setPrefWidth(100);
        }
    }
    
    private void dotTooltip (Circle c, boolean entered) {
        if(entered)
        {  
            Tooltip tool = new Tooltip("x: " + c.getCenterX() + "\ty:" + c.getCenterY());
            Tooltip.install(c, makeBubble(tool));
            cList.add(c);
            tList.add(tool);
        }else {
            int index = cList.indexOf(c);            
            Tooltip.uninstall(c, tList.remove(index));
            cList.remove(c);
        }
    }
    
    private Tooltip makeBubble(Tooltip tooltip) {
        tooltip.setStyle("-fx-font-size: 16px; -fx-shape: \"" + SQUARE_BUBBLE + "\";");
        tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
        tooltip.setShowDelay(Duration.millis(100)); 
        return tooltip;
    }     
}