public class Main {

    public static void main(String[] args) {

        System.out.println("Starting training...");
        
        
        InputNeuron q = new InputNeuron(0.8);
        Bionet a = new Bionet(1);
        Bionet b = new Bionet(0);
        q.create_synapse(a);
        //a.create_synapse(b);
        

        for(int i=0; i<100; i++) {
            a.resetNet();
            b.resetNet();
            a.fire();
            //b.hebbian_update(1.0);
            //a.hebbian_update(b.y);
            //System.out.println(  Double.toString(b.y) + " " + Double.toString(a.w.get(0)));
            a.hebbian_update(1.0);
            System.out.println( Double.toString(a.y) + Double.toString(a.w.get(0)));
        }

        System.out.println("Training finished");

    }
}