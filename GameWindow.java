import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
    
       static String background = "img\\GameBG.png";
    // static String[] song = ...  
    
        Timer timer; 
        long time; 
        int score; 
        boolean finjeu; 
        Note test;
        
        Pointeur pointeur;
                        
        BufferedImage ArrierePlan; 
        Graphics buffer; 
        Rectangle Ecran; 
                         
        //Pointeur pointeur;
        
        //LinkedList <objet> Objets;
        
        
        
        public GameWindow() {
            
            /** affichage plein écran !!! */
                             
            setTitle("Kara-OK");
            setUndecorated(true);
            this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
           
           
            time = 0;
            score = 0;
            finjeu=false;
            
            Ecran=new Rectangle(getInsets().left,getInsets().top,getSize().width-getInsets().right-getInsets().left,getSize().height-getInsets().bottom-getInsets().top);
            ArrierePlan =new BufferedImage(getSize().width,getSize().height,BufferedImage.TYPE_INT_RGB);
            buffer = ArrierePlan.getGraphics();
            
            pointeur = new Pointeur(Ecran);
            
            timer = new Timer(20,new TimerAction());
            
            this.addKeyListener(new GameKeyAdapter());
            
            test = new Note("B",Ecran,200);
            System.out.println();
            System.out.println(test.h);System.out.println(test.x);
            
            timer.start();
            
            setVisible(true);
            
            
        }
        
        public void paint(Graphics g){
            
            /*ImageIcon gameAdd = new ImageIcon("img\\GameAdd.png");
            ImageIcon img = new ImageIcon(background);
            buffer.drawImage(img.getImage(), gameAdd.getIconWidth(), 0, img.getImageObserver());*/
            paintBackGround(buffer,Ecran);
            
            test.draw(time,buffer);
            
           // buffer.drawImage(gameAdd.getImage(),0,0,gameAdd.getImageObserver()); // draw last
            paintAdd(buffer, Ecran);
            pointeur.draw(time,buffer); // draw last 
            g.drawImage(ArrierePlan,0,0,this);
        }
        public void paintBackGround(Graphics g, Rectangle aframe){
            g.setColor(new Color(52,152,219));
            g.fillRect(0, 0, (int)aframe.getWidth(),(int) aframe.getHeight());
            g.setColor(new Color(41,128,185));
            for(int i=0; i<13; i++){
                fillRectY(buffer,0, (int)(aframe.getHeight()*(1+i*0.5)*0.1) , (int)aframe.getWidth(), (int)(aframe.getHeight()/(100+100*(i%2))));
               
            }
        }
        
        public void paintAdd(Graphics g, Rectangle aframe){
            g.setColor(new Color(52,152,219));
            g.fillRect(0,0,(int)aframe.getWidth()/6,(int)aframe.getHeight());
            g.setColor(Color.yellow);
            fillRectX(g,(int)aframe.getWidth()/6,0,(int)aframe.getWidth()/100,(int)aframe.getHeight());
        }
        
        //dessine les rectangles en les centrant sur les coordonnées y !
        public void fillRectY(Graphics g, int x, int y, int l, int h){
            g.fillRect(x,y-h/2,l,h);
        }
        public void fillRectX(Graphics g, int x, int y, int l, int h){
            g.fillRect(x-l/2,y,l,h);
        }

        private class TimerAction implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
        game_display();
        time++;
        }

        
    }
        
        public void game_display(){
            test.move(time);
            repaint();
    
        }
        
     /**public static void main(String[] args) {
        GameWindow Monjeu = new GameWindow();
        }*/
        
    private class GameKeyAdapter extends KeyAdapter{
            
        
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code){
            case KeyEvent.VK_ESCAPE: System.exit(0);
                break;
            case KeyEvent.VK_ENTER:
                    if (!timer.isRunning()) {
                        timer.start();
                    } else {
                        timer.stop();
                    }
                break;
                }
            }
        }
    }

   

