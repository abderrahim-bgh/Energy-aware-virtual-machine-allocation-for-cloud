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
    String state; String energy;

    public Vmcl(String vm, String cpu, String ram, String state, String energy) {
        this.vm = vm;
        this.cpu = cpu;
        this.ram = ram;
        this.state = state;
        this.energy = energy;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }
    
}

