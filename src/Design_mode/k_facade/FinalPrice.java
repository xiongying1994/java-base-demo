package Design_mode.k_facade;

/**
 * 计费子系统
 *
 * @author a
 */
public class FinalPrice {
    ProductPrice productPrice;
    Postage postage;
    Discount discount;

    public FinalPrice() {
        productPrice = new ProductPrice();
        postage = new Postage();
        discount = new Discount();
    }

    int getFinalPrice(String product, String addr, String discountCode) {
        return productPrice.getPrice(product) + postage.getPostage(addr) - discount.getDiscount(discountCode);
    }
}
