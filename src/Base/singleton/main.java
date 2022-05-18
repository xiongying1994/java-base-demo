package Base.singleton;

/**
 * @Author: xiongying
 * @Date: 2021/3/16 下午11:08
 */
public class main {
    public static void main(String[] args) {
        SingleLazy singleLazy = SingleLazy.getInstance();
        singleLazy.function();

        SingleLazyDouble singleLazyDouble = SingleLazyDouble.getInstance();
        singleLazyDouble.function();

        SingleHungry singleHungry = SingleHungry.getInstance();
        singleHungry.function();

        SingleEnum singleEnum = SingleEnum.Instance;
        singleEnum.function();

    }
}
