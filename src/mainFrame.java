import javax.swing.JFrame;
public class mainFrame extends JFrame
{
    private static final int WIDTH = 960;
    private static final int HEIGHT = 540;
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
