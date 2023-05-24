import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DashboardPanel extends JPanel implements ActionListener, MouseListener {
	private static final Color DASHBOARD_BACKGROUND_COLOR = new Color(240, 240, 240);
	private InfrastructureComponentViewPanel infrastructuurViewPanel;
	private JPanel statusPanel;
	public DashboardPanel() {
		setBackground(DashboardPanel.DASHBOARD_BACKGROUND_COLOR);
		setLayout(new GridLayout(1, 2));
		ArrayList<InfrastructureComponent> components = InfrastructureComponentRepository.findAll();

		this.infrastructuurViewPanel = new InfrastructureComponentViewPanel(this, components);
		this.statusPanel = new InfrastructureStatusPanel();

		add(this.infrastructuurViewPanel);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// A component has been clicked
		if(e.getSource() instanceof ComponentPanel) {
			Component component = ((ComponentPanel) e.getSource()).getComponent();
			updateStatusPanel(component);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	public void updateStatusPanel(Component component){
		setStatusPanel(new ComponentStatusPanel(component.getId()));
		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
