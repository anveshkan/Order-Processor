import order.Item;
import order.Order;
import order.OrderLine;

import java.io.PrintStream;
import java.util.Map;

/**
 * @author Anvesh
 */
public class Printer {

    public static final String FORMAT_SPECIFIER = "%d %s: %.2f";
    /**
     * PrintStream object to which this instance prints the output.
     */
    private final PrintStream printStream;

    /**
     * Initializes the object with given PrintStream which is
     * used for sending the output to.
     *
     * @param printStream to which the output is printed
     */
    public Printer(final PrintStream printStream) {
        this.printStream = printStream;
    }

    /**
     * Print the summary of each order and the grand total of all the orders
     * to the underlying print stream.
     *
     * @param orderMap orders whose price summary has to be printed
     * @see #printOrders(Map, PrintStream)
     */
    public void print(final Map<String, Order> orderMap) {
        printOrders(orderMap, this.printStream);
    }

    /**
     * Prints the summary of all the orders in the orderMap to the given printStream.
     * For each order, iterates on the order lines and printOrders the total price
     * which is the item's price * quantity * taxes.
     * <p>
     * For each order, print the total Sales Tax paid and Total price without taxes
     * for this order
     */
    public static void printOrders(final Map<String, Order> orderMap,
                                   final PrintStream printStream) {

        final double grandTotal =
                Calculator.trimFractionalDigits(Calculator.calculateGrandTotal(orderMap));

        printOrders(printStream, orderMap);
        printStream.println("Sum of orders: " + grandTotal);
    }

    /**
     * Print the summary of each order and the grand total of all the orders
     * to the underlying print stream.
     *
     * @param printStream object to which the output is printed on
     * @param orderMap    map of orders whose price summary has to be printed
     * @see #printOrder(PrintStream, Order)
     */
    public static void printOrders(final PrintStream printStream,
                                   final Map<String, Order> orderMap) {
        // Iterate through the orders
        for (Map.Entry<String, Order> entry : orderMap.entrySet()) {
            final Order order =
                    entry.getValue();
            printStream.println("*******" + entry.getKey() + "*******");
            printOrder(printStream, order);

        }
    }

    /**
     * Print the summary of the order and the grand total of all the orders
     * to the underlying print stream.
     * <p>
     * Iterating through the order, for each order line it prints the
     * quantity of the item , item name, and price of the order line
     * inclusive of tax. Then prints the total tax and total price of the order.
     * </p>
     *
     * @param printStream object to which the output is printed on
     * @param order       order whose price summary has to be printed
     * @see #printOrderLine(PrintStream, OrderLine)
     */
    public static void printOrder(final PrintStream printStream,
                                  final Order order) {
        // Iterate through the items in the order
        for (OrderLine orderLine : order) {
            printOrderLine(printStream, orderLine);
        }

        final TwoTuple<Double, Double> orderPriceAndTaxTuple =
                Calculator.calculateOrderPriceAndTax(order);
        final double totalOrderPrice =
                Calculator.trimFractionalDigits(orderPriceAndTaxTuple.getFirst());
        final double totalOrderTax =
                Calculator.trimFractionalDigits(orderPriceAndTaxTuple.getSecond());

        // Print out the total taxes
        printStream.println("Sales Tax: " + totalOrderTax);

        // Print out the total amount
        printStream.println("Total: " + totalOrderPrice);
    }

    /**
     * Prints the quantity of the item , item name, and price of the order line
     * inclusive of tax. Then prints the total tax and total price of the order.
     *
     * @param printStream object to which the output is printed on
     * @param orderLine object whose summary has to be printed
     */
    public static void printOrderLine(final PrintStream printStream, final OrderLine orderLine) {
        // Calculate the total price
        final Item item = orderLine.getItem();
        final double itemPriceAndTax = Calculator.trimFractionalDigits(
                        Calculator.calculateOrderLinePrice(orderLine, true));
        final String orderLineSummary =
                buildOrderLineSummary(orderLine, itemPriceAndTax);
        printStream.println(orderLineSummary);
    }

    /**
     * Generates a string which is a summary of the order line.
     * @param orderLine object whose summary has to be printed
     * @param price price of the order line
     * @return a String object which is a summary of the order line.
     */
    public static String buildOrderLineSummary(final OrderLine orderLine,
                                               final double price) {
        final Item item = orderLine.getItem();
        return String.format(FORMAT_SPECIFIER, orderLine.getQuantity(), item.getName(), price);
    }
}
