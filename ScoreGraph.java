import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;

import java.awt.Graphics2D;



import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreGraph extends JPanel {
    int i=4;
    scoreWindow scorewindow;
    int scoreRect;
    int score,sc;
    static int ymin=49, ymax=298;
    static int scoremax=5000;
    JLabel dancer2;
    JLabel scoreStars;
    JLabel[] stars= new JLabel[10];
    Color c1,c2,c3,c4;
        
    public ScoreGraph(scoreWindow scorewindow) {
        super();
        this.scorewindow=scorewindow;
        this.setBackground(Content.colors[12]);
        this.setOpaque(true);
        this.setBounds(52,52,146,496);
        this.setLayout(null);
        c1=Content.colors[1];
        c2=Content.colors[4];
        c3=Content.colors[8];
        c4=Content.colors[10];
        sc=scorewindow.score;
        score=(int)(scorewindow.score*ymax/scoremax) ;
        scoreRect=ymax;
        
        dancer2=new JLabel(new ImageIcon("C:\\Users\\Administrateur\\Documents\\mouchaeljack.gif"));
        dancer2.setBounds(73, ymin+scoreRect-32, 100,100 );
        scorewindow.getContentPane().add(dancer2);
        
        scoreStars = new JLabel();
        scoreStars.setBackground(Content.colors[13]);
        scoreStars.setOpaque(true);
        scoreStars.setBounds(252,102,396,96);
        scorewindow.getContentPane().add(scoreStars);
        
        for(int i=0; i<5;i++){
            stars[i]=new JLabel(new ImageIcon("img\\PlayOff.png"));
            stars[i].setOpaque(true);
            stars[i].setBounds(100,(int)(ymin+i*ymax/5)-25,50,50);
            this.add(stars[i]);
            
            stars[i+5]=new JLabel(new ImageIcon("img\\PlayOff.png"));
            stars[i+5].setOpaque(true);
            stars[i+5].setBounds(321-i*75,25,50,50);
            scoreStars.add(stars[i+5]);
        }
        
    }
    public void paint(Graphics g){
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.white);
        g.fillRect(48, ymin, 50, ymax);
        g.setColor(this.getBackground());
        g.fillRect(49,ymin+1,48,scoreRect);
        
        Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp = new GradientPaint(0, ymin+scoreRect-39, Content.colors[12], 0, ymin+scoreRect+40, c1,true);
        g2d.setPaint(gp);
        g2d.fillRect(49,ymin+scoreRect-39,12,39);
        g.setColor(c1);
        g.fillRect(49,ymin+scoreRect,12,5);
        
        
        gp = new GradientPaint(0, ymin+scoreRect-39, Content.colors[12], 0, ymin+scoreRect+40, c2,true);
        g2d.setPaint(gp);
        g2d.fillRect(61,ymin+scoreRect-39,12,39);
        g.setColor(c2);
        g.fillRect(61,ymin+scoreRect,12,5);
        
        gp = new GradientPaint(0, ymin+scoreRect-39, Content.colors[12], 0, ymin+scoreRect+40, c3,true);
        g2d.setPaint(gp);
        g2d.fillRect(73,ymin+scoreRect-39,12,39);
        g.setColor(c3);
        g.fillRect(73,ymin+scoreRect,12,5);
        
        gp = new GradientPaint(0, ymin+scoreRect-39, Content.colors[12], 0, ymin+scoreRect+40, c4,true);
        g2d.setPaint(gp);
        g2d.fillRect(85,ymin+scoreRect-39,12,39);
        g.setColor(c4);
        g.fillRect(85,ymin+scoreRect,12,5);
        
        g.setColor(Color.white);
        g.setFont((new Font("LAIKA", Font.BOLD, 17)));
        g.drawString("score: "+sc, 25, 375);
        
        this.paintComponents(g);
        
    }
    public void update(long time){
        if(time>50){
            if(scoreRect!=ymax-score){
                scoreRect=ymax-(int)(score*(time-50)/(150+time*0.3));
                dancer2.setBounds(73, ymin+scoreRect-32, 100,100 );
                if(i!=-1 && scoreRect<=stars[i].getY()-20){
                    System.out.println("[DEBUD] i= " + i + "stars = " + stars[i].getY()+"scoreRect=" + scoreRect);
                    stars[i].setIcon(new ImageIcon(Content.play +"On.png"));
                    stars[i+5].setIcon(new ImageIcon(Content.play+"On.png"));
                    i--;
                }
            }
            if(time%20==0){
                int pos = (int)(Math.random()*11);
                c1=Content.colors[pos];
                pos = (int)(Math.random()*11);
                c2=Content.colors[pos];
                pos = (int)(Math.random()*11);
                c3=Content.colors[pos];
                pos = (int)(Math.random()*11);
                c4=Content.colors[pos];
            }
            repaint();
        }
        
    }
}
