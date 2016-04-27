package ve.com.bybeeconcept.rokk3rstest_audel.model;

import com.orm.SugarRecord;

/**
 * Created by audel on 2/25/16.
 */
public class ShoppingCartProd extends SugarRecord{

    long productId;
    long cartAmount;

    public ShoppingCartProd(long productId, long cartAmount) {
        this.productId = productId;
        this.cartAmount = cartAmount;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(long cartAmount) {
        this.cartAmount = cartAmount;
    }
}
