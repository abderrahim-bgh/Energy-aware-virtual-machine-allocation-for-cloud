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
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    @FXML
    private TableColumn<showIdivFit,String> p1;   
    @FXML
    private TableColumn<showIdivFit,String> p2;   
    @FXML
    private Label cross_seting;
    
    public int randomDegrry,TaillePop,fittChoice;
    int numberSelect,threshold; String numSlal;
     int numberSelectInPop ,  numberSelectInIndividual;
     
    public int getNumberSelectInPop() {
        return numberSelectInPop;
    }
    public void setNumberSelectInPop(int numberSelectInPop) {
        this.numberSelectInPop = numberSelectInPop;
    }
    public int getNumberSelectInIndividual() {
        return numberSelectInIndividual;
    }
    public void setNumberSelectInIndividual(int numberSelectInIndividual) {
        this.numberSelectInIndividual = numberSelectInIndividual;
    }
    public int getThreshold() {
        return threshold;
    }
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
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
    public String getNumSlal() {
        return numSlal;
    }
    public void setNumSlal(String numSlal) {
        this.numSlal = numSlal;
    }
    public int getRandomDegrry() {
        return randomDegrry;
    }
    public void setRandomDegrry(int randomDegrry) {
        this.randomDegrry = randomDegrry;
    }
    int itteration;

    public int getItteration() {
        return itteration;
    }

    public void setItteration(int itteration) {
        this.itteration = itteration;
    }
    int cr;

    public int getCr() {
        return cr;
    }

    public void setCr(int cr) {
        this.cr = cr;
    }
    
    int ssla=0;
    
    List<List> initialPop= new ArrayList<>();         
    List<showIdivFit> indiv= new ArrayList<>();  
    List<showIdivFit> indiv1= new ArrayList<>(); 
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
                               if(Vm1[3][j].equals("")){
                                   for(int pc=0;pc<Pm1[1].length;pc++){
                                      int ps= Integer.parseInt(Pm1[5][pc])+Integer.parseInt(Pm1[5][pc])+Integer.parseInt(vm_migrated.get(i).cpu.toString());
                                       if(ps<Integer.parseInt(Pm1[1][pc]))  Vm1[3][j]=Pm1[1][pc];
                                   }
                               }
                               Vm1[3][j]=vm_migrated.get(i).vm_storage.toString();
                               //Vm1[4][j]=vm_migrated.get(i).vm_storage.toString();
                               tabVms[0][j]=vm_migrated.get(i).vm.toString();
                               tabVms[1][j]=vm_migrated.get(i).vm_storage;
                               }
                                         String eng = String.valueOf(energy(Pm1,Vm1));
                                        // double sss= (1-(energy(Pm1,Vm1)/(Pm1[0].length*250)))*100;
                                        double sss= (ssla/(Pm1[0].length));
                                         // double sss= (1-(Double.parseDouble(eng)/(Pm1[0].length*250)))*100;
                                         DecimalFormat df = new DecimalFormat("#.##");
                                         String ss= df.format(sss);
                                         double f=0;
                                         if(getFittChoice()==1)f=fitness1(Double.parseDouble(ss),Double.parseDouble(eng),Pm1);
                                         else if(getFittChoice()==2) f=fitness2(Double.parseDouble(eng),Pm1);
                                         else if(getFittChoice()==3) f=fitness3(Double.parseDouble(ss),Pm1);
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
        if(getCr()==1)
            GA2 (Pm1,Vm1);
        else GA (Pm1,Vm1);
    }
    
    List<List> newPop= new ArrayList<>();
    List<List> newPopShow= new ArrayList<>();
     List<List> ShopInPop= new ArrayList<>();
     public void GA2 (String[][] Pm1,String[][] Vm1){
         boolean one=false;
        //setings 
        cross_seting.setText("1- Population size :"+getTaillePop()+" individual"+
                "\n2- The crossover size : "+getNumberSelect()+" %"
                 +"\n3- the consolidation the VM threshold:"+getNumSlal()+"%"
                  );
        //initial pop
        
          for(int i=0;i<initialPop.size();i++){
                      List<individu> individual=new  ArrayList<individu>();
                      List<individu> individual2=new  ArrayList<individu>();
                      individual=initialPop.get(i);
                      for(int j=0;j<individual.size();j++){
                            individu id= new individu(individual.get(j).vm, individual.get(j).cpu, individual.get(j).ram, individual.get(j).vm_storage, individual.get(j).pm_num, individual.get(j).Type_pop, individual.get(j).energy, individual.get(j).sla, individual.get(j).fit);
                            individual2.add(id);
                      }
                      showIdivFit sh=new showIdivFit(String.valueOf(i+1),initialPop.get(i).toString(),String.valueOf(individual.get(1).fit),individual.get(1).sla, individual.get(1).energy);
                      indiv.add(sh);
                      ShopInPop.add(individual2);
                      fitt.getItems().add(sh);
                  }
          List<Double> listFit= new ArrayList<Double>();
          double b=1;
          double avB=0;
          while(b>avB){
                   System.out.println("b "+b+" avb "+avB);    
          for(int g=0;g<getItteration();g++){
              
          
          //selection
        String [][] sellection =selectionsSize((getNumberSelect()*initialPop.size())/100);
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
        
        if(!one){
           for(int i=0;i<newPop.size();i++){
                      List<individu> individual=new  ArrayList<individu>();
                      List<individu> individual2=new  ArrayList<individu>();
                      individual=newPop.get(i);
                      for(int j=0;j<individual.size();j++){
                            individu id= new individu(individual.get(j).vm, individual.get(j).cpu, individual.get(j).ram, individual.get(j).vm_storage, individual.get(j).pm_num, individual.get(j).Type_pop, individual.get(j).energy, individual.get(j).sla, individual.get(j).fit);
                            individual2.add(id);
                      }
                       newPopShow.add(individual2);
                         if(sellection[0].length>i){
                       showIdivFit sh=new showIdivFit(String.valueOf(i+1),newPop.get(i).toString(),
                               sellection[0][i],sellection[1][i], "0");
                       // indiv.add(sh);
                       fitt1.getItems().add(sh);  
                         }
                      
                     }
                      
                  }
       
           //for have one population
           initialPop.addAll(newPop);
           
            FitragePop(initialPop,Pm1);
           // mutation
           int muNb=(getNumberSelectInIndividual()*initialPop.get(0).size())/100;
            int muNb1=(getNumberSelectInPop()*initialPop.size())/100;
        mutation(initialPop,Pm1,Vm1,muNb1,muNb);
        //supp
        SuppRep(initialPop);
         // Repair for threshold
         Reparation(initialPop,Pm1);
         
         //supp 
         SuppRep(initialPop);
         
         if(!one){
             int redand = (getTaillePop()+newPop.size())-initialPop.size();
             cross_seting.setText(cross_seting.getText().toString()+"\n4- The number of redandance individuals deleted :"+redand+" Individual");
         }
          one=true;
         // calcule energy and sla 
         energyGa(Pm1,initialPop);
         
        double totalFit=0;
         for(int f=0;f<initialPop.size();f++){
              List<individu> individual=new  ArrayList<individu>();
              individual=initialPop.get(f);
              for(int i=0;i<individual.size();i++){
                  
                  if(getFittChoice()==1)        
                    individual.get(i).setFit(fitness1(Double.parseDouble(individual.get(i).getSla()),
                          Double.parseDouble(individual.get(i).getEnergy()),Pm1));
                  else if(getFittChoice()==2)  individual.get(i).setFit(fitness2(
                          Double.parseDouble(individual.get(i).getEnergy()),Pm1));
                  else if(getFittChoice()==3)  individual.get(i).setFit(fitness3(Double.parseDouble(individual.get(i).getSla()),Pm1));
              }
             totalFit=totalFit+  individual.get(1).getFit();
         }
         
         listFit.add(totalFit);
         if(g<1)avB=totalFit;
         
         //for(int i=0;i<getItteration();i++)
         //  System.out.println("ftG  "+listFit.get(i));
          
         
           List<List<individu>> CompareList= new ArrayList<>();
           
           for(int i=0;i<initialPop.size();i++){
                List<individu> individualX=new  ArrayList<individu>();
                individualX=initialPop.get(i);
                CompareList.add(individualX);
           }
           Collections.sort(CompareList,new Comparator<List<individu>>(){
                    @Override
                    public int compare( List<individu> o1,  List<individu>  o2) {
                        return Double.compare(o1.get(0).getFit(), (o2.get(0).getFit()));
                    }
                });
            Collections.reverse(CompareList);
            initialPop.clear();
            for(int i=0;i<getTaillePop();i++){
                List<individu> individualX=new  ArrayList<individu>();
                try{
                     individualX=CompareList.get(i);
                initialPop.add(individualX);
                } catch(IndexOutOfBoundsException e){
                    
                }
               
            }
            indiv.clear();
             for(int i=0;i<initialPop.size();i++){
                      List<individu> individual=new  ArrayList<individu>();
                      individual=initialPop.get(i);
                      showIdivFit sh=new showIdivFit(String.valueOf(i+1),initialPop.get(i).toString(),String.valueOf(individual.get(1).fit),individual.get(1).sla, individual.get(1).energy);
                      indiv.add(sh);
                  }
        }  
           b=listFit.get(listFit.size()-1);
       }   
          boolean window0=false;
          if(!window0){
            window0=true;
            AnchorPane root1= new AnchorPane();
        FXMLLoader loader0= new FXMLLoader(getClass().getResource("resultAG.fxml"));
                 try {
                     root1=loader0.load();
                 } catch (IOException ex) {
                     Logger.getLogger(AgPageController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 ResultAGController RG =loader0.getController();
                 List<individu> ResultList= new ArrayList<>();
                 ResultList=initialPop.get(0);
                  int meg=0;
                 if(getEnMeg().equals("1"))
                     meg= MegrationEnergy(getVmMeg(),ResultList);
                 
                 RG.setEnergyMeg(meg);
                 RG.BestPop(ResultList, Pm1);
                           Double [] ftG= new Double[listFit.size()];
                           for(int i=0;i<listFit.size();i++) ftG[i]=listFit.get(i);
                 RG.chartFit(ftG);
        Tab tabP1=new Tab();
        tabP1.setText("Result");
        tabP1.setContent(root1);
        firstController.tb(tabP1);
        }
          
    }
    public void GA (String[][] Pm1,String[][] Vm1){
        //setings 
        cross_seting.setText("1- Population size :"+getTaillePop()+" individual"+
                "\n2- The crossover size : "+getNumberSelect()+" %"
                 +"\n3- the consolidation the VM threshold:"+getNumSlal()+"%"
                  );
        //initial pop
        
          for(int i=0;i<initialPop.size();i++){
                      List<individu> individual=new  ArrayList<individu>();
                      List<individu> individual2=new  ArrayList<individu>();
                      individual=initialPop.get(i);
                      for(int j=0;j<individual.size();j++){
                            individu id= new individu(individual.get(j).vm, individual.get(j).cpu, individual.get(j).ram, individual.get(j).vm_storage, individual.get(j).pm_num, individual.get(j).Type_pop, individual.get(j).energy, individual.get(j).sla, individual.get(j).fit);
                            individual2.add(id);
                      }
                      showIdivFit sh=new showIdivFit(String.valueOf(i+1),initialPop.get(i).toString(),String.valueOf(individual.get(1).fit),individual.get(1).sla, individual.get(1).energy);
                      indiv.add(sh);
                      ShopInPop.add(individual2);
                      fitt.getItems().add(sh);
                  }
          Double [] ftG= new Double[getItteration()];
          for(int g=0;g<getItteration();g++){
              
          
          //selection
        String [][] sellection =selectionsSize((getNumberSelect()*initialPop.size())/100);
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
        if(g<1){
           for(int i=0;i<newPop.size();i++){
                      List<individu> individual=new  ArrayList<individu>();
                      List<individu> individual2=new  ArrayList<individu>();
                      individual=newPop.get(i);
                      for(int j=0;j<individual.size();j++){
                            individu id= new individu(individual.get(j).vm, individual.get(j).cpu, individual.get(j).ram, individual.get(j).vm_storage, individual.get(j).pm_num, individual.get(j).Type_pop, individual.get(j).energy, individual.get(j).sla, individual.get(j).fit);
                            individual2.add(id);
                      }
                       newPopShow.add(individual2);
                       if(sellection[0].length>i){
                           showIdivFit sh=new showIdivFit(String.valueOf(i+1),newPop.get(i).toString(),
                               sellection[0][i],sellection[1][i], "0");
                       // indiv.add(sh);
                       fitt1.getItems().add(sh);  
                       }
                      
                     }
                      
                  }
           //for have one population
           initialPop.addAll(newPop);
           
            FitragePop(initialPop,Pm1);
           // mutation
           int muNb=(getNumberSelectInIndividual()*initialPop.get(0).size())/100;
            int muNb1=(getNumberSelectInPop()*initialPop.size())/100;
        mutation(initialPop,Pm1,Vm1,muNb1,muNb);
        //supp
        SuppRep(initialPop);
         // Repair for threshold
         Reparation(initialPop,Pm1);
         
         //supp 
         SuppRep(initialPop);
         
         if(g<1){
             int redand = (getTaillePop()+newPop.size())-initialPop.size();
             cross_seting.setText(cross_seting.getText().toString()+"\n4- The number of redandance individuals deleted :"+redand+" Individual");
         }
         // calcule energy and sla 
         energyGa(Pm1,initialPop);
         
        double totalFit=0;
         for(int f=0;f<initialPop.size();f++){
              List<individu> individual=new  ArrayList<individu>();
              individual=initialPop.get(f);
              for(int i=0;i<individual.size();i++){
                  
                  if(getFittChoice()==1)        
                    individual.get(i).setFit(fitness1(Double.parseDouble(individual.get(i).getSla()),
                          Double.parseDouble(individual.get(i).getEnergy()),Pm1));
                  else if(getFittChoice()==2)  individual.get(i).setFit(fitness2(
                          Double.parseDouble(individual.get(i).getEnergy()),Pm1));
                  else if(getFittChoice()==3)  individual.get(i).setFit(fitness3(Double.parseDouble(individual.get(i).getSla()),Pm1));
              }
             totalFit=totalFit+  individual.get(1).getFit();
         }
         
          ftG[g]=totalFit;
         
         for(int i=0;i<getItteration();i++)
           System.out.println("ftG  "+ftG[i]);
          
         
           List<List<individu>> CompareList= new ArrayList<>();
           
           for(int i=0;i<initialPop.size();i++){
                List<individu> individualX=new  ArrayList<individu>();
                individualX=initialPop.get(i);
                CompareList.add(individualX);
           }
           Collections.sort(CompareList,new Comparator<List<individu>>(){
                    @Override
                    public int compare( List<individu> o1,  List<individu>  o2) {
                        return Double.compare(o1.get(0).getFit(), (o2.get(0).getFit()));
                    }
                });
            Collections.reverse(CompareList);
            initialPop.clear();
            for(int i=0;i<getTaillePop();i++){
                List<individu> individualX=new  ArrayList<individu>();
                try{
                     individualX=CompareList.get(i);
                initialPop.add(individualX);
                } catch(IndexOutOfBoundsException e){
                    
                }
               
            }
            indiv.clear();
             for(int i=0;i<initialPop.size();i++){
                      List<individu> individual=new  ArrayList<individu>();
                      individual=initialPop.get(i);
                      showIdivFit sh=new showIdivFit(String.valueOf(i+1),initialPop.get(i).toString(),String.valueOf(individual.get(1).fit),individual.get(1).sla, individual.get(1).energy);
                      indiv.add(sh);
                  }
        }  
          
          boolean window0=false;
          if(!window0){
            window0=true;
            AnchorPane root1= new AnchorPane();
        FXMLLoader loader0= new FXMLLoader(getClass().getResource("resultAG.fxml"));
                 try {
                     root1=loader0.load();
                 } catch (IOException ex) {
                     Logger.getLogger(AgPageController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 ResultAGController RG =loader0.getController();
                 List<individu> ResultList= new ArrayList<>();
                 ResultList=initialPop.get(0);
                 int meg=0;
                 if(getEnMeg().equals("1"))
                     meg= MegrationEnergy(getVmMeg(),ResultList);
                 
                 RG.setEnergyMeg(meg);
                 RG.BestPop(ResultList, Pm1);
                 RG.chartFit(ftG);
                 
        Tab tabP1=new Tab();
        tabP1.setText("Result");
        tabP1.setContent(root1);
        firstController.tb(tabP1);
        }
          
    }
     String[][] VmMeg;
     String EnMeg;

    public String getEnMeg() {
        return EnMeg;
    }

    public void setEnMeg(String EnMeg) {
        this.EnMeg = EnMeg;
    }

    public String[][] getVmMeg() {
        return VmMeg;
    }

    public void setVmMeg(String[][] VmMeg) {
        this.VmMeg = VmMeg;
    }
     

    public int MegrationEnergy(String[][] Vm,List<individu> ResultList){
        int x=0;
            for(int j=0;j<Vm[0].length;j++){
                if(!ResultList.get(j).pm_num.equals(Vm[3][j])){
                    x=x+2;
                }
            }
        return x;
    }
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
                                         double f=0;
                                         if(getFittChoice()==1)f=fitness1(Double.parseDouble(ss),Double.parseDouble(eng),Pm1);
                                         else if(getFittChoice()==2) f=fitness2(Double.parseDouble(eng),Pm1);
                                         else if(getFittChoice()==3) f=fitness3(Double.parseDouble(ss),Pm1);                  
                                         individu e=new individu(Vm1[0][i], Vm1[1][i], Vm1[2][i], Vm1[4][i], Vm1[3][i],"Random",eng,ss+"%",f);
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
             double f=0;
             if(getFittChoice()==1)f=fitness1(Double.parseDouble(ss),Double.parseDouble(eng),Pm1);
             else if(getFittChoice()==2) f=fitness2(Double.parseDouble(eng),Pm1);
             else if(getFittChoice()==3) f=fitness3(Double.parseDouble(ss),Pm1);
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
       individual=ShopInPop.get(n-1);
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
        List<individu> individual2=new  ArrayList<>();
       individual2=newPopShow.get(n-1);
       for(int i=0;i<individual2.size();i++){
        ag_coding coding=new ag_coding(individual2.get(i).vm.toString(), individual2.get(i).pm_num.toString());
                                      new_indiv.getItems().add(coding);
       }
    }
    @FXML
    public double energy (String [][]classment_pm1,String[][] classment_vm1){
        double energy_total=0;
        int sla_x=0;
         ssla=0;
        int  num_sla =Integer.parseInt(getNumSlal());
          for(int j=0;j<classment_pm1[0].length;j++){
             int cpu=0;
             int ram=0;
             int z= Integer.parseInt(classment_pm1[0][j]);
             int z1=Integer.parseInt(classment_pm1[1][j]);
             sla_x= (z1*num_sla)/100;

           for(int i=0;i<classment_vm1[4].length;i++){
             if(classment_vm1[3][i]!=null)
             if(classment_vm1[3][i].equals(String.valueOf(z))){
                 cpu=cpu+Integer.parseInt(classment_vm1[1][i]);
                 ram=ram+Integer.parseInt(classment_vm1[2][i]);
                }  
            }
           
           if(cpu > sla_x){
                             int cpux=cpu-sla_x;
                             int tTershold=Integer.parseInt(classment_pm1[1][j]);
                             int Sla =(cpux*100)/tTershold;
                             ssla=ssla+Sla;
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
    
    public void energyGa (String [][]classment_pm1,List <List> Pop){
        for(int p=0;p<Pop.size();p++){
            List<individu> individual=new  ArrayList<individu>();
            individual=Pop.get(p);
        double energy_total=0;
        int sla_x=0;
        int ssl=0;
        int  num_sla =Integer.parseInt(getNumSlal());
          for(int j=0;j<classment_pm1[0].length;j++){
             int cpu=0;
             int ram=0;
             int z= Integer.parseInt(classment_pm1[0][j]);
             int z1=Integer.parseInt(classment_pm1[1][j]);
             sla_x= (z1*num_sla)/100;

           for(int i=0;i<individual.size();i++){
             if(individual.get(i).getPm_num()!=null)
             if(individual.get(i).getPm_num().equals(String.valueOf(z))){
                 cpu=cpu+Integer.parseInt(individual.get(i).getCpu());
                 ram=ram+Integer.parseInt(individual.get(i).getRam());
                }  
            }
           
           if(cpu > sla_x){
                             int cpux=cpu-sla_x;
                             
                             int Sla =(cpux*100)/z1;
                             ssl=ssl+Sla;
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
             double sss= (ssl/(classment_pm1[0].length));
           //  double sss= (1-(energy_total/(classment_pm1[0].length*250)))*100;

          for(int i=0;i<individual.size();i++){
              individual.get(i).setEnergy(String.valueOf(energy_total));
               individual.get(i).setSla(String.valueOf(sss));
              
          }
        }
    }

      public double fitness2(double energy,String [][] Pm1){
       double alpha = (energy - (175*Pm1[0].length)) / ((250*Pm1[0].length) -( 175*Pm1[0].length)) ;
       double Fitness= (1-alpha);
       
       return Fitness;
    }
    public double fitness1(double sla,double energy,String [][] Pm1){
       double alpha = (energy - (175*Pm1[0].length)) / ((250*Pm1[0].length) -( 175*Pm1[0].length)) ;
       double beta = sla/100;  
       double Fitness= (1-alpha)*(1-beta);
       return Fitness;
    }
    public double fitness3(double sla,String [][] Pm1){
       double beta = sla/100;  
       double Fitness= (1-beta);
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
                              if(individual.get(k).pm_num== null) filtre=true;
                              else if (individual.get(k).pm_num.equals("")) filtre=true;
                              else if(individual.get(k).pm_num!= null)
                               if(individual.get(k).pm_num.equals(numPm)){
                                  pmUtilisation=pmUtilisation+Integer.parseInt(individual.get(k).getCpu());
                              }
                              
                          }
                          int cpuPm=Integer.parseInt(Pm1[1][j]);
                          if(cpuPm<pmUtilisation){

                              //newPop.remove(i);
                              filtre=true;
                              break;
                          }
                          
                      }
                      if(filtre==false) { newPopFiltred.add(newPop.get(i));
                      }
        }
        newPop.clear();
        newPop.addAll(newPopFiltred);
        
    }
    public void FitragePop(List<List> newPop,String[][] Pm1){
        List<List> newPopFiltred=new ArrayList<List>();
        
        for(int i=0;i<newPop.size();i++){
                      boolean filtre=false;
                      List<individu> individual=new  ArrayList<individu>();
                      individual=newPop.get(i);
                      for(int j=0;j<Pm1[0].length;j++){
                          String numPm=Pm1[0][j];
                          int pmUtilisation=0;
                          for(int k=0;k<individual.size();k++){
                              if(individual.get(k).pm_num== null) filtre=true;
                              
                              else if (individual.get(k).pm_num.equals("")) filtre=true;

                          }
                          int cpuPm=Integer.parseInt(Pm1[1][j]);
                      }
                      if(filtre==false) { newPopFiltred.add(newPop.get(i));
                      }
        }
        newPop.clear();
        newPop.addAll(newPopFiltred);
        
    }
    int RepSize=0;
    public void Reparation(List <List> oldPop,String [][] allPm){
        int thershold= getThreshold();
        for(int pi=0;pi<oldPop.size();pi++){
            boolean RepSizeBoll=false;
            List<Vmcl> vm_migrated= new ArrayList<>();
             List<individu> individual=new  ArrayList<>();
           individual =oldPop.get(pi);
           // algo of Minimization of megration
            int cpu_thre=0;
            int cpu_thre_max=0;
            List<Vmcl> old_vm=new  ArrayList<>();
            for(int i=0;i<allPm[1].length;i++){
               // String [][] vm_list= new String [4][vm[0].length];
                List<Vmcl> vm_list=new  ArrayList<>();
                int z=0;
                for(int j=0;j<individual.size();j++){
                    if(String.valueOf(i+1).equals(individual.get(j).pm_num)){
                   
                        Vmcl vm= new Vmcl(individual.get(j).getVm().toString(),individual.get(j).getCpu().toString(),
                            individual.get(j).getRam().toString(),individual.get(j).getVm_storage().toString());
                        vm_list.add(vm);
                    }
                }
                    //stor vms list 
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
                cpu_thre_max= (Integer.parseInt(allPm[1][i])*thershold)/100;   
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
            }        
           for(int x=0;x<vm_migrated.size();x++){
               for(int j=0;j<allPm[1].length;j++){
                   int cpu =0;
                    for(int i=0;i<individual.size();i++){
                        if(individual.get(i).pm_num!=null)
                     if(individual.get(i).pm_num.equals(allPm[0][j])){
                        cpu=cpu+Integer.parseInt(individual.get(i).getCpu());
                       } 
                    }
                    cpu=cpu+Integer.parseInt(vm_migrated.get(x).getCpu());
                    cpu_thre_max= (Integer.parseInt(allPm[1][j])*thershold)/100;
                    if(cpu<=cpu_thre_max){
                        for(int i=0;i<individual.size();i++){
                            if(individual.get(i).getVm().equals(vm_migrated.get(x).getVm())){
                                individual.get(i).pm_num=allPm[0][j];
                                RepSizeBoll=true;
                            }
                        }
                    }
                }
           }
            oldPop.set(pi, individual);
            if(RepSizeBoll==true){
                RepSize++;
            }
        }
        Mutation1Controller mt= new Mutation1Controller();
        mt.setRepSize(String.valueOf(RepSize));
    }
    FXMLDocumentController firstController;
    
   public void setFirstController(FXMLDocumentController controller) {
        this.firstController = controller;
    }
        List<List> po2=new ArrayList<>();
        
        boolean pop2=false;
         boolean window=false;
    public  void mutation(List<List> population,String [][]Pm1,String [][]classment_vm1, int numberSelectInPop, int numberSelectInIndividual) {
        Random rand = new Random();
        // po2.addAll(initialPop);
        int populationSize = population.size();
        for (int i = 0; i < numberSelectInPop; i++) {
            int randomIndex = rand.nextInt(0,populationSize-1);
            List<individu> individual = population.get(randomIndex);
           
            int individualSize = individual.size();
            List<individu> ind=new ArrayList<>();
                   ind.addAll(individual);
            for (int j = 0; j < numberSelectInIndividual; j++) {
               int randomGen = rand.nextInt(0,individualSize-1);
              individu in= individual.get(randomGen);
             for(int p=0;p<Pm1[0].length;p++){
                  int cpu=0;
                  boolean cx=false;
                   
              for(int x=0;x<individualSize;x++){
                   if(individual.get(x).pm_num.equals(Pm1[0][p])){
                 cpu=cpu+Integer.parseInt(individual.get(x).getCpu());
                 cx=true;
                }  
              }
              int Tcpu= Integer.parseInt(in.getCpu())+cpu;
              if(cx==true)
              if(Tcpu<=Integer.parseInt(Pm1[1][p])){
                  int val=p+1;
                 
                  if(!pop2){
                      for(int x=0;x<individual.size();x++){
                          if(x==randomGen){
                              individu indivi = new individu( individual.get(randomGen).vm,  individual.get(randomGen).cpu,  individual.get(randomGen).ram,
                                individual.get(randomGen).vm_storage, String.valueOf(individual.get(randomGen).getPm_num()+"=>"+val),
                               individual.get(randomGen).Type_pop, individual.get(randomGen).energy, individual.get(randomGen).sla,
                               individual.get(randomGen).fit);
                            ind.set(x,indivi);
                          }
                          
                      }
                       
                          
                  }
                  individual.get(randomGen).setPm_num(String.valueOf(val));
                   population.set(randomIndex, individual);
                  
                  break;
                }  
             }
             
        }
          po2.add( ind);   
    }          
        pop2=true; 
        if(!window){
            window=true;
            AnchorPane root1= new AnchorPane();
        FXMLLoader loader0= new FXMLLoader(getClass().getResource("Mutation1.fxml"));
                 try {
                     root1=loader0.load();
                 } catch (IOException ex) {
                     Logger.getLogger(AgPageController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 Mutation1Controller mt =loader0.getController();
                int RepSizePers= RepSize*100/getTaillePop();
                 mt.setDataMut("1- Population mutation rate :"+numberSelectInPop+" individual"+
                "\n2-  Individual mutation rate :"+numberSelectInIndividual+ "gen"
                 +"\n3- threshold max :"+getThreshold()+"%"
                 +"\n4- Population Reparation rate 100%");
                 mt.setPopulation2(po2);
                 mt.mutt();
        
        Tab tabP1=new Tab();
        tabP1.setText("Mutation");
        tabP1.setContent(root1);
        firstController.tb(tabP1);
        }
        
        
}

     public void SuppRep(List<List> population){
         int k=0;
         for(int i=0;i<population.size();i++){
              List<individu> p1=new  ArrayList<individu>();
              p1=population.get(i);
             
             for(int j=i+1;j<population.size()-1;j++){
                   List<individu> p2=new  ArrayList<individu>();
                   p2=population.get(j);
                 if(p1.toString().equals(p2.toString())){
                     k++;
                     population.remove(i);
                 }
                 
             }
             
         }
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
                  p1.setCellValueFactory(new PropertyValueFactory<showIdivFit,String>("fit"));
                  p2.setCellValueFactory(new PropertyValueFactory<showIdivFit,String>("sla"));
                  
               
                 
    }    
    
}