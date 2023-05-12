import java.util.Date;

public class Measurement{

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public float getProcessorload() {
        return processorload;
    }

    public void setProcessorload(float processorload) {
        this.processorload = processorload;
    }

    public float getUsedDiskspaceInGB() {
        return usedDiskspaceInGB;
    }

    public void setUsedDiskspaceInGB(float usedDiskspaceInGB) {
        this.usedDiskspaceInGB = usedDiskspaceInGB;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    private float processorload;
    private float usedDiskspaceInGB;
    private Date date;
    private Date uptime;
    private int infrastructureComponent; // @todo eventueel updaten naar een object

    public int getInfrastructureComponent() {
        return infrastructureComponent;
    }

    public void setInfrastructureComponent(int infrastructureComponent) {
        this.infrastructureComponent = infrastructureComponent;
    }


    public Measurement() {}

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", processorload=" + processorload +
                ", diskspace=" + usedDiskspaceInGB +
                ", date=" + date +
                ", uptime=" + uptime +
                '}';
    }
}
