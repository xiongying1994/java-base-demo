package Design_mode.c_factorymethod.realexample;

/**
 * 方便演示而实现的简陋的数组list
 * @author a
 * @param <T>
 */
public class ArrayList<T> implements List<T> {
    /**
     * 存放的元素个数,会默认初始化为0
     */
    private int size;
    /**
     * 使用数组存放元素
     */
    private Object[] defaultList;
    /**
     * 默认长度
     */
    private static final int defaultLength = 10;

    /**
     * 默认构造函数
     */
    public ArrayList() {
        defaultList = new Object[defaultLength];
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    /**
     * 添加元素
     * @param t
     * @return
     */
    @Override
    public boolean add(T t) {
        if (size <= defaultLength) {
            defaultList[size++] = t;
            return true;
        }
        return false;
    }

    /**
     * 遍历器
     */
    private class MyIterator implements Iterator<T> {
        private int next;

        @Override
        public boolean hasNext() {
            return next < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            return (T) defaultList[next++];
        }
    }
}