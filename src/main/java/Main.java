import order.Item;
import order.Order;
import order.OrderLine;

import java.util.LinkedHashMap;
import java.util.Map;

/* ****************************************************************************************
 
Please remove all bugs from the code below to get the following output:

<pre>

*******Order 1*******
1 book: 13.74
1 music CD: 16.49
1 chocolate bar: 0.94
Sales Tax: 2.84
Total: 28.33
*******Order 2*******
1 imported box of chocolate: 11.5
1 imported bottle of perfume: 54.62
Sales Tax: 8.62
Total: 57.5
*******Order 3*******
1 Imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 10.73
1 box of imported chocolates: 12.94
Sales Tax: 8.77
Total: 67.98
Sum of orders: 153.81
 
</pre>
 
******************************************************************************************** */

public class Main {

    public static void main(String[] args) throws Exception {

        final Printer printer = new Printer(System.out);

        final Map<String, Order> o = new LinkedHashMap<String, Order>();
        final boolean imported = true;

        final Order c1 = new Order("Order 1");
        int quantity = 1;

        c1.add(new OrderLine(new Item("book", 12.49f), quantity = 1));
        c1.add(new OrderLine(new Item("music CD", 14.99f), quantity = 1));
        c1.add(new OrderLine(new Item("chocolate bar", 0.85f), quantity = 1));
        o.put(c1.getName(), c1);


        final Order c2 = new Order("Order 2");
        c2.add(new OrderLine(new Item("imported box of chocolate", 10, imported), quantity = 1));
        c2.add(new OrderLine(new Item("imported bottle of perfume", 47.50f, imported), quantity = 1));

        o.put(c2.getName(), c2);


        final Order c3 = new Order("Order 3");
        c3.add(new OrderLine(new Item("Imported bottle of perfume", 27.99f, imported), quantity = 1));
        c3.add(new OrderLine(new Item("bottle of perfume", 18.99f), quantity = 1));
        c3.add(new OrderLine(new Item("packet of headache pills", 9.75f), quantity = 1));
        c3.add(new OrderLine(new Item("Chocolates", "box of imported chocolates", 11.25f, imported), quantity = 1));

        o.put(c3.getName(), c3);

        printer.print(o);

    }
}