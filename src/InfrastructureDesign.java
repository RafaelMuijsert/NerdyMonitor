import java.util.ArrayList;

public class InfrastructureDesign {
	private ArrayList<Firewall> firewalls;
	private ArrayList<Webserver> webservers;
	private ArrayList<Databaseserver> databases;
	private ArrayList<Component> components;

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
		int totalCosts = 0;
		for (Component component : getComponents()){
			totalCosts += component.getAnnualPriceInEuro();
		}

		return totalCosts;
	}

	public double getTotalAvailability() {
		int totalAvailability = 0;
		ArrayList<Component> components = getComponents();

		for (Component component : components){
			totalAvailability += component.getAvailability();
		}

		return totalAvailability / components.size();
	}
	public ArrayList<Component>  getComponents() {
//		ArrayList<Component> components = new ArrayList<>();
//
//		components.addAll(getDatabases());
//		components.addAll(getWebservers());
//		components.addAll(getFirewalls());

		return this.components;
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

	public void add(ArrayList<Component> components) {
		this.components = components;

		// Convert components to component type array
		for (Component component : components) {
			if (component.getComponentTypesId() == Component.FIREWALL) {
				this.add(new Firewall(component.getId()));
			} else if (component.getComponentTypesId() == Component.DBSERVER) {
				this.add(new Databaseserver(component.getId()));
			} else if (component.getComponentTypesId() == Component.WEBSERVER) {
				this.add(new Webserver(component.getId()));
			}
		}
	}
}
