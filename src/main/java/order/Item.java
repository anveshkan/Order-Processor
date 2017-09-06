package order;

/**
 * Represents an item, contains a name, a price and a description.
 *
 * @author Anvesh
 */
public class Item {

    /**
     * Name of the item
     */
    private final String name;
    /**
     * Description of the item
     */
    private String description;
    /**
     * Price of the item
     */
    private float price;
    /**
     * Boolean holding true if the item is imported otherwise false.
     */
    private boolean imported;

    /**
     * Initializes the item with given name and price.
     *
     * @param name  name of the item
     * @param price price of the item
     * @see #Item(String, float, boolean)
     */
    public Item(String name, float price) {
        this(name, price, false);
    }

    /**
     * Initializes the item with given name, price and imported.
     * Sets the description of the item to empty
     *
     * @param name     name of the item
     * @param price    price of the item
     * @param imported boolean indicating if the item is imported
     * @see #Item(String, float, boolean)
     */
    public Item(String name, float price, boolean imported) {
        this(name, "", price, imported);
    }

    public Item(String name, String description, float price, boolean imported) {
        super();
        this.name = name;
        this.description = description;
        this.price = price;
        this.imported = imported;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }
}
