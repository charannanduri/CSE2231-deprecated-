import static org.junit.Assert.assertEquals;
import static components.sequence.Sequence1L.*;
import static components.sequence.Sequence.*;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * @author Evan Frisbie
 *
 */
public class SmoothTest {

    /*
     * Tests of smooth
     */

    // Test for correct output
    @Test
    public void testReduceToGCD_0_0() {
        Sequence case1 = {5, 6, 7, 8};
        sequence case2 = {1, 2, 3};
        smooth(case1, case2);
        assertEquals({5, 6, 7, 8}, case1);
        assertEquals({5, 6, 8}, case2);
    }

    //Test for length constraint
    @Test
    public void testReduceToGCD_30_21() {
        Sequence case1 = {1};
        sequence case2 = {3, 4, 5};
        smooth(case1, case2);
        assertEquals({1}, case1);
        assertEquals({}, case2);
    }

}