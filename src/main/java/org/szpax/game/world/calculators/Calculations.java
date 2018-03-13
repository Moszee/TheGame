package org.szpax.game.world.calculators;

import org.szpax.game.world.Kingdom;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static org.szpax.game.world.assets.Building.HOUSE;

public class Calculations {

    private Map<CalculationKey, Calculator> calculators = new HashMap<>();

    public Calculations(Map<CalculationKey, Calculator> calculators) {
        this.calculators.putAll(calculators);
    }

    public KingdomCalculation get(CalculationKey foodProduction) {
        return (Kingdom kingdom) -> calculators.entrySet()
                .stream()
                .filter(it -> it.getKey().conforms(foodProduction))
                .mapToDouble(it -> it.getValue().calculateChange(kingdom));
    }

    public Double freeHousing(Kingdom kingdom) {
        return (kingdom.getBuildings().get(HOUSE) * 5d) - kingdom.getPopulation().total();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Map<CalculationKey, Calculator> calculators = new HashMap<>();

        public FormulaStep calculationOf(CalculationKey calculationKey) {
            return new FormulaStep(this, calculationKey);
        }

        public Calculations build() {
            return new Calculations(calculators);
        }
    }

    public static class FormulaStep {
        private final Builder builder;
        private final CalculationKey calculationKey;

        public FormulaStep(Builder builder, CalculationKey calculationKey) {
            this.builder = builder;
            this.calculationKey = calculationKey;
        }

        public Builder formula(Calculator calculator) {
            builder.calculators.put(calculationKey, calculator);
            return builder;
        }
    }

    @FunctionalInterface
    public interface KingdomCalculation {
        DoubleStream in(Kingdom kingdom);
    }
}
