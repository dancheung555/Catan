import java.util.ArrayList;

public class Intersection {

    int r;
    int c;

    Settlement settlement;
    boolean open;

    //for r % 3 == 0, LEFT road goes DOWN, MIDDLE goes UP, RIGHT goes DOWN
    //for r % 3 == 1, LEFT road goes UP, MIDDLE goes DOWN, RIGHT goes UP
    Road leftRoad, middleRoad, rightRoad;

    Port p;


    public Intersection(int row, int col) {
        r = row;
        c = col;
        open = true;
    }

    public void addSettlement(Settlement s) {
        settlement = s;
        updateOpen();
    }

    public Settlement getSettlement() { return settlement; }

    public void upgradeSettlement() {
        settlement.upgrade();
    }

    public void updateOpen() {
        close();
        if (c % 3 == 0) {
            try {
                main.inter[r - 1][c + 1].close();
            } catch (Exception e) {};
            try {
                main.inter[r + 1][c + 1].close();
            } catch (Exception e) {};
            try {
                main.inter[r][c - 2].close();
            } catch (Exception e) {};
        }
        else {
            try {
                main.inter[r - 1][c - 1].close();
            } catch (Exception e) {};
            try {
                main.inter[r + 1][c - 1].close();
            } catch (Exception e) {};
            try {
                main.inter[r][c + 2].close();
            } catch (Exception e) {};
        }
    }

    public void close() {
        open = false;
    }

    public boolean isOpen() {
        return open;
    }

    //returns if the road has been successfully built
    public boolean buildLeftRoad(Player p) {
        if (r % 3 == 1) {
            try {
                leftRoad = new Road(r, c, r - 1, c + 1, p);
                main.inter[r - 1][c + 1].setRightRoad(leftRoad);
                return true;
            } catch (Exception e) { System.out.println("l1 error"); }
        }
        else {
            try {
                leftRoad = new Road(r, c, r - 1, c - 1, p);
                main.inter[r - 1][c - 1].setRightRoad(leftRoad);
                return true;
            } catch (Exception e) { System.out.println("l2 error"); }
        }
        return false;
    }

    public boolean buildMiddleRoad(Player p) {
        if (r % 3 == 1) {
            try {
                middleRoad = new Road(r, c, r, c - 2, p);
                main.inter[r][c - 2].setMiddleRoad(middleRoad);
                return true;
            } catch (Exception e) { System.out.println("m1 error"); }
        }
        else {
            try {
                middleRoad = new Road(r, c, r, c + 2, p);
                main.inter[r][c + 2].setMiddleRoad(middleRoad);
                return true;
            } catch (Exception e) { System.out.println("m2 error");}
        }
        return false;
    }

    public boolean buildRightRoad(Player p) {
        if (r % 3 == 1) {
            try {
                rightRoad = new Road(r, c, r + 1, c + 1, p);
                main.inter[r + 1][c + 1].setLeftRoad(rightRoad);
                return true;
            } catch (Exception e) { System.out.println("r1 error"); }
        }
        else {
            try {
                rightRoad = new Road(r, c, r + 1, c - 1, p);
                main.inter[r + 1][c - 1].setLeftRoad(rightRoad);
                return true;
            } catch (Exception e) { System.out.println("r2 error"); }
        }
        return false;
    }

    public void setLeftRoad(Road lr) { leftRoad = lr; }
    public void setMiddleRoad(Road mr) { middleRoad = mr; }
    public void setRightRoad(Road rr) { rightRoad = rr; }
    public Road getLeftRoad() { return leftRoad; }
    public Road getMiddleRoad() { return middleRoad; }
    public Road getRightRoad() { return rightRoad; }

}
