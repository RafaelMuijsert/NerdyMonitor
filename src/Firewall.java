import javax.imageio.ImageIO;
import java.io.File;

public class Firewall extends InfrastructureComponent{

    public Firewall(String name) {
        super(0);
    }

    @Override
    public void setImage() {
        File img = new File("icons/firewall.png");
        try{
            super.image = ImageIO.read(img);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
