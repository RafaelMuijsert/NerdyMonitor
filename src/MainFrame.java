import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	private static final String WINDOW_TITLE = "NerdyGadgets monitor";
	private NavigationBar navBar;
	private JPanel activeBody;

	public MainFrame(int width, int height) {
		setSize(width, height);
		setTitle(MainFrame.WINDOW_TITLE);
		// Centered window location
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Border layout
		setLayout(new BorderLayout());

		this.navBar = new NavigationBar(this);
		add(this.navBar, BorderLayout.WEST);

		setActiveBody(new TestPanel());

		setVisible(true);
	}

	public JPanel getActiveBody() {
		return activeBody;
	}
	public void setActiveBody(JPanel activeBody) {
		if(this.activeBody != null) {
			remove(this.activeBody);
		}

		this.activeBody = activeBody;
		add(this.activeBody, BorderLayout.CENTER);
	}
}
