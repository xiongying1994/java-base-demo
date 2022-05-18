package Design_mode.b_simplefactory;

/**
 * 演示简单工厂
 * 符合 依赖倒置原则 ，使用抽象类而不是具体来表示一个类型，依赖于抽象
 * 不同类型但同属一个大类的各种商品，通过继承同一个抽象商品接口，可以在同一个工厂类中生产
 * 工厂类中的生产方法，只需要将返回值设为 抽象商品 即可返回各种类型的商品了
 * @author xiongying
 */

public class SimpleFactory {
    public static void main(String args[]) throws Exception {
        Factory factory = new Factory();
        factory.produce("PRO5").run();
        factory.produce("PRO6").run();
    }
}

/**
 * 抽象产品
 */
interface MeizuPhone {
    void run();
}

/**
 * 具体产品X2
 */
class PRO5 implements MeizuPhone {
    @Override
    public void run() {
        System.out.println("我是一台PRO5");
    }
}

class PRO6 implements MeizuPhone {
    @Override
    public void run() {
        System.out.println("我是一台PRO6");
    }
}

/**
 * 工厂
 */
class Factory {
    MeizuPhone produce(String product) throws Exception {
        if (product.equals("PRO5")) {
            return new PRO5();
        } else if (product.equals("PRO6")) {
            return new PRO6();
        }

        throw new Exception("No Such Class");
    }
}