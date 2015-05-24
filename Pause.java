
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Pause extends JPanel{
    public GameWindow game;
    public Button resume;
    public Button backToMenu;
    public Button exitGame;
    public Button restart;
    public Button logo;
    

    
    public Pause(GameWindow gamewindow) {
        super();
        game=gamewindow;
        game.clip.stop();
        game.timer.stop();
        this.setBounds(0, 0, (int)game.Ecran.getWidth(),(int) game.Ecran.getHeight());
        this.setBackground(new Color(0,0,0,0));
        this.setLayout(null);
        
        logo = new Button();
        logo.setBackground(Content.colors[6]);
        logo.setBorderPainted(false);
        logo.setForeground(Color.white);
        logo.setFont(new Font("SansSerif", Font.BOLD, 40));
        logo.setText("PAUSE");
        logo.setBounds((int)(game.Ecran.getWidth()*0.375),(int)game.Ecran.getHeight()*1/10,(int)game.Ecran.getWidth()/4,46);
        logo.addActionListener(null);
        game.add(logo);
        
        resume=new Button();
        resume.setBounds((int)game.Ecran.getWidth()*5/12,(int)game.Ecran.getHeight()*2/10,(int)game.Ecran.getWidth()/6,(int)game.Ecran.getHeight()/15);
        resume.setBorderPainted(false);
        resume.setBackground(Content.colors[2]);
        resume.setForeground(Color.white);
        resume.setFont(new Font("Arial", Font.BOLD, (int)game.Ecran.getHeight()/40));
        resume.setText("Resume Game" );
        resume.addActionListener(new resumeListener());
        resume.addMouseListener(resume);
        game.add(resume);
        
        restart=new Button();
        restart.setBounds((int)game.Ecran.getWidth()*5/12,(int)game.Ecran.getHeight()*3/10,(int)game.Ecran.getWidth()/6,(int)game.Ecran.getHeight()/15);
        restart.setBorderPainted(false);
        restart.setBackground(Content.colors[2]);
        restart.setForeground(Color.white);
        restart.setFont(new Font("Arial", Font.BOLD, (int)game.Ecran.getHeight()/40));
        restart.setText("restart Game" );
        restart.addActionListener(new restartListener());
        restart.addMouseListener(restart);
        game.add(restart);
        
        backToMenu=new Button();
        backToMenu.setBounds((int)game.Ecran.getWidth()*5/12,(int)game.Ecran.getHeight()*4/10,(int)game.Ecran.getWidth()/6,(int)game.Ecran.getHeight()/15);
        backToMenu.setBorderPainted(false);
        backToMenu.setBackground(Content.colors[2]);
        backToMenu.setForeground(Color.white);
        backToMenu.setFont(new Font("Arial", Font.BOLD, (int)game.Ecran.getHeight()/40));
        backToMenu.setText("backToMenu" );
        backToMenu.addActionListener(new backListener());
        backToMenu.addMouseListener(backToMenu);
        game.add(backToMenu);
        
        exitGame=new Button();
        exitGame.setBounds((int)game.Ecran.getWidth()*5/12,(int)game.Ecran.getHeight()*5/10,(int)game.Ecran.getWidth()/6,(int)game.Ecran.getHeight()/15);
        exitGame.setBorderPainted(false);
        exitGame.setBackground(Content.colors[2]);
        exitGame.setForeground(Color.white);
        exitGame.setFont(new Font("Arial", Font.BOLD, (int)game.Ecran.getHeight()/40));
        exitGame.setText("Leave Game" );
        exitGame.addActionListener(new exitListener());
        exitGame.addMouseListener(exitGame);
        game.add(exitGame);
        
        
        exitGame.requestFocus();
        resume.requestFocus();
        backToMenu.requestFocus();
        logo.requestFocus();
        restart.requestFocus();
        game.requestFocus();
        
        
    }
    
    class exitListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            JOptionPane jop = new JOptionPane();            
                  int option = jop.showConfirmDialog(null, 
                    "Are you sure you wan't to leave ?", 
                    "Leaving game", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE);

                  if(option == JOptionPane.OK_OPTION){
                    System.exit(0);
                  }
                  else{
                      backToMenu.repaint();
                      game.requestFocus();
                  }
        }
    }
    class restartListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            game.state=Karaok.State.Restart;
        }
    }
    
    class resumeListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            
            game.state=Karaok.State.Game; // change game window state 
            game.getContentPane().remove(Pause.this);
            game.getContentPane().remove(resume);
            game.getContentPane().remove(backToMenu);
            game.getContentPane().remove(exitGame);
            game.getContentPane().remove(restart);
            
            game.clip.start(); 
            game.timer.restart();
            game.requestFocus(); // request focus back 
            
            
            System.out.println("[DEBUG]" );
            
        }
    }
    class backListener implements ActionListener{
        
        public void actionPerformed (ActionEvent arg0){
            game.state=Karaok.State.Menu; // change gamewindow state
            System.out.println("[DEBUG] state changed to "+game.state);
            game.dispose();
            System.out.println("[DEBUG] gamewindow disposed");
            
        }
    }
}
