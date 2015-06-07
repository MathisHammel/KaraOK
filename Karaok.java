import java.awt.Font;
import java.awt.Menu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.IOException;

public class Karaok {
    public enum State{
        Menu, Game, Win, Pause, Restart;
    }
    public static  State state;
    public static int song; 
    public static FreqThread freqmaster;
    
    
    public static void main (String[] args) {
        state = State.Menu;
        freqmaster=new FreqThread();
        freqmaster.start();
        int a=1; // variable a will help to know the state Before the change happended
        window MenuWindow=null;
        GameWindow gamewindow = null;
        Pause pause=null;
        scoreWindow scorewindow=null;
        while(true){
        switch(state){
        case Menu :
             
            if(a!=0){
                
                MenuWindow = new window();
                if(a==3)scorewindow.dispose();
                a=0;
            }
            state= MenuWindow.state;
            song= MenuWindow.song;
            break;
                   
        case Game : 
            if( a!=1 && a!=2){ 
                System.out.println("[DEBUG] song value: " + song);
                gamewindow = new GameWindow(song);
                SongWillStart sws=new SongWillStart(gamewindow);
                a=1;
            }
            else{ 
                if(a==2){SongWillStart sws=new SongWillStart(gamewindow);}
                a=1;
            }
            state= gamewindow.state;
            break;
                   
        case Win :
            if(a!=3){
                scorewindow=new scoreWindow(song);
                a=3;
            }
            song=scorewindow.song;
            state=scorewindow.state;
            break;
                   
        case Pause :
            if(a!=2){
                pause=new Pause(gamewindow);
                a=2;
                System.out.println("[DEBUG] Pause has been created");
                System.out.println("[DEBUG]" + gamewindow.state +" a = " + a);
            }
                     
            state=gamewindow.state;
                     
            break;
        case Restart:
            a=1;
            GameWindow other = new GameWindow(song);
            gamewindow.dispose();
            gamewindow=other;
            SongWillStart sws=new SongWillStart(gamewindow);
            state=State.Game;
            break;
            
                   default : 
                     break;
                 }
         
        }
        
        }
    
}
        

