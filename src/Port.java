public class Port {

    ResourceCard specialty;
    int offer;
    Intersection intersection;

    int x1, x2;
    int y1, y2;

    public Port(ResourceCard s, int o) {
        specialty = s;
        offer = o;
    }

    public void setCoords(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        main.inter[x1][y1].setPort(this);
        this.x2 = x2;
        this.y2 = y2;
        main.inter[x2][y2].setPort(this);
    }

    public ResourceCard getSpecialty() {
        return specialty;
    }

    public int getOffer() {
        return offer;
    }

    public Intersection getIntersection() { return intersection; }

}
