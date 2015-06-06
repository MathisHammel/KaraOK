
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.io.File;

import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.SwingConstants;


public class GameWindow extends JFrame{


    // static String[] song = ...

        Timer timer;
        long time;
        int score;
        boolean finjeu;
        Note test;
        JLabel popup, title, lyrics;
        boolean pop;
        Pointeur pointeur;

        BufferedImage ArrierePlan;
        Graphics buffer;
        Rectangle Ecran;

        Clip clip;
        int song;
        static Karaok.State state;
        
        // pointeurs + notes.
        LinkedList <Note> note;





        public GameWindow(int asong) {

            /** affichage plein écran !!! */
            // frame
            
            setTitle("Kara-OK");
            setUndecorated(true);
            this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
            this.setLayout(null);
            getContentPane().setBackground(new Color(Color.TRANSLUCENT));
            
            this.setIconImage(new ImageIcon(Content.icon).getImage());
            
            
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
                    //clip.start();
                } catch(Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }

            // key adapter
            this.addKeyListener(new GameKeyAdapter());

            test = new Note("B",Ecran,200);

            // timer
            timer = new Timer(10,new TimerAction());
            timer.start();
            
            title = new JLabel(Content.titles[song],SwingConstants.CENTER);
            title.setOpaque(false);
            title.setForeground(Color.white);
            title.setFont(new Font("LAIKA", Font.PLAIN, (int)Ecran.getHeight()/20));
            title.setBounds(0,0,(int)Ecran.getWidth(),(int)Ecran.getHeight()/15);
            getContentPane().add(title);
            
            
            // initialisation des notes:
            note= new LinkedList();

            setVisible(true);


        }

        public void paint(Graphics g){

            
            paintBackGround(buffer,Ecran);
            test.draw(time,buffer);
            
            paintAdd(buffer, Ecran);
           
            getContentPane().paintComponents(buffer);
            pointeur.draw(time,buffer); // draw last
            buffer.setColor(Color.white);
            buffer.setFont(new Font("LAIKA", Font.BOLD, (int)Ecran.getHeight()/20));
            buffer.drawString("score: "+ score , (int)(Ecran.getWidth()*0.80), (int)Ecran.getHeight()/20);

            g.drawImage(ArrierePlan,0,0,this);
        }
        public void paintBackGround(Graphics g, Rectangle aframe){
            g.setColor(Content.background[song*2]);
            g.fillRect(0, 0, (int)aframe.getWidth(),(int) aframe.getHeight());
            g.setColor(Content.background[song*2+1]);
            for(int i=0; i<13; i++){
                fillRectY(buffer,0, (int)(aframe.getHeight()*(1+i*0.5)*0.1) , (int)aframe.getWidth(), (int)(aframe.getHeight()/(100+100*(i%2))));

            }
        }

        public void paintAdd(Graphics g, Rectangle aframe){
            g.setColor(Content.background[song*2]);
            g.fillRect(0,0,(int)aframe.getWidth()/6,(int)aframe.getHeight());
            g.setColor(Content.background[song*2+1]);
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
            pointeur.move(time,Karaok.freqmaster.mainFreq); //FIXME
            pointeur.changeColor(Math.abs(587.33-Karaok.freqmaster.mainFreq));
            repaint();


        }

     
     public void songWillStart(){
         int timeleft=5;
         popup = new JLabel("KARA-OK",SwingConstants.CENTER);
         popup.setBounds((int)Ecran.getWidth()*3/8,(int)(Ecran.getHeight()*0.35),(int)Ecran.getWidth()/4, (int)(Ecran.getHeight()*0.30));
         popup.setBackground(Content.colors[2]);
         popup.setOpaque(true);
         popup.setForeground(Color.white);
         popup.setFont(new Font("LAIKA", Font.BOLD, (int)Ecran.getHeight()/17));
         popup.setText("<html><center>GET READY !<br> The song will start in: <br> " + timeleft );
         this.getContentPane().add(popup);
         
         while(timeleft>0){
             timeleft= 5- (int)time/100;
             popup.setText("<html><center>GET READY !<br> The song will start in: <br> " + timeleft );
         }
         getContentPane().remove(popup);
         popup=null;
         clip.start();
         time=0;
     }
     
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




