import java.util.TreeMap;
import java.util.Map;

public class main {

    static Comparable[][] board = new Comparable[11][17];


    public static void main(String[] args) {
        //mainFrame main = new mainFrame("Catan");

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

    }






    public static int rollDie() {
        return (int) (6 * Math.random() + 1) + (int) (6 * Math.random() + 1);
    }
}
