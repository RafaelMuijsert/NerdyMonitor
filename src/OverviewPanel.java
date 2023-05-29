import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class OverviewPanel extends JPanel implements ActionListener {
	private InfrastructureDesign infrastructureDesign;
	private final Object goBackPanel;
	private final MainFrame parentPanel;
	private ArrayList<InfrastructureComponent> components;
	private static final Font TITLE_FONT = new Font("Montserrat", Font.PLAIN, 32);
	private JButton jbTerug;
	private JButton jbOpslaan ;

	public OverviewPanel(InfrastructureDesign infrastructureDesign, JPanel goBackPanel, MainFrame parentPanel) {
		this.infrastructureDesign = infrastructureDesign;
		this.goBackPanel = goBackPanel;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());

		JPanel jpCost = new JPanel();
		JPanel jpOverview = new JPanel();

		jpCost.setLayout(new BorderLayout());
		JLabel jlCost = new JLabel("Kosten ontwerp", SwingConstants.CENTER);
		jlCost.setFont(OverviewPanel.TITLE_FONT);
		jpCost.add(jlCost, BorderLayout.NORTH);

		JPanel jpCostOverview = new JPanel();
		GridLayout glCostOverview = new GridLayout(0, 1);
		glCostOverview.setVgap(4);
		jpCostOverview.setLayout(glCostOverview);

		ArrayList<Integer> componentIds = new ArrayList<>();
		for (Component component : this.infrastructureDesign.getComponents()) {
			if(componentIds.get(component.getId()) == null){
				componentIds.add(component.getId());
			}
			jpCostOverview.add(new ComponentOverview(component, 1));
		}
//		for(Firewall firewall: this.infrastructureDesign.getFirewalls()) {
//			jpCostOverview.add(new ComponentOverview(firewall, 1));
//		}
//
//		for(Databaseserver database: this.infrastructureDesign.getDatabases()) {
//			jpCostOverview.add(new ComponentOverview(database, 1));
//		}
//
//		for(Webserver webserver: this.infrastructureDesign.getWebservers()) {
//			jpCostOverview.add(new ComponentOverview(webserver, 1));
//		}

		JScrollPane scrollPane = new JScrollPane(jpCostOverview);
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		jpCost.add(scrollPane, BorderLayout.CENTER);


		BorderLayout jpOverviewLayout = new BorderLayout();
		jpOverviewLayout.setHgap(10);
		jpOverviewLayout.setVgap(10);
		jpOverview.setLayout(jpOverviewLayout);

		JPanel jpOverviewControls = new JPanel();
		JPanel jpOverviewInfo = new JPanel();

		jpOverviewControls.setLayout(new FlowLayout());

		jbTerug = new JButton("Terug");
		jbOpslaan = new JButton("Opslaan");

		jbTerug.addActionListener(this);
		jbOpslaan.addActionListener(this);
		jpOverviewControls.add(jbTerug);
		jpOverviewControls.add(jbOpslaan);

		jpOverviewInfo.setLayout(new GridLayout(2, 0));
		JLabel jlOverview = new JLabel("Overzicht", SwingConstants.CENTER);
		jlOverview.setFont(OverviewPanel.TITLE_FONT);
		jpOverviewInfo.add(jlOverview);

		jpOverview.add(jpOverviewInfo, BorderLayout.NORTH);
		//

		JPanel jpOverviewSummary = new JPanel();
		GridLayout grOverviewSummary = new GridLayout(0, 2);
		grOverviewSummary.setVgap(4);
		jpOverviewSummary.setLayout(grOverviewSummary);

		// @todo dynamishc
		jpOverviewSummary.add(new JLabel("Totaalprijs"));
		jpOverviewSummary.add(new JLabel("€ " +String.valueOf(infrastructureDesign.getTotalCost())));
		jpOverviewSummary.add(new JLabel("Totale beschikbaarheid"));
		jpOverviewSummary.add(new JLabel(String.valueOf(infrastructureDesign.getTotalAvailability()) + "%"));
		jpOverviewSummary.add(new JLabel("Totaal componenten"));
		jpOverviewSummary.add(new JLabel(String.valueOf(infrastructureDesign.getComponents().size())));
		//
		jpOverview.add(jpOverviewSummary, BorderLayout.CENTER);
		jpOverview.add(jpOverviewControls, BorderLayout.SOUTH);

		jpOverview.setPreferredSize(new Dimension(500, 0));

		add(jpCost, BorderLayout.CENTER);
		add(jpOverview, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbTerug){
			this.parentPanel.setActiveBody((JPanel) this.goBackPanel);
		}
	}
}
