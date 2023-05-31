import java.time.LocalDateTime;
import java.util.Date;

public class Measurement{
    public final static String TABLE = "Measurement";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public double getProcessorload() {
        return processorload;
    }
    public String getProcessorload(boolean format) {
        return formatContent(format, "%", String.valueOf(getProcessorload()) );

    }

    public void setProcessorload(double processorload) {
        this.processorload = processorload;
    }

    public double getUsedDiskspaceInGB() {
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


    public void setUsedDiskspaceInGB(double usedDiskspaceInGB) {
        this.usedDiskspaceInGB = usedDiskspaceInGB;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    private double processorload;
    private double usedDiskspaceInGB;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    private double temperature;
    private LocalDateTime date;
    private String uptime;
    private int infrastructureComponent; // @todo eventueel updaten naar een object

    public double getTotalDiskspaceInGB() {
        return totalDiskspaceInGB;
    }

    public void setTotalDiskspaceInGB(double totalDiskspaceInGB) {
        this.totalDiskspaceInGB = totalDiskspaceInGB;
    }

    private double totalDiskspaceInGB;
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
