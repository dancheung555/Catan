import java.util.ArrayList;
import java.awt.Color;

public class Player {
    Color color;
    ArrayList<ResourceCard> resourceHand;
    ArrayList<DevelopmentCard> developmentCardHand;
    ArrayList<Settlement> settlements = new ArrayList<Settlement>();
    ArrayList<Road> eligibleRoads = new ArrayList<Road>();

    int victoryPoints;
    int longestRoadLength;
    int knightsPlayed;



    public Player(Color c)
    {
        color = c;
        resourceHand = new ArrayList<ResourceCard>();
    }

    public void addResourceCard(ResourceCard type, int count) {
        while (count > 0) {
            resourceHand.add(type);
            count--;
        }
    }

    public int countResources(ResourceCard type) {
        int count = 0;
        for (ResourceCard card: resourceHand) {
            if (card.equals(type))
                count++;
        }
        return count;
    }

    public void removeResourceCard(ResourceCard type, int count) {
        while (count > 0) {
            resourceHand.remove(type);
            count--;
        }
    }

    public void addDevelopmentCard(ResourceCard type) {
        resourceHand.add(type);
    }

    public void playDevelopmentCard(ResourceCard type) {
        resourceHand.remove(type);
        if (type.equals(DevelopmentCard.KNIGHT))
            knightsPlayed++;
    }

    public void addSettlement(Settlement s) {
        settlements.add(s);
        updateEligibleRoads();
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
                } catch (Exception e) { System.out.println("error roadCounter 0 1"); }
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

    public Color getColor() {
        return color;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public int getLongestRoadLength() {
        return longestRoadLength;
    }

    public void updateLongestRoad() {
    }

    public int getKnightsPlayed() {
        return knightsPlayed;
    }



}
