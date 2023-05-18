import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

public class ComponentStatusPanel extends JPanel {
	public ComponentStatusPanel(int infrastructureComponentId) {

		// Get Component from database
		InfrastructureComponent infrastructureComponent = new InfrastructureComponent(infrastructureComponentId);

		// Retrieve all Measurements of the current Component
		ArrayList<Measurement> measurements = MeasurementRepository.getAllFromComponent(infrastructureComponentId);
		Measurement mostRecentMeasurement = (measurements.size() >= 1) ? measurements.get(measurements.size() - 1) : null;
		if(mostRecentMeasurement == null) {
			return;
		}

		MatteBorder borderTop = new MatteBorder(1, 0, 0, 0, Color.black);
		JPanel chartsPanel = new JPanel(new GridLayout(1, 2));

		this.setLayout(new GridLayout(3, 1));

		// Initialize Charts
		Chart pieChart = new Chart("Diskruimte", Chart.Type.PIECHART, this.getBackground());
		Chart lineChart = new Chart("Processorbelasting", Chart.Type.LINECHART, this.getBackground());

		chartsPanel.setBorder(borderTop);
		chartsPanel.add(lineChart.createChart(getDataSet(measurements, Chart.Type.LINECHART)));
		chartsPanel.add(pieChart.createChart(getDataSet(measurements, Chart.Type.PIECHART)));

		JPanel detailsPanel = new JPanel();
		detailsPanel.setBorder(borderTop);
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

		detailsPanel.add(new JLabel("Processorbelasting: " + mostRecentMeasurement.getProcessorload(true) ));
		detailsPanel.add(new JLabel("CPU temperatuur: " + mostRecentMeasurement.getProcessorload() + " \u2103"));
		detailsPanel.add(new JLabel("Diskruimte: " + mostRecentMeasurement.getUsedDiskspaceInGB(true)));
		detailsPanel.add(new JLabel("Uptime: " + mostRecentMeasurement.getUptime()));


		add(new ComponentPanel(infrastructureComponent));
		add(chartsPanel);
		add(detailsPanel);
	}

	private AbstractDataset getDataSet(ArrayList<Measurement> measurements, Chart.Type chartType) {
		//  Can't create datasets without data being provided : )
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
			((DefaultPieDataset) dataset).setValue("Space left", mostRecentMeasurement.getTotalDiskspaceInGB() - mostRecentMeasurement.getUsedDiskspaceInGB());
		}
		return (AbstractDataset) dataset;
	}
}
