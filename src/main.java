import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import static java.lang.System.*;

public class main {

    static Tile[][] board = new Tile[11][17];


    public static void main(String[] args) {
        //mainFrame main = new mainFrame("Catan");

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
        boolean running = false;

        while (running) {

        }

        distributeResources(rollDie());
        tempdisplayshittyboard();


    }






    public static int rollDie() {
        return (int) (6 * Math.random() + 1) + (int) (6 * Math.random() + 1);
    }

    public static void distributeResources(int roll) {
        int r, c;
        for (int i = 2; i < 43; i += 2) {
            c = 3 * (i / 9) + 2;
            r = i % 9 + 1;
            //board[r][c].distributeResources();
            board[r][c] = new Tile();
        }
    }

    public static void tradePlayers(Player a, Player b, ArrayList<ResourceCard> aOffer, ArrayList<ResourceCard> bOffer) {

    }

    public static void tradeBank(Player p, ArrayList<ResourceCard> offer, ResourceCard receipt) {

    }

    public static void build(Player p, String type) {

    }

    public static void moveRobber(Player p, int x, int y) {

    }

    public static void steal(Player stealer, Player victim) {

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
        for (int r = 0; r < 11; r++) {
            for (int c = 0; c < 17; c++) {
                if (board[r][c] == null) {
                    out.print("•");
                }
                else
                    out.print("T");
            }
            out.println("");
        }
    }
}
