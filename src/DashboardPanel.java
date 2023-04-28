import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
	private static final Color DASHBOARD_BACKGROUND_COLOR = new Color(240, 240, 240);
	private ComponentViewPanel componentViewPanel;
	private ComponentStatusPanel componentStatusPanel;
	public DashboardPanel() {
		setBackground(DashboardPanel.DASHBOARD_BACKGROUND_COLOR);
		setLayout(new GridLayout(1, 2));

		this.componentViewPanel = new ComponentViewPanel();
		this.componentStatusPanel = new ComponentStatusPanel();

		add(this.componentViewPanel);
		add(this.componentStatusPanel);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
