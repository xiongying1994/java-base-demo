package Design_mode.k_facade;

import java.util.Random;

/**
 * 库存子系统
 *
 * @author a
 */
public class Stock {
    /**
     * 模拟是否还有库存
     *
     * @param product
     * @return
     */
    boolean hasStock(String product) {
        return new Random().nextInt(Math.abs(product.hashCode())) > 0;
    }
}