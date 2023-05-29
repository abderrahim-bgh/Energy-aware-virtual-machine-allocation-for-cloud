/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package soutnoce_vm;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author abderrahim
 */
public class Mutation1Controller implements Initializable {

    @FXML
    private Label mut_seting;
    @FXML
    private TableView<vmInPm> mut_tab;
    @FXML
    private TableColumn<vmInPm, String> nbmut;
    @FXML
    private TableColumn<vmInPm, String> oldmut;
    
    String dataMut,RepSize;
    List<List> population2=new ArrayList<>();

    public String getRepSize() {
        return RepSize;
    }

    public void setRepSize(String RepSize) {
        this.RepSize = RepSize;
    }

    public List<List> getPopulation2() {
        return population2;
    }

    public void setPopulation2(List<List> population2) {
        this.population2 = population2;
    }

    public String getDataMut() {
        return dataMut;
    }

    public void setDataMut(String dataMut) {
        this.dataMut = dataMut;
    }

     public void mutt(){
     
         mut_seting.setText(getDataMut());
          for(int i=0;i<population2.size();i++){
              List<individu> individual = population2.get(i);
             
                   vmInPm vp= new vmInPm(String.valueOf(i+1), population2.get(i).toString(), population2.get(i).toString());
                   mut_tab.getItems().add(vp);
             
         }
     }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          nbmut.setCellValueFactory(new PropertyValueFactory<vmInPm,String>("Vm"));
           oldmut.setCellValueFactory(new PropertyValueFactory<vmInPm,String>("Old_pm"));
   
        
    }    
    
}
