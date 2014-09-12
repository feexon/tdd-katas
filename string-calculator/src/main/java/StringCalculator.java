import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by L.x on 14-8-27.
 */
public class StringCalculator {
    public int add(String expression) {
        Numbers numbers = Numbers.from(expression);
        Numbers negatives = numbers.negatives();
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " + negatives.toString());
        }
        return numbers.in(1000).sum();
    }


}

class Numbers {

    private int[] numbers;

    private Numbers(String expression) {
        parse(expression);
    }

    public Numbers(int[] numbers) {
        this.numbers = numbers;
    }

    private void parse(String expression) {
        numbers = convert(compile(expression));
    }

    private int[] convert(String[] all) {
        int[] integers = new int[all.length];
        for (int i = 0; i < all.length; i++) {
            integers[i] = Integer.parseInt(all[i]);
        }
        return integers;
    }

    private String[] compile(String expression) {
        Matcher delimitersDefinition = Pattern.compile("^//(.*?)\n").matcher(expression);
        if (delimitersDefinition.find()) {
            return split(expression.substring(delimitersDefinition.end()), delimiters(delimitersDefinition.group(1)));
        }
        return split(expression, "[\n,;]");
    }

    private String[] split(String numbers, String delimiters) {
        return numbers.isEmpty() ? new String[0] : numbers.split(delimiters);
    }

    private String delimiters(String definition) {
        String delimiters = definition.replaceAll("\\[([^\\[\\]]+)\\]", "\\\\Q$1\\\\E|");
        return delimiters.equals(definition) ? Pattern.quote(definition) : delimiters.substring(0, delimiters.length() - 1);
    }

    public int[] values() {
        return numbers;
    }

    public int count() {
        return numbers.length;
    }

    public boolean isEmpty() {
        return count() == 0;
    }

    public static Numbers from(String expression) {
        return new Numbers(expression);
    }

    public Numbers negatives() {
        int[] values = values();
        int n = 0;
        int[] negatives = new int[values.length];
        for (int value : values) {
            if (value < 0) {
                negatives[n++] = value;
            }
        }
        return new Numbers(copy(negatives, n));
    }

    private static int[] copy(int[] array, int n) {
        int[] result = new int[n];
        System.arraycopy(array, 0, result, 0, n);
        return result;
    }

    public Numbers in(int max) {
        int[] filtered = new int[numbers.length];
        int n = 0;
        for (int number : numbers) {
            if (number <= max) {
                filtered[n++] = number;
            }
        }
        return new Numbers(copy(filtered, n));
    }

    public int sum() {
        int sum = 0;
        for (int value : values()) {
            sum += value;
        }
        return sum;
    }

    public String toString() {
        if (isEmpty()) {
            return "";
        }
        StringBuilder out = new StringBuilder().append(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            out.append(" ").append(numbers[i]);
        }
        return out.toString();
    }
}