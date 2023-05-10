/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soutnoce_vm;

/**
 *
 * @author abderrahim
 */
public class Individual {
    String energy;
    String sla;
    double fit;
    String id ;

    public Individual(String energy, String sla, double fit, String id) {
        this.energy = energy;
        this.sla = sla;
        this.fit = fit;
        this.id = id;
    }

    
    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getSla() {
        return sla;
    }

    public void setSla(String sla) {
        this.sla = sla;
    }

    public double getFit() {
        return fit;
    }

    public void setFit(double fit) {
        this.fit = fit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
   
    
}
