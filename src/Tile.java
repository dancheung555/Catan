import java.util.ArrayList;

public class Tile {
    ResourceCard resourceType;
    int r;
    int c;
    int pipNumber;

    public Tile() {
        resourceType = null;
    }

    public Tile(ResourceCard rType) {
        resourceType = rType;
    }

    public void setCoords(int x, int y) {
        r = x;
        c = y;
        System.out.println("" + r + " " + c);
    }

    public int getPipNumber() {
        return pipNumber;
    }

    public void setPipNumber(int p) {
        pipNumber = p;
    }

    public void distributeResources() {
        if (!(main.robberx == r && main.robbery == c)) {
            if (main.inter[r - 1][c - 1].settlement != null) {
                main.inter[r - 1][c - 1].settlement.owner.addResourceCard(resourceType, main.inter[r - 1][c - 1].settlement.tier);
                main.removeFromBank(resourceType, main.inter[r - 1][c - 1].settlement.tier);
                System.out.println("distributed left up");
            }
            if (main.inter[r - 1][c + 1].settlement != null) {
                main.inter[r - 1][c + 1].settlement.owner.addResourceCard(resourceType, main.inter[r - 1][c + 1].settlement.tier);
                main.removeFromBank(resourceType, main.inter[r - 1][c + 1].settlement.tier);
                System.out.println("distributed left down");
            }
            if (main.inter[r][c + 2].settlement != null) {
                main.inter[r][c + 2].settlement.owner.addResourceCard(resourceType, main.inter[r][c + 2].settlement.tier);
                main.removeFromBank(resourceType, main.inter[r][c + 2].settlement.tier);
                System.out.println("distributed middle down");
            }
            if (main.inter[r + 1][c - 1].settlement != null) {
                main.inter[r + 1][c - 1].settlement.owner.addResourceCard(resourceType, main.inter[r + 1][c - 1].settlement.tier);
                main.removeFromBank(resourceType, main.inter[r + 1][c - 1].settlement.tier);
                System.out.println("distributed right down");
            }
            if (main.inter[r + 1][c + 1].settlement != null) {
                main.inter[r + 1][c + 1].settlement.owner.addResourceCard(resourceType, main.inter[r + 1][c + 1].settlement.tier);
                main.removeFromBank(resourceType, main.inter[r + 1][c + 1].settlement.tier);
                System.out.println("distributed right up");
            }
            if (main.inter[r][c - 2].settlement != null) {
                main.inter[r][c - 2].settlement.owner.addResourceCard(resourceType, main.inter[r][c - 2].settlement.tier);
                main.removeFromBank(resourceType, main.inter[r][c - 2].settlement.tier);
                System.out.println("distributed middle up");
            }
        }
    }

    public ResourceCard getResourceType() {
        return resourceType;
    }




}
