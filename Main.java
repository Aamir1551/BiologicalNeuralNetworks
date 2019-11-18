public class Main {

    public static void main(String[] args) {

        System.out.println("Starting training...");
        
        
        Bionet a = new Bionet();
        Bionet b = new Bionet();
        Bionet c = new Bionet();
        Bionet d = new Bionet();
        a.create_synapse(b);
        b.create_synapse(c);
        c.create_synapse(d);
        

        for(int i=0; i<10000; i++) {
            
            a.resetNet();
            b.resetNet();
            c.resetNet();
            d.resetNet();
            a.inject(0.8);
            a.fire();
            d.hebbian_update(-1.0);
            c.hebbian_update(d.y);
            b.hebbian_update(c.y);
            System.out.println(  Double.toString(b.y) + " " + Double.toString(b.w.get(0)) + " " + Double.toString(b.b.get(0)));
            System.out.println(  Double.toString(c.y) + " " + Double.toString(c.w.get(0)) + " " + Double.toString(c.b.get(0)));
            System.out.println(  Double.toString(d.y) + " " + Double.toString(d.w.get(0)) + " " + Double.toString(d.b.get(0)));
            //a.hebbian_update(1.0);
            //System.out.println( Double.toString(a.y) + Double.toString(a.w.get(0)));
        }

        System.out.println("Training finished");

    }
}