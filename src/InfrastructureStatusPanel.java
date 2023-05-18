import javax.swing.*;
import java.awt.*;

public class InfrastructureStatusPanel extends JPanel {
	private JLabel jlProcessorLoad;
	private JLabel jlDiskSpace;
	private JLabel jlUptime;
	public InfrastructureStatusPanel() {
		setLayout(new FlowLayout());
		System.out.println("Infrastructure Status Panel");
		add(new JLabel("Infrastructure status: alive"));
	}
}
