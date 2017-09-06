import order.Item;
import order.Order;
import order.OrderLine;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestPrinter {

    private static Map<String, Order> buildSampleOrderMap() throws Exception {
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
        c3.add(new OrderLine(new Item("box of imported chocolates", "box of imported chocolates", 11.25f, true), 1));

        orderMap.put(c3.getName(), c3);
        return orderMap;
    }

    @Test
    public void testOrderLineSummaryDescription() throws Exception {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(out);
        final Printer printer = new Printer(printStream);

        final Map<String, Order> map = buildSampleOrderMap();
        printer.print(map);
        final String lineSepearator = System.getProperty("line.separator");
        final StringBuilder builder = new StringBuilder();
        builder.append("*******Order 1*******" + lineSepearator);
        builder.append("1 book: 13.74" + lineSepearator);
        builder.append("1 music CD: 16.49" + lineSepearator);
        builder.append("1 chocolate bar: 0.94" + lineSepearator);
        builder.append("Sales Tax: 2.84" + lineSepearator);
        builder.append("Total: 28.33" + lineSepearator);
        builder.append("*******Order 2*******" + lineSepearator);
        builder.append("1 imported box of chocolate: 11.50" + lineSepearator);
        builder.append("1 imported bottle of perfume: 54.63" + lineSepearator);
        builder.append("Sales Tax: 8.63" + lineSepearator);
        builder.append("Total: 57.5" + lineSepearator);
        builder.append("*******Order 3*******" + lineSepearator);
        builder.append("1 Imported bottle of perfume: 32.19" + lineSepearator);
        builder.append("1 bottle of perfume: 20.89" + lineSepearator);
        builder.append("1 packet of headache pills: 10.73" + lineSepearator);
        builder.append("1 box of imported chocolates: 12.94" + lineSepearator);
        builder.append("Sales Tax: 8.77" + lineSepearator);
        builder.append("Total: 67.98" + lineSepearator);
        builder.append("Sum of orders: 153.81" + lineSepearator);

        final String actual = out.toString();
        final String expected = builder.toString();
        Assert.assertEquals(actual, expected);


    }
}
