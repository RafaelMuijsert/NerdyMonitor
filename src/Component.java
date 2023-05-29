import java.sql.ResultSet;

public class Component {
    public final static String TABLE = "Component";

    public static final int FIREWALL = 1;
    public static final int DBSERVER = 2;
    public static final int WEBSERVER = 3;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAvailability() {
        return availability;
    }

    public void setAvailability(double availability) {
        this.availability = availability;
    }

    public double getAnnualPriceInEuro() {
        return annualPriceInEuro;
    }

    public void setAnnualPriceInEuro(double annualPriceInEuro) {
        this.annualPriceInEuro = annualPriceInEuro;
    }

    public int getComponentTypesId() {
        return componentTypesId;
    }

    public void setComponentTypesId(int componentTypesId) {
        this.componentTypesId = componentTypesId;
    }

    public Double getDiskspaceInGB() {
        return diskspaceInGB;
    }

    public void setDiskspaceInGB(Double diskspaceInGB) {
        this.diskspaceInGB = diskspaceInGB;
    }

    private int id;
    private String name;
    private double availability;
    private double annualPriceInEuro;
    private int componentTypesId;
    private double diskspaceInGB;

    public Component() {}

    public Component(int id) {
        Database db = new Database();

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
                return;
            }

            while (resultset.next()) {
                this.setId(resultset.getInt("id"));
                this.setName(resultset.getString("name"));
                this.setAvailability(resultset.getDouble("availability"));
                this.setAnnualPriceInEuro(resultset.getDouble("annual_price_in_euro"));
                this.setComponentTypesId(resultset.getInt("Component_types_id"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Override
    public String toString() {
        return "Component{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", availability=" + availability +
                ", annualPriceInEuro=" + annualPriceInEuro +
                ", componentTypesId=" + componentTypesId +
                ", diskspaceInGB=" + diskspaceInGB +
                '}';
    }
}
