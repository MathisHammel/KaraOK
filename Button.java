import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;
  
public class Button extends JButton implements MouseListener{

	private static final long serialVersionUID = 1L;
	
  private Image imgOff;
  private Image imgOn;
  private Image img;

  

  public Button(ImageIcon iconOff, ImageIcon iconOn){
    super(iconOff);
    
    imgOff=iconOff.getImage();
    imgOn=iconOn.getImage();

  this.addMouseListener(this);
  }

  public Button(ImageIcon image) {
      super(image);
      imgOff= image.getImage();
      imgOn= image.getImage();
      img=imgOff;
  }
  
  protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (img == null) return;
      g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
  }
  
  protected void setIcon(ImageIcon image){
      super.setIcon(image);
      img= image.getImage();
  }
  
  public void mouseClicked(MouseEvent event) {
                    
  }

  public void mouseEntered(MouseEvent event) {
      img = imgOn;
  }

  public void mouseExited(MouseEvent event) {
	  img = imgOff;
  }

  public void mousePressed(MouseEvent event) {

  }
 
  public void mouseReleased(MouseEvent event) {
          
  }       
}