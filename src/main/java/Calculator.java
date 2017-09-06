import order.Item;
import order.Order;
import order.OrderLine;

import java.util.Map;

/**
 * @author Anvesh
 */
public class Calculator {

    /**
     * Returns the value affecting only the fractional part such that it
     * contains only 2 fractional digits.
     *
     * @param value value to be trimmed
     *
     * @return value containing only the first two fractional digits
     *
     * @see #trimFractionalDigits(double, int)
     */
    public static double trimFractionalDigits(final double value) {
        return trimFractionalDigits(value, 2);
    }

    /**
     * Returns the value affecting only the fractional part such that it
     * contains only specified number of fractional digits. That is, it removes
     * the fractional digits other than specified number of first fractional
     * digits.
     * <p>
     * <code>
     * <pre>
     *     final double value = 123.4567;
     *     final double trimmed = tCalculator.rimFractionalDigits(value,2);
     *     //Now variable trimmed contains the value '123.45'
     *     </pre>
     * </code>
     * <p>
     * </p>
     *
     * @param value  value to be trimmed
     * @param places number of first fractional digits to be retained
     *
     * @return value containing only the specified number of fractional digits
     */
    public static double trimFractionalDigits(final double value,
                                              final int places) {
        final double factor = Math.pow(10, places);
        return Math.round(value * factor) / factor;
    }

    /**
     * Calculates the grand total price of all the orders in the map.
     *
     * @param orderMap map of orders keyed by order name
     *
     * @return a sum of the price of each line orders in all the orders.
     */
    public static double calculateGrandTotal(final Map<String, Order> orderMap) {
        // Iterate through the orders
        Order order;
        TwoTuple<Double, Double> orderPriceAndTaxTuple;
        double totalOrderPrice;
        double grandTotal = 0;
        for (Map.Entry<String, Order> entry : orderMap.entrySet()) {
            order = entry.getValue();
            orderPriceAndTaxTuple = calculateOrderPriceAndTax(order);
            totalOrderPrice = trimFractionalDigits(orderPriceAndTaxTuple.getFirst());
            grandTotal += totalOrderPrice;
        }
        return grandTotal;
    }

    /**
     * Calculates the price with option of including the tax in the price.
     *
     * @param orderLine    order line containing the item and quantity
     * @param taxInclusive include the tax in the price if true else false.
     *
     * @return price with optionally included tax
     */
    public static double calculateOrderLinePrice(final OrderLine orderLine,
                                                 final boolean taxInclusive) {
        final Item item = orderLine.getItem();
        if (taxInclusive) {
            return
                    (item.getPrice() + calculateItemTax(item)) * orderLine.getQuantity();
        } else {
            return
                    item.getPrice() * orderLine.getQuantity();
        }
    }

    /**
     * Calculates the tax on the item for one unit. Imported items have
     * an extra of 5% tax in addition to the 10% tax.
     *
     * @param item - items whose tax has to be calculated.
     *
     * @return tax on the item
     */
    public static double calculateItemTax(final Item item) {
        final float itemsPrice = item.getPrice();
        double itemTax;
        if (item.isImported()) {
            // Extra 5% tax on imported items
            itemTax = trimFractionalDigits(itemsPrice * 0.15);
        } else {
            itemTax = trimFractionalDigits(itemsPrice * 0.10);
        }
        return itemTax;
    }

    /**
     * Returns an instance of {@link TwoTuple} with the first and second
     * containing the total order price and the total tax on the order,
     * respectively.
     *
     * @param order order whose tax and price has to be calculated.
     *
     * @return an instance of TwoTuple holding price and tax on the order.
     */
    public static TwoTuple<Double, Double> calculateOrderPriceAndTax(final Order order) {
        double totalOrderTax = 0;
        double totalOrderPrice = 0;

        // Iterate through the items in the order
        for (OrderLine orderLine : order) {

            // Calculate the taxes
            final Item item = orderLine.getItem();
            final double itemsPrice =
                    trimFractionalDigits(calculateOrderLinePrice(orderLine, false));
            final double itemTax =
                    trimFractionalDigits(calculateItemTax(item));

            totalOrderTax += itemTax;
            totalOrderPrice += itemsPrice;
        }

        return new TwoTuple<Double, Double>(totalOrderPrice, totalOrderTax);
    }

}
