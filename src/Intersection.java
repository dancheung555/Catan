import java.util.ArrayList;

public class Intersection extends Tile {

    int row;
    int col;

    Settlement settlement;
    ArrayList<Road> roads;
    Port p;

    public Intersection(int r, int c) {
        super(r, c);
    }

    public void addSettlement(Settlement s) {
        settlement = s;
    }

    public Settlement getSettlement() { return settlement; }




}
