import java.sql.ResultSet;
import java.util.ArrayList;

public class BacktrackAlgo {
    public static double availability;
    private static double bestCosts = 1000000;
    private static ArrayList<String> serverConfiguration;
    private static ArrayList<ArrayList<Double>> webservers = new ArrayList<>();
    private static ArrayList<String> webserversNames = new ArrayList<>();

    private static ArrayList<ArrayList<Double>> DBservers = new ArrayList<>();
    private static ArrayList<String> DBserversNames = new ArrayList<>();

    private static ArrayList<ArrayList<Double>> firewalls = new ArrayList<>();
    private static ArrayList<String> firewallsNames = new ArrayList<>();

    static ArrayList<String> currentConfig = new ArrayList<>();

    public BacktrackAlgo() {
        /**
         * Deze constructor is er alleen voor om de beschikbare servers in het algoritme te verwerken
         * Deze informatie wordt later uit de database gehaald, maar voor nu is het even hardcoded.
         * Er is bewust voor de constructie gekozen van namen en specificaties apart te houden. Dit is
         * ook gedaan omdat Strings en nummers niet bij elkaar in 1 array kunnen.
         **/
        Database db = new Database();
        try {

            String SQL = "SELECT C.name name, availability, annual_price_in_euro price, CT.name AS type " +
                    "FROM Component C " +
                    "JOIN Component_types CT ON C.Component_types_id=CT.id " +
                    "WHERE CT.name='webserver'";
            ResultSet resultset = db.findRaw(SQL);

            while (resultset.next()) {
                ArrayList<Double> webserverSpecs = new ArrayList<>();

                webserverSpecs.add(resultset.getDouble("price"));
                webserverSpecs.add(resultset.getDouble("availability") / 100);
                this.webservers.add(webserverSpecs);
                this.webserversNames.add(resultset.getString("name"));
            }

            SQL = "SELECT C.name name, availability, annual_price_in_euro price, CT.name AS type " +
                    "FROM Component C " +
                    "JOIN Component_types CT ON C.Component_types_id=CT.id " +
                    "WHERE CT.name='DBserver'";
            resultset = db.findRaw(SQL);

            while (resultset.next()) {
                ArrayList<Double> DBserverSpecs = new ArrayList<>();

                DBserverSpecs.add(resultset.getDouble("price"));
                DBserverSpecs.add(resultset.getDouble("availability") / 100);
                this.DBservers.add(DBserverSpecs);
                this.DBserversNames.add(resultset.getString("name"));
            }

            SQL = "SELECT C.name name, availability, annual_price_in_euro price, CT.name AS type " +
                    "FROM Component C " +
                    "JOIN Component_types CT ON C.Component_types_id=CT.id " +
                    "WHERE CT.name='firewall'";
            resultset = db.findRaw(SQL);

            while (resultset.next()) {
                ArrayList<Double> firewallSpecs = new ArrayList<>();

                firewallSpecs.add(resultset.getDouble("price"));
                firewallSpecs.add(resultset.getDouble("availability") / 100);
                this.firewalls.add(firewallSpecs);
                this.firewallsNames.add(resultset.getString("name"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static double getBestCosts() {
        return bestCosts; //Spreekt voor zich, haalt de beste kosten op en returnt deze
    }

    private static void isBetter(ArrayList<String> serverConfig) {
        /**
         * Deze functie berekent de beschikbaarheid van de configuratie die wordt meegegeven.
         * Als de beschikbaarheid hoger is dan het opgegeven attribuut "availability" dan wordt
         * gekeken naar de kosten van de configuratie en of deze lager is dan de vorige beste
         * configuratie die gevonden is. Als de nieuwe configuratie goedkoper is wordt deze
         * de nieuwe "serverConfiguration" en wordt "bestCosts" de nieuwe kosten.
         **/
        /*deze 2 arraylists zorgen ervoor dat de webservers en databaseservers gescheiden worden
         * zodat de beschikbaarheidsberekening correct kan worden uitgevoerd
         * De webservers en databaseservers worden namelijk door elkaar in "serverConfig" meegegeven.
         * */
        ArrayList<String> webservs = new ArrayList<>();
        ArrayList<String> DBservs = new ArrayList<>();

        double percFirewall = 0;
        double costFirewall = 0;
        String firewallName = "";

        for (int i = 0; i < serverConfig.size(); i++) {
            String item = serverConfig.get(i);
            for (int j = 0; j < webservers.size(); j++) {
                if (item == webserversNames.get(j)) {
                    webservs.add(item);
                }
            }

            for (int j = 0; j < DBservers.size(); j++) {
                if (item == DBserversNames.get(j)) {
                    DBservs.add(item);
                }
            }

            for (int j = 0; j < firewalls.size(); j++) {
                if (item == firewallsNames.get(j)) {
                    percFirewall = firewalls.get(firewallsNames.indexOf(item)).get(1);
                    costFirewall = firewalls.get(firewallsNames.indexOf(item)).get(0);
                    firewallName = item;
                }
            }
        }
        //bereken de beschikbaarheid van alle webservers samen
        double percWeb = 1;
        int costWeb = 0;
        for (int i = 0; i < webservs.size(); i++) { // \/\/haal de beschikbaarheid en kosten van de server op
            double perc = webservers.get(webserversNames.indexOf(webservs.get(i))).get(1);
            costWeb += webservers.get(webserversNames.indexOf(webservs.get(i))).get(0);

            percWeb *= (1 - perc); //bereken de kans dat alle servers uitvallen
        }
        percWeb = 1 - percWeb; //zet het percentage om in verwachte beschikbaarheid

        //Databasegedeelte. Werkt hetzelfde als het webgedeelte.
        double percDB = 1;
        int costDB = 0;
        for (int i = 0; i < DBservs.size(); i++) {
            double perc = DBservers.get(DBserversNames.indexOf(DBservs.get(i))).get(1);
            costDB += DBservers.get(DBserversNames.indexOf(DBservs.get(i))).get(0);

            percDB *= (1 - perc);

        }
        percDB = 1 - percDB;

        double availabilityCalc = percFirewall * percDB * percWeb;

        webservs.addAll(DBservs); //voeg de databaseservers gestructueerd bij de webservers
        webservs.add(firewallName);

        if (availabilityCalc > availability) {
            double totalCosts = costWeb + costDB + costFirewall;
            if (totalCosts < bestCosts) {//als de kosten lager zijn dan de beste die tot nu toe gevonden is:
                bestCosts = totalCosts; //beste kosten worden de nieuwe kosten
                serverConfiguration = webservs; //beste configuratie wordt de nieuwe configuratie
            }
        }

    }

    public static ArrayList<String> getServerConfiguration() {
        calculateFirewallsConfiguration(6); //bereken de goedkoopste beschikbaarheid voor het opgegeven percentage
        return serverConfiguration; //return de servernamen van de beste configuratie in een arraylist
    }

    public static void calculateFirewallsConfiguration(int maxServers) {
        for (int f = 0; f < firewalls.size(); f++) {
            currentConfig.add(firewallsNames.get(f));
            calculateServerConfiguration(maxServers);
            currentConfig.remove(currentConfig.size() - 1);
        }
    }

    private static void calculateServerConfiguration(int maxServers) {
        /**
         * De structuur van dit stuk code is gebaseerd op een project waar Mattias vroeger aan heeft gewerkt.
         * In dat project werd namelijk een zoekboom gemaakt waarin de beste optie teruggeven wordt.
         * Deze code werkt hetzelfde, met de recursieve call die gemaakt wordt.
         **/
        if (maxServers == 0) {
            return; //anders loopt de functie oneindig door
        }
        for (int i = 0; i < webservers.size(); i++) { //loop door alle webservers heen
            currentConfig.add(webserversNames.get(i)); //voeg de huidige webserver toe aan de config om te testen
            isBetter(currentConfig); //test de huidige configuratie

            for (int j = 0; j < DBservers.size(); j++) {//loop door alle databaseservers heen, de rest gaat hetzelfde als bij het webgedeelte
                currentConfig.add(DBserversNames.get(j));
                isBetter(currentConfig);
                calculateServerConfiguration(maxServers - 1); //Voeg een extra server aan de config door de functie opnieuw aan te roepen
                currentConfig.remove(currentConfig.size() - 1); //haal de databaseserver er weer uit om een nieuwe te proberen
            }
            calculateServerConfiguration(maxServers - 1); //Voeg een extra server aan de config door de functie opnieuw aan te roepen
            currentConfig.remove(currentConfig.size() - 1); //haal de webserver er weer uit om een nieuwe toe te proberen
        }
    }


    //Voorbeeldcode voor de beschikbaarheidspanel.
    public static void main(String[] args) {
        BacktrackAlgo algo = new BacktrackAlgo();
        BacktrackAlgo.availability = 0.9999; //Beschikbaarheid invullen.
        ArrayList<String> result = BacktrackAlgo.getServerConfiguration();
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

        System.out.println(algo.getBestCosts());
    }
}