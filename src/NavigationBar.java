import javax.swing.*;
import java.awt.*;

public class NavigationBar extends JPanel {
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

		this.jbDashboard = new JButton(loadIcon("icons/dashboard.png"));
		this.jbDashboard.setBackground(NavigationBar.NAVIGATION_BAR_SELECTED_COLOR);
		this.jbDashboard.setOpaque(true);
		this.jbNewDesign = new JButton(loadIcon("icons/new_design.png"));
		this.jbNewDesign.setBackground(NavigationBar.NAVIGATION_BAR_COLOR);
		this.jbNewDesign.setOpaque(true);
		this.jbOpenDesign = new JButton(loadIcon("icons/open_design.png"));
		this.jbOpenDesign.setBackground(NavigationBar.NAVIGATION_BAR_COLOR);
		this.jbOpenDesign.setOpaque(true);


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

	private ImageIcon loadIcon(String path) {
		ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(
				NavigationBar.ICON_SIZE,
				NavigationBar.ICON_SIZE,
				java.awt.Image.SCALE_SMOOTH
		); // scale it the smooth way
		return new ImageIcon(newimg);  // transform it back
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
}
