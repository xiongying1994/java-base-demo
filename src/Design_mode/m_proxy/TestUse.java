package Design_mode.m_proxy;

/**
 * 代理模式
 * 一个用户不想或者不能够直接引用一个对象（或者设计者不希望用户直接访问该对象）
 * 而代理对象可以在客户端和目标对象之间起到中介的作用。而且这个代理对象中，我们可以做更多的操作。
 *
 * 代理类中，实例化被代理类对象，再在下面使用方法
 *
 * @author a
 */
public class TestUse {
    public static void main(String args[]) {
        AbstractObject obj = new ProxyObject();
        obj.method1();
        obj.method2();
        obj.method3();
    }
}