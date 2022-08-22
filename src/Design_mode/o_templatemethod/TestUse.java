package Design_mode.o_templatemethod;

/**
 * 模板方法模式
 * <p>
 * 通过抽象的模板方法类，定义既定模板方法，所有普通方法按模板顺序调用
 * 再在子类中按需要重写普通方法
 * <p>
 * 核心：在抽象类中封装不可变的顺序部分与不可变的方法，在子类中通过重写扩展可变的部分
 */
public class TestUse {
    public static void main(String args[]) {
        Template template = new Template();
        template.dealData();
    }
}