
public class Settlement {

    Player owner;
    int tier;           //tier = 1 means a settlement, tier = 2 means a city

    int row;
    int col;

    public Settlement(Player o, int r, int c) {
        owner = o;
        row = r;
        col = c;
        tier = 1;
    }

    public Settlement(int r, int c) {
        row = r;
        col = c;
    }

    public Player getOwner() { return owner; }
    public int getTier() { return tier; }
    public void upgrade() { tier = 2; }

    public boolean equals(Settlement s) {
        if (s.row == row && s.col == col)
            return true;
        return false;
    }




}
