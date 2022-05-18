package Design_mode.g_adapter.List;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * 自定义迭代器
 * 目标就是Iterator，被适配者是Enumeration
 * 这里就是新建了一个Iterator对比工具类，来进行 Enumeration 到 Iterator 的转换
 */
public class IteratorAdapter implements Iterator<String> {
    Enumeration<String> myEnum;

    public IteratorAdapter(Enumeration<String> myEnum) {
        this.myEnum = myEnum;
    }

    @Override
    public boolean hasNext() {
        return myEnum.hasMoreElements();
    }

    @Override
    public String next() {
        return myEnum.nextElement();
    }
}