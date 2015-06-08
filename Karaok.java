
/**class Karaok
 * this class is the main class of the game 
 */
public class Karaok {
    // enum State is a State that will help us to know where we are in the game
    public enum State{
        Menu, Game, Win, Pause, Restart;
    }
    public static  State state;
    
    // the song 
    public static int song; 
    
    // the Thread that manage the mic 
    public static FreqThread freqmaster;


    /**the main method of the game
     * depending on the state 
     * @param args
     */
    public static void main (String[] args) {
        //generate the font used in every window of the game
        try {
            Content.createFont();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        state = State.Menu;
        
        // the FregThread
        freqmaster=new FreqThread();
        freqmaster.start();
        
        int a=1; // variable a will help to know the state Before the change happended
        window MenuWindow=null;
        GameWindow gamewindow = null;
        Pause pause=null;
        scoreWindow scorewindow=null;
        
        while(true){
            switch(state){
                case Menu : // MainMenu
             
                    if(a!=0){
                        MenuWindow = new window(); //create new main menu window
                        if(a==3)scorewindow.dispose(); // when come from scorewindow
                        a=0;
                    }
                    state= MenuWindow.state;
                    song= MenuWindow.song;
                break;
                   
                case Game :  
                    if( a!=1 && a!=2){ 
                        gamewindow = new GameWindow(song); //create new GameWindow 
                        SongWillStart sws=new SongWillStart(gamewindow); // add the delay before game start
                        a=1;
                    }else{ 
                        if(a==2){SongWillStart sws=new SongWillStart(gamewindow);} // coming from pause
                        a=1;
                    }
                    state= gamewindow.state;
                break;
                   
                case Win : //scoreWindow 
                    if(a!=3){
                        scorewindow=new scoreWindow(song); //create a scoreWindow
                        a=3;
                    }
                    song=scorewindow.song;
                    state=scorewindow.state;
                break;
                   
                case Pause :
                    if(a!=2){
                        pause=new Pause(gamewindow); 
                        a=2;
                    }
                     
                    state=gamewindow.state;
                     
                break;
            
                case Restart: //restart the game
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
        

