import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfrastructureDesign {
    private ArrayList<Firewall> firewalls;
    private ArrayList<Webserver> webservers;
    private ArrayList<Databaseserver> databases;
    private ArrayList<Component> components;
    private boolean custom;

    public InfrastructureDesign(boolean custom) {
        this.custom = custom;
        this.firewalls = new ArrayList<Firewall>();
        this.webservers = new ArrayList<Webserver>();
        this.databases = new ArrayList<Databaseserver>();
    }

    public InfrastructureDesign(String path) {
        this.firewalls = new ArrayList<Firewall>();
        this.webservers = new ArrayList<Webserver>();
        this.databases = new ArrayList<Databaseserver>();

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
        for (Component component : getComponents()) {
            totalCosts += component.getAnnualPriceInEuro();
        }

        return totalCosts;
    }

    public double getTotalAvailability() {
        int totalAvailability = 0;
        ArrayList<Component> components = getComponents();

        for (Component component : components) {
            totalAvailability += component.getAvailability();
        }

        return totalAvailability / components.size();
    }

    public ArrayList<Component> getComponents() {
        return this.components;
    }

    public void saveDesign(String path) {
        Gson gson = new Gson();

        Map<String, Object> map = new HashMap<>();

        map.put("date_created", java.time.LocalDateTime.now().toString());
        map.put("custom", this.custom);

        ArrayList<Integer> componentIds = new ArrayList<>();
        ArrayList<Map> componentsHashmap = new ArrayList<>();

        // Format components into Hashmap for JSON file
        for (int i = 0; i < components.size(); i++) {
            // Only add to known components id's if not added yet or the list is still empty
            if (componentIds.size() > 0 || !componentIds.contains(components.get(i).getId())) {
                componentIds.add(components.get(i).getId());
            } else {
                continue;
            }

            int quantity = 0;
            // Loop through the remaining components in the list and increment quantity if it is the same component
            for (int x = i; x < components.size(); x++) {
                Component current = components.get(i);
                Component loop = components.get(x);

                if (current.getId() == loop.getId()) {
                    quantity++;
                }
            }

            Map<String, Object> componentMap = new HashMap<>();
            componentMap.put("id", components.get(i).getId());
            componentMap.put("quantity", quantity);
            componentsHashmap.add(componentMap);
        }

        map.put("components", componentsHashmap);

        // Convert Hashmaps to a JSON string
        String json = gson.toJson(map);

        try {
            // Create a new JSON file with given Design
            Files.write(Path.of(path + ".json"), json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean loadDesign(String path) {
        Gson gson = new Gson();
        ArrayList<Component> importComponents = new ArrayList<>();
        // create a reader
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        if (!jsonObject.has("custom") || !jsonObject.has("date_created") || !jsonObject.has("components")) {
            return false;
        }

        // Copy details from json to current Object.
        this.custom = jsonObject.get("custom").getAsBoolean();
        JsonArray components = jsonObject.getAsJsonArray("components");

        // Iterate over the nested components
        for (JsonElement element : components) {
            // Convert JsonElement to JsonObject to be able to access the attributes.
            JsonObject componentJSON = element.getAsJsonObject();
            if (componentJSON.has("id") && componentJSON.has("quantity")) {
                int id = componentJSON.get("id").getAsInt();
                int quantity = componentJSON.get("quantity").getAsInt();
                Component component = new Component(id);

                for (int i = 0; i < quantity; i++) {
                    importComponents.add(component);
                }
            }
        }

        add(importComponents);


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
