/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package soutnoce_vm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author abderrahim
 */
public class RandomController implements Initializable {

   
      
    @FXML
    private TableColumn<PmVmCl, String> vm_N;
      
    @FXML
    private TableColumn<PmVmCl, String> pm_N;
    @FXML
    private TableView<PmVmCl> vm_in_pm;
    
    public void get_data(String[] pm1,String[] vm1){
        for(int i=0;i<vm1.length;i++){
    PmVmCl pv1= new PmVmCl( pm1[i],vm1[i] );
    vm_in_pm.getItems().add(pv1);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // PmVmCl pv= new PmVmCl();
          //PmVmCl pv1= new PmVmCl( pv.getPm(), pv.getVm());
               
               
               // vm_in_pm.getItems().add(pv1);
         vm_N.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("vm"));
         pm_N.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("pm"));
    }    
    
}
