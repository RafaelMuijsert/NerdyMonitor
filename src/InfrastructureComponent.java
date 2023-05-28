import javax.swing.*;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.util.Date;

import static Utils.ImageUtils.getImageIcon;

public  class InfrastructureComponent extends Component {
    public final static String TABLE = "Infrastructure_component";

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAvailability() {
        return  availability;
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public InfrastructureComponent(int infrastructureComponentId){
        Database db = new Database();
        System.out.println();
        try {

            String SQL = "SELECT IC.id ICid, name, availability, annual_price_in_euro, uptime, total_diskspace_in_GB, used_diskspace_in_GB, processorload, Component_types_id" + " " +
                    "FROM Infrastructure_component IC " +
                    "JOIN Component C ON IC.Component_id=C.id " +
                    "LEFT JOIN Measurement M on IC.id = M.Infrastructure_component_id " +
                    "WHERE IC.id=" + infrastructureComponentId+ " " +
                    "GROUP by Infrastructure_component_id " +
                    "LIMIT 1";


            ResultSet resultset = db.findRaw(SQL);

            if(resultset == null){
                return;
            }

            while(resultset.next()) {

                this.setId(resultset.getInt("ICid"));
                this.setName(resultset.getString("name"));
                this.setComponentTypesId(resultset.getInt("Component_types_id"));

                this.setAvailability(resultset.getDouble("availability"));
                this.setAnnualPriceInEuro(resultset.getDouble("annual_price_in_euro"));
                this.setUptime(resultset.getDate("uptime"));
                this.setDiskSpace(resultset.getDouble("total_diskspace_in_GB"));
                this.setProcessorLoad(resultset.getDouble("processorload"));
                this.setAvailable((this.getUptime() != null));

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public InfrastructureComponent(){}
    public void setDataFromResultSet(ResultSet resultset) {
        try {
                InfrastructureComponent infrastructureComponent = new InfrastructureComponent();

                infrastructureComponent.setId(resultset.getInt("ICid"));
                infrastructureComponent.setName(resultset.getString("name"));
                infrastructureComponent.setComponentTypesId(resultset.getInt("Component_types_id"));

                infrastructureComponent.setAvailability(resultset.getDouble("availability"));
                infrastructureComponent.setAnnualPriceInEuro(resultset.getDouble("annual_price_in_euro"));
                infrastructureComponent.setUptime(resultset.getDate("uptime"));
                infrastructureComponent.setDiskSpace(resultset.getDouble("total_diskspace_in_GB"));
                infrastructureComponent.setProcessorLoad(resultset.getDouble("processorload"));
        } catch (Exception e){

        }
    }
//    public void update() {
//        Database db = new Database();
//        try {
//            ResultSet resultset = db.findRaw("SELECT processorload, diskspace_in_GB, uptime" +
//                    "FROM Measurement M JOIN Infrastructure_component IC ON IC.id=M.infrastructure_component " +
//                    "WHERE IC.id=" + String.valueOf(this.componentNumber) + " ORDER BY M.id DESC LIMIT 1");
//            this.uptime = resultset.getDate("uptime");
//            this.diskSpace = resultset.getDouble("diskspace_in_GB");
//            this.processorLoad = resultset.getDouble("processorload");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

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