package Base.singleton;

/**
 * @Author: xiongying
 * @Date: 2021/3/16 下午10:51
 * 饿汉式
 * 天生线程安全，因为在类加载时已经创建了单例，仅此一份
 */
public class SingleHungry {
    private SingleHungry(){};
    private static SingleHungry singleHungry = new SingleHungry();

    public static SingleHungry getInstance(){
        return singleHungry;
    }

    public void function(){
        // do something
        System.out.println("i am SingleHungry");
    }
}
