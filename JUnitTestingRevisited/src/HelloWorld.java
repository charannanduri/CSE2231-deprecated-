package components.queue;

import components.queue.QueueSecondary;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code Queue} represented as a {@code Sequence} of entries, with
 * implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @correspondence this = $this.entries
 */
public class Queue3<T> extends QueueSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Entries included in {@code this}.
     */
    private Sequence<T> entries;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.entries = new Sequence1L<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Queue3() {
        this.createNewRep();
    }

    /*
     * Standard methods removed to reduce clutter...
     */

    /*
     * Kernel methods ---------------------------------------------------------
     */

    /*
     * My interpretation of this assignment is that we are using sequence kernel
     * methods and the sequence1l implementation to create a queue that, under
     * the hood, works like a sequence. In this queue, you can still add and get
     * variables from the beginning and end of the queue, respectively. However,
     * the benefit here is that in this type of queue, you could remove various
     * values from anywhere in the queue or add them anywhere in the queue.
     */

    @Override
    public final void enqueue(T x) {
        assert x != null : "Violation of: x is not null";

        //Add the x value as the first value in the sequence
        this.add(0, x);

        return null;
    }

    @Override
    public final T dequeue() {
        assert this.length() > 0 : "Violation of: this /= <>";

        //Remove the last variable
        this.remove(this.length());

        // This line added just to make the component compilable.
        return null;
    }

    @Override
    public final int length() {

        int length = this.length();

        // This line added just to make the component compilable.
        return 0;
    }

    /*
     * Note: I am not totally sure that this is where this method was supposed
     * to go but that assignment says to layer it on the kernel methods and this
     * is my interpretation of that instruction.
     */

    /**
     * Reports the front of {@code this}.
     *
     * @return the front entry of {@code this}
     * @aliases reference returned by {@code front}
     * @requires this /= <>
     * @ensures <front> is prefix of this
     */
    @Override
    public T front() {
        assert this.length() > 0 : "Violation of: this /= <>";

        T entry = this.entry(0);

        return entry;

    }

    /*
     * Iterator removed to reduce clutter...
     */

}
