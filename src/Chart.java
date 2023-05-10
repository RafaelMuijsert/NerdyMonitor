import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.PieDataset;

import java.awt.*;
import java.util.ArrayList;

public class Chart {

    public static final int PIECHART = 0;
    public static final int LINECHART = 1;

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
    private ArrayList<Measurement> data;//@todo meting object array

    /**
     * Pie chart constructor
     * @param title
     */
    public  Chart(
            String title,
            Type type
    ) {
        this.title = title;
        this.type = type;
    }
    public  Chart(
        String title,
        Type type,
        String xLabel,
        String yLabel
    ) {
        this.title = title;
        this.type = type;
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
        chart.setBackgroundPaint( Color.WHITE ); // Set Same as main backgroundcolor

        // Disable context menu
        chartPanel.setPopupMenu(null);

        // Disable zoom
//        chartPanel.setDomainZoomable(false);
//        chartPanel.setRangeZoomable(false);

        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );

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
