import java.util.Date;

public class Measurement{

    private int id;
    private float processorload;
    private float diskspace;
    private Date date;
    private Date uptime;


    public Measurement() {}

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", processorload=" + processorload +
                ", diskspace=" + diskspace +
                ", date=" + date +
                ", uptime=" + uptime +
                '}';
    }
}
