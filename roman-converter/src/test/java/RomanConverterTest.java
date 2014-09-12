import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 14-9-11.
 */
@RunWith(Parameterized.class)
public class RomanConverterTest {

    private RomanConverter converter;
    private int arabic;
    private String roman;

    public RomanConverterTest(int arabic, String roman) {
        this.arabic = arabic;
        this.roman = roman;
    }

    @Before
    public void setUp() throws Exception {
        converter = new RomanConverter();
    }

    @Parameterized.Parameters(name = "{0} must be {1}")
    public static Collection<Object[]> parameters() {
        return asList(new Object[][]{
                {0, ""},
                {1, "I"},
                {5, "V"},
                {2, "II"},
                {6, "VI"},
                {10, "X"},
                {9, "IX"},
                {50, "L"},
                {40, "XL"},
                {100, "C"},
                {90, "XC"},
                {500, "D"},
                {400, "CD"},
                {1000, "M"},
                {900, "CM"},
                {4999, "MMMMCMXCIX"}
        });
    }

    @Test
    public void conversions() throws Exception {
        assertThat(converter.convert(arabic), equalTo(roman));
    }


}
