package Design_mode.j_decorator;

/**
 * 装饰模式
 * 需要扩展被装饰者的功能；原本只存本地，如今需要存数据库，后来又需要存网络
 * 新增抽象的装饰类，继承原功能接口，并且在构造方法中，引入接口形参 -- 为了保留原被装饰者的功能！
 * 实现装饰类，添加新增的功能。
 * <p>
 * 关键在于将被装饰者作为构造方法的入参传了进去，并且在构造方法中将这个对象引入到装饰实现类中，那么就可以
 * 覆写接口方法，并在方法中引入被装饰者的方法，保留了原功能，还可以新增功能。
 * 后续可以持续的新增。
 *
 * @author a
 */
public class TestUse {
    public static void main(String args[]) {
        // 被装饰者
        final String data = "数据";
        IPersistentUtil iPersistentUtil = new PersistentUtil();
        iPersistentUtil.persistentMsg(data);

        System.out.println("下面装饰数据库持久化：");
        iPersistentUtil = new PersistentDbDecorator(iPersistentUtil);
        iPersistentUtil.persistentMsg(data);

        // 继续装饰会持续的新增功能，并且不会覆盖原有的功能
        System.out.println("下面继续装饰网络存储器持久化：");
        iPersistentUtil = new PersistentNetDecorator(iPersistentUtil);
        iPersistentUtil.persistentMsg(data);

        System.out.println("重新开始装饰网络存储器持久化：");
        iPersistentUtil = new PersistentUtil();
        iPersistentUtil = new PersistentNetDecorator(iPersistentUtil);
        iPersistentUtil.persistentMsg(data);
    }
}
