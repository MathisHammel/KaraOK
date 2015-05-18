import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
 
public class Button extends JButton { 
     
    private Image img;
     
    //Un constructeur pour choisir plus simplement l'image
    public Button(ImageIcon image) {
        super(image);
        img= image.getImage();
    }
     
    //On doit redéfinir la méthode paintComponent() pour les composant swing !!! et paint() pour awt
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img == null) return;
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this); //ici on adapte l'image à la taille du composant(getWidth(), getHeight())...
    }
    protected void setIcon(ImageIcon image){
        super.setIcon(image);
        img= image.getImage();
    }
}