package presentation.ui;

import domain.model.word.Matches;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView {

    public static final String RESET = "\033[0m";  // Text Reset

    private static final String SYMBOL = "■";
    private static final Map<Matches.Type, String> SYMBOLS = Map.of(
            Matches.Type.MATCH, "\u001B[32m" + SYMBOL + "\u001B[32m",
            Matches.Type.EXIST, "\u001B[33m" + SYMBOL + "\u001B[33m",
            Matches.Type.MISMATCH, "\u001B[37m" + SYMBOL + "\u001B[37m"
    );

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void render(List<Matches> matches) {
        System.out.println(RESET + "게임이 종료되었습니다." + RESET);
        matches.forEach(ConsoleView::render);
    }

    public static void render(Matches matches) {
        matches.stream()
                .map(SYMBOLS::get)
                .forEach(System.out::print);
        System.out.println();
    }


    public static void printTodayWord(String word) {
        System.out.println(RESET + "오늘의 단어는 " + word + RESET);
    }

    public static void welcome() {
        System.out.println("오늘의 단어를 맞춰볼까요?");
    }

    public static void error(String message, String input) {
        System.out.println(RESET + message + ":" + input + RESET);
    }

    public static String ask() {
        System.out.print(RESET + "단어를 입력해주세요. " + RESET);
        return SCANNER.nextLine();
    }
}
