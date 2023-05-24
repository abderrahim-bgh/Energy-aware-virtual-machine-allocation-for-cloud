/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soutnoce_vm;

/**
 *
 * @author abderrahim
 */
public class individu1 {
    String vm;
    String cpu;
    String ram;
    String vm_storage;
    String pm_num;
    String Type_pop;
    String energy;
    String sla;
    double fit;

    public double getFit() {
        return fit;
    }

    public void setFit(double fit) {
        this.fit = fit;
    }
    

  
    
    public individu1(String vm, String cpu, String ram, String vm_storage, String pm_num, String Type_pop,String energy,String sla,double fit) {
        this.vm = vm;
        this.energy=energy;
        this.cpu = cpu;
        this.ram = ram;
        this.vm_storage = vm_storage;
        this.pm_num = pm_num;
        this.Type_pop = Type_pop;
        this.fit=fit;
        this.sla=sla;
    }
      public String getSla() {
        return sla;
    }

    public void setSla(String sla) {
        this.sla = sla;
    }
    

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }
    
    public void setType_pop(String Type_pop) {
        this.Type_pop = Type_pop;
    }

    public String getType_pop() {
        return Type_pop;
    }

    public String getPm_num() {
        return pm_num;
    }

    public void setPm_num(String pm_num) {
        this.pm_num = pm_num;
    }


    public String getVm_storage() {
        return vm_storage;
    }

    public void setVm_storage(String vm_storage) {
        this.vm_storage = vm_storage;
    }

    public String getVm() {
        return vm;
    }

    public void setVm(String vm) {
        this.vm = vm;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    @Override
    public String toString() {
        return vm+"("+pm_num+")";
    }

    
   
}

