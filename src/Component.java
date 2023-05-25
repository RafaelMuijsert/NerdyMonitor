public class Component {
    public final static String TABLE = "Component";

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

    public Component() {

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
