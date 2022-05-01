
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

    public Player getOwner() { return owner; }
    public int getTier() { return tier; }
    public void upgrade() { tier = 2; }

    public int compareTo(Object o) {
        return 0;
    }




}
