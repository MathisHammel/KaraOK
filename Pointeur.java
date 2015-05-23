import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Pointeur extends Object {

    Color color; // the color off pointeur
    int ymax, ymin; // edges for the pointer

    public Pointeur(Rectangle aframe) {
        super((int)aframe.getWidth()/6, (int)aframe.getHeight()/2, (int)aframe.getHeight()/10, (int)aframe.getHeight()/20,  0,  0,  0 , aframe, "pointeur");
        ymin=(int)aframe.getHeight()/10;
        ymax=ymin*7;
        color= Content.mainColor;
    }


    void move(long t, double frequency) {
        //y =(int)(ymin*frequency/440) ;
        y=(int)( ymin + ((frequency-440)/440)*(ymax-ymin));
    }


    void move(long t) {
        // nothing, we need frequency to go further
    }


    void draw(long t, Graphics g) {
        g.setColor(color);
        color = color.darker();
        g.setColor(color);
        g.fillOval((int)x-l/2,(int) y-h/2, l, h);
        color=color.brighter();
        g.setColor(color);
        g.fillOval((int)x-l/2 +6, (int)y-h/2 +6, l-12, h-12);

    }
    public void changeColor ( double deltanote){
        if(deltanote==-1){color= Content.mainColor;}
        else if(deltanote<20){ color= Color.green;}
        else if(deltanote<40){color= Color.yellow;}
        else if(deltanote<60){color= Color.orange;}
            else if (deltanote>=60){color = Color.red;}
    }
}


