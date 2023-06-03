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
		this.statusPanel = new InfrastructureStatusPanel(components);

		int delay = InfrastructureComponent.MeasurementIntervalMinutes * 1000; //milliseconds
		ActionListener taskPerformer = evt -> {
			updateComponentPanels();
		};

		new javax.swing.Timer(delay, taskPerformer).start();

		add(this.infrastructuurViewPanel);
		add(this.statusPanel);


	}

	// Refresh infrastructure components and current Status panel
	public void updateComponentPanels() {
		ArrayList<InfrastructureComponent> components = InfrastructureComponentRepository.findAll();

		if(this.infrastructuurViewPanel != null){
			remove(this.infrastructuurViewPanel);
			remove(this.statusPanel);
		}

		this.infrastructuurViewPanel = new InfrastructureComponentViewPanel(this, components);


		if((this.statusPanel instanceof ComponentStatusPanel)) {
			ComponentStatusPanel componentStatusPanel = (ComponentStatusPanel) this.statusPanel;
			InfrastructureComponent infrastructureComponent = new InfrastructureComponent(componentStatusPanel.infrastructureComponent.getId());
			if(!infrastructureComponent.isAvailable()){
				this.statusPanel = new InfrastructureStatusPanel(components);
			}

		}

		add(this.infrastructuurViewPanel);
		add(this.statusPanel);

		this.refresh();
	}

	public void setStatusPanel(JPanel statusPanel) {
		remove(this.statusPanel);
		this.statusPanel = statusPanel;
		add(this.statusPanel);

		this.refresh();
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

		remove(this.statusPanel);
		this.statusPanel = new ComponentStatusPanel(component.getId());
		add(this.statusPanel);

		this.refresh();
	}
	public void refresh() {
		revalidate();
		repaint();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
