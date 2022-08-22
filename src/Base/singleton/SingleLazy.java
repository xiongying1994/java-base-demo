package Base.singleton;

/**
 * @Author: xiongying
 * @Date: 2021/3/16 下午10:49
 * 懒汉式
 * 静态内部类为最优解
 * 在调用 getInstance() 方法时，才会调用初始化方法
 */
public class SingleLazy {
    private static class SingleHolder {
        public static final SingleLazy singleLazy = new SingleLazy();
    }

    private SingleLazy() {
    }

    ;

    public static final SingleLazy getInstance() {
        return SingleHolder.singleLazy;
    }

    public void function() {
        // do something
        System.out.println("i am SingleLazy");
    }
}
