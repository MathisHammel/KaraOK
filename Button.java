import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * classe Button extends Jbutton Implements MouseListener
 * this classe creates a Jbutton elements that can be used as Mouselistener too
 * When mouse is on, the button will change
 * the ImageIcon will be adapted to the size of the button
 */
public class Button extends JButton implements MouseListener  {
 
  public String URL; // the URL of the Icon, the real URL must end by On.png or Off.png, but will not be written in URL
  public ImageIcon img;

    /**
     * Button constructor 
     * create a JButton that change Icon when mouse is on
     * @param url the URL of the Icon without On.png or Off.png
     */
    public Button(String url){
    super(new ImageIcon( url+"Off.png"));
    URL = url;
    img=new ImageIcon(url+"Off.png");

  }

    /**
     * Button constructor 
     * no interaction with the Icon and the mouse
     * @param image the ImageIcon of the button
     */
    public Button(ImageIcon image) {
      super(image);
      URL = "";
      img=image;
  }

    /**
     * Button constructor 
     * create a JButton, Font will change when mouse is on
     */
    public Button (){
      super();
  }

    /**
     * Overide paintCompononent method
     * adapte the size of the Icon to the size of the button 
     * @param g Graphics
     */
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (img == null) return;
      g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
  }

    /**
     * Overide setIcon methode
     * @param image ImageIcon that will be the Icon of the button 
     */
    protected void setIcon(ImageIcon image){
      super.setIcon(image);
      img= image;
  }


    /**
     * Overide mouseClicked methode
     * no actions
     * @param event MouseEvent
     */
    public void mouseClicked(MouseEvent event) {
                    
  }

    /**
     * Overide mouseEntered methode
     * change the Icon of the Button to On 
     * @param event MouseEvent
     */
    public void mouseEntered(MouseEvent event) {
      setIcon(new ImageIcon( URL+"On.png"));
      this.setForeground(Color.white);
  }

    /**
     * Overide mouseExited
     * chanfe the Icon of the Button to Off
     * @param event MouseEvent
     */
    public void mouseExited(MouseEvent event) {
      setIcon(new ImageIcon(URL + "Off.png"));
      this.setForeground(Content.mainColor);
  }

    /**
     * Overide mousePressed methode
     * no actions
     * @param event MouseEvent
     */
  public void mousePressed(MouseEvent event) {

  }
    /**
     * Overide mouseReleased methode
     * no actions
     * @param event MouseEvent
     */
  public void mouseReleased(MouseEvent event) {
          
  }   
  
  
}