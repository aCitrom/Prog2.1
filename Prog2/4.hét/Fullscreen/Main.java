import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Main extends JFrame {
  JPanel contentPane = new JPanel();
  JButton fullscreenButton = new JButton("Fullscreen Mode");
  boolean fullScreen = false;
  int PrevX, PrevY, PrevWidth, PrevHeight;

  public static void main(String[] args) {
    Main frame = new Main();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 500);
    frame.setVisible(true);
  }

  public Main() {
    setContentPane(contentPane);

    fullscreenButton.addActionListener(e -> {
      if (fullScreen == false) {
        PrevX = getX();
        PrevY = getY();
        PrevWidth = getWidth();
        PrevHeight = getHeight();
        dispose();
        setUndecorated(true);
        setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit()
            .getScreenSize().height);
        setVisible(true);
        fullScreen = true;
      } else {
        setVisible(true);
        setBounds(PrevX, PrevY, PrevWidth, PrevHeight);
        dispose();
        setUndecorated(false);
        setVisible(true);
        fullScreen = false;
      }
    });
    contentPane.add(fullscreenButton);
    fullscreenButton.setVisible(true);
  }
}
