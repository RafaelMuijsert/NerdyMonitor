import javax.imageio.ImageIO;
import java.io.File;

public class Webserver extends InfrastructureComponent{

    public Webserver(String name) {
        super(name);
    }

    @Override
    public void setImage() {
        File img = new File("icons/webserver.png");
        try{
            super.image = ImageIO.read(img);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
