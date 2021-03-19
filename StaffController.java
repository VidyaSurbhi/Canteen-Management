/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginpage;

import beans.UserBeans;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.Staff;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class StaffController implements Initializable {

    @FXML
    private BorderPane borderstaff;
    @FXML
    private Button btninsert;
    @FXML
    private TextField empname;
    @FXML
    private TextField empid;
    @FXML
    private ComboBox desigcb;
    @FXML
    private TextField salary;
    @FXML
    private TextField age;
    @FXML
    private TextField email;
    @FXML
    private TextField mobile;
    @FXML
    private TextField address;
    private Staff staffobj;
    @FXML
    private TableColumn<UserBeans, String> colempname;
    @FXML
    private TableColumn<UserBeans, String> colage;
    @FXML
    private TableView<UserBeans> tableemp;
    @FXML
    private TableColumn<UserBeans, String> colempid;
    @FXML
    private TableColumn<UserBeans, String> colmob;
    @FXML
    private TableColumn<UserBeans, String> coldes;
    @FXML
    private TableColumn<UserBeans, String> colsal;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        staffobj=new Staff();
        desigcb.getItems().addAll("Manager","Worker","Volunteer","Cashier");
        colempid.setCellValueFactory(new PropertyValueFactory<>("eid"));
        colempname.setCellValueFactory(new PropertyValueFactory<>("ename"));
        colage.setCellValueFactory(new PropertyValueFactory<>("eage"));
        colsal.setCellValueFactory(new PropertyValueFactory<>("esal"));
        colmob.setCellValueFactory(new PropertyValueFactory<>("emob"));
        coldes.setCellValueFactory(new PropertyValueFactory<>("edesig"));
        tableemp.setItems(staffobj.showTableRecords());
           }    

   
    private void setTableData(MouseEvent event) {
        try{
        String name = tableemp.getSelectionModel().getSelectedItem().getEname();
        //printEmployeeInfo(empModel.searchEmployee(id));
        }
        catch(NullPointerException ex){
           Alert alert = new Alert(AlertType.WARNING);
           alert.setHeaderText("Empty Record Selected!");
           alert.show();
       }
        
    }

    @FXML
    private void insertaction(ActionEvent event) {
        UserBeans e = new UserBeans();
        e.setEname(empname.getText());
        e.setEid(Integer.parseInt(empid.getText()));
        e.setEdesig(desigcb.getValue().toString());
        e.setEmob(Integer.parseInt(mobile.getText()));
        e.setEemail(email.getText());
        e.setEage(Integer.parseInt(age.getText()));
        e.setEadd(address.getText());
        e.setEsal(Integer.parseInt(salary.getText()));
        boolean flag = staffobj.insertaction(e);
        if(!flag){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Employee Information Successfully Inserted!");
            alert.show();
            clearRecord();
            tableemp.setItems(staffobj.showTableRecords());
        

        }
        else{
             Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("Somethig went wrong, try again!");
            alert.show();
        }
      }
      private void clearRecord(){
            empid.setText("");
            empname.setText("");
            desigcb.setValue("");
            address.setText("");
            mobile.setText("");
            email.setText("");
           salary.setText("");
           age.setText("");
    
           // txtStatus.setText("");
        }

    @FXML
    private void updateStaff(ActionEvent event) {
        
        UserBeans e=new UserBeans();
      //  e.setEname(empname.getText());
        e.setEid(Integer.parseInt(empid.getText()));
        e.setEdesig(desigcb.getValue().toString());
        e.setEmob(Integer.parseInt(mobile.getText()));
        e.setEemail(email.getText());
        e.setEage(Integer.parseInt(age.getText()));
        e.setEadd(address.getText());
        e.setEsal(Integer.parseInt(salary.getText()));
        boolean flag=staffobj.updateStaffInfo(e);
        
        if(flag){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Employee Information not updated");
            alert.show();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Employee Information successfully updated!");
            alert.show();
            tableemp.setItems(staffobj.showTableRecords());

        }        
      }
    
    @FXML
    private void delete(ActionEvent event) {
      String name = empname.getText();
        boolean flag = staffobj.deleteItem(name);
        if(flag){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Item Information successfully Deleted!");
            alert.show();
           tableemp.setItems(staffobj.showTableRecords());
           clearRecord();

        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Something went wrong try again!");
            alert.show();
        }
    }
    
    private void setdata(MouseEvent event) {
        
        try{
        String name = tableemp.getSelectionModel().getSelectedItem().getEname();
        printItem(staffobj.searchStaff(name));
        }
        catch(NullPointerException ex){
           Alert alert = new Alert(AlertType.WARNING);
           alert.setHeaderText("Empty Record Selected!");
           alert.show();
       }
        
    }
   
     public void printItem(UserBeans e){
        if(e!=null){
            empid.setText(e.getEid()+"");
            empname.setText(e.getEname());
            desigcb.setValue(e.getEdesig()+"");
            mobile.setText(e.getEmob()+"");
            email.setText(e.getEemail());
            age.setText(e.getEage()+"");
            address.setText(e.getEadd());
            salary.setText(e.getEsal()+"");
        }
     }

    @FXML
    private void searchstaff(ActionEvent event) {
    }

    @FXML
    private void reportstaff(ActionEvent event) {
    }

    @FXML
    private void tableaction(MouseEvent event) {
         try{
        String name = tableemp.getSelectionModel().getSelectedItem().getEname();
        printItem(staffobj.searchStaff(name));
        }
        catch(NullPointerException ex){
           Alert alert = new Alert(AlertType.WARNING);
           alert.setHeaderText("Empty Record Selected!");
           alert.show();
       }
        
    }
}

    
    
    

