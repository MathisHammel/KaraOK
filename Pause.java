
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Pause {
    public GameWindow game;
    public Button resume;
    public Button backToMenu;
    public Button exitGame;
    public Button restart;
    public JLabel logo;
    public Button end;


    
    public Pause(GameWindow gamewindow) {
        //super();
        game=gamewindow;
		
        
        // stop timer and song 
        game.clip.stop();
        game.timer.stop();
        
        // create logo Label <======= FIXME
        logo = new JLabel("PAUSE",SwingConstants.CENTER);
        logo.setBackground(Content.colors[6]);
        logo.setOpaque(true);
        logo.setForeground(Color.white);
        logo.setFont(Content.font.deriveFont((float)game.Ecran.getHeight()/17));
        logo.setBounds((int)(game.Ecran.getWidth()*0.375),(int)game.Ecran.getHeight()*1/10,(int)game.Ecran.getWidth()/4,46);
        game.getContentPane().add(logo);
        
        // create resume Button to resume game
        resume=new Button();
        resume.setBounds((int)game.Ecran.getWidth()*5/12,(int)game.Ecran.getHeight()*2/10,(int)game.Ecran.getWidth()/6,(int)game.Ecran.getHeight()/15);
        resume.setBorderPainted(false);
        resume.setBackground(Content.colors[13]);
        resume.setForeground(Content.mainColor);
        resume.setFont(Content.font.deriveFont((float)game.Ecran.getHeight()/20));
        resume.setText("Resume" );
        resume.addActionListener(new resumeListener());
        resume.addMouseListener(resume);
        game.getContentPane().add(resume);
        
        //create restart button to restart the song 
        restart=new Button();
        restart.setBounds((int)game.Ecran.getWidth()*5/12,(int)game.Ecran.getHeight()*3/10,(int)game.Ecran.getWidth()/6,(int)game.Ecran.getHeight()/15);
        restart.setBorderPainted(false);
        restart.setBackground(Content.colors[12]);
        restart.setForeground(Content.mainColor);
        restart.setFont(Content.font.deriveFont((float)game.Ecran.getHeight()/20));
        restart.setText("restart" );
        restart.addActionListener(new restartListener());
        restart.addMouseListener(restart);
        game.getContentPane().add(restart);
        
        end=new Button();
        end.setBounds((int)game.Ecran.getWidth()*5/12,(int)game.Ecran.getHeight()*4/10,(int)game.Ecran.getWidth()/6,(int)game.Ecran.getHeight()/15);
        end.setBorderPainted(false);
        end.setBackground(Content.colors[9]);
        end.setForeground(Content.mainColor);
        end.setFont(Content.font.deriveFont((float)game.Ecran.getHeight()/20));
        end.setText("end song" );
        end.addActionListener(new endListener());
        end.addMouseListener(end);
        game.getContentPane().add(end);
        
        // create backToMenu button to go back to the game launcher
        backToMenu=new Button();
        backToMenu.setBounds((int)game.Ecran.getWidth()*5/12,(int)game.Ecran.getHeight()*5/10,(int)game.Ecran.getWidth()/6,(int)game.Ecran.getHeight()/15);
        backToMenu.setBorderPainted(false);
        backToMenu.setBackground(Content.colors[11]);
        backToMenu.setForeground(Content.mainColor);
        backToMenu.setFont(Content.font.deriveFont((float)game.Ecran.getHeight()/22));
        backToMenu.setText("main menu" );
        backToMenu.addActionListener(new backListener());
        backToMenu.addMouseListener(backToMenu);
        game.getContentPane().add(backToMenu);
        
        // create exitGame button to close the game
        exitGame=new Button();
        exitGame.setBounds((int)game.Ecran.getWidth()*5/12,(int)game.Ecran.getHeight()*6/10,(int)game.Ecran.getWidth()/6,(int)game.Ecran.getHeight()/15);
        exitGame.setBorderPainted(false);
        exitGame.setBackground(Content.colors[10]);
        exitGame.setForeground(Content.mainColor);
        exitGame.setFont(Content.font.deriveFont((float)game.Ecran.getHeight()/22));
        exitGame.setText("exit game" );
        exitGame.addActionListener(new exitListener());
        exitGame.addMouseListener(exitGame);
        game.getContentPane().add(exitGame);
        
        
        
        //repaint the gamewindow 
        game.repaint();
        
        
    }
    
    class exitListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            // create an OptionPane to be sure that player wants ton leave the game
            PopUp jop = new PopUp(game);            
                  
        }
    }
    class restartListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            game.state=Karaok.State.Restart; // change the gamewindow state to Restart
            System.out.println("[DEBUG] game restarted");
        }
    }
    
    class resumeListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            
            game.state=Karaok.State.Game; // change the gamewindow state to Game
            
            //remove all the pause's components
            game.getContentPane().remove(resume);
            game.getContentPane().remove(backToMenu);
            game.getContentPane().remove(exitGame);
            game.getContentPane().remove(restart);
            game.getContentPane().remove(logo);
            game.getContentPane().remove(end);
            
            // restart song and timer
            //game.clip.start(); 
            //game.timer.restart();
            game.requestFocus(); // request focus back 
            
            
            System.out.println("[DEBUG] game resumed" );
			
            
        }
    }
    class backListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            game.state=Karaok.State.Menu; // change gamewindow state to Menu
            
            // dispose this gamewindow
            System.out.println("[DEBUG] state changed to "+game.state);
            game.dispose();
            System.out.println("[DEBUG] gamewindow disposed");
            
        }
    }
    class endListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            game.state=Karaok.State.Win; // change gamewindow state to Menu
            
            // dispose this gamewindow
            System.out.println("[DEBUG] state changed to "+game.state);
            game.dispose();
            System.out.println("[DEBUG] gamewindow disposed");
            
        }
    }
}
