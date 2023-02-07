/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soutnoce_vm;

/**
 *
 * @author abderrahim
 */
public class Vmcl {
     String vm;
    String cpu;
    String ram;
    String vm_storage;

    public Vmcl(String vm, String cpu, String ram,String vm_storage) {
        this.vm = vm;
        this.cpu = cpu;
        this.ram = ram;
        this.vm_storage=vm_storage;
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

    
   
}

