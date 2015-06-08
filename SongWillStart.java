import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class SongWillStart extends JLabel{
    Timer timer;
    long time=0;
    GameWindow game;

    /**
     * the SongWillStart constructor to create a PopUp message before the song start
     * @param agame GameWindow
     */
    public SongWillStart(GameWindow agame) {
        super("",SwingConstants.CENTER);
        
        game=agame;
        
        // create timer
        timer = new Timer(10,new TimerAction());
        timer.start();
        
        int timeLeft=3; // time left before the game start
        
        // create the Label 
        this.setBounds((int)game.Ecran.getWidth()*3/8,(int)(game.Ecran.getHeight()*0.25),(int)game.Ecran.getWidth()/4, (int)(game.Ecran.getHeight()*0.30));
        this.setBackground(Content.colors[2]); // yellow
        this.setOpaque(true);
        this.setForeground(Color.white);
        this.setFont(Content.font.deriveFont((float)game.Ecran.getHeight()/17));
        this.setText("<html><center>GET READY !<br> The song will start in: <br> .");
        
        // create a new Label to update the time
        // this was necessary to DEBUG this class
        JLabel timeRemaining = new JLabel("3");
        timeRemaining.setBounds((int)(game.Ecran.getWidth()*0.081),(int)(game.Ecran.getHeight()*0.225),(int)(game.Ecran.getWidth()*0.075), (int)(game.Ecran.getHeight()*0.075));
        timeRemaining.setBackground(Content.colors[2]);
        timeRemaining.setOpaque(true);
        timeRemaining.setForeground(Color.white);
        timeRemaining.setFont(Content.font.deriveFont((float)game.Ecran.getHeight()/15));
        timeRemaining.setText( "   "+timeLeft );
        this.add(timeRemaining);
        game.getContentPane().add(this);
        
        // wait until timeLeft=0
        while(timeLeft>0){
            timeLeft= 3- (int)time/100;
            timeRemaining.setText( "   "+timeLeft );
        }
        //remove SongWillStart
        game.getContentPane().remove(this);
        
        //restart song and timer
        game.clip.start();
        game.timer.start();
    }
    
    
    private class TimerAction implements ActionListener {

        /**
         * Actione performed by the timer
         * @param e ActionEvent
         */
        public void actionPerformed(ActionEvent e) {
            game_display();
            time++;
        }
    }


    /**
     * Action performed each timer action
     * the pointeur can move
     * @return void
     */
    public void game_display(){
       
        game.pointeur.move(time,Karaok.freqmaster.mainFreq); 
        game.repaint();


    }
}

