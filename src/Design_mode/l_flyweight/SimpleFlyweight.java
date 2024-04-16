package Design_mode.l_flyweight;

import java.util.HashMap;

/**
 * 享元模式
 * 实际上就是我们常见，且常用的池化技术，例如连接池，线程池，String缓存池等
 * 享元模式就是运行共享技术有效地支持大量细粒度对象的复用
 *
 * 但是享元模式会使得系统变得更加复杂，同时也会导致读取外部状态所消耗的时间过长
 *
 * 简单的享元模式
 *
 * @author a
 */
public class SimpleFlyweight {
    public static void main(String args[]) {
        FlyweightFactory factory = new FlyweightFactory();
        IFlyweight flyweight1, flyweight2, flyweight3, flyweight4;
        flyweight1 = factory.getFlyweight("value1");
        flyweight2 = factory.getFlyweight("value1");
        flyweight3 = factory.getFlyweight("value1");
        flyweight4 = factory.getFlyweight("value2");
        flyweight1.doSomething();
        flyweight2.doSomething();
        flyweight3.doSomething();
        flyweight4.doSomething();
        System.out.println(factory.size());
    }
}

/**
 * 享元接口
 */
interface IFlyweight {
    void doSomething();
}

/**
 * 具体享元
 */
class Flyweight implements IFlyweight {
    private String value;

    public Flyweight(String value) {
        this.value = value;
    }

    @Override
    public void doSomething() {
        System.out.println(value);
    }
}

/**
 * 享元工厂
 * 关键在于存入 HashMap这一步，实现 池 共享的概念
 */
class FlyweightFactory {
    HashMap<String, IFlyweight> flyweights = new HashMap<String, IFlyweight>();

    IFlyweight getFlyweight(String value) {
        IFlyweight flyweight = flyweights.get(value);
        if (flyweight == null) {
            flyweight = new Flyweight(value);
            flyweights.put(value, flyweight);
        }
        return flyweight;
    }

    public int size() {
        return flyweights.size();
    }
}
