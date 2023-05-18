
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ComponentViewPanel extends JPanel implements ActionListener, MouseListener{
	private DashboardPanel dashboardPanel;
	public ComponentViewPanel(DashboardPanel dashboardPanel) {
		ArrayList<InfrastructureComponent> infrastructureComponents = InfrastructureComponentRepository.findAll();

		// Could not find any monitored components
		if(infrastructureComponents.size() == 0){
			return;
		}

		JPanel content = new JPanel();
		content.setLayout(new GridLayout(0, 4, 0, 10));
		//		ComponentPanel[] componentPanels = new Com[infrastructureComponents.size()];
		for (InfrastructureComponent infrastructureComponent: infrastructureComponents){
			InfrastructureComponentPanel componentPanel =  new InfrastructureComponentPanel(infrastructureComponent);
			if(infrastructureComponent.getUptime() == null){
				componentPanel.setBorder(new LineBorder(Color.RED, 5, true));
			}
			else {
				// On mouse click event, update Component Status panel
				componentPanel.addMouseListener( this );
			}

			content.add(componentPanel);
		}

		JScrollPane scrollPane = new JScrollPane(content);
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		add(scrollPane);

		this.dashboardPanel = dashboardPanel;

		setLayout(new GridLayout(1, 2));
	}

	public void updateStatusPanel(Component component){
		this.dashboardPanel.setStatusPanel(new ComponentStatusPanel(component.getId()));
		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Wederom voor het testen van het dashboard
		// Verwijder dit

		if (e.getSource() instanceof ComponentPanel) {
			InfrastructureComponent component = (InfrastructureComponent) ((ComponentPanel) e.getSource()).getComponent();

			this.dashboardPanel.setStatusPanel(new ComponentStatusPanel(component.getId()));
			revalidate();
			repaint();

		}
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

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}

