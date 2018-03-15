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
    private final World world;

    public Kingdom(String name, World world) {
        this.storage.add(FOOD, 10d);

        this.population.add(FORAGER, 10d);
        this.population.add(WOODCUTTER, 5d);

        this.buildings.add(HOUSE, 4d);

        this.resources.add(BERRIES, 10d);

        this.name = name;
        this.world = world;
    }

    private Kingdom(Container<Material> storage,
                    Container<Occupation> population,
                    Container<Building> buildings,
                    Container<Resource> resources,
                    String name,
                    World world) {
        this.storage = new Container<>(storage);
        this.population = new Container<>(population);
        this.resources = new Container<>(resources);
        this.buildings = new Container<>(buildings);
        this.name = name;
        this.world = world;
    }

    public World world() {
        return world;
    }

    public Kingdom copy() {
        return new Kingdom(
                this.storage,
                this.population,
                this.buildings,
                this.resources,
                this.name,
                this.world
        );
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
        sb.append(resources.describeState()).append(System.lineSeparator());
        return sb.toString();
    }

    public String getName() {
        return name;
    }
}
