import javax.swing.*;
import java.awt.*;

public class OverviewPanel extends JPanel {
	private InfrastructureDesign infrastructureDesign;
	public OverviewPanel(InfrastructureDesign infrastructureDesign) {
		this.infrastructureDesign = infrastructureDesign;
		setLayout(new BorderLayout());
		add(new JLabel("Kosten ontwerp"), BorderLayout.CENTER);
		add(new JLabel("Overzicht"), BorderLayout.EAST);
	}
}
