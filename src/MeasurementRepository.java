import java.sql.ResultSet;
import java.util.ArrayList;

public class MeasurementRepository {
    /**
     * Get all measurements of a given component.
     * @param componentID
     * @return
     */
    public static ArrayList<Measurement> getAllFromComponent(int componentID, int limit){
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

            ResultSet resultset = db.find(new String[]{"*"}, Measurement.TABLE, where, true, limit);

            // Could not find measurements
            if(resultset == null) {
                return null;
            }

            // Loop through each record
            while(resultset.next()) {
                Measurement measurement = new Measurement();

                measurement.setId(resultset.getInt("id"));
                measurement.setDate(resultset.getTimestamp("date").toLocalDateTime());
                measurement.setUptime(resultset.getString("uptime"));
                measurement.setUsedDiskspaceInGB(resultset.getDouble("used_diskspace_in_GB"));
                measurement.setTotalDiskspaceInGB(resultset.getDouble("total_diskspace_in_GB"));
                measurement.setProcessorload(resultset.getDouble("processorload"));
                measurement.setInfrastructureComponent(resultset.getInt("Infrastructure_component_id"));
                measurement.setTemperature(resultset.getDouble("temperature"));

                measurements.add(measurement);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return measurements;
    }

}
