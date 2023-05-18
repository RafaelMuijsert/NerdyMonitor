import Utils.ImageUtils;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

public class ComponentStatusPanel extends JPanel {
	public ComponentStatusPanel(int infrastructureComponentId) {
		Database db = new Database();
		try{
		}catch (Exception e) {

		}
		// Retrieve all Measurements of the current Component
		ArrayList<Measurement> measurements = MeasurementRepository.getAllFromComponent(infrastructureComponentId);
		Measurement mostRecentMeasurement = (measurements.size() >= 1) ? measurements.get(measurements.size() - 1) : null;
		MatteBorder borderTop = new MatteBorder(1, 0, 0, 0, Color.black);
		JPanel chartsPanel = new JPanel(new GridLayout(1, 2));

		this.setLayout(new GridLayout(3, 1));

		// Initialize Charts
		Chart pieChart = new Chart("Diskruimte", Chart.Type.PIECHART, this.getBackground());
		Chart lineChart = new Chart("Processorbelasting", Chart.Type.LINECHART, this.getBackground());

		chartsPanel.setBorder(borderTop);
		chartsPanel.add(lineChart.createChart(getDataSet(measurements, Chart.Type.LINECHART)));
		chartsPanel.add(pieChart.createChart(getDataSet(measurements, Chart.Type.PIECHART)));

		JPanel costsPanel = new JPanel();
		costsPanel.setBorder(borderTop);
		costsPanel.setLayout(new BoxLayout(costsPanel, BoxLayout.Y_AXIS));

		costsPanel.add(new JLabel("Processorbelasting: " + mostRecentMeasurement.getProcessorload() + " %"));
		costsPanel.add(new JLabel("CPU temperatuur: " + mostRecentMeasurement.getProcessorload() + " \u2103"));
		costsPanel.add(new JLabel("Diskruimte: " + mostRecentMeasurement.getUsedDiskspaceInGB() + " GB"));
		costsPanel.add(new JLabel("Uptime: " + mostRecentMeasurement.getUptime()));


		add(new JLabel(ImageUtils.getImageIcon("icons/ComponentTypes/"+ 1 +".png", 150, 150))); // @todo component placeholder image
		add(chartsPanel);
		add(costsPanel);
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
