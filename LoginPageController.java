/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginpage;

import beans.UserBeans;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LoginPageController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtpass;
    @FXML
    private Button btnLogin;
    private User userModel;
    @FXML
    private VBox sideMenu;

  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel=new User();
        borderpane.setLeft(null);
        // TODO
    }   


     private void loadUI(String ui){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
}

    @FXML
    private void billaction(MouseEvent event) {
        loadUI("Bill");
    }

    @FXML
    private void itemaction(MouseEvent event) {
        loadUI("Item");
    }

    @FXML
    private void stockaction(MouseEvent event) {
        loadUI("Stock");
    }

    @FXML
    private void salesaction(MouseEvent event) {
        loadUI("Sales");
    }

    @FXML
    private void staffaction(MouseEvent event) {
        loadUI("Staff");
    }

    @FXML
    private void setting(MouseEvent event) {
        loadUI("MainPage");
    }

    @FXML
    private void report(MouseEvent event) {
        loadUI("reporting");
    }

    @FXML
    private void exitaction(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void loginUser(ActionEvent event) {
        String username=txtUser.getText();
        String password=txtpass.getText();
        UserBeans ub=new UserBeans();
        ub.setName(username);
        ub.setPassword(password);
        boolean flag=userModel.userLogin(ub);
        if(flag){
            Alert alert=new Alert(AlertType.INFORMATION);
           // alert.setHeaderText("Login Success!");
           // alert.showAndWait();
            //System.out.println(flag);
           borderpane.setLeft(sideMenu);
            loadUI("home");
            
        }
        else{
           Alert alert=new Alert(AlertType.WARNING);
            alert.setHeaderText("Login Failed!");
            alert.show();
            System.out.println(flag);
        }
        
    }
    
        
    }
    

