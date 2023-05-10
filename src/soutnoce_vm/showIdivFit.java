
package soutnoce_vm;

/**
 *
 * @author abderrahim
 */
public class showIdivFit {
    String indiv,fit;
    String nb,sla,enrgy;

    public void setEnrgy(String enrgy) {
        this.enrgy = enrgy;
    }

    public void setSla(String sla) {
        this.sla = sla;
    }

    public String getEnrgy() {
        return enrgy;
    }

    public String getSla() {
        return sla;
    }
    
    public showIdivFit(String nb, String indiv, String fit,String sla,String enrgy ) {
        this.indiv = indiv;
        this.fit = fit;
        this.nb = nb;
        this.sla=sla;
        this.enrgy=enrgy;
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
