

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import javax.swing.Timer;

import javax.sound.sampled.Clip;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;




public class scoreWindow extends JFrame {
    int score;
    int song;
    Clip clip;
    Timer timer;
    Button close, minimize;
    Button next, prev, replay, backToMenu, exitGame;
    JLabel title;
    BufferedImage ArrierePlan;
    Graphics buffer;
    Rectangle Ecran;
    long time;
    LightPad[] lightPad;
    Karaok.State state;
    JLabel dancer;
    
    public scoreWindow( int song) {
        this.song=song;
        state=Karaok.State.Win;
        setTitle("Kara-OK");
        setUndecorated(true); //no borders 
        setSize(700, 500);
        this.setLocationRelativeTo(getRootPane()); // place the frame on the center of the screen
        this.setIconImage(new ImageIcon(Content.icon).getImage()); // icon off the game 
        this.setLayout(null);
        
        
        // set the mic curseur 
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage(Content.cursor);
        Cursor cursor = tk.createCustomCursor(img, new Point(0, 0), "micro");
        this.setCursor(cursor);
        
        time=0;
        timer = new javax.swing.Timer(30,new TimerAction());
        timer.start();
        this.setBackground(new Color(127,140,141,254)); // main color + alpha max
        lightPad = new LightPad[78];
        int b =0;
            for(int i=0;i<14;i++){
                for(int j=0;j<10;j++){
                    if( ((0<j && j<9) && (0<i && i<4)) /* graph*/ ||  (j==1  &&(6<i && i<11)) /*title*/ || ((j==2 || j==3) &&(4<i && i<13 ))/*score*/ );
                    else{if((j==5 || j==6) &&((i==5 || i==6 || i==8 || i==9 || i==11 || i==12)) || (j==8 &&((4<i && i<8)||(9<i && i<13))) ) ; //button
                         else{
                   
                        lightPad[b]=new LightPad(50*i,50*j); 
                        this.getContentPane().add(lightPad[b]);
                        b++;
                         }
                    }
                    
                }
            }
            
        lightPad[68].setBounds(650,23,50,25); // special lightPad under minimize and close button
        
        //set button 
        
        dancer = new JLabel(new ImageIcon("C:\\Users\\Administrateur\\Documents\\spouderman.gif"));
        dancer.setBounds(75, 325, 100, 100);
        getContentPane().add(dancer);
        
        JLabel dancer2=new JLabel(new ImageIcon("C:\\Users\\Administrateur\\Documents\\mouchaeljack.gif"));
        dancer2.setBounds(75, 225, 100, 100);
        getContentPane().add(dancer2);
        
        
        // the close button to close the game
        close=new Button(Content.close);
        close.setBounds(675,0,25,23);
        close.setBorderPainted(false);
        close.addActionListener(new closeListener());
        close.addMouseListener(close);
        getContentPane().add(close);
        
        // the minimize button to minimize game launcher
        minimize=new Button(Content.minimize);
        minimize.setBounds(650,0,25,23);
        minimize.setBorderPainted(false);
        minimize.addActionListener(new minimizeListener());
        minimize.addMouseListener(minimize);
        getContentPane().add(minimize);
        
        next=new Button(Content.next);
        next.setBounds(552,252,96,96);
        next.setBorderPainted(false);
        next.addActionListener(new nextListener()); 
        next.addMouseListener(next);
        getContentPane().add(next);
        
        replay=new Button(Content.replay);
        replay.setBounds(402,252,96,96);
        replay.setBorderPainted(false);
        replay.addActionListener(new replayListener()); 
        replay.addMouseListener(replay);
        getContentPane().add(replay);
        
        prev=new Button(Content.prev);
        prev.setBounds(252,252,96,96);
        prev.setBorderPainted(false);
        prev.addActionListener(new prevListener()); 
        prev.addMouseListener(prev);
        getContentPane().add(prev);
        
        
        title = new JLabel(Content.files[2][song],SwingConstants.CENTER);
        title.setBackground(Content.colors[2]);
        title.setOpaque(true);
        title.setForeground(Color.white);
        title.setFont(new Font("LAIKA", Font.PLAIN, 16));
        title.setBounds(352,52,196,46);
        getContentPane().add(title);
        
        // create backToMenu button to go back to the game launcher
        backToMenu=new Button();
        backToMenu.setBounds(252,402,146,46);
        backToMenu.setBorderPainted(false);
        backToMenu.setBackground(Content.colors[2]);
        backToMenu.setForeground(Content.mainColor);
        backToMenu.setFont(new Font("LAIKA", Font.PLAIN, 20));
        backToMenu.setText("Menu" );
        backToMenu.addActionListener(new backListener()); 
        backToMenu.addMouseListener(backToMenu);
        getContentPane().add(backToMenu);
        
        // create exitGame button to close the game
        exitGame=new Button();
        exitGame.setBounds(502,402,146,46);
        exitGame.setBorderPainted(false);
        exitGame.setBackground(Content.colors[2]);
        exitGame.setForeground(Content.mainColor);
        exitGame.setFont(new Font("LAIKA", Font.BOLD, 20));
        exitGame.setText("Exit" );
        exitGame.addActionListener(new exitListener()); 
        exitGame.addMouseListener(exitGame);
        getContentPane().add(exitGame);
        
        
        
        JLabel test = new JLabel();
        test.setBackground(Color.pink);
        test.setOpaque(true);
        test.setBounds(0, 0, 200, 500);
        getContentPane().add(test);
        
        JLabel test1 = new JLabel();
        test1.setBackground(Content.colors[13]);
        test1.setOpaque(true);
        test1.setBounds(252,102,396,96);
        getContentPane().add(test1);
    
        this.addKeyListener(new GameKeyAdapter());
        this.setFocusable(true);
        this.requestFocus();
        
        try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("songs\\MJ.wav").getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch(Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
    
        setVisible(true);
        
    }
    public static void main(String[]args){
        scoreWindow bite = new scoreWindow(1);
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
        int pos = (int)(Math.random()*(lightPad.length-1));
        lightPad[pos].update();
        pos = (int)(Math.random()*(lightPad.length-1));
        lightPad[pos].update();
        pos = (int)(Math.random()*(lightPad.length-1));
        lightPad[pos].downdate();
        repaint();
            
    }
    
    class exitListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            // create an OptionPane to be sure that player wants ton leave the game
            PopUp jop = new PopUp(scoreWindow.this);            
                  
        }
    }
    class backListener implements ActionListener{ 
        
        public void actionPerformed (ActionEvent arg0){
            state=Karaok.State.Menu; // change gamewindow state to Menu
            clip.close();
        }
    }
    class replayListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
          
            state=Karaok.State.Game; //change the state to Game
            dispose(); // close this Frame
            clip.close(); // stop song
            
        }
    }
    class prevListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            if(song-1<0)song=Content.files[0].length-1;
            else song--;
            
            state=Karaok.State.Game; //change the state to Game
            dispose(); // close this Frame
            clip.close(); // stop song
           
           
        }
    }
    class nextListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            if(song+1>Content.files[0].length-1)song=0;
            else song++;
            
            state=Karaok.State.Game; //change the state to Game
            dispose(); // close this Frame
            clip.close(); // stop song
           
           
        }
    }
}
   