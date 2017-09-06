package order;

/**
 * Represents an order line which contains the {@link Item} and the quantity.
 *
 * @author Anvesh
 */
public class OrderLine {

    /**
     * Quantity of the item
     */
    private final int quantity;
    /**
     * Item of the order line
     */
    private final Item item;

    /**
     * Initializes the object with the given item and quantity of the item
     *
     * @param item     Item of the order
     * @param quantity Quantity of the item
     * @throws Exception if the item is null
     */
    public OrderLine(final Item item, final int quantity) throws Exception {
        if (item == null) {
            System.err.println("ERROR - Item is NULL");
            throw new Exception("Item is NULL");
        }
        assert quantity > 0;
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
