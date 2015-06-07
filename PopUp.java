import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PopUp extends JOptionPane {
    public PopUp(JFrame frame) {
        super();
                    
              int option = this.showConfirmDialog(null, 
                "wallah're you sure you want to leave ?", 
                "Leaving game", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);

              if(option == JOptionPane.OK_OPTION){
                System.exit(0);
              }else{
                  frame.requestFocus();
              }
        
    }
}
