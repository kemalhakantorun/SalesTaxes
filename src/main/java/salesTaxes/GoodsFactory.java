package salesTaxes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Hakan on 24.1.2015.
 */
public class GoodsFactory {
    private static final Logger logger = LogManager.getLogger(GoodsFactory.class);
    private GoodsFactory() {
    }

    public static Goods createGoods(String type, String name, double price , int count, boolean isImported) {
        if ( type.equals(Book.type))
        {
            return new Book(name, price, count, isImported);
        }
        else if ( type.equals(Medicine.type)){
            return new Medicine(name, price, count, isImported);
        }
        else if ( type.equals(Food.type)){
            return new Food(name, price, count, isImported);
        }
        else if ( type.equals(OtherItem.type)){
            return new OtherItem(name, price, count, isImported);
        }
        logger.error("Type is not Defined.!!!");
        throw new RuntimeException("Not a listed type");
    }
}
