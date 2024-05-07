package Design_mode.m_proxy;

/**
 * 代理模式
 * 一个用户不想或者不能够直接引用一个对象（或者设计者不希望用户直接访问该对象）
 * 而代理对象可以在客户端和目标对象之间起到中介的作用。而且这个代理对象中，我们可以做更多的操作。
 *
 * 代理模式通常会给每个方法都有对应的代理方法，一般不修改逻辑（有时也改，不拘泥）
 *
 * 与适配器模式：适配器模式会修改方法逻辑 -- 因为需要新增适配方法
 * 与外观模式：很相似，但是外观是提供统一的入口，而代理则是给每个方法都提供一个代理方法
 * <p>
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
