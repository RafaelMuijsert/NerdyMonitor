import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
	private static final Color DASHBOARD_BACKGROUND_COLOR = new Color(240, 240, 240);
	private ComponentViewPanel componentViewPanel;
	private JPanel statusPanel;
	public DashboardPanel() {
		setBackground(DashboardPanel.DASHBOARD_BACKGROUND_COLOR);
		setLayout(new GridLayout(1, 2));

		this.componentViewPanel = new ComponentViewPanel(this);
		this.statusPanel = new InfrastructureStatusPanel();

		add(this.componentViewPanel);
		add(this.statusPanel);
	}

	public void setStatusPanel(JPanel statusPanel) {
		remove(this.statusPanel);
		this.statusPanel = statusPanel;
		add(this.statusPanel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
