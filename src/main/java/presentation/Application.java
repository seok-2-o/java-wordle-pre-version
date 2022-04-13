package presentation;

import domain.model.TodayWordle;
import domain.model.Word;
import utility.WordPool;
import presentation.ui.ConsoleView;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        ConsoleView.welcome();
        TodayWordle game = new TodayWordle(WordPool.getTodayWord());

        while (!game.isEnd()) {
            String input = ConsoleView.ask();
            try {
                Word word = new Word(input);
                game.attempt(word);
            } catch (IllegalArgumentException e) {
                ConsoleView.error(input);
                continue;
            }
            ConsoleView.render(game.getLastAttempt());
        }
        ConsoleView.render(game.getAllAttempt());
        ConsoleView.printTodayWord();

    }
}
