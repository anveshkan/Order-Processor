package order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Anvesh
 */
public class Order implements Iterable<OrderLine> {

    private String name;

    public Order(final String name) {
        this.name = name;
    }

    private List<OrderLine> orderLines;

    public void add(OrderLine o) throws Exception {
        if (orderLines == null) {
            orderLines = new ArrayList<OrderLine>();
        }
        if (o == null) {
            System.err.println("ERROR - Order is NULL");
            throw new IllegalArgumentException("Order is NULL");
        }
        orderLines.add(o);
    }

    public int size() {
        return orderLines.size();
    }

    public OrderLine get(int i) {
        return orderLines.get(i);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Iterator<OrderLine> iterator() {
        if (orderLines == null) {
            return (Iterator<OrderLine>) Collections.emptyList();
        } else {
            return orderLines.iterator();
        }
    }
}
