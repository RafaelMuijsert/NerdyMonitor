import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ComponentOverview extends JPanel {
	private InfrastructureComponent infrastructureComponent;
	private int quantity;
	private JLabel jlComponentName;
	private JLabel jlAvailability;
	private JLabel jlComponentNumber;
	private JLabel jlPrice;
	private JLabel jlQuantity;
	public ComponentOverview(InfrastructureComponent infrastructureComponent, int quantity) {
		this.infrastructureComponent = infrastructureComponent;
		this.quantity = quantity;
		this.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.weightx = 0.5;
		constraints.gridx = 0;
		this.jlComponentName = new JLabel("Component Name");
		this.jlComponentName.setFont(new Font("Montserrat", Font.PLAIN, 24));

		this.add(this.jlComponentName);

		constraints.gridx = 1;
		this.add(new JLabel("Joling"), constraints);

		constraints.gridx = 2;
		this.add(new JLabel("69"), constraints);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.RED);
		g.fillRect(0, 0, this.getHeight() - 4, this.getHeight() - 4);

		g.setColor(Color.BLACK);
		((Graphics2D)g).setStroke(new BasicStroke(4));
		g.drawLine(0, this.getHeight(), this.getWidth(), this.getHeight());
	}
}
