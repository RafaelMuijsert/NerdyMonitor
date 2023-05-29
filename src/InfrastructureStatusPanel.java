import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class InfrastructureStatusPanel extends JPanel {
	private JLabel jlProcessorLoad;
	private JLabel jlDiskSpace;
	private JLabel jlUptime;

	private int availableDiskspace, totalDiskspace, avgProcessorLoad, avgProcessorTemp;
	public InfrastructureStatusPanel(      ArrayList<InfrastructureComponent> components) {
		// Get all components which are bweing monitored


		if(components == null || components.size() == 0) {
			JLabel text = new JLabel("Er is een fout opgetreden, probeer het later opnieuw");
			text.setFont(new Font("Verdana",1,20));
			add(text);
			return;
		}

		MatteBorder borderTop = new MatteBorder(1, 0, 0, 0, Color.black);
		JPanel chartsPanel = new JPanel(new GridLayout(1, 2));

		this.setLayout(new GridLayout(3, 1));

		// Initialize Charts
		Chart pieChart = new Chart("Diskruimte", Chart.Type.PIECHART, this.getBackground());
		Chart lineChart = new Chart("Processorbelasting", Chart.Type.LINECHART, this.getBackground());

		chartsPanel.setBorder(borderTop);
		chartsPanel.add(lineChart.createChart(getDataSet(components, Chart.Type.LINECHART)));
		chartsPanel.add(pieChart.createChart(getDataSet(components, Chart.Type.PIECHART)));

		String[][] data = {
				{ "Gemiddelde processorbelasting", avgProcessorLoad + "%"},
				{ "Gemiddelde CPU temperatuur", avgProcessorTemp + " \u2103" },
				{ "Diskruimte", availableDiskspace + "GB" + "/" + totalDiskspace+"GB"},
		};

		JTable details = new JTable(data, new String[] { "Measurement", "Value"} );
		details.setDefaultEditor(Object.class, null);

		// Disable user select
		details.setFocusable(false);
		details.setRowSelectionAllowed(false);
		JLabel title = new JLabel("Algemene Infrastructuur", SwingConstants.CENTER);
		title.setFont(new Font("Montserrat", Font.BOLD, 32));

		add(title);
		add(chartsPanel);
		add(details);
	}

	private AbstractDataset getDataSet(ArrayList<InfrastructureComponent> components, Chart.Type chartType) {
		//  Can't create datasets without data being provided : )
		if(components.size() == 0) {
			return null;
		}

		Object dataset = new Object();

		// Retrieve all Measurements of the current Component

		if(chartType == Chart.Type.LINECHART) {
			dataset = new DefaultCategoryDataset();
			// Format Measurement Objects into Datasets
			for (InfrastructureComponent component : components) {
				ArrayList<Measurement> measurements = MeasurementRepository.getAllFromComponent(component.getId(), 0);
				if(measurements == null || measurements.size() == 0){
					continue;
				}
				for (Measurement measurement : measurements) {
					avgProcessorLoad += measurement.getProcessorload();
					avgProcessorTemp += measurement.getTemperature();
					((DefaultCategoryDataset) dataset).addValue(measurement.getProcessorload(), component.getName(), measurement.getDate());
				}
				avgProcessorLoad /= measurements.size();
				avgProcessorTemp /= measurements.size();
			}
		}
		else if(chartType == Chart.Type.PIECHART) {
			dataset = new DefaultPieDataset();
			for (InfrastructureComponent component : components) {
				ArrayList<Measurement> measurements = MeasurementRepository.getAllFromComponent(component.getId() , 1);
				if(measurements == null || measurements.size() == 0){
					continue;
				}
				if(component.isAvailable()){
					this.availableDiskspace += measurements.get(0).getTotalDiskspaceInGB();
				}

				this.totalDiskspace += measurements.get(0).getTotalDiskspaceInGB();
			}
//			Measurement mostRecentMeasurement = measurements.get(measurements.size() - 1);
			((DefaultPieDataset) dataset).setValue("Bruikbaar", availableDiskspace);
			((DefaultPieDataset) dataset).setValue("Totaal", totalDiskspace);
		}
		return (AbstractDataset) dataset;
	}
}
