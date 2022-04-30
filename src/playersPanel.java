/*import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class playersPanel extends JPanel implements MouseListener {

    BufferedImage brick, ore, sheep, wheat, wood;

    public playersPanel() {
        try {
            brick = ImageIO.read(new File("Clay card.png"));
            ore = ImageIO.read(new File("Ore Card.png"));
            sheep = ImageIO.read(new File("Sheep Card.png"));
            wheat = ImageIO.read(new File("Wheat Card.png"));
            wood = ImageIO.read(new File("Wood Card.png"));
        }
        catch (Exception e) {}

    }

    public void paint(Graphics g) {
        int x = 30;
        int y = 30;
        for (Player p: main.players) {
            x = 30;
            for (ResourceCard card: p.resourceHand) {
                if (card.equals(ResourceCard.BRICK))
                    g.drawImage(brick, x, y, 30, 60, null);
                else if (card.equals(ResourceCard.ORE))
                    g.drawImage(ore, x, y, );
                else if (card.equals(ResourceCard.SHEEP))
                    img = grassland;
                else if (card.equals(ResourceCard.WHEAT))
                    img = wheat;
                else if (card.equals(ResourceCard.WOOD))
                    img = forest;
            }
        }
    }


    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}*/
