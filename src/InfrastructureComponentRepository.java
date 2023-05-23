import java.sql.ResultSet;
import java.util.ArrayList;

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
                infrastructureComponent.setUptime(resultset.getDate("uptime"));
                infrastructureComponent.setDiskSpace(resultset.getDouble("total_diskspace_in_GB"));
                infrastructureComponent.setProcessorLoad(resultset.getDouble("processorload"));
                infrastructureComponent.setAvailable((infrastructureComponent.getUptime() != null));
                infrastructureComponents.add(infrastructureComponent);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return infrastructureComponents;
    }
}
