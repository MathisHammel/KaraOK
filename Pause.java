
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Pause extends JPanel{
    public GameWindow game;
    public Karaok.State state;
    public Button resume;
    public Button backToMenu;
    public Button exitGame;
    public JPanel pause;
    public JLabel logo;
    

    
    public Pause(GameWindow gamewindow) {
        super();
        pause=this;
        state=Karaok.State.Pause;
        game=gamewindow;
        game.clip.stop();
        game.timer.stop();
        this.setBounds(0, 0, (int)game.Ecran.getWidth(),(int) game.Ecran.getHeight());
        this.setBackground(new Color(0,0,0,0));
        this.setLayout(null);
        
        resume=new Button(Content.play);
        resume.setBounds((int)game.Ecran.getWidth()/2,(int)game.Ecran.getHeight()/10,25,23);
        resume.setBorderPainted(false);
        resume.addActionListener(new resumeListener());
        resume.addMouseListener(resume);
        game.add(resume);
        
        exitGame=new Button(Content.close);
        exitGame.setBounds((int)game.Ecran.getWidth()/2,(int)game.Ecran.getHeight()*2/10,25,23);
        exitGame.setBorderPainted(false);
        exitGame.addActionListener(new exitListener());
        exitGame.addMouseListener(exitGame);
        game.add(exitGame);
        
        backToMenu=new Button(Content.close);
        backToMenu.setBounds((int)game.Ecran.getWidth()/2,(int)game.Ecran.getHeight()*3/10,25,23);
        backToMenu.setBorderPainted(false);
        backToMenu.addActionListener(new backListener());
        backToMenu.addMouseListener(backToMenu);
        game.add(backToMenu);
        
        logo = new JLabel("PAUSE",SwingConstants.CENTER);
        logo.setBackground(Content.colors[6]);
        logo.setOpaque(true);
        logo.setForeground(Color.white);
        logo.setFont(new Font("SansSerif", Font.BOLD, 40));
        logo.setBounds((int)(game.Ecran.getWidth()*0.375),(int)game.Ecran.getHeight()*1/10,(int)game.Ecran.getWidth()/4,46);
        game.add(logo);
        
        exitGame.repaint();
        resume.repaint();
        backToMenu.repaint();
        logo.repaint();
        
        
    }
    
    class exitListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            System.exit(0);
        }
    }
    
    class resumeListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            game.getContentPane().remove(pause);
            game.clip.start();
            game.timer.restart();
            game.requestFocus();
            state=Karaok.State.Game;
            
        }
    }
    class backListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            state=Karaok.State.Menu;
            game.dispose();
        }
    }
}
