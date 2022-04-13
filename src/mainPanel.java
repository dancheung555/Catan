import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Stack;
import java.util.Collections;
public class mainPanel extends JPanel implements MouseListener {
    private BufferedImage clay, forest, desert, mountains, grassland, wheat, clayCard, wheatCard, woodCard, oreCard, sheepCard, buildingCost;
    private Stack<BufferedImage> tiles = new Stack<>();
    public mainPanel()
    {
        try
        {
            clay = ImageIO.read(new File("Clay.png"));
            for(int i = 0; i<3; i++)
                tiles.push(clay);
            forest = ImageIO.read(new File("Forest.png"));
            for(int i = 0; i<4; i++)
                tiles.push(forest);
            desert = ImageIO.read(new File("Desert.png"));
            tiles.push(desert);
            mountains = ImageIO.read(new File("Mountains.png"));
            for(int i = 0; i<3; i++)
                tiles.push(mountains);
            grassland = ImageIO.read(new File("Grassland.png"));
            for(int i = 0; i<4; i++)
                tiles.push(grassland);
            wheat = ImageIO.read(new File("Wheat.png"));
            for(int i = 0; i<4; i++)
                tiles.push(wheat);
            clayCard = ImageIO.read(new File("Clay Card.png"));
            wheatCard = ImageIO.read(new File("Wheat Card.png"));
            woodCard = ImageIO.read(new File("Wood Card.png"));
            oreCard = ImageIO.read(new File("Ore Card.png"));
            sheepCard = ImageIO.read(new File("Sheep Card.png"));
            buildingCost = ImageIO.read(new File("Building Costs.png"));
        }
        catch (Exception E)
        {
            System.out.println("error");
        }
        Collections.shuffle(tiles);
        addMouseListener(this);
    }
    public void paint(Graphics g)
    {
        g.setColor(new Color(0, 140, 240));
        g.fillRect(0,0, getWidth(), getHeight());
        g.setColor(Color.BLUE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString("Settler 1", 0,40);
        g.drawLine(10,50,10,80);
        g.drawLine(10,50,0,60);
        g.drawLine(10,50,20,60);
        for(int i = 0; i<3; i++)
            g.drawImage(clayCard, i*13+30,50,26,39,null);
        g.setColor(Color.RED);
        g.drawString("Settler 2", 0,175);
        g.drawLine(10,185,10,215);
        g.drawLine(10,185,0,195);
        g.drawLine(10,185,20,195);
        for(int i = 0; i<3; i++)
            g.drawImage(clayCard, i*13+30,185,26,39,null);
        g.setColor(Color.GREEN);
        g.drawString("Settler 3", 0,310);
        g.drawLine(10,320,10,350);
        g.drawLine(10,320,0,330);
        g.drawLine(10,320,20,330);
        for(int i = 0; i<3; i++)
            g.drawImage(clayCard, i*13+30,320,26,39,null);
        g.setColor(Color.YELLOW);
        g.drawString("Settler 4", 0,445);
        g.drawLine(10,455,10,485);
        g.drawLine(10,455,0,465);
        g.drawLine(10,455,20,465);
        g.drawImage(buildingCost,310,340,160,200,null);
        for(int i = 0; i<3; i++)
            g.drawImage(clayCard, i*13+30,455,26,39,null);
        for(int i = 0; i<3; i++)
        {
            g.drawImage(tiles.pop(), i*90+570,62, 90,104,null);
            g.drawImage(tiles.pop(), i*90+570,374, 90,104,null);
        }
        for(int i = 0; i<4; i++)
        {
            g.drawImage(tiles.pop(), i*90+525,140, 90,104,null);
            g.drawImage(tiles.pop(), i*90+525,296, 90,104,null);
        }
        for(int i = 0; i<5; i++)
            g.drawImage(tiles.pop(), i*90+480,218, 90,104,null);
        g.fillRect(885,400,60,20);
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}
