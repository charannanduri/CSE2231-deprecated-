import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack1LTest extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {

        Stack<String> stack = new Stack1L<String>();

        // This line added just to make the program compilable.
        return stack;
    }

    @Override
    protected final Stack<String> constructorRef() {

        // TODO - fill in body

        // This line added just to make the program compilable.
        return null;
    }

}
