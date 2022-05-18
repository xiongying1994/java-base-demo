package Design_mode.k_facade;

/**
 * 计算优惠
 *
 * @author a
 */
public class Discount {
    int getDiscount(String discountCode) {
        return Math.abs(discountCode.hashCode()) % 3;
    }
}
