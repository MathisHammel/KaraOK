import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Note extends Object {
    
    int ymin,length;
    Rectangle Ecran;
    double creationTime;
    String note;
    double frequency; // maybe not usefull
    boolean destroy;
    
    public Note(String anote, Rectangle aframe, int length,double time ) {
        
        super((int)aframe.getWidth(),0,(int)aframe.getHeight()/30,length , -1, 0, (float)aframe.getWidth()/1000 , aframe, "note");
        ymin=(int)(aframe.getHeight()*0.1);
        creationTime=time;
        this.length=length;
        Ecran=aframe;
        note=anote;
        int a=0;
        while(!Content.notes[a].equals(note)){
            a++;
        }
        y=ymin*(1+a/2) -h/2;
        destroy=false;
        
    }

    @Override
    void draw(long t, Graphics g) {
        g.setColor(Content.colors[2]);
        g.fillRect(x, y, l, h);
        /**g.setColor(color.brighter());
        g.fillRect(x+ 2, y+h/4, l-4, h/8);*/

    }

    void move(double t) {
        double delta=t-creationTime;
        if(x+length>Ecran.getWidth()/6){
            x= (int)(Ecran.getWidth()/6 + Ecran.getWidth()*5/6 *(1- delta/5.0));
        }else{
            destroy=true;
        }
    }

    @Override
    void move(long t, double frquency) {
        // nothing, no need

    }
}
