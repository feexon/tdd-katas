import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 14-9-14.
 */
public class WordWrapperTest {

    @Test
    public void emptyString() throws Exception {
        assertThat(WordWrapper.wrap("", 1), equalTo(""));
    }

    @Test
    public void noWrap() throws Exception {
        assertThat(WordWrapper.wrap("the", 3), equalTo("the"));
    }

    @Test
    public void wrapWordOnce() throws Exception {
        assertThat(WordWrapper.wrap("the", 2), equalTo("th\ne"));
    }

    @Test
    public void wrapWordTwice() throws Exception {
        assertThat(WordWrapper.wrap("the", 1), equalTo("t\nh\ne"));
    }

    @Test
    public void spaceOnBreakpoint_willBeDiscarded() throws Exception {
        assertThat(WordWrapper.wrap("the bar", 3), equalTo("the\nbar"));
    }

    @Test
    public void spaceBeforeBreakpoint_willBeDiscarded() throws Exception {
        assertThat(WordWrapper.wrap("the bar", 4), equalTo("the\nbar"));
    }

    @Test
    public void breakingAtSpaceManyTimes() throws Exception {
        assertThat(WordWrapper.wrap("the bar color", 5), equalTo("the\nbar\ncolor"));
    }

    @Test
    public void breakingWordsAndSpaces() throws Exception {
        assertThat(WordWrapper.wrap("the bar", 2), equalTo("th\ne\nba\nr"));
    }
}
