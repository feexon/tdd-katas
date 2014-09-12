import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 14-9-12.
 */
public class ChineseNumeralsConverterTest {

    private ChineseNumeralsConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new ChineseNumeralsConverter();
    }

    @Test
    public void digits() throws Exception {
        assertThat(converter.convert(0), equalTo("零"));
        assertThat(converter.convert(1), equalTo("壹"));
        assertThat(converter.convert(2), equalTo("贰"));
        assertThat(converter.convert(3), equalTo("叁"));
        assertThat(converter.convert(4), equalTo("肆"));
        assertThat(converter.convert(5), equalTo("伍"));
        assertThat(converter.convert(6), equalTo("陸"));
        assertThat(converter.convert(7), equalTo("柒"));
        assertThat(converter.convert(8), equalTo("捌"));
        assertThat(converter.convert(9), equalTo("玖"));
    }

    @Test
    public void high() throws Exception {
        assertThat(converter.convert(10), equalTo("壹拾"));
        assertThat(converter.convert(20), equalTo("贰拾"));
        assertThat(converter.convert(100), equalTo("壹佰"));
        assertThat(converter.convert(10000), equalTo("壹萬"));
        assertThat(converter.convert(100000000), equalTo("壹億"));
    }

    @Test
    public void noZeros() throws Exception {
        assertThat(converter.convert(12), equalTo("壹拾贰"));
        assertThat(converter.convert(120), equalTo("壹佰贰拾"));
    }

    @Test
    public void zeroInMiddle() throws Exception {
        assertThat(converter.convert(102), equalTo("壹佰零贰"));
        assertThat(converter.convert(1012), equalTo("壹仟零壹拾贰"));
    }

    @Test
    public void multiUnits() throws Exception {
        assertThat(converter.convert(100000), equalTo("壹拾萬"));
        assertThat(converter.convert(1000000), equalTo("壹佰萬"));
        assertThat(converter.convert(1020000), equalTo("壹佰零贰萬"));
        assertThat(converter.convert(1020010000), equalTo("壹拾億贰仟零壹萬"));
    }
}
