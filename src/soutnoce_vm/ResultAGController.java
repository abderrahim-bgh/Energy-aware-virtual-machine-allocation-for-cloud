
package soutnoce_vm;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
public class ResultAGController implements Initializable {

    @FXML
    private TableView<ag_coding> AllPmVm;
    @FXML
    private Label calcule_label;
    @FXML
    private Label resultLab;
    @FXML
    private ListView<String> list_pm;

    @FXML
    private TableColumn<ag_coding,String> pm_all;

    @FXML
    private TableColumn<ag_coding,String> vm_all;

    @FXML
    private TableView<PmVmCl> vm_in_pm;

    @FXML
    private TableColumn<PmVmCl, String> vm_N;
      
    @FXML
    private TableColumn<PmVmCl, String> vp_cpu;
    @FXML
    private TableColumn<PmVmCl, String> vp_ram;
    @FXML
    private NumberAxis nbChart;
    @FXML
    private CategoryAxis chartdown;
     @FXML
    private LineChart<?, ?> chatFit;  
     int energyMeg;

    public int getEnergyMeg() {
        return energyMeg;
    }

    public void setEnergyMeg(int energyMeg) {
        this.energyMeg = energyMeg;
    }
     
        
        public void BestPop( List<individu> ResultList,String [][]Pm){
            for(int i=0;i<Pm[0].length;i++){
            list_pm.getItems().add("PM"+Pm[0][i]);
           }
            List<individu> individual=new  ArrayList<>();
       individual=ResultList;
       for(int i=0;i<individual.size();i++){
        ag_coding coding=new ag_coding(individual.get(i).vm.toString(), individual.get(i).pm_num.toString());
        AllPmVm.getItems().add(coding);
       }
       list_pm.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
             vm_in_pm.setVisible(true);
          String click = list_pm.getSelectionModel().getSelectedItem();
          vm_in_pm.getItems().clear();
          int cpu=0;
          int ram=0;
          int vm_p=0;
          int storage=0;
          String clk=click.substring(2,click.length());
          for(int i=0;i<ResultList.size();i++){
              if( ResultList.get(i).getPm_num()!=null)
          if(ResultList.get(i).getPm_num().equals(clk)){
              vm_p++;
               PmVmCl pv= new PmVmCl( "vm"+ResultList.get(i).getVm(),ResultList.get(i).getCpu(),ResultList.get(i).getRam() );
              vm_in_pm.getItems().add(pv);
              vm_in_pm.refresh();
              cpu=cpu+Integer.parseInt(ResultList.get(i).getCpu());
              ram=ram+Integer.parseInt(ResultList.get(i).getRam());
              storage=storage+Integer.parseInt(ResultList.get(i).getVm_storage());
          }
              
              }   
              int num=0;
          num= Integer.parseInt(Pm[2][Integer.parseInt(clk)-1])*1024 ;
          double Energy =0;
          if(cpu==0){
              Energy =0;
          }
          else{
             double c1=Integer.parseInt(Pm[1][Integer.parseInt(clk)-1]);
             double c=cpu/c1;
             double k =0.7;
             double  k1=0.3;
             double e2=k1*250;
           Energy = (k*250) + (e2*c);  
          }
          calcule_label.setVisible(true);
          calcule_label.setText(" - CPU utilization of "+click+" is: "+cpu+"Mips /"+Pm[1][Integer.parseInt(clk)-1]+
                  "Mips \n - RAM utilisation :"+ram+"MB /"+num+"MB"
                          + "\n - Total of vm placed in this "+click+" is: "+vm_p
                           + "\n - Total storage in "+click+"is :"+storage+" G / "+Pm[4][Integer.parseInt(clk)-1]+" G"
                            + "\n - Energy: "+Energy+" W"
                              );
          }
       });
       resultLab.setText( " 1- The total energy consumption:"+
                               ResultList.get(0).getEnergy()+" W"
                               +"\n\n 2- SLA Violation :"+ ResultList.get(0).getSla()+"%"
                               +"\n\n 3- Migration energy: "+getEnergyMeg()
                               +"\n\n 4- fitness :"+ ResultList.get(0).getFit()
                               );
        }
        public void chartFit(Double [] fit){
            XYChart.Series xy = new XYChart.Series();
            for(int i=0;i<fit.length;i++)
              xy.getData().add(new XYChart.Data(String.valueOf(i+1),fit[i]));
            
            chatFit.getData().addAll(xy);
        }
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pm_all.setCellValueFactory(new PropertyValueFactory<ag_coding,String>("pm"));
        vm_all.setCellValueFactory(new PropertyValueFactory<ag_coding,String>("vm"));  
         vm_N.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("vm"));
         vp_cpu.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("cpu"));
         vp_ram.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("ram"));
    }    
    
}
