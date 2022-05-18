package Design_mode.k_facade;

/**
 * 外观模式
 *
 * @author a
 */
public class TestUse {
	public static void main(String args[]){
		Object info = ProductSalesman.instance.buySomething("银河飞船", "地球", "K1234523");
		System.out.println(info);
	}
}