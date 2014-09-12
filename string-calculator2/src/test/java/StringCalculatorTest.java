import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by L.x on 14-9-10.
 */
public class StringCalculatorTest {
    StringCalculator calculator = new StringCalculator();

    @Test
    public void emptyString() throws Exception {
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void singleNumber() throws Exception {
        assertEquals(1, calculator.add("1"));
        assertEquals(2, calculator.add("2"));
    }

    @Test
    public void addNumbers() throws Exception {
        assertEquals(3, calculator.add("1,2"));
        assertEquals(6, calculator.add("1,2,3"));
    }

    @Test
    public void separateNumbersByNewLineAsCommas() throws Exception {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    public void supportDifferentDelimiters() throws Exception {
        assertEquals(3, calculator.add("//;\n1;2"));
        assertEquals(5, calculator.add("//@\n2@3"));
    }

    @Test
    public void should_addNegativeNumber_raiseException() throws Exception {
        try {
            calculator.add("1,-2");
            fail("should failed!");
        } catch (IllegalArgumentException expected) {
            assertEquals("negatives not allowed: -2", expected.getMessage());
        }

    }

    @Test
    public void listAllAddedNegativeNumbersInSequence() throws Exception {
        try {
            calculator.add("-1,3,-2");
            fail("should failed!");
        } catch (IllegalArgumentException expected) {
            assertEquals("negatives not allowed: -1, -2", expected.getMessage());
        }

    }

    @Test
    public void numberBiggerThan1000WillIgnored() throws Exception {
        assertEquals(1001, calculator.add("1,1000"));
        assertEquals(1, calculator.add("1,1001"));
    }

    @Test
    public void anyLengthOfDelimiters() throws Exception {
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void allowMultipleDelimiters() throws Exception {
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void anyLengthOfMultipleDelimiters() throws Exception {
        assertEquals(6, calculator.add("//[***][%%]\n1%%2***3"));
    }

    @Test
    public void delimiterDefinitionOnly() throws Exception {
        assertEquals(0, calculator.add("//[***][%%]\n"));
    }
}
