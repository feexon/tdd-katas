import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by L.x on 14-9-11.
 */
public class RomanConverter {

    private static final int ZERO = 0;
    private static final String NOTHING = "";

    public String convert(int arabic) {
        if (arabic == ZERO) {
            return NOTHING;
        }
        Conversion conversion = Conversion.closingTo(arabic);
        return conversion.name() + convert(arabic - conversion.value());
    }

    enum Conversion {
        I(1), V(5), IX(9), X(10), XL(40), L(50), XC(90), C(100), CD(400), D(500), CM(900), M(1000);
        private int value;
        private static final Conversion[] CONVERSIONS = values();

        static {
            Arrays.sort(CONVERSIONS, orderByValueDesc());
        }

        private static Comparator<Conversion> orderByValueDesc() {
            return new Comparator<Conversion>() {
                @Override
                public int compare(Conversion o1, Conversion o2) {
                    return -o1.value() + o2.value();
                }
            };
        }

        private static Conversion closingTo(int arabic) {
            for (Conversion conversion : CONVERSIONS) {
                if (arabic >= conversion.value()) {
                    return conversion;
                }
            }
            return I;
        }

        Conversion(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }
}
