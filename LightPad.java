import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

/**
 * class LightPad extends JLabel
 * this class create a JLabel with specifique paint method 
 * used in window.java and scoreWindow.java to generate the background
 */
public class LightPad extends JLabel{
    
    Color current ; // the current color of the LightPad

    /**LightPad constructor 
     * create a LightPad with grey background at (x,y) position 
     * @param x int x position
     * @param y int y position  
     */
    public LightPad(int x, int y){
        super();
        this.setBounds(x,y,50,50); 
        current= Content.mainColor; // set color to grey
    }

    /**
     * method that paint the LightPad
     * @param g Graphics
     */
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        
        //paint large grey rectangle for borders
        g.setColor(Content.mainColor);
        g.fillRect(0, 0, 50, 50);
        
        // paint smaller rectangle 
        g.setColor(current);
        g.fillRect(2, 2, 46, 46);
       
    }

    /**update the color og the LightPad with the main color (grey)
     * @return void
     */
    public void downdate(){
        
        current= Content.mainColor; 
        this.repaint();
        
    }


    /**
     * update the color of the LightPad with a random color from Content.colors
     * @return void
     */
    public void update (){
        
        // select a random color in the palet
        int pos = (int)(Math.random()*Content.colors.length); 
        current=Content.colors[pos];
        this.repaint();
        
    }
}
