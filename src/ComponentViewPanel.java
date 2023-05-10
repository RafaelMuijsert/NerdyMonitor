import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComponentViewPanel extends JPanel implements ActionListener {
	// Deze buttons zijn om de navigatie van het dashboard te testen
	// Vervang deze met de daadwerkelijke componenten, m.b.v het TO
	private JButton jbComponent;
	private DashboardPanel dashboardPanel;
	public ComponentViewPanel(DashboardPanel dashboardPanel) {
		// DashboardPanel is nodig om de ComponentStatusPanel aan te passen
		// als op een infrastructuurcomponent wordt geklikt.
		this.dashboardPanel = dashboardPanel;
		// Dit is allemaal voor het testen van het dashboard
		// Verwijder alles bij daadwerkelijke implementatie
		setLayout(new FlowLayout());
		setBackground(Color.PINK);
		this.jbComponent = new JButton("Infrastructure Component");
		this.jbComponent.addActionListener(this);
		add(this.jbComponent);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Wederom voor het testen van het dashboard
		// Verwijder dit
		if(e.getSource() == this.jbComponent) {
			this.dashboardPanel.setStatusPanel(new ComponentStatusPanel(1));
			revalidate();
			repaint();
		}
	}
}

