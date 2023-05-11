import Utils.ImageUtils;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ComponentStatusPanel extends JPanel {
	public ComponentStatusPanel(int componentId) {

		// Retrieve all Measurements of the current Component
		ArrayList<Measurement> measurements = MeasurementRepository.getAllFromComponent(componentId);

		Panel chartsPanel = new Panel(new GridLayout(1, 2));

		// Initialize Charts
		Chart pieChart = new Chart("Diskruimte", Chart.Type.PIECHART);
		Chart lineChart = new Chart("Processorbelasting", Chart.Type.LINECHART);

		chartsPanel.add(lineChart.createChart(getDataSet(measurements, Chart.Type.LINECHART)));
		chartsPanel.add(pieChart.createChart(getDataSet(measurements, Chart.Type.PIECHART)));

		this.setLayout(new GridLayout(3, 1));

		add(new JLabel(ImageUtils.getImageIcon("icons/dashboard.png", 150, 150))); // @todo component placeholder image
		add(chartsPanel);
		add(new JLabel("Component status: conscious"));
	}

	private AbstractDataset getDataSet(ArrayList<Measurement> measurements, Chart.Type chartType) {
		if(measurements.size() == 0){
			return null;
		}

		Object dataset = new Object();

		if(chartType == Chart.Type.LINECHART) {
			dataset = new DefaultCategoryDataset();
			// Format Measurement Objects into Datasets
			for (Measurement measurement : measurements) {
				((DefaultCategoryDataset) dataset).addValue(measurement.getProcessorload(), "Test", measurement.getDate());
			}
		}
		else if(chartType == Chart.Type.PIECHART) {
			dataset = new DefaultPieDataset();
			Measurement mostRecentMeasurement = measurements.get(measurements.size() - 1);
			((DefaultPieDataset) dataset).setValue("Gebruikt", mostRecentMeasurement.getDiskspace());
			((DefaultPieDataset) dataset).setValue("Over", 500 - mostRecentMeasurement.getDiskspace()); // @todo haal van infrastructuur component de totale geheugen op
		}
		return (AbstractDataset) dataset;
	}
}
