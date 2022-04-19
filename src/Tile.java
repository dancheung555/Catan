import java.util.ArrayList;

public class Tile {

    ResourceCard resourceType;
    ArrayList<Settlement> settlements;

    int row;
    int col;

    int pipNumber;

    public Tile() {
        resourceType = null;
    }

    public Tile(ResourceCard rType) {
        resourceType = rType;
    }

    public Tile(int x, int y) {
        row = x;
        col = y;
    }

    public Tile(int x, int y, ResourceCard rType, int pip) {
        row = x;
        col = y;
        resourceType = rType;
        pipNumber = pip;
    }

    public int getPipNumber() {
        return pipNumber;
    }

    public void setPipNumber(int p) {
        pipNumber = p;
    }

    public void distributeResources() {
        for (Settlement s: settlements) {
            s.getOwner().addResourceCard(resourceType, s.getTier());
        }
    }

    public ResourceCard getResourceType() {
        return resourceType;
    }



}
