import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OverviewPanel extends JPanel {
	private InfrastructureDesign infrastructureDesign;
	private ArrayList<InfrastructureComponent> components;
	public OverviewPanel(InfrastructureDesign infrastructureDesign) {
		this.infrastructureDesign = infrastructureDesign;
		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		JPanel jpCost = new JPanel();
		JPanel jpOverview = new JPanel();

		jpCost.setLayout(new GridLayout(0, 1));
		JLabel jlCost = new JLabel("Kosten ontwerp");
		jlCost.setFont(new Font("Montserrat", Font.PLAIN, 32));
		jpCost.add(jlCost);

		jpOverview.setLayout(new GridLayout(0, 2));
		JLabel jlOverview = new JLabel("Overzicht");
		jlOverview.setFont(new Font("Montserrat", Font.PLAIN, 32));
		jpOverview.add(jlOverview);
		jpOverview.add(new JLabel(""));
		jpOverview.add(new JLabel("Totaalprijs"));
		jpOverview.add(new JLabel("$50"));
		jpOverview.add(new JLabel("Totale beschikbaarheid"));
		jpOverview.add(new JLabel("99.98%"));
		jpOverview.add(new JLabel("Totaal componenten"));
		jpOverview.add(new JLabel("4"));

		constraints.gridwidth = 1;
		add(jpCost);
		constraints.gridwidth = 2;
		add(jpOverview);
	}
}
