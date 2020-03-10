import java.awt.*;
import javax.swing.*;

public class RPG extends JFrame {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public RPG() {
        setTitle("GPA Survivors");

        MainPanel panel = new MainPanel();
        Container contentPane = getContentPane();
        contentPane.add(panel);
        pack();
    }

    public static void main(String[] args) {
        RPG frame = new RPG();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
