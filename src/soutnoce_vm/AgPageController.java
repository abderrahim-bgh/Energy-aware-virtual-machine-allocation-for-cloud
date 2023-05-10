/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package soutnoce_vm;

import java.net.URL;
import java.text.DecimalFormat;
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
import javafx.scene.control.Button;
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
    private TableColumn<ag_coding, String> pm_RFF1;
    @FXML
    private TableView<ag_coding> new_indiv;
    @FXML
    private TableColumn<ag_coding, String> vm_RFF1;
    @FXML
    private TableView<showIdivFit> fitt;
    @FXML
    private TableColumn<showIdivFit,String> idivid;
    @FXML
    private TableColumn<showIdivFit,String> fit;
    @FXML
    private TableColumn<showIdivFit,String> nb;    
    @FXML
    private TableColumn<showIdivFit,String> SLAv;
    @FXML
    private TableColumn<showIdivFit,String> Energy;
     @FXML
    private TableView<showIdivFit> fitt1;
    @FXML
    private TableColumn<showIdivFit,String> idivid1;
    @FXML
    private TableColumn<showIdivFit,String> nb1;    
    public int randomDegrry,TaillePop,fittChoice;
    int numberSelect;

    public int getNumberSelect() {
        return numberSelect;
    }

    public void setNumberSelect(int numberSelect) {
        this.numberSelect = numberSelect;
    }
   

    public void setFittChoice(int fittChoice) {
        this.fittChoice = fittChoice;
    }

    public int getFittChoice() {
        return fittChoice;
    }
    

    public int getTaillePop() {
        return TaillePop;
    }

    public void setTaillePop(int TaillePop) {
        this.TaillePop = TaillePop;
    }
    

    public int getRandomDegrry() {
        return randomDegrry;
    }

    public void setRandomDegrry(int randomDegrry) {
        this.randomDegrry = randomDegrry;
    }
    List<List> initialPop= new ArrayList<>();         
    List<showIdivFit> indiv= new ArrayList<>();  
    public void InitializationBFD(String [][]Vm1,String[][] Pm1){
        for(int p=0;p<getTaillePop()/3;p++){
         List<individu> individual=new  ArrayList<>();
         int total_cpu=0;
         int total_ram=0;  
         List<Vmcl> vm_migrated=new  ArrayList<>();
         String[][] Vm2= random(Vm1,Pm1);
          
            for(int j=0;j<Vm1[0].length;j++){
                        vm_migrated.add(new Vmcl(Vm1[0][j],Vm1[1][j],Vm1[2][j],""));            
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
                               
                                         double sss= (1-(energy(Pm1,Vm1)/(Pm1[0].length*250)))*100;
                                         DecimalFormat df = new DecimalFormat("#.##");
                                         String ss= df.format(sss);
                                         String eng = String.valueOf(energy(Pm1,Vm1));
                                         double f;
                                         if(getFittChoice()==1)
                                         f=fitness1(Double.parseDouble(ss),energy(Pm1,Vm1));
                                         else  f=0;
                                      individu e= new individu(vm_migrated.get(i).vm.toString(), vm_migrated.get(i).cpu.toString(),
                                              vm_migrated.get(i).ram.toString(), Vm1[4][j], Vm1[3][j],"Random Best-fit decreasing",eng,ss+"%",f);
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
        GA (Pm1);
    }
    List<List> newPop= new ArrayList<>();
    public void GA (String[][] Pm1){
        //initial pop
          for(int i=0;i<initialPop.size();i++){
                      List<individu> individual=new  ArrayList<individu>();
                      individual=initialPop.get(i);
                      
                      showIdivFit sh=new showIdivFit(String.valueOf(i+1),initialPop.get(i).toString(),String.valueOf(individual.get(1).fit),individual.get(1).sla, individual.get(1).energy);
                      indiv.add(sh);
                      fitt.getItems().add(sh);
                  }
          //selection
        String [][] sellection =selectionsSize((getNumberSelect()*indiv.size())/100);
        //crossover
         
        for(int i=0;i<sellection[0].length;i++){
            int Npr1=Integer.parseInt(sellection[0][i]);
             int Npr2=Integer.parseInt(sellection[1][i]);
             List<individu> individual=new  ArrayList<individu>();
             List<individu> individual2=new  ArrayList<individu>();
               individual = initialPop.get(Npr1-1);
               individual2 = initialPop.get(Npr2-1);
           newPop.addAll(onePointCrossover(individual,individual2));
        }
        
        FitrageNewPop(newPop,Pm1);
         for(int i=0;i<newPop.size();i++){
                      List<individu> individual=new  ArrayList<individu>();
                      individual=newPop.get(i);
                      
                      showIdivFit sh=new showIdivFit(String.valueOf(i+1),newPop.get(i).toString(),"0","0", "0");
                     // indiv.add(sh);
                      fitt1.getItems().add(sh);
                  }
        
    }
    
    @FXML
    private Button next;
    public void InitializationRandom(String [][]Vm1,String[][] Pm1){   
                 for(int pp=0;pp<getTaillePop()/3;pp++){
                   
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
                   double sss= (1-(energy(Pm1,Vm1)/(Pm1[0].length*250)))*100;
                                         DecimalFormat df = new DecimalFormat("#.##");
                                         String ss= df.format(sss);
                                          String eng = String.valueOf(energy(Pm1,Vm1));
                                          double f;
                                         if(getFittChoice()==1)
                                         f=fitness1(Double.parseDouble(ss),energy(Pm1,Vm1));
                                         else  f=0;                   individu e=new individu(Vm1[0][i], Vm1[1][i], Vm1[2][i], Vm1[4][i], Vm1[3][i],"Random",eng,ss+"%",f);
                   individual.add(e);
               }
               initialPop.add(individual);
        }

    }
    
        public String [][] random(String [][]Vm2,String[][] Pm1){
           int nb_vm = (Vm2[0].length*getRandomDegrry())/100;
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
        for(int pp=0;pp<getTaillePop()/3;pp++){
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
            double sss= (1-(energy(Pm1,Vm1))/(Pm1[0].length*250))*100;
            DecimalFormat df = new DecimalFormat("#.##");
            String ss= df.format(sss);
             String eng = String.valueOf(energy(Pm1,Vm1));
             double f;
             if(getFittChoice()==1)
                f=fitness1(Double.parseDouble(ss),energy(Pm1,Vm1));
             else  f=0;
             individu e=new individu(Vm1[0][i], Vm1[1][i], Vm1[2][i], Vm1[4][i], Vm1[3][i],"Random First Fit",eng,ss+"%",f);
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
    @FXML
    void selectIdiv1(MouseEvent event) {
        new_indiv.getItems().clear();
      
       int n= Integer.parseInt(fitt1.getSelectionModel().getSelectedItem().getNb());
        List<individu> individual=new  ArrayList<>();
       individual=newPop.get(n-1);
       for(int i=0;i<individual.size();i++){
        ag_coding coding=new ag_coding(individual.get(i).vm.toString(), individual.get(i).pm_num.toString());
                                      new_indiv.getItems().add(coding);
       }
    }
    public double energy (String [][]classment_pm1,String[][] classment_vm1){
        double energy_total=0;
          for(int j=0;j<classment_pm1[0].length;j++){
             int cpu=0;
             int ram=0;
             int z= Integer.parseInt(classment_pm1[0][j]);
             int z1=Integer.parseInt(classment_pm1[1][j]);
           for(int i=0;i<classment_vm1[4].length;i++){
             if(classment_vm1[3][i]!=null)
             if(classment_vm1[3][i].equals(String.valueOf(z))){
                 cpu=cpu+Integer.parseInt(classment_vm1[1][i]);
                 ram=ram+Integer.parseInt(classment_vm1[2][i]);
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
          return energy_total;
    }


    public double fitness1(double sla,double energy){
       double alpha = (energy - 175000) / (250000 - 175000) ;
       double beta = sla/20;  
       double Fitness= (1-alpha)*(1-beta);
       
       return Fitness;
    }
    

    
 public static List<showIdivFit> stochasticUniversalSampling(List<showIdivFit> population) {
    double totalFitness = 0;
    int numSelections=2;
    // Calculate total fitness 
     for (showIdivFit individual : population) {
        totalFitness += Double.parseDouble(individual.getFit());
    }
    
    
    // Calculate the size of each segment in the roulette wheel
    double segmentSize = totalFitness / numSelections;
    
    // Start at a random point in the roulette wheel
    double start = new Random().nextDouble() * segmentSize;
    
    // Select individuals based on their position in the roulette wheel
    List<showIdivFit> selections = new ArrayList<>();
    int index = 0;
    double accumulatedFitness = 0;
    while (selections.size() < numSelections) {
      accumulatedFitness += Double.parseDouble(population.get(index).getFit());
      if (accumulatedFitness > start) {
        selections.add(population.get(index));
        start += segmentSize;
      }
      index = (index + 1) % population.size();
    }
    
    return selections;
  }
 
    public String[][] selectionsSize(int numberSelect) {
        List<showIdivFit> findiv= new ArrayList<>();
        findiv= stochasticUniversalSampling(indiv);
                String [][] allSelections= new String [2][numberSelect];
                allSelections[0][0]=findiv.get(0).getNb();
                allSelections[1][0]=findiv.get(1).getNb();
                int i=1;
                while(i<numberSelect){
                    findiv= new ArrayList<>();
                    findiv= stochasticUniversalSampling(indiv);
                    String s1=findiv.get(0).getNb();
                    String s2=findiv.get(1).getNb();
                    boolean add=false;
                    for(int j=0;j<i;j++ ){
                        if(i<numberSelect)
                        if(s1.equals(allSelections[0][j]) && s2.equals(allSelections[1][j])){
                            add=true;
                        }
                    }
                    if(add==false) { 
                    allSelections[0][i]=s1;
                    allSelections[1][i]=s2;
                    i++;
                    }
                        
                    

                }
                  for(int i1=0;i1<numberSelect;i1++){
                        System.out.println(allSelections[0][i1]+ " "+allSelections[1][i1]);    
                            
                  }
                  return allSelections ;
    }

    public static List<List> onePointCrossover(List<individu> parent1, List<individu> parent2) {
    int length = parent1.size();
    Random rand = new Random();
    int crossoverPoint = rand.nextInt(length);
    List<individu> child1 = new ArrayList<individu>();
    List<individu> child2 = new ArrayList<individu>();
    List<individu> individual=new  ArrayList<>();
    // Create the first child by swapping tails of parents
    child1.addAll(parent1.subList(0, crossoverPoint));
    child1.addAll(parent2.subList(crossoverPoint, length));

    // Create the second child by swapping tails of parents
    child2.addAll(parent2.subList(0, crossoverPoint));
    child2.addAll(parent1.subList(crossoverPoint, length));

    // Return the two offspring
    List<List> offspring = new ArrayList<List>();
    offspring.add(child1);
    offspring.add(child2);
    
    return offspring;
}
    
    public void FitrageNewPop(List<List> newPop,String[][] Pm1){
        List<List> newPopFiltred=new ArrayList<List>();
        
        for(int i=0;i<newPop.size();i++){
                      boolean filtre=false;
                      List<individu> individual=new  ArrayList<individu>();
                      individual=newPop.get(i);
                      for(int j=0;j<Pm1[0].length;j++){
                          String numPm=Pm1[0][j];
                          int pmUtilisation=0;
                          for(int k=0;k<individual.size();k++){
                              if(individual.get(k).pm_num.equals(numPm)){
                                  pmUtilisation=pmUtilisation+Integer.parseInt(individual.get(k).getCpu());
                              }
                          }
                          int cpuPm=Integer.parseInt(Pm1[1][j]);
                          if(cpuPm<pmUtilisation){

                              newPop.remove(i);
                              filtre=true;
                              break;
                          }
                      }
                      if(filtre==false)  newPopFiltred.add(newPop.get(i));
        }
        newPop.clear();
        newPop.addAll(newPopFiltred);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
                  pm_RFF.setCellValueFactory(new PropertyValueFactory<ag_coding,String>("pm"));
                  vm_RFF.setCellValueFactory(new PropertyValueFactory<ag_coding,String>("vm"));
                  pm_RFF1.setCellValueFactory(new PropertyValueFactory<ag_coding,String>("pm"));
                  vm_RFF1.setCellValueFactory(new PropertyValueFactory<ag_coding,String>("vm"));
                  idivid.setCellValueFactory(new PropertyValueFactory<showIdivFit,String>("indiv"));
                  nb.setCellValueFactory(new PropertyValueFactory<showIdivFit,String>("nb"));
                  fit.setCellValueFactory(new PropertyValueFactory<showIdivFit,String>("fit"));
                  SLAv.setCellValueFactory(new PropertyValueFactory<showIdivFit,String>("sla"));
                  Energy.setCellValueFactory(new PropertyValueFactory<showIdivFit,String>("enrgy"));
                   idivid1.setCellValueFactory(new PropertyValueFactory<showIdivFit,String>("indiv"));
                  nb1.setCellValueFactory(new PropertyValueFactory<showIdivFit,String>("nb"));
                 
    }    
    
}
