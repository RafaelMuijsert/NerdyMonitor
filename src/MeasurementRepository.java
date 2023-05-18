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

        try {
            String[][] where = new String[][] {
                    new String[] {
                            "Infrastructure_component_id",
                            "=",
                            Integer.toString(componentID)
                    }
            };

            ResultSet resultset = db.find(new String[]{"*"}, "Measurement", where, false, 0);

            // Could not find measurements
            if(resultset == null) {
                return null;
            }

            // Loop through each record
            while(resultset.next()) {
                Measurement measurement = new Measurement();

                measurement.setId(resultset.getInt("id"));
                measurement.setDate(resultset.getDate("date"));
                measurement.setUptime(resultset.getDate("uptime"));
                measurement.setUsedDiskspaceInGB(resultset.getFloat("used_diskspace_in_GB"));
                measurement.setTotalDiskspaceInGB(resultset.getFloat("total_diskspace_in_GB"));
                measurement.setProcessorload(resultset.getFloat("processorload"));
                measurement.setInfrastructureComponent(resultset.getInt("Infrastructure_component_id"));
                measurement.setTemperature(resultset.getFloat("temperature"));

                measurements.add(measurement);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return measurements;
    }

}
