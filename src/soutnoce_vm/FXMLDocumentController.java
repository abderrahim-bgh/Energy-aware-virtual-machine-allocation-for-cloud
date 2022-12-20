
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
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    private TableColumn<Vmcl, String> vm;

    @FXML
    private TableColumn<Vmcl, Integer> vm_cpu;

    @FXML
    private TableColumn<Vmcl, Integer> vm_eng;

    @FXML
    private TableColumn<Vmcl, Integer> vm_ram;
    
    @FXML
    private TableColumn<PmVmCl, String> vm_N;
      
    @FXML
    private TableColumn<PmVmCl, String> pm_N;
    @FXML
   // private TableView<PmVmCl> vm_in_pm;
    
   
    String vmm[][] = new String[5][100];
        String pmm[][] = new String[6][100];
        int nb_vm=0;
        int nb_pm=0;
        
                String allVm[][] = new String[5][100];
                 String allPm[][] = new String[6][100];

   
    
        
    @FXML
    void add_pm(ActionEvent event) {
         idpm.setVisible(true);
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
                    pmm[z][m]=txt;
                    
                    
                   // System.out.println(txt);
                    z++;
                txt="";
                }
            
            } 
        }
        
         int vi=1;
         for(int i=0;i<100;i++){
             if(pmm[2][i]!=null){
             int r = Integer.parseInt(pmm[1][i]);

            for(int j=0;j<r;j++){
               
              //  for(int z=0;z< Integer.parseInt(vmm[1][i]);z++){
                pmCl ipm = new pmCl("pm"+vi,pmm[2][i],pmm[3][i],pmm[4][i],pmm[5][i]);
                        idpm.getItems().add(ipm);
                        nb_pm++;
                        allPm[0][i]= String.valueOf( vi);
                      allPm[1][i]=pmm[2][i];
                       allPm[2][i]=pmm[3][i];
                       allPm[3][i]=pmm[4][i];
                       allPm[4][i]=pmm[5][i];
                        vi++;
                 
            }
            }
        }
        
                // Files.lines(file.toPath()).forEach(System.out::println);
            } catch (IOException ex) {
               
            }
        }
    }

    @FXML
    void add_vm(ActionEvent event) {
        
        idvm.setVisible(true);
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
         for(int i=0;i<100;i++){
             if(vmm[1][i]!=null){
            
               
              //  for(int z=0;z< Integer.parseInt(vmm[1][i]);z++){
                Vmcl vmi = new Vmcl("vm"+vi,vmm[2][i],vmm[3][i],"on",vmm[4][i]);
                        idvm.getItems().add(vmi);
                        nb_vm++;
                        allVm[0][i]= String.valueOf( vi);
                      allVm[1][i]=vmm[2][i];
                       System.out.println(vmm[2][i]+" "+ allVm[1][i]);
                       allVm[2][i]=vmm[3][i];
                       allVm[3][i]=vmm[4][i];
                        vi++;
                 
            
            }
        }
        
                // Files.lines(file.toPath()).forEach(System.out::println);
            } catch (IOException ex) {
               
            }
        }
        
        
    }
     Random rd = new Random();
      Set<Integer>set = new LinkedHashSet<Integer>();
    Parent root1;
       @FXML
    void random(ActionEvent event) throws IOException {
           
                
                // vm_in_pm.setVisible(true);
                for(int i=0;i<nb_pm;i++){
                for(int j=0;j<nb_vm;j++){
                // int elemnt= ;
                set.add(rd.nextInt(nb_vm));
                
                
                }
                }
                System.out.println(set);
                Integer[] array = new Integer[set.size()];
                int k = 0;
                for (Integer i: set) {
                array[k++] = i;
                }
                // System.out.print(elemnt[1]+elemnt[2]+elemnt[5]);
                
                int z=0;
                int  N_vm_cpu=0; int  N_vm_ram=0;
                
                for(int i=0;i<nb_vm;i++){
                System.out.println(nb_vm +" "+ allVm[0][i]);
                }
                String Vm1[]=new String[nb_vm];
                String Pm1[]= new String[nb_vm];
                for(int i=0;i<nb_vm;i++){
                int xv=array[i];
                if(z<nb_pm && !allVm[1][xv].isEmpty()){
                
                N_vm_cpu= N_vm_cpu+ Integer.parseInt(allVm[1][xv]);
                // System.out.println(N_vm_cpu +" "+Integer.parseInt( allPm[1][i]));
                int p= Integer.parseInt( allPm[1][z]);
                if(N_vm_cpu <=p && nb_vm>0){
                    
                        Vm1[i]="vm"+(array[i]+1);
                        Pm1[i]="pm"+(z+1);
                PmVmCl pv= new PmVmCl("pm"+(z+1),"vm"+(array[i]+1));
               
                
            
              
              //  vm_in_pm.getItems().add(pv);
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
               
                randomController.get_data(Pm1, Vm1);
                   
                   Stage stage =new Stage();
                   stage.setTitle("random");
                   stage.setScene(new Scene(root1));
                   stage.show();
           

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idpm.setVisible(false);
        idvm.setVisible(false);
       // vm_N.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("vm"));
        // pm_N.setCellValueFactory(new PropertyValueFactory<PmVmCl,String>("pm"));
          pm.setCellValueFactory(new PropertyValueFactory<pmCl,String>("pm"));
            pm_ram.setCellValueFactory(new PropertyValueFactory<pmCl,Integer>("ram"));
              pm_eng.setCellValueFactory(new PropertyValueFactory<pmCl,Integer>("energy"));
                pm_cpu.setCellValueFactory(new PropertyValueFactory<pmCl,Integer>("cpu"));
                                pm_state.setCellValueFactory(new PropertyValueFactory<pmCl,String>("state"));
                                 vm.setCellValueFactory(new PropertyValueFactory<Vmcl,String>("vm"));
            vm_ram.setCellValueFactory(new PropertyValueFactory<Vmcl,Integer>("ram"));
              vm_eng.setCellValueFactory(new PropertyValueFactory<Vmcl,Integer>("energy"));
                vm_cpu.setCellValueFactory(new PropertyValueFactory<Vmcl,Integer>("cpu"));

    }

  

}
