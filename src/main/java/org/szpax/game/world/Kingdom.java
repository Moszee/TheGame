package org.szpax.game.world;

import org.szpax.game.world.assets.*;

import static org.szpax.game.world.assets.Building.HOUSE;
import static org.szpax.game.world.assets.Material.FOOD;
import static org.szpax.game.world.assets.Occupation.FORAGER;
import static org.szpax.game.world.assets.Occupation.WOODCUTTER;
import static org.szpax.game.world.assets.Resource.BERRIES;

public class Kingdom {
    private Container<Material> storage = new Container<>("materials", Material.values());
    private Container<Occupation> population = new Container<>("population", Occupation.values());
    private Container<Building> buildings = new Container<>("buildings", Building.values());

    private Container<Resource> resources = new Container<>("resources", Resource.values());

    private final String name;

    public Kingdom(String name) {
        this.storage.add(FOOD, 10);

        this.population.add(FORAGER, 10);
        this.population.add(WOODCUTTER, 5);

        this.buildings.add(HOUSE, 4);

        this.resources.add(BERRIES, 32);

        this.name = name;
    }

    public Container<Material> getStorage() {
        return storage;
    }

    public Container<Occupation> getPopulation() {
        return population;
    }

    public Container<Building> getBuildings() {
        return buildings;
    }

    public Container<Resource> getResources() {
        return resources;
    }

    public String describeState() {
        StringBuilder sb = new StringBuilder();
        sb.append("kingdom '").append(name).append("':").append(System.lineSeparator());
        sb.append(storage.describeState()).append(System.lineSeparator());
        sb.append(population.describeState()).append(System.lineSeparator());
        sb.append(buildings.describeState()).append(System.lineSeparator());
        return sb.toString();
    }
}
