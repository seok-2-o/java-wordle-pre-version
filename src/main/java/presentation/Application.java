package presentation;

import infra.TextWordProvider;
import domain.model.Word;
import domain.model.Wordle;
import infra.WordProvider;

import presentation.ui.ConsoleView;


public class Application {

    public static void main(String[] args) {

        ConsoleView.welcome();
        WordProvider provider = new TextWordProvider();
        Word today = Word.todayWord(provider);
        Wordle game = new Wordle(today);

        while (!game.isEnd()) {
            String input = ConsoleView.ask();
            try {
                Word answer = new Word(input, provider);
                game.attempt(answer);
            } catch (IllegalArgumentException e) {
                ConsoleView.error(e.getMessage(), input);
                continue;
            }
            ConsoleView.render(game.getLastAttempt());
        }

        ConsoleView.render(game.getAllAttempt());
        ConsoleView.printTodayWord(today.toString());

    }
}
