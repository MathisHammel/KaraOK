import java.awt.Color;
import java.awt.Graphics;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LightPad extends JLabel{
    
    Color current ;
    
    public LightPad(int x, int y){
        super();
        this.setBounds(x,y,50,50); // set size
        current= Content.mainColor; // set color to grey
    }
    
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        //paint large grey rectangle for borders
        g.setColor(Content.mainColor);
        g.fillRect(0, 0, 50, 50);
        // paint smaller rectangle 
        g.setColor(current);
        g.fillRect(2, 2, 46, 46);
       
    }
    
    public void downdate(){
        
        current= Content.mainColor; //set current color to grey
        this.repaint();
        
    }
    
    
    public void update (){
        
        // select a random color in the palet
        int pos = (int)(Math.random()*Content.colors.length); 
        current=Content.colors[pos];
        this.repaint();
        
    }
}
