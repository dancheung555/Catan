import java.util.ArrayList;

public class Intersection {

    int x;
    int y;

    Settlement settlement;
    ArrayList<Road> roads;
    Port p;

    boolean open;

    public Intersection(int r, int c) {
        x = r;
        y = c;
        open = true;
    }

    public void addSettlement(Settlement s) {
        settlement = s;
        updateOpen();
    }

    public Settlement getSettlement() { return settlement; }

    public void updateOpen() {
        close();
        if (y % 3 == 0) {
            try {
                main.inter[x - 1][y + 1].close();
            } catch (Exception e) {};
            try {
                main.inter[x + 1][y + 1].close();
            } catch (Exception e) {};
            try {
                main.inter[x][y - 2].close();
            } catch (Exception e) {};
        }
        else {
            try {
                main.inter[x - 1][y - 1].close();
            } catch (Exception e) {};
            try {
                main.inter[x + 1][y - 1].close();
            } catch (Exception e) {};
            try {
                main.inter[x][y + 2].close();
            } catch (Exception e) {};
        }
    }

    public void close() {
        open = false;
    }

    public boolean isOpen() {
        return open;
    }


}
