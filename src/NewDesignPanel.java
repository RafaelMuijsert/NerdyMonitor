import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewDesignPanel extends JPanel implements ActionListener {
	private JButton jb1;
	private JButton jb2;
	private MainFrame mainFrame;

	public NewDesignPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		centerPanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

		Dimension dimension = new Dimension(300, 300);

		this.jb1 = new JButton("Leeg ontwerp");
		jb1.setMinimumSize(dimension);
		jb1.addActionListener(this);
		this.jb2 = new JButton("Ontwerp op basis van beschikbaarheidspercentage");
		jb2.setMinimumSize(dimension);
		jb2.addActionListener(this);

		centerPanel.add(Box.createHorizontalGlue());
		centerPanel.add(jb1);
		centerPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		centerPanel.add(jb2);
		centerPanel.add(Box.createHorizontalGlue());

		add(Box.createVerticalGlue());
		add(centerPanel);
		add(Box.createVerticalGlue());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1) {
			this.mainFrame.setActiveBody(new EmptyDesignPanel());
		} else if(e.getSource()==jb2) {
			this.mainFrame.setActiveBody(new AvailabilityDesignPanel(this.mainFrame));
		}
	}
}
