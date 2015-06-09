import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


/**class Pointeur extends Object
 * this class create the pointeur that show the note currently singed
 */
public class Pointeur extends Object {

    Color color; // the color off pointeur
    int ymax, ymin; // edges for the pointeur deplacements

    public Pointeur(Rectangle aframe) {
        super((int)aframe.getWidth()/6, (int)aframe.getHeight()/2, (int)aframe.getHeight()/10, (int)aframe.getHeight()/20,  0,  0,  0 , aframe, "pointeur");
        ymin=(int)(aframe.getHeight()*0.1);
        ymax=(int)(aframe.getHeight()*0.7);
        color= Content.mainColor;
    }


    /**
     * Override move method frome Object
     * @param t time
     * @param frequency the frequency currently singed
     */
    void move(long t, double frequency) {
        
        y=(int)( ymax - ((frequency-440)/440)*(ymax-ymin));
    }


    void move(double t) {
        // nothing, we need frequency to go further
    }


    /**Override the draw method from Object
     * @param t time 
     * @param g Graphics
     */
    void draw(long t, Graphics g) {
        g.setColor(color);
        color = color.darker();
        g.setColor(color);
        g.fillOval((int)x-l/2,(int) y-h/2, l, h);
        color=color.brighter();
        g.setColor(color);
        g.fillOval((int)x-l/2 +4, (int)y-h/2 +4, l-8, h-8);

    }

    /**this method change the color of the pointeur depending of score calculated for this note 
     * @param score the score calculated for this note
     */
    public void changeColor ( double score){
        if(score==-1){color= Content.mainColor;}
        else if(score>6){ color= Content.colors[5];}
        else if(score>3){color= Content.colors[2];}
        else if(score>0){color= Content.colors[6];}
            else if (score==0){color = Content.colors[10];}
    }
}


