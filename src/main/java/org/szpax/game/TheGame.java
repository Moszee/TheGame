package org.szpax.game;

import org.szpax.game.commands.Command;
import org.szpax.game.commands.ExitCommand;
import org.szpax.game.commands.NextTurnCommand;
import org.szpax.game.world.Kingdom;
import org.szpax.game.world.calculators.KingdomCalculator;
import org.szpax.game.world.events.events.Construction;
import org.szpax.game.world.events.EventChain;
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

public class TheGame {

    private static int turnNumber = 0;

    private List<Command> commands = new ArrayList<>();
    private final EventChain eventChain;
    private final KingdomCalculator kingdomCalculator = new KingdomCalculator();

    private TheGame() {
        commands.add(new NextTurnCommand());
        commands.add(new ExitCommand());

        eventChain = EventChain.builder()
                .addEvent(Production.of(FOOD).build())
                .addEvent(Production.of(WOOD).build())
                .addSaturatingEvent(Migration.of(FORAGER)
                        .requires(kingdom -> kingdomCalculator.foodChange(kingdom) < 4)
                        .requires(kingdom -> kingdomCalculator.freeHousing(kingdom) > 0)
                        .requires(4, FOOD).build())
                .addSaturatingEvent(Migration.of(WOODCUTTER)
                        .requires(kingdom -> kingdomCalculator.freeHousing(kingdom) > 0)
                        .requires(4, FOOD).build())
                .addSaturatingEvent(Construction.of(HOUSE)
                        .requires(10, WOOD).build()).build();

    }

    public static void main(String[] args) throws Exception {
        new TheGame().play();
    }

    private void play() throws Exception {
        Scanner in = new Scanner(System.in);
        Kingdom kingdom = new Kingdom("test");

        while (true) {
            System.out.println("========~~~~ TURN " + turnNumber++ + " ~~~~========");
            kingdom.calculate();
            Command command = null;
            while (command == null || !command.isTerminating()) {
                System.out.println(kingdom.describeState());
                System.out.println("What next?");

                for (int i = 0; i < commands.size(); i++) {
                    System.out.println("    " + i + ") " + commands.get(i).getDescription());
                }

                command = commands.get(in.nextInt());
                command.doWork(kingdom);

                if (command.isTerminating())
                    eventChain.play(kingdom);
            }
        }
    }

}
