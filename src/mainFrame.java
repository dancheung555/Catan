import javax.swing.JFrame;
public class mainFrame extends JFrame
{
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    mainPanel mp = new mainPanel();



    public mainFrame(String title)
    {
        super(title);
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mp.setBounds(780, 0, 1920, 1080);
        add(mp);
        setVisible(true);
    }
}
