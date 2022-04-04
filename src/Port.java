public class Port {

    ResourceCard specialty;
    int offer;

    int row;
    int col;

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
}
