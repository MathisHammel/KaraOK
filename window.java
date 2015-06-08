

import java.awt.Color;
import java.awt.Cursor;

import java.awt.Image;
import java.awt.Point;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.File;

import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;

import javax.swing.Timer;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


/**
 * class window extends JFram
 * this is the class of the MainMenu 
 */
public class window extends JFrame {
    
    // the current state of the game
    Karaok.State state;
    
    // the LightPad background animation
    LightPad[] lightPad; 
    
    // the song 
    int song; // refer to the current song
    Clip clip; // to play the song 
    
    // Frame component
    Button  close, minimize; // window interaction
    Button next, prev,play ,pict, nextpict, prevpict; // game interaction
    JLabel title, logo; 
    
    //timer
    Timer timer; // swing Timer 
     
     
    
    
    /** 
    * the launcher window constructor
    * create a new launcher window
    * Size 700,500
    * presse esce to exit, enter to stop timer,
    */
    public window() {
        
        state=Karaok.State.Menu;
        
        //create the Frame 
        setTitle("Kara-OK");
        setUndecorated(true); //no borders 
        setSize(700, 500);
        this.setLocationRelativeTo(getRootPane()); // place the frame on the center of the screen
        this.setIconImage(new ImageIcon(Content.icon).getImage()); // icon of the game 
        this.setLayout(null); //to place each component manually
        
        // set the mouse pointer as a mic 
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage(Content.cursor);
        Cursor cursor = tk.createCustomCursor(img, new Point(0, 0), "micro");
        this.setCursor(cursor);
        
        //create the background with lightPad
        this.setBackground(new Color(127,140,141,254)); // main color + alpha max
        lightPad=new LightPad[108];
        int b=0; // int to fill the lightPad
        
        
        //place each LightPad where there is no other elements 
        for(int i=0;i<14;i++){
            for(int j=0;j<10;j++){
                if(((j==4 || j==5) && (1<i && i<12)) || ((j==6 || j==7) &&(i==6 || i==7)));
                else{if(j==1 &&(4<i && i<9 )|| j==3 &&(4<i && i<9) ) ;
                     else{
                         lightPad[b]=new LightPad(50*i,50*j); 
                         this.getContentPane().add(lightPad[b]);
                         b++;
                     }
                }
                
            }
        }
        
        lightPad[98].setBounds(650,23,50,25); // special lightPad under minimize and close button
        
        /* set interactives button */
        
        // the close button to close the game
        close=new Button(Content.close);
        close.setBounds(675,0,25,23);
        close.setBorderPainted(false);
        close.addActionListener(new closeListener());
        close.addMouseListener(close);
        this.getContentPane().add(close);
        
        // the minimize button to minimize game launcher
        minimize=new Button(Content.minimize);
        minimize.setBounds(650,0,25,23);
        minimize.setBorderPainted(false);
        minimize.addActionListener(new minimizeListener());
        minimize.addMouseListener(minimize);
        this.getContentPane().add(minimize);
        
        // the prev button to select previous song 
        prev=new Button(Content.prev);
        prev.setBounds(102,202,96,96);
        prev.setBorderPainted(false);
        prev.addActionListener(new prevListener());
        prev.addMouseListener(prev);
        this.getContentPane().add(prev);
        
        //the prevpict showing the pict of the prev song
        prevpict=new Button(new ImageIcon(Content.files[1][Content.files[0].length-1]));
        prevpict.setBounds(202,202,96,96);
        prevpict.setBorderPainted(false);
        this.getContentPane().add(prevpict);
        
        // the pict button showing the song displayed 
        pict=new Button(new ImageIcon(Content.files[1][0]));
        pict.setBounds(302,202,96,96);
        pict.setBorderPainted(false);
        pict.addActionListener(new pictListener());
        this.getContentPane().add(pict);
        
        // the nextpict button showing the next song pict, clicking on it will select this song
        nextpict=new Button(new ImageIcon(Content.files[1][1]));
        nextpict.setBounds(402,202,96,96);
        nextpict.setBorderPainted(false);
        this.getContentPane().add(nextpict);
        
        // the next button to select next song 
        next=new Button(Content.next);
        next.setBounds(502,202,96,96);
        next.setBorderPainted(false);
        next.addActionListener(new nextListener());
        next.addMouseListener(next);
        this.getContentPane().add(next);
        
        //the play button to start the game 
        play=new Button(Content.play);
        play.setBounds(302,302,96,96);
        play.setBorderPainted(false);
        play.addActionListener(new pictListener());
        play.addMouseListener(play);
        this.getContentPane().add(play);
        
        //the title of the song  
        title = new JLabel(Content.files[2][0],SwingConstants.CENTER);
        title.setBackground(Content.colors[2]);
        title.setOpaque(true);
        title.setForeground(Color.white);
        title.setFont(Content.font.deriveFont(16f));
        title.setBounds(252,152,196,46);
        this.getContentPane().add(title);
        
        //the logo of the game
        logo = new JLabel("KARA-OK",SwingConstants.CENTER);
        logo.setBackground(Content.colors[13]);
        logo.setOpaque(true);
        logo.setForeground(Color.white);
        logo.setFont(Content.font.deriveFont(40f));
        logo.setBounds(252,52,196,46);
        this.getContentPane().add(logo);
        
       
        // the timer   
        timer = new Timer(20,new TimerAction());
        timer.start();
        
        this.setFocusable(true);
        this.requestFocus(); //give focus to the window for keyboard actions
        
        this.addKeyListener(new GameKeyAdapter());
        
        //play song  
        song=0;  //initialise the song to 0: first song
        try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Content.files[0][song]).getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch(Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
       
        
        setVisible(true);
       
    }
    
    /**class nextListener implements ActionListener
     * the ActionListener of next Button
     */
    class nextListener implements ActionListener{
        /**
         * Override actionPerformed method
         * update the song, and picts 
         * @param arg0 ActionEvent
         */
        public void actionPerformed (ActionEvent arg0){
            // change song value
            if(song+1>Content.files[0].length-1)song=0;
            else song++;
            
            //stop song 
            clip.close();
            
            //start new song 
            try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Content.files[0][song]).getAbsoluteFile());
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch(Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
            
            //update picts
            prevpict.setIcon(pict.img);
            pict.setIcon(nextpict.img);
            if(song+1==Content.files[0].length) nextpict.setIcon(new ImageIcon(Content.files[1][0]));
            else nextpict.setIcon(new ImageIcon(Content.files[1][song+1]));
            
            
            //update title
            title.setText(Content.files[2][song]);
            
            // change timer speed
            timer.setDelay(song*10+10);
            
            // bring the focus back on the frame
            requestFocus(); 
        }
    }
    /**class prevListener implements ActionListener
     * the ActionListener of prev Button
     */ 
    class prevListener implements ActionListener{
         /**
          * Override actionPerformed method
          * update the song, set state to Game ans close the window 
          * @param arg0 ActionEvent
          */
            public void actionPerformed (ActionEvent arg0){
            
                //change song value
                if(song-1<0)song=Content.files[0].length-1;
                else song--;
                
                //stop current song
                clip.close(); 
                
                //start new song 
                try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Content.files[0][song]).getAbsoluteFile());
                        clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                    } catch(Exception ex) {
                        System.out.println("Error with playing sound.");
                        ex.printStackTrace();
                    }
                
                //update picts 
                nextpict.setIcon(pict.img);
                pict.setIcon(prevpict.img);
                if(song==0) prevpict.setIcon(new ImageIcon(Content.files[1][Content.files[0].length-1]));
                else prevpict.setIcon(new ImageIcon(Content.files[1][song-1]));
                
                //update title
                title.setText(Content.files[2][song]);
                
                //change the timer speed
                timer.setDelay(song*10+10);
                
                //bring the focus on the frame
                requestFocus();
            }
    }
    /**class closeListener implements ActionListener
     * the ActionListener of close Button
     */    
    class closeListener implements ActionListener{
         /**
          * Override actionPerformed method
          * System.exit(0) 
          * @param arg0 ActionEvent
          */
        public void actionPerformed (ActionEvent arg0){
            System.exit(0);
        }
    }
    /**class minimizeListener implements ActionListener
     * the ActionListener of minimize Button
     */
    class minimizeListener implements ActionListener{

         /**
          * Override actionPerformed method
          * minimize the window 
          * @param arg0 ActionEvent
          */
        public void actionPerformed (ActionEvent arg0){
            setState(JFrame.ICONIFIED);
        }
    }
    /**class pictListener implements ActionListener
     * the ActionListener of pict Button
     */
    class pictListener implements ActionListener{

         /**
          * Override actionPerformed method
          * set state to Game and close this window
          * @param arg0 ActionEvent
          */
        public void actionPerformed (ActionEvent arg0){
            //change the state to Game
            state=Karaok.State.Game;
            
            // close this Frame
            dispose(); 
            
            // stop song
            clip.close(); 
            
        }
    }
    /**class GameKeyAdapter extends KeyAdapter
     * this class will help to manage interaction with keyboard
     */
    private class GameKeyAdapter extends KeyAdapter{


        /**
         * the action displayed each time a key is pressed
         * keys: escape,P.
         * @param e KeyEvent
         */
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code){
            case KeyEvent.VK_ESCAPE: 
                System.exit(0);
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


    
    
    private class TimerAction implements ActionListener {

        /**
         * the action performed each timer step
         * @param e ActionEvent
         */
        public void actionPerformed(ActionEvent e) {
        game_display();
        }
    
    
    }
    
    /** 
    * Timer managment action method
    * LightPads randomly change colors each timer action 
    * 2 LightPad colored, 1 LightPad back to mainColor
    * @return void 
    */
    public void game_display(){
        // color 2 LightPad
        int pos = (int)(Math.random()*(lightPad.length));
        lightPad[pos].update();
        pos = (int)(Math.random()*(lightPad.length));
        lightPad[pos].update();
        
        // 1 Light Pad to grey
        pos = (int)(Math.random()*(lightPad.length));
        lightPad[pos].downdate();
        
        repaint();
            
    }
}