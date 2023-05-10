import Utils.ImageUtils;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ComponentStatusPanel extends JPanel {
	public ComponentStatusPanel(int componentId) {

		// Get Measurements using given Infrastructure Component ID
		ArrayList<Measurement> measurements = MeasurementRepository.getAllFromComponent(componentId);

		// Initialize Charts
		Chart pieChart = new Chart("Diskruimte", Chart.Type.PIECHART);
		Chart lineChart = new Chart("Processorbelasting", Chart.Type.LINECHART);

		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();
		DefaultPieDataset pieDataset = new DefaultPieDataset();

		// Format Measurement Objects into Datasets
		for (Measurement measurement : measurements) {
			System.out.println(measurement);
			lineDataset.addValue((double)measurement.getProcessorload(), "Test", measurement.getDate());
		}
		Measurement mostRecentMeasurement = measurements.get(measurements.size() - 1);
		pieDataset.setValue("Gebruikt", mostRecentMeasurement.getDiskspace());
		pieDataset.setValue("Over", 100); // @todo haal van infrastructuur component de totale geheugen op

		Panel chartsPanel = new Panel(new GridLayout(1, 2));
		chartsPanel.add(lineChart.createChart(lineDataset));
		chartsPanel.add(pieChart.createChart(pieDataset));

		this.setLayout(new GridLayout(3, 1));

		add(new JLabel(ImageUtils.getImageIcon("icons/dashboard.png", 150, 150))); // @todo component placeholder image
		add(chartsPanel); 
		add(new JLabel("Component status: conscious"));
	}
}
