import java.util.ArrayList;
import java.awt.Color;

public class Player {
    Color color;

    ArrayList<ResourceCard> resourceHand;
    boolean[] selectedResources;

    ArrayList<DevelopmentCard> developmentCardHand = new ArrayList<DevelopmentCard>();
    int selectedDevelopmentCard = -1;

    ArrayList<Settlement> settlements = new ArrayList<Settlement>();

    ArrayList<Road> eligibleRoads = new ArrayList<Road>();
    ArrayList<Settlement> eligibleSettlements = new ArrayList<Settlement>();

    ArrayList<Port> accessiblePorts = new ArrayList<Port>();

    int visibleVictoryPoints;
    int hiddenVictoryPoints;
    int longestRoadLength;
    int knightsPlayed;

    boolean longestRoad;
    boolean largestArmy;


    public Player(Color c) {
        color = c;
        resourceHand = new ArrayList<ResourceCard>();
    }

    public void addResourceCard(ResourceCard type, int count) {
        while (count > 0) {
            resourceHand.add(type);
            count--;
        }
        selectedResources = new boolean[resourceHand.size()];
    }

    public int countResources(ResourceCard type) {
        int count = 0;
        for (ResourceCard card: resourceHand) {
            if (card != null && card.equals(type))
                count++;
        }
        return count;
    }

    public void removeResourceCard(ResourceCard type, int count) {
        while (count > 0) {
            resourceHand.remove(type);
            count--;
        }
        selectedResources = new boolean[resourceHand.size()];
    }

    public void selectResourceCard(int i) {
        if (selectedResources[i])
            selectedResources[i] = false;
        else
            selectedResources[i] = true;
    }

    public boolean hasResourceCardsSelected() {
        for (boolean b: selectedResources) {
            if (b)
                return true;
        }
        return false;
    }

    public ArrayList<ResourceCard> getSelectedCards() {
        ArrayList<ResourceCard> sel = new ArrayList<ResourceCard>();
        for (int i = 0; i < selectedResources.length; i++) {
            if (selectedResources[i])
                sel.add(resourceHand.get(i));
        }
        return sel;
    }

    public void removeSelectedCards() {
        for (int i = selectedResources.length - 1; i >= 0; i--) {
            if (selectedResources[i]) {
                main.addToBank(resourceHand.remove(i), 1);
            }
        }
        selectedResources = new boolean[resourceHand.size()];
    }

    public boolean hasDevelopmentCard(DevelopmentCard dc) {
        for (DevelopmentCard dcFUCK: developmentCardHand) {
            if (dcFUCK == dc)
                return true;
        }
        return false;
    }

    public void selectDevelopmentCard(int sel) {
        selectedDevelopmentCard = sel;
        developmentCardFunction();
    }

    public void developmentCardFunction() {
        if (developmentCardHand.get(selectedDevelopmentCard) == DevelopmentCard.KNIGHT) {
            main.movingRobber = true;
        }
        else if (developmentCardHand.get(selectedDevelopmentCard) == DevelopmentCard.MONOPOLY) {

        }
    }

    public void removeDevelopmentCard() {
        resourceHand.remove(selectedDevelopmentCard);
    }

    public void addSettlement(Settlement s) {
        settlements.add(s);
        if (main.inter[s.row][s.col].hasPort()) {
            accessiblePorts.add(main.inter[s.row][s.col].p);
        }
        if (!main.startingSetup)
            updateEligibleRoads();
    }

    public void updateStartingEligibleRoads(int r, int c) {
        eligibleRoads = new ArrayList<Road>();
        if (c % 3 == 0) {
            try {
                if (main.inter[r - 1][c + 1] != null)
                    eligibleRoads.add(new Road(r, c, r - 1, c + 1, this));
            } catch (Exception e) {}
            try {
                if (main.inter[r][c - 2] != null)
                    eligibleRoads.add(new Road(r, c, r, c - 2, this));
            } catch (Exception e) {}
            try {
                if (main.inter[r + 1][c + 1] != null)
                    eligibleRoads.add(new Road(r, c, r + 1, c + 1, this));
            } catch (Exception e) {}
        }
        else {
            try {
                if (main.inter[r - 1][c - 1] != null)
                    eligibleRoads.add(new Road(r, c, r - 1, c - 1, this));
            } catch (Exception e) {}
            try {
                if (main.inter[r][c + 2] != null)
                    eligibleRoads.add(new Road(r, c, r, c + 2, this));
            } catch (Exception e) {}
            try {
                if (main.inter[r + 1][c - 1] != null)
                    eligibleRoads.add(new Road(r, c, r + 1, c - 1, this));
            } catch (Exception e) {}
        }
    }

    public void updateEligibleRoads() {
        eligibleRoads = new ArrayList<Road>();
        for (Settlement s: settlements) {
            roadCounter(s.row, s.col, 0);
        }
    }

    private void roadCounter(int r, int c, int fromDirection) {
        if (c % 3 == 0) {
            if (fromDirection != 3) {
                try {
                    if (main.inter[r][c].getLeftRoad() != null)
                        roadCounter(r - 1, c + 1, 1);
                    else if (main.inter[r - 1][c + 1] != null)
                        eligibleRoads.add(new Road(r, c, r - 1, c + 1, this));
                } catch (Exception e) { System.out.println("error roadCounter 0 3"); }
            }
            if (fromDirection != 2) {
                try {
                    if (main.inter[r][c].getMiddleRoad() != null)
                        roadCounter(r, c - 2, 2);
                    else if (main.inter[r][c - 2] != null)
                        eligibleRoads.add(new Road(r, c, r, c - 2, this));
                } catch (Exception e) { System.out.println("error roadCounter 0 2"); }
            }
            if (fromDirection != 1) {
                try {
                    if (main.inter[r][c].getRightRoad() != null)
                        roadCounter(r + 1, c + 1, 3);
                    else if (main.inter[r + 1][c + 1] != null)
                        eligibleRoads.add(new Road(r, c, r + 1, c + 1, this));
                } catch (Exception fuckYouStroudGoddamnTreesWereAss) { System.out.println("error roadCounter 0 1"); }
            }
        }
        else {
            if (fromDirection != 3) {
                try {
                    if (main.inter[r][c].getLeftRoad() != null)
                        roadCounter(r - 1, c - 1, 1);
                    else if (main.inter[r - 1][c - 1] != null)
                        eligibleRoads.add(new Road(r, c, r - 1, c - 1, this));
                } catch (Exception e) { System.out.println("error roadCounter 1 3"); }
            }
            if (fromDirection != 2) {
                try {
                    if (main.inter[r][c].getMiddleRoad() != null)
                        roadCounter(r, c + 2, 2);
                    else if (main.inter[r][c + 2] != null)
                        eligibleRoads.add(new Road(r, c, r, c + 2, this));
                } catch (Exception e) { System.out.println("error roadCounter 1 2"); }
            }
            if (fromDirection != 1) {
                try {
                    if (main.inter[r][c].getRightRoad() != null)
                        roadCounter(r + 1, c - 1, 3);
                    else if (main.inter[r + 1][c - 1] != null)
                        eligibleRoads.add(new Road(r, c, r + 1, c - 1, this));
                } catch (Exception e) { System.out.println("error roadCounter 1 1"); }
            }
        }
    }

    public boolean canBuildRoad(int x, int y, int direction) {
        Road temp = null;
        if (direction == 1)
            temp = new Road(x, y, x + 1, y + 1, this);
        else if (direction == 2)
            temp = new Road(x, y, x, y - 2, this);
        else if (direction == 3)
            temp = new Road(x, y, x + 1, y - 1, this);
        for (Road r: eligibleRoads) {
            if (r.equals(temp)) {
                System.out.println("canpring");
                return true;
            }
        }
        System.out.println("cannotpring");
        return false;
    }

    public void updateEligibleSettlements() {
        eligibleSettlements = new ArrayList<Settlement>();
        for (Settlement s: settlements) {
            settlementCounter(s.row, s.col, 0);
        }
    }

    public void settlementCounter(int r, int c, int fromDirection) {
        if (main.inter[r][c].isOpen()) {
            eligibleSettlements.add(new Settlement(r, c));
        }
        if (c % 3 == 0) {
            if (fromDirection != 3) {
                try {
                    if (main.inter[r][c].getLeftRoad() != null)
                        settlementCounter(r - 1, c + 1, 1);
                } catch (Exception e) { System.out.println("error settlementCounter 0 3"); }
            }
            if (fromDirection != 2) {
                try {
                    if (main.inter[r][c].getMiddleRoad() != null)
                        settlementCounter(r, c - 2, 2);
                } catch (Exception e) { System.out.println("error settlementCounter 0 2"); }
            }
            if (fromDirection != 1) {
                try {
                    if (main.inter[r][c].getRightRoad() != null)
                        settlementCounter(r + 1, c + 1, 3);
                } catch (Exception e) { System.out.println("error settlementCounter 0 1"); }
            }
        }
        else {
            if (fromDirection != 3) {
                try {
                    if (main.inter[r][c].getLeftRoad() != null)
                        settlementCounter(r - 1, c - 1, 1);
                } catch (Exception e) { System.out.println("error settlementCounter 1 3"); }
            }
            if (fromDirection != 2) {
                try {
                    if (main.inter[r][c].getMiddleRoad() != null)
                        settlementCounter(r, c + 2, 2);
                } catch (Exception e) { System.out.println("error settlementCounter 1 2"); }
            }
            if (fromDirection != 1) {
                try {
                    if (main.inter[r][c].getRightRoad() != null)
                        settlementCounter(r + 1, c - 1, 3);
                } catch (Exception e) { System.out.println("error settlementCounter 1 1"); }
            }
        }
    }

    public boolean canBuildSettlement(int x, int y) {
        for (Settlement s: eligibleSettlements) {
            if (s.equals(new Settlement(x, y))) {
                System.out.println("canpring set");
                return true;
            }
        }
        System.out.println("cannotpring set");
        return false;
    }

    public boolean hasResourcesForRoad() {
        return countResources(ResourceCard.BRICK) >= 1 &&
                countResources(ResourceCard.WOOD) >= 1;
    }

    public void buyRoad() {
        removeResourceCard(ResourceCard.BRICK, 1);
        removeResourceCard(ResourceCard.WOOD, 1);
    }

    public boolean hasResourcesForSettlement() {
        return countResources(ResourceCard.BRICK) >= 1 &&
                countResources(ResourceCard.WOOD) >= 1 &&
                countResources(ResourceCard.WHEAT) >= 1 &&
                countResources(ResourceCard.SHEEP) >= 1;
    }

    public void buySettlement() {
        removeResourceCard(ResourceCard.BRICK, 1);
        removeResourceCard(ResourceCard.WOOD, 1);
        removeResourceCard(ResourceCard.WHEAT, 1);
        removeResourceCard(ResourceCard.SHEEP, 1);
    }

    public boolean hasResourcesForCity() {
        return countResources(ResourceCard.WHEAT) >= 2 &&
                countResources(ResourceCard.ORE) >= 3;
    }

    public void buyCity() {
        removeResourceCard(ResourceCard.WHEAT, 2);
        removeResourceCard(ResourceCard.ORE, 3);
    }

    public boolean hasResourcesForDevelopmentCard() {
        return countResources(ResourceCard.SHEEP) >= 1 &&
                countResources(ResourceCard.WHEAT) >= 1 &&
                countResources(ResourceCard.ORE) >= 1;
    }

    public void buyDevelopmentCard() {
        removeResourceCard(ResourceCard.SHEEP, 1);
        removeResourceCard(ResourceCard.WHEAT, 1);
        removeResourceCard(ResourceCard.ORE, 1);
        developmentCardHand.add(main.daStack.pop());
        updateHiddenVictoryPoints();
    }

    public Color getColor() {
        return color;
    }

    public void updateVisibleVictoryPoints() {
        visibleVictoryPoints = 0;
        for (Settlement s: settlements) {
            visibleVictoryPoints += s.tier;
        }
        if (longestRoad)
            visibleVictoryPoints += 2;
        if (largestArmy)
            visibleVictoryPoints += 2;
        main.checkForWinner();
    }

    public void updateHiddenVictoryPoints() {
        int victoryPointCards = 0;
        for (DevelopmentCard dc: developmentCardHand) {
            if (dc == DevelopmentCard.VICTORYPOINT)
                victoryPointCards++;
        }
        hiddenVictoryPoints = victoryPointCards;
        main.checkForWinner();
    }

    public int getLongestRoadLength() {
        return longestRoadLength;
    }


    //FINISH THIS METHOD YOU FUCKING CUNT SO FUCKING LAZY YOU CAN'T FINISH IN ONE NIGHT GODDAMN
    public void updateLongestRoad() {
        System.out.println("updateLingestRoad claled");
        for (Settlement s: settlements) {
            longestRoadLength = Math.max(updateLongestRoadHelper(s.row, s.col, 0), longestRoadLength);
        }
    }

    private int updateLongestRoadHelper(int r, int c, int fromDirection) {
        int max = 0, leftLength = 0, middleLength = 0, rightLength = 0;
        if (c % 3 == 0) {
            if (main.inter[r][c].getLeftRoad() != null && fromDirection != 3) {
                leftLength = 1 + updateLongestRoadHelper(r - 1, c + 1, 1);
            }
            if (main.inter[r][c].getMiddleRoad() != null && fromDirection != 2) {
                middleLength = 1 + updateLongestRoadHelper(r, c - 2, 2);
            }
            if (main.inter[r][c].getRightRoad() != null && fromDirection != 1) {
                rightLength = 1 + updateLongestRoadHelper(r + 1, c + 1, 3);
            }
        }
        else {
            if (main.inter[r][c].getLeftRoad() != null && fromDirection != 3) {
                leftLength = 1 + updateLongestRoadHelper(r - 1, c - 1, 1);
            }
            if (main.inter[r][c].getMiddleRoad() != null && fromDirection != 2) {
                middleLength = 1 + updateLongestRoadHelper(r, c + 2, 2);
            }
            if (main.inter[r][c].getRightRoad() != null && fromDirection != 1) {
                rightLength = 1 + updateLongestRoadHelper(r + 1, c - 1, 3);
            }
        }
        max = Math.max(leftLength + middleLength, middleLength + rightLength);
        max = Math.max(max, leftLength + rightLength);
        return max;
    }

    public int getKnightsPlayed() {
        return knightsPlayed;
    }

    public void rewardLongestRoad() { longestRoad = true; }
    public void removeLongestRoad() { longestRoad = false; }
    public void rewardLargestArmy() { largestArmy = true; }
    public void removeLargestArmy() { largestArmy = false; }

    public String toString() {
        String out = "";
        for (boolean b: selectedResources) {
            if (b)
                out += "1";
            else
                out += "0";
        }
        return out;
    }

}
