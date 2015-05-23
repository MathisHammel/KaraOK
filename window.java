

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
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class window extends JFrame {
    
    static String[][] files = {{"songs\\Fun.wav","songs\\queen.wav","songs\\beyonce.wav" }
                               ,{"img\\fun-we-are-young.png","img\\WATC.png", "img\\WATW.png"}
                               ,{"<html><center>FUN<br> we are young","<html><center>QUEEN<br> we are the champions", "<html><center>USA FOR AFRICA<br> we are the world"}};
    int song;
    Clip clip;
    Button next, prev,play , close, minimize;
    Button pict, nextpict, prevpict;
    Karaok.State state;
    long time;
    Timer timer;
    LightPad[] lightPad;
    JLabel title, logo;
    
    
    
    public window() {
        setTitle("Kara-OK");
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        this.setLocationRelativeTo(getRootPane());
        this.setIconImage(new ImageIcon("img\\microphone_converted.png").getImage());
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("img\\mic_converted.png");
        Cursor cursor = tk.createCustomCursor(img, new Point(0, 0), "micro");
        this.setCursor(cursor);
        //background black
        this.setBackground(new Color(127,140,141,254));
        this.setOpacity(1);
        
        
        
        state=Karaok.State.Menu;
        //mise en place du backgroud !
        lightPad=new LightPad[108];
        int b=0;
        this.setLayout(null);
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
        
        lightPad[98].setBounds(650,23,50,25);
        // mise en place des éléments de la fenetre

        close=new Button("img\\Close");
        close.setBounds(675,0,25,23);
        close.setBorderPainted(false);
        close.addActionListener(new closeListener());
        close.addMouseListener(close);
        this.getContentPane().add(close);
        
        minimize=new Button("img\\Minimize");
        minimize.setBounds(650,0,25,23);
        minimize.setBorderPainted(false);
        minimize.addActionListener(new minimizeListener());
        minimize.addMouseListener(minimize);
        this.getContentPane().add(minimize);
        
        prev=new Button("img\\NextPrev\\Prev04");
        prev.setBounds(102,202,96,96);
        prev.setBorderPainted(false);
        prev.addActionListener(new prevListener());
        prev.addMouseListener(prev);
        this.getContentPane().add(prev);
        
        prevpict=new Button(new ImageIcon(files[1][files[0].length-1]));
        prevpict.setBounds(202,202,96,96);
        prevpict.setBorderPainted(false);
        this.getContentPane().add(prevpict);
        
        pict=new Button(new ImageIcon(files[1][0]));
        pict.setBounds(302,202,96,96);
        pict.setBorderPainted(false);
        pict.addActionListener(new pictListener());
        this.getContentPane().add(pict);
        
        nextpict=new Button(new ImageIcon(files[1][1]));
        nextpict.setBounds(402,202,96,96);
        nextpict.setBorderPainted(false);
        this.getContentPane().add(nextpict);
        
        next=new Button("img\\NextPrev\\Next04");
        next.setBounds(502,202,96,96);
        next.setBorderPainted(false);
        next.addActionListener(new nextListener());
        next.addMouseListener(next);
        this.getContentPane().add(next);
        
        play=new Button("img\\Play");
        play.setBounds(302,302,96,96);
        play.setBorderPainted(false);
        play.addActionListener(new pictListener());
        play.addMouseListener(play);
        this.getContentPane().add(play);
        
        title = new JLabel(files[2][0],SwingConstants.CENTER);
        title.setBackground(new Color(52,152,219));
        title.setOpaque(true);
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.BOLD, 17));
        title.setBounds(252,152,196,46);
        this.getContentPane().add(title);
        
        logo = new JLabel("KARA-OK",SwingConstants.CENTER);
        logo.setBackground(new Color(142,68,173));
        logo.setOpaque(true);
        logo.setForeground(Color.white);
        logo.setFont(new Font("SansSerif", Font.BOLD, 40));
        logo.setBounds(252,52,196,46);
        this.getContentPane().add(logo);
        
       
        //lancement de la musique   
        
        song=0;  
        try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(files[0][song]).getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch(Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
       
        time=0;
        timer = new Timer(20,new TimerAction());
        timer.start();
        
        this.setFocusable(true);//permet à la fenetre d'avoir le focus
        this.requestFocus();
        this.addKeyListener(new GameKeyAdapter());
        
        setVisible(true);
    }
    
     class nextListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            if(song+1>files[0].length-1)song=0;
            else song++;
            clip.stop();
            try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(files[0][song]).getAbsoluteFile());
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch(Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
            
            prevpict.setIcon(pict.img);
            pict.setIcon(nextpict.img);
            if(song+1==files[0].length) nextpict.setIcon(new ImageIcon(files[1][0]));
            else nextpict.setIcon(new ImageIcon(files[1][song+1]));
            title.setText(files[2][song]);
            timer.setDelay(song*10+10);
            setFocusable(true);//permet à la fenetre d'avoir le focus
            requestFocus();
        }
    }
        
        class prevListener implements ActionListener{
            
            public void actionPerformed (ActionEvent arg0){
                if(song-1<0)song=files[0].length-1;
                else song--;
                clip.stop();
                try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(files[0][song]).getAbsoluteFile());
                        clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                    } catch(Exception ex) {
                        System.out.println("Error with playing sound.");
                        ex.printStackTrace();
                    }
                nextpict.setIcon(pict.img);
                pict.setIcon(prevpict.img);
                if(song==0) prevpict.setIcon(new ImageIcon(files[1][files[0].length-1]));
                else prevpict.setIcon(new ImageIcon(files[1][song-1]));
                title.setText(files[2][song]);
                timer.setDelay(song*10+10);
                setFocusable(true);//permet à la fenetre d'avoir le focus
                requestFocus();
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
            setFocusable(true);//permet à la fenetre d'avoir le focus
            requestFocus();
        }
    }
    class pictListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
          
            System.out.println("bouton appuyé");
            state=Karaok.State.Game;
            dispose();
            clip.stop();
            
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
        time++;
        }
    
    
    }
    
    public void game_display(){
        int pos = (int)(Math.random()*(lightPad.length-1));
        lightPad[pos].update(time);
        pos = (int)(Math.random()*(lightPad.length-1));
        lightPad[pos].downdate(time);
        pos = (int)(Math.random()*(lightPad.length-1));
        lightPad[pos].update(time);
        close.repaint();
        minimize.repaint();
        prev.repaint();
        prevpict.repaint();
        pict.repaint();
        nextpict.repaint();
        next.repaint();
        play.repaint();
        title.repaint();
        logo.repaint();
            
    }
}