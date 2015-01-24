package salesTaxes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Order;

/**
 * Created by Hakan on 24.1.2015.
 */
public class MainApp {
    private static final Logger logger = LogManager.getLogger(MainApp.class);

    public static void main(final String... args) {
        logger.trace("Application Started");
        OrderReceipt orderReceipt = OrderReceipt.getInstace();
        // we created our sigleton instance for showing orderreceipt
        InputReader inputReader =InputReader.getInstace();
        // we created our singleton instance that also reads input.txt file


        for(Goods order: inputReader.getOrders())
        {
            // taking each order from input.txt to orderreceipt
            orderReceipt.addOrder(order);
        }

        orderReceipt.printReceipt();

        logger.trace("Application Ended");
    }
}
