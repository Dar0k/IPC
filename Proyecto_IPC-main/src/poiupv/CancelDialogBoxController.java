    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author svalero
 */
public class CancelDialogBoxController implements Initializable {


    

    private int selectedIndex;
    @FXML
    private Button buttonOk;
    @FXML
    private TextField editName;
    @FXML
    private TextField editSurname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void initData (  int index){    
    }


    @FXML
    private void onActionButtonOk(ActionEvent event) {
        //Close the window
        ((Node) event.getSource()).getScene().getWindow().hide();

    }
            
    
}
