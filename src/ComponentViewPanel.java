
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ComponentViewPanel extends JPanel{

	public ComponentViewPanel(
			Object parentPanel,
			ArrayList<Component> components
	)
	{

		// Could not find any monitored components
		if(infrastructureComponents.size() == 0){
			JLabel text = new JLabel("Kon geen Infrastructuur componenten vinden");
			text.setFont(new Font("Verdana",1,20));
			add(text);
			return;
		}

		JPanel content = new JPanel();
		content.setLayout(new GridLayout(0, 4, 0, 10));

		for (InfrastructureComponent infrastructureComponent: infrastructureComponents){
			InfrastructureComponentPanel componentPanel =  new InfrastructureComponentPanel(infrastructureComponent);
			if(infrastructureComponent.getUptime() == null){
				componentPanel.setBorder(new LineBorder(Color.RED, 5, true));
			}
			else {
				// On mouse click event, update Component Status panel
				componentPanel.addMouseListener( this );
			}

		for (Component component: components) {
			ComponentPanel componentPanel =  new ComponentPanel(component);

			componentPanel.addMouseListener((MouseListener) parentPanel);
			content.add(componentPanel);
		}

		JScrollPane scrollPane = new JScrollPane(content);
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		add(scrollPane);


		setLayout(new GridLayout(1, 2));
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof ComponentPanel) {
			InfrastructureComponent component = (InfrastructureComponent) ((ComponentPanel) e.getSource()).getComponent();

			this.dashboardPanel.setStatusPanel(new ComponentStatusPanel(component.getId()));
			revalidate();
			repaint();

		}
	}
}

