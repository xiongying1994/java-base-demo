package Design_mode.k_facade;

/**
 * 计算邮费
 *
 * @author a
 */
public class Postage {
	int getPostage(String addr){
		return Math.abs(addr.hashCode())%20+6;//模拟邮费计算
	}
}
