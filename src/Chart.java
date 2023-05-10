import Helpers.ChartHelper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

public class Chart {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title, xLabel, yLabel;
    //@todo meting object array

    public  Chart(String title, String xLabel, String yLabel) {
        this.title = title;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
    }

    public ChartPanel createChart () {

        JFreeChart lineChart = ChartFactory.createLineChart(
            this.title,
            this.xLabel,
            this.yLabel,
            createDataset(),
            PlotOrientation.VERTICAL,
            true,false,true
        );

        ChartPanel chartPanel = new ChartPanel(lineChart);

        // Disable context menu
        chartPanel.setPopupMenu(null);

        // Disable zoom
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);

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
