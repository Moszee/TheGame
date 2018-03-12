package org.szpax.game.world.calculators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.szpax.game.world.assets.Material.FOOD;
import static org.szpax.game.world.assets.Occupation.WOODCUTTER;
import static org.szpax.game.world.calculators.MaterialCalculationType.PRODUCTION;

public class CalculationKeyTest {

    @Test
    public void conformsForEqualKey() {
        CalculationKey baseKey = CalculationKey.builder()
                .calculationType(PRODUCTION)
                .material(FOOD)
                .build();


        CalculationKey verification = CalculationKey.builder()
                .calculationType(PRODUCTION)
                .material(FOOD)
                .build();

        assertThat(baseKey.conforms(verification)).isTrue();
    }

    @Test
    public void conformsForInclusiveKey() {
        CalculationKey baseKey = CalculationKey.builder()
                .calculationType(PRODUCTION)
                .material(FOOD)
                .build();


        CalculationKey verification = CalculationKey.builder()
                .calculationType(PRODUCTION)
                .build();

        assertThat(baseKey.conforms(verification)).isTrue();
    }

    @Test
    public void notConformsForBroaderKey() {
        CalculationKey baseKey = CalculationKey.builder()
                .calculationType(PRODUCTION)
                .material(FOOD)
                .build();


        CalculationKey verification = CalculationKey.builder()
                .calculationType(PRODUCTION)
                .material(FOOD)
                .occupation(WOODCUTTER)
                .build();

        assertThat(baseKey.conforms(verification)).isFalse();

    }

}