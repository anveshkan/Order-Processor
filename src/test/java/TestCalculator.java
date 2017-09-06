import order.Item;
import order.Order;
import order.OrderLine;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestCalculator {

    @Test
    public void testTrimmingFractionalDigits() {
        final double actual = Calculator.trimFractionalDigits(1234.5689, 2);
        final double expected = 1234.56;
        final double delta = 0.01;
        Assert.assertEquals(expected, actual, delta);
    }

    @Test
    public void testCalculateOrderLinePriceInclusiveOfTax1() throws Exception {
        final Item item = new Item("book", 12.49f);
        final OrderLine orderLine = new OrderLine(item, 1);
        final double actualPriceWithTax = Calculator.calculateOrderLinePrice(orderLine, true);
        final double afterTrimming = Calculator.trimFractionalDigits(actualPriceWithTax);
        Assert.assertEquals(13.74, afterTrimming, 0);
    }

    @Test
    public void testCalculateOrderLinePriceInclusiveOfTax2() throws Exception {
        final Item item = new Item("book", 12.49f);
        final OrderLine orderLine = new OrderLine(item, 2);
        final double actualPriceWithTax = Calculator.calculateOrderLinePrice(orderLine, true);
        final double afterTrimming = Calculator.trimFractionalDigits(actualPriceWithTax);
        Assert.assertEquals(27.48, afterTrimming, 0);
    }

    @Test
    public void testCalculateOrderLinePriceInclusiveOfTax3() throws Exception {
        final Item item = new Item("book", 12.49f);
        final OrderLine orderLine = new OrderLine(item, 10);
        final double actualPriceWithTax = Calculator.calculateOrderLinePrice(orderLine, true);
        final double afterTrimming = Calculator.trimFractionalDigits(actualPriceWithTax);
        Assert.assertEquals(137.4, afterTrimming, 0);
    }

    @Test
    public void testCalculateOrderLinePriceExclusiveOfTax1() throws Exception {
        final Item item = new Item("book", 12.49f);
        final OrderLine orderLine = new OrderLine(item, 1);
        final double actualPriceWithTax = Calculator.calculateOrderLinePrice(orderLine, false);
        final double afterTrimming = Calculator.trimFractionalDigits(actualPriceWithTax);
        Assert.assertEquals(12.49, afterTrimming, 0);
    }

    @Test
    public void testCalculateOrderLinePriceExclusiveOfTax2() throws Exception {
        final Item item = new Item("book", 12.49f);
        final OrderLine orderLine = new OrderLine(item, 2);
        final double actualPriceWithTax = Calculator.calculateOrderLinePrice(orderLine, false);
        final double afterTrimming = Calculator.trimFractionalDigits(actualPriceWithTax);
        Assert.assertEquals(24.98, afterTrimming, 0);
    }

    @Test
    public void testCalculateOrderPriceAndTax1() throws Exception {
        final Order c1 = new Order("Order 1");
        c1.add(new OrderLine(new Item("book", 12.49f), 1));
        c1.add(new OrderLine(new Item("music CD", 14.99f), 1));
        c1.add(new OrderLine(new Item("chocolate bar", 0.85f), 1));
        final TwoTuple<Double, Double> orderPriceAndTax = Calculator.calculateOrderPriceAndTax(c1);
        final Double orderPrice = orderPriceAndTax.getFirst();
        final Double orderTax = orderPriceAndTax.getSecond();
        Assert.assertEquals(2.84, orderTax, 0.009);
        Assert.assertEquals(28.33, orderPrice, 0.009);
    }

    @Test
    public void testCalculateOrderPriceAndTaxOnImportedItems() throws Exception {
        final Order c1 = new Order("Order 2");
        c1.add(new OrderLine(new Item("imported box of chocolate", 10, true), 1));
        c1.add(new OrderLine(new Item("imported bottle of perfume", 47.50f, true), 1));
        final TwoTuple<Double, Double> orderPriceAndTax = Calculator.calculateOrderPriceAndTax(c1);
        final Double orderPrice = orderPriceAndTax.getFirst();
        final Double orderTax = orderPriceAndTax.getSecond();
        Assert.assertEquals(8.63, orderTax, 0.009);
        Assert.assertEquals(57.5, orderPrice, 0.009);
    }


    @Test
    public void testCalculateGrandTotal() throws Exception {

        final Map<String, Order> orderMap = buildSampleOrderMap();

        final double grandTotal = Calculator.calculateGrandTotal(orderMap);
        Assert.assertEquals(153.81, grandTotal, 0.009);
    }

    public static Map<String, Order> buildSampleOrderMap() throws Exception {
        final Map<String, Order> orderMap = new LinkedHashMap<String, Order>();
        final Order c1 = new Order("Order 1");

        c1.add(new OrderLine(new Item("book", 12.49f), 1));
        c1.add(new OrderLine(new Item("music CD", 14.99f), 1));
        c1.add(new OrderLine(new Item("chocolate bar", 0.85f), 1));
        orderMap.put(c1.getName(), c1);


        final Order c2 = new Order("Order 2");
        c2.add(new OrderLine(new Item("imported box of chocolate", 10, true), 1));
        c2.add(new OrderLine(new Item("imported bottle of perfume", 47.50f, true), 1));

        orderMap.put(c2.getName(), c2);


        final Order c3 = new Order("Order 3");
        c3.add(new OrderLine(new Item("Imported bottle of perfume", 27.99f, true), 1));
        c3.add(new OrderLine(new Item("bottle of perfume", 18.99f), 1));
        c3.add(new OrderLine(new Item("packet of headache pills", 9.75f), 1));
        c3.add(new OrderLine(new Item("Chocolates", "box of imported chocolates", 11.25f, true), 1));

        orderMap.put(c3.getName(), c3);
        return orderMap;
    }


}
