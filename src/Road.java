public class Road {

    int row1;
    int col1;
    int row2;
    int col2;

    Player owner;

    public Road(int r1, int c1, int r2, int c2, Player o) {
        row1 = r1;
        col1 = c1;
        row2 = r2;
        col2 = c2;
        owner = o;
    }

}
