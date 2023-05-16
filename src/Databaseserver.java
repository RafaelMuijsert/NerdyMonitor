import javax.imageio.ImageIO;
import java.io.File;

public class Databaseserver extends InfrastructureComponent{

    public Databaseserver(String name) {
        super(0);
    }

    @Override
    public void setImage() {
        File img = new File("icons/databaseserver.png");
        try{
            super.image = ImageIO.read(img);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
