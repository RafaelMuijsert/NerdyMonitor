import java.util.ArrayList;
import java.util.Arrays;

public class BacktrackAlgo {
    public static double availability;
    private static int bestCosts = 1000000;
    private static ArrayList<String> serverConfiguration;
    private static double webservers[][];
    private static double DBservers[][];
    private static String webserversNames[];
    private static String DBserversNames[];
    static ArrayList<String> currentConfig = new ArrayList<>();

    public BacktrackAlgo() {
        /**
         * Deze constructor is er alleen voor om de beschikbare servers in het algoritme te verwerken
         * Deze informatie wordt later uit de database gehaald, maar voor nu is het even hardcoded.
         * Er is bewust voor de constructie gekozen van namen en specificaties apart te houden. Dit is
         * ook gedaan omdat Strings en nummers niet bij elkaar in 1 array kunnen.
         **/
        webservers = new double[][]{{2200, 0.8}, {3200, 0.9}, {5100, 0.95}};
        DBservers = new double[][]{{5100, 0.9}, {7700, 0.95}, {12200, 0.98}};

        webserversNames = new String[]{"HAL9001W", "HAL9002W", "HAL9003W"};
        DBserversNames = new String[]{"HAL9001DB", "HAL9002DB", "HAL9003DB"};
    }

    public static int getBestCosts() {
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

        for (int i = 0; i < serverConfig.size(); i++) {
            String item = serverConfig.get(i);
            if (item.contains("W")) { //Als de server HAL900.W:
                webservs.add(item); //Voeg de server toe aan de webservers
            } else { //anders is het een databaseserver
                DBservs.add(item); //voeg de server toe aan de databaseservers
            }
        }
        //bereken de beschikbaarheid van alle webservers samen
        double percWeb = 1;
        int costWeb = 0;
        for (int i = 0; i < webservs.size(); i++) { // \/\/haal de beschikbaarheid en kosten van de server op
            double perc = webservers[Arrays.asList(webserversNames).indexOf(webservs.get(i))][1];
            costWeb += webservers[Arrays.asList(webserversNames).indexOf(webservs.get(i))][0];

            percWeb *= (1 - perc); //bereken de kans dat alle servers uitvallen
        }
        percWeb = 1 - percWeb; //zet het percentage om in verwachte beschikbaarheid

        //databasegedeelte. Werkt hetzelfde als het webgedeelte.
        double percDB = 1;
        int costDB = 0;
        for (int i = 0; i < DBservs.size(); i++) {
            double perc = DBservers[Arrays.asList(DBserversNames).indexOf(DBservs.get(i))][1];
            costDB += DBservers[Arrays.asList(DBserversNames).indexOf(DBservs.get(i))][0];

            percDB *= (1 - perc);

        }
        percDB = 1 - percDB;

        double availabilityCalc = 0.99998 * percDB * percWeb; //0.99998 is de beschikbaarheid van de pfSense

        if (availabilityCalc > availability) {
            int totalCosts = costWeb + costDB + 4000; //4000 is de kosten van de pfSense
            if (totalCosts < bestCosts) {//als de kosten lager zijn dan de beste die tot nu toe gevonden is:
                bestCosts = totalCosts; //beste kosten worden de nieuwe kosten
                webservs.addAll(DBservs); //voeg de databaseservers gestructueerd bij de webservers
                serverConfiguration = webservs; //beste configuratie wordt de nieuwe configuratie
            }
        }

    }

    public static ArrayList<String> getServerConfiguration() {
        calculateServerConfiguration(6); //bereken de goedkoopste beschikbaarheid voor het opgegeven percentage
        return serverConfiguration; //return de servernamen van de beste configuratie in een arraylist
    }

    public static void calculateServerConfiguration(int maxServers) {
        /**
         * De structuur van dit stuk code is gebaseerd op een project waar Mattias vroeger aan heeft gewerkt.
         * In dat project werd namelijk een zoekboom gemaakt waarin de beste optie teruggeven wordt.
         * Deze code werkt hetzelfde, met de recursieve call die gemaakt wordt.
         **/
        if (maxServers == 0) {
            return; //anders loopt de functie oneindig door
        }
        for (int i = 0; i < 3; i++) { //loop door alle webservers heen
            currentConfig.add(webserversNames[i]); //voeg de huidige webserver toe aan de config om te testen
            isBetter(currentConfig); //test de huidige configuratie

            for (int j = 0; j < 3; j++) {//loop door alle databaseservers heen, de rest gaat hetzelfde als bij het webgedeelte
                currentConfig.add(DBserversNames[j]);
                isBetter(currentConfig);
                calculateServerConfiguration(maxServers - 1); //Voeg een extra server aan de config door de functie opnieuw aan te roepen
                currentConfig.remove(currentConfig.size() - 1); //haal de databaseserver er weer uit om een nieuwe te proberen
            }
            calculateServerConfiguration(maxServers - 1); //Voeg een extra server aan de config door de functie opnieuw aan te roepen
            currentConfig.remove(currentConfig.size() - 1); //haal de webserver er weer uit om een nieuwe toe te proberen
        }
    }

    //Dit is om het algoritme te testen. In de applicatie wordt dit weggehaald
    public static void main(String[] args) {
        BacktrackAlgo algo = new BacktrackAlgo();
        BacktrackAlgo.availability = 0.946;
        ArrayList<String> result = BacktrackAlgo.getServerConfiguration();
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

        System.out.println(algo.getBestCosts());
    }
}