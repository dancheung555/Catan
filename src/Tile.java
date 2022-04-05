import java.util.ArrayList;

public class Tile {

    ResourceCard resourceType;
    ArrayList<Settlement> settlements;

    int row;
    int col;

    int pipNumber;

    public Tile(int x, int y) {
        row = x;
        col = y;
    }

}
