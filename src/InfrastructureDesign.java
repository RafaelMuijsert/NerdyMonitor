import java.util.ArrayList;

public class InfrastructureDesign {
	private ArrayList<Firewall> firewalls;
	private ArrayList<Webserver> webservers;
	private ArrayList<Databaseserver> databases;

	public InfrastructureDesign() {
		this.firewalls = new ArrayList<Firewall>();
		this.webservers = new ArrayList<Webserver>();
		this.databases = new ArrayList<Databaseserver>();
	}

	public InfrastructureDesign(String path) {
		this();
		loadDesign(path);
	}

	public ArrayList<Firewall> getFirewalls() {
		return firewalls;
	}

	public ArrayList<Databaseserver> getDatabases() {
		return databases;
	}

	public ArrayList<Webserver> getWebservers() {
		return webservers;
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
		this.firewalls.add(firewall);
	}

	public void add(Webserver webserver) {
		this.webservers.add(webserver);
	}

	public void add(Databaseserver database) {
		this.databases.add(database);
	}
}
