import java.awt.*;
import java.util.*;

import static java.lang.System.*;

public class main {

    static Tile[][] board = new Tile[11][17];
    static Intersection[][] inter = new Intersection[11][17];
    static ArrayList<Port> ports = new ArrayList<Port>();

    static HashMap<ResourceCard, Integer> bank = new HashMap<ResourceCard, Integer>();

    static ArrayList<Player> players = new ArrayList<Player>();
    static int turn = 0;
    static int[] startingTurnOrder = {0, 1, 2, 3, 3, 2, 1, 0};

    static int robberx, robbery;
    static int dice1, dice2;

    //gamestates
    static boolean
            startingSetup,
            movingRobber,
            buildingSettlement,
            buildingCity,
            buildingRoad,
            canRollDie,
            canEndTurn;

    //for highlighting when building
    static boolean
            highlightEligibleSettlements,
            highlightEligibleCities,
            highlightEligibleRoads;

    public static void main(String[] args) {


        createBoard();
        fillBank();
        //tempdisplayshittyboard();

        players.add(new Player(new Color(246, 149, 60)));
        players.add(new Player(new Color(255, 255, 255)));
        players.add(new Player(new Color(0, 160, 224)));
        players.add(new Player(new Color(239, 65, 64)));


        startingSetup = true;
        //for settler 1
        buildingSettlement = true;
        highlightEligibleSettlements = true;
        //

        rollDie();
        canRollDie = true;

        mainFrame main = new mainFrame("Catan");






        boolean running = false;

        while (running) {




            turn = (turn + 1) % 4;
        }




    }






    public static int rollDie() {
        dice1 = (int) (6 * Math.random() + 1);
        dice2 = (int) (6 * Math.random() + 1);
        return dice1 + dice2;
    }

    public static void distributeResources(int roll) {
        int r, c;
        for (int i = 2; i < 43; i += 2) {
            c = 3 * (i / 9) + 2;
            r = i % 9 + 1;
            if (board[r][c] != null && board[r][c].getPipNumber() == roll)
                board[r][c].distributeResources();
        }
    }

    public static void tradePlayers(Player a, Player b, ArrayList<ResourceCard> aOffer, ArrayList<ResourceCard> bOffer) {
        for (ResourceCard rc: aOffer) {
            a.removeResourceCard(rc, 1);
            b.addResourceCard(rc, 1);
        }
        for (ResourceCard rc: bOffer) {
            b.removeResourceCard(rc, 1);
            a.addResourceCard(rc, 1);
        }
    }

    public static void tradeBank(Player p, ArrayList<ResourceCard> offer, ResourceCard receipt) {
        ResourceCard type = offer.get(0);
        int count = 0;
        for (ResourceCard rc: offer) {
            if (rc.equals(type)) {
                count++;
            }
        }
        for (Port port: ports) {
            if (port.getIntersection().getSettlement().getOwner().equals(p)) {
                if (port.getSpecialty().equals(type) && count > 1) {
                    p.removeResourceCard(type, 2);
                    return;
                }
                else if (port.getSpecialty() == null && count > 2) {
                    p.removeResourceCard(type, 3);
                    return;
                }
            }
        }
        if (count > 3)
            p.removeResourceCard(type, 4);
    }

    //(1) check resources, (2) check roads, (3) displaying it
    //tier = 0 means road, tier = 1 means settlement, tier = 2 means city
    public static boolean checkResources(Player p, int tier) {
        int BrickCount = p.countResources(ResourceCard.BRICK);
        int WoodCount = p.countResources(ResourceCard.WOOD);
        int SheepCount = p.countResources(ResourceCard.SHEEP);
        int WheatCount = p.countResources(ResourceCard.WHEAT);
        int OreCount = p.countResources(ResourceCard.ORE);
        //check roads
        if(tier == 0) {
            if(BrickCount>=1 && WoodCount>=1) { return true; } else { return false; }
        }
        //check settlement
        if(tier == 0) {
            if(BrickCount>=1 && WoodCount>=1 && SheepCount>=1 && WheatCount>=1) { return true; } else { return false; }
        }
        //check city
        if(tier == 0) {
            if(OreCount >= 3 && WheatCount>=2) { return true; } else { return false; }
        }
        return false;
    }

    public static boolean checkRoads(Player p, int x, int y) {

        return false;
    }

    public static void buildSettlement(Player p, int x, int y) {

    }

    public static void buildCity(Player p, int x, int y) {

    }

    public static void buildRoad(Player p, int x1, int y1, int x2, int y2) {

    }


    public static void moveRobber(Player p, int x, int y) {

    }

    public static void steal(Player stealer, Player victim) {
        stealer.addResourceCard(victim.resourceHand.remove((int) (Math.random() * victim.resourceHand.size())), 1);
    }

    public static void halveResources() {

    }

    public static void developmentCardFunction(Player p, String type) {

    }

    public static void longestRoad() {

    }

    public static void largestArmy() {

    }

    public static void tempdisplayshittyboard() {
        Tile temp;
        for (int y = 0; y < 17; y++) {
            for (int x = 0; x < 11; x++) {
                temp = board[x][y];
                if (temp == null) {
                    out.print(" •• ");
                }
                else {
                    try {
                        if (temp.getPipNumber() / 10 == 1) {
                            out.print(temp.getResourceType().toString().substring(0, 1) + " " + temp.getPipNumber());
                        }
                        else {
                            out.print(temp.getResourceType().toString().substring(0, 1) + " 0" + temp.getPipNumber());
                        }
                    }
                    catch (Exception e) {
                        out.print("D 00");
                    }
                }
            }
            out.println("");
        }

        out.println("\n");

        Intersection itemp;
        for (int y = 0; y < 17; y++) {
            for (int x = 0; x < 11; x++) {
                itemp = inter[x][y];
                if (itemp == null) {
                    out.print(" •• ");
                }
                else {
                    out.print(" II ");
                }
            }
            out.println("");
        }
    }

    public static void createBoard() {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < 4; i++) {
            tiles.add(new Tile(ResourceCard.WOOD));
            tiles.add(new Tile(ResourceCard.WHEAT));
            tiles.add(new Tile(ResourceCard.SHEEP));
            tiles.add(new Tile(ResourceCard.ORE));
            tiles.add(new Tile(ResourceCard.BRICK));
        }
        tiles.remove(tiles.size() - 1);
        tiles.remove(tiles.size() - 1);
        tiles.add(new Tile());

        Collections.shuffle(tiles);

        int[] pipOrder = {5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11};
        int p = 0;
        for (Tile t: tiles){
            if (t.getResourceType() != null) {
                t.setPipNumber(pipOrder[p]);
                p++;
            }
        }

        for (Tile t: tiles) {
            out.println("" + t.getResourceType() + " \t" + t.getPipNumber());
        }

        board[3][2] = tiles.remove(0);
        board[2][5] = tiles.remove(0);
        board[1][8] = tiles.remove(0);
        board[2][11] = tiles.remove(0);
        board[3][14] = tiles.remove(0);
        board[5][14] = tiles.remove(0);
        board[7][14] = tiles.remove(0);
        board[8][11] = tiles.remove(0);
        board[9][8] = tiles.remove(0);
        board[8][5] = tiles.remove(0);
        board[7][2] = tiles.remove(0);
        board[5][2] = tiles.remove(0);
        board[4][5] = tiles.remove(0);
        board[3][8] = tiles.remove(0);
        board[4][11] = tiles.remove(0);
        board[6][11] = tiles.remove(0);
        board[7][8] = tiles.remove(0);
        board[6][5] = tiles.remove(0);
        board[5][8] = tiles.remove(0);

        int x, y;
        for (int i = 2; i < 43; i += 2) {
            y = 3 * (i / 9) + 2;
            x = i % 9 + 1;
            if (board[x][y] != null) {
                board[x][y].setCoords(x, y);
                if (board[x][y].getPipNumber() == 0) {
                    robberx = x;
                    robbery = y;
                }
            }
        }

        for (int i = 2; i < 87; i += 2) {
            y = 2 * (i / 10);
            x = i % 10 + 1;
            if (y % 3 != 2)
                inter[x][y] = new Intersection(x, y);
            inter[9][0] = null;
            inter[1][16] = null;
        }
        for (int i = 0; i < 63; i+= 2) {
            y = 2 * (i / 8) + 1;
            x = i % 8 + 2;
            if (y % 3 != 2)
                inter[x][y] = new Intersection(x, y);
        }
        inter[0][7] = new Intersection(0, 7);
        inter[0][9] = new Intersection(0, 9);
        inter[10][7] = new Intersection(10, 7);
        inter[10][9] = new Intersection(10, 9);



        ports.add(new Port(null, 3));
        ports.add(new Port(null, 3));
        ports.add(new Port(null, 3));
        ports.add(new Port(null, 3));
        ports.add(new Port(ResourceCard.BRICK, 2));
        ports.add(new Port(ResourceCard.ORE, 2));
        ports.add(new Port(ResourceCard.SHEEP, 2));
        ports.add(new Port(ResourceCard.WHEAT, 2));
        ports.add(new Port(ResourceCard.WOOD, 2));
        Collections.shuffle(ports);

        ports.get(0).setCoords(2, 1, 3, 0);
        ports.get(0).setCoords(2, 1, 3, 0);
        ports.get(0).setCoords(2, 1, 3, 0);
        ports.get(0).setCoords(2, 1, 3, 0);
        ports.get(0).setCoords(2, 1, 3, 0);
        ports.get(0).setCoords(2, 1, 3, 0);
        ports.get(0).setCoords(2, 1, 3, 0);
        ports.get(0).setCoords(2, 1, 3, 0);



    }

    public static void fillBank() {
        bank.put(ResourceCard.BRICK, 19);
        bank.put(ResourceCard.ORE, 19);
        bank.put(ResourceCard.SHEEP, 19);
        bank.put(ResourceCard.WHEAT, 19);
        bank.put(ResourceCard.WOOD, 19);
    }

    public static void removeFromBank(ResourceCard type, int count) {
        bank.replace(type, bank.get(type) - count);
    }

    public static void addToBank(ResourceCard type, int count) {
        bank.replace(type, bank.get(type) + count);
    }

}
