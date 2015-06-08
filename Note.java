import java.awt.Graphics;
import java.awt.Rectangle;

/**class Note extends Object
 * this is the object that will show the note to sing 
 */
public class Note extends Object {
    
    // position of the first note
    int ymin;
    
    // the Rectangle to draw
    Rectangle Ecran;
    
    //when the elements was created
    double creationTime;
    
    // the note
    String note;
    
    // when out of the frame
    boolean destroy;

    /**the Note Constructor 
     * @param anote the note this will represent
     * @param aframe the frame og the game 
     * @param duration the duration of the note 
     * @param time the current time
     */
    public Note(String anote, Rectangle aframe, double duration,double time ) {
        super((int)aframe.getWidth(),0,(int)aframe.getHeight()/30,/*calcul the length*/(int)(duration*aframe.getWidth()/6) , -1, 0, (float)aframe.getWidth()/1000 , aframe, "note");
        
        ymin=(int)(aframe.getHeight()*0.1);
        
        creationTime=time;
        
        Ecran=aframe;
        
        note=anote;
        
        // place the Note to the y pos corresponding to the note
        int a=0;
        while(!Content.notes[a].equals(note)){
            a++;
        }
        y=(int)(ymin*(1+(11-a)*0.5) -h/2);
        
        destroy=false;
    }

    /**Override draw methode
     * draw the element on the fram
     * @param t time
     * @param g Graphics
     */
    void draw(long t, Graphics g) {
        g.setColor(Content.colors[2]);
        g.fillRect(x, y, l, h);

    }

    /**this method move the Note to a new position 
     * this position is calculated with the current time 
     * @param t current time
     */
    void move(double t) {
        double delta=t-creationTime;
        if(x+l>Ecran.getWidth()/6){
            x= (int)(Ecran.getWidth()/6 + Ecran.getWidth()*5/6 *(1- delta/5.0));
        }else{
            destroy=true;
        }
    }

    @Override
    void move(long t, double frequency) {
    }
}
