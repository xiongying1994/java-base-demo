package Base.singleton;

/**
 * @Author: xiongying
 * @Date: 2021/3/16 下午10:51
 * 双重校验锁
 */
public class SingleLazyDouble {
    private static volatile SingleLazyDouble instance = null;

    private SingleLazyDouble() {
    }

    ;

    public static final SingleLazyDouble getInstance() {
        if (instance == null) {
            synchronized (SingleLazyDouble.class) {
                if (instance == null) {
                    instance = new SingleLazyDouble();
                }
            }
        }
        return instance;
    }

    public void function() {
        // do something
        System.out.println("i am SingleLazyDouble");
    }
}
