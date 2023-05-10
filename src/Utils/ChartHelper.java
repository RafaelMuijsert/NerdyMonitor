package Utils;

import org.jfree.data.category.DefaultCategoryDataset;
import java.lang.reflect.Array;

public class ChartHelper {

    /**
     * Format an array into a dataset object which can be used for creating Charts with JfreeChart.
     * @param data
     * @return
     */
    public static DefaultCategoryDataset formatDataset(Array data, int chartType) {
        if(data == null) {
            return null;
        }


        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue( 15 , "schools" , "1970" );

        return dataset;
    }
}
