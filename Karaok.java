import java.awt.Menu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Karaok {
    public enum State{
        Menu, Game, Win, Pause;
    }
    public static  State state;
    public static boolean pass;
    public static int song; 
    
    
    public static void main (String[] args){
        state = State.Menu;
        int a=0;
        pass=false;
        window MenuWindow=null;
        GameWindow gamewindow = null;
        Pause pause=null;
        while(true){
        switch(state){
        case Menu :
            if(!pass){
                pass=true;
                MenuWindow = new window();
                a=0;
            }
            state= MenuWindow.state;
            song= MenuWindow.song;
            break;
                   
        case Game : 
            if( a==0){ 
                System.out.println("[DEBUG] song value: " + song);
                gamewindow = new GameWindow(song);
                pass=false;
                a++;
            }
            else{ 
                a=1;
            }
            state= gamewindow.state;
            break;
                   
                   case Win : ;
                   break;
                   
        case Pause :
            if(a==1){
                pause=new Pause(gamewindow);
                a++;
                System.out.println("[DEBUG] Pause has been created");
            }
                     
            state=pause.state;
                     
            break;
        
            
                   default : 
                     break;
                 }
         
        }
        
        }
    
}
        

