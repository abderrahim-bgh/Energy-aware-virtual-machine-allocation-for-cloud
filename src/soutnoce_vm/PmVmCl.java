/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soutnoce_vm;

/**
 *
 * @author abderrahim
 */
public class PmVmCl {
     String cpu,vm,ram;

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }
     

    public PmVmCl(String vm,String cpu, String ram) {
          this.vm = vm;
        this.cpu = cpu;
        this.ram=ram;
      
    }

  

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getVm() {
        return vm;
    }

    public void setVm(String vm) {
        this.vm = vm;
    }
     
    
}
