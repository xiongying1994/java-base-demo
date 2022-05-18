package Design_mode.d_abstractfactory;

/**
 * 抽象工厂模式
 */
public class AbstractFactory {
    public static void main(String args[]) {
        IFactory bigfactory = new BigFactory();
        IFactory smallfactory = new SmallFactory();
        bigfactory.producePhone().run();
        bigfactory.produceHeadset().play();
        smallfactory.producePhone().run();
        smallfactory.produceHeadset().play();

        /**
         * 其实就是下面这种，可以很明显的看出，抽象工厂 只是在 产品族上要比  工厂方法 多
         */
//        IFactory factory = new BigFactory();
//        factory.producePhone().run();
//        factory.produceHeadset().play();
//
//        factory = new SmallFactory();
//        factory.producePhone().run();
//        factory.produceHeadset().play();
    }
}

/**
 * 抽象产品*2
 */
interface Headset {
    void play();
}

/**
 * 抽象产品
 */
interface MeizuPhone {
    void run();
}

/**
 * 具体产品*2*2
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

class EP21 implements Headset {
    @Override
    public void play() {
        System.out.println("我是一副EP21");
    }
}

class EP30 implements Headset {
    @Override
    public void play() {
        System.out.println("我是一台EP30");
    }
}

/**
 * 抽象工厂
 */
interface IFactory {
    MeizuPhone producePhone();

    Headset produceHeadset();
}

/**
 * 具体工厂*2
 */
class BigFactory implements IFactory {
    @Override
    public MeizuPhone producePhone() {
        return new PRO5();
    }

    @Override
    public Headset produceHeadset() {
        return new EP30();
    }
}

class SmallFactory implements IFactory {
    @Override
    public MeizuPhone producePhone() {
        return new MX5();
    }

    @Override
    public Headset produceHeadset() {
        return new EP21();
    }
}