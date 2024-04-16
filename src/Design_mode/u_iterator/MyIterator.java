package Design_mode.u_iterator;

/**
 * 只是需要遍历一堆数据，那么只需要2个方法就可以了
 * @param <T>
 */
public interface MyIterator<T> {
    /**
     * 是否还有下一个元素
     * @return
     */
    boolean hasNext();

    /**
     * 得到下一个元素
     * @return
     */
    T next();

    T remove();
}
