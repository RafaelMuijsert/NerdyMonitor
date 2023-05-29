import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.PieDataset;
import java.awt.*;
import java.text.NumberFormat;

public class Chart {

    enum Type {
        PIECHART,
        LINECHART
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title, xLabel, yLabel;
    private Type type;
    private Color color;

    /**
     * Pie chart constructor
     * @param title
     */
    public  Chart(
            String title,
            Type type,
            Color color
    ) {
        this.title = title;
        this.type = type;
        this.color = color;
    }

    /**
     * Line chart constructor
     * @param title
     * @param type
     * @param xLabel
     * @param yLabel
     */
    public  Chart(
        String title,
        Type type,
        Color color,
        String xLabel,
        String yLabel
    ) {
        this.title = title;
        this.type = type;
        this.color = color;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
    }

    public ChartPanel createChart (AbstractDataset dataset) {

        JFreeChart chart = null;

        if(type == Type.PIECHART) {

            chart = ChartFactory.createPieChart(
                    this.title,
                    (PieDataset) dataset
            );
            // Create label hints
            PiePlot plot = (PiePlot) chart.getPlot();

            // Format the content of the labels
            PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                    "{0}: {1} ({2})",
                    NumberFormat.getInstance(),
                    NumberFormat.getPercentInstance()
            );

            plot.setLabelGenerator(gen);
        }
        else if (type == Type.LINECHART) {
            chart = ChartFactory.createLineChart(
                    this.title,
                    this.xLabel,
                    this.yLabel,
                    (CategoryDataset) dataset,
                    PlotOrientation.VERTICAL,
                    true,false,true
            );
        }
        // Create from panel from Chart Object
        ChartPanel chartPanel = new ChartPanel(chart);
        chart.setBackgroundPaint(color);

        // Disable context menu
        chartPanel.setPopupMenu(null);

        // Disable zoom
//        chartPanel.setDomainZoomable(false);
//        chartPanel.setRangeZoomable(false);

        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ));

        return chartPanel;
    }

}
