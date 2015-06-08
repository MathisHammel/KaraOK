import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * class PopUp extends JOptionPane
 * this class create a JOptionPane used before leaving the game
 */
public class PopUp extends JOptionPane {

    /**
     * the PopUp constructor extends JOptionPane
     * the warning frame asking if you want to leave the game
     * @param frame JFrame the frame you want to add this PopUp
     */
    public PopUp(JFrame frame) {
        super();
                    
        int option = this.showConfirmDialog(null, 
                    "wallah're you sure you want to leave ?", // message
                    "Leaving game",                           // Frame name
                    JOptionPane.YES_NO_OPTION,                //type of answer
                    JOptionPane.WARNING_MESSAGE);             // type of JOptionPane  

              if(option == JOptionPane.OK_OPTION){ // clicked yes
                System.exit(0);
              }else{ // clicked no
                  frame.requestFocus(); //ask the focus back
              }
        
    }
}
