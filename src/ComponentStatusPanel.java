import javax.swing.*;
import java.awt.*;

public class ComponentStatusPanel extends JPanel {
	public ComponentStatusPanel() {
		System.out.println("Component Status Panel");
		setBackground(Color.CYAN);
		add(new JLabel("Component status: conscious"));
	}
}
