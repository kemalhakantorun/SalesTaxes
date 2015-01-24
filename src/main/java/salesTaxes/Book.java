package salesTaxes;

/**
 * Created by Hakan on 24.1.2015.
 */
public class Book extends Goods {
    public static final String type = "Book";
    private final double TAXRATE = 0;

    public Book(String name, double price, int count, boolean isImported) {
        super(name, price, count, isImported);
    }
    @Override
    public double getTaxValue() {
        return super.getTaxValue(TAXRATE,super.getTaxRate_Imported());
    }

    public static enum Types {
        Book("book"),
        Books("books"),
        Novel("novel");

        private final String text;

        private Types(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
