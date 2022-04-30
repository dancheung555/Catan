import java.util.ArrayList;
import java.awt.Color;

public class Player {
    Color color;
    ArrayList<ResourceCard> resourceHand;
    ArrayList<DevelopmentCard> developmentCardHand;
    ArrayList<Settlement> settlements;
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
