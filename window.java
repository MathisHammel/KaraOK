

import java.awt.Color;
import java.awt.Cursor;

import java.awt.Font;
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


public class window extends JFrame {
    
    
    int song; // to know which song is currently displayed
    Clip clip;
    Button  close, minimize; // button needed for the window interaction
    Button next, prev,play ,pict, nextpict, prevpict; // button used to select the song and start the game 
    Karaok.State state; // the state of the game  
    Timer timer; // swing Timer 
    LightPad[] lightPad; // used for the background animation 
    JLabel title, logo; // logo of the game, and title of the song 
    
    
    
    public window() {
        
        state=Karaok.State.Menu;
        
        //create the game Frame 
        setTitle("Kara-OK");
        setUndecorated(true); //no borders 
        setSize(700, 500);
        this.setLocationRelativeTo(getRootPane()); // place the frame on the center of the screen
        this.setIconImage(new ImageIcon(Content.icon).getImage()); // icon off the game 
        
        // set the mouse pointer as a mic image
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage(Content.cursor);
        Cursor cursor = tk.createCustomCursor(img, new Point(0, 0), "micro");
        this.setCursor(cursor);
        
        //create the background with lightPad
        this.setBackground(new Color(127,140,141,254)); // main color + alpha max
        lightPad=new LightPad[108];
        int b=0;
        this.setLayout(null);
        //place the lightPad where there is no other elements 
        for(int i=0;i<14;i++){
            for(int j=0;j<10;j++){
                if(((j==4 || j==5) && (1<i && i<12)) || ((j==6 || j==7) &&(i==6 || i==7))){
                    j++;
                }
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
        
        // set interactives button 
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
        title.setFont(new Font("LAIKA", Font.BOLD, 16));
        title.setBounds(252,152,196,46);
        this.getContentPane().add(title);
        
        //the logo of the song 
        logo = new JLabel("KARA-OK",SwingConstants.CENTER);
        logo.setBackground(Content.colors[13]);
        logo.setOpaque(true);
        logo.setForeground(Color.white);
        logo.setFont(new Font("LAIKA", Font.BOLD, 40));
        logo.setBounds(252,52,196,46);
        this.getContentPane().add(logo);
        
       
        //initialize the timer   
        timer = new Timer(20,new TimerAction());
        timer.start();
        
        this.setFocusable(true);
        this.requestFocus();//give focus to the window for keyboard actions
        this.addKeyListener(new GameKeyAdapter());
        
        //start song 
        song=0;  
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
    
     class nextListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            if(song+1>Content.files[0].length-1)song=0;
            else song++;
            clip.close(); //stop song 
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
            requestFocus(); // bring the focus back to the frame
        }
    }
        
        class prevListener implements ActionListener{
            
            public void actionPerformed (ActionEvent arg0){
                if(song-1<0)song=Content.files[0].length-1;
                else song--;
                clip.close(); //stop current song
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
                requestFocus();//bring the focus on the frame
            }
    }
        
    class closeListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            System.exit(0);
        }
    }
    class minimizeListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            setState(JFrame.ICONIFIED);
        }
    }
    class pictListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
          
            state=Karaok.State.Game; //change the state to Game
            dispose(); // close this Frame
            clip.close(); // stop song
            
        }
    }
    private class GameKeyAdapter extends KeyAdapter{
            
        
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
    
        public void actionPerformed(ActionEvent e) {
        game_display();
        }
    
    
    }
    
    public void game_display(){
        //change the color of 2 lightPad and set 1 to grey
        int pos = (int)(Math.random()*(lightPad.length));
        lightPad[pos].update();
        pos = (int)(Math.random()*(lightPad.length));
        lightPad[pos].update();
        
        pos = (int)(Math.random()*(lightPad.length));
        lightPad[pos].downdate();
        repaint();
            
    }
}