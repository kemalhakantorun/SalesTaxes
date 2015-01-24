package salesTaxes;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Hakan on 24.1.2015.
 */
public abstract class Goods {
    private String name;
    private double price;
    private int count;
    private boolean isImported;

    private final double TAXRATE = 10;
    private final double TAXRATE_IMPORTED = 5;

    public Goods(String name,double price , int count,boolean isImported) {
        this.isImported = isImported;
        this.count = count;
        this.price = price;
        this.name = name;
    }

    private Goods() {
    }

    public double getTaxRate()
    {
        return TAXRATE;
    }

    public double getTaxRate_Imported()
    {
        return TAXRATE_IMPORTED;
    }

    public double getTaxValue()
    {
        return getTaxValue(TAXRATE,TAXRATE_IMPORTED);
    }

    public double getTaxValue(double taxrate, double taxrate_imported)
    {
        double result = 0;

        Locale locale  = new Locale("en", "US");
        String pattern = "###.#";

        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);


        result = (double) count * price;
        //first calculate total price with count*price
        result *= taxrate/100.0;
        result=Double.parseDouble(decimalFormat.format(result));
        // calculation of normal tax part ( without import tax added )
        if ( isImported )
        {
            double importTax = 0;
            importTax = (double) count * price;
            importTax *= taxrate_imported/100.0;
            importTax=Double.parseDouble(decimalFormat.format(importTax));
            result += importTax;
            // calcuation of import tax part
        }

        return  result;
    }

    public double getTotalValue()
    {
        return ((double)count * price) + getTaxValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setImported(boolean isImported) {
        this.isImported = isImported;
    }

    @Override
    public String toString() {
        Locale locale  = new Locale("en", "US");
        String pattern = "###.#";

        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);

        return "" + count + " " +  name + ": " + decimalFormat.format(getTotalValue()) ;
    }
}
