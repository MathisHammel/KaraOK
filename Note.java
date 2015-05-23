import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Note extends Object {
    static String[] notes ={"A","Bb","B","C","Db","D","Eb","E","F","Gb","G","Ab","A"};
    int ymin;
    String note;
    double frequency; // maybe not usefull
    
    public Note(String anote, Rectangle aframe, int length) {
        super((int)aframe.getWidth(),0,(int)aframe.getHeight()/30,length , -1, 0, 1 , aframe, "note");
        ymin=(int)(aframe.getHeight()*0.1);
        note=anote;
        int a=0;
        while(!notes[a].equals(note)){
            a++;
        }
        y=ymin*(1+a/2) -h/2;
        
    }

    @Override
    void draw(long t, Graphics g) {
        Color color = new Color(231,76,60);
        g.setColor(color);
        g.fillRect(x, y, l, h);
        /**g.setColor(color.brighter());
        g.fillRect(x+ 2, y+h/4, l-4, h/8);*/

    }

    @Override
    void move(long t) {
        x+= dx*speed;
    }

    @Override
    void move(long t, double frquency) {
        // nothing, no need

    }
}
