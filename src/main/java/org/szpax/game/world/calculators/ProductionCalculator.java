package org.szpax.game.world.calculators;

import org.szpax.game.world.Kingdom;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ProductionCalculator {

    private final List<Function<Kingdom, Integer>> consumptions;
    private final List<Function<Kingdom, Integer>> productions;

    public ProductionCalculator(List<Function<Kingdom, Integer>> consumptions, List<Function<Kingdom, Integer>> productions) {
        this.consumptions = consumptions;
        this.productions = productions;
    }

    public Integer calculateChange(Kingdom kingdom) {
        Integer totalProduced = productions.stream().mapToInt(it -> it.apply(kingdom)).sum();
        Integer totalConsumed = consumptions.stream().mapToInt(it -> it.apply(kingdom)).sum();

        return totalProduced - totalConsumed;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<Function<Kingdom, Integer>> consumptions = new ArrayList<>();
        private List<Function<Kingdom, Integer>> productions = new ArrayList<>();

        public Builder addConsumption(Function<Kingdom, Integer> consumption) {
            consumptions.add(consumption);
            return this;
        }

        public Builder addProduction(Function<Kingdom, Integer> production) {
            productions.add(production);
            return this;
        }

        public ProductionCalculator build() {
            return new ProductionCalculator(consumptions, productions);
        }

    }
}
