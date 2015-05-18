import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;


import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class window extends JFrame {
    
    static String[][] files = {{"C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\Fun.wav","C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\queen.wav","C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\beyonce.wav" },{"C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\fun-we-are-young.png","C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\WATC.png", "C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\WATW.png"}};
    int song;
    Clip clip;
    Button next, prev, close, mini, minimize;
    Button pict, nextpict, prevpict;
    
    public window() {
        setTitle("Kara-OK.Jackson");
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 458);
        this.setLocationRelativeTo(getRootPane());
        this.setIconImage(new ImageIcon("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\microphone_converted.png").getImage());
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\mic_converted.png");
        Cursor cursor = tk.createCustomCursor(img, new Point(0, 0), "micro");
        this.setCursor(cursor);
        
        this.setContentPane(new JLabel(new ImageIcon("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\image2.png")));
        song=0;
        //création des outils fenetres 
        close=new Button(new ImageIcon("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\close.png"));
        close.setBounds(670,0,30,20);
        close.setBorderPainted(false);
        close.addActionListener(new closeListener());
        this.getContentPane().add(close);
        
        minimize=new Button(new ImageIcon("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\reduire.png"));
        minimize.setBounds(640,0,30,20);
        minimize.setBorderPainted(false);
        minimize.addActionListener(new minimizeListener());
        this.getContentPane().add(minimize);
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
        
        //mise en place des éléments graphiques 
        next=new Button(new ImageIcon("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\next.png"));
        next.setBounds(600,265,50,50);
        next.setOpaque(false);
        next.setContentAreaFilled(false);
        next.setBorderPainted(false);
        next.addActionListener(new nextListener());
        this.getContentPane().add(next);
        
        prev=new Button(new ImageIcon("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\prev.png"));
        prev.setBounds(50,265,50,50);
        prev.setOpaque(false);
        prev.setContentAreaFilled(false);
        prev.setBorderPainted(false);
        prev.addActionListener(new prevListener());
        this.getContentPane().add(prev);
        
        pict=new Button(new ImageIcon(files[1][0]));
        pict.setBounds(260,200,180,180);
        pict.setOpaque(false);
        pict.setBorderPainted(false);
        this.getContentPane().add(pict);
        
        nextpict=new Button(new ImageIcon(files[1][1]));
        nextpict.setBounds(450,245,90,90);
        nextpict.setOpaque(false);
        nextpict.setBorderPainted(false);
        this.getContentPane().add(nextpict);
        
        prevpict=new Button(new ImageIcon(files[1][files[0].length-1]));
        prevpict.setBounds(160,245,90,90);
        prevpict.setOpaque(false);
        prevpict.setBorderPainted(false);
        this.getContentPane().add(prevpict);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
    window Monjeu = new window();
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
            
            pict.setIcon(new ImageIcon(files[1][song]));
            if(song == files[1].length-1)nextpict.setIcon(new ImageIcon(files[1][0]));
            else nextpict.setIcon(new ImageIcon(files[1][song+1]));
            if(song == 0)prevpict.setIcon(new ImageIcon(files[1][files[0].length-1]));
            else prevpict.setIcon(new ImageIcon(files[1][song-1]));
            
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
}