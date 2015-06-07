import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class SongWillStart extends JLabel{
    Timer timer;
    long time=0;
    GameWindow game;
    public SongWillStart(GameWindow agame) {
        super("",SwingConstants.CENTER);
        game=agame;
        timer = new Timer(10,new TimerAction());
        timer.start();
        int timeleft=3;
        this.setBounds((int)game.Ecran.getWidth()*3/8,(int)(game.Ecran.getHeight()*0.25),(int)game.Ecran.getWidth()/4, (int)(game.Ecran.getHeight()*0.30));
        this.setBackground(Content.colors[2]);
        this.setOpaque(true);
        this.setForeground(Color.white);
        this.setFont(new Font("LAIKA", Font.BOLD, (int)game.Ecran.getHeight()/17));
        this.setText("<html><center>GET READY !<br> The song will start in: <br> " + timeleft );
        game.getContentPane().add(this);
        
        while(timeleft>0){
            timeleft= 3- (int)time/100;
            this.setText("<html><center>GET READY !<br> The song will start in: <br> " + timeleft );
        }
        game.getContentPane().remove(this);
        System.out.println("[DEBUD] songwillstarted removed");
        game.clip.start();
        game.timer.start();
    }
    
    
    private class TimerAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            game_display();
            time++;
        }
    }


    
    public void game_display(){
       
        game.pointeur.move(time,Karaok.freqmaster.mainFreq); 
        game.repaint();


    }
}

