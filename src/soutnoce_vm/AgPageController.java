/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package soutnoce_vm;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * @author abderrahimA
 */
public class AgPageController implements Initializable {

    @FXML
    private Label ll;

    @FXML
    private TableColumn<ag_coding, String> pm_RFF;


    @FXML
    private TableView<ag_coding> rff;

    @FXML
    private TableColumn<ag_coding, String> vm_RFF;

    @FXML
    private TableView<showIdivFit> fitt;
    @FXML
    private TableColumn<showIdivFit,String> idivid;
    @FXML
    private TableColumn<showIdivFit,String> fit;
    @FXML
    private TableColumn<showIdivFit,String> nb;      
    
             List<List> initialPop= new ArrayList<>();         
    public void InitializationBFD(String [][]Vm1,String[][] Pm1){
         System.out.println("bfd");
        for(int p=0;p<30;p++){
         List<individu> individual=new  ArrayList<>();
         int total_cpu=0;
         int total_ram=0;  
         List<Vmcl> vm_migrated=new  ArrayList<>();
         String[][] Vm2= random(Vm1,Pm1);
          
            for(int j=0;j<Vm1[0].length;j++){
                        vm_migrated.add(new Vmcl(Vm1[0][j],Vm1[1][j],Vm1[2][j],Vm1[4][j]));            
                }
            String tabVms [][]= new String [4][Vm1[0].length];
            //mbfd
            Collections.sort(vm_migrated,new Comparator<Vmcl>(){
                    @Override
                    public int compare(Vmcl o1, Vmcl o2) {
                        return Integer.compare(Integer.parseInt(o1.getCpu()), Integer.parseInt(o2.getCpu()));
                    }
                });
                Collections.reverse(vm_migrated);
              //  String [] allocate_vm=new String[vm_migrated.size()];
                for(int j=0;j<vm_migrated.size();j++){
                    
             
                    double minPower=250;
                    String allocatedHost=null;
                    int total_cpu2=0;
                    int total_ram2=0;
                    for(int z=0;z<Pm1[1].length;z++){
                        //if host has enough resource for vm
                        
                        if(Pm1[5][z]==null)Pm1[5][z]="0";
                          total_cpu=Integer.parseInt(Pm1[5][z])+Integer.parseInt(vm_migrated.get(j).cpu);
                        if(Pm1[6][z]==null)Pm1[6][z]="0";
                          total_ram=Integer.parseInt(Pm1[6][z])+Integer.parseInt(vm_migrated.get(j).ram); 
                          int p2=Integer.parseInt(Pm1[2][z])*1024;
                          int p1=Integer.parseInt(Pm1[1][z]);
                        if(p1>=total_cpu ){
                            
                            int num=0;
                            num= Integer.parseInt(Pm1[2][z])*1024 ;
                           double c1=Integer.parseInt(Pm1[1][z]);
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
                            //   allocate_vm[j]= allocatedHost; 
                               vm_migrated.get(j).setVm_storage(allocatedHost);
                               Pm1[5][Integer.parseInt(allocatedHost)-1]=String.valueOf(total_cpu2);
                               Pm1[6][Integer.parseInt(allocatedHost)-1]=String.valueOf(total_ram2);
                               
                           }
                }
 
                 Collections.sort(vm_migrated,new Comparator<Vmcl>(){
                    @Override
                    public int compare(Vmcl o1, Vmcl o2) {
                        return Integer.compare(Integer.parseInt(o1.getVm()), Integer.parseInt(o2.getVm()));
                    }
                });
                  
                   for(int i=0;i<vm_migrated.size();i++){
                        
                       String v=vm_migrated.get(i).vm.toString();

                       for(int j=0;j<Vm1[0].length;j++){
                           
                           if(v.equals(Vm1[0][j])){
                               if(i<Vm2[0].length){
                            Vm1[3][j]=Vm2[3][j];
                            vm_migrated.get(i).vm_storage=Vm2[3][j];
                        }
                          
                        
                        else{
                                Vm1[0][j]=vm_migrated.get(i).vm.toString();
                               Vm1[1][j]=vm_migrated.get(i).cpu.toString();
                               Vm1[2][j]=vm_migrated.get(i).ram.toString();
                               Vm1[3][j]=vm_migrated.get(i).vm_storage.toString();
                               
                               
                               //Vm1[4][j]=vm_migrated.get(i).vm_storage.toString();
                               tabVms[0][j]=vm_migrated.get(i).vm.toString();
                               tabVms[1][j]=vm_migrated.get(i).vm_storage;
                               }
                               if(p==0){
                               ag_coding coding=new ag_coding(vm_migrated.get(i).vm.toString(), vm_migrated.get(i).vm_storage);
                                     // RBFD.getItems().add(coding);
                               }
                                      individu e= new individu(vm_migrated.get(i).vm.toString(), vm_migrated.get(i).cpu.toString(),
                                              vm_migrated.get(i).ram.toString(), Vm1[4][j], Vm1[3][j],"Random Best-fit decreasing ");
                                      individual.add(e);
                           }
                        }
                      
                    }
        
                   initialPop.add(individual);
                   vm_migrated.clear();
   
                   for(int z1=0;z1<Pm1[5].length;z1++){
                       Pm1[5][z1]="0";
                       Pm1[6][z1]="0";
                   }
     }
                  for(int i=0;i<initialPop.size();i++){
                      showIdivFit sh=new showIdivFit(String.valueOf(i+1),initialPop.get(i).toString(), String.valueOf(i));
                      fitt.getItems().add(sh);
                    //  idivid.getColumns().add(initialPop.get(i).toString());
                  }
                  
                System.out.println("pop"+initialPop.get(61).toString());
                 System.out.println("pop"+initialPop.get(62).toString());
                  System.out.println("pop"+initialPop.get(63).toString());

    }
    
    public void InitializationRandom(String [][]Vm1,String[][] Pm1){   
         System.out.println("rd");
                 for(int pp=0;pp<30;pp++){
                   
                   // z is number  of pm used ;
                int z=0;
                int  N_vm_cpu=0; int  N_vm_ram=0;
                String tabVms [][]= new String [4][Vm1[0].length];
                 String []T_vm_return= new String[Vm1[0].length];
                 
                  for(int i2=0;i2<Vm1[0].length;i2++){
             
                  T_vm_return[i2]=Vm1[0][i2];
              
              }
              // add line of number vm in list (for shuffling)
                List<String> vmList = Arrays.asList(T_vm_return);
               // shuffle the Vms number in the list
		Collections.shuffle(vmList);
                // return the Vms number in the array
		vmList.toArray(T_vm_return);
               
                for(int i=0;i<Vm1[0].length;i++){
                    //get vm form vms random
                    
                   int xv=Integer.parseInt(T_vm_return[i]);
                  
                  if(z<Pm1[0].length && !Vm1[1][xv-1].isEmpty()){
                       Random rd = new Random();
                       // random number from liste of PMs
                          int nbP = rd.nextInt(0,Pm1[0].length );
                          // get ram and cpu of the this pm 
                           int p= Integer.parseInt( Pm1[1][nbP]);
                           int p2= Integer.parseInt( Pm1[2][nbP])*1024;
                  
                            // total cpu and ramin this pm
                         if(Pm1[5][nbP]== null)Pm1[5][nbP]="0";
                     N_vm_cpu= Integer.parseInt(Pm1[5][nbP])+ Integer.parseInt(Vm1[1][xv-1]);
                     
                     if(Pm1[6][nbP]== null)Pm1[6][nbP]="0";
                     N_vm_ram=Integer.parseInt(Pm1[6][nbP])+Integer.parseInt(Vm1[2][xv-1]);
                  
                     // it must totl of cpu or ram inf to cpu and ram of pm
                     if(N_vm_cpu <=p&& N_vm_ram<=p2 && Vm1[0].length>0){
                         
                           //add new random list of Vms
                            if(i<Vm1[0].length){
                                Pm1[5][nbP]=String.valueOf(N_vm_cpu);
                                Pm1[6][nbP]=String.valueOf(N_vm_ram);
                                 N_vm_cpu=0;
                                 N_vm_ram=0;
                               tabVms[0][i]=Vm1[0][i];
                               //vm placed in pm number
                               Vm1[3][xv-1]=String.valueOf(nbP+1);
                               tabVms[1][xv-1]=String.valueOf(nbP+1);
                                 
                               
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
                if(pp==0)
                for(int i=0;i<Vm1[0].length;i++){
                    ag_coding coding=new ag_coding(String.valueOf(i+1), String.valueOf(Vm1[3][i]));
                                    //  random.getItems().add(coding);
                }
           for(int z1=0;z1<Pm1[5].length;z1++){
                   Pm1[5][z1]="0";
                    Pm1[6][z1]="0";
                }
           List<individu> individual=new  ArrayList<>();
               for(int i=0;i<Vm1[0].length;i++){
                   individu e=new individu(Vm1[0][i], Vm1[1][i], Vm1[2][i], Vm1[4][i], Vm1[3][i],"Random");
                   individual.add(e);
               }
               initialPop.add(individual);
        }
                  System.out.println("pop"+initialPop.get(31).toString());
                 System.out.println("pop"+initialPop.get(32).toString());
                  System.out.println("pop"+initialPop.get(33).toString());

    }
    
        public String [][] random(String [][]Vm2,String[][] Pm1){
           int nb_vm = (Vm2[0].length*20)/100;
            int z=0;
               
                String[][] Vm1=new String[Vm2.length][nb_vm];
                int  N_vm_cpu=0; int  N_vm_ram=0;
                String tabVms [][]= new String [4][Vm1[0].length];
                 String []T_vm_return= new String[Vm1[0].length];
              

                 for(int i2=0;i2<nb_vm;i2++){
              
                     Random rd = new Random();
                     int k = rd.nextInt(Vm2[0].length-1);
                                Vm1[0][i2]=Vm2[0][i2];
                                Vm1[1][i2]=Vm2[1][i2];
                                Vm1[2][i2]=Vm2[2][i2];
                              //   Vm2[3][i3]=String.valueOf(i2);
                                Vm1[4][i2]=Vm2[4][i2];
                           
                  T_vm_return[i2]=Vm2[0][i2];
              
              }
                   // z is number  of pm used ;
               
                  for(int i2=0;i2<Vm1[0].length;i2++){
             
                  T_vm_return[i2]=Vm1[0][i2];
              
              }
              // add line of number vm in list (for shuffling)
                List<String> vmList = Arrays.asList(T_vm_return);
               // shuffle the Vms number in the list
		Collections.shuffle(vmList);
                // return the Vms number in the array
		vmList.toArray(T_vm_return);
               
                for(int i=0;i<Vm1[0].length;i++){
                    //get vm form vms random
                  
                   int xv=Integer.parseInt(T_vm_return[i]);
                  
                  if(z<Pm1[0].length && !Vm1[1][xv-1].isEmpty()){
                       Random rd = new Random();
                       // random number from liste of PMs
                          int nbP = rd.nextInt(0,Pm1[0].length );
                          // get ram and cpu of the this pm 
                           int p= Integer.parseInt( Pm1[1][nbP]);
                           int p2= Integer.parseInt( Pm1[2][nbP])*1024;
                  
                            // total cpu and ramin this pm
                         if(Pm1[5][nbP]== null)Pm1[5][nbP]="0";
                     N_vm_cpu= Integer.parseInt(Pm1[5][nbP])+ Integer.parseInt(Vm1[1][xv-1]);
                     
                     if(Pm1[6][nbP]== null)Pm1[6][nbP]="0";
                     N_vm_ram=Integer.parseInt(Pm1[6][nbP])+Integer.parseInt(Vm1[2][xv-1]);
                  
                     // it must totl of cpu or ram inf to cpu and ram of pm
                     if(N_vm_cpu <=p&& N_vm_ram<=p2 && Vm1[0].length>0){
                         
                           //add new random list of Vms
                            if(i<Vm1[0].length){
                                Pm1[5][nbP]=String.valueOf(N_vm_cpu);
                                Pm1[6][nbP]=String.valueOf(N_vm_ram);
                                 N_vm_cpu=0;
                                 N_vm_ram=0;
                               tabVms[0][i]=Vm1[0][i];
                               //vm placed in pm number
                               Vm1[3][xv-1]=String.valueOf(nbP+1);
                               tabVms[1][xv-1]=String.valueOf(nbP+1);
                                 
                               
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
               
                return Vm1;
               
    }

        
    public void InitializationFF(String [][]Vm1,String[][] Pm1){
        System.out.println("ff");
        for(int pp=0;pp<30;pp++){
         List<individu> individual=new  ArrayList<>();
       String[][] Vm2= random(Vm1,Pm1);

        int z=0;
        int  N_vm_cpu=0; int  N_vm_ram=0;
                
         for(int i=0;i<Vm1[0].length;i++){
             if(i<Vm2[0].length)
                 Vm1[3][i]=Vm2[3][i];
             else{
               // int xv=Integer.parseInt(Vm1[0][i]);
               if(Vm1[0][i]!=null){
                if(z<Pm1[0].length && !Vm1[0][i].isEmpty()){
                if(Pm1[5][z]== null)Pm1[5][z]="0";
                     N_vm_cpu= Integer.parseInt(Pm1[5][z])+ Integer.parseInt(Vm1[1][i]);
                     
                     if(Pm1[6][z]== null)Pm1[6][z]="0";
                     N_vm_ram=Integer.parseInt(Pm1[6][z])+Integer.parseInt(Vm1[2][i]);
                
                int p= Integer.parseInt( Pm1[1][z]);
                int p2= Integer.parseInt( Pm1[2][z])*1024;
                
                if(N_vm_cpu <=p&& N_vm_ram<p2 && Vm1[0].length>0){
                  
                       Pm1[5][z]=String.valueOf(N_vm_cpu);
                       Pm1[6][z]=String.valueOf(N_vm_ram);
                      
                          //vm1[3] is the number of pm 
                          
                              Vm1[3][i]=String.valueOf(z+1);
                
                }
                else{
                N_vm_cpu=0;
                N_vm_ram=0;
                z++;
                i--;
                
                }
                }
                
                   }
             }
            }
              
        for(int i=0;i<Vm1[0].length;i++){
             individu e=new individu(Vm1[0][i], Vm1[1][i], Vm1[2][i], Vm1[4][i], Vm1[3][i],"Random First Fit");
              individual.add(e);
             ag_coding coding=new ag_coding(String.valueOf(i+1), String.valueOf(Vm1[3][i]));
                                      rff.getItems().add(coding);
        }
           initialPop.add(individual);
       
                for(int z1=0;z1<Pm1[5].length;z1++){
                   Pm1[5][z1]="0";
                    Pm1[6][z1]="0";
                }
                }
                
             
    }
      @FXML
    void selectIdiv(MouseEvent event) {
        rff.getItems().clear();
      
       int n= Integer.parseInt(fitt.getSelectionModel().getSelectedItem().getNb());
        List<individu> individual=new  ArrayList<>();
       individual=initialPop.get(n-1);
         ll.setText(individual.get(1).Type_pop);
       for(int i=0;i<individual.size();i++){
        ag_coding coding=new ag_coding(individual.get(i).vm.toString(), individual.get(i).pm_num.toString());
                                      rff.getItems().add(coding);
       }
    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                  pm_RFF.setCellValueFactory(new PropertyValueFactory<ag_coding,String>("pm"));
                  vm_RFF.setCellValueFactory(new PropertyValueFactory<ag_coding,String>("vm"));
                  
                  idivid.setCellValueFactory(new PropertyValueFactory<showIdivFit,String>("indiv"));
                  nb.setCellValueFactory(new PropertyValueFactory<showIdivFit,String>("nb"));
                  fit.setCellValueFactory(new PropertyValueFactory<showIdivFit,String>("fit"));
                 

    }    
    
}
