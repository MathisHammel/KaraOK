import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreGraph extends JPanel {
    
    int i=4; // variable used in updated methode
    
    scoreWindow scorewindow;
    
    //scores
    int scoreRect; // the actual Height of the score rectangle
    int score; // the height final height of score rectangle
    int sc; // the real score
    
    // edges of rectangles 
    static int ymin=49, ymax=298;
    static int scoremax=5000;
    
    // components 
    JLabel dancer;
    JLabel scoreStars;
    JLabel[] stars= new JLabel[10];
    
    // colors for MJ dancefloor
    Color[] c = new Color[4];
        
    public ScoreGraph(scoreWindow scorewindow) {
        super();
        
        this.scorewindow=scorewindow;
        sc=scorewindow.score;
        
        //set the this
        this.setBackground(Content.colors[12]);
        this.setOpaque(true);
        this.setBounds(52,52,146,496);
        this.setLayout(null);
        
        //set Colors 
        for(int j =0;j<4;j++){
            c[j]=Content.colors[1+j*3];
        }
        
        // set scores
        score=(int)(scorewindow.score*ymax/scoremax) ;
        scoreRect=ymax;
        
        // create MichaelJackson 
        dancer=new JLabel(new ImageIcon("img\\mouchaeljack.gif"));
        dancer.setBounds(73, ymin+scoreRect-32, 100,100 );
        scorewindow.getContentPane().add(dancer);
        
        //create the Label to add stars 
        scoreStars = new JLabel();
        scoreStars.setBackground(Content.colors[12]);
        scoreStars.setOpaque(true);
        scoreStars.setBounds(252,102,396,96);
        scorewindow.getContentPane().add(scoreStars);
        
        // set the stars 
        for(int i=0; i<5;i++){
            stars[i]=new JLabel(new ImageIcon("img\\NextPrev\\StarOff_small.png"));
            stars[i].setOpaque(true);
            stars[i].setBounds(100,(int)(ymin+i*ymax/5)-25,50,50);
            this.add(stars[i]);
            
            stars[i+5]=new JLabel(new ImageIcon("img\\NextPrev\\StarOff.png"));
            stars[i+5].setOpaque(true);
            stars[i+5].setBounds(319-i*73,24,50,50);
            scoreStars.add(stars[i+5]);
        }
        
    }
    public void paint(Graphics g){
        //paint the background
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        //paint borders
        g.setColor(Color.white);
        g.fillRect(48, ymin, 50, ymax);
        g.setColor(this.getBackground()); 
        g.fillRect(49,ymin+1,48,scoreRect); // this rectangle will get smaller 
        
        // paint dancefloor
        Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp;
        for(int j=0;j<4;j++){
            gp = new GradientPaint(0, ymin+scoreRect-39, Content.colors[12], 0, ymin+scoreRect+40, c[j],true);
            g2d.setPaint(gp);
            g2d.fillRect(49+j*12,ymin+scoreRect-39,12,39);
            g.setColor(c[j]);
            g.fillRect(49+j*12,ymin+scoreRect,12,5);
        }
        
        // paint all the components
        this.paintComponents(g);
        
    }

    /**
     * update method
     * will be updated each timer action on scoreWindow
     * manage the score animation 
     * @param time the time since the scorewindow opended
     */
    public void update(long time){

        //update everything with time only after a short delay = 50 
        if(time>50){
            if(scoreRect!=ymax-score){
                scoreRect=ymax-(int)(score*(time-50)/(150+time*0.3));
                dancer.setBounds(73, ymin+scoreRect-32, 100,100 );
                if(i!=-1 && scoreRect<=stars[i].getY()-20){
                    System.out.println("[DEBUG] i= " + i + "stars = " + stars[i].getY()+"scoreRect=" + scoreRect);
                    stars[i].setIcon(new ImageIcon("img\\NextPrev\\StarOn_small.png"));
                    stars[i+5].setIcon(new ImageIcon("img\\NextPrev\\StarOn.png"));
                    i--;
                }
            }
            //change colors of the dancefloor
            if(time%20==0){
                for(int j=0; j<4;j++){
                    int pos = (int)(Math.random()*11);
                    c[j]=Content.colors[pos];
                }
            }
            repaint();
        }
        
    }
}
