import java.awt.Menu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Karaok {
    public enum State{
        Menu, Game, Win, Pause;
    }
    public static State state;
    public static boolean pass;
    
    
    public static void main (String[] args){
        state = State.Menu;
        pass=false;
        window MenuWindow=null;
        while(true){
        switch(state){
        case Menu :
            if(!pass){
            pass=true;
                       MenuWindow = new window();     
        }
                     state= MenuWindow.state;
                   break;
                   
        case Game : if(pass){ 
                        System.out.println("changé");
                        GameWindow gamewindow = new GameWindow();
                        pass=false;
        }
                   break;
                   
                   case Win : ;
                   break;
                   
                   case Pause : ;
                   default : 
                     break;
                 }
         
        }
        
        }
    
}
        

