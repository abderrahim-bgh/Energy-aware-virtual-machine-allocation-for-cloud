
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package soutnoce_vm;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author abderrahim
 */
public class RandomController implements Initializable {
    
    @FXML
    private Label lb1;

    @FXML
    private Label lb2;

   @FXML
    private Label alocation;
   
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
    @FXML
    public Label titel_classification;
    @FXML
    private Button place_vm;
    @FXML
    private Button random;
    @FXML
    private Button MBFD;
    @FXML
    private TableView<vmInPm> tab_vms;
    @FXML
    private TableColumn<vmInPm, String> vm_Name;
    @FXML
    private TableColumn<vmInPm, String> new_pm;

    @FXML
    private TableColumn<vmInPm, String> old_pm;
    TableRow<vmInPm> row ;
    
    String sla1,AlVm;

    public String getAlVm() {
        return AlVm;
    }

    public void setAlVm(String AlVm) {
        this.AlVm = AlVm;
    }
    
    public String getSla1() {
        return sla1;
    }

    public void setSla1(String sla1) {
        this.sla1 = sla1;
    }
    
    public void pm_clik(String[][]classment_pm1,String[][]classment_vm1){
    
        list_pm.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if(list_pm.getSelectionModel().getSelectedItem().equals("Old and New Pms")){
                 tab_vms.setVisible(true);
          lb1.setVisible(false);                        
          lb2.setVisible(false);
          vm_in_pm.setVisible(false);
          calcule_label.setVisible(false);
            }
            else{
          tab_vms.setVisible(false);
          lb1.setVisible(true);                        
          lb2.setVisible(true);
          vm_in_pm.setVisible(true);
          String click = list_pm.getSelectionModel().getSelectedItem();
          vm_in_pm.getItems().clear();
          int cpu=0;
          int ram=0;
          int vm_p=0;
          int storage=0;
          String clk=click.substring(2,click.length());
          for(int i=0;i<classment_vm1[3].length;i++){
              if( classment_vm1[3][i]!=null)
          if(classment_vm1[3][i].equals(clk)){
              vm_p++;
               PmVmCl pv1= new PmVmCl( classment_vm1[0][i],classment_vm1[1][i],classment_vm1[2][i] );
              vm_in_pm.getItems().add(pv1);
              cpu=cpu+Integer.parseInt(classment_vm1[1][i]);
              ram=ram+Integer.parseInt(classment_vm1[2][i]);
              storage=storage+Integer.parseInt(classment_vm1[4][i]);
          }
              }
          int num=0;
          num= Integer.parseInt(classment_pm1[2][Integer.parseInt(clk)-1])*1024 ;
          double Energy =0;
          if(cpu==0){
              Energy =0;
          }
          else{
             double c1=Integer.parseInt(classment_pm1[1][Integer.parseInt(clk)-1]);
             double c=cpu/c1;
             double k =0.7;
             double  k1=0.3;
             double e2=k1*250;
           Energy = (k*250) + (e2*c);  
          }
          
          calcule_label.setVisible(true);
          calcule_label.setText(" - CPU utilization of "+click+" is: "+cpu+"Mips /"+classment_pm1[1][Integer.parseInt(clk)-1]+
                  "Mips \n - RAM utilisation :"+ram+"MB /"+num+"MB"
                          + "\n - Total of vm placed in this "+click+" is: "+vm_p
                           + "\n - Total storage in "+click+"is :"+storage+" G / "+classment_pm1[3][Integer.parseInt(clk)-1]+" G"
                            + "\n - Energy: "+Energy+" W");
        }
            }
    });
    
    
    }
    
    public void listAllVMs(String [][]tabVms){
        if(!titel_classification.getText().equals("MBFD Classification"))
            new_pm.setVisible(false);
        else new_pm.setVisible(true);
        tab_vms.getItems().clear();
        for(int i=0;i<tabVms[0].length;i++){
            if(tabVms[0][i]!=null){
                if(tabVms[2][i]==null) tabVms[2][i]= "  / ";
                vmInPm pv = new vmInPm(tabVms[0][i], "Pm "+tabVms[1][i], "Pm "+tabVms[2][i]);
                tab_vms.getItems().add(pv);
            }
        }
    }
    String slaV;

    public String getSlaV() {
        return slaV;
    }

    public void setSlaV(String slaV) {
        this.slaV = slaV;
    }
    public void get_data(String[][]classment_pm1,String[][]classment_vm1,String[][]vm2){
        
        double energy_total=0;
      
        list_pm.getItems().add("Old and New Pms");
        
        String p=classment_pm1[0][0];

        for(int i=0;i<classment_pm1[0].length;i++){
            list_pm.getItems().add("PM"+classment_pm1[0][i]);
           }
                  int nb_vm=0;
       for(int i=0;i<classment_vm1[0].length;i++){
                if(classment_vm1[3][i]!=null)
                    if(!classment_vm1[3][i].equals("0")) nb_vm++;
       }

       String num_sla="";
       if(titel_classification.getText().equals("MBFD Classification"))
            num_sla =getSlaV();
         int ssla=0;
         for(int j=0;j<classment_pm1[0].length;j++){
             int sla_x=0;
             int cpu=0;
             int ram=0;
             int z= Integer.parseInt(classment_pm1[0][j]);
             int z1=Integer.parseInt(classment_pm1[1][j]);
             if(titel_classification.getText().equals("MBFD Classification"))
                 sla_x= (z1*Integer.valueOf(num_sla))/100;
             
           for(int i=0;i<classment_vm1[4].length;i++){
             if(classment_vm1[3][i]!=null)
             if(classment_vm1[3][i].equals(String.valueOf(z))){
                 cpu=cpu+Integer.parseInt(classment_vm1[1][i]);
                 ram=ram+Integer.parseInt(classment_vm1[2][i]);
                }  
            }
           if(titel_classification.getText().equals("MBFD Classification"))
               if(cpu > sla_x){ ssla++;} 
            int num=0;
          num= Integer.parseInt(classment_pm1[2][Integer.parseInt(classment_pm1[0][j])-1])*1024 ;
          double Energy =0;
          if(cpu==0){
              Energy =0;
          }
          else{
             double c1=Integer.parseInt(classment_pm1[1][Integer.parseInt(classment_pm1[0][j])-1]);
             double c=cpu/c1;
             double k =0.7;
             double  k1=0.3;
             double e2=k1*250;
           Energy = (k*250) + (e2*c);  
          }
          
          energy_total=energy_total+Energy;
        }
          double sss= (1-(energy_total/(classment_pm1[0].length*250)))*100;
                   DecimalFormat df = new DecimalFormat("#.##");
                   String ss= df.format(sss);
         if(titel_classification.getText().equals("MBFD Classification")){
              total_label.setText("1- Total of vm placed "
                           + "\n is: "+nb_vm+"/"+(classment_vm1[0].length+vm2[0].length)
                            + "\n\n  2- The total energy\n consumption:"+
                               energy_total+" W"+"\n\n  3- Vms that we did not find \na place in Pm:"+getSla1()+"/"+getAlVm()+" VMs"
                                +"\n\n  4- SLA violations: "+ss+" %"
                                 ); 
         }
         else{
            total_label.setText("1- Total of vm placed "
                    + "\n is: "+nb_vm+"/"+(classment_vm1[0].length+vm2[0].length)
                            + "\n\n  2- The total energy\n consumption:"+
                               energy_total+" W"
                               ); 
         
         }
            pm_clik(classment_pm1,classment_vm1);
            
   
      
    }
    
    
    
    
    
      public void get_date2(String[][] classment_pm1,String[][]vm,String [][] vm2){ 
     String[][] VmAll= new String[5][vm[0].length+vm2[0].length];
      
       VmAll[0]=Arrays.copyOf(vm[0], vm[0].length+vm2[0].length);
      System.arraycopy(vm2[0],0,VmAll[0],vm[0].length,vm2[0].length);
       VmAll[1]=Arrays.copyOf(vm[1], vm[1].length+vm2[1].length);
      System.arraycopy(vm2[1],0,VmAll[1],vm[1].length,vm2[1].length);
       VmAll[2]=Arrays.copyOf(vm[2], vm[2].length+vm2[2].length);
      System.arraycopy(vm2[2],0,VmAll[2],vm[2].length,vm2[2].length);
      
       VmAll[4]=Arrays.copyOf(vm[4], vm[4].length+vm2[4].length);
      System.arraycopy(vm2[4],0,VmAll[4],vm[4].length,vm2[4].length);
      
         String[][] VmAll2= new String[5][vm[0].length+vm2[0].length];
      
       VmAll2[0]=Arrays.copyOf(vm[0], vm[0].length+vm2[0].length);
      System.arraycopy(vm2[0],0,VmAll2[0],vm[0].length,vm2[0].length);
       VmAll2[1]=Arrays.copyOf(vm[1], vm[1].length+vm2[1].length);
      System.arraycopy(vm2[1],0,VmAll2[1],vm[1].length,vm2[1].length);
       VmAll2[2]=Arrays.copyOf(vm[2], vm[2].length+vm2[2].length);
      System.arraycopy(vm2[2],0,VmAll2[2],vm[2].length,vm2[2].length);
      
       VmAll2[4]=Arrays.copyOf(vm[4], vm[4].length+vm2[4].length);
      System.arraycopy(vm2[4],0,VmAll2[4],vm[4].length,vm2[4].length);
     
      
      place_vm.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent event) {
              tab_vms.getItems().clear();
              titel_classification.setText("first fit Classification");
              alocation.setText("the second a location");
              
              double energy_total=0;
                  int nb_vm=0;
        String[] vm_p=new String[vm2[3].length];
        vm_p=Arrays.copyOf(vm2[3], vm2[3].length);          
        String[] cpu_pm=new String[classment_pm1[4].length];
       cpu_pm=Arrays.copyOf(classment_pm1[4], classment_pm1[4].length);
      String[] cpu_vm=new String[classment_pm1[4].length];
       cpu_vm=Arrays.copyOf(classment_pm1[5], classment_pm1[4].length);
       int total_cpu=0;
       int total_ram=0;
       int z=0;
       String tabVms [][]= new String [4][VmAll[0].length];
       
       for(int i=0;i<vm2[0].length;i++){
           
           if(z<100){
               
             if(cpu_pm[z]==null)cpu_pm[z]="0";
           total_cpu=Integer.parseInt(cpu_pm[z])+Integer.parseInt(vm2[1][i]);
            if(cpu_vm[z]==null)cpu_vm[z]="0";
           total_ram=Integer.parseInt(cpu_vm[z])+Integer.parseInt(vm2[2][i]);
           
           int p1=Integer.parseInt(classment_pm1[1][z]);
           int p2=Integer.parseInt(classment_pm1[2][z])*1024;
              
           if(p1>=total_cpu && total_ram<=p2 ){
               cpu_pm[z]=String.valueOf(total_cpu);
               cpu_vm[z]=String.valueOf(total_ram);
               
               vm_p[i]=String.valueOf(z+1);
               
           }
              else{
           
               
               z++;
               i--;
           }
           
        
        }
              }
            VmAll[3]=Arrays.copyOf(vm[3], vm[3].length+vm2[3].length);
      System.arraycopy(vm_p,0,VmAll[3],vm[3].length,vm2[3].length);
      
      tabVms[1]=Arrays.copyOf(vm[3], vm[3].length+vm2[3].length);
      System.arraycopy(vm_p,0,tabVms[1],vm[3].length,vm2[3].length);
      tabVms[0]=Arrays.copyOf(vm[0], vm[3].length+vm2[3].length);
      System.arraycopy(vm2[0],0,tabVms[0],vm[0].length,vm2[0].length);
      listAllVMs(tabVms);
      
        for(int i=0;i<VmAll[0].length;i++){
                if(VmAll[3][i]!=null) nb_vm++;
       }
        
        String eng[]=new String[classment_pm1[0].length];
        
           for(int j=0;j<classment_pm1[0].length;j++){
             int cpu=0;
             int ram=0;
             int zi= Integer.parseInt(classment_pm1[0][j]);
           for(int i=0;i<VmAll[4].length;i++){
             if(VmAll[3][i]!=null)
             if(VmAll[3][i].equals(String.valueOf(zi))){
                 cpu=cpu+Integer.parseInt(VmAll[1][i]);
                 ram=ram+Integer.parseInt(VmAll[2][i]);
                }  
            }
            int num=0;
          num= Integer.parseInt(classment_pm1[2][Integer.parseInt(classment_pm1[0][j])-1])*1024 ;
          double Energy =0;
          if(cpu==0){
              Energy =0;
          }
          else{
             double c1=Integer.parseInt(classment_pm1[1][Integer.parseInt(classment_pm1[0][j])-1]);
             double c=cpu/c1;
             double k =0.7;
             double  k1=0.3;
             double e2=k1*250;
           Energy = (k*250) + (e2*c);  
          }
          
          energy_total=energy_total+Energy;
          
          eng[j]=String.valueOf(Energy);
          
        }
           FXMLDocumentController dc = new FXMLDocumentController();
         //  dc.getEnergy(eng);
         
            total_label.setText("1- Total of vm placed "
                    + "\n is: "+nb_vm+"/"+(VmAll[0].length)
                            + "\n\n  2- The total energy\n consumption: "+
                                energy_total+" W"
                               ); 
            
            
                        pm_clik(classment_pm1,VmAll);
                         tabVms = new String [4][VmAll[0].length];
            
          }
      });
      
    
      random.setOnMouseClicked(new EventHandler<MouseEvent>(){
         @Override
         public void handle(MouseEvent event) {
              titel_classification.setText("Random Classification");
             tab_vms.getItems().clear();
             alocation.setText("the second a location");
             String tabVms [][]= new String [4][VmAll2[0].length];
             
      tabVms[0]=Arrays.copyOf(vm[0], vm[3].length+vm2[3].length);
      System.arraycopy(vm2[0],0,tabVms[0],vm[0].length,vm2[0].length);
              double energy_total=0;
                  int nb_vm=0;
        String[] vm_p=new String[vm2[3].length];
        vm_p=Arrays.copyOf(vm2[3], vm2[3].length);  
        String[] cpu_pm=new String[classment_pm1[4].length];
        cpu_pm=Arrays.copyOf(classment_pm1[4], classment_pm1[4].length);
        String[] ram_vm=new String[classment_pm1[4].length];
        ram_vm=Arrays.copyOf(classment_pm1[5], classment_pm1[4].length);
       int total_cpu=0;
       int total_ram=0;
       String[] vm_p1=new String[vm2[0].length];
        vm_p1=Arrays.copyOf(vm2[0], vm2[0].length); 
        
       String vm_shuffle[]=new String[vm2[0].length];
        List<String> vmList = Arrays.asList(vm2[0]);
               // shuffle the Vms number in the list
		Collections.shuffle(vmList);
                // return the Vms number in the array
		vmList.toArray(vm_shuffle);
             
                Random rd = new Random();
               int z=0;
       for(int i=0;i<vm2[0].length;i++){
           
            
           if(z<classment_pm1[0].length){
          
            if(cpu_pm[z]==null)cpu_pm[z]="0";
           total_cpu=Integer.parseInt(cpu_pm[z])+Integer.parseInt(vm2[1][i]);
            if(ram_vm[z]==null)ram_vm[z]="0";
           total_ram=Integer.parseInt(ram_vm[z])+Integer.parseInt(vm2[2][i]);
              
                String vm_number=vm_shuffle[i];
                int p1=Integer.parseInt(classment_pm1[1][z]);
           int p2=Integer.parseInt(classment_pm1[2][z])*1024;
                        String vi=vm2[0][i].substring(2,vm2[0][i].length());
                   int xv=Integer.parseInt(vi)-vm[0].length;
            
                total_cpu=Integer.parseInt(cpu_pm[z])+Integer.parseInt(vm2[1][xv-1]);
                total_ram=Integer.parseInt(ram_vm[z])+Integer.parseInt(vm2[2][xv-1]);
              
               cpu_pm[z]=String.valueOf(total_cpu);
               ram_vm[z]=String.valueOf(total_ram);
               
           if(total_cpu<=p1 &&total_ram<=p2 ){
                     vm_p[xv-1]=String.valueOf(z+1);
            }
              else{
           
               total_cpu=0;
               total_ram=0;
               z++;
               i--;
           }
             
           }
              }
       
        VmAll2[3]=Arrays.copyOf(vm[3], vm[3].length+vm2[3].length);
      System.arraycopy(vm_p,0,VmAll2[3],vm[3].length,vm2[3].length);
      
      tabVms[1]=Arrays.copyOf(vm[3], vm[3].length+vm2[3].length);
      System.arraycopy(vm_p,0,tabVms[1],vm[3].length,vm2[3].length);
      
     
      listAllVMs(tabVms);
      //for clear vm_p tab
        vm_p=new String[vm2[3].length];
      for(int i=0;i<VmAll2[0].length;i++){
                if(VmAll2[3][i]!=null) nb_vm++;
       }
       
             for(int j=0;j<classment_pm1[0].length;j++){
             int cpu=0;
             int ram=0;
             int zi= Integer.parseInt(classment_pm1[0][j]);
           for(int i=0;i<VmAll2[4].length;i++){
             if(VmAll2[3][i]!=null)
             if(VmAll2[3][i].equals(String.valueOf(zi))){
                 cpu=cpu+Integer.parseInt(VmAll2[1][i]);
                 ram=ram+Integer.parseInt(VmAll2[2][i]);
                }  
            }
            int num=0;
          num= Integer.parseInt(classment_pm1[2][Integer.parseInt(classment_pm1[0][j])-1])*1024 ;
          double Energy =0;
          if(cpu==0){
              Energy =0;
          }
          else{
             double c1=Integer.parseInt(classment_pm1[1][Integer.parseInt(classment_pm1[0][j])-1]);
             double c=cpu/c1;
             double k =0.7;
             double  k1=0.3;
             double e2=k1*250;
           Energy = (k*250) + (e2*c);  
          }
          
          energy_total=energy_total+Energy;
        }
         
            total_label.setText("1- Total of vm placed "
                    + "\n is: "+nb_vm+"/"+(VmAll[0].length)
                            + "\n\n  2- The total energy\n consumption: "+
                                energy_total+" W"
                               );  
            
                        vm2[0]=Arrays.copyOf(vm_p1, vm2[0].length); 
                        pm_clik(classment_pm1,VmAll2);
                        
                        tabVms = new String [4][VmAll2[0].length];
            
          }
      });
           String []percentage_min ={"10%","15%","20%","25%","30%","40%","50%"};
            String []percentage_max = {"50%","60%","70%","80%","85%","90%","95%"};
           

      MBFD.setOnMouseClicked(new EventHandler<MouseEvent>(){
         @Override
         public void handle(MouseEvent event) {

           Dialog<String> dialog = new Dialog<>();
           dialog.setTitle("energy threshold");
          dialog.setHeaderText("Modified Best Fit Decreasing");
           dialog.setResizable(true);
          
           Label label1 = new Label("Min threshold : ");
           Label label2 = new Label("Max threshold : ");
           ChoiceBox min = new ChoiceBox();
           ChoiceBox max = new ChoiceBox();
           min.getItems().addAll(percentage_min);
           max.getItems().addAll(percentage_max);
         
          GridPane grid = new GridPane();
            grid.add(label1, 1, 1);
            grid.add(min, 2, 1);
            grid.add(label2, 1, 2);
            grid.add(max, 2, 2);
         dialog.getDialogPane().setContent(grid);
         
ButtonType buttonTypeOk = new ButtonType("Okay", ButtonData.OK_DONE);
dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
 
dialog.setResultConverter(new Callback<ButtonType, String>() {
    @Override
    public String call(ButtonType b) {
 
        if (b == buttonTypeOk) {
            titel_classification.setText("MBFD Classification");
            tab_vms.getItems().clear();
            String num_sla="";
            String[] vm_p=new String[vm[3].length];
            vm_p=Arrays.copyOf(vm[3], vm[3].length);
            
            String[] cpu_pm=new String[classment_pm1[4].length];
            cpu_pm=Arrays.copyOf(classment_pm1[4], classment_pm1[4].length);
           
            String[] ram_vm=new String[classment_pm1[5].length];
            ram_vm=Arrays.copyOf(classment_pm1[5], classment_pm1[5].length);
            
            int total_cpu=0;
            int total_ram=0;
            List<Vmcl> vm_migrated=new  ArrayList<>();
            
            for(int j=0;j<vm2[0].length;j++){
                        vm_migrated.add(new Vmcl(vm2[0][j],vm2[1][j],vm2[2][j],vm2[4][j]));
                    
                }
            String[][] VmAll3= new String[5][vm[0].length+vm2[0].length];
                     VmAll3[0]=Arrays.copyOf(vm[0], vm[0].length+vm2[0].length);
                     System.arraycopy(vm2[0],0,VmAll3[0],vm[0].length,vm2[0].length);
                     VmAll3[1]=Arrays.copyOf(vm[1], vm[1].length+vm2[1].length);
                     System.arraycopy(vm2[1],0,VmAll3[1],vm[1].length,vm2[1].length);
                     VmAll3[2]=Arrays.copyOf(vm[2], vm[2].length+vm2[2].length);
                     System.arraycopy(vm2[2],0,VmAll3[2],vm[2].length,vm2[2].length);
      
                   VmAll3[4]=Arrays.copyOf(vm[4], vm[4].length+vm2[4].length);
                   System.arraycopy(vm2[4],0,VmAll3[4],vm[4].length,vm2[4].length);
                   
                   String tabVms [][]= new String [4][VmAll2[0].length];
                   tabVms[1]=Arrays.copyOf(vm[3], VmAll2[0].length);
                   tabVms[0]=Arrays.copyOf(vm[0], vm[3].length+vm2[3].length);
                   System.arraycopy(vm2[0],0,tabVms[0],vm[0].length,vm2[0].length);
            //mbfd
            Collections.sort(vm_migrated,new Comparator<Vmcl>(){
                    @Override
                    public int compare(Vmcl o1, Vmcl o2) {
                        return Integer.compare(Integer.parseInt(o1.getCpu()), Integer.parseInt(o2.getCpu()));
                    }
                });
            
                Collections.reverse(vm_migrated);
                String [] allocate_vm=new String[vm_migrated.size()];
                for(int j=0;j<vm_migrated.size();j++){
                    double minPower=250;
                    String allocatedHost=null;
                    int total_cpu2=0;
                    int total_ram2=0;
                    for(int z=0;z<classment_pm1[0].length;z++){
                        //if host has enough resource for vm
                        
                        if(cpu_pm[z]==null)cpu_pm[z]="0";
                          total_cpu=Integer.parseInt(cpu_pm[z])+Integer.parseInt(vm_migrated.get(j).cpu);
                        if(ram_vm[z]==null)ram_vm[z]="0";
                        
                          total_ram=Integer.parseInt(ram_vm[z])+Integer.parseInt(vm_migrated.get(j).ram); 
                          int p2=Integer.parseInt(classment_pm1[2][z])*1024;
                          int p1=Integer.parseInt(classment_pm1[1][z]);
                        if(p1>=total_cpu ){
                            
                            int num=0;
                            num= Integer.parseInt(classment_pm1[2][z])*1024 ;
                           double c1=Integer.parseInt(classment_pm1[1][z]);
                           double c=total_cpu/c1;
                           double k =0.7;
                           double  k1=0.3;
                           double e2=k1*250;
                           double Energy = (k*250) + (e2*c); 
                          
                           if(Energy<minPower){
                               allocatedHost=classment_pm1[0][z];
                               minPower=Energy;
                               total_cpu2=total_cpu;
                               total_ram2=total_ram;
                               
                           }
                        }
                    }

                    if(allocatedHost!=null) {
                               allocate_vm[j]= allocatedHost;
                               cpu_pm[Integer.parseInt(allocatedHost)-1]=String.valueOf(total_cpu2);
                               ram_vm[Integer.parseInt(allocatedHost)-1]=String.valueOf(total_ram2);
                               
                           }
                }
                
                     
                   
                  for(int i=0;i<vm_p.length;i++){
                      VmAll3[3][i]=vm_p[i];
                  }
                   for(int i=0;i<vm_migrated.size();i++){
                       String v=vm_migrated.get(i).vm.toString();
                       for(int j=0;j<VmAll3[0].length;j++){
                           if(v.equals(VmAll3[0][j])){
                               
                               VmAll3[3][j]=allocate_vm[i];
                               tabVms[1][j]=allocate_vm[i];
                           }
                        }
                    }
                   // end mbfd
                   vm_migrated= new ArrayList<>();
            
            //Minimization of Migrations algo
                    int cpu_thre=0;
                     int cpu_thre_max=0;
                    List<Vmcl> old_pm=new  ArrayList<>();
            for(int i=0;i<classment_pm1[0].length;i++){
               // String [][] vm_list= new String [4][vm[0].length];
                List<Vmcl> vm_list=new  ArrayList<>();
                int z=0;
                for(int j=0;j<VmAll3[0].length;j++){
                    if(classment_pm1[0][i].equals(VmAll3[3][j])){
                        vm_list.add(new Vmcl(VmAll3[0][j],VmAll3[1][j],VmAll3[2][j],VmAll3[4][j]));
                        z++;
                    }

                    
                }
                
              
                Collections.sort(vm_list,new Comparator<Vmcl>(){
                    @Override
                    public int compare(Vmcl o1, Vmcl o2) {
                        return Integer.compare(Integer.parseInt(o1.getCpu()), Integer.parseInt(o2.getCpu()));
                    }
                });
                Collections.reverse(vm_list);
                int cpu_total=0;
                for(int j=0;j<vm_list.size();j++){
                cpu_total=cpu_total+Integer.parseInt(vm_list.get(j).cpu.toString());
                }
                String Tmin= min.getValue().toString();
                String num=Tmin.substring(0, Tmin.length()-1);
                int min_threthreshold=Integer.parseInt(num);
                cpu_thre= (Integer.parseInt(classment_pm1[1][i])*min_threthreshold)/100;
                
                String Tmax= max.getValue().toString();
                String num2=Tmax.substring(0, Tmax.length()-1);
                num_sla=num2;
                int max_threthreshold=Integer.parseInt(num2);
                
                cpu_thre_max= (Integer.parseInt(classment_pm1[1][i])*max_threthreshold)/100;
                 
                
        
                Vmcl bestFit_vm = null;
                if(vm_list.size()>=1) {
                    int Maxx=Integer.parseInt(vm_list.get(0).cpu.toString());
                int bestFit_cpu= Maxx;
                while(cpu_thre_max< cpu_total){
                    for(int j=0;j<vm_list.size();j++){
                        int t0= cpu_total-cpu_thre_max;
                        if(Integer.parseInt(vm_list.get(j).cpu.toString())>t0){
                           int t= Integer.parseInt(vm_list.get(j).cpu.toString())-cpu_total
                                +cpu_thre_max; 
                           if(t<bestFit_cpu){
                               bestFit_cpu=t;
                               bestFit_vm=vm_list.get(j);
                           }
                        }
                        else if(bestFit_cpu==Maxx){
                            bestFit_vm=vm_list.get(j);
                        }
                    }
                    cpu_total=cpu_total-Integer.parseInt(bestFit_vm.cpu.toString());
                    vm_migrated.add(bestFit_vm);
                    vm_list.remove(bestFit_vm);
                }
                }
                
                if(cpu_thre >cpu_total){
                    vm_migrated.addAll(vm_list);
                }
            }
             for(int j=0;j<vm_migrated.size();j++)
              for(int i=0;i<VmAll3[0].length;i++){
                  if(VmAll3[0][i].equals(vm_migrated.get(j).vm)){
                               // delete cpu utilization of vm deleted
                               int x=Integer.parseInt(VmAll3[3][i]);
                               int d=Integer.parseInt(vm_migrated.get(j).cpu.toString());
                               int r = Integer.parseInt(cpu_pm[x-1]) -d;
                               cpu_pm[x-1]=String.valueOf(r);
                               //deleted ram utilization from this pm of vm deleted
                               int xr=Integer.parseInt(VmAll3[3][i]);
                               int dr=Integer.parseInt(vm_migrated.get(j).ram.toString());
                               int rr = Integer.parseInt(ram_vm[xr-1]) -dr;
                               ram_vm[xr-1]=String.valueOf(rr);
                               
                               old_pm.add(new Vmcl(VmAll3[0][i],VmAll3[1][i],VmAll3[2][j],VmAll3[3][i]));
                               VmAll3[3][i]="0";
                    }
                 }
             
             
            
            // mbfd 2
             List<String> allocate_vm1=new  ArrayList<>();
            // sort Decreasing cpu Utilization
             Collections.sort(vm_migrated,new Comparator<Vmcl>(){
                    @Override
                    public int compare(Vmcl o1, Vmcl o2) {
                        return Integer.compare(Integer.parseInt(o1.getCpu()), Integer.parseInt(o2.getCpu()));
                    }
                });
                Collections.reverse(vm_migrated);
                for(int j=0;j<vm_migrated.size();j++){
                    double minPower=250;
                    String allocatedHost=null;
                    int total_cpu2=0;
                    int total_ram2=0;
                    String Tmax= max.getValue().toString();
                         String num2=Tmax.substring(0, Tmax.length()-1);
                         int max_threthreshold=Integer.parseInt(num2);
                    for(int z=0;z<classment_pm1[0].length;z++){
                        //if host has enough resource for vm
                        if(cpu_pm[z]==null)cpu_pm[z]="0";
                          total_cpu=Integer.parseInt(cpu_pm[z])+Integer.parseInt(vm_migrated.get(j).cpu);
                        if(ram_vm[z]==null)ram_vm[z]="0";
                          total_ram=Integer.parseInt(ram_vm[z])+Integer.parseInt(vm_migrated.get(j).ram); 
                        int p2=Integer.parseInt(classment_pm1[2][z])*1024;
                        int p1=Integer.parseInt(classment_pm1[1][z]);
                         
                         int cpu_thre_max1=(p1*max_threthreshold)/100;
                        if(cpu_thre_max1>=total_cpu ){
                            int num=0;
                            num= Integer.parseInt(classment_pm1[2][z])*1024 ;
                           double c1=Integer.parseInt(classment_pm1[1][z]);
                           double c=total_cpu/c1;
                           double k =0.7;
                           double  k1=0.3;
                           double e2=k1*250;
                           double Energy = (k*250) + (e2*c); 
                          
                           if(Energy<minPower){
                               allocatedHost=classment_pm1[0][z];
                               minPower=Energy;
                               total_cpu2=total_cpu;
                               total_ram2=total_ram;
                               
                               
                           }
                        }
                    }

                    if(allocatedHost!=null) {
                               allocate_vm1.add(allocatedHost);
                               cpu_pm[Integer.parseInt(allocatedHost)-1]=String.valueOf(total_cpu2);
                               ram_vm[Integer.parseInt(allocatedHost)-1]=String.valueOf(total_ram2);
                               String v=vm_migrated.get(j).vm.toString();
                               for(int i=0;i<VmAll3[0].length;i++){
                                   if(v.equals(VmAll3[0][i])){
                                       VmAll3[3][i]=allocatedHost;
                                       tabVms[2][i]=allocatedHost;
                                       
                                       
                                   }
                               }
                           }
                }
                
                int SLA=0;
                for(int j=0;j<old_pm.size();j++)
                    for(int i=0;i<VmAll3[0].length;i++){
                         if(VmAll3[3][i]!=null)
                            if(VmAll3[3][i].equals("0")){
                                String c= old_pm.get(j).vm.toString();
                                if(c.equals(VmAll3[0][i])){
                                    SLA++;
                                    // vm_storage in old_pm is number of pm
                                   VmAll3[3][i]=old_pm.get(j).vm_storage.toString();
                                   tabVms[2][i]=old_pm.get(j).vm_storage.toString();
                                     // add cpu utilization of vm deleted
                                     int x=Integer.parseInt(VmAll3[3][i]);
                                     int d=Integer.parseInt(old_pm.get(j).cpu.toString());
                                     int r = Integer.parseInt(cpu_pm[x-1]) +d;
                                     cpu_pm[x-1]=String.valueOf(r);
                                    //add ram utilization from this pm of vm deleted
                                     int xr=Integer.parseInt(VmAll3[3][i]);
                                     int dr=Integer.parseInt(old_pm.get(j).ram.toString());
                                     int rr = Integer.parseInt(ram_vm[xr-1]) +dr;
                                     ram_vm[xr-1]=String.valueOf(rr);
                                }
                            }
                    }
                    listAllVMs(tabVms);
                    int nb_vm=0;
                    for(int i=0;i<VmAll3[0].length;i++){
                        if(VmAll3[3][i]!=null ) 
                            if(!VmAll3[3][i].equals("0"))
                                nb_vm++;
                    }
                    int sla_x=0;
                    
                     
                   int ssla=0;
                   double energy_total=0;
                   for(int j=0;j<classment_pm1[0].length;j++){
                       int cpu=0; int ram=0;
                       int zi= Integer.parseInt(classment_pm1[0][j]);
                       int z1=Integer.parseInt(classment_pm1[1][j]);
                        sla_x= (z1*Integer.valueOf(num_sla))/100;
                       for(int i=0;i<VmAll3[4].length;i++){
                           if(VmAll3[3][i]!=null )
                               if(!VmAll3[3][i].equals("0"))
                                   if(VmAll3[3][i].equals(String.valueOf(zi))){
                                       cpu=cpu+Integer.parseInt(VmAll2[1][i]);
                                       ram=ram+Integer.parseInt(VmAll2[2][i]);
                }  
            }
                        if(cpu > sla_x){ ssla++;} 
                       int num=0; double Energy =0;
                       num= Integer.parseInt(classment_pm1[2][Integer.parseInt(classment_pm1[0][j])-1])*1024 ;
                       if(cpu==0){
                           Energy =0;
                       }
                       else{
                           double c1=Integer.parseInt(classment_pm1[1][Integer.parseInt(classment_pm1[0][j])-1]);
                           double c=cpu/c1;
                           double k =0.7;  double  k1=0.3;
                           double e2=k1*250;
                           Energy = (k*250) + (e2*c);  
                       }
                       energy_total=energy_total+Energy;
                   }
                  //is % of pms top of threshold (SLA violation)
                   double sss= (1-(energy_total/(classment_pm1[0].length*250)))*100;
                   DecimalFormat df = new DecimalFormat("#.##");
                   String ss= df.format(sss);
                   total_label.setText("1- Total of vm placed "
                           + "\n is: "+String.valueOf(nb_vm)+"/"+(VmAll[0].length)
                            + "\n\n  2- The total energy\n consumption:"+
                               energy_total+" W"+"\n\n  3- Vms that we did not find \na place in Pm:"+SLA+"/"+old_pm.size()+" VMs"
                                +"\n\n  4- SLA violations: "+ss+" %"
                                 );  
                   
                           
                   
                   
                                          pm_clik(classment_pm1,VmAll3);
                                          tabVms = new String [4][VmAll[0].length];
  
        }
 
        return null;
    }
});
         
    Optional<String> result = dialog.showAndWait();
         


         }
      
          
      });
      
      
      }
       public void mbfd1(){
            }

    @FXML
    ImageView back;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        back.setOnMouseClicked(event->{
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
        });
                                
        calcule_label.setVisible(false);                        
        vm_in_pm.setVisible(false);
        lb1.setVisible(false);                        
        lb2.setVisible(false);
        
         vm_N.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("vm"));
         vp_cpu.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("cpu"));
         vp_ram.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("ram"));
         vm_Name.setCellValueFactory(new PropertyValueFactory<vmInPm,String>("Vm"));
         old_pm.setCellValueFactory(new PropertyValueFactory<vmInPm,String>("Old_pm"));
         new_pm.setCellValueFactory(new PropertyValueFactory<vmInPm,String>("New_pm"));

    }    
    
}