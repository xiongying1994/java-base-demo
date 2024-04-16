package Design_mode.u_iterator;

/**
 * 集合接口
 *
 * @param <T>
 */
public interface MyList<T> {
    /**
     * 返回一个遍历器
     *
     * @return
     */
    MyIterator<T> iterator();

    /**
     * 添加元素到列表
     *
     * @param t
     * @return
     */
    boolean add(T t);

    /**
     * 得到元素
     *
     * @param index
     * @return
     */
    T get(int index);

    /**
     * 删除最后一个元素
     *
     * @return
     */
    T remove();

    /**
     * 删除指定元素
     *
     * @param element
     * @return
     */
    boolean remove(T element);

    /**
     * 删除指定位置元素
     *
     * @param index
     * @return
     */
    T remove(int index);

    /**
     * 修改指定位置值
     *
     * @param index
     * @param element
     * @return
     */
    boolean set(int index, T element);

    int size();
}
