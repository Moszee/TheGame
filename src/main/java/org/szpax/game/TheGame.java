package org.szpax.game;

import lombok.extern.slf4j.Slf4j;
import org.szpax.game.commands.Command;
import org.szpax.game.commands.ExitCommand;
import org.szpax.game.commands.NextTurnCommand;
import org.szpax.game.world.Kingdom;
import org.szpax.game.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.szpax.game.world.StandardCalculationSet.STANDARD_CALCULATIONS;
import static org.szpax.game.world.StandardEventChain.EVENT_CHAIN;

@Slf4j
public class TheGame {

    private static int turnNumber = 0;

    private List<Command> commands = new ArrayList<>();
    private final World world;

    private TheGame() {
        commands.add(new NextTurnCommand());
        commands.add(new ExitCommand());

        world = World.newWorld()
                .withCalculcations(STANDARD_CALCULATIONS)
                .withEventChain(EVENT_CHAIN).create();

    }

    public static void main(String[] args) throws Exception {
        new TheGame().play();
    }

    private void play() throws Exception {
        Scanner in = new Scanner(System.in);
        Kingdom kingdom = new Kingdom("test", world);

        while (true) {
            System.out.println("========~~~~ TURN " + turnNumber++ + " ~~~~========");
            log.info("Turn number: {}", turnNumber);
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
