import Utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationBar extends JPanel implements ActionListener {
	private static final int ICON_SIZE = 32;
	private static final Color NAVIGATION_BAR_COLOR = new Color(MaterialColors.SHADE_400[6]);
	private static final Color NAVIGATION_BAR_SELECTED_COLOR = new Color(MaterialColors.SHADE_100[6]);
	private static final int NAVIGATION_BAR_ROUNDING = 48;
	private MainFrame mainFrame;
	private JButton jbDashboard;
	private JButton jbNewDesign;
	private JButton jbOpenDesign;

	public NavigationBar(MainFrame mainFrame) {
		this.setBorder(new LineBorder(new Color(240, 240, 240), 8, true));
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

		if(e.getSource() == this.jbDashboard) {
			this.mainFrame.setActiveBody(new DashboardPanel());
		} else if(e.getSource() == this.jbNewDesign) {
			this.mainFrame.setActiveBody(new NewDesignPanel(this.mainFrame));
		} else if(e.getSource() == this.jbOpenDesign) {

			// Don't allow user to edit files
			UIManager.put("FileChooser.readOnly", Boolean.TRUE);

			JFileChooser jFileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files", "json");
			jFileChooser.setFileFilter(filter);

			jFileChooser.showOpenDialog(this.mainFrame);
			if(jFileChooser.getSelectedFile() == null){
				return;
			}
			String fileName = jFileChooser.getSelectedFile().getPath();
			String fileType = fileName.substring(fileName.lastIndexOf("."),fileName.length());

			// We only allow JSON file types!!
			if(!fileType.equals(".json")){
				JOptionPane.showMessageDialog(this.mainFrame, "Je kan alleen JSON bestanden openen");
				return;
			}

			// Load the existing Design
			try{
				InfrastructureDesign infrastructureDesign = new InfrastructureDesign(true);

				boolean successfullyImported = infrastructureDesign.loadDesign(jFileChooser.getSelectedFile().getPath());
				if(!successfullyImported){
					JOptionPane.showMessageDialog(this.mainFrame, "Het geselecteerde Infrastructuur ontwerp is niet in het juiste formaat!");
					return;
				}

				this.jbOpenDesign.setBackground(NavigationBar.NAVIGATION_BAR_COLOR);
				this.jbNewDesign.setBackground(NavigationBar.NAVIGATION_BAR_SELECTED_COLOR);

				this.mainFrame.setActiveBody(new EmptyDesignPanel(this.mainFrame, infrastructureDesign.getComponents()));
			}
			catch (Exception ex){
				JOptionPane.showMessageDialog(this.mainFrame, "Er is een fout opgetreden");
			}

		}
	}
}
