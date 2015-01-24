package salesTaxes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Hakan on 24.1.2015.
 */
public class InputReader {
    private static final Logger logger = LogManager.getLogger(InputReader.class);
    private static final Object lock = new Object();
    private static InputReader instace;
    private ArrayList<Goods> orders;

    private InputReader() {
        logger.trace("Singleton InputReader Created");
        orders = new ArrayList<Goods>();
        int orderCounts = 0;
        logger.trace("Trying to open input.txt");
        InputStreamReader isReader = new InputStreamReader(
                MainApp.class.getResourceAsStream("..//input.txt")
        );
        BufferedReader br = new BufferedReader(isReader);


        String thisLine = null;
        int lineCount =0 ;
        try {
            while ((thisLine = br.readLine()) != null) {

                logger.entry("Line " + lineCount+1);

                        parseLine(thisLine);
                }

                logger.exit(thisLine);
                lineCount++;
            }catch (IOException e) {
            e.printStackTrace();
        }
        logger.trace("Total Orders: " + lineCount);
    }

    private void parseLine(String line)
    {

        int orderCount=0;
        String orderName="";
        String orderType="";
        double orderPrice=0;
        boolean orderImported=false;
        String[] lineParts = line.split(" ");
        Locale locale  = new Locale("en", "US");
        // using en us local for . instead of , in currency(double)
        NumberFormat format = NumberFormat.getInstance(locale);



        int count=0;
        logger.trace("Input Line : \"" + line+"\"");
        for ( String part:lineParts) {
            logger.trace((count+1) +" part of line : \""+ part+"\"");
            count++;
        } //read all lines of read input

        orderCount = Integer.parseInt(lineParts[0]);
        //first part is count number

        if (lineParts[1].equals("imported") ||lineParts[1].equals("Imported"))
        {
            orderImported = true;
        }
        // if there is imported or Imported after count we set it

        try {
            orderPrice = format.parse(lineParts[lineParts.length-1]).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // get price tag

        for ( int i = 1 ; i< lineParts.length -2 ;i++)
        {
            orderName+=lineParts[i]+" ";
        }
        // the other part which is not count , imported , at  and pricetag

        orderType = findTypeofOrder(orderName);
        // scan for the category name

        logger.trace("Number of orderCount : "+orderCount);
        logger.trace("Name of order: "+orderName);
        logger.trace("Type of order: " +orderType);
        logger.trace("Price of Order: "+orderPrice);
        logger.trace("Is it Imported: "+((orderImported)?"yes":"no"));
        orders.add(GoodsFactory.createGoods(orderType, orderName, orderPrice, orderCount, orderImported));
    }

    private String findTypeofOrder(String orderName)
    {
    // in here we will compare our ordername in our enum values
        // so we can say which product in which category

        for (String partNameofOrder : orderName.split(" "))
        {
            for (Food.Types typeName : Food.Types.values()) {
                if (typeName.toString().equals(partNameofOrder)) {
                    return Food.type;
                }
            }

            for (Medicine.Types typeName : Medicine.Types.values()) {
                if (typeName.toString().equals(partNameofOrder)) {
                    return Medicine.type;
                }
            }

            for (Book.Types typeName : Book.Types.values()) {
                if (typeName.toString().equals(partNameofOrder)) {
                    return Book.type;
                }
            }
        }
        return OtherItem.type;
    }

    public static InputReader getInstace() {
        if (instace == null) {
            synchronized (lock) {
                if (instace == null) {
                    instace = new InputReader();
                }
            }
        }
        return instace;
    }

    public ArrayList<Goods> getOrders() {
        return orders;
    }


}
