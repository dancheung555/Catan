import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Stack;

import static java.lang.System.*;

public class main {

    static Tile[][] board = new Tile[11][17];
    static ArrayList<Port> ports = new ArrayList<Port>();
    static ArrayList<Player> players = new ArrayList<Player>();



    public static void main(String[] args) {
        mainFrame main = new mainFrame("Catan");

        /* For testing roll die distributions
        int roll = 0;
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int i = 0; i < 10000; i++) {
            roll = rollDie();
            if (!map.containsKey(roll)) {
                map.put(roll, 0);
            }
            map.put(roll, map.get(roll) + 1);
        }

        System.out.println(map);
        */


        int robberx, robbery;


        createBoard();
        tempdisplayshittyboard();




        boolean running = false;

        while (running) {




        }




    }






    public static int rollDie() {
        return (int) (6 * Math.random() + 1) + (int) (6 * Math.random() + 1);
    }

    public static void distributeResources(int roll) {
        int r, c;
        for (int i = 2; i < 43; i += 2) {
            c = 3 * (i / 9) + 2;
            r = i % 9 + 1;
            if (board[r][c].getPipNumber() == roll)
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
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 17; y++) {
                if (board[x][y] == null) {
                    out.print("••");
                }
                else {
                    if (board[x][y].getPipNumber() / 10 == 1) {

                    }
                    else
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

        Stack<Tile> randoTiles = new Stack<Tile>();
        for (int i = 19; i > 0; i--) {
            randoTiles.add(tiles.remove((int) (Math.random() * i)));
        }

        int[] pipOrder = {5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11};
        int p = 0;
        while (p < 18) {
            if (randoTiles.peek().getResourceType() != null) {
                randoTiles.peek().setPipNumber(pipOrder[p]);
                tiles.add(randoTiles.pop());
                p++;
            }
            else
                tiles.add(randoTiles.pop());
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

        ArrayList<Port> toRandomizePorts = new ArrayList<Port>();
        toRandomizePorts.add(new Port(null, 3));
        toRandomizePorts.add(new Port(null, 3));
        toRandomizePorts.add(new Port(null, 3));
        toRandomizePorts.add(new Port(null, 3));
        toRandomizePorts.add(new Port(ResourceCard.BRICK, 2));
        toRandomizePorts.add(new Port(ResourceCard.ORE, 2));
        toRandomizePorts.add(new Port(ResourceCard.SHEEP, 2));
        toRandomizePorts.add(new Port(ResourceCard.WHEAT, 2));
        toRandomizePorts.add(new Port(ResourceCard.WOOD, 2));

        for (int i = 9; i > 0; i--) {
            ports.add(toRandomizePorts.remove((int) (Math.random() * i)));
        }

        for (Port po: ports) {
            out.println("" + po.getSpecialty() + " \t" + po.getOffer());
        }

    }

}
