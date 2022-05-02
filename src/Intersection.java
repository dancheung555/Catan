import java.util.ArrayList;

public class Intersection {

    int r;
    int c;

    Settlement settlement;
    boolean open;

    //for c % 3 == 0, LEFT road goes DOWN, MIDDLE goes UP, RIGHT goes DOWN
    //for c % 3 == 1, LEFT road goes UP, MIDDLE goes DOWN, RIGHT goes UP
    Road leftRoad, middleRoad, rightRoad;

    Port p;


    public Intersection(int row, int col) {
        r = row;
        c = col;
        open = true;
    }

    public void addSettlement(Settlement s) {
        settlement = s;
        main.players.get(main.turn).addSettlement(s);
        updateOpen();
    }

    public boolean hasSettlement() {
        return settlement != null;
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
        if (c % 3 == 0) {
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
        p.updateEligibleRoads();
        return false;
    }

    public boolean buildMiddleRoad(Player p) {
        if (c % 3 == 0) {
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
        p.updateEligibleRoads();
        return false;
    }

    public boolean buildRightRoad(Player p) {
        if (c % 3 == 0) {
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
        p.updateEligibleRoads();
        return false;
    }

    public void setLeftRoad(Road lr) { leftRoad = lr; }
    public void setMiddleRoad(Road mr) { middleRoad = mr; }
    public void setRightRoad(Road rr) { rightRoad = rr; }
    public Road getLeftRoad() { return leftRoad; }
    public Road getMiddleRoad() { return middleRoad; }
    public Road getRightRoad() { return rightRoad; }

    public void obtainStartingResources() {
        if (c % 3 == 0) {
            try {
                settlement.owner.addResourceCard(main.board[r - 1][c - 1].getResourceType(), 1);
                main.removeFromBank(main.board[r - 1][c - 1].getResourceType(), 1);
            } catch (Exception e) {}
            try {
                settlement.owner.addResourceCard(main.board[r][c + 2].getResourceType(), 1);
                main.removeFromBank(main.board[r][c + 2].getResourceType(), 1);
            } catch (Exception e) {}
            try {
                settlement.owner.addResourceCard(main.board[r + 1][c - 1].getResourceType(), 1);
                main.removeFromBank(main.board[r + 1][c - 1].getResourceType(), 1);
            } catch (Exception e) {}
        }
        else {
            try {
                settlement.owner.addResourceCard(main.board[r - 1][c + 1].getResourceType(), 1);
                main.removeFromBank(main.board[r - 1][c + 1].getResourceType(), 1);
            } catch (Exception e) {}
            try {
                settlement.owner.addResourceCard(main.board[r][c - 2].getResourceType(), 1);
                main.removeFromBank(main.board[r][c - 2].getResourceType(), 1);
            } catch (Exception e) {}
            try {
                settlement.owner.addResourceCard(main.board[r + 1][c + 1].getResourceType(), 1);
                main.removeFromBank(main.board[r - 1][c + 1].getResourceType(), 1);
            } catch (Exception e) {}
        }
    }
}
