package salesTaxes;

/**
 * Created by Hakan on 24.1.2015.
 */
public class OtherItem extends Goods {
    public static final String type = "OtherItem";
    private final double TAXRATE = 10;

    public OtherItem(String name, double price, int count, boolean isImported) {
        super(name, price, count, isImported);
    }
    @Override
    public double getTaxValue() {
        return super.getTaxValue(TAXRATE,super.getTaxRate_Imported());
    }



}
