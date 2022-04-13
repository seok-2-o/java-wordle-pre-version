package presentation;

import word.domain.model.TextWordProvider;
import word.domain.model.Word;
import domain.model.Wordle;
import word.domain.model.WordProvider;

import presentation.ui.ConsoleView;

public class Application {

    public static void main(String[] args) {

        ConsoleView.welcome();
        WordProvider pool = new TextWordProvider();
        Wordle game = new Wordle(pool.getTodayWord());

        while (!game.isEnd()) {
            String input = ConsoleView.ask();
            try {
                Word answer = pool.wrap(input);
                game.attempt(answer);
            } catch (IllegalArgumentException e) {
                ConsoleView.error(e.getMessage(), input);
                continue;
            }
            ConsoleView.render(game.getLastAttempt());
        }
        ConsoleView.render(game.getAllAttempt());
        ConsoleView.printTodayWord(pool.toString());

    }
}
