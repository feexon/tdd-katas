import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by L.x on 14-9-10.
 */
public class StringCalculator {
    public int add(String numbers) {
        Matcher definition = Pattern.compile("^//(?:(?:\\[.*?\\])+|.)\n").matcher(numbers);
        if (definition.find()) {
            return sum(of(numbers.substring(definition.end())).split(by(numbers.substring(2, definition.end() - 1))));
        }
        return sum(of(numbers).split("[,\n]"));
    }

    private String of(String numbers) {
        return numbers.isEmpty() ? "0" : numbers;
    }

    private String by(String definition) {
        Matcher matcher = Pattern.compile("\\[(.*?)\\]").matcher(definition);
        if (!matcher.find()) {
            return delimiter(definition);
        }

        String[] delimiters = new String[definition.length() / 3];
        int n = 0;
        do {
            delimiters[n++] = delimiter(matcher.group(1));
        } while (matcher.find());
        return join(Arrays.copyOf(delimiters, n), "|");
    }

    private String delimiter(String definition) {
        return Pattern.quote(definition);
    }

    private String join(Object[] array, String delimiter) {
        if (array.length == 0) {
            return "";
        }
        StringBuilder out = new StringBuilder().append(array[0]);
        for (int i = 1; i < array.length; i++) {
            out.append(delimiter).append(array[i]);
        }

        return out.toString();
    }

    private int sum(String[] numbers) {
        return sum(lessThan(1001, of(numbers)));
    }

    private Integer[] of(String[] numbers) {
        Integer[] integers = new Integer[numbers.length];
        int i = 0;
        for (String number : numbers) {
            integers[i++] = Integer.parseInt(number);
        }
        return integers;
    }

    private Integer[] lessThan(int max, Integer[] numbers) {
        Integer[] result = new Integer[numbers.length];
        int n = 0;
        for (int number : numbers) {
            if (number < max) {
                result[n++] = number;
            }
        }
        return Arrays.copyOf(result, n);
    }

    private int sum(Integer[] numbers) {
        Integer[] negatives = lessThan(0, numbers);
        if (negatives.length > 0) {
            throw new IllegalArgumentException("negatives not allowed: " + join(negatives, ", "));
        }
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

}
