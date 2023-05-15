import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OverviewPanel extends JPanel {
	private InfrastructureDesign infrastructureDesign;
	private ArrayList<InfrastructureComponent> components;
	private static final Font TITLE_FONT = new Font("Montserrat", Font.PLAIN, 32);
	public OverviewPanel(InfrastructureDesign infrastructureDesign) {
		this.infrastructureDesign = infrastructureDesign;
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
		for(int i = 0; i < 80; i++) {
			jpCostOverview.add(new ComponentOverview(new Firewall("asdf"), 1));
		}
		jpCost.add(new JScrollPane(jpCostOverview), BorderLayout.CENTER);
//		jpCost.add(jpCostOverview, BorderLayout.CENTER);


		BorderLayout jpOverviewLayout = new BorderLayout();
		jpOverviewLayout.setHgap(10);
		jpOverviewLayout.setVgap(10);
		jpOverview.setLayout(jpOverviewLayout);

		JPanel jpOverviewControls = new JPanel();
		JPanel jpOverviewInfo = new JPanel();

		jpOverviewControls.setLayout(new FlowLayout());
		jpOverviewControls.add(new JButton("Terug"));
		jpOverviewControls.add(new JButton("Opslaan"));

		jpOverviewInfo.setLayout(new GridLayout(2, 0));
		JLabel jlOverview = new JLabel("Overzicht", SwingConstants.CENTER);
		jlOverview.setFont(OverviewPanel.TITLE_FONT);
		jpOverviewInfo.add(jlOverview);

		jpOverview.add(jpOverviewInfo, BorderLayout.NORTH);
		jpOverview.add(jpOverviewControls, BorderLayout.SOUTH);

		jpOverview.setPreferredSize(new Dimension(500, 0));

		add(jpCost, BorderLayout.CENTER);
		add(jpOverview, BorderLayout.EAST);
	}
}
