import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * @author Evan Frisbie
 *
 */
public class SequenceTest {

    /*
     * Tests of smooth
     */

    // Test for correct adding
    @Test
    public void testAdd() {
        Sequence<Integer> test = new Sequence1L<Integer>();

        test.add(0, 1);
        test.add(1, 2);
        test.add(2, 3);

        assertEquals("[1, 2, 3]", test);
    }

    //Test for correct removal
    @Test
    public void testRemove() {
        Sequence<Integer> test = new Sequence1L<Integer>();

        test.add(0, 1);
        test.add(1, 2);
        test.add(2, 3);
        test.remove(1);

        assertEquals("[1, 3]", test);
    }

    //Test for correct length
    @Test
    public void testLength() {
        Sequence<Integer> test = new Sequence1L<Integer>();

        test.add(0, 1);
        test.add(1, 2);
        test.add(2, 3);
        test.remove(1);

        assertEquals(2, test.length());
    }

}