import java.awt.*;
import java.util.*;

import static java.lang.System.*;

public class main {

    static Tile[][] board = new Tile[11][17];
    static Intersection[][] inter = new Intersection[11][17];
    static ArrayList<Port> ports = new ArrayList<Port>();

    static Stack<DevelopmentCard> daStack = new Stack<DevelopmentCard>();
    static HashMap<ResourceCard, Integer> bank = new HashMap<ResourceCard, Integer>();

    static ResourceCard[] rco = {ResourceCard.BRICK, ResourceCard.ORE, ResourceCard.SHEEP, ResourceCard.WHEAT, ResourceCard.WOOD};
    static DevelopmentCard[] dco = {DevelopmentCard.KNIGHT, DevelopmentCard.VICTORYPOINT, DevelopmentCard.MONOPOLY, DevelopmentCard.ROADBUILDING, DevelopmentCard.YEAROFPLENTY};

    static mainFrame mf;

    static Player[] players = new Player[]{
            new Player(new Color(246, 149, 60)),
            new Player(new Color(255, 255, 255)),
            new Player(new Color(0, 160, 224)),
            new Player(new Color(239, 65, 64))
    };
    static int turn = 0;
    static int[] startingTurnOrder = {0, 1, 2, 3, 3, 2, 1, 0};

    static int robberx, robbery;
    static int dice1 = 6, dice2 = 6;

    static int winner = -1;

    //gamestates
    static boolean
            startingScreen,
            startingSetup,
            playingdevelopmentcard,
            halving,
            movingRobber,
            stealing,
            buildingSettlement,
            buildingCity,
            buildingRoad,
            canRollDie,
            canEndTurn,
            canSelectCards,
            tradingBuilding,
            gameEnded;

    //for highlighting when building
    static boolean
            highlightEligibleSettlements,
            highlightEligibleCities,
            highlightEligibleRoads;

    //development card functions
    static boolean
            knighting,
            monopolying,
            yearofplentying,
            yearofplentying2,
            roadbuildinging,
            roadbuildinging2;

    static boolean[] displayHands = new boolean[]{false, false, false, false};

    static String guide = "fuck you";

    public static void main(String[] args) {


        startingScreen = true;
        createBoard();
        createDevelopmentCardStack();
        fillBank();
        //tempdisplayshittyboard();

        startingSetup = true;
        //for settler 1
        buildingSettlement = true;
        highlightEligibleSettlements = true;
        //



        mf = new mainFrame("Catan");

    }






    public static int rollDie() {
        dice1 = (int) (6 * Math.random() + 1);
        dice2 = (int) (6 * Math.random() + 1);
        if (dice1 + dice2 == 7) {
            for (int i = 0; i < 4; i++) {
                if (players[i].resourceHand.size() > 7) {
                    halving = true;
                    guide = "Player " + (i + 1) + ": choose " + (players[i].resourceHand.size() / 2) + " resource cards to discard";
                    mf.mp.resetHalvingIndex();
                    break;
                }
            }
            if (!halving) {
                movingRobber = true;
                guide = "Player " + (turn + 1) + ": Click a tile to move the robber (click the pip)";
            }
        }
        else {
            distributeResources(dice1 + dice2);
            guide = "Player " + (turn + 1) + ": trade and build!";
        }
        canRollDie = false;
        canSelectCards = true;
        tradingBuilding = true;
        canEndTurn = true;

        return dice1 + dice2;
    }

    public static boolean checkPlayingDevelopmentCard(Player p) {
        if (p.selectedDevelopmentCard != -1) {
            playingdevelopmentcard = true;
            return true;
        }
        return false;
    }

    public static void developmentCardFunction(Player p) {
        if (p.selectedDevelopmentCard != -1) {
            if (p.getSelectedDevelopmentCard() == DevelopmentCard.KNIGHT) {
                knighting = true;
                guide = "Player " + (turn + 1) + ": Click a tile to move the robber (click the pip)";
            }
            else if (p.getSelectedDevelopmentCard() == DevelopmentCard.MONOPOLY) {
                monopolying = true;
                guide = "Player " + (turn + 1) + ": Choose a resource (click card in bank), \n" +
                        "all players must give their resources of this type to you!";
            }
            else if (p.getSelectedDevelopmentCard() == DevelopmentCard.ROADBUILDING) {
                roadbuildinging = true;
                guide = "Player " + (turn + 1) + ": Build two roads free of charge!";
            }
            else if (p.getSelectedDevelopmentCard() == DevelopmentCard.YEAROFPLENTY) {
                yearofplentying = true;
                guide = "Player " + (turn + 1) + ": Choose any two resources from the bank";
            }
        }

    }

    public static void distributeResources(int roll) {
        int r, c;
        for (int i = 2; i < 43; i += 2) {
            c = 3 * (i / 9) + 2;
            r = i % 9 + 1;
            if (board[r][c] != null && board[r][c].getPipNumber() == roll)
                board[r][c].distributeResources();
        }
    }

    public static void domesticTrade() {
        Player b = null;
        if (players[turn].hasResourceCardsSelected()) {
            for (Player p: players) {
                if (p != players[turn] && p.hasResourceCardsSelected()){
                    b = p;
                }
            }
        }
        if (b == null)
            return;

        out.println(players[turn]);
        out.println(b);
        ArrayList<ResourceCard> aOffer = players[turn].getSelectedCards();
        ArrayList<ResourceCard> bOffer = b.getSelectedCards();

        for (ResourceCard rc: aOffer) {
            players[turn].removeResourceCard(rc, 1);
            b.addResourceCard(rc, 1);
        }
        for (ResourceCard rc: bOffer) {
            b.removeResourceCard(rc, 1);
            players[turn].addResourceCard(rc, 1);
        }

    }

    //FINISH THIS METHOD MOTHERFUCKER YOU STUPID ASS BITCH FUCK YOU KALE
    //you're gay as fuck bitchass SUCK MY DICK WHORE - Derrek

    public static void maritimeTrade(ResourceCard rc) {
        out.println("maritimeTrade method called");
        ArrayList<ResourceCard> offer = players[turn].getSelectedCards();
        int tradeRate = 4;

        if (players[turn].hasResourceCardsSelected()) {
            out.println("has cards selected");
            //check if all resourceCards in selectedHand are the same, doesn't work otherwise
            ResourceCard firstRc = null;
            for (ResourceCard rcFUCK: offer) {
                if (firstRc == null)
                    firstRc = rcFUCK;
                else if (rcFUCK != firstRc || firstRc == rc)
                    return;
            }
            out.println("offer is single carded");
            //check player's accessiblePorts for potential better trades
            for (Port porn: players[turn].accessiblePorts) {
                if (porn.getSpecialty() == null) {
                    tradeRate = 3;
                    out.println("found neutral port");
                }
                if (porn.getSpecialty() != null && porn.getSpecialty().equals(firstRc)) {
                    tradeRate = 2;
                    out.println("found specific");
                    break;
                }
            }

            //finally perform trade
            if (offer.size() >= tradeRate) {
                players[turn].removeResourceCard(firstRc, tradeRate);
                addToBank(firstRc, tradeRate);
                players[turn].addResourceCard(rc, 1);
                removeFromBank(rc, 1);
            }
        }
    }

    public static void buildSettlement(Player p, int x, int y) {
        main.inter[x][y].addSettlement(new Settlement(p, x, y));
        p.updateEligibleSettlements();
        p.updateEligibleRoads();
        p.buySettlement();
        p.updateVisibleVictoryPoints();
        highlightEligibleSettlements = false;
        buildingSettlement = false;
    }

    public static void buildCity(Player p, int x, int y) {
        main.inter[x][y].upgradeSettlement();
        p.buyCity();
        p.updateVisibleVictoryPoints();
        main.highlightEligibleCities = false;
        main.buildingCity = false;
    }

    public static void buildRoad(Player p, int x, int y, int direction, boolean isFree) {
        if (direction == 2) {
            main.inter[x][y].buildMiddleRoad(p);
        }
        else {
            main.inter[x][y].buildRightRoad(p);
        }
        p.updateEligibleSettlements();
        p.updateEligibleRoads();
        if (!isFree)
            p.buyRoad();
        p.updateLongestRoad();
        main.assignLongestRoad();
        main.highlightEligibleRoads = false;
        main.buildingRoad = false;
    }

    public static void developmentCardFunction() {

    }

    public static void monopoly(Player p, ResourceCard rc) {
        int toBeTransferredButNotStolenBecauseItsMonopolyAndIfYoureSaltyFuckYou = 0;
        for (Player pee: players) {
            toBeTransferredButNotStolenBecauseItsMonopolyAndIfYoureSaltyFuckYou = pee.countResources(rc);
            pee.removeResourceCard(rc, toBeTransferredButNotStolenBecauseItsMonopolyAndIfYoureSaltyFuckYou);
            p.addResourceCard(rc, toBeTransferredButNotStolenBecauseItsMonopolyAndIfYoureSaltyFuckYou);
        }
    }

    public static void moveRobber(int x, int y) {
        robberx = x;
        robbery = y;
        movingRobber = false;
        if (canSteal(players[turn])) {
            stealing = true;
            guide = "Player " + (turn + 1) + ": Choose a player to steal from";
        }
        else
            guide = "Player " + (turn + 1) + ": trade and build!";
    }

    public static boolean canSteal(Player stealer) {
        if (inter[robberx - 1][robbery - 1].settlement != null && inter[robberx - 1][robbery - 1].settlement.owner != stealer) {
            return true;
        }
        else if (inter[robberx - 1][robbery + 1].settlement != null && inter[robberx - 1][robbery + 1].settlement.owner != stealer) {
            return true;
        }
        else if (inter[robberx][robbery + 2].settlement != null && inter[robberx][robbery + 2].settlement.owner != stealer) {
            return true;
        }
        else if (inter[robberx + 1][robbery + 1].settlement != null && inter[robberx + 1][robbery + 1].settlement.owner != stealer) {
            return true;
        }
        else if (inter[robberx + 1][robbery - 1].settlement != null && inter[robberx + 1][robbery - 1].settlement.owner != stealer) {
            return true;
        }
        else if (inter[robberx][robbery - 2].settlement != null && inter[robberx][robbery - 2].settlement.owner != stealer) {
            return true;
        }
        return false;
    }

    public static void steal(Player stealer, Player victim) {
        out.println("steal method called");
        if (inter[robberx - 1][robbery - 1].settlement != null && inter[robberx - 1][robbery - 1].settlement.owner == victim) {}
        else if (inter[robberx - 1][robbery + 1].settlement != null && inter[robberx - 1][robbery + 1].settlement.owner == victim) {}
        else if (inter[robberx][robbery + 2].settlement != null && inter[robberx][robbery + 2].settlement.owner == victim) {}
        else if (inter[robberx + 1][robbery + 1].settlement != null && inter[robberx + 1][robbery + 1].settlement.owner == victim) {}
        else if (inter[robberx + 1][robbery - 1].settlement != null && inter[robberx + 1][robbery - 1].settlement.owner == victim) {}
        else if (inter[robberx][robbery - 2].settlement != null && inter[robberx][robbery - 2].settlement.owner == victim) {}
        else {
            guide = "Victim must be adjacent to robber tile";
            return;
        }

        if (stealer.equals(victim)) {
            guide = "Can't steal from yourself!";
        }
        else {
            stealer.addResourceCard(victim.resourceHand.remove((int) (Math.random() * victim.resourceHand.size())), 1);
            guide = "Player " + (turn + 1) + ": trade and build!";
            stealing = false;
        }

    }

    public static void tempdisplayshittyboard() {
        Tile temp;
        for (int y = 0; y < 17; y++) {
            for (int x = 0; x < 11; x++) {
                temp = board[x][y];
                if (temp == null) {
                    out.print(" •• ");
                }
                else {
                    try {
                        if (temp.getPipNumber() / 10 == 1) {
                            out.print(temp.getResourceType().toString().substring(0, 1) + " " + temp.getPipNumber());
                        }
                        else {
                            out.print(temp.getResourceType().toString().substring(0, 1) + " 0" + temp.getPipNumber());
                        }
                    }
                    catch (Exception e) {
                        out.print("D 00");
                    }
                }
            }
            out.println("");
        }

        out.println("\n");

        Intersection itemp;
        for (int y = 0; y < 17; y++) {
            for (int x = 0; x < 11; x++) {
                itemp = inter[x][y];
                if (itemp == null) {
                    out.print(" •• ");
                }
                else {
                    out.print(" II ");
                }
            }
            out.println("");
        }
    }

    public static void createBoard() {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < 4; i++) {
            tiles.add(new Tile(ResourceCard.WOOD));
            tiles.add(new Tile(ResourceCard.WHEAT));
            tiles.add(new Tile(ResourceCard.SHEEP));
            tiles.add(new Tile(ResourceCard.ORE));
            tiles.add(new Tile(ResourceCard.BRICK));
        }
        tiles.remove(tiles.size() - 1);
        tiles.remove(tiles.size() - 1);
        tiles.add(new Tile());

        Collections.shuffle(tiles);

        int[] pipOrder = {5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11};
        int p = 0;
        for (Tile t: tiles){
            if (t.getResourceType() != null) {
                t.setPipNumber(pipOrder[p]);
                p++;
            }
        }

        for (Tile t: tiles) {
            out.println("" + t.getResourceType() + " \t" + t.getPipNumber());
        }

        board[3][2] = tiles.remove(0);
        board[2][5] = tiles.remove(0);
        board[1][8] = tiles.remove(0);
        board[2][11] = tiles.remove(0);
        board[3][14] = tiles.remove(0);
        board[5][14] = tiles.remove(0);
        board[7][14] = tiles.remove(0);
        board[8][11] = tiles.remove(0);
        board[9][8] = tiles.remove(0);
        board[8][5] = tiles.remove(0);
        board[7][2] = tiles.remove(0);
        board[5][2] = tiles.remove(0);
        board[4][5] = tiles.remove(0);
        board[3][8] = tiles.remove(0);
        board[4][11] = tiles.remove(0);
        board[6][11] = tiles.remove(0);
        board[7][8] = tiles.remove(0);
        board[6][5] = tiles.remove(0);
        board[5][8] = tiles.remove(0);

        int x, y;
        for (int i = 2; i < 43; i += 2) {
            y = 3 * (i / 9) + 2;
            x = i % 9 + 1;
            if (board[x][y] != null) {
                board[x][y].setCoords(x, y);
                if (board[x][y].getPipNumber() == 0) {
                    robberx = x;
                    robbery = y;
                }
            }
        }

        for (int i = 2; i < 87; i += 2) {
            y = 2 * (i / 10);
            x = i % 10 + 1;
            if (y % 3 != 2)
                inter[x][y] = new Intersection(x, y);
            inter[9][0] = null;
            inter[1][16] = null;
        }
        for (int i = 0; i < 63; i+= 2) {
            y = 2 * (i / 8) + 1;
            x = i % 8 + 2;
            if (y % 3 != 2)
                inter[x][y] = new Intersection(x, y);
        }
        inter[0][7] = new Intersection(0, 7);
        inter[0][9] = new Intersection(0, 9);
        inter[10][7] = new Intersection(10, 7);
        inter[10][9] = new Intersection(10, 9);



        ports.add(new Port(null, 3));
        ports.add(new Port(null, 3));
        ports.add(new Port(null, 3));
        ports.add(new Port(null, 3));
        ports.add(new Port(ResourceCard.BRICK, 2));
        ports.add(new Port(ResourceCard.ORE, 2));
        ports.add(new Port(ResourceCard.SHEEP, 2));
        ports.add(new Port(ResourceCard.WHEAT, 2));
        ports.add(new Port(ResourceCard.WOOD, 2));
        Collections.shuffle(ports);

        ports.get(0).setCoords(2, 1, 3, 0);
        ports.get(1).setCoords(5, 0, 6, 1);
        ports.get(2).setCoords(8, 3, 9, 4);
        ports.get(3).setCoords(10, 7, 10, 9);
        ports.get(4).setCoords(9, 12, 8, 13);
        ports.get(5).setCoords(6, 15, 5, 16);
        ports.get(6).setCoords(3, 16, 2, 15);
        ports.get(7).setCoords(1, 12, 1, 10);
        ports.get(8).setCoords(1, 6, 1, 4);


    }

    public static void createDevelopmentCardStack() {
        for (int i = 0; i < 14; i++) {
            daStack.push(DevelopmentCard.KNIGHT);
        }
        for (int i = 0; i < 2; i++) {
            daStack.push(DevelopmentCard.MONOPOLY);
            daStack.push(DevelopmentCard.ROADBUILDING);
            daStack.push(DevelopmentCard.YEAROFPLENTY);
        }
        for (int i = 0; i < 5; i++) {
            daStack.push(DevelopmentCard.VICTORYPOINT);
        }
        Collections.shuffle(daStack);
    }

    public static void fillBank() {
        for (ResourceCard rc: rco) {
            bank.put(rc, 19);
        }
    }

    public static void removeFromBank(ResourceCard type, int count) {
        bank.replace(type, bank.get(type) - count);
    }

    public static void addToBank(ResourceCard type, int count) {
        bank.replace(type, bank.get(type) + count);
    }

    public static void assignLongestRoad() {
        int plaer = -1;
        for (int i = 0; i < 4; i++) {
            players[i].removeLongestRoad();
            if (players[i].getLongestRoadLength() > 4 && plaer == -1)
                plaer = i;
            else if (plaer > -1 && players[i].getLongestRoadLength() > players[plaer].getLongestRoadLength())
                plaer = i;
        }
        if (plaer > -1) {
            players[plaer].rewardLongestRoad();
            players[plaer].updateVisibleVictoryPoints();
        }
    }

    public static void assignLargestArmy() {
        int plaer = -1;
        for (int i = 0; i < 4; i++) {
            players[plaer].removeLargestArmy();
            if (players[i].getKnightsPlayed() > 2 && plaer == -1)
                plaer = i;
            else if (players[i].getKnightsPlayed() > players[plaer].getKnightsPlayed())
                plaer = i;
        }
        players[plaer].rewardLargestArmy();
    }

    public static void endTurn() {
        canRollDie = true;
        canSelectCards = false;
        tradingBuilding = false;
        turn = (turn + 1) % 4;
    }

    public static void checkForWinner() {
        for (int i = 0; i < 4; i++) {
            if (players[i].visibleVictoryPoints + players[i].hiddenVictoryPoints >= 10) {
                winner = i + 1;
                gameEnded = true;
                return;
            }
        }
    }

}
