import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * abstract class Object 
 * this class will be the model for moving objects in the game
 */
public abstract class Object {
    
    //position en size
    int x, y, h, l;
    
    //speed
    float dx, dy, speed;
    
    //frame 
    Rectangle frameEdges;
    
    String objectName;
    
    public Object(int ax, int ay,int ah,int al , float adx, float ady, float aspeed , Rectangle aframe, String name) {
        x=ax; y=ay; h=ah; l=al;
        dx=adx; dy=ady; speed=aspeed;
        frameEdges=aframe;
        objectName=name;
    }

    /**this method draw into a Graphics
     * @param t time
     * @param g Graphics
     */
    abstract void draw(long t, Graphics g);

    /**this method move the object
     * @param t current time
     */
    abstract void move(double t);

    /** this methode move the Object with a frequency parameter
     * @param t time 
     * @param frquency
     */
    abstract void move(long t, double frquency);
    
}
