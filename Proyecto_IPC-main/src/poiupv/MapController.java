/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author DROCPER
 */
public class MapController implements Initializable {

    @FXML
    private ToggleGroup group1;
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
    private BorderPane map;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
}
