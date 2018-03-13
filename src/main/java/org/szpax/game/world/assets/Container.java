package org.szpax.game.world.assets;

import org.szpax.game.world.exceptions.NotEnoughResourcesException;

import java.util.HashMap;
import java.util.Map;

public class Container<T extends Named> {

    private Map<T, Double> elements = new HashMap<>();
    private String name;

    public Container(String name, T[] values) {
        for (T m : values) {
            elements.put(m, 0d);
        }
        this.name = name;
    }

    public Container(Container<T> other) {
        for (Map.Entry<T, Double> m : other.elements.entrySet()) {
            elements.put(m.getKey(), m.getValue());
        }
        this.name = other.name;
    }

    public Double get(T type) {
        return elements.get(type);
    }

    public void add(T type, Double amount) {
        elements.put(type, elements.get(type) + amount);
    }

    public void take(T type, Double amount) throws NotEnoughResourcesException {
        Double value = elements.get(type) - amount;
        if (value < 0) {
            throw new NotEnoughResourcesException(amount, elements.get(type), type);
        }
        elements.put(type, value);
    }

    public Double total() {
        return elements.values().stream().reduce((i1, i2) -> i1 + i2).orElse(0d);
    }

    public String describeState() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":").append(System.lineSeparator());
        for (Map.Entry<T, Double> entries : elements.entrySet()) {
            sb.append("    ").append(entries.getKey().getName()).append(": ").append(entries.getValue()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
