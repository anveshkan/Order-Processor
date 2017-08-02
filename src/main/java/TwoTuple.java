/**
 * A tuple holding a list of two objects.
 *
 * @param <A> type of the first object
 * @param <B> type of the second object
 *
 * @author Anvesh
 */
public class TwoTuple<A, B> {

    private final A first;
    private final B second;

    /**
     * Initializes the object with the given first and second objects
     *
     * @param first  first object of the tuple
     * @param second second object of the tuple
     */
    public TwoTuple(final A first, final B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

}
