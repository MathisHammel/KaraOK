import java.awt.Color;
import java.awt.Graphics;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LightPad extends JPanel{
    
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.setColor(new Color(127,140,141));
        g.fillRect(0, 0, 50, 50);
        g.setColor(Color.red);
        g.drawRect(0, 0, 50, 50);
    }
}
