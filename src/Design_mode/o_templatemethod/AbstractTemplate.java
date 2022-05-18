package Design_mode.o_templatemethod;

/**
 * 抽象模板
 */
public abstract class AbstractTemplate {
	Object data;

	/**
	 * 这个就是模板方法
	 * 其中已经设定好了执行的逻辑
	 */
	void dealData(){
		getData();
		calcData();
		printData();
	}

	/**
	 * 下面是普通方法，可能已经实现，也可能需要子类实现
	 */
	abstract void getData();
	abstract void calcData();
	void printData(){
		System.out.println(data);
	}
}