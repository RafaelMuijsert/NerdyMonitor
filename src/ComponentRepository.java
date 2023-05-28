import java.sql.ResultSet;
import java.util.ArrayList;


public class ComponentRepository {
    public static Component find (int id) {
        Database db = new Database();

        Component component = null;

        try {
            String[][] where = new String[][]{
                    new String[]{
                            "id",
                            "=",
                            Integer.toString(id)
                    }
            };
            ResultSet resultset = db.find(new String[]{"*"}, "Component", where, false, 1);
            if(resultset == null) {
                return null;
            }

            while (resultset.next()) {
                component = new Component();

                component.setId(resultset.getInt("id"));
                component.setName(resultset.getString("name"));
                component.setAvailability(resultset.getDouble("availability"));
                component.setAnnualPriceInEuro(resultset.getDouble("annual_price_in_euro"));
                component.setComponentTypesId(resultset.getInt("Component_types_id"));
                component.setDiskspaceInGB(resultset.getDouble("diskspace_in_GB"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return component;
    }

    public static ArrayList<Component> findAll() {
        Database db = new Database();

        ArrayList<Component> components = new ArrayList<>();
        String[][] where = new String[][] {};

        try {

            ResultSet resultset = db.find(new String[]{"*"}, Component.TABLE, where, true, 0 );

            while(resultset.next()) {
                Component component = new Component();

                component.setId(resultset.getInt("id"));
                component.setName(resultset.getString("name"));
                component.setComponentTypesId(resultset.getInt("Component_types_id"));

                component.setAvailability(resultset.getFloat("availability"));
                component.setAnnualPriceInEuro(resultset.getFloat("annual_price_in_euro"));

                System.out.println(component);
                components.add(component);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return components;
    }
}
