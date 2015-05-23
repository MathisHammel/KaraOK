
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class Pause extends JPanel{
    public GameWindow game;
    public Karaok.State state;
    public Button resume;
    public Button backToMenu;
    public Button exitGame;
    public JPanel pause;
    

    
    public Pause(GameWindow gamewindow) {
        super();
        pause=this;
        game=gamewindow;
        this.setBackground(new Color(0,0,0,0));
        this.setLayout(null);
        
        resume=new Button(Content.play);
        resume.setBounds(675,0,25,23);
        resume.setBorderPainted(false);
        resume.addActionListener(new resumeListener());
        resume.addMouseListener(resume);
        game.add(resume);
        
        exitGame=new Button(Content.close);
        exitGame.setBounds(675,0,25,23);
        exitGame.setBorderPainted(false);
        exitGame.addActionListener(new exitListener());
        exitGame.addMouseListener(exitGame);
        game.add(exitGame);
        
        backToMenu=new Button(Content.close);
        backToMenu.setBounds(675,0,25,23);
        backToMenu.setBorderPainted(false);
        backToMenu.addActionListener(new backListener());
        backToMenu.addMouseListener(exitGame);
        game.add(exitGame);
        
        
    }
    
    class exitListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            System.exit(0);
        }
    }
    
    class resumeListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            state=Karaok.State.Game;
            game.getContentPane().remove(pause);
            game.clip.start();
            game.timer.restart();
        }
    }
    class backListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            state=Karaok.State.Menu;
            game.dispose();
        }
    }
}
