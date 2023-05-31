import Utils.TimeUtils;

import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class InfrastructureComponentRepository {

    public static ArrayList<InfrastructureComponent> findAll() {
        Database db = new Database();

        ArrayList<InfrastructureComponent> infrastructureComponents= new ArrayList<>();

        try {

            String SQL = "SELECT IC.id ICid, name, availability, annual_price_in_euro, uptime, total_diskspace_in_GB, used_diskspace_in_GB, processorload, Component_types_id " +
                    "FROM Infrastructure_component IC " +
                    "JOIN Component C ON IC.Component_id=C.id " +
                    "LEFT JOIN Measurement M on IC.id = M.Infrastructure_component_id " +
                    "GROUP by ICid " +
                    "ORDER BY ICid";

            ResultSet resultset = db.findRaw(SQL);

            while(resultset.next()) {
                InfrastructureComponent infrastructureComponent = new InfrastructureComponent();

                infrastructureComponent.setId(resultset.getInt("ICid"));
                infrastructureComponent.setName(resultset.getString("name"));
                infrastructureComponent.setComponentTypesId(resultset.getInt("Component_types_id"));

                infrastructureComponent.setAvailability(resultset.getDouble("availability"));
                infrastructureComponent.setAnnualPriceInEuro(resultset.getDouble("annual_price_in_euro"));
                infrastructureComponent.setUptime(resultset.getString("uptime"));
                infrastructureComponent.setDiskSpace(resultset.getDouble("total_diskspace_in_GB"));
                infrastructureComponent.setProcessorLoad(resultset.getDouble("processorload"));
                infrastructureComponents.add(infrastructureComponent);

                // Retrieve most recent measurement

                if(infrastructureComponent.getUptime() != null){
                    ArrayList<Measurement> measurement = MeasurementRepository.getAllFromComponent(infrastructureComponent.getId(), 1);
                    if(measurement == null || measurement.size() == 0){
                        continue;
                    }

                    // Time elapsed since previous measure is less than
                    if(TimeUtils.getTimeDifference(java.time.LocalDateTime.now(), measurement.get(0).getDate()) <= InfrastructureComponent.MeasurementIntervalMinutes){
                        infrastructureComponent.setAvailable(true);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return infrastructureComponents;
    }
}
