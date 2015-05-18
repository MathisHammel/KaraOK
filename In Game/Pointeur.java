import java.awt.Rectangle;

public class Pointeur extends Object {
    
    static String[] picture = {"picture1","2","3", "and so on"}; // all the picts needed for unbelivable animations ! 
    int ymax, ymin; // edges for the pointer 
    
    public Pointeur(Rectangle aframe) {
        super((int)aframe.getWidth()/6, (int)aframe.getHeight()/2,  0,  0,  0 ," ", aframe, "pointeur");
        ymin=(int)aframe.getHeight()/6;
        ymax=ymin*5;
    }

    
    void move(long t, double frequency) {
        y =(int)((ymax-ymin)*frequency/(830.61-440.0)) ;
        
    }

    
    void move(long t) {
        // noting we need frequency to go further
    }
}
