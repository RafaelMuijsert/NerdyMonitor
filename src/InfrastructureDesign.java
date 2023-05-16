import java.util.ArrayList;

public class InfrastructureDesign {
	private ArrayList<Firewall> firewalls;
	private ArrayList<Webserver> webservers;
	private ArrayList<Database> databases;

	public InfrastructureDesign() {

	}

	public InfrastructureDesign(String path) {
		loadDesign(path);
	}

	public int getTotalCost() {
		return 1000;
	}

	public double getTotalAvailability() {
		return 1.0;
	}

	public void saveDesign(String path) {

	}

	public boolean loadDesign(String path) {
		return true;
	}

	public void add(Firewall firewall) {

	}

	public void add(Webserver webserver) {

	}

	public void add(Database database) {

	}
}
