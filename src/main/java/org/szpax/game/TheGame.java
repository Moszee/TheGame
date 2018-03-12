package org.szpax.game;

import org.szpax.game.commands.Command;
import org.szpax.game.commands.ExitCommand;
import org.szpax.game.commands.NextTurnCommand;
import org.szpax.game.world.Kingdom;
import org.szpax.game.world.World;
import org.szpax.game.world.assets.Occupation;
import org.szpax.game.world.calculators.Calculations;
import org.szpax.game.world.events.EventChain;
import org.szpax.game.world.events.events.Construction;
import org.szpax.game.world.events.events.Consumption;
import org.szpax.game.world.events.events.Migration;
import org.szpax.game.world.events.events.Production;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.szpax.game.world.assets.Building.HOUSE;
import static org.szpax.game.world.assets.Material.FOOD;
import static org.szpax.game.world.assets.Material.WOOD;
import static org.szpax.game.world.assets.Occupation.FORAGER;
import static org.szpax.game.world.assets.Occupation.WOODCUTTER;
import static org.szpax.game.world.assets.Resource.BERRIES;
import static org.szpax.game.world.calculators.CalculationKeys.*;

public class TheGame {

    private static int turnNumber = 0;

    private List<Command> commands = new ArrayList<>();
    private final World world;

    private TheGame() {
        commands.add(new NextTurnCommand());
        commands.add(new ExitCommand());

        Calculations calculations = Calculations.builder()

                .calculationOf(FOOD_CONSUMPTION)
                .formula((kingdom, world) -> kingdom.getPopulation().total() / 5)

                .calculationOf(FORAGER_FOOD_PRODUCTION)
                .formula((kingdom, world) -> {
                            Integer foragers = kingdom.getPopulation().get(Occupation.FORAGER);
                            Integer berries = kingdom.getResources().get(BERRIES);

                            return (int) Math.floor(Math.min(foragers / 2, berries * Math.log(foragers + 1)));
                        }
                )

                .calculationOf(WOODCUTTER_WOOD_PRODUCTION)
                .formula((kingdom, world) -> kingdom.getPopulation().get(WOODCUTTER) / 2)

                .calculationOf(FREE_HOUSING)
                .formula((kingdom, world) -> kingdom.getBuildings().get(HOUSE) * 5 - kingdom.getPopulation().total())


                .build();

        EventChain.Builder eventChain = EventChain.builder()
                .addEvent(Production.of(FOOD))
                .addEvent(Consumption.of(FOOD))
                .addEvent(Production.of(WOOD))
                .addSaturatingEvent(Migration.of(FORAGER)
                        .requires(kingdom -> {
                            int foodProduced = calculations.get(FOOD_PRODUCTION).in(kingdom).sum();
                            int foodConsumed = calculations.get(FOOD_CONSUMPTION).in(kingdom).sum();

                            return foodProduced - foodConsumed < 4;
                        })
                        .requires(kingdom -> calculations.get(FREE_HOUSING).in(kingdom).sum() > 0)
                        .requires(4, FOOD))
                .addSaturatingEvent(Migration.of(WOODCUTTER)
                        .requires(kingdom -> calculations.get(FREE_HOUSING).in(kingdom).sum() > 0)
                        .requires(4, FOOD))
                .addSaturatingEvent(Construction.of(HOUSE)
                        .requires(10, WOOD));

        world = World.newWorld()
                .withCalculcations(calculations)
                .withEventChain(eventChain).create();

    }

    public static void main(String[] args) throws Exception {
        new TheGame().play();
    }

    private void play() throws Exception {
        Scanner in = new Scanner(System.in);
        Kingdom kingdom = new Kingdom("test");

        while (true) {
            System.out.println("========~~~~ TURN " + turnNumber++ + " ~~~~========");
            Command command = null;
            while (command == null || !command.isTerminating()) {
                System.out.println(kingdom.describeState());
                System.out.println("What now?");

                for (int i = 0; i < commands.size(); i++) {
                    System.out.println("    " + i + ") " + commands.get(i).getDescription());
                }

                command = commands.get(in.nextInt());
                command.doWork(kingdom);

                if (command.isTerminating())
                    world.executeEventsIn(kingdom);
            }
        }
    }

}
