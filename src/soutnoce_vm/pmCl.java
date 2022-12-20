
package soutnoce_vm;

/**
 *
 * @author abderrahim
 */
public class pmCl {
    String pm;
    String cpu;
    String ram;
    String state; String energy;

    public pmCl(String pm, String cpu, String ram, String state, String energy) {
        this.pm = pm;
        this.cpu = cpu;
        this.ram = ram;
        this.state = state;
        this.energy = energy;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
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
