public class Component {
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

    public Float getAvailability() {
        return availability;
    }

    public void setAvailability(Float availability) {
        this.availability = availability;
    }

    public float getAnnualPriceInEuro() {
        return annualPriceInEuro;
    }

    public void setAnnualPriceInEuro(float annualPriceInEuro) {
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
    private Float availability;
    private float annualPriceInEuro;
    private int componentTypesId;
    private Double diskspaceInGB;

    public Component() {

    }



}
