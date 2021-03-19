/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginpage;

import beans.UserBeans;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Item;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ItemController implements Initializable {

    @FXML
    private TextField itemname;
    @FXML
    private ComboBox itemtype;
    @FXML
    private TextField itemdesc;
     
    private Item itemobj;
    @FXML
    private TextField itemprice;
    @FXML
    private TableColumn<UserBeans,String> colname;
    @FXML
    private TableColumn<UserBeans,String> coltype;
    @FXML
    private TableColumn<UserBeans,String> colprice;
    @FXML
    private TableView<UserBeans> tableItem;
    @FXML
    private Button updateitem;
    @FXML
    private Button deleteitem;
    @FXML
    private TableColumn<UserBeans,String> coldesc;
    @FXML
    private Button itemreport;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         itemobj = new Item();
        itemtype.getItems().addAll("Water","Raw","Package");
        colname.setCellValueFactory(new PropertyValueFactory<>("Iname"));
        colprice.setCellValueFactory(new PropertyValueFactory<> ("Iprice"));
        coldesc.setCellValueFactory(new PropertyValueFactory<> ("Idesc"));
        coltype.setCellValueFactory(new PropertyValueFactory<> ("Itype"));
        tableItem.setItems(itemobj.showTableRecords());
        // TODO
    }    

    @FXML
    private void insertitem(ActionEvent event) {
        UserBeans e = new UserBeans();
        e.setIname(itemname.getText());
        e.setItype(itemtype.getValue().toString());
        e.setIdesc(itemdesc.getText());
        e.setIprice(Double.parseDouble(itemprice.getText()));
                
        boolean flag = itemobj.insertitem(e);
        if(!flag){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Item Information Successfully Inserted!");
            alert.show();
            //clearRecord();
            tableItem.setItems(itemobj.showTableRecords());
        }
        else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Somethig went wrong, try again!");
            alert.show();
        }
    }
        private void clearRecord(){
            itemname.setText("");
            itemtype.setValue("");
            itemdesc.setText("");
            itemprice.setText("");
            
      
  }
    @FXML
    private void update(ActionEvent event) {   
       UserBeans u = new UserBeans();
      //u.setName(itemname.getText());
       u.setIprice(Double.parseDouble(itemprice.getText()));
       u.setIdesc(itemdesc.getText());
       u.setItype(itemtype.getValue().toString());
        boolean flag=itemobj.updateItem(u);
        if(flag){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Item Information not updated");
            alert.show();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Item Information successfully updated!");
            alert.show();
            
            tableItem.setItems(itemobj.showTableRecords());

        }
    }

    @FXML
    private void delete(ActionEvent event) {
        String name = itemname.getText();
        boolean flag = itemobj.deleteItem(name);
        if(flag){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Item Information successfully Deleted!");
            alert.show();
           tableItem.setItems(itemobj.showTableRecords());
           clearRecord();

        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Something went wrong try again!");
            alert.show();
        }

    }

    @FXML
    private void setdata(MouseEvent event) {
        
        try{
        String name = tableItem.getSelectionModel().getSelectedItem().getIname();
        printItem(itemobj.searchItem(name));
        }
        catch(NullPointerException ex){
           Alert alert = new Alert(AlertType.WARNING);
           alert.setHeaderText("Empty Record Selected!");
           alert.show();
       }
        
    }
    
     public void printItem(UserBeans e){
        if(e!=null){
            itemname.setText(e.getIname());
            itemprice.setText(e.getIprice()+"");
            itemtype.setValue(e.getItype()+"");
            itemdesc.setText(e.getIdesc());
        }
           


     }

    @FXML
    private void report(ActionEvent event) {
        UserBeans e=new UserBeans();
        e.setIname(itemname.getText());
        e.setItype(itemtype.getValue().toString());
        e.setIdesc(itemdesc.getText());
        e.setIprice(Double.parseDouble(itemprice.getText()));
        itemobj.printRecord(e);
        
        
    }
    }
    

