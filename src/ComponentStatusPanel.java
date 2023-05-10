import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;

public class ComponentStatusPanel extends JPanel {
	public ComponentStatusPanel() {

		Chart pieChart = new Chart("Diskruimte", Chart.PIECHART);
		Chart lineChart = new Chart("Processorbelasting", Chart.LINECHART);

		Panel chartsPanel = new Panel(new GridLayout(1, 2));

		chartsPanel.add(lineChart.createChart(null));
		chartsPanel.add(pieChart.createChart(null));

//		setBackground(Color.GRAY); // @todo remove this

		this.setLayout(new GridLayout(3, 1));

		add(new JLabel(ImageUtils.getImageIcon("icons/dashboard.png", 150, 150))); // @todo placeholder image
		add(chartsPanel); // @todo chart panels
		add(new JLabel("Component status: conscious"));


	}
}
