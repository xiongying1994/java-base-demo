package Design_mode.o_templatemethod;

/**
 * 具体模板
 *
 * 选择要重写的普通方法进行重写，定义自己的规则
 * 模板方法中会按既定模板顺序调用
 */
public class Template extends AbstractTemplate {
	@Override
	void getData() {
		data = "data";
	}
	@Override
	void calcData() {
		data = (String)data+data;
	}
}