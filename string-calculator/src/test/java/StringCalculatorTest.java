import org.hamcrest.CoreMatchers;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Ignore;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by L.x on 14-8-27.
 */
public class StringCalculatorTest {
    StringCalculator calculator = new StringCalculator();

    private class CalculatorAssert {

        private String numbers;

        public CalculatorAssert(String numbers) {
            this.numbers = numbers;
        }

        public void is(int expected) {
            assertThat(calculator.add(numbers), equalTo(expected));
        }

        public void raising(String message) {
            raising(message(message));
        }

        private void raising(Matcher<Exception> matcher) {
            try {
                calculator.add(numbers);
                fail("should raise exception");
            } catch (IllegalArgumentException expected) {
                assertThat(expected, matcher);
            }
        }

        private FeatureMatcher<Exception, String> message(final String message) {
            return new FeatureMatcher<Exception, String>(equalTo(message), "message", "message") {
                @Override
                protected String featureValueOf(Exception actual) {
                    return actual.getMessage();
                }
            };
        }

        public void raising(Class<? extends Exception> errorType) {
            raising(CoreMatchers.<Exception>instanceOf(errorType));
        }
    }

    @Test
    public void addEmptyString_returnZero() throws Exception {
        sum("").is(0);
    }

    private CalculatorAssert sum(String numbers) {
        return new CalculatorAssert(numbers);
    }

    @Test
    public void parseToIntWithSingleNumber() throws Exception {
        sum("0").is(0);
        sum("1").is(1);
    }

    @Test
    public void sumNumbers() throws Exception {
        sum("1,2").is(3);
        sum("1,2,3").is(6);
    }

    @Test
    public void allowNewLinesBetweenNumbers() throws Exception {
        sum("1\n2,3").is(6);
    }

    @Test
    @Ignore
    public void shouldSeparatorsTogether_raiseException() throws Exception {
        sum("1,\n").raising(NumberFormatException.class);
    }

    @Test
    public void usingCustomNumberSeparator() throws Exception {
        sum("//;\n1;2").is(3);
    }

    @Test
    public void firstLineIsOptional() throws Exception {
        sum("1;2").is(3);
    }

    @Test
    public void negativeNumberNotAllowed() throws Exception {
        sum("-1").raising("negatives not allowed: -1");
        sum("-1,-2").raising("negatives not allowed: -1 -2");
    }


    @Test
    public void numberLargerThan1000_willIgnored() throws Exception {
        sum("2,1001").is(2);
    }

    @Test
    public void delimitersCanAnyOfLength() throws Exception {
        sum("//*\n2*3").is(5);
        sum("//[*]\n2*3").is(5);
        sum("//[***]\n1***2***3").is(6);
    }

    @Test
    public void allowMultiDelimiters() throws Exception {
        sum("//[*][%]\n1*2%3").is(6);
    }

    @Test
    public void allowMultiDelimitersLongerThanOneChar() throws Exception {
        sum("//[*-][%$]\n1*-2%$3").is(6);
    }

    @Test
    public void delimiterDefinitionOnly() throws Exception {
        sum("//[*-][%$]\n").is(0);
    }


}

