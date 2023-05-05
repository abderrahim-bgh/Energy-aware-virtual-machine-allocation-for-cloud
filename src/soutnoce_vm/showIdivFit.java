/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soutnoce_vm;

/**
 *
 * @author abderrahim
 */
public class showIdivFit {
    String indiv,fit;
    String nb;

    public showIdivFit(String nb, String indiv, String fit) {
        this.indiv = indiv;
        this.fit = fit;
        this.nb = nb;
    }

    public void setNb(String nb) {
        this.nb = nb;
    }

    public String getNb() {
        return nb;
    }

    public void setIndiv(String indiv) {
        this.indiv = indiv;
    }

    public String getFit() {
        return fit;
    }

    public String getIndiv() {
        return indiv;
    }

    public void setFit(String fit) {
        this.fit = fit;
    }
    
}
