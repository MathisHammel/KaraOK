
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

import javax.swing.Timer;

import javax.sound.sampled.Clip;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;




public class scoreWindow extends JFrame {
    //score
    double score;
    ScoreGraph scoreGraph;
    
    //song
    int song;
    Clip clip;
    
    //timer
    Timer timer;
    long time;
    
    //components 
    Button close, minimize;
    Button next, prev, replay, backToMenu, exitGame;
    JLabel title;
    
    //background 
    LightPad[] lightPad;
    
    // State
    Karaok.State state;
    
    public scoreWindow( int song, double score) {
        
        this.song=song;
        
        state=Karaok.State.Win;
        
        //create frame
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
        
        //create background
        this.setBackground(new Color(127,140,141,254)); // main color + alpha max
        lightPad = new LightPad[78];
        int b =0;
            for(int i=0;i<14;i++){
                for(int j=0;j<10;j++){
                    if( ((0<j && j<9) && (0<i && i<4)) /* graph*/ ||  (j==1  &&(6<i && i<11)) /*title*/ || ((j==2 || j==3) &&(4<i && i<13 ))/*score*/ );
                    else{if((j==5 || j==6) &&((i==5 || i==6 || i==8 || i==9 || i==11 || i==12)) || (j==8 &&((4<i && i<8)||(9<i && i<13))) ) ; //buttons
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
        
        //the next button to play the next song 
        next=new Button(Content.next);
        next.setBounds(552,252,96,96);
        next.setBorderPainted(false);
        next.addActionListener(new nextListener()); 
        next.addMouseListener(next);
        getContentPane().add(next);
        
        //the replay button to replay the song
        replay=new Button(Content.replay);
        replay.setBounds(402,252,96,96);
        replay.setBorderPainted(false);
        replay.addActionListener(new replayListener()); 
        replay.addMouseListener(replay);
        getContentPane().add(replay);
        
        //the prev button to play previous song 
        prev=new Button(Content.prev);
        prev.setBounds(252,252,96,96);
        prev.setBorderPainted(false);
        prev.addActionListener(new prevListener()); 
        prev.addMouseListener(prev);
        getContentPane().add(prev);
        
        //the title Label 
        title = new JLabel(Content.files[2][song],SwingConstants.CENTER);
        title.setBackground(Content.colors[2]);
        title.setOpaque(true);
        title.setForeground(Color.white);
        title.setFont(Content.font.deriveFont(16f));
        title.setBounds(352,52,196,46);
        getContentPane().add(title);
        
        // create backToMenu button to go back to the game launcher
        backToMenu=new Button();
        backToMenu.setBounds(252,402,146,46);
        backToMenu.setBorderPainted(false);
        backToMenu.setBackground(Content.colors[2]);
        backToMenu.setForeground(Content.mainColor);
        backToMenu.setFont(Content.font.deriveFont(20f));
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
        exitGame.setFont(Content.font.deriveFont(20f));
        exitGame.setText("Exit" );
        exitGame.addActionListener(new exitListener()); 
        exitGame.addMouseListener(exitGame);
        getContentPane().add(exitGame);
        
        this.score= score; 
        
        // the ScoreGraph component
        scoreGraph=new ScoreGraph(this);
        this.getContentPane().add(scoreGraph);
        scoreGraph.repaint();
        
        //the KeyListener
        this.addKeyListener(new GameKeyAdapter());
        
        //request the focus back on the frame
        this.setFocusable(true);
        this.requestFocus();
        
        //start song
        try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("songs\\MJ.wav").getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch(Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        
        //timer
        time=0;
        timer = new javax.swing.Timer(30,new TimerAction());
        timer.start();
    
        setVisible(true);
        
    }
    /**class closeListener implements ActionListener
     * the ActionListener of close Button
     */
    class closeListener implements ActionListener{
        /**
         * Override actionPerformed method
         * System.exit.(0)
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
        time++;
        int pos = (int)(Math.random()*(lightPad.length-1));
        lightPad[pos].update();
        pos = (int)(Math.random()*(lightPad.length-1));
        lightPad[pos].update();
        pos = (int)(Math.random()*(lightPad.length-1));
        lightPad[pos].downdate();
        scoreGraph.update(time);
        repaint();
        
            
    }
    /**class exitListener implements ActionListener
     * the ActionListener of exit Button
     */
    class exitListener implements ActionListener{
        /**
         * Override actionPerformed method
         * create a PopUp element
         * @param arg0 ActionEvent
         */
        public void actionPerformed (ActionEvent arg0){
            // create an OptionPane to be sure that player wants to leave the game
            PopUp jop = new PopUp(scoreWindow.this);            
                  
        }
    }
    /**class backListener implements ActionListener
     * the ActionListener of backToMenu Button
     */
    class backListener implements ActionListener{ 
        /**
         * Override actionPerformed method
         * change the state to Menu and stop the song 
         * @param arg0 ActionEvent
         */
        public void actionPerformed (ActionEvent arg0){
            state=Karaok.State.Menu; // change scoreWindow state to Menu
            clip.close();
        }
    }
    /**class replayListener implements ActionListener
     * the ActionListener of replay Button
     */
    class replayListener implements ActionListener{
        /**
         * Override actionPerformed method
         * change state and close this window
         * @param arg0 ActionEvent
         */
        public void actionPerformed (ActionEvent arg0){
          
            state=Karaok.State.Game; //change the state to Game
            dispose(); // close this Frame
            clip.close(); // stop song
            
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
            if(song-1<0)song=Content.files[0].length-1;
            else song--;
            
            state=Karaok.State.Game; //change the state to Game
            dispose(); // close this Frame
            clip.close(); // stop song
           
           
        }
    }
    /**class nextListener implements ActionListener
     * the ActionListener of next Button
     */
    class nextListener implements ActionListener{
        /**
         * Override actionPerformed method
         * update the song, set state to Game ans close the window 
         * @param arg0 ActionEvent
         */
        public void actionPerformed (ActionEvent arg0){
            if(song+1>Content.files[0].length-1)song=0;
            else song++;
            
            state=Karaok.State.Game; //change the state to Game
            dispose(); // close this Frame
            clip.close(); // stop song
           
           
        }
    }
}
   