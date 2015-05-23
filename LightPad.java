import java.awt.Color;
import java.awt.Graphics;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LightPad extends JLabel{
    
    Color current ;
    
    public LightPad(int x, int y){
        super();
        this.setBounds(x,y,50,50);
        current= Content.mainColor;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.setColor(Content.mainColor);
        g.fillRect(0, 0, 50, 50);
        g.setColor(current);
        g.fillRect(2, 2, 46, 46);
       
    }
    
    public void downdate(long t){
        int pos = (int)(Math.random()*Content.colors.length);
        current= Content.mainColor;
        this.repaint(t);
    }
    
    public void update (long t){
        int pos = (int)(Math.random()*Content.colors.length);
        current=Content.colors[pos];
        this.repaint(t);
        
    }
}
