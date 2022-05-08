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
            knight, victorypoint, monopoly, roadbuilding, yearofplenty,
            developmentCosts, background, robber, startpanel;
    private BufferedImage brickicon, oreicon, sheepicon, wheaticon, woodicon;

    private BufferedImage[] pips = new BufferedImage[13];
    private BufferedImage[] dicepips = new BufferedImage[7];

    final int HEIGHT = 810;
    final int marg = 90;
    private int h = (HEIGHT - marg) / 16;
    private int w = (int) ((HEIGHT - marg) * 1.0825 / 10);

    //index i for general usage
    int i = 0;

    int halvingIndex = 0;

    public mainPanel()
    {
        try
        {
            startpanel = ImageIO.read(new File("StartPanel.png"));

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

            dicepips[0] = null;
            dicepips[1] = ImageIO.read(new File("dice1pip.png"));
            dicepips[2] = ImageIO.read(new File("dice2pip.png"));
            dicepips[3] = ImageIO.read(new File("dice3pip.png"));
            dicepips[4] = ImageIO.read(new File("dice4pip.png"));
            dicepips[5] = ImageIO.read(new File("dice5pip.png"));
            dicepips[6] = ImageIO.read(new File("dice6pip.png"));

            background = ImageIO.read(new File("board background.png"));

            clayCard = ImageIO.read(new File("Clay Card.png"));
            wheatCard = ImageIO.read(new File("Wheat Card.png"));
            woodCard = ImageIO.read(new File("Wood Card.png"));
            oreCard = ImageIO.read(new File("Ore Card.png"));
            sheepCard = ImageIO.read(new File("Sheep Card.png"));
            cardBack = ImageIO.read(new File("Development Card.png"));
            developmentCosts = ImageIO.read(new File("developmentcosts.png"));

            knight = ImageIO.read(new File("Knight Card.png"));
            victorypoint = ImageIO.read(new File("Victory Point Card.png"));
            monopoly = ImageIO.read(new File("Monopoly.png"));
            roadbuilding = ImageIO.read(new File("Road Building.png"));
            yearofplenty = ImageIO.read(new File("Year of Plenty.png"));

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
        if (main.startingScreen) {

        }
        else {
            g.setColor(new Color(191, 191, 191));
            g.fillRect(0, 0, 1440, 810);

            int cardx;
            for (int i = 0; i < 4; i++) {
                g.setFont(new Font("Times New Roman", Font.PLAIN, 60));
                g.setColor(main.players[i].getColor());
                g.drawString("" + (i + 1), 45, 120 + i * 120);

                g.setColor(Color.darkGray);
                if (i == main.turn) {
                    g.fillPolygon(new int[]{8, 8, 38}, new int[]{75 + i * 120, 90 + i * 120, 83 + i * 120}, 3);
                }
                g.setColor(Color.gray);
                g.fillRect(8, 98 + i * 120, 30, 30);

                g.setFont(new Font("Times New Roman", Font.PLAIN, 15));
                g.setColor(Color.black);
                cardx = 0;
                if (main.displayHands[i]) {
                    BufferedImage img = null;
                    for (ResourceCard rc : main.players[i].resourceHand) {
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

                            if (!main.players[i].selectedResources[cardx])
                                g.drawImage(img, cardx * 23 + 75, i * 120 + 60, 53, 78, null);
                            else
                                g.drawImage(img, cardx * 23 + 75, i * 120 + 45, 53, 78, null);
                            cardx++;
                        }
                    }
                    cardx = 0;
                    for (DevelopmentCard dc : main.players[i].developmentCardHand) {
                        if (dc != null) {
                            if (dc == DevelopmentCard.KNIGHT)
                                img = knight;
                            else if (dc == DevelopmentCard.VICTORYPOINT)
                                img = victorypoint;
                            else if (dc == DevelopmentCard.MONOPOLY)
                                img = monopoly;
                            else if (dc == DevelopmentCard.ROADBUILDING)
                                img = roadbuilding;
                            else if (dc == DevelopmentCard.YEAROFPLENTY)
                                img = yearofplenty;

                            if (main.players[i].selectedDevelopmentCard == cardx)
                                g.drawImage(img, cardx * 23 + (473 - main.players[i].developmentCardHand.size() * 23), i * 120 + 45, 53, 78, null);
                            else
                                g.drawImage(img, cardx * 23 + (473 - main.players[i].developmentCardHand.size() * 23), i * 120 + 60, 53, 78, null);
                            cardx++;
                        }
                    }
                    g.drawString("VP: " + main.players[i].visibleVictoryPoints + " (+" + main.players[i].hiddenVictoryPoints + ")", 45, i * 120 + 150);
                } else {
                    for (ResourceCard rc : main.players[i].resourceHand) {
                        if (rc != null) {
                            if (!main.players[i].selectedResources[cardx])
                                g.drawImage(cardBack, cardx * 23 + 75, i * 120 + 60, 53, 78, null);
                            else
                                g.drawImage(cardBack, cardx * 23 + 75, i * 120 + 45, 53, 78, null);
                            cardx++;
                        }
                    }
                    cardx = 0;
                    for (DevelopmentCard dc : main.players[i].developmentCardHand) {
                        if (dc != null) {
                            if (main.players[i].selectedDevelopmentCard == cardx)
                                g.drawImage(cardBack, cardx * 23 + (473 - main.players[i].developmentCardHand.size() * 23), i * 120 + 45, 53, 78, null);
                            else
                                g.drawImage(cardBack, cardx * 23 + (473 - main.players[i].developmentCardHand.size() * 23), i * 120 + 60, 53, 78, null);
                            cardx++;
                        }
                    }
                    g.drawString("VP: " + main.players[i].visibleVictoryPoints, 45, i * 120 + 150);
                }
                if (main.players[i].longestRoad)
                    g.setColor(new Color(255, 203, 67));
                g.drawString("LR: " + main.players[i].longestRoadLength, 165, i * 120 + 150);
                g.setColor(Color.black);
                if (main.players[i].largestArmy)
                    g.setColor(new Color(255, 203, 67));
                g.drawString("LA: " + main.players[i].knightsPlayed, 285, i * 120 + 150);
            }

            //dice
            g.drawImage(dicepips[main.dice1], 345, 540, 75, 75, null);
            g.drawImage(dicepips[main.dice2], 425, 540, 75, 75, null);
            if (main.canRollDie)
                main.guide = "Roll the die!";

            //trade button
            g.setColor(Color.red);
            g.fillRect(360, 645, 140, 45);

            //display bank
            g.setColor(Color.black);
            Font bankFont = new Font("Times New Roman", Font.PLAIN, 15);
            g.setFont(bankFont);
            FontMetrics bankFontMetrics = getFontMetrics(bankFont);
            g.drawImage(clayCard, 45, 540, 53, 78, null);
            g.drawString("" + main.bank.get(ResourceCard.BRICK), 70 - bankFontMetrics.stringWidth("" + main.bank.get(ResourceCard.BRICK)) / 2, 630);
            g.drawImage(oreCard, 105, 540, 53, 78, null);
            g.drawString("" + main.bank.get(ResourceCard.ORE), 130 - bankFontMetrics.stringWidth("" + main.bank.get(ResourceCard.ORE)) / 2, 630);
            g.drawImage(sheepCard, 165, 540, 53, 78, null);
            g.drawString("" + main.bank.get(ResourceCard.SHEEP), 190 - bankFontMetrics.stringWidth("" + main.bank.get(ResourceCard.SHEEP)) / 2, 630);
            g.drawImage(wheatCard, 225, 540, 53, 78, null);
            g.drawString("" + main.bank.get(ResourceCard.WHEAT), 250 - bankFontMetrics.stringWidth("" + main.bank.get(ResourceCard.WHEAT)) / 2, 630);
            g.drawImage(woodCard, 285, 540, 53, 78, null);
            g.drawString("" + main.bank.get(ResourceCard.WOOD), 310 - bankFontMetrics.stringWidth("" + main.bank.get(ResourceCard.WOOD)) / 2, 630);

            //end turn button
            g.setColor(Color.cyan);
            g.fillRect(360, 705, 140, 45);

            g.drawImage(developmentCosts, 45, 645, 300, 150, null);

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

            g.drawImage(background, 542, 0, 900, 810, null);

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

                    g.drawImage(img, x * w - w + 608, y * h - 2 * h + 45, 2 * w, 4 * h, null);

                    pip = temp.getPipNumber();
                    g.drawImage(pips[pip], x * w + 578, y * h + 15, 60, 60, null);
                }
            }

            g.drawImage(robber, main.robberx * w + 578, main.robbery * h + 15, 60, 60, null);

            int portpos[] = {
                    773, 30,
                    1050, 30,
                    1287, 165,
                    1418, 405,
                    1287, 645,
                    1050, 780,
                    773, 780,
                    638, 540,
                    638, 270};
            int portposI = 0;
            int portx, porty;
            Font portFont = new Font("Times New Roman", 0, 30);
            g.setFont(portFont);
            FontMetrics portFontMetrics = getFontMetrics(portFont);
            int portFontWidth;
            Color portBrown = new Color(118, 80, 6);
            g.setColor(Color.white);

            //to display ports
            for (Port p : main.ports) {
                portx = portpos[portposI++];
                porty = portpos[portposI++];
                if (p.getSpecialty() == null) {
                    portFontWidth = portFontMetrics.stringWidth("3");
                    g.setColor(portBrown);
                    g.fillOval(portx - 15, porty - 15, 30, 30);
                    g.setColor(Color.white);
                    g.drawString("3", portx - portFontWidth / 2, porty + 8);
                } else {
                    portFontWidth = portFontMetrics.stringWidth("2");
                    if (p.getSpecialty().equals(ResourceCard.BRICK))
                        g.drawImage(brickicon, portx - 15, porty - 15, 30, 30, null);
                    else if (p.getSpecialty().equals(ResourceCard.ORE))
                        g.drawImage(oreicon, portx - 15, porty - 15, 30, 30, null);
                    else if (p.getSpecialty().equals(ResourceCard.SHEEP))
                        g.drawImage(sheepicon, portx - 15, porty - 15, 30, 30, null);
                    else if (p.getSpecialty().equals(ResourceCard.WHEAT))
                        g.drawImage(wheaticon, portx - 15, porty - 15, 30, 30, null);
                    else if (p.getSpecialty().equals(ResourceCard.WOOD))
                        g.drawImage(woodicon, portx - 15, porty - 15, 30, 30, null);
                    g.drawString("2", portx - portFontWidth / 2, porty + 10);
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
                            g.fillOval(r1 * w + 593, c1 * h + 30, 30, 30);
                        } else
                            g.fillRect(r1 * w + 593, c1 * h + 30, 30, 30);
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
            g.setColor(new Color(85, 255, 59, 172));
            if (main.highlightEligibleSettlements) {
                if (main.startingSetup) {
                    for (int i = 3; i < 184; i += 2) {
                        r1 = i % 11;
                        c1 = i / 11;
                        if (c1 % 3 != 2 && main.inter[r1][c1] != null) {
                            if (main.inter[r1][c1].isOpen()) {
                                g.fillOval(r1 * w + 593, c1 * h + 30, 30, 30);
                            }
                        }
                    }
                    main.guide = "Player " + (main.turn + 1) + ": choose a location to build a settlement";
                } else {
                    for (Settlement s : main.players[main.turn].eligibleSettlements) {
                        g.fillOval(s.row * w + 593, s.col * h + 30, 30, 30);
                    }
                }
            } else if (main.highlightEligibleCities) {

                for (Settlement s : main.players[main.turn].settlements) {
                    g.fillRect(s.row * w + 593, s.col * h + 30, 30, 30);
                }

            } else if (main.highlightEligibleRoads || main.roadbuildinging || main.roadbuildinging2) {
                for (Road r : main.players[main.turn].eligibleRoads) {
                    g.fillPolygon(r.p);
                }
                main.guide = "Player " + (main.turn + 1) + ": choose a location to build a road";
            }

            //print guide
            if (!main.gameEnded) {
                g.setColor(Color.black);
                g.setFont(new Font("Times New Roman", Font.PLAIN, 15));
                g.drawString(main.guide, 5, 15);
            } else {
                g.setColor(new Color(63, 63, 63, 191));
                g.fillRect(0, 0, 1440, 810);
                Font endFont = new Font("Times New Roman", Font.PLAIN, 100);
                g.setFont(endFont);
                FontMetrics endFontMetrics = getFontMetrics(endFont);
                g.drawString("Player " + main.winner + " wins!", 720 - endFontMetrics.stringWidth("Player " + main.winner + " wins!"), 455);
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

        out.println("" + x + " " + y);

        if (!main.gameEnded) {
            for (int i = 0; i < 4; i++) {
                if (x > 8 && x < 38 && y > 98 + i * 120 && y < 128 + i * 120) {
                    if (main.displayHands[i]) {
                        main.displayHands[i] = false;
                    } else {
                        main.displayHands[i] = true;
                    }
                    repaint();
                    break;
                }
            }

            if (main.playingdevelopmentcard) {
                if (main.knighting) {
                    int closestTilex = ((int) ((x - 608 + w / 2) / w - 1)) * w + w + 608;
                    int closestTiley = ((int) ((y - 45) / (3 * h))) * 3 * h + 2 * h + 45;
                    int closestTileBoardx = (int) ((x - 608 + w / 2) / w - 1) + 1;
                    int closestTileBoardy = ((y - 45) / (3 * h)) * 3 + 2;

                    if (Math.pow(closestTilex - x, 2) + Math.pow(closestTiley - y, 2) < 8100 && main.board[closestTileBoardx][closestTileBoardy] != null) {
                        if (main.board[closestTileBoardx][closestTileBoardy].getPipNumber() != 0) {
                            main.moveRobber(closestTileBoardx, closestTileBoardy);
                            out.println("success " + main.robberx + " " + main.robbery);
                            main.players[main.turn].removeDevelopmentCard();
                            main.players[main.turn].knightsPlayed++;
                            repaint();
                        }
                    }
                    main.knighting = false;
                }
                else if (main.monopolying) {
                    if (x > 45 && x < 345 && y > 540 && y < 618) {
                        int clickedCard = (x - 45) / 60;
                        out.println("maritime trade called");
                        out.println(clickedCard);
                        if (x - (clickedCard * 60 + 45) < 53) {
                            main.monopoly(main.players[main.turn], main.rco[clickedCard]);
                            main.players[main.turn].removeDevelopmentCard();
                            repaint();
                        }
                    }
                    main.monopolying = false;
                }
                else if (main.roadbuildinging) {
                    int ygroup = (y + h / 2 + 45) / (3 * h);
                    int closestIntery = (y + h / 2 + 45) / (3 * h) * (3 * h) - h + 45;
                    int closestInterBoardx;
                    int closestInterx;

                    if (Math.abs(y - closestIntery) < 30) {
                        closestInterBoardx = (x + w / 2 - 608) / w;
                        closestInterx = (x + w / 2 - 608) / w * w + 608;
                        out.println(closestInterx + " " + closestInterBoardx);

                        if (main.inter[closestInterBoardx][3 * ygroup] != null) {
                            out.println(Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2));
                            if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 900 && main.inter[closestInterBoardx][3 * ygroup].getMiddleRoad() == null) {
                                main.buildRoad(main.players[main.turn], closestInterBoardx, 3 * ygroup, 2, true);
                                main.roadbuildinging2 = true;
                                repaint();
                                main.players[main.turn].removeDevelopmentCard();
                            }
                        }
                    } else {
                        ygroup = y / (3 * h);
                        int closestInterBoardy = ygroup * 3;
                        closestIntery = ygroup * (3 * h) + h / 2 + 45;
                        closestInterBoardx = (x - 608) / w;
                        closestInterx = closestInterBoardx * w + w / 2 + 608;

                        if (main.inter[closestInterBoardx][closestInterBoardy] != null && main.inter[closestInterBoardx + 1][closestInterBoardy + 1] != null) {
                            if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 900) {
                                main.buildRoad(main.players[main.turn], closestInterBoardx, closestInterBoardy, 1, true);
                                main.roadbuildinging2 = true;
                                repaint();
                                main.players[main.turn].removeDevelopmentCard();
                            }
                        } else if (main.inter[closestInterBoardx][closestInterBoardy + 1] != null && main.inter[closestInterBoardx + 1][closestInterBoardy] != null) {
                            if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 900) {
                                main.buildRoad(main.players[main.turn], closestInterBoardx, closestInterBoardy + 1, 1, true);
                                main.roadbuildinging2 = true;
                                repaint();
                                main.players[main.turn].removeDevelopmentCard();
                            }
                        }
                    }
                    main.roadbuildinging = false;
                }
                else if (main.yearofplentying) {
                    if (x > 45 && x < 345 && y > 540 && y < 618) {
                        int clickedCard = (x - 45) / 60;
                        out.println("maritime trade called");
                        out.println(clickedCard);
                        if (x - (clickedCard * 60 + 45) < 53) {
                            main.players[main.turn].addResourceCard(main.rco[clickedCard], 1);
                            main.removeFromBank(main.rco[clickedCard], 1);
                            main.yearofplentying2 = true;
                            repaint();
                            main.players[main.turn].removeDevelopmentCard();
                        }
                    }
                    main.yearofplentying = false;
                }
                main.players[main.turn].selectedDevelopmentCard = -1;
                main.playingdevelopmentcard = false;
            }

            else if (main.roadbuildinging2) {
                int ygroup = (y + h / 2 + 45) / (3 * h);
                int closestIntery = (y + h / 2 + 45) / (3 * h) * (3 * h) - h + 45;
                int closestInterBoardx;
                int closestInterx;

                if (Math.abs(y - closestIntery) < 30) {
                    closestInterBoardx = (x + w / 2 - 608) / w;
                    closestInterx = (x + w / 2 - 608) / w * w + 608;
                    out.println(closestInterx + " " + closestInterBoardx);

                    if (main.inter[closestInterBoardx][3 * ygroup] != null) {
                        out.println(Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2));
                        if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 900 && main.inter[closestInterBoardx][3 * ygroup].getMiddleRoad() == null) {
                            main.buildRoad(main.players[main.turn], closestInterBoardx, 3 * ygroup, 2, true);
                            main.roadbuildinging2 = false;
                            main.canSelectCards = true;
                            repaint();
                        }
                    }
                } else {
                    ygroup = y / (3 * h);
                    int closestInterBoardy = ygroup * 3;
                    closestIntery = ygroup * (3 * h) + h / 2 + 45;
                    closestInterBoardx = (x - 608) / w;
                    closestInterx = closestInterBoardx * w + w / 2 + 608;

                    if (main.inter[closestInterBoardx][closestInterBoardy] != null && main.inter[closestInterBoardx + 1][closestInterBoardy + 1] != null) {
                        if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 900) {
                            main.buildRoad(main.players[main.turn], closestInterBoardx, closestInterBoardy, 1, true);
                            main.roadbuildinging2 = false;
                            main.canSelectCards = true;
                            repaint();
                        }
                    } else if (main.inter[closestInterBoardx][closestInterBoardy + 1] != null && main.inter[closestInterBoardx + 1][closestInterBoardy] != null) {
                        if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 900) {
                            main.buildRoad(main.players[main.turn], closestInterBoardx, closestInterBoardy + 1, 1, true);
                            main.roadbuildinging2 = false;
                            main.canSelectCards = true;
                            repaint();
                        }
                    }
                }
            }

            else if (main.yearofplentying2) {
                if (x > 45 && x < 345 && y > 540 && y < 618) {
                    int clickedCard = (x - 45) / 60;
                    out.println("maritime trade called");
                    out.println(clickedCard);
                    if (x - (clickedCard * 60 + 45) < 53) {
                        main.players[main.turn].addResourceCard(main.rco[clickedCard], 1);
                        main.removeFromBank(main.rco[clickedCard], 1);
                        main.yearofplentying2 = false;
                        main.canSelectCards = true;
                        repaint();
                    }
                }
            }

            else if (main.startingSetup) {
                try {
                    main.turn = main.startingTurnOrder[i];

                    if (main.buildingSettlement) {
                        int closestInterx = ((int) ((x - 608 + w / 2) / w)) * w + 608;
                        int closestIntery = ((int) ((y - 45 + h / 2) / h)) * h + 45;
                        int closestInterBoardx = ((int) ((x - 608 + w / 2) / w));
                        int closestInterBoardy = ((int) ((y - 45 + h / 2) / h));

                        if (Math.pow(closestInterx - x, 2) + Math.pow(closestIntery - y, 2) < 225 && main.inter[closestInterBoardx][closestInterBoardy] != null) {
                            if (main.inter[closestInterBoardx][closestInterBoardy].isOpen()) {
                                main.inter[closestInterBoardx][closestInterBoardy].addSettlement(new Settlement(main.players[main.turn], closestInterBoardx, closestInterBoardy));
                                main.players[main.turn].updateStartingEligibleRoads(closestInterBoardx, closestInterBoardy);
                                main.highlightEligibleSettlements = false;
                                main.buildingSettlement = false;
                                main.highlightEligibleRoads = true;
                                main.buildingRoad = true;
                                main.players[main.turn].updateVisibleVictoryPoints();
                                if (i > 3) {
                                    main.inter[closestInterBoardx][closestInterBoardy].obtainStartingResources();
                                }
                                repaint();
                            }
                        }
                    } else if (main.buildingRoad) {

                        int ygroup = (y + h / 2 + 45) / (3 * h);
                        int closestIntery = (y + h / 2 + 45) / (3 * h) * (3 * h) - h + 45;
                        int closestInterBoardx;
                        int closestInterx;

                        if (Math.abs(y - closestIntery) < 30) {
                            closestInterBoardx = (x + w / 2 - 608) / w;
                            closestInterx = (x + w / 2 - 608) / w * w + 608;
                            out.println(closestInterx + " " + closestInterBoardx);

                            if (main.inter[closestInterBoardx][3 * ygroup] != null) {
                                out.println(Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2));
                                if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 225 &&
                                        main.inter[closestInterBoardx][3 * ygroup].getMiddleRoad() == null &&
                                        main.players[main.turn].canBuildRoad(closestInterBoardx, 3 * ygroup, 2)) {
                                    main.inter[closestInterBoardx][3 * ygroup].buildMiddleRoad(main.players[main.turn]);
                                    main.highlightEligibleRoads = false;
                                    main.buildingRoad = false;
                                    main.highlightEligibleSettlements = true;
                                    main.buildingSettlement = true;
                                    //main.players[main.turn].updateLongestRoad();
                                    i++;
                                    repaint();
                                }
                            }
                        } else {
                            ygroup = y / (3 * h);
                            int closestInterBoardy = ygroup * 3;
                            closestIntery = ygroup * (3 * h) + h / 2 + 45;
                            closestInterBoardx = (x - 608) / w;
                            closestInterx = closestInterBoardx * w + w / 2 + 608;

                            if (main.inter[closestInterBoardx][closestInterBoardy] != null && main.inter[closestInterBoardx + 1][closestInterBoardy + 1] != null) {
                                if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 225 &&
                                        main.players[main.turn].canBuildRoad(closestInterBoardx, closestInterBoardy, 1)) {
                                    main.inter[closestInterBoardx][closestInterBoardy].buildRightRoad(main.players[main.turn]);
                                    main.highlightEligibleRoads = false;
                                    main.buildingRoad = false;
                                    main.highlightEligibleSettlements = true;
                                    main.buildingSettlement = true;
                                    //main.players[main.turn].updateLongestRoad();
                                    i++;
                                    repaint();
                                }
                            } else if (main.inter[closestInterBoardx][closestInterBoardy + 1] != null && main.inter[closestInterBoardx + 1][closestInterBoardy] != null) {
                                if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 225 &&
                                        main.players[main.turn].canBuildRoad(closestInterBoardx, closestInterBoardy + 1, 3)) {
                                    main.inter[closestInterBoardx][closestInterBoardy + 1].buildRightRoad(main.players[main.turn]);
                                    main.highlightEligibleRoads = false;
                                    main.buildingRoad = false;
                                    main.highlightEligibleSettlements = true;
                                    main.buildingSettlement = true;
                                    //main.players[main.turn].updateLongestRoad();
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
                        for (Player p : main.players) {
                            p.updateEligibleRoads();
                        }
                    }
                    main.turn = main.startingTurnOrder[i];
                } catch (Exception fuckyoujava) {
                }
            } else if (main.canRollDie) {
                if (x > 345 && x < 503 && y > 540 && y < 615) {
                    main.rollDie();
                    repaint();
                }
            }

            //FUCK YOU KALE FINISH THIS MOTHERFUCKer
            else if (main.halving) {
                for (int j = halvingIndex; j < 4; j++) {
                    if (main.players[j].resourceHand.size() > 7) {
                        halvingIndex = j;
                        main.guide = "Player " + (j + 1) + ": choose " + (main.players[j].resourceHand.size() / 2) + " resource cards to discard";
                        break;
                    }
                }
                if (main.players[halvingIndex].resourceHand.size() > 7) {
                    out.println("halving player " + halvingIndex);
                    int clickedCard = (x - 75) / 23;
                    int numCardsToBeDiscarded = main.players[halvingIndex].resourceHand.size() / 2;
                    if (y - halvingIndex * 120 - 45 < 90) {
                        try {
                            main.players[halvingIndex].selectResourceCard(clickedCard);
                        } catch (Exception fuckoff) {
                            if (clickedCard == main.players[halvingIndex].resourceHand.size())
                                main.players[halvingIndex].selectResourceCard(clickedCard - 1);
                        }
                        if (main.players[halvingIndex].getSelectedCards().size() == numCardsToBeDiscarded) {
                            main.players[halvingIndex].removeSelectedCards();
                            halvingIndex++;
                            out.println("1 halving next plaer");
                            for (int j = halvingIndex; j < 4; j++) {
                                if (main.players[j].resourceHand.size() > 7) {
                                    halvingIndex = j;
                                    main.guide = "Player " + (j + 1) + ": choose " + (main.players[j].resourceHand.size() / 2) + " resource cards to discard";
                                    break;
                                }
                            }
                        }
                        repaint();
                    }
                } else {
                    halvingIndex++;
                    out.println("2 halving next player");
                }
                if (halvingIndex > 3) {
                    main.halving = false;
                    main.movingRobber = true;
                    main.guide = "Choose a tile to move the robber to";
                }
            } else if (main.movingRobber) {
                int closestTilex = ((int) ((x - 608 + w / 2) / w - 1)) * w + w + 608;
                int closestTiley = ((int) ((y - 45) / (3 * h))) * 3 * h + 2 * h + 45;
                int closestTileBoardx = (int) ((x - 608 + w / 2) / w - 1) + 1;
                int closestTileBoardy = ((y - 45) / (3 * h)) * 3 + 2;

                if (Math.pow(closestTilex - x, 2) + Math.pow(closestTiley - y, 2) < 8100 && main.board[closestTileBoardx][closestTileBoardy] != null) {
                    if (main.board[closestTileBoardx][closestTileBoardy].getPipNumber() != 0) {
                        main.moveRobber(closestTileBoardx, closestTileBoardy);
                        out.println("success " + main.robberx + " " + main.robbery);
                        repaint();
                    }
                }
            } else if (main.stealing) {
                int clickedSettler = (y - 45) / 120;
                out.println("stealing clicked on bitchass");
                if (x > 45 && x < 585) {
                    main.steal(main.players[main.turn], main.players[clickedSettler]);
                    repaint();
                }
            } else if (main.canSelectCards) {

                int clickedSettler = (y - 45) / 120;
                int clickedCard = -1;

                if (clickedSettler > 3) {}
                else if (y - clickedSettler * 120 - 45 < 90) {
                    if (x < main.players[clickedSettler].resourceHand.size() * 23 + 130) {
                        clickedCard = (x - 75) / 23;
                        try {
                            main.players[clickedSettler].selectResourceCard(clickedCard);
                        } catch (Exception fuckoff) {
                            if (clickedCard == main.players[clickedSettler].resourceHand.size())
                                main.players[clickedSettler].selectResourceCard(clickedCard - 1);
                        }
                        repaint();
                    }
                    else if (clickedSettler == main.turn) {
                        clickedCard = (x - (473 - main.players[clickedSettler].developmentCardHand.size() * 23)) / 23;
                        try {
                            main.players[clickedSettler].selectDevelopmentCard(clickedCard);
                            out.println("player " + clickedSettler + " selected dev card " + clickedCard);
                        } catch (Exception fuckoff) {
                            if (clickedCard == main.players[clickedSettler].developmentCardHand.size())
                                main.players[clickedSettler].selectDevelopmentCard(clickedCard - 1);
                        }
                        main.checkPlayingDevelopmentCard(main.players[main.turn]);
                        if (main.playingdevelopmentcard)
                            main.developmentCardFunction(main.players[main.turn]);
                        repaint();
                    }
                }


                if (main.tradingBuilding) {

                    if (x > 360 && x < 450 && y > 645 && y < 690) {
                        out.println("domestic trade called");
                        main.domesticTrade();
                        repaint();
                    }

                    if (x > 45 && x < 345 && y > 540 && y < 618) {
                        clickedCard = (x - 45) / 60;
                        out.println("maritime trade called");
                        out.println(clickedCard);
                        if (x - (clickedCard * 60 + 45) < 53) {
                            main.maritimeTrade(main.rco[clickedCard]);
                            repaint();
                        }
                    }

                    if (x > 45 && x < 345 && y > 645 && y < 795) {
                        clickedCard = (x - 45) / 75;
                        out.println("called build");
                        if (clickedCard == 0) {
                            if (main.players[main.turn].hasResourcesForRoad()) {
                                main.buildingRoad = true;
                                main.highlightEligibleRoads = true;
                                main.canSelectCards = false;
                            } else {
                                main.guide = "Insufficient resources for road";
                            }
                        } else if (clickedCard == 1) {
                            if (main.players[main.turn].hasResourcesForSettlement()) {
                                main.buildingSettlement = true;
                                main.highlightEligibleSettlements = true;
                                main.canSelectCards = false;
                            } else {
                                main.guide = "Insufficient resources for settlement";
                            }
                        } else if (clickedCard == 2) {
                            if (main.players[main.turn].hasResourcesForCity()) {
                                main.buildingCity = true;
                                main.highlightEligibleCities = true;
                                main.canSelectCards = false;
                            } else {
                                main.guide = "Insufficient resources for city";
                            }
                        } else {
                            if (main.players[main.turn].hasResourcesForDevelopmentCard()) {
                                main.players[main.turn].buyDevelopmentCard();
                            } else {
                                main.guide = "Insufficient resources for development card";
                            }
                        }
                        repaint();
                    }
                }
                if (main.canEndTurn) {
                    if (x > 360 && x < 450 && y > 705 && y < 750) {
                        main.endTurn();
                        out.println("turn ended");
                        repaint();
                    }
                }
            } else if (main.buildingSettlement) {
                int closestInterx = ((int) ((x - 608 + w / 2) / w)) * w + 608;
                int closestIntery = ((int) ((y - 45 + h / 2) / h)) * h + 45;
                int closestInterBoardx = ((int) ((x - 608 + w / 2) / w));
                int closestInterBoardy = ((int) ((y - 45 + h / 2) / h));

                if (Math.pow(closestInterx - x, 2) + Math.pow(closestIntery - y, 2) < 8100 && main.inter[closestInterBoardx][closestInterBoardy] != null) {
                    if (main.players[main.turn].canBuildSettlement(closestInterBoardx, closestInterBoardy)) {
                        main.buildSettlement(main.players[main.turn], closestInterBoardx, closestInterBoardy);
                        repaint();
                    }
                }
                main.canSelectCards = true;
            } else if (main.buildingCity) {
                int closestInterx = ((int) ((x - 608 + w / 2) / w)) * w + 608;
                int closestIntery = ((int) ((y - 45 + h / 2) / h)) * h + 45;
                int closestInterBoardx = ((int) ((x - 608 + w / 2) / w));
                int closestInterBoardy = ((int) ((y - 45 + h / 2) / h));

                if (Math.pow(closestInterx - x, 2) + Math.pow(closestIntery - y, 2) < 2025 && main.inter[closestInterBoardx][closestInterBoardy] != null) {
                    if (main.inter[closestInterBoardx][closestInterBoardy].getSettlement() != null && main.inter[closestInterBoardx][closestInterBoardy].getSettlement().getTier() == 1) {
                        main.buildCity(main.players[main.turn], closestInterBoardx, closestInterBoardy);
                        repaint();
                    }
                }
                main.canSelectCards = true;
            } else if (main.buildingRoad) {

                int ygroup = (y + h / 2 + 45) / (3 * h);
                int closestIntery = (y + h / 2 + 45) / (3 * h) * (3 * h) - h + 45;
                int closestInterBoardx;
                int closestInterx;

                if (Math.abs(y - closestIntery) < 30) {
                    closestInterBoardx = (x + w / 2 - 608) / w;
                    closestInterx = (x + w / 2 - 608) / w * w + 608;
                    out.println(closestInterx + " " + closestInterBoardx);

                    if (main.inter[closestInterBoardx][3 * ygroup] != null) {
                        out.println(Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2));
                        if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 900 && main.inter[closestInterBoardx][3 * ygroup].getMiddleRoad() == null) {
                            main.buildRoad(main.players[main.turn], closestInterBoardx, 3 * ygroup, 2, false);
                            repaint();
                        }
                    }
                } else {
                    ygroup = y / (3 * h);
                    int closestInterBoardy = ygroup * 3;
                    closestIntery = ygroup * (3 * h) + h / 2 + 45;
                    closestInterBoardx = (x - 608) / w;
                    closestInterx = closestInterBoardx * w + w / 2 + 608;

                    if (main.inter[closestInterBoardx][closestInterBoardy] != null && main.inter[closestInterBoardx + 1][closestInterBoardy + 1] != null) {
                        if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 900) {
                            main.buildRoad(main.players[main.turn], closestInterBoardx, closestInterBoardy, 1, false);
                            repaint();
                        }
                    } else if (main.inter[closestInterBoardx][closestInterBoardy + 1] != null && main.inter[closestInterBoardx + 1][closestInterBoardy] != null) {
                        if (Math.pow(x - closestInterx, 2) + Math.pow(closestIntery - y, 2) < 900) {
                            main.buildRoad(main.players[main.turn], closestInterBoardx, closestInterBoardy + 1, 1, false);
                            repaint();
                        }
                    }
                }
                main.canSelectCards = true;
            }
        }
        repaint();
    }

    public void resetHalvingIndex() {
        halvingIndex = 0;
    }

}