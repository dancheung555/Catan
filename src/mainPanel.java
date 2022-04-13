import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
public class mainPanel extends JPanel implements MouseListener {
    public mainPanel()
    {
        addMouseListener(this);
    }
    public void paint(Graphics g)
    {
        g.setColor(new Color(0, 140, 240));
        g.fillRect(0,0, getWidth(), getHeight());
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}
