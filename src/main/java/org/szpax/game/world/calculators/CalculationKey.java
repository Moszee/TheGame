package org.szpax.game.world.calculators;

import org.szpax.game.world.assets.Building;
import org.szpax.game.world.assets.Material;
import org.szpax.game.world.assets.Occupation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CalculationKey {

    private final Map<Class, Object> partialKeys;

    public CalculationKey(Map<Class, Object> partialKeys) {
        this.partialKeys = Collections.unmodifiableMap(partialKeys);
    }

    public boolean conforms(CalculationKey otherKey) {
        boolean allThisPartialObjectsMatchOrAreNull = otherKey.partialKeys.entrySet()
                .stream()
                .allMatch(this::correspondingIsEqual);

        boolean allOtherPartialObjectsMatch = partialKeys.entrySet()
                .stream()
                .allMatch(this::correspondingIsNullOrEqual);


        return allThisPartialObjectsMatchOrAreNull && allOtherPartialObjectsMatch;
    }

    private boolean correspondingIsNullOrEqual(Map.Entry<Class, Object> it) {
        return this.partialKeys.get(it.getKey()) == null || correspondingIsEqual(it);
    }

    private boolean correspondingIsEqual(Map.Entry<Class, Object> it) {
        return this.partialKeys.get(it.getKey()) != null && this.partialKeys.get(it.getKey()).equals(it.getValue());
    }


    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof CalculationKey)) return false;

        CalculationKey otherCalculcationKey = (CalculationKey) other;

        Map<Class, Object> otherPartialKeys = otherCalculcationKey.partialKeys;
        Map<Class, Object> thisPartialKeys = this.partialKeys;

        if (!containsAll(otherPartialKeys, thisPartialKeys)) return false;
        if (!containsAll(thisPartialKeys, otherPartialKeys)) return false;

        return true;
    }

    private boolean containsAll(Map<Class, Object> otherPartialKeys, Map<Class, Object> thisPartialKeys) {
        for (Map.Entry<Class, Object> obj : otherPartialKeys.entrySet()) {
            Object thisValue = thisPartialKeys.get(obj.getKey());

            if (thisValue == null) return false;

            if (!thisValue.equals(obj.getValue())) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (Object o : partialKeys.values()) {
            result = 31 * result + (o != null ? o.hashCode() : 0);
        }
        return result;
    }

    public static Builder process(MaterialCalculationType materialCalculationType) {
        return new Builder(materialCalculationType);
    }

    public static Builder numberOf(BuildingCalculationType buildingCalculationType) {
        return new Builder(buildingCalculationType);
    }

    public static class Builder {
        private Map<Class, Object> partialKeys = new HashMap<>();

        public Builder(BuildingCalculationType buildingCalculationType) {
            partialKeys.put(BuildingCalculationType.class, buildingCalculationType);
        }

        public Builder(MaterialCalculationType materialCalculationType) {
            partialKeys.put(MaterialCalculationType.class, materialCalculationType);

        }

        public Builder of(Material material){
            partialKeys.put(Material.class, material);
            return this;
        }

        public Builder by(Occupation occupation) {
            partialKeys.put(Occupation.class, occupation);
            return this;
        }

        public Builder placesIn(Building building) {
            partialKeys.put(Building.class, building);
            return this;
        }

        public CalculationKey build() {
            return new CalculationKey(partialKeys);
        }

    }
}
