package org.szpax.game.world.assets;

import org.szpax.game.world.exceptions.NotEnoughResourcesException;

import java.util.HashMap;
import java.util.Map;

public class Container<T extends Named> {

    private Map<T, Integer> elements = new HashMap<>();
    private String name;

    public Container(String name, T[] values) {
        for(T m : values) {
            elements.put(m, 0);
        }
        this.name = name;
    }

    public int get(T type) {
        return elements.get(type);
    }

    public void add(T type, int amount) {
        elements.put(type, elements.get(type) + amount);
    }

    public void take(T type, int amount) throws NotEnoughResourcesException {
        int value = elements.get(type) - amount;
        if(value < 0) {
            throw new NotEnoughResourcesException(amount, elements.get(type), type);
        }
        elements.put(type, value);
    }

    public int total() {
        return elements.values().stream().reduce((i1, i2) -> i1 + i2).orElse(0);
    }

    public String describeState() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":").append(System.lineSeparator());
        for(Map.Entry<T, Integer> entries : elements.entrySet()) {
            sb.append("    ").append(entries.getKey().getName()).append(": ").append(entries.getValue()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
