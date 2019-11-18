import java.util.ArrayList;

public class Bionet {
    
    public ArrayList<Double> w = new ArrayList<Double>();
    public ArrayList<Double> b = new ArrayList<Double>();
    public double y = -0.7;
    public double l_r = 0.01; 
    public ArrayList<Bionet>  postSynapticConnections = new ArrayList<Bionet>();
    public ArrayList<Bionet>  preSynapticConnections = new ArrayList<Bionet>();
    public double threshold = -0.55;
    public static int id = 0;
    public int pid = 0;
    public Bionet() {
        this.pid = Bionet.id;
        Bionet.id+=1;
    }

    public void inject(double current) {
        this.y+=current;
    }


    public void fire() { //needs to be disabled once fired
        for(int i=0; i<this.preSynapticConnections.size(); i++) {
            this.y += this.w.get(i) * this.preSynapticConnections.get(i).y;
            this.y += this.b.get(i);
        }
        if(this.y>=this.threshold){ //fire when reached threshold
            System.out.println(this.pid + " fired");
            for(int i=0; i<this.postSynapticConnections.size(); i++) {
                this.postSynapticConnections.get(i).fire();
            }
        }
    }

    public void resetNet() {
        this.y = -0.7;
    }


    public void hebbian_update(Double postValue) {
        for(int i=0; i<this.preSynapticConnections.size(); i++) {
            this.w.set(i, this.w.get(i) + this.l_r * this.y * (postValue - this.y * this.w.get(i)));
            this.b.set(i, this.b.get(i) + this.l_r * (postValue));
            this.b.set(i, Math.min(Math.max(this.b.get(i), -1), 1));
            this.w.set(i, Math.min(Math.max(this.w.get(i), -1), 1));
        }
    }

    public void hebbian_update() {
        for(int i=0; i<this.preSynapticConnections.size(); i++) {
            this.w.set(i, this.w.get(i) + this.l_r * this.y * (this.preSynapticConnections.get(i).y - this.y * this.w.get(i)));
            this.b.set(i, this.b.get(i) + this.l_r * (this.preSynapticConnections.get(i).y));
            this.w.set(i, Math.min(Math.max(this.w.get(i), -1), 1));
            this.b.set(i, Math.min(Math.max(this.b.get(i), -1), 1));
        }
    }

    public void create_synapse(Bionet n) {
        this.postSynapticConnections.add(n);
        n.preSynapticConnections.add(this);
        n.w.add(0.0);
        n.b.add(0.0);
    }

}