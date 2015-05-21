import java.awt.Color;
import java.awt.Graphics;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LightPad extends JPanel{
    static Color[] colors = {new Color(26,188,156),new Color(22,160,133),new Color(241,196,15),new Color(243,156,18),new Color(46,204,113),new Color(39,174,96),new Color(230,126,34), new Color(211,84,0),new Color(52,152,219),new Color(41,128,185),new Color(231,76,60),new Color(192,57,43), new Color(155,89,182),new Color(142,68,173)};
    static Color mainColor =Color.black; //new Color(127,140,141);
    Color current ;
    
    public LightPad(){
        current= mainColor;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.setColor(mainColor);
        g.fillRect(0, 0, 50, 50);
        g.setColor(current);
        g.fillRect(2, 2, 46, 46);
       
    }
    
    public void downdate(long t){
        int pos = (int)(Math.random()*colors.length);
        current= mainColor;
        this.repaint(t);
    }
    
    public void update (long t){
        int pos = (int)(Math.random()*colors.length);
        current=colors[pos];
        this.repaint(t);
        
    }
}
