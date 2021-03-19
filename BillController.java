/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginpage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class BillController implements Initializable {

    @FXML
    private TextField billid;
    @FXML
    private TextField billdate;
    @FXML
    private BorderPane borderpanebill;
    @FXML
    private TextField itemname;
    @FXML
    private TextField quantity;
    @FXML
    private TextField price;
    @FXML
    private Button addbtn;
    @FXML
    private Button printbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void printaction(ActionEvent event) {
    }

    @FXML
    private void addaction(ActionEvent event) {
    }
    
}
