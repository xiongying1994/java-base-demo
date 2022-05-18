package Design_mode.c_factorymethod;

/**
 * 工厂方法模式
 * 不仅 商品 进行抽象化，将 工厂 也抽象化
 * @author a
 */
public class FactoryMethod {
    public static void main(String args[]) {
        IFactory bigfactory = new SmallFactory();
        MeizuPhone meizuPhone = bigfactory.produce();
        meizuPhone.run();

        bigfactory = new BigFactory();
        meizuPhone = bigfactory.produce();
        meizuPhone.run();
    }
}

/**
 * 抽象产品
 */
interface MeizuPhone {
    void run();
}

/**
 * 具体产品*2
 */
class PRO5 implements MeizuPhone {
    @Override
    public void run() {
        System.out.println("我是一台PRO5");
    }
}

class MX5 implements MeizuPhone {
    @Override
    public void run() {
        System.out.println("我是一台MX5");
    }
}

interface IFactory {
    MeizuPhone produce();
}

/**
 * 工厂*2
 */
class BigFactory implements IFactory {
    @Override
    public MeizuPhone produce() {
        return new PRO5();
    }
}

class SmallFactory implements IFactory {
    @Override
    public MeizuPhone produce() {
        return new MX5();
    }
}