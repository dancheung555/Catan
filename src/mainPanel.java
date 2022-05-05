import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Stack;
import java.util.Collections;

import static java.lang.System.out;

public class mainPanel extends JPanel implements MouseListener {
    private BufferedImage clay, forest, desert, mountains, grassland, wheat,
            clayCard, wheatCard, woodCard, oreCard, sheepCard, cardBack,
            developmentCosts, background, robber;
    private BufferedImage brickicon, oreicon, sheepicon, wheaticon, woodicon;
    private BufferedImage[] pips = new BufferedImage[13];

    final int HEIGHT = 540;
    final int marg = 60;
    private int h = (HEIGHT - marg) / 16;
    private int w = (int) ((HEIGHT - marg) * 1.0825 / 10);

    //index i for general usage
    int i = 0;

    public mainPanel()
    {
        try
        {
            clay = ImageIO.read(new File("Clay.png"));
            forest = ImageIO.read(new File("Forest.png"));
            desert = ImageIO.read(new File("Desert.png"));
            mountains = ImageIO.read(new File("Mountains.png"));
            grassland = ImageIO.read(new File("Grassland.png"));
            wheat = ImageIO.read(new File("Wheat.png"));

            pips[0] = null;
            pips[1] = null;
            pips[2] = ImageIO.read(new File("2 pip.png"));
            pips[3] = ImageIO.read(new File("3 pip.png"));
            pips[4] = ImageIO.read(new File("4 pip.png"));
            pips[5] = ImageIO.read(new File("5 pip.png"));
            pips[6] = ImageIO.read(new File("6 pip.png"));
            pips[7] = null;
            pips[8] = ImageIO.read(new File("8 pip.png"));
            pips[9] = ImageIO.read(new File("9 pip.png"));
            pips[10] = ImageIO.read(new File("10 pip.png"));
            pips[11] = ImageIO.read(new File("11 pip.png"));
            pips[12] = ImageIO.read(new File("12 pip.png"));

            background = ImageIO.read(new File("board background.png"));

            clayCard = ImageIO.read(new File("Clay Card.png"));
            wheatCard = ImageIO.read(new File("Wheat Card.png"));
            woodCard = ImageIO.read(new File("Wood Card.png"));
            oreCard = ImageIO.read(new File("Ore Card.png"));
            sheepCard = ImageIO.read(new File("Sheep Card.png"));
            cardBack = ImageIO.read(new File("Development Card.png"));
            developmentCosts = ImageIO.read(new File("developmentcosts.png"));

            brickicon = ImageIO.read(new File("icon brick.png"));
            oreicon = ImageIO.read(new File("icon ore.png"));
            sheepicon = ImageIO.read(new File("icon sheep.png"));
            wheaticon = ImageIO.read(new File("icon wheat.png"));
            woodicon = ImageIO.read(new File("icon wood.png"));

            robber = ImageIO.read(new File("robber.png"));

        }
        catch (Exception E)
        {
            out.println("error");
        }

        addMouseListener(this);
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.drawLine(0, 540, 960, 540);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        g.drawString(main.guide, 5, 5);
        g.setColor(new Color(191, 191, 191));
        g.fillRect(0, 0, 960, 540);

        int cardx;
        g.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        for (int i = 0; i < 4; i++) {
            g.setColor(main.players.get(i).getColor());
            g.drawString("" + (i + 1), 30, 80 + i * 80);

            g.setColor(Color.darkGray);
            if (i == main.turn) {
                g.fillPolygon(new int[]{5, 5, 25}, new int[]{50 + i * 80, 60 + i * 80, 55 + i * 80}, 3);
            }
            g.setColor(Color.gray);
            g.fillRect(5, 65 + i * 80, 20, 20);

            cardx = 0;
            if (main.displayHands[i]) {
                BufferedImage img = null;
                for (ResourceCard rc : main.players.get(i).resourceHand) {
                    if (rc != null) {
                        if (rc.equals(ResourceCard.BRICK))
                            img = clayCard;
                        else if (rc.equals(ResourceCard.ORE))
                            img = oreCard;
                        else if (rc.equals(ResourceCard.SHEEP))
                            img = sheepCard;
                        else if (rc.equals(ResourceCard.WHEAT))
                            img = wheatCard;
                        else if (rc.equals(ResourceCard.WOOD))
                            img = woodCard;

                        if (!main.players.get(i).selectedResources[cardx])
                            g.drawImage(img, cardx * 15 + 50, i * 80 + 40, 35, 52, null);
                        else
                            g.drawImage(img, cardx * 15 + 50, i * 80 + 30, 35, 52, null);
                        cardx++;
                    }
                }
            }
            else {
                for (ResourceCard rc : main.players.get(i).resourceHand) {
                    if (rc != null) {
                        if (!main.players.get(i).selectedResources[cardx])
                            g.drawImage(cardBack, cardx * 15 + 50, i * 80 + 40, 35, 52, null);
                        else
                            g.drawImage(cardBack, cardx * 15 + 50, i * 80 + 30, 35, 52, null);
                        cardx++;
                    }
                }
                cardx = 0;
                for (DevelopmentCard dc : main.players.get(i).developmentCardHand) {
                    if (dc != null) {
                        g.drawImage(cardBack, cardx * 15 + (355 - main.players.get(i).developmentCardHand.size() * 15), i * 80 + 40, 35, 52, null);
                    }
                }
            }
        }

        //dice
        g.setColor(Color.white);
        g.fillRoundRect(230, 360, 50, 50, 10, 10);
        g.fillRoundRect(285, 360, 50, 50, 10, 10);
        g.setColor(Color.black);
        Font dieFont = new Font("Times New Roman", Font.PLAIN, 40);
        g.setFont(dieFont);
        FontMetrics dieFontMetrics = getFontMetrics(dieFont);
        g.drawString("" + main.dice1, 255 - dieFontMetrics.stringWidth("" + main.dice1) / 2, 400);
        g.drawString("" + main.dice2, 310 - dieFontMetrics.stringWidth("" + main.dice2) / 2, 400);

        //trade button
        g.setColor(Color.red);
        g.fillRect(240, 430, 60, 30);

        //display bank
        g.setColor(Color.black);
        Font bankFont = new Font("Times New Roman", Font.PLAIN, 10);
        g.setFont(bankFont);
        FontMetrics bankFontMetrics = getFontMetrics(bankFont);
        g.drawImage(clayCard, 30, 360, 35, 52, null);
        g.drawString("" + main.bank.get(ResourceCard.BRICK), 47 - bankFontMetrics.stringWidth("" + main.bank.get(ResourceCard.BRICK)) / 2, 420);
        g.drawImage(oreCard, 70, 360, 35, 52, null);
        g.drawString("" + main.bank.get(ResourceCard.ORE), 87 - bankFontMetrics.stringWidth("" + main.bank.get(ResourceCard.ORE)) / 2, 420);
        g.drawImage(sheepCard, 110, 360, 35, 52, null);
        g.drawString("" + main.bank.get(ResourceCard.SHEEP), 127 - bankFontMetrics.stringWidth("" + main.bank.get(ResourceCard.SHEEP)) / 2, 420);
        g.drawImage(wheatCard, 150, 360, 35, 52, null);
        g.drawString("" + main.bank.get(ResourceCard.WHEAT), 167 - bankFontMetrics.stringWidth("" + main.bank.get(ResourceCard.WHEAT)) / 2, 420);
        g.drawImage(woodCard, 190, 360, 35, 52, null);
        g.drawString("" + main.bank.get(ResourceCard.WOOD), 207 - bankFontMetrics.stringWidth("" + main.bank.get(ResourceCard.WOOD)) / 2, 420);

        //end turn button
        g.setColor(Color.cyan);
        g.fillRect(240, 470, 60, 30);

        g.drawImage(developmentCosts,30, 430, 200, 100, null);

        /*
        g.setColor(new Color(0, 140, 240));
        g.fillRect(0,0, WIDTH, HEIGHT);
        g.setColor(Color.BLUE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString("Settler 1", 0,40);
        g.drawLine(10,50,10,80);
        g.drawLine(10,50,0,60);
        g.drawLine(10,50,20,60);
        for(int i = 0; i<3; i++)
            g.drawImage(clayCard, i*13+30,50,52,78,null);
        g.setColor(Color.RED);
        g.drawString("Settler 2", 0,175);
        g.drawLine(10,185,10,215);
        g.drawLine(10,185,0,195);
        g.drawLine(10,185,20,195);
        for(int i = 0; i<3; i++)
            g.drawImage(clayCard, i*13+30,185,52,78,null);
        g.setColor(Color.GREEN);
        g.drawString("Settler 3", 0,310);
        g.drawLine(10,320,10,350);
        g.drawLine(10,320,0,330);
        g.drawLine(10,320,20,330);
        for(int i = 0; i<3; i++)
            g.drawImage(clayCard, i*13+30,320,52,78,null);
        g.setColor(Color.YELLOW);
        g.drawString("Settler 4", 0,445);
        g.drawLine(10,455,10,485);
        g.drawLine(10,455,0,465);
        g.drawLine(10,455,20,465);
        g.drawImage(buildingCost,310,340,160,200,null);
        for(int i = 0; i<3; i++)
            g.drawImage(clayCard, i*13+30,455,52,78,null);
        */

        //g.drawImage(buildingCost,310,340,160,200,null);

        /*
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
        g.fillRect(885, 510,60,20);
        */


        g.drawImage(background, 345, 0, 600, 540, null);

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

                if (res == null)
                    img = desert;
                else if (res.equals(ResourceCard.BRICK))
                    img = clay;
                else if (res.equals(ResourceCard.ORE))
                    img = mountains;
                else if (res.equals(ResourceCard.SHEEP))
                    img = grassland;
                else if (res.equals(ResourceCard.WHEAT))
                    img = wheat;
                else if (res.equals(ResourceCard.WOOD))
                    img = forest;

                g.drawImage(img, x * w - w + 390, y * h - 2 * h + 30, 2 * w, 4 * h, null);

                pip = temp.getPipNumber();
                g.drawImage(pips[pip], x * w + 370, y * h + 10, 40, 40, null);
            }
        }

        g.drawImage(robber, main.robberx * w + 370, main.robbery * h + 10, 40, 40, null);

        int portpos[] = {
                500, 20,
                685, 20,
                843, 110,
                930, 270,
                843, 430,
                685, 520,
                500, 520,
                410, 360,
                410, 180};
        int portposI = 0;
        int portx, porty;
        Font portFont = new Font("Times New Roman", 0, 20);
        g.setFont(portFont);
        FontMetrics portFontMetrics = getFontMetrics(portFont);
        int portFontWidth;
        Color portBrown = new Color(118, 80, 6);
        g.setColor(Color.white);

        //to display ports
        for (Port p: main.ports) {
            portx = portpos[portposI++];
            porty = portpos[portposI++];
            if (p.getSpecialty() == null) {
                portFontWidth = portFontMetrics.stringWidth("3");
                g.setColor(portBrown);
                g.fillOval(portx - 10, porty - 10, 20, 20);
                g.setColor(Color.white);
                g.drawString("3", portx - portFontWidth / 2, porty + 4);
            }
            else {
                portFontWidth = portFontMetrics.stringWidth("2");
                if (p.getSpecialty().equals(ResourceCard.BRICK))
                    g.drawImage(brickicon, portx - 10, porty - 10, 20, 20, null);
                else if (p.getSpecialty().equals(ResourceCard.ORE))
                    g.drawImage(oreicon, portx - 10, porty - 10, 20, 20, null);
                else if (p.getSpecialty().equals(ResourceCard.SHEEP))
                    g.drawImage(sheepicon, portx - 10, porty - 10, 20, 20, null);
                else if (p.getSpecialty().equals(ResourceCard.WHEAT))
                    g.drawImage(wheaticon, portx - 10, porty - 10, 20, 20, null);
                else if (p.getSpecialty().equals(ResourceCard.WOOD))
                    g.drawImage(woodicon, portx - 10, porty - 10, 20, 20, null);
                g.drawString("2", portx - portFontWidth / 2, porty + 4);
            }
        }

        int r1, c1;

        Intersection itemp;
        //to display settlements and cities on the board
        for (int i = 3; i < 184; i += 2) {
            r1 = i % 11;
            c1 = i / 11;
            if (c1 % 3 != 2 && main.inter[r1][c1] != null) {
                itemp = main.inter[r1][c1];
                if (itemp.getSettlement() != null) {
                    g.setColor(itemp.getSettlement().getOwner().getColor());
                    if (itemp.getSettlement().getTier() == 1) {
                        g.fillOval(r1 * w + 380, c1 * h + 20, 20, 20);
                    }
                    else
                        g.fillRect(r1 * w + 380, c1 * h + 20, 20, 20);
                }
            }
        }

        //to display roads on the board
        for (int i = 3; i < 64; i += 2) {
            r1 = i % 11;
            c1 = (i / 11) * 3;
            if (main.inter[r1][c1] != null) {
                itemp = main.inter[r1][c1];
                if (itemp.getLeftRoad() != null) {
                    g.setColor(itemp.getLeftRoad().color);
                    g.fillPolygon(itemp.getLeftRoad().p);
                }
                if (itemp.getMiddleRoad() != null) {
                    g.setColor(itemp.getMiddleRoad().color);
                    g.fillPolygon(itemp.getMiddleRoad().p);
                }
                if (itemp.getRightRoad() != null) {
                    g.setColor(itemp.getRightRoad().color);
                    g.fillPolygon(itemp.getRightRoad().p);
                }
            }
        }

        //to highlight open and eligible settlement, city, or road intersections/edges
        if (main.highlightEligibleSettlements) {
            g.setColor(new Color(85, 255, 59, 172));
            for (int i = 3; i < 184; i += 2) {
                r1 = i % 11;
                c1 = i / 11;
                if (c1 % 3 != 2 && main.inter[r1][c1] != null) {
                    if (main.inter[r1][c1].isOpen()) {
                        g.fillOval(r1 * w + 380, c1 * h + 20, 20, 20);
                    }
                }
            }
        }
        else if (main.highlightEligibleCities) {
            g.setColor(new Color(85, 255, 59, 172));
            for (Settlement s: main.players.get(main.turn).settlements) {
                g.fillRect(s.row * w + 380, s.col * h + 20, 20, 20);
            }
        }
        else if (main.highlightEligibleRoads) {
            g.setColor(new Color(85, 255, 59, 172));
            for (Road r: main.players.get(main.turn).eligibleRoads) {
                g.fillPolygon(r.p);
            }
        }



    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        for (int i = 0; i < 4; i++) {
            if (x > 5 && x < 25 && y > 65 + i * 80 && y < 85 + i * 80) {
                if (main.displayHands[i]) {
                    main.displayHands[i] = false;
                }
                else {
                    main.displayHands[i] = true;
                }
                repaint();
                break;
            }
        }



        if (main.startingSetup) {
            try {
                main.turn = main.startingTurnOrder[i];

                if (main.buildingSettlement) {
                    int closestInterx = ((int) ((x - 390 + w / 2) / w)) * w + 390;
                    int closestIntery = ((int) ((y - 30 + h / 2) / h)) * h + 30;
                    int closestInterBoardx = ((int) ((x - 390 + w / 2) / w));
                    int closestInterBoardy = ((int) ((y - 30 + h / 2) / h));

                    if (Math.pow(closestInterx - x, 2) + Math.pow(closestIntery - y, 2) < 100 && main.inter[closestInterBoardx][closestInterBoardy] != null) {
                        if (main.inter[closestInterBoardx][closestInterBoardy].isOpen()) {
                            main.inter[closestInterBoardx][closestInterBoardy].addSettlement(new Settlement(main.players.get(main.turn), closestInterBoardx, closestInterBoardy));
                            main.players.get(main.turn).updateStartingEligibleRoads(closestInterBoardx, closestInterBoardy);
                            main.highlightEligibleSettlements = false;
                            main.buildingSettlement = false;
                            main.highlightEligibleRoads = true;
                            main.buildingRoad = true;
                            if (i > 3) {
                                main.inter[closestInterBoardx][closestInterBoardy].obtainStartingResources();
                            }
                            repaint();
                        }
                    }
                }
                else if (main.buildingRoad) {

                    int ygroup = (y + h / 2 + 30) / (3 * h);
                    int closestIntery = (y + h / 2 + 30) / (3 * h) * (3 * h) - h + 30;
                    int closestInterBoardx;
                    int closestInterx;

                    if (Math.abs(y - closestIntery) < 20) {
                        closestInterBoardx = (x + w / 2 - 390) / w;
                        closestInterx = (x + w / 2 - 390) / w * w + 390;
                        out.println(closestInterx + " " + closestInterBoardx);

                        if (main.inter[closestInterBoardx][3 * ygroup] != null) {
                            out.println(Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2));
                            if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 100 &&
                                    main.inter[closestInterBoardx][3 * ygroup].getMiddleRoad() == null &&
                                    main.players.get(main.turn).canBuildRoad(closestInterBoardx, 3 * ygroup, 2)) {
                                main.inter[closestInterBoardx][3 * ygroup].buildMiddleRoad(main.players.get(main.turn));
                                main.highlightEligibleRoads = false;
                                main.buildingRoad = false;
                                main.highlightEligibleSettlements = true;
                                main.buildingSettlement = true;
                                i++;
                                repaint();
                            }
                        }
                    }
                    else {
                        ygroup = y / (3 * h);
                        int closestInterBoardy = ygroup * 3;
                        closestIntery = ygroup * (3 * h) + h / 2 + 30;
                        closestInterBoardx = (x - 390) / w;
                        closestInterx = closestInterBoardx * w + w / 2 + 390;

                        if (main.inter[closestInterBoardx][closestInterBoardy] != null && main.inter[closestInterBoardx + 1][closestInterBoardy + 1] != null) {
                            if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 100 &&
                                    main.players.get(main.turn).canBuildRoad(closestInterBoardx, closestInterBoardy, 1)) {
                                main.inter[closestInterBoardx][closestInterBoardy].buildRightRoad(main.players.get(main.turn));
                                main.highlightEligibleRoads = false;
                                main.buildingRoad = false;
                                main.highlightEligibleSettlements = true;
                                main.buildingSettlement = true;
                                i++;
                                repaint();
                            }
                        }
                        else if (main.inter[closestInterBoardx][closestInterBoardy + 1] != null && main.inter[closestInterBoardx + 1][closestInterBoardy] != null) {
                            if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 100 &&
                                    main.players.get(main.turn).canBuildRoad(closestInterBoardx, closestInterBoardy + 1, 3)) {
                                main.inter[closestInterBoardx][closestInterBoardy + 1].buildRightRoad(main.players.get(main.turn));
                                main.highlightEligibleRoads = false;
                                main.buildingRoad = false;
                                main.highlightEligibleSettlements = true;
                                main.buildingSettlement = true;
                                i++;
                                repaint();
                            }
                        }
                    }
                }
                if (i > 7) {
                    main.startingSetup = false;
                    main.buildingSettlement = false;
                    main.highlightEligibleSettlements = false;
                    main.canRollDie = true;
                    for (Player p: main.players) {
                        p.updateEligibleRoads();
                    }
                }
                main.turn = main.startingTurnOrder[i];
            } catch (Exception fuckyoujava) {}
        }

        else if (main.canRollDie) {
            if (x > 230 && x < 335 && y > 360 && y < 410) {
                main.rollDie();
                repaint();
            }
        }

        //FUCK YOU KALE FINISH THIS MOTHERFUCKer
        else if (main.halving) {
            i = 0;
            for (int j = i; j < 4; j++) {
                if (main.players.get(j).resourceHand.size() > 7) {
                    i = j;
                    break;
                }
            }
            if (main.players.get(i).resourceHand.size() > 7) {
                out.println("halving player " + i);
                int clickedCard = (x - 50) / 15;
                int numCardsToBeDiscarded = main.players.get(i).resourceHand.size() / 2;
                if (y - i * 80 - 30 < 60) {
                    try {
                        main.players.get(i).selectResourceCard(clickedCard);
                    }
                    catch (Exception fuckoff) {
                        if (clickedCard == main.players.get(i).resourceHand.size())
                            main.players.get(i).selectResourceCard(clickedCard - 1);
                    }
                    if (main.players.get(i).getSelectedCards().size() == numCardsToBeDiscarded) {
                        main.players.get(i).removeSelectedCards();
                        i++;
                        out.println("1 halving next plaer");
                        for (int j = i; j < 4; j++) {
                            if (main.players.get(j).resourceHand.size() > 7) {
                                i = j;
                                break;
                            }
                        }
                    }
                    repaint();
                }
            }
            else {
                i++;
                out.println("2 halving next player");
            }
            if (i > 3) {
                main.halving = false;
                main.movingRobber = true;
            }
        }

        else if (main.movingRobber) {
            int closestTilex = ((int) ((x - 390 + w / 2) / w - 1)) * w + w + 390;
            int closestTiley = ((int) ((y - 30) / (3 * h))) * 3 * h + 2 * h + 30;
            int closestTileBoardx = (int) ((x - 390 + w / 2) / w - 1) + 1;
            int closestTileBoardy = ((y - 30) / (3 * h)) * 3 + 2;

            if (Math.pow(closestTilex - x, 2) + Math.pow(closestTiley - y, 2) < 900 && main.board[closestTileBoardx][closestTileBoardy] != null) {
                if (main.board[closestTileBoardx][closestTileBoardy].getPipNumber() != 0) {
                    main.moveRobber(closestTileBoardx, closestTileBoardy);
                    out.println("success " + main.robberx + " " + main.robbery);
                    main.movingRobber = false;
                    repaint();
                }
            }
        }

        else if (main.stealing) {
            int clickedSettler = (y - 30) / 80;
            out.println("stealing clicked on bitchass");
            if (x > 30 && x < 390) {
                main.steal(main.players.get(main.turn), main.players.get(clickedSettler));
                repaint();
            }
        }

        else if (main.canSelectCards) {

            int clickedSettler = (y - 30) / 80;
            int clickedCard = (x - 50) / 15;

            if (clickedSettler > 3) {}
            else if (y - clickedSettler * 80 - 30 < 60) {
                try {
                    main.players.get(clickedSettler).selectResourceCard(clickedCard);
                }
                catch (Exception fuckoff) {
                    if (clickedCard == main.players.get(clickedSettler).resourceHand.size())
                        main.players.get(clickedSettler).selectResourceCard(clickedCard - 1);
                }
                repaint();
            }

            if (main.tradingBuilding) {

                if (x > 240 && x < 300 && y > 430 && y < 460) {
                    out.println("domestic trade called");
                    main.domesticTrade();
                    repaint();
                }

                if (x > 30 && x < 230 && y > 360 && y < 412) {
                    clickedCard = (x - 30) / 40;
                    out.println("maritime trade called");
                    out.println(clickedCard);
                    if (x - (clickedCard * 40 + 30) < 35) {
                        main.maritimeTrade(main.rco[clickedCard]);
                        repaint();
                    }
                }

                if (x > 30 && x < 230 && y > 430 && y < 530) {
                    clickedCard = (x - 30) / 50;
                    out.println("called build");
                    if (clickedCard == 0) {
                        if (main.players.get(main.turn).hasResourcesForRoad()) {
                            main.buildingRoad = true;
                            main.highlightEligibleRoads = true;
                            main.canSelectCards = false;
                        }
                        else {
                            main.guide = "Insufficient resources for road";
                        }
                    }
                    else if (clickedCard == 1) {
                        if (main.players.get(main.turn).hasResourcesForSettlement()) {
                            main.buildingSettlement = true;
                            main.highlightEligibleSettlements = true;
                            main.canSelectCards = false;
                        }
                        else {
                            main.guide = "Insufficient resources for settlement";
                        }
                    }
                    else if (clickedCard == 2) {
                        if (main.players.get(main.turn).hasResourcesForCity()) {
                            main.buildingCity = true;
                            main.highlightEligibleCities = true;
                            main.canSelectCards = false;
                        }
                        else {
                            main.guide = "Insufficient resources for city";
                        }
                    }
                    else {
                        if (main.players.get(main.turn).hasResourcesForDevelopmentCard()) {
                            main.players.get(main.turn).buyDevelopmentCard();
                        }
                        else {
                            main.guide = "Insufficient resources for development card";
                        }
                    }
                    repaint();
                }
            }
            if (main.canEndTurn) {
                if (x > 240 && x < 300 && y > 470 && y < 500) {
                    main.endTurn();
                    out.println("turn ended");
                    repaint();
                }
            }
        }

        else if (main.buildingSettlement) {
            int closestInterx = ((int) ((x - 390 + w / 2) / w)) * w + 390;
            int closestIntery = ((int) ((y - 30 + h / 2) / h)) * h + 30;
            int closestInterBoardx = ((int) ((x - 390 + w / 2) / w));
            int closestInterBoardy = ((int) ((y - 30 + h / 2) / h));

            if (Math.pow(closestInterx - x, 2) + Math.pow(closestIntery - y, 2) < 900 && main.inter[closestInterBoardx][closestInterBoardy] != null) {
                if (main.inter[closestInterBoardx][closestInterBoardy].isOpen()) {
                    main.buildSettlement(main.players.get(main.turn), closestInterBoardx, closestInterBoardy);
                    repaint();
                }
            }
            main.canSelectCards = true;
        }

        else if (main.buildingCity) {
            int closestInterx = ((int) ((x - 390 + w / 2) / w)) * w + 390;
            int closestIntery = ((int) ((y - 30 + h / 2) / h)) * h + 30;
            int closestInterBoardx = ((int) ((x - 390 + w / 2) / w));
            int closestInterBoardy = ((int) ((y - 30 + h / 2) / h));

            if (Math.pow(closestInterx - x, 2) + Math.pow(closestIntery - y, 2) < 900 && main.inter[closestInterBoardx][closestInterBoardy] != null) {
                if (main.inter[closestInterBoardx][closestInterBoardy].getSettlement() != null && main.inter[closestInterBoardx][closestInterBoardy].getSettlement().getTier() == 1) {
                    main.buildCity(main.players.get(main.turn), closestInterBoardx, closestInterBoardy);
                    repaint();
                }
            }
            main.canSelectCards = true;
        }

        else if (main.buildingRoad) {

            int ygroup = (y + h / 2 + 30) / (3 * h);
            int closestIntery = (y + h / 2 + 30) / (3 * h) * (3 * h) - h + 30;
            int closestInterBoardx;
            int closestInterx;

            if (Math.abs(y - closestIntery) < 20) {
                closestInterBoardx = (x + w / 2 - 390) / w;
                closestInterx = (x + w / 2 - 390) / w * w + 390;
                out.println(closestInterx + " " + closestInterBoardx);

                if (main.inter[closestInterBoardx][3 * ygroup] != null) {
                    out.println(Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2));
                    if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 400 && main.inter[closestInterBoardx][3 * ygroup].getMiddleRoad() == null) {
                        main.buildRoad(main.players.get(main.turn), closestInterBoardx, 3 * ygroup, 2);
                        repaint();
                    }
                }
            }
            else {
                ygroup = y / (3 * h);
                int closestInterBoardy = ygroup * 3;
                closestIntery = ygroup * (3 * h) + h / 2 + 30;
                closestInterBoardx = (x - 390) / w;
                closestInterx = closestInterBoardx * w + w/2 + 390;

                if (main.inter[closestInterBoardx][closestInterBoardy] != null && main.inter[closestInterBoardx + 1][closestInterBoardy + 1] != null) {
                    if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 400) {
                        main.buildRoad(main.players.get(main.turn), closestInterBoardx, closestInterBoardy, 1);
                        repaint();
                    }
                }
                else if (main.inter[closestInterBoardx][closestInterBoardy + 1] != null && main.inter[closestInterBoardx + 1][closestInterBoardy] != null) {
                    if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 400) {
                        main.buildRoad(main.players.get(main.turn), closestInterBoardx, closestInterBoardy + 1, 1);
                        repaint();
                    }
                }
            }
            main.canSelectCards = true;
        }

    }
}