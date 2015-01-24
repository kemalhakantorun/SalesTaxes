package salesTaxes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Order;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Hakan on 24.1.2015.
 */
public class OrderReceipt {
    private ArrayList<Goods> orders;
    private static final Logger logger = LogManager.getLogger(OrderReceipt.class);
    private static final Object lock = new Object();
    private static OrderReceipt instace;



    private OrderReceipt() {
            logger.trace("Singleton OrderReceipt Created");
            orders = new ArrayList<Goods>();
    }

    public void addOrder(Goods order)
    {
        orders.add(order);
    }
    public void printReceipt()
    {
        double totalPrice=0;
        double totalTaxes=0;
        Locale locale  = new Locale("en", "US");
        String pattern = "##0.00";

        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);

        System.out.println("\n*****************************");
        for(Goods order : orders) {
            System.out.println(order.getCount()+" "+ order.getName() + ": "+ decimalFormat.format(order.getTotalValue()));
            totalTaxes+= order.getTaxValue();
            totalPrice+=order.getTotalValue();
            //calcuated total and tax values.
        }
        System.out.println("Sales Taxes: "+decimalFormat.format(totalTaxes));
        System.out.println("Total :"+decimalFormat.format(totalPrice));
        System.out.println("\n*****************************\n");
    }


    public static OrderReceipt getInstace() {
        if (instace == null) {
            synchronized (lock) {
                if (instace == null) {
                    instace = new OrderReceipt();
                }
            }
        }
        return instace;
    }
}

