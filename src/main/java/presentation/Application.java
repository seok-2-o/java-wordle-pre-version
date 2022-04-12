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

        Scanner scanner = new Scanner(System.in);
        while (!game.isEnd()) {
            game.attempt(new Word(scanner.nextLine()));
            ConsoleView.render(game.getLastAttempt());
        }
        ConsoleView.render(game.getAllAttempt());
        ConsoleView.printTodayWord();

    }
}
