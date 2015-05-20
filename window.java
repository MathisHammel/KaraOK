

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.File;


import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class window extends JFrame {
    
    static String[][] files = {{"C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\Fun.wav","C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\queen.wav","C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\beyonce.wav" },{"C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\fun-we-are-young.png","C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\WATC.png", "C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\WATW.png"}};
    int song;
    Clip clip;
    Button next, prev, close, mini, minimize;
    Button pict, nextpict, prevpict;
    Karaok.State state;
    
    public window() {
        setTitle("Kara-OK.Jackson");
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        this.setLocationRelativeTo(getRootPane());
        this.setIconImage(new ImageIcon("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\microphone_converted.png").getImage());
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\mic_converted.png");
        Cursor cursor = tk.createCustomCursor(img, new Point(0, 0), "micro");
        this.setCursor(cursor);
        
        this.addKeyListener(new WindowKeyAdapter());
        
        state=Karaok.State.Menu;
        this.setLayout(new GridLayout(5,7));
        LightPad[] lightPad = new LightPad[140];
        int a = 0;
        GridLayout grid = new GridLayout(2,2);
        for(int i=0; i<35; i++){
            JPanel panel = new JPanel();
            switch (i){
            case 6: 
                panel.setLayout(new GridLayout(2,1));
               // panel.setBounds(new Rectangle(100,100));
                JPanel papa = new JPanel();
                papa.setBackground(new Color(127,140,141));
                papa.setLayout(null);
                
                
                close=new Button(new ImageIcon("C:\\Users\\Administrateur\\Documents\\GitHub\\Projetinfo\\KaraOK\\img\\Close.png"));
                close.setBounds(70,0,30,26);
                close.setBorderPainted(false);
                close.addActionListener(new closeListener());
                papa.add(close);
                
                minimize=new Button(new ImageIcon("C:\\Users\\Administrateur\\Documents\\GitHub\\Projetinfo\\KaraOK\\img\\Minimize.png"));
                minimize.setBounds(38,0,32,26);
                minimize.setBorderPainted(false);
                minimize.addActionListener(new minimizeListener());
                papa.add(minimize);
            
                panel.add(papa);
                
                papa = new JPanel();
                
                papa.setLayout(new GridLayout(1,2));
                lightPad[a]=new LightPad();
                papa.add(lightPad[a]);
                a++;
                lightPad[a]=new LightPad();
                papa.add(lightPad[a]);
                panel.add(papa);
                this.getContentPane().add(panel);
                break;
            case 15:
                
                /**prev=new Button(new ImageIcon("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\prev.png"));
                prev.setBounds(0,0,50,50);
                prev.setBorderPainted(false);
                prev.addActionListener(new prevListener());
                panel.add(prev);*/
                this.getContentPane().add(panel);
            default:
                panel.setLayout(new GridLayout(2,2));
                for(int j=0; j<4;j++){
                    lightPad[a]=new LightPad();
                    panel.add(lightPad[a]);
                    a++;
                    this.getContentPane().add(panel);
                }
            break;
            
            }
        }
        
        
        //this.setContentPane(new JLabel(new ImageIcon("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\image2.png")));
        
        song=0;
        /**
        //création des outils fenetres 
        close=new Button(new ImageIcon("C:\\Users\\Administrateur\\Documents\\GitHub\\Projetinfo\\KaraOK\\img\\Close.png"));
        close.setBounds(670,0,30,20);
        close.setBorderPainted(false);
        close.addActionListener(new closeListener());
        this.getContentPane().add(close);
        
        minimize=new Button(new ImageIcon("C:\\Users\\Administrateur\\Documents\\GitHub\\Projetinfo\\KaraOK\\img\\Minimize.png"));
        minimize.setBounds(630,0,25,25);
        minimize.setBorderPainted(false);
        minimize.addActionListener(new minimizeListener());
        this.getContentPane().add(minimize);

        */
        
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
        /**
        //mise en place des éléments graphiques 
        next=new Button(new ImageIcon("C:\\JDeveloper\\mywork\\OCR\\ProjetInfo\\classes\\next.png"));
        next.setBounds(600,265,50,50);
        next.setOpaque(false);
        next.setContentAreaFilled(false);
        next.setBorderPainted(false);
        next.addActionListener(new nextListener());
        this.getContentPane().add(next);
        
        
        pict=new Button(new ImageIcon(files[1][0]));
        pict.setBounds(260,200,180,180);
        pict.setOpaque(false);
        pict.setBorderPainted(false);
        pict.addActionListener(new pictListener());
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
        */
        
        
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
    class pictListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
          
            System.out.println("bouton appuyé");
            state=Karaok.State.Game;
            dispose();
            clip.stop();
            
        }
    }
    private class WindowKeyAdapter extends KeyAdapter{
            
        
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
           
            if(code== KeyEvent.VK_ESCAPE){ 
                System.out.println("coucou");
                System.exit(0);
            }
}
    }
}