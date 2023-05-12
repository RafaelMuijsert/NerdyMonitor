import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationBar extends JPanel implements ActionListener {
	private static final int ICON_SIZE = 32;
	private static final Color NAVIGATION_BAR_COLOR = Color.LIGHT_GRAY;
	private static final Color NAVIGATION_BAR_SELECTED_COLOR = Color.GREEN;
	private static final int NAVIGATION_BAR_MARGIN = 10;
	private static final int NAVIGATION_BAR_ROUNDING = 40;
	private MainFrame mainFrame;
	private JButton jbDashboard;
	private JButton jbNewDesign;
	private JButton jbOpenDesign;

	public NavigationBar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.jbDashboard = new JButton(ImageUtils.getImageIcon("icons/dashboard.png", NavigationBar.ICON_SIZE, NavigationBar.ICON_SIZE));
		this.jbDashboard.setBackground(NavigationBar.NAVIGATION_BAR_SELECTED_COLOR);
		this.jbDashboard.setOpaque(true);
		this.jbDashboard.addActionListener(this);
		this.jbNewDesign = new JButton(ImageUtils.getImageIcon("icons/new_design.png", NavigationBar.ICON_SIZE, NavigationBar.ICON_SIZE));
		this.jbNewDesign.setBackground(NavigationBar.NAVIGATION_BAR_COLOR);
		this.jbNewDesign.setOpaque(true);
		this.jbNewDesign.addActionListener(this);
		this.jbOpenDesign = new JButton(ImageUtils.getImageIcon("icons/open_design.png", NavigationBar.ICON_SIZE, NavigationBar.ICON_SIZE));
		this.jbOpenDesign.setBackground(NavigationBar.NAVIGATION_BAR_COLOR);
		this.jbOpenDesign.setOpaque(true);
		this.jbOpenDesign.addActionListener(this);


		add(new Box.Filler(
				new Dimension(10, 10),
				new Dimension(20, 20),
				new Dimension(30, 30)
		));
		add(this.jbDashboard);
		add(new Box.Filler(
				new Dimension(10, 10),
				new Dimension(20, 20),
				new Dimension(30, 30)
		));
		add(this.jbNewDesign);
		add(new Box.Filler(
				new Dimension(10, 10),
				new Dimension(20, 20),
				new Dimension(30, 30)
		));
		add(this.jbOpenDesign);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(NavigationBar.NAVIGATION_BAR_COLOR);
		g.fillRoundRect(
				0,
				0,
				g.getClipBounds().width,
				g.getClipBounds().height,
				NavigationBar.NAVIGATION_BAR_ROUNDING,
				NavigationBar.NAVIGATION_BAR_ROUNDING
		);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.jbOpenDesign.setBackground(NavigationBar.NAVIGATION_BAR_COLOR);
		this.jbNewDesign.setBackground(NavigationBar.NAVIGATION_BAR_COLOR);
		this.jbDashboard.setBackground(NavigationBar.NAVIGATION_BAR_COLOR);

		((JButton)e.getSource()).setBackground(NavigationBar.NAVIGATION_BAR_SELECTED_COLOR);
	}
}
