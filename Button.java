import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Button extends JButton implements MouseListener  {
	
  public String URL;
  public ImageIcon img;

  

  public Button(String url){
    super(new ImageIcon( url+"Off.png"));
    URL = url;
    img=new ImageIcon(url+"Off.png");

  }

  public Button(ImageIcon image) {
      super(image);
      URL = "";
      img=image;
  }
  
  protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (img == null) return;
      g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
  }
  
  protected void setIcon(ImageIcon image){
      super.setIcon(image);
      img= image;
  }
  
  
  public void mouseClicked(MouseEvent event) {
                    
  }

  public void mouseEntered(MouseEvent event) {
      setIcon(new ImageIcon( URL+"On.png"));
  }

  public void mouseExited(MouseEvent event) {
      setIcon(new ImageIcon(URL + "Off.png"));
  }

  public void mousePressed(MouseEvent event) {

  }
 
  public void mouseReleased(MouseEvent event) {
          
  }   
  
  
}