import java.awt.Graphics;

import java.awt.Rectangle;


public abstract class Object {
    
    int x, y, h, l;
    float dx, dy, speed;
    Rectangle frameEdges;
    String objectName;
    
    public Object(int ax, int ay,int ah,int al , float adx, float ady, float aspeed , Rectangle aframe, String name) {
        x=ax; y=ay; h=ah; l=al;
        dx=adx; dy=ady; speed=aspeed;
        frameEdges=aframe;
        objectName=name;
    }
    
    abstract void draw(long t, Graphics g);
    
    abstract void move(double t);
    
    abstract void move(long t, double frquency);
    
}
