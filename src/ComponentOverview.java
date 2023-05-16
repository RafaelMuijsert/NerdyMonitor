import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
	private static final Font COMPONENT_DETAIL_FONT = new Font("Montserrat", Font.PLAIN, 18);
	public ComponentOverview(InfrastructureComponent infrastructureComponent, int quantity) {
		// Padding
		this.setBorder(new EmptyBorder(16, 16, 16, 16));

		this.infrastructureComponent = infrastructureComponent;
		this.quantity = quantity;
		this.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.weightx = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 0;

		constraints.anchor = GridBagConstraints.LINE_START;
		JLabel img = new JLabel();
		img.setIcon(Utils.ImageUtils.getImageIcon("icons/webserver.png", 128, 128));
		this.add(img, constraints);

		constraints.weighty = 0.5;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		this.jlComponentName = new JLabel("Webserver 01");
		this.jlComponentName.setFont(new Font("Montserrat", Font.BOLD, 24));

		this.add(this.jlComponentName, constraints);

		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		this.jlAvailability = new JLabel("Beschikbaarheid: 58%");
		this.jlAvailability.setFont(ComponentOverview.COMPONENT_DETAIL_FONT);
		this.add(this.jlAvailability, constraints);

		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.LAST_LINE_START;
		this.jlComponentNumber = new JLabel("Componentnummer");
		this.jlComponentNumber.setFont(ComponentOverview.COMPONENT_DETAIL_FONT);
		this.add(this.jlComponentNumber, constraints);

		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.FIRST_LINE_END;
		this.jlPrice = new JLabel("10$");
		this.jlPrice.setFont(ComponentOverview.COMPONENT_DETAIL_FONT);
		this.add(this.jlPrice, constraints);

		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		this.jlQuantity = new JLabel("1x");
		this.jlQuantity.setFont(ComponentOverview.COMPONENT_DETAIL_FONT);
		this.add(jlQuantity, constraints);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		((Graphics2D)g).setStroke(new BasicStroke(4));
		g.drawLine(0, this.getHeight(), this.getWidth(), this.getHeight());
	}
}
