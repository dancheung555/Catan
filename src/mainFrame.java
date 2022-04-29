import javax.swing.JFrame;
public class mainFrame extends JFrame
{
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    public mainFrame(String title)
    {
        super(title);
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new mainPanel());
        setResizable(false);
        setVisible(true);
    }
}
