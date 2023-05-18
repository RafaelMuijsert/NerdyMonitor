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
		Measurement mostRecentMeasurement = (measurements.size() >= 1) ? measurements.get(measurements.size() - 1) : null;
		Panel chartsPanel = new Panel(new GridLayout(1, 2));

		this.setLayout(new GridLayout(3, 1));

		// Initialize Charts
		Chart pieChart = new Chart("Diskruimte", Chart.Type.PIECHART, this.getBackground());
		Chart lineChart = new Chart("Processorbelasting", Chart.Type.LINECHART, this.getBackground());

		chartsPanel.add(lineChart.createChart(getDataSet(measurements, Chart.Type.LINECHART)));
		chartsPanel.add(pieChart.createChart(getDataSet(measurements, Chart.Type.PIECHART)));

		Panel costsPanel = new Panel(new FlowLayout());
		costsPanel.add(new JLabel("Processorbelasting: " + mostRecentMeasurement.getProcessorload()));
		costsPanel.add(new JLabel("CPU temp: " + mostRecentMeasurement.getProcessorload()));

		add(new JLabel(ImageUtils.getImageIcon("icons/ComponentTypes/"+ 1 +".png", 150, 150))); // @todo component placeholder image
		add(chartsPanel);
		add(new JLabel());
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
				((DefaultCategoryDataset) dataset).addValue(measurement.getProcessorload(), "Processorbelasting", measurement.getDate());
			}
		}
		else if(chartType == Chart.Type.PIECHART) {
			dataset = new DefaultPieDataset();
			Measurement mostRecentMeasurement = measurements.get(measurements.size() - 1);
			((DefaultPieDataset) dataset).setValue("Used space", mostRecentMeasurement.getUsedDiskspaceInGB());
			((DefaultPieDataset) dataset).setValue("Free space", 500 - mostRecentMeasurement.getUsedDiskspaceInGB()); // @todo haal van infrastructuur component de totale geheugen op
		}
		return (AbstractDataset) dataset;
	}
}
