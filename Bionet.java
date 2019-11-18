import java.util.ArrayList;

public class Bionet {
    
    public ArrayList<Double> w = new ArrayList<Double>();
    public double y = -0.7;
    public double l_r = 0.01; 
    public ArrayList<Bionet>  postSynapticConnections = new ArrayList<Bionet>();
    public ArrayList<Bionet>  preSynapticConnections = new ArrayList<Bionet>();
    public double threshold = -0.55;
    public Bionet(int inputs) {
        for(int i=0; i<inputs; i++) {
            this.w.add(0.0);
        }
    }


    public void fire() { //needs to be disabled once fired
        for(int i=0; i<this.preSynapticConnections.size(); i++) {
            System.out.println(this.preSynapticConnections.get(i).y);
            this.y += this.w.get(i) * this.preSynapticConnections.get(i).y;
        }
        if(this.y>=this.threshold){ //fire when reached threshold
            for(int i=0; i<this.postSynapticConnections.size(); i++) {
                this.postSynapticConnections.get(i).fire();
            }
        }
    }

    public void resetNet() {
        this.y = 0;
    }


    public void hebbian_update(Double postValue) {
        for(int i=0; i<this.postSynapticConnections.size(); i++) {
            this.w.set(i, this.w.get(i) + this.l_r * (postValue * this.y - this.y * this.y * this.w.get(i)));
        }
    }

    public void hebbian_update() {
        for(int i=0; i<this.postSynapticConnections.size(); i++) {
            this.w.set(i, this.w.get(i) + this.l_r * (this.postSynapticConnections.get(i).y * this.y - this.y * this.y * this.w.get(i)));
        }
    }

    public void create_synapse(Bionet n) {
        this.postSynapticConnections.add(n);
        n.preSynapticConnections.add(this);
        n.w.add(0.0);
    }

}