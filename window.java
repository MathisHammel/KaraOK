




import java.awt.Cursor;

import java.awt.GridLayout;
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

import javax.swing.JPanel;

public class window extends JFrame {
    
    static String[][] files = {{"C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\Fun.wav","C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\queen.wav","C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\beyonce.wav" },{"C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\fun-we-are-young.png","C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\WATC.png", "C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\WATW.png"}};
    int song;
    Clip clip;
    Button next, prev,play , close, minimize;
    Button pict, nextpict, prevpict;
    Karaok.State state;
    long time;
    Timer timer;
    LightPad[] lightPad;
    
    public window() {
        setTitle("Kara-OK");
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        this.setLocationRelativeTo(getRootPane());
        this.setIconImage(new ImageIcon("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\microphone_converted.png").getImage());
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\mic_converted.png");
        Cursor cursor = tk.createCustomCursor(img, new Point(0, 0), "micro");
        this.setCursor(cursor);
        
        
        
        state=Karaok.State.Menu;
        //mise en place du backgroud !
        lightPad=new LightPad[140];
        int b=0;
        this.setLayout(null);
        for(int i=0;i<14;i++){
            for(int j=0;j<10;j++){
                lightPad[b]=new LightPad(50*i,50*j);  
                
                this.getContentPane().add(lightPad[b]);
                b++;
            }
        }
        
        
        // mise en place des �l�ments de la fenetre
        close=new Button("C:\\Users\\Administrateur\\Documents\\GitHub\\Projetinfo\\KaraOK\\img\\Close");
        close.setBounds(670,0,30,26);
        close.setBorderPainted(false);
        close.addActionListener(new closeListener());
        close.addMouseListener(close);
        getContentPane().add(close);
        
        minimize=new Button("C:\\Users\\Administrateur\\Documents\\GitHub\\Projetinfo\\KaraOK\\img\\Minimize");
        minimize.setBounds(638,0,32,26);
        minimize.setBorderPainted(false);
        minimize.addActionListener(new minimizeListener());
        minimize.addMouseListener(minimize);
        getContentPane().add(minimize);
        
        prev=new Button(new ImageIcon("C:\\Users\\Administrateur\\Documents\\GitHub\\Projetinfo\\KaraOK\\img\\NextPrev\\Next01Off.png"));
        prev.setBounds(100,200,100,100);
        prev.setBorderPainted(false);
        prev.addActionListener(new prevListener());
        this.getContentPane().add(prev);
        
        prevpict=new Button(new ImageIcon(files[1][files[0].length-1]));
        prevpict.setBounds(200,200,100,100);
        prevpict.setBorderPainted(false);
        this.getContentPane().add(prevpict);
        
        pict=new Button(new ImageIcon(files[1][0]));
        pict.setBounds(300,200,100,100);
        pict.setBorderPainted(false);
        pict.addActionListener(new pictListener());
        this.getContentPane().add(pict);
        
        nextpict=new Button(new ImageIcon(files[1][1]));
        nextpict.setBounds(400,200,100,100);
        nextpict.setBorderPainted(false);
        this.getContentPane().add(nextpict);
        
        next=new Button(new ImageIcon("C:\\Users\\Administrateur\\Documents\\GitHub\\Projetinfo\\KaraOK\\img\\NextPrev\\Next05Off.png"));
        next.setBounds(500,200,100,100);
        next.setBorderPainted(false);
        next.addActionListener(new nextListener());
        this.getContentPane().add(next);
        
        play=new Button(new ImageIcon("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\next.png"));
        play.setBounds(300,300,100,100);
       
        play.setBorderPainted(false);
        play.addActionListener(new pictListener());
        this.getContentPane().add(play);
        //
        //
        //
        
        
        song=0;  
        //lancement de la musique
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
        timer = new Timer(10,new TimerAction());
        timer.start();
        this.setFocusable(true);//permet � la fenetre d'avoir le focus
        this.requestFocus();
        this.addKeyListener(new GameKeyAdapter());
        setVisible(true);
    }
    
   /** public static void main(String[] args) {
    window Monjeu = new window();
    }*/
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
            
            pict.setIcon(new ImageIcon(files[1][song]));
            if(song == files[1].length-1)nextpict.setIcon(new ImageIcon(files[1][0]));
            else nextpict.setIcon(new ImageIcon(files[1][song+1]));
            if(song == 0)prevpict.setIcon(new ImageIcon(files[1][files[0].length-1]));
            else prevpict.setIcon(new ImageIcon(files[1][song-1]));
            setFocusable(true);//permet � la fenetre d'avoir le focus
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
                pict.setIcon(new ImageIcon(files[1][song]));
                if(song == files[1].length-1)prevpict.setIcon(new ImageIcon(files[1][0]));
                else prevpict.setIcon(new ImageIcon(files[1][song+1]));
                if(song == 0)nextpict.setIcon(new ImageIcon(files[1][files[0].length-1]));
                else nextpict.setIcon(new ImageIcon(files[1][song-1]));
                setFocusable(true);//permet � la fenetre d'avoir le focus
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
            setFocusable(true);//permet � la fenetre d'avoir le focus
            requestFocus();
        }
    }
    class pictListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
          
            System.out.println("bouton appuy�");
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
        int pos = (int)(Math.random()*140);
        lightPad[pos].update(time);
        pos = (int)(Math.random()*140);
        lightPad[pos].downdate(time);
        pos = (int)(Math.random()*140);
        lightPad[pos].update(time);
        close.repaint();
        minimize.repaint();
        prev.repaint();
        prevpict.repaint();
        pict.repaint();
        nextpict.repaint();
        next.repaint();
        play.repaint();
            
    }
}