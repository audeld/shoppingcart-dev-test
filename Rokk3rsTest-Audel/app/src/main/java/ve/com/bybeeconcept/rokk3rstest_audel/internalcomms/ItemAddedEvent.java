package ve.com.bybeeconcept.rokk3rstest_audel.internalcomms;

/**
 * Created by audel on 2/25/16.
 */
public class ItemAddedEvent {
    private long itemId;
    private double price;

    public ItemAddedEvent() {
    }

    public ItemAddedEvent(long itemId, double price) {
        this.itemId = itemId;
        this.price = price;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
