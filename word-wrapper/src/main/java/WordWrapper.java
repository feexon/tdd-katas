/**
 * Created by L.x on 14-9-14.
 */
public class WordWrapper {

    public static String wrap(String sentence, int columns) {
        if (sentence.length() <= columns) {
            return sentence;
        }
        return breaking(sentence, atBreakpoint(sentence, columns), columns);
    }

    private static int atBreakpoint(String sentence, int columns) {
        int space = sentence.lastIndexOf(' ', columns);
        return space != -1 ? space : columns;
    }

    private static String breaking(String sentence, int breakpoint, int columns) {
        return sentence.substring(0, breakpoint) + "\n" + wrap(trimLeft(sentence.substring(breakpoint)), columns);
    }

    private static String trimLeft(String string) {
        return string.replaceFirst("^\\s+", "");
    }
}
