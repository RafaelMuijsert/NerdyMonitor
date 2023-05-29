
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ComponentViewPanel extends JPanel{

	public ComponentViewPanel(Object parentPanel, ArrayList<Component> components)
	{

		// Could not find any monitored components
		if(components == null || components.size() == 0){
			JLabel text = new JLabel("Kon geen Infrastructuur componenten vinden");
			text.setFont(new Font("Verdana",1,20));
			add(text);
			return;
		}

		JPanel content = new JPanel();
		content.setLayout(new GridLayout(0, 4));

		for (Component component: components) {

			ComponentPanel componentPanel =  new ComponentPanel(component);
			componentPanel.setSize(100, 100);
			componentPanel.addMouseListener((MouseListener) parentPanel);
			content.add(componentPanel);
		}

		JScrollPane scrollPane = new JScrollPane(content);
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		add(scrollPane);


		setLayout(new GridLayout(1, 2));
	}

}

