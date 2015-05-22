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
    
       static String background = "C:\\Users\\Administrateur\\Documents\\GitHub\\Projetinfo\\KaraOK\\img\\GameBG.png";
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
            
            timer = new Timer(10,new TimerAction());
            
            this.addKeyListener(new GameKeyAdapter());
            
            test = new Note("B",Ecran,50);
            System.out.println();
            System.out.println(test.h);System.out.println(test.x);
            
            timer.start();
            
            setVisible(true);
            
            
        }
        
        public void paint(Graphics g){
            //Color couleur = new Color(254, 40, 162);
           // buffer.setColor(couleur);
           // buffer.fillRect(Ecran.x,Ecran.y,Ecran.x+Ecran.width,Ecran.y+Ecran.height);
            ImageIcon img = new ImageIcon(background);
            buffer.drawImage(img.getImage(), 0, 0, img.getImageObserver());
            pointeur.draw(time,buffer);
            test.draw(time,buffer);
            g.drawImage(ArrierePlan,0,0,this);
        }

        private class TimerAction implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
        game_display();
        time++;
        }

        
    }
        
        public void game_display(){
            
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

   

