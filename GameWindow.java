 //pourquoi ne marche pas ?????
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

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


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

        Clip clip;
        int song;
        FreqThread freqmaster;
        static Karaok.State state;

        //LinkedList <objet> Objets;





        public GameWindow(int asong) {

            /** affichage plein écran !!! */
            // frame
            freqmaster=new FreqThread();
            freqmaster.start();
            setTitle("Kara-OK");

            setUndecorated(true);
            this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
            this.setLayout(null);
            // buffer
            Ecran=new Rectangle(getInsets().left,getInsets().top,getSize().width-getInsets().right-getInsets().left,getSize().height-getInsets().bottom-getInsets().top);
            ArrierePlan =new BufferedImage(getSize().width,getSize().height,BufferedImage.TYPE_INT_RGB);
            buffer = ArrierePlan.getGraphics();
            // variables
            time = 0;
            score = 0;
            finjeu=false;

           //STATE !!!!!!!!!
            state = Karaok.State.Game;

            pointeur = new Pointeur(Ecran);
            // music
            song=asong;
            try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Content.files[3][song]).getAbsoluteFile());
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch(Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }

            // key adapter
            this.addKeyListener(new GameKeyAdapter());

            test = new Note("B",Ecran,200);

            // timer
            timer = new Timer(20,new TimerAction());
            timer.start();

            setVisible(true);


        }

        public void paint(Graphics g){


            paintBackGround(buffer,Ecran);
            test.draw(time,buffer);


            paintAdd(buffer, Ecran);
            pointeur.draw(time,buffer); // draw last
            g.drawImage(ArrierePlan,0,0,this);
        }
        public void paintBackGround(Graphics g, Rectangle aframe){
            g.setColor(Content.colors[8]);
            g.fillRect(0, 0, (int)aframe.getWidth(),(int) aframe.getHeight());
            g.setColor(Content.colors[9]);
            for(int i=0; i<13; i++){
                fillRectY(buffer,0, (int)(aframe.getHeight()*(1+i*0.5)*0.1) , (int)aframe.getWidth(), (int)(aframe.getHeight()/(100+100*(i%2))));

            }
        }

        public void paintAdd(Graphics g, Rectangle aframe){
            g.setColor(Content.colors[8]);
            g.fillRect(0,0,(int)aframe.getWidth()/6,(int)aframe.getHeight());
            g.setColor(Content.colors[9]);
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
            pointeur.move(time,freqmaster.mainFreq);
            pointeur.changeColor(Math.abs(587.33-freqmaster.mainFreq));
            repaint();


        }



     /**public static void main(String[] args) {
        GameWindow Monjeu = new GameWindow();
        }*/

    private class GameKeyAdapter extends KeyAdapter{


        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code){
            case KeyEvent.VK_ESCAPE: 
                state=Karaok.State.Pause;
                    JOptionPane jop = new JOptionPane();            
                          int option = jop.showConfirmDialog(null, 
                            "Are you sure you wan't to leave ?", 
                            "Leaving game", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.WARNING_MESSAGE);

                          if(option == JOptionPane.OK_OPTION){
                            System.exit(0);
                          }
                break;
            
            case KeyEvent.VK_P:
                System.out.println("[DEBUG] P Key pressed");
                state=Karaok.State.Pause;
                }
            }
        }
    }



