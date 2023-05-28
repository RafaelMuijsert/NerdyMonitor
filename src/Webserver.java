import javax.imageio.ImageIO;
import java.io.File;

public class Webserver extends InfrastructureComponent{

    public Webserver(int infrastructureComponentId) {
        super(infrastructureComponentId);
        imagePath = "icons/webserver.png";
    }
}
