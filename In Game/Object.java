import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import java.io.File;

import javax.imageio.ImageIO;

public abstract class Object {
    
    int x, y, h, l;
    float dx, dy, speed;
    Image pict;
    Rectangle edges, frameEdges;
    String objectName;
    
    public Object(int ax, int ay, float adx, float ady, float aspeed ,String pictName, Rectangle aframe, String name) {
        x=ax; y=ay;
        dx=adx; dy=ady; speed=aspeed;
        try {
         pict= ImageIO.read(new File(pictName));
         }
        catch(Exception err) {
         System.out.println(pictName+" introuvable !");
         System.out.println("Mettre les images dans le repertoire :"
        +getClass().getClassLoader().getResource(pictName));
         System.exit(0);
         }
        h= pict.getHeight(null);
        l= pict.getWidth(null);
        edges = new Rectangle(ax,ay,l,h);
        frameEdges=aframe;
        objectName=name;
    }
    
    public void draw(long t, Graphics g){
        g.drawImage(pict, x, y, null);
    }
    
    abstract void move(long t);
    abstract void move(long t, double frquency);
    
}
