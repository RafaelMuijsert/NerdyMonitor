import java.awt.image.BufferedImage;
import java.util.Date;

public abstract class InfrastructureComponent {
    private double price;
    private double availability;
    private int componentNumber;
    private String name;
    private BufferedImage image;
    private boolean isAvailable;
    private Date uptime;
    private double diskSpace;
    private double diskSpaceUsed;
    private double processorLoad;

}
