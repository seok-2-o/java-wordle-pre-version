package presentation;

import domain.model.word.TextWordProvider;
import domain.model.word.Word;
import domain.model.Wordle;
import domain.model.word.WordProvider;

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
        ConsoleView.printTodayWord(pool.getTodayWord().toString());

    }
}
