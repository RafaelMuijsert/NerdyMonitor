import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.ArrayList;

public class Chart {

    public static final int PIECHART = 0;
    public static final int LINECHART = 1;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title, xLabel, yLabel;
    private int chartType;
    private ArrayList<Measurement> data;//@todo meting object array

    /**
     * Pie chart constructor
     * @param title
     */
    public  Chart(
            String title,
            int chartType
    ) {
        this.title = title;
        this.chartType = chartType;
    }
    public  Chart(
        String title,
        int chartType,
        String xLabel,
        String yLabel
    ) {
        this.title = title;
        this.chartType = chartType;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
    }

    public ChartPanel createChart (DefaultPieDataset dataset) {

        JFreeChart chart = null;

        if(chartType == PIECHART) {
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            pieDataset.setValue("Michael", 40);
            pieDataset.setValue("Test", 60);

            chart = ChartFactory.createPieChart(
                    this.title,
                    pieDataset
            );
        }
        else if (chartType == LINECHART) {
            chart = ChartFactory.createLineChart(
                    this.title,
                    this.xLabel,
                    this.yLabel,
                    createDataset(),
                    PlotOrientation.VERTICAL,
                    true,false,true
            );
        }

        ChartPanel chartPanel = new ChartPanel(chart);

        // Disable context menu
        chartPanel.setPopupMenu(null);

        // Disable zoom
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);

        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        chartPanel.setBackground(Color.BLUE);

        return chartPanel;
    }

    //@todo Dummy data
    private DefaultCategoryDataset createDataset() {

        //Meting object array,
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        dataset.addValue( 15 , "dwa" , "1970" );
        dataset.addValue( 30 , "schools" , "1980" );
        dataset.addValue( 60 , "schools" ,  "1990" );
        dataset.addValue( 120 , "schools" , "2000" );
        dataset.addValue( 240 , "schools" , "2010" );
        dataset.addValue( 300 , "schools" , "2014" );
        return dataset;
    }


}
