package Design_mode.k_facade;

/**
 * 获取商品价格
 *
 * @author a
 */
public class ProductPrice {
    /**
     * 模拟获取商品价格
     *
     * @param product
     * @return
     */
    int getPrice(String product) {
        return Math.abs(product.hashCode());
    }
}
