import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

public class Firewall extends InfrastructureComponent{
    public Firewall(int infrastructureComponentId) {
        super(infrastructureComponentId);
        this.imagePath = "icons/firewall.png";
    }
}
