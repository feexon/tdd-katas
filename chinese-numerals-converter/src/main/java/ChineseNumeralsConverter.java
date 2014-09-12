import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by L.x on 14-9-12.
 */
public class ChineseNumeralsConverter {
    private static final String[] DIGITS = "零壹贰叁肆伍陸柒捌玖".split("(?!^)");
    public static final String ZERO = DIGITS[0];

    public String convert(int number) {
        if (number < DIGITS.length) {
            return DIGITS[number];
        }
        Unit unit = Unit.highestOf(number);
        return convert(unit.amountOf(number)) + unit.mean + convert(unit, number);
    }

    private String convert(Unit high, int number) {
        if (high.isTailingZeros(number)) {
            return "";
        }
        int rest = high.rest(number);
        return high.containsZerosBetween(rest)?ZERO + convert(rest) : convert(rest);
    }

    private enum Unit {
        HUNDRED_MILLION("億", 100000000),
        MYRIAD("萬", 10000),
        THOUSAND("仟", 1000),
        HUNDRED("佰", 100),
        TEN("拾", 10);
        private static final int RADIX = 10;
        public final String mean;
        public final int value;
        private static final Unit[] UNITS = values();

        static {
            Arrays.sort(UNITS, orderByValueDesc());
        }

        private static Comparator<Unit> orderByValueDesc() {
            return new Comparator<Unit>() {
                @Override
                public int compare(Unit o1, Unit o2) {
                    return -o1.value + o2.value;
                }
            };
        }

        Unit(String mean, int value) {
            this.mean = mean;
            this.value = value;
        }

        private static Unit highestOf(int number) {
            for (Unit unit : UNITS) {
                if (number >= unit.value) {
                    return unit;
                }
            }
            return null;
        }

        private boolean containsZerosBetween(int number) {
            return number * RADIX < value;
        }

        private boolean isTailingZeros(int number) {
            return rest(number) == 0;
        }

        private int rest(int number) {
            return number % value;
        }

        private int amountOf(int number) {
            return number / value;
        }
    }
}
