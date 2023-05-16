import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	private static final String WINDOW_TITLE = "NerdyGadgets monitor";
	private NavigationBar navBar;
	private JPanel activeBody;

	public MainFrame() {
		Database db = new Database();
		setTitle(MainFrame.WINDOW_TITLE);
		setResizable(false);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		// Centered window location
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Border layout
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(16);
		borderLayout.setVgap(16);
		setLayout(borderLayout);

		this.navBar = new NavigationBar(this);
		add(this.navBar, BorderLayout.WEST);

//		setActiveBody(new DashboardPanel());
		setActiveBody(new OverviewPanel(new InfrastructureDesign()));

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
