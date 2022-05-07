import java.awt.Polygon;
import java.awt.Color;

public class Road {

    int x1, y1, x2, y2;

    Player owner;
    Color color;
    Polygon p;

    final int HEIGHT = 810;
    final int marg = 90;
    private int h = (HEIGHT - marg) / 16;
    private int w = (int) ((HEIGHT - marg) * 1.0825 / 10);

    public Road(int r1, int c1, int r2, int c2, Player o) {
        x1 = r1;
        y1 = c1;
        x2 = r2;
        y2 = c2;
        owner = o;
        color = owner.getColor();

        int xpos = (int) ((x1 + x2) * w / 2) + 608;
        int ypos = (int) ((y1 + y2) * h / 2) + 45;
        if (x1 == x2) {
            p = new Polygon(
                    new int[]{xpos - 8, xpos - 8, xpos + 8, xpos + 8},
                    new int[]{ypos - 30, ypos + 30, ypos + 30, ypos - 30},
                    4
            );
        }
        else if ((x1 - x2) / (y1 - y2) == 1) {
            p = new Polygon(
                    new int[]{xpos - 22, xpos - 30, xpos + 22, xpos + 30},
                    new int[]{ypos - 21, ypos - 6, ypos + 21, ypos + 6},
                    4
            );
        }
        else {
            p = new Polygon(
                    new int[]{xpos - 30, xpos - 22, xpos + 30, xpos + 22},
                    new int[]{ypos + 6, ypos + 21, ypos - 6, ypos - 21},
                    4
            );
        }
    }

    public boolean equals(Road r) {
        return r.owner == owner &&
                ((r.x1 == x1 && r.y1 == y1 && r.x2 == x2 && r.y2 == y2) ||
                (r.x1 == x2 && r.y1 == y2 && r.x2 == x1 && r.y2 == y1));
    }

}
