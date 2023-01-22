
package soutnoce_vm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    private TableColumn<Vmcl, Integer> vm_eng;

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
    private ChoiceBox<String> choice_vmp;
   
    //number of VMs
    int vv=290;
    //number of PMs
    int pp = 100;
    // in VM there are type of vm and number, CPu and ram and energy 
    String vmm[][] = new String[5][vv];
        // in VM there are type of vm and number, CPu and ram, State and energy 
        String pmm[][] = new String[6][pp];
        int nb_vm=0;
        int nb_pm=0;
        
        // same of vmm and pmm but we remove type and changing of mempry and cpu.
                String allVm[][] = new String[6][vv];
                 String allPm[][] = new String[8][pp];

   
    
        
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
            try {
                 BufferedReader br
            = new BufferedReader(new FileReader(file));
                 
                 String st;
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
        
                // Files.lines(file.toPath()).forEach(System.out::println);
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
        // open file 
 FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
            .addAll(
                new FileChooser.ExtensionFilter("TXT files (*.*)", "*.*"),
                new FileChooser.ExtensionFilter("txt files (*.*)", "*.*"));
        
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                 BufferedReader br
            = new BufferedReader(new FileReader(file));
                 
                 String st;
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
               // System.out.println("typ2");
                   m++;
                   z=0;
                }
                else {
                    vmm[z][m]=txt;
                    
                    
                   // System.out.println(txt);
                    z++;
                txt="";
                }
            
            } 
        }        
         int vi=1;
         for(int i=0;i<vv;i++){
             if(vmm[1][i]!=null){
                // add Vm in table view
                Vmcl vmi = new Vmcl("vm"+vi,vmm[2][i],vmm[3][i]+" MB",vmm[1][i]+" G",vmm[4][i]);
                        idvm.getItems().add(vmi);
                        nb_vm++;
                        allVm[0][i]= String.valueOf( vi);
                      allVm[1][i]=vmm[2][i];
                       System.out.println(vmm[2][i]+" "+ allVm[1][i]);
                       allVm[2][i]=vmm[3][i];
                       allVm[3][i]=vmm[4][i];
                       allVm[4][i]=vmm[1][i];
                        vi++;
                 
            
            }
        }
        
                // Files.lines(file.toPath()).forEach(System.out::println);
            } catch (IOException ex) {
               
            }
        } 
    }
    
    // for open anthere window
    Parent root1;
    
       @FXML
    void random(ActionEvent event) throws IOException {
              // get % of VM p
              String prce= choice_vmp.getValue();
              // empl: 50% = 50
              String val_p=prce.substring(0,2);
                // copy all vms
               String []T_vm_return= new String[allVm[0].length];
               List<String> vmLs = Arrays.asList(allVm[0]);
               vmLs.toArray(T_vm_return);
              // add line of number vm in list 
                List<String> vmList = Arrays.asList(allVm[0]);
               // shuffle the Vms number in the list
		Collections.shuffle(vmList);
                // return the Vms number in the array
		vmList.toArray(allVm[0]);
                
                System.out.println(allVm[0]);
              
                int z=0;
                int  N_vm_cpu=0; int  N_vm_ram=0;
                
                for(int i=0;i<nb_vm;i++){
                System.out.println(nb_vm +" "+ allVm[0][i]+" "+allVm[0].length);
                }
                String Vm1[][]=new String[6][nb_vm];
                String Pm1[][]= new String[4][nb_pm];
               int num_max=(nb_pm*Integer.parseInt(val_p))/100;
                for(int i=0;i<nb_vm;i++){
                   int xv=Integer.parseInt(allVm[0][i]);
                  if(z<nb_pm && !allVm[1][xv-1].isEmpty()){
                       Random rd = new Random();
                          int nbP = rd.nextInt(0,nb_pm);
                           int p= Integer.parseInt( allPm[1][nbP]);
                           int p2= Integer.parseInt( allPm[2][z])*1024;
                            if(allPm[5][nbP]== null)allPm[5][nbP]="0";
                     N_vm_cpu= Integer.parseInt(allPm[5][nbP])+ Integer.parseInt(allVm[1][xv-1]);
                     allPm[5][nbP]=String.valueOf(N_vm_cpu);
                     if(allPm[6][nbP]== null)allPm[6][nbP]="0";
                     N_vm_ram=Integer.parseInt(allPm[6][nbP])+Integer.parseInt(allVm[2][xv-1]);
                     allPm[6][nbP]=String.valueOf(N_vm_ram);
                      
                     if(Integer.parseInt(allPm[5][nbP]) <=p&& N_vm_ram<=p2 && nb_vm>0){
                            N_vm_cpu=0;
                            N_vm_ram=0;
                            Vm1[3][i]=String.valueOf(nbP+1);
                            System.out.println("nbP "+allPm[5][nbP]+" "+allPm[1][nbP]+" "+p);
                            if(i<num_max){
                            Vm1[0][i]="vm"+allVm[0][i];
                            }
                            else{
                                Vm1[5][i]="vm"+allVm[0][i];
                            }
                            Vm1[1][i]=allVm[1][xv-1];
                            Vm1[2][i]=allVm[2][xv-1];
                            Vm1[4][i]=allVm[4][i];
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
                
                for(int i=0;i<nb_pm;i++){
                allPm[5][i]="0";
                allPm[6][i]="0";
                }
               
                       // FXMLLoader fxml= new FXMLLoader(getClass().getResource("random.fxml"));
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("random.fxml"));
                root1=loader.load();
                 RandomController randomController = loader.
                       getController();
               
                randomController.get_data(Pm1[0], Vm1[0],Vm1[5],Vm1[1], Vm1[2],Pm1[1],Pm1[2],Vm1[3],Vm1[4],Pm1[3]);
                   randomController.titel_classification.setText("Random Classification");
                   Stage stage =new Stage();
                   stage.setTitle("random");
                  
                   stage.setScene(new Scene(root1));
                   stage.show();
                   // return vm no random
            List<String> vmLs1 = Arrays.asList(T_vm_return);
               vmLs1.toArray(allVm[0]);

    }
    
    @FXML
    void firstfit(ActionEvent event) throws IOException {
          
                System.out.println(allVm[0]);
              
                
                int z=0;
                int  N_vm_cpu=0; int  N_vm_ram=0;
                
                for(int i=0;i<nb_vm;i++){
                System.out.println(nb_vm +" "+ allVm[0][i]+" "+allVm[0].length);
                }
                String Vm1[][]=new String[6][nb_vm];
                String Pm1[][]= new String[4][nb_pm];
                for(int i=0;i<nb_vm;i++){
                int xv=Integer.parseInt(allVm[0][i]);
                if(z<nb_pm && !allVm[1][xv-1].isEmpty()){
                
                N_vm_cpu= N_vm_cpu+ Integer.parseInt(allVm[1][i]);
                N_vm_ram=N_vm_ram+Integer.parseInt(allVm[2][i]);
                int p= Integer.parseInt( allPm[1][z]);
                int p2= Integer.parseInt( allPm[2][z])*1024;
                if(N_vm_cpu <=p&& N_vm_ram<p2 && nb_vm>0){
                        Vm1[3][i]=String.valueOf(z+1);
                        Vm1[0][i]="vm"+allVm[0][i];
                        Vm1[1][i]=allVm[1][i];
                        Vm1[2][i]=allVm[2][i];
                        Vm1[4][i]=allVm[4][i];

                        
                        Pm1[0][z]=String.valueOf(z+1);
                         Pm1[1][z]=allPm[1][z];
                         Pm1[2][z]=allPm[2][z];
                         Pm1[3][z]=allPm[7][z];

                
                }
                else{
                N_vm_cpu=0;
                N_vm_ram=0;
                z++;
                i--;
                
                }
                }
                
                
                
            }
               
                       // FXMLLoader fxml= new FXMLLoader(getClass().getResource("random.fxml"));
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("random.fxml"));
                root1=loader.load();
                 RandomController randomController = loader.
                       getController();
               
                randomController.get_data(Pm1[0], Vm1[0],Vm1[5],Vm1[1], Vm1[2],Pm1[1],Pm1[2],Vm1[3],Vm1[4],Pm1[3]);
                randomController.titel_classification.setText("first fit Classification");
                   Stage stage =new Stage();
                   stage.setTitle("random");
                   stage.setScene(new Scene(root1));
                   stage.show();
    }
    
    private String []percentage={"10%","20%","30%","40%","50%","100%"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        label_pm.setVisible(false);
        label_vm.setVisible(false);
        choice_vmp.setVisible(false);
                lebel_spnr.setVisible(false);

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
              vm_eng.setCellValueFactory(new PropertyValueFactory<Vmcl,Integer>("energy"));
                vm_cpu.setCellValueFactory(new PropertyValueFactory<Vmcl,Integer>("cpu"));
                vm_storage.setCellValueFactory(new PropertyValueFactory<Vmcl,Integer>("vm_storage"));

    }

  

}
