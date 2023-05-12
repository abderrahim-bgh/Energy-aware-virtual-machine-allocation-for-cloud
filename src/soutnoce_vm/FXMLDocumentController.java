
package soutnoce_vm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.text.View;

public class FXMLDocumentController implements Initializable {

        @FXML
    private TableView<pmCl> idpm;

    @FXML
    private TableView<Vmcl> idvm;

    @FXML
    private Label label;

    @FXML
    private TableColumn<pmCl, String> pm;

    @FXML
    private TableColumn<pmCl,Integer> pm_cpu;

    @FXML
    private TableColumn<pmCl, Integer> pm_eng;

    @FXML
    private TableColumn<pmCl, Integer> pm_ram;

    @FXML
    private TableColumn<pmCl, String> pm_state;
    @FXML
    private TableColumn<pmCl, Integer> pm_storage;

    @FXML
    private TableColumn<Vmcl, String> vm;

    @FXML
    private TableColumn<Vmcl, Integer> vm_cpu;
    
    @FXML
    private TableColumn<Vmcl, Integer> vm_storage;

    @FXML
    private TableColumn<Vmcl, Integer> vm_ram;
    
    @FXML
    private TableColumn<PmVmCl, String> vm_N;
      
    @FXML
    private TableColumn<PmVmCl, String> pm_N;
    @FXML
    private Spinner<String> spnr_pourcentage;
     @FXML
    private Label label_pm;

    @FXML
    private Label label_vm;
    @FXML
    private Label lebel_spnr; 
    @FXML
    private Label lebel_spnr1;
    @FXML
    private ChoiceBox<String> choice_vmp;
    @FXML 
    Tab home_tab;
    @FXML
    TabPane tabP;
    //number of VMs
    int vv=0;
    //number of PMs
    int pp = 0;
        String vmm[][] ;
        String pmm[][] ;
        int nb_vm=0;
        int nb_pm=0;
        String allVm[][];
        String allPm[][];

   
    
        
    @FXML
    void add_pm(ActionEvent event) {
        // change visibylity of table view of pm
         label_pm.setVisible(true);
         idpm.setVisible(true);
         //for get file of pm
FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
            .addAll(
                new FileChooser.ExtensionFilter("TXT files (*.*)", "*.*"),
                new FileChooser.ExtensionFilter("txt files (*.*)", "*.*"));
        
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            //Count the number of lines
                 int x=1;
                  String st;
                    BufferedReader br0;
             try {
                 br0 = new BufferedReader(new FileReader(file));
                  while ((st = br0.readLine()) != null){
                     for(int i=0;i<st.length();i++)
                         if(st.charAt(i)=='+') x++;
                 }
             } catch (FileNotFoundException ex) {
             } catch (IOException ex) {
                 Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
             }
                
                 pp=x;
                 label_pm.setText("    The physical Machines ("+pp+" PMs )");
                pmm=  new String[6][pp];
                // in Pm there are type of vm and number, CPu and ram, State and energy 
                allPm = new String[8][pp];
            try {
                 BufferedReader br
            = new BufferedReader(new FileReader(file));
                 
                
                 
        // Condition holds true till
        // there is character in a string
       String  txt="" ;
       // m for the number of lines (each line in pm)
       // z is the charactreristic of pm( each colon is a cpu or ram ...
       int z=0; int m =0;
        while ((st = br.readLine()) != null){
            for(int i=0;i<st.length();i++){
                
                if(st.charAt(i)!=','&& st.charAt(i)!='+'){
                    // put char in this string 
                    txt= txt+st.charAt(i);
                }
                // if there are + go to anthere pm
                else if(st.charAt(i)=='+'){
                    
                   m++;
                   z=0;
                }
                else {
                    // if thre are , add in table and go to next colon
                    pmm[z][m]=txt;
                    z++;
                    txt="";
                }
            
            } 
        }
         int vi=1;
         for(int i=0;i<pp;i++){
             if(pmm[2][i]!=null){
                      //Add pm in Table view
                      pmCl ipm = new pmCl("pm"+vi,pmm[2][i],pmm[3][i]+" G",pmm[4][i],pmm[1][i]+" G",pmm[5][i]);
                      idpm.getItems().add(ipm);
                      // add pm in second arry 
                      nb_pm++;
                      allPm[0][i]= String.valueOf( vi);
                      allPm[1][i]=pmm[2][i];
                      allPm[2][i]=pmm[3][i];
                      allPm[3][i]=pmm[4][i];
                      allPm[4][i]=pmm[5][i];
                      allPm[7][i]=pmm[1][i];
                      
                        vi++;
                 
            
            }
        }
        
            } catch (IOException ex) {
               
            }
        }
    }

    @FXML
    void add_vm(ActionEvent event) {
        
        // visibility of table view
        idvm.setVisible(true);
        label_vm.setVisible(true);
        choice_vmp.setVisible(true);
        lebel_spnr.setVisible(true);
         lebel_spnr1.setVisible(true);
        // open file 
 FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
            .addAll(
                new FileChooser.ExtensionFilter("TXT files (*.*)", "*.*"),
                new FileChooser.ExtensionFilter("txt files (*.*)", "*.*"));
        
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            //Count the number of lines
                 int x=1;
                  String st;
                    BufferedReader br0;
             try {
                 br0 = new BufferedReader(new FileReader(file));
                  while ((st = br0.readLine()) != null){
                     for(int i=0;i<st.length();i++)
                         if(st.charAt(i)=='+') x++;
                 }
             } catch (FileNotFoundException ex) {
             } catch (IOException ex) {
                 Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
             }
             vv=x;
             label_vm.setText("   The Virtual Machines ("+vv+" VMs )");
                 // in VM there are type of vm and number, CPu and ram and  
                 vmm = new String[5][vv];
                 // same of vmm and pmm but we remove type and changing of mempry and cpu.
                 allVm = new String[6][vv];
            try {
                 BufferedReader br
            = new BufferedReader(new FileReader(file));
                 
                
                 
        // Condition holds true till
        // there is character in a string
       String  txt="" ;
       // this loop is the same of Add_pm
       int z=0; int m =0;
        while ((st = br.readLine()) != null){
            for(int i=0;i<st.length();i++){
                if(st.charAt(i)!=','&& st.charAt(i)!='+'){
                    txt= txt+st.charAt(i);
                }
                else if(st.charAt(i)=='+'){
                   m++;
                   z=0;
                }
                else {
                    vmm[z][m]=txt;
                    
                    z++;
                txt="";
                }
            
            } 
        }        
         int vi=1;
         for(int i=0;i<vv;i++){
             if(vmm[1][i]!=null){
                // add Vm in table view
                Vmcl vmi = new Vmcl("vm"+vi,vmm[2][i],vmm[3][i]+" MB",vmm[1][i]+" G");
                        idvm.getItems().add(vmi);
                        nb_vm++;
                        allVm[0][i]= String.valueOf( vi);
                      allVm[1][i]=vmm[2][i];
                       allVm[2][i]=vmm[3][i];
                     //  allVm[3][i]=vmm[4][i];
                       allVm[4][i]=vmm[1][i];
                        vi++;
                 
            
            }
        }
        
            } catch (IOException ex) {
               
            }
        } 
    }
    
    // for open anthere window
    AnchorPane root1= new AnchorPane();
    @FXML
    StackPane stak;
       @FXML
       Button random1;
       maincontroller mn=new maincontroller();
       @FXML
    void random(ActionEvent event) throws IOException {
              mn.setZ("1");
               String tabVms [][]= new String [4][nb_vm];
              // get % of VM p
              String prce= choice_vmp.getValue();
              // empl: 50% = 50
              String val_p=prce.substring(0,prce.length()-1);
                   // max number of vm by %          
              int num_max=(nb_vm*Integer.parseInt(val_p))/100;
              // number of vms no utilized
               int num2=nb_vm-num_max;    
               String Vm2[][]=new String[6][num2];
              int i3 =0;
              
              // add vm no used in array
              String []T_vm_return= new String[num_max];
              for(int i2=0;i2<nb_vm;i2++){
              if (i2>=num_max){
                                Vm2[0][i3]="vm"+allVm[0][i2];
                                Vm2[1][i3]=allVm[1][i2];
                                Vm2[2][i3]=allVm[2][i2];
                              //   Vm2[3][i3]=String.valueOf(i2);
                                Vm2[4][i3]=allVm[4][i2];
                                i3++;
                            }
              else if(i2<num_max){
                  T_vm_return[i2]=allVm[0][i2];
              }
              }
              
               
              // add line of number vm in list (for shuffling)
                List<String> vmList = Arrays.asList(T_vm_return);
               // shuffle the Vms number in the list
		Collections.shuffle(vmList);
                // return the Vms number in the array
		vmList.toArray(T_vm_return);
                
              // z is number  of pm used ;
                int z=0;
                int  N_vm_cpu=0; int  N_vm_ram=0;
                
               
                String Vm1[][]=new String[6][num_max];
                String Pm1[][]= new String[6][nb_pm];
               
                for(int i=0;i<num_max;i++){
                    //get vm form vms random
                    
                   int xv=Integer.parseInt(T_vm_return[i]);
                  
                  if(z<nb_pm && !allVm[1][xv-1].isEmpty()){
                       Random rd = new Random();
                       // random number from liste of PMs
                          int nbP = rd.nextInt(0,nb_pm);
                          // get ram and cpu of the this pm 
                           int p= Integer.parseInt( allPm[1][nbP]);
                           int p2= Integer.parseInt( allPm[2][nbP])*1024;
                  
                            // total cpu and ramin this pm
                         if(allPm[5][nbP]== null)allPm[5][nbP]="0";
                     N_vm_cpu= Integer.parseInt(allPm[5][nbP])+ Integer.parseInt(allVm[1][xv-1]);
                     
                     if(allPm[6][nbP]== null)allPm[6][nbP]="0";
                     N_vm_ram=Integer.parseInt(allPm[6][nbP])+Integer.parseInt(allVm[2][xv-1]);
                  
                     // it must totl of cpu or ram inf to cpu and ram of pm
                     if(N_vm_cpu <=p&& N_vm_ram<=p2 && nb_vm>0){
                         
                           //add new random list of Vms
                            if(i<num_max){
                                allPm[5][nbP]=String.valueOf(N_vm_cpu);
                                allPm[6][nbP]=String.valueOf(N_vm_ram);
                                 N_vm_cpu=0;
                                 N_vm_ram=0;
                               Vm1[0][i]="vm"+allVm[0][i];
                               tabVms[0][i]="vm"+allVm[0][i];
                               //vm placed in pm number
                               Vm1[3][i]=String.valueOf(nbP+1);
                               tabVms[1][i]=String.valueOf(nbP+1);
                               //cpu of vm
                               Vm1[1][i]=allVm[1][xv-1];
                               // ram of vm
                               Vm1[2][i]=allVm[2][xv-1];
                               //storage of vm
                               Vm1[4][i]=allVm[4][xv-1];
                            }
                           
                            // list of pm
                            
                        }
                      else{
                         N_vm_cpu=0;
                         N_vm_ram=0;
                           z++;
                            i--; 
                         }
                    }  
                }
                for(int i=0;i<nb_pm;i++){
                    if(i<nb_pm){
                                 Pm1[0][i]=String.valueOf(i+1);
                                 Pm1[1][i]=allPm[1][i];
                                 Pm1[2][i]=allPm[2][i];
                                 Pm1[3][i]=allPm[7][i];
                                

                            }
                }
               for(int c=0;c<Pm1[4].length;c++){
                 //cpu using
                   Pm1[4][c]=allPm[5][c];
                   //ram using
                    Pm1[5][c]=allPm[6][c];
               }
                   
                for(int i=0;i<nb_pm;i++){
                allPm[5][i]="0";
                allPm[6][i]="0";
                }
               
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("random.fxml"));
                root1=loader.load();
                 RandomController randomController = loader.
                       getController();
                randomController.get_data(Pm1,Vm1,Vm2);
                   randomController.titel_classification.setText("Random Classification");
                   
                   randomController.listAllVMs(tabVms);
                    randomController.get_date2(Pm1,Vm1,Vm2);
                     Tab tabP1=new Tab();
                     tabP1.setText("random");
                      tabP1.setContent(root1);
                     tabP.getTabs().add(tabP1);
                      tabP.getSelectionModel().select(tabP1);
                   
                    tabVms= new String [4][nb_vm];
                  
    }
    
    private AnchorPane getTablePane(ActionEvent event)  {
   return (AnchorPane) ((Node) event.getSource()).getScene().getRoot();

    }

    @FXML
    void mbfd(ActionEvent event) throws IOException {
        
              // get % of VM p
              String prce= choice_vmp.getValue();
              // empl: 50% = 50
              String val_p=prce.substring(0,prce.length()-1);
                   // max number of vm by %          
              int num_max=(nb_vm*Integer.parseInt(val_p))/100;
              // number of vms no utilized
               int num2=nb_vm-num_max;    
               String Vm2[][]=new String[6][num2];
                String Vm1[][]=new String[6][num_max];
                   String Pm1[][]= new String[6][nb_pm];
              int i3 =0;
              // add vm no used in array
              String []T_vm_return= new String[num_max];
              for(int i2=0;i2<nb_vm;i2++){
              if (i2>=num_max){
                                Vm2[0][i3]="vm"+allVm[0][i2];
                                Vm2[1][i3]=allVm[1][i2];
                                Vm2[2][i3]=allVm[2][i2];
                              //   Vm2[3][i3]=String.valueOf(i2);
                                Vm2[4][i3]=allVm[4][i2];
                                i3++;
                            }
              else if(i2<num_max){
                  T_vm_return[i2]=allVm[0][i2];
              }
              }
              String []percentage_min ={"10%","15%","20%","25%","30%","35%"};
              String []percentage_max ={"65%","70%","80%","85%","90%","95%","97%"};
              //dialog of threshold 
              
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
         ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
         dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
 
         dialog.setResultConverter(new Callback<ButtonType, String>() {
         @Override
         public String call(ButtonType b) {
             if (b == buttonTypeOk) {
                  int total_cpu=0;
            int total_ram=0;
            
            //start MBFD: 
            List<Vmcl> vm_migrated=new  ArrayList<>();
            for(int j=0;j<num_max;j++){
                        vm_migrated.add(new Vmcl(allVm[0][j],allVm[1][j],allVm[2][j],allVm[4][j]));
                    
                }
            String tabVms [][]= new String [4][nb_vm];
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
                    for(int z=0;z<allPm[1].length;z++){
                        //if host has enough resource for vm
                        
                        if(allPm[5][z]==null)allPm[5][z]="0";
                          total_cpu=Integer.parseInt(allPm[5][z])+Integer.parseInt(vm_migrated.get(j).cpu);
                        if(allPm[6][z]==null)allPm[6][z]="0";
                        
                          total_ram=Integer.parseInt(allPm[6][z])+Integer.parseInt(vm_migrated.get(j).ram); 
                          System.out.println(allPm[0][z]);
                          int p2=Integer.parseInt(allPm[2][z])*1024;
                          int p1=Integer.parseInt(allPm[1][z]);
                        if(p1>=total_cpu ){
                            
                            int num=0;
                            num= Integer.parseInt(allPm[2][z])*1024 ;
                           double c1=Integer.parseInt(allPm[1][z]);
                           double c=total_cpu/c1;
                           double k =0.7;
                           double  k1=0.3;
                           double e2=k1*250;
                           double Energy = (k*250) + (e2*c); 
                          
                           if(Energy<minPower){
                               allocatedHost=String.valueOf(z+1);
                               minPower=Energy;
                               total_cpu2=total_cpu;
                               total_ram2=total_ram;
                               
                           }
                        }
                    }

                    if(allocatedHost!=null) {
                               allocate_vm[j]= allocatedHost;
                               allPm[5][Integer.parseInt(allocatedHost)-1]=String.valueOf(total_cpu2);
                               allPm[6][Integer.parseInt(allocatedHost)-1]=String.valueOf(total_ram2);
                               
                           }
                }
 
                  
                   for(int i=0;i<vm_migrated.size();i++){
                       String v=vm_migrated.get(i).vm.toString();

                       for(int j=0;j<Vm1[0].length;j++){
                           if(v.equals(allVm[0][j])){
                               Vm1[0][j]=vm_migrated.get(i).vm.toString();
                               Vm1[1][j]=vm_migrated.get(i).cpu.toString();
                               Vm1[2][j]=vm_migrated.get(i).ram.toString();
                               Vm1[3][j]=allocate_vm[i];
                               Vm1[4][j]=vm_migrated.get(i).vm_storage.toString();
                               tabVms[0][j]=vm_migrated.get(i).vm.toString();
                               tabVms[1][j]=allocate_vm[i];
                              
                           }
                        }
                    }
                   // End MBFD
                    FXMLLoader loader= new FXMLLoader(getClass().getResource("random.fxml"));
                 try {
                     root1=loader.load();
                 } catch (IOException ex) {
                 }
                 RandomController randomController = loader.
                       getController();
                   vm_migrated= new ArrayList<>();
                   
                   //Minimization of Migrations algo
                    int cpu_thre=0;
                     int cpu_thre_max=0;
                    List<Vmcl> old_pm=new  ArrayList<>();
            for(int i=0;i<allPm[1].length;i++){
               // String [][] vm_list= new String [4][vm[0].length];
                List<Vmcl> vm_list=new  ArrayList<>();
                int z=0;
               for(int j=0;j<Vm1[0].length;j++){
                    if(String.valueOf(i+1).equals(Vm1[3][j])){
                        vm_list.add(new Vmcl(Vm1[0][j],Vm1[1][j],Vm1[2][j],Vm1[4][j]));
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
                cpu_thre= (Integer.parseInt(allPm[1][i])*min_threthreshold)/100;
                
                String Tmax= max.getValue().toString();
                String num2=Tmax.substring(0, Tmax.length()-1);
                int max_threthreshold=Integer.parseInt(num2);
                randomController.setSlaV(num2);                
                cpu_thre_max= (Integer.parseInt(allPm[1][i])*max_threthreshold)/100;
                 
                
        
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
              for(int i=0;i<Vm1[0].length;i++){
                  if(Vm1[0][i].equals(vm_migrated.get(j).vm)){
                      
                               int x=Integer.parseInt(Vm1[3][i]);
                               int d=Integer.parseInt(vm_migrated.get(j).cpu.toString());
                               int r = Integer.parseInt(allPm[5][x-1]) -d;
                               allPm[5][x-1]=String.valueOf(r);
                               //deleted ram utilization from this pm of vm deleted
                               int xr=Integer.parseInt(Vm1[3][i]);
                               int dr=Integer.parseInt(vm_migrated.get(j).ram.toString());
                               int rr = Integer.parseInt(allPm[6][xr-1]) -dr;
                               allPm[6][xr-1]=String.valueOf(rr);
                               
                               old_pm.add(new Vmcl(Vm1[0][i],Vm1[1][i],Vm1[2][j],Vm1[3][i]));
                               Vm1[3][i]="0";
                    }
                 }
            
            // end of MM algo
            // mbfd 2
            // sort Decreasing cpu Utilization
             Collections.sort(vm_migrated,new Comparator<Vmcl>(){
                    @Override
                    public int compare(Vmcl o1, Vmcl o2) {
                        return Integer.compare(Integer.parseInt(o1.getCpu()), Integer.parseInt(o2.getCpu()));
                    }
                });
                Collections.reverse(vm_migrated);
                List<String> allocate_vm1=new  ArrayList<>();
                for(int j=0;j<vm_migrated.size();j++){
                    double minPower=250;
                    String allocatedHost=null;
                    int total_cpu2=0;
                    int total_ram2=0;
                    for(int z=0;z<allPm[1].length;z++){
                        //if host has enough resource for vm
                        
                        if(allPm[5][z]==null)allPm[5][z]="0";
                          total_cpu=Integer.parseInt(allPm[5][z])+Integer.parseInt(vm_migrated.get(j).cpu);
                        if(allPm[6][z]==null)allPm[6][z]="0";
                          total_ram=Integer.parseInt(allPm[6][z])+Integer.parseInt(vm_migrated.get(j).ram); 
                          int p2=Integer.parseInt(allPm[2][z])*1024;
                          int p1=Integer.parseInt(allPm[1][z]);
                          String Tmax= max.getValue().toString();
                         String num2=Tmax.substring(0, Tmax.length()-1);
                         int max_threthreshold=Integer.parseInt(num2);
                         cpu_thre_max=(p1*max_threthreshold)/100;
                        if(cpu_thre_max>=total_cpu){
                           int num=0;
                           num= Integer.parseInt(allPm[2][z])*1024 ;
                           double c1=Integer.parseInt(allPm[1][z]);
                           double c=total_cpu/c1;
                           double k =0.7;
                           double  k1=0.3;
                           double e2=k1*250;
                           double Energy = (k*250) + (e2*c); 
                           if(Energy<minPower){
                               allocatedHost= String.valueOf(z+1);
                               

                               minPower=Energy;
                               total_cpu2=total_cpu;
                               total_ram2=total_ram;
                           }
                        }
                    }
                    if(allocatedHost!=null) {
                        allocate_vm1.add(allocatedHost);
                               allPm[5][Integer.parseInt(allocatedHost)-1]=String.valueOf(total_cpu2);
                               allPm[6][Integer.parseInt(allocatedHost)-1]=String.valueOf(total_ram2);
                               String v=vm_migrated.get(j).vm.toString();
                               for(int i=0;i<Vm1[0].length;i++){
                                   if(v.equals(Vm1[0][i])){
                                       Vm1[3][i]=allocatedHost;
                                        tabVms[2][i]=allocatedHost;
                                   }
                               }
                           }
                }
                int SLA=0;
               
                
                for(int j=0;j<old_pm.size();j++)
                for(int i=0;i<Vm1[0].length;i++){
                    if(Vm1[3][i]!=null)
                    if(Vm1[3][i].equals("0")){
                        String c= old_pm.get(j).vm.toString();
                        if(c.equals(Vm1[0][i])){ 
                            Vm1[3][i]=old_pm.get(j).vm_storage.toString();
                             tabVms[2][i]=old_pm.get(j).vm_storage.toString();
                            SLA++;
                            //add cpu
                            int x=Integer.parseInt(Vm1[3][i]);
                               int d=Integer.parseInt(old_pm.get(j).cpu.toString());
                               int r = Integer.parseInt(allPm[5][x-1]) +d;
                               allPm[5][x-1]=String.valueOf(r);
                               //add  ram utilization from this pm of vm deleted
                               int xr=Integer.parseInt(Vm1[3][i]);
                               int dr=Integer.parseInt(old_pm.get(j).ram.toString());
                               int rr = Integer.parseInt(allPm[6][xr-1]) +dr;
                               allPm[6][xr-1]=String.valueOf(rr);
                        }
                    }
                }
            
                   
                     for(int i=0;i<nb_pm;i++){
                    if(i<nb_pm){
                                 Pm1[0][i]=String.valueOf(i+1);
                                 Pm1[1][i]=allPm[1][i];
                                 Pm1[2][i]=allPm[2][i];
                                 Pm1[3][i]=allPm[7][i];
                                

                            }
                }
               for(int c=0;c<Pm1[4].length;c++){
                 //cpu using
                   Pm1[4][c]=allPm[5][c];
                   //ram using
                    Pm1[5][c]=allPm[6][c];
               }
                   vm_migrated.clear();
                for(int i=0;i<nb_pm;i++){
                allPm[5][i]="0";
                allPm[6][i]="0";
                }
                
                
               
                
                randomController.setSla1(String.valueOf(SLA));
                   randomController.setAlVm(String.valueOf(old_pm.size()));
                   randomController.titel_classification.setText("MBFD Classification");
                   randomController.listAllVMs(tabVms);
                   randomController.get_data(Pm1,Vm1,Vm2);
                    randomController.get_date2(Pm1,Vm1,Vm2);
                   
                   Tab tabP1=new Tab();
                     tabP1.setText("MBFD");
                      tabP1.setContent(root1);
                     tabP.getTabs().add(tabP1);
                      tabP.getSelectionModel().select(tabP1);
                   tabVms= new String [4][nb_vm];
                 }
             return null;
             }
         });
          Optional<String> result = dialog.showAndWait();
           
    }
    @FXML
    void firstfit(ActionEvent event) throws IOException {
          mn.setZ("1");
          String tabVms [][]= new String [4][nb_vm];
                    // get % of VM p
              String prce= choice_vmp.getValue();
              // empl: 50% = 50
              String val_p=prce.substring(0,prce.length()-1);
                   // max number of vm by %          
              int num_max=(nb_vm*Integer.parseInt(val_p))/100;
              // number of vms no utilized
               int num2=nb_vm-num_max;    
               String Vm2[][]=new String[6][num2];
              int i3 =0;
              // add vm no used in array
              for(int i2=0;i2<nb_vm;i2++){
              if (i2>=num_max){
                                Vm2[0][i3]="vm"+allVm[0][i2];
                                Vm2[1][i3]=allVm[1][i2];
                                Vm2[2][i3]=allVm[2][i2];
                              //   Vm2[3][i3]=String.valueOf(i2);
                                Vm2[4][i3]=allVm[4][i2];
                                i3++;
                            }
              }
                int z=0;
                int  N_vm_cpu=0; int  N_vm_ram=0;
                
                
                 
                String Vm1[][]=new String[6][num_max];
                String Pm1[][]= new String[6][nb_pm];
                int i2=0;
                
                for(int i=0;i<nb_vm;i++){
                    
                int xv=Integer.parseInt(allVm[0][i]);
                if(z<nb_pm && !allVm[1][xv-1].isEmpty()){
                if(allPm[5][z]== null)allPm[5][z]="0";
                     N_vm_cpu= Integer.parseInt(allPm[5][z])+ Integer.parseInt(allVm[1][i]);
                     
                     if(allPm[6][z]== null)allPm[6][z]="0";
                     N_vm_ram=Integer.parseInt(allPm[6][z])+Integer.parseInt(allVm[2][i]);
                
                int p= Integer.parseInt( allPm[1][z]);
                int p2= Integer.parseInt( allPm[2][z])*1024;
                if(N_vm_cpu <=p&& N_vm_ram<p2 && nb_vm>0){
                     if(i<num_max){
                       allPm[5][z]=String.valueOf(N_vm_cpu);
                       allPm[6][z]=String.valueOf(N_vm_ram);

                     
                          //vm1[3] is the number of pm 
                        Vm1[3][i]=String.valueOf(z+1);
                        Vm1[0][i]="vm"+allVm[0][i];
                        Vm1[1][i]=allVm[1][i];
                        Vm1[2][i]=allVm[2][i];
                        Vm1[4][i]=allVm[4][i];
                        tabVms[0][i]="vm"+allVm[0][i];
                        tabVms[1][i]=String.valueOf(z+1);
                            }
                        
                        if(i<nb_pm){
                        Pm1[0][i]=String.valueOf(i+1);
                         Pm1[1][i]=allPm[1][i];
                         Pm1[2][i]=allPm[2][i];
                         Pm1[3][i]=allPm[7][i];
                         
             
                        }

                
                }
                else{
                N_vm_cpu=0;
                N_vm_ram=0;
                z++;
                i--;
                
                }
                }
                
                
                
            }
                for(int c=0;c<Pm1[4].length;c++){

                   Pm1[4][c]=allPm[5][c];
                   Pm1[5][c]=allPm[6][c];
               
               }
                          
                  for(int i=0;i<nb_pm;i++){
                allPm[5][i]="0";
                allPm[6][i]="0";
                }
               
                       // FXMLLoader fxml= new FXMLLoader(getClass().getResource("random.fxml"));
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("random.fxml"));
                 
                 root1=loader.load();
                 RandomController randomController = loader.
                       getController();
               
                randomController.get_data(Pm1, Vm1,Vm2);
                randomController.get_date2(Pm1,Vm1,Vm2);
                randomController.titel_classification.setText("first fit Classification");
                randomController.listAllVMs(tabVms);
                    
                   Tab tabP1=new Tab();
                     tabP1.setText("first fit");
                      tabP1.setContent(root1);
                     tabP.getTabs().add(tabP1);
                      tabP.getSelectionModel().select(tabP1);
                      
                   tabVms= new String [4][nb_vm];
                   
    }
       @FXML
         void  AG(ActionEvent event) throws IOException{
             String[][] Vm1= new String[5][nb_vm];
            String[][] Pm1= new String[4][nb_pm];

              // get % of VM p
              String prce= choice_vmp.getValue();
              // empl: 50% = 50
              String val_p=prce.substring(0,prce.length()-1);
              
               Dialog<String> dialog = new Dialog<>();
           dialog.setTitle("genetic algorithm");
          dialog.setHeaderText(" genetic algorithm parameter");
           dialog.setResizable(true);
           String [] tallieSelc={"30%","50%","60%","70%","80%","100%"};
           Label label2 = new Label("the crossover size : ");
           ChoiceBox selection = new ChoiceBox();
           String []percentage_max ={"65%","70%","80%","85%","90%","95%","97%"};
           Label label0 = new Label("threshold : ");
           ChoiceBox mutation = new ChoiceBox();
           mutation.getItems().addAll(percentage_max);
           Label label1 = new Label("population size: ");
           TextField Pop = new TextField();
           RadioButton fit1 = new RadioButton();
           RadioButton fit2 = new RadioButton();
           fit1.setText("fitness 1");
           fit2.setText("fitness 2");
           selection.getItems().addAll(tallieSelc);
          GridPane grid = new GridPane();
           grid.add(label0, 1, 1);
           grid.add(mutation, 2, 1);
            grid.add(label1, 1, 2);
            grid.add(Pop, 2, 2);
            grid.add(label2, 1, 3);
            grid.add(selection, 2, 3);
            grid.add(fit1, 1, 4);
            grid.add(fit2, 1, 5);
            ToggleGroup grp=new ToggleGroup();
            fit1.setToggleGroup(grp);
            fit2.setToggleGroup(grp);
         dialog.getDialogPane().setContent(grid);
         ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
         dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
 
         dialog.setResultConverter(new Callback<ButtonType, String>() {
         @Override
         public String call(ButtonType b) {
             if (b == buttonTypeOk) {
                 FXMLLoader loader0= new FXMLLoader(getClass().getResource("AgPage.fxml"));
                 try {
                     root1=loader0.load();
                 } catch (IOException ex) {
                     Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 AgPageController gc = loader0.getController();
                 if(fit1.isSelected())
                     gc.setFittChoice(1);
                 else if(fit2.isSelected())
                     gc.setFittChoice(2);
                  String Vselection= selection.getValue().toString();
                String num=Vselection.substring(0, Vselection.length()-1);
                int Vselec=Integer.parseInt(num);
                   gc.setNumberSelect(Vselec);
                   gc.setRandomDegrry(Integer.parseInt(val_p));
                   gc.setTaillePop(Integer.parseInt(Pop.getText().toString()));
                   gc. InitializationFF(allVm,allPm);
                   gc. InitializationRandom(allVm,allPm);
                   gc.InitializationBFD(allVm, allPm);
                    
                   Tab tabP1=new Tab();
                     tabP1.setText("AG");
                      tabP1.setContent(root1);
                     tabP.getTabs().add(tabP1);
                      tabP.getSelectionModel().select(tabP1);
             }
              return null;
         }
         });         
            Optional<String> result = dialog.showAndWait();   
         }
    private String []percentage={"10%","20%","30%","40%","50%","100%"};
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        label_pm.setVisible(false);
        label_vm.setVisible(false);
        choice_vmp.setVisible(false);
        lebel_spnr.setVisible(false);
        lebel_spnr1.setVisible(false);
        
        choice_vmp.getItems().addAll(percentage);
        
        idpm.setVisible(false);
        idvm.setVisible(false);
       // vm_N.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("vm"));
        // pm_N.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("pm"));
        pm_storage.setCellValueFactory(new PropertyValueFactory<pmCl,Integer>("pm_storage"));
          pm.setCellValueFactory(new PropertyValueFactory<pmCl,String>("pm"));
            pm_ram.setCellValueFactory(new PropertyValueFactory<pmCl,Integer>("ram"));
              pm_eng.setCellValueFactory(new PropertyValueFactory<pmCl,Integer>("energy"));
                pm_cpu.setCellValueFactory(new PropertyValueFactory<pmCl,Integer>("cpu"));
                                pm_state.setCellValueFactory(new PropertyValueFactory<pmCl,String>("state"));
                                 vm.setCellValueFactory(new PropertyValueFactory<Vmcl,String>("vm"));
            vm_ram.setCellValueFactory(new PropertyValueFactory<Vmcl,Integer>("ram"));
                vm_cpu.setCellValueFactory(new PropertyValueFactory<Vmcl,Integer>("cpu"));
                vm_storage.setCellValueFactory(new PropertyValueFactory<Vmcl,Integer>("vm_storage"));

    }

  

}
