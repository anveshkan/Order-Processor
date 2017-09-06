import org.junit.Assert;
import org.junit.Test;

public class TestTwoTuple {

    @Test
    public void testOrderOfObjectsStoredInFirstAndSecond() throws Exception {
        final Boolean first = Boolean.TRUE;
        final Boolean second = Boolean.FALSE;
        final TwoTuple<Boolean, Boolean> twoTuple = new TwoTuple<Boolean, Boolean>(first, second);
        Assert.assertSame(Boolean.TRUE, twoTuple.getFirst());
        Assert.assertSame(Boolean.FALSE, twoTuple.getSecond());
    }
}
