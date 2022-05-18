package Design_mode.g_adapter.List;

import java.util.Iterator;

/**
 * 适配器
 * 继承 Iterable 接口，覆写 iterator() 方法自定义 迭代器
 *
 * 这是个对象适配器，适配的对象功能在 IteratorAdapter 自定义迭代器中
 * 这里继承的接口是为了自定义迭代器，实现 iterator 功能
 */
public class BookAdapter extends Book implements Iterable<String> {
    @Override
    public Iterator<String> iterator() {
        return new IteratorAdapter(getEnum());
    }
}
