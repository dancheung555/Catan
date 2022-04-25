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
    private BufferedImage[] pips = new BufferedImage[13];
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

            pips[0] = null;
            pips[1] = null;
            pips[2] = ImageIO.read(new File("2 pip.png"));
            pips[3] = ImageIO.read(new File("3 pip.png"));
            pips[4] = ImageIO.read(new File("4 pip.png"));
            pips[5] = ImageIO.read(new File("5 pip.png"));
            pips[6] = ImageIO.read(new File("6 pip.png"));
            pips[7] = ImageIO.read(new File("7 pip.png"));
            pips[8] = ImageIO.read(new File("8 pip.png"));
            pips[9] = ImageIO.read(new File("9 pip.png"));
            pips[10] = ImageIO.read(new File("10 pip.png"));
            pips[11] = ImageIO.read(new File("11 pip.png"));
            pips[12] = ImageIO.read(new File("12 pip.png"));



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
        /*g.setColor(new Color(0, 140, 240));
        g.fillRect(0,0, 960, 540);
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
        g.fillRect(885,450,60,20);
        g.fillRect(885, 480,60,20);
        g.fillRect(885, 510,60,20);*/

        int x, y;
        Tile temp = null;
        Image img = null;
        ResourceCard res;
        int pip;
        for (int i = 2; i < 43; i += 2) {
            x = i % 9 + 1;
            y = 3 * (i / 9) + 2;

            temp = main.board[x][y];
            if (temp != null) {
                res = temp.getResourceType();
                pip = temp.getPipNumber();

                if (res == null)

                if (res.equals(ResourceCard.BRICK))
                    img = clay;
                else if (res.equals(ResourceCard.ORE))
                    img = mountains;
                else if (res.equals(ResourceCard.SHEEP))
                    img = grassland;
                else if (res.equals(ResourceCard.WHEAT))
                    img = wheat;
                else if (res.equals(ResourceCard.WOOD))
                    img = forest;

                g.drawImage(img, x * 98 - 49, y * 112 - 56, 98, 112, null);
                g.drawImage(pips[pip], x * 98 - 20, y * 112 - 20, 40, 40, null);
            }
        }


    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}