import javax.swing.*;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.util.Date;

import static Utils.ImageUtils.getImageIcon;

public  class InfrastructureComponent extends Component {
    public final static String TABLE = "Component_types_id";

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public Float getAvailability() {
        return (float) availability;
    }

    public void setAvailability(double availability) {
        this.availability = availability;
    }

    public void setComponentNumber(int componentNumber) {
        this.componentNumber = componentNumber;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public void setDiskSpace(double diskSpace) {
        this.diskSpace = diskSpace;
    }

    public void setDiskSpaceUsed(double diskSpaceUsed) {
        this.diskSpaceUsed = diskSpaceUsed;
    }

    public void setProcessorLoad(double processorLoad) {
        this.processorLoad = processorLoad;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    private double price;
    private double availability;
    private int componentNumber;
    public BufferedImage image;
    private boolean isAvailable;
    private Date uptime;
    private double diskSpace;
    private double diskSpaceUsed;
    private double processorLoad;
    protected String imagePath;
    private Component component;

    public InfrastructureComponent(int infrastructureComponentId){
        Database db = new Database();
        System.out.println();
        try {

            String SQL = "SELECT IC.id ICid, name, availability, annual_price_in_euro, uptime, total_diskspace_in_GB, used_diskspace_in_GB, processorload, Component_types_id" + " " +
                    "FROM Infrastructure_component IC " +
                    "JOIN Component C ON IC.Component_id=C.id " +
                    "JOIN Measurement M on M.id = (SELECT id from Measurement where Infrastructure_component_id = IC.id ORDER BY M.date DESC limit 1) " +
                    "WHERE IC.id=" + infrastructureComponentId+ " " +
                    "LIMIT 1";

            ResultSet resultset = db.findRaw(SQL);

            if(resultset == null){
                return;
            }

            while(resultset.next()) {
                super.setName(resultset.getString("name"));
                super.setComponentTypesId(resultset.getInt("Component_types_id"));

                this.availability = resultset.getDouble("availability");
                this.price = resultset.getDouble("annual_price_in_euro");
                this.componentNumber = resultset.getInt("ICid"); // dunno wat de punt van dit uberhaupt is
                this.uptime = resultset.getDate("uptime");
                this.diskSpace = resultset.getDouble("total_diskspace_in_GB");
                this.processorLoad = resultset.getDouble("processorload");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public InfrastructureComponent(){}

    public void update() {
        Database db = new Database();
        try {
            ResultSet resultset = db.findRaw("SELECT processorload, diskspace_in_GB, uptime" +
                    "FROM Measurement M JOIN Infrastructure_component IC ON IC.id=M.infrastructure_component " +
                    "WHERE IC.id=" + String.valueOf(this.componentNumber) + " ORDER BY M.id DESC LIMIT 1");
            this.uptime = resultset.getDate("uptime");
            this.diskSpace = resultset.getDouble("diskspace_in_GB");
            this.processorLoad = resultset.getDouble("processorload");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Date getUptime() {
        return uptime;
    }

    public double getDiskSpace() {
        return diskSpace;
    }

    public double getPrice() {
        return price;
    }

    public double getDiskSpaceUsed() {
        return diskSpaceUsed;
    }

    public double getProcessorLoad() {
        return processorLoad;
    }

    public int getComponentNumber() {
        return componentNumber;
    }


    public ImageIcon getImage() {
        return getImageIcon(this.imagePath, 128, 128);
    }

    @Override
    public String toString() {
        return "InfrastructureComponent{" +
                "price=" + price +
                ", availability=" + availability +
                ", componentNumber=" + componentNumber +
                ", image=" + image +
                ", isAvailable=" + isAvailable +
                ", uptime=" + uptime +
                ", diskSpace=" + diskSpace +
                ", diskSpaceUsed=" + diskSpaceUsed +
                ", processorLoad=" + processorLoad +
                ", imagePath='" + imagePath + '\'' +
                ", component=" + component +
                '}';
    }
}