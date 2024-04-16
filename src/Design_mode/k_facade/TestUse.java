package Design_mode.k_facade;

/**
 * 外观模式
 * 外观模式提供了一个统一的接口，用来访问子系统中的一群接口
 * 简单来说，就是通过一个接口，来调用<黑盒>中的一大批逻辑，这些逻辑如何使用无需知道，客户只需要调用这一个接口就可以了
 * 实现了松耦合  -- 其实就很像jar包，或者说python的一些功能包，只需要引入，然后简单的方式使用即可，不需要关系功能具体的实现
 *
 * 但是违背了  开闭原则，对于外观内（客户端）的功能，无法做到 对扩展开放，对修改关闭；如需调整可能需要修改外观类或客户端的源代码
 *
 * @author a
 */
public class TestUse {
    public static void main(String args[]) {
        Object info = ProductSalesman.instance.buySomething("银河飞船", "地球", "K1234523");
        System.out.println(info);
    }
}
