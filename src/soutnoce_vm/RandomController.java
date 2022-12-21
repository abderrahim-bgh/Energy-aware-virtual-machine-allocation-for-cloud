/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package soutnoce_vm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author abderrahim
 */
public class RandomController implements Initializable {

   
    @FXML
    private Label total_label;
      
    @FXML
    private TableColumn<PmVmCl, String> vm_N;
      
    @FXML
    private TableColumn<PmVmCl, String> vp_cpu;
        @FXML
    private TableColumn<PmVmCl, String> vp_ram;
    @FXML
    private TableView<PmVmCl> vm_in_pm;
    
    @FXML
    private Label calcule_label;
    @FXML
    private ListView<String> list_pm;
    
    public void get_data(String[] pm1,String[] vm1,String [] cpu_vm, String[] ram_vm,String [] cpu_pm, String[] ram_pm,String[]vm_place){
        String p=pm1[0];

        for(int i=0;i<pm1.length;i++){list_pm.getItems().add("PM"+pm1[i]);
           }
                  int nb_vm=0;
       for(int i=0;i<vm1.length;i++){
                if(vm1[i]!=null) nb_vm++;
       }
            total_label.setText("Total of vm placed is "+nb_vm+"/"+vm1.length);          
       list_pm.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
           
          vm_in_pm.setVisible(true);
          String click = list_pm.getSelectionModel().getSelectedItem();
          vm_in_pm.getItems().clear();
          int cpu=0;
          int ram=0;
          int vm_p=0;
          String clk=click.substring(2,click.length());
          for(int i=0;i<vm_place.length;i++){
              if( vm_place[i]!=null)
          if(vm_place[i].equals(clk)){
              vm_p++;
               PmVmCl pv1= new PmVmCl( vm1[i],cpu_vm[i],ram_vm[i] );
              vm_in_pm.getItems().add(pv1);
              cpu=cpu+Integer.parseInt(cpu_vm[i]);
              ram=ram+Integer.parseInt(ram_vm[i]);
          }
              }
          int num=0;
          num= Integer.parseInt(ram_pm[Integer.parseInt(clk)-1])*1024 ;
          double c1=Integer.parseInt(cpu_pm[Integer.parseInt(clk)-1]);
          double c=cpu/c1;
          double k =0.7;
        double  k1=0.3;
          double e2=k1*250;
          double Energy = (k*250) + (e2*c); 
           System.out.println(c+" "+c1+" "+cpu+" "+e2+" "+Energy);
          calcule_label.setVisible(true);
          calcule_label.setText(" - CPU utilization of "+click+" is: "+cpu+"Mips /"+cpu_pm[Integer.parseInt(clk)-1]+
                  "Mips \n - RAM utilisation :"+ram+"MB /"+num+"MB"
                          + "\n - Total of vm placed in this "+click+" is: "+vm_p
                           +"\n - Energy: "+Energy);
        }
    });
      
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                                
        calcule_label.setVisible(false);                        
        vm_in_pm.setVisible(false);

         vm_N.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("vm"));
         vp_cpu.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("cpu"));
         vp_ram.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("ram"));

    }    
    
}
