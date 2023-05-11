import java.sql.ResultSet;
import java.util.ArrayList;

public class MeasurementRepository {
    /**
     * Get all measurements of a given component.
     * @param componentID
     * @return
     */
    public static ArrayList<Measurement> getAllFromComponent(int componentID){
        Database db = new Database();
        ArrayList<Measurement> measurements = new ArrayList<>();

        try{
            String[][] where = new String[][]{
                    new String[]{"infrastructuur_component_id", "=", Integer.toString(componentID)}
            };

            ResultSet resultset = db.find(new String[]{"*"}, "Measurement", where, false, 0);
            // Loop through each record
            while(resultset.next()) {
                Measurement measurement = new Measurement();

                measurement.setId(resultset.getInt("id"));
                measurement.setDate(resultset.getDate("date"));
                measurement.setUptime(resultset.getDate("uptime"));
                measurement.setDiskspace(resultset.getFloat("diskspace"));
                measurement.setProcessorload(resultset.getFloat("processorload"));

                measurements.add(measurement);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return measurements;
    }

}
