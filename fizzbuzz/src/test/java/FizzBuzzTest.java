import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 14-9-14.
 */
public class FizzBuzzTest {

    private String fizzbuzz(int number) {
        if (fizz(number)) {
            return buzz(number) ? "FizzBuzz" : "Fizz";
        }
        if (buzz(number)) {
            return "Buzz";
        }
        return String.valueOf(number);

    }

    private boolean buzz(int number) {
        return number % 5 == 0;
    }

    private boolean fizz(int number) {
        return number % 3 == 0;
    }

    @Test
    public void asString() throws Exception {
        assertThat(fizzbuzz(1), equalTo("1"));
        assertThat(fizzbuzz(2), equalTo("2"));
    }

    @Test
    public void fizz() throws Exception {
        assertThat(fizzbuzz(3), equalTo("Fizz"));
        assertThat(fizzbuzz(6), equalTo("Fizz"));
    }

    @Test
    public void buzz() throws Exception {
        assertThat(fizzbuzz(5), equalTo("Buzz"));
        assertThat(fizzbuzz(10), equalTo("Buzz"));
    }

    @Test
    public void fizz_and_buzz() throws Exception {
        assertThat(fizzbuzz(15), equalTo("FizzBuzz"));
    }
}
