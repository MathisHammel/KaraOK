import java.awt.Menu;

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
        

