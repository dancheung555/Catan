import javax.swing.JFrame;
public class mainFrame extends JFrame
{
    private static final int WIDTH = 1440;
    private static final int HEIGHT = 810;

    mainPanel mp = new mainPanel();



    public mainFrame(String title)
    {
        super(title);
        setSize(WIDTH + 14,HEIGHT + 36);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mp);
        setVisible(true);
    }
}
