import java.util.ArrayList;

public class Intersection {

    int row;
    int col;

    Settlement settlement;
    ArrayList<Road> roads;
    Port p;

    public Intersection(int r, int c) {
        row = r;
        col = c;
    }

    public void addSettlement(Settlement s) {
        settlement = s;
    }

    public Settlement getSettlement() { return settlement; }




}
