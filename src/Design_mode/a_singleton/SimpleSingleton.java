package Design_mode.a_singleton;

/**
 * 简单的单例模式，不能用于多线程
 * 懒汉式
 *
 * @author Administrator
 */
public class SimpleSingleton {
    private static SimpleSingleton instance;

    private SimpleSingleton() {
    }

    public static SimpleSingleton getIntance() {
        if (instance == null) {
            instance = new SimpleSingleton();
        }
        return instance;
    }
}

/**
 * 简单的单例模式
 * 饿汉式
 */
class SingletonDemo {
    private SingletonDemo() {
    }

    private static final SingletonDemo instance = new SingletonDemo();

    public static SingletonDemo getInstance() {
        return instance;
    }
}

/**
 * 常用的线程安全的单例模式
 * 懒汉式--静态内部类
 */
class Singleton {
    private Singleton() {
    }

    private static class SingletonHolder {
        public static final Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
}

/**
 * 懒汉式
 * 双重校验
 */
class Singleton2{
    private Singleton2(){}
    private static volatile Singleton2 instance2 = null;
    public static Singleton2 getInstance() {
        if (instance2 == null) {
            synchronized(Singleton2.class) {
                if (instance2 == null) {
                    instance2 = new Singleton2();
                }
            }
        }
        return instance2;
    }
}
