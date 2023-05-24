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
    private TableColumn<vmInPm, String> newmut;
    @FXML
    private TableColumn<vmInPm, String> oldmut;
     @FXML
    private TableView<ag_coding> mut_indiv;
    @FXML
    private TableColumn<ag_coding, String> vm_mut;
    @FXML
    private TableColumn<ag_coding, String> pm_mut;
    String dataMut;
    List<List> population2=new ArrayList<>();

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
        @FXML
     void selectIdiv2(MouseEvent event) {
        mut_indiv.getItems().clear();
       int n= Integer.parseInt(mut_tab.getSelectionModel().getSelectedItem().getVm());
        List<individu1> individual=new  ArrayList<>();
       individual=population2.get(n-1);
       for(int i=0;i<individual.size();i++){
        ag_coding coding=new ag_coding(individual.get(i).vm.toString(), individual.get(i).pm_num.toString());
        mut_indiv.getItems().add(coding);
       }
    }
     public void mutt(){
     
         mut_seting.setText(getDataMut());
         System.out.println("mtttt"+population2.get(0).toString());
          for(int i=0;i<population2.size();i++){
              List<individu1> individual = population2.get(i);
              for(int j=0;j<individual.size();j++){
                   vmInPm vp= new vmInPm(String.valueOf(i+1), individual.get(j).toString(), population2.get(i).toString());
                   mut_tab.getItems().add(vp);
              }
            
             
         }
     }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           pm_mut.setCellValueFactory(new PropertyValueFactory<ag_coding,String>("pm"));
           vm_mut.setCellValueFactory(new PropertyValueFactory<ag_coding,String>("vm"));        
           nbmut.setCellValueFactory(new PropertyValueFactory<vmInPm,String>("Vm"));
           oldmut.setCellValueFactory(new PropertyValueFactory<vmInPm,String>("Old_pm"));
            newmut.setCellValueFactory(new PropertyValueFactory<vmInPm,String>("New_pm"));
   
        
    }    
    
}
