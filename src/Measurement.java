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
    public String getProcessorload(boolean format) {
        return formatContent(format, "%", String.valueOf(getProcessorload()) );

    }

    public void setProcessorload(float processorload) {
        this.processorload = processorload;
    }

    public float getUsedDiskspaceInGB() {
        return usedDiskspaceInGB;
    }
    public String getUsedDiskspaceInGB(boolean format) {
        return formatContent(format, "GB", String.valueOf(getUsedDiskspaceInGB()) );
    }

    public String formatContent(boolean format, String formatSymbol, String value) {
        String formattedContent = value;
        if(format) {
            formattedContent += " ";
            formattedContent += formatSymbol;
        }

        return formattedContent;
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

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    private float temperature;
    private Date date;
    private Date uptime;
    private int infrastructureComponent; // @todo eventueel updaten naar een object

    public float getTotalDiskspaceInGB() {
        return totalDiskspaceInGB;
    }

    public void setTotalDiskspaceInGB(float totalDiskspaceInGB) {
        this.totalDiskspaceInGB = totalDiskspaceInGB;
    }

    private float totalDiskspaceInGB;
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
