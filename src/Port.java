public class Port {

    ResourceCard specialty;
    int offer;
    Intersection intersection;

    int row;
    int col;

    public Port(ResourceCard s, int o) {
        specialty = s;
        offer = o;
    }

    public Port(ResourceCard s, int o, int r, int c) {
        specialty = s;
        offer = o;
        row = r;
        col = c;
    }

    public ResourceCard getSpecialty() {
        return specialty;
    }

    public int getOffer() {
        return offer;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Intersection getIntersection() { return intersection; }

}
