
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
    public Button logo;
    

    
    public Pause(GameWindow gamewindow) {
        //super();
        game=gamewindow;
        
        // stop timer and song 
        game.clip.stop();
        game.timer.stop();
        
        // create logo Label <======= FIXME
        logo = new Button();
        logo.setBackground(Content.colors[6]);
        logo.setBorderPainted(false);
        logo.setForeground(Color.white);
        logo.setFont(new Font("SansSerif", Font.BOLD, 40));
        logo.setText("PAUSE");
        logo.setBounds((int)(game.Ecran.getWidth()*0.375),(int)game.Ecran.getHeight()*1/10,(int)game.Ecran.getWidth()/4,46);
        logo.addActionListener(null);
        game.getContentPane().add(logo);
        
        // create resume Button to resume game
        resume=new Button();
        resume.setBounds((int)game.Ecran.getWidth()*5/12,(int)game.Ecran.getHeight()*2/10,(int)game.Ecran.getWidth()/6,(int)game.Ecran.getHeight()/15);
        resume.setBorderPainted(false);
        resume.setBackground(Content.colors[2]);
        resume.setForeground(Color.white);
        resume.setFont(new Font("Arial", Font.BOLD, (int)game.Ecran.getHeight()/40));
        resume.setText("Resume Game" );
        resume.addActionListener(new resumeListener());
        resume.addMouseListener(resume);
        game.getContentPane().add(resume);
        
        //create restart button to restart the song 
        restart=new Button();
        restart.setBounds((int)game.Ecran.getWidth()*5/12,(int)game.Ecran.getHeight()*3/10,(int)game.Ecran.getWidth()/6,(int)game.Ecran.getHeight()/15);
        restart.setBorderPainted(false);
        restart.setBackground(Content.colors[2]);
        restart.setForeground(Color.white);
        restart.setFont(new Font("Arial", Font.BOLD, (int)game.Ecran.getHeight()/40));
        restart.setText("restart Game" );
        restart.addActionListener(new restartListener());
        restart.addMouseListener(restart);
        game.getContentPane().add(restart);
        
        // create backToMenu button to go back to the game launcher
        backToMenu=new Button();
        backToMenu.setBounds((int)game.Ecran.getWidth()*5/12,(int)game.Ecran.getHeight()*4/10,(int)game.Ecran.getWidth()/6,(int)game.Ecran.getHeight()/15);
        backToMenu.setBorderPainted(false);
        backToMenu.setBackground(Content.colors[2]);
        backToMenu.setForeground(Color.white);
        backToMenu.setFont(new Font("Arial", Font.BOLD, (int)game.Ecran.getHeight()/40));
        backToMenu.setText("backToMenu" );
        backToMenu.addActionListener(new backListener());
        backToMenu.addMouseListener(backToMenu);
        game.getContentPane().add(backToMenu);
        
        // create exitGame button to close the game
        exitGame=new Button();
        exitGame.setBounds((int)game.Ecran.getWidth()*5/12,(int)game.Ecran.getHeight()*5/10,(int)game.Ecran.getWidth()/6,(int)game.Ecran.getHeight()/15);
        exitGame.setBorderPainted(false);
        exitGame.setBackground(Content.colors[2]);
        exitGame.setForeground(Color.white);
        exitGame.setFont(new Font("Arial", Font.BOLD, (int)game.Ecran.getHeight()/40));
        exitGame.setText("Leave Game" );
        exitGame.addActionListener(new exitListener());
        exitGame.addMouseListener(exitGame);
        game.getContentPane().add(exitGame);
        
        //repaint the gamewindow 
        game.repaint();
        
        
    }
    
    class exitListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            // create an OptionPane to be sure that player wants ton leave the game
            JOptionPane jop = new JOptionPane();            
                  int option = jop.showConfirmDialog(null, 
                    "wallah're you sure you want to leave ?", 
                    "Leaving game", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE);

                  if(option == JOptionPane.OK_OPTION){ // clicked on yes
                    System.exit(0); 
                  }
                  else{ //clicked on no
                      game.repaint(); //repaint the gamewindow
                      game.requestFocus(); // the gamewindow request the focus back
                  }
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
            
            // restart song and timer
            game.clip.start(); 
            game.timer.restart();
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
}
