import com.mysql.cj.protocol.Resultset;

import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.util.Date;

public abstract class InfrastructureComponent {
    private double price;
    private double availability;
    private int componentNumber;
    private String name;
    public BufferedImage image;
    private boolean isAvailable;
    private Date uptime;
    private double diskSpace;
    private double diskSpaceUsed;
    private double processorLoad;

    public InfrastructureComponent(int componentID) {
        Database db = new Database();
        try {
            ResultSet resultset = db.findRaw("SELECT IC.id ICid, name, availability, annual_price_in_euro " +
                    "FROM Infrastructure_component IC JOIN Component C ON IC.component=C.id WHERE IC.id=" + componentID + "LIMIT 1");
            while (resultset.next()) {
                this.name = resultset.getString("name");
                this.availability = resultset.getDouble("availability");
                this.price = resultset.getDouble("annual_price_in_euro");
                this.componentNumber = resultset.getInt("ICid");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            ResultSet resultset = db.findRaw("SELECT processorload, diskspace_in_GB, uptime" +
                    "FROM Measurement M JOIN Infrastructure_component IC ON IC.id=M.infrastructure_component " +
                    "WHERE IC.id=" + String.valueOf(this.componentNumber) + " ORDER BY M.id DESC LIMIT 1");
            while (resultset.next()) {
                this.uptime = resultset.getDate("uptime");
                this.diskSpace = resultset.getDouble("diskspace_in_GB");
                this.processorLoad = resultset.getDouble("processorload");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update() {
        Database db = new Database();
        try {
            ResultSet resultset = db.findRaw("SELECT processorload, diskspace_in_GB, uptime" +
                    "FROM Measurement M JOIN Infrastructure_component IC ON IC.id=M.infrastructure_component " +
                    "WHERE IC.id=" + String.valueOf(this.componentNumber) + " ORDER BY M.id DESC LIMIT 1");
            while (resultset.next()) {
                this.uptime = resultset.getDate("uptime");
                this.diskSpace = resultset.getDouble("diskspace_in_GB");
                this.processorLoad = resultset.getDouble("processorload");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Date getUptime() {
        return uptime;
    }

    public double getAvailability() {
        return availability;
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

    public String getName() {
        return name;
    }

    public abstract void setImage();
}