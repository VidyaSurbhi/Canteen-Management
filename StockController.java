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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.Stock;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class StockController implements Initializable {

    @FXML
    private Button insertbtn;
    @FXML
    private BorderPane borderpanestock;
    @FXML
    private TextField productname;
    @FXML
    private TextField amount;
    @FXML
    private TextField quantity;
    @FXML
    private TextField totalcost;
    @FXML
    private TextField date;
    @FXML
    private ComboBox producttype;
    
    private Stock stockobj;
    @FXML
    private TableView<UserBeans> tablestock;
    @FXML
    private TableColumn<UserBeans, String> coldate;
    @FXML
    private TableColumn<UserBeans, String> colprdname;
    @FXML
    private TableColumn<UserBeans, String> coltype;
    @FXML
    private TableColumn<UserBeans, String> colamt;
    @FXML
    private TableColumn<UserBeans, String> colqty;
    @FXML
    private TableColumn<UserBeans, String> coltotal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stockobj = new Stock();
        producttype.getItems().addAll("Water","Juice","Liquid","Raw","Package");
        colprdname.setCellValueFactory(new PropertyValueFactory<>("prdname"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("prdtype"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colamt.setCellValueFactory(new PropertyValueFactory<>("prdamt"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("prdcost"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("prddate"));
        tablestock.setItems(stockobj.showTableRecords());
     }    
    @FXML
    private void insertstock(ActionEvent event) {
        UserBeans e = new UserBeans();
        e.setPrdname(productname.getText());
        e.setPrdtype(producttype.getValue().toString());
        e.setPrdamt(Double.parseDouble(amount.getText()));
        e.setQty(Integer.parseInt(quantity.getText()));
        e.setPrdcost(Double.parseDouble(totalcost.getText()));
        e.setPrddate(date.getText());        
        boolean flag = stockobj.insertstock(e);
        if(!flag){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Stock Information Successfully Inserted!");
            alert.show();
            clearRecord();
            tablestock.setItems(stockobj.showTableRecords());

        }
        else{
             Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("Somethig went wrong, try again!");
            alert.show();
        }
    }
private void clearRecord(){
            productname.setText("");
            producttype.setValue("");
            amount.setText("");
            quantity.setText("");
            totalcost.setText("");
            date.setText("");
          
    
           // txtStatus.setText("");
}

@FXML
 private void update(ActionEvent event) {   
        UserBeans e = new UserBeans();
       // e.setPrdname(productname.getText());
        e.setPrdtype(producttype.getValue().toString());
        e.setQty(Integer.parseInt(quantity.getText()));
        e.setPrdamt(Double.parseDouble(amount.getText()));
        e.setPrdcost(Double.parseDouble(totalcost.getText()));
        e.setPrddate(date.getText());
        boolean flag=stockobj.updateStock(e);
        if(flag){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Stock Information not updated");
            alert.show();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Stock Information successfully updated!");
            alert.show();
            tablestock.setItems(stockobj.showTableRecords());

        }
    }

 @FXML
 private void tableaction(MouseEvent event) {
     try{
        String name = tablestock.getSelectionModel().getSelectedItem().getPrdname();
        printItem(stockobj.searchStock(name));
        }
        catch(NullPointerException ex){
           Alert alert = new Alert(AlertType.WARNING);
           alert.setHeaderText("Empty Record Selected!");
           alert.show();
       }
        
    }
    public void printItem(UserBeans e){
        if(e!=null){
            productname.setText(e.getPrdname()+"");
            producttype.setValue(e.getPrdtype());
            amount.setText(e.getPrdamt()+"");
            quantity.setText(e.getQty()+"");
            totalcost.setText(e.getPrdcost()+"");
            date.setText(e.getPrddate());
        }
     }

    
}
