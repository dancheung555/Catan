
public class Settlement {

    Player owner;
    int tier;

    int row;
    int col;

    public Settlement(Player o, int r, int c) {
        owner = o;
        row = r;
        col = c;
        //main.board[r][c].addSettlement(this);
    }

    public Player getOwner() { return owner; }
    public int getTier() { return tier; }

    public int compareTo(Object o) {
        return 0;
    }


}
