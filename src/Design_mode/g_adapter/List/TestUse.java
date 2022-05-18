package Design_mode.g_adapter.List;

import java.util.Iterator;

/**
 * 一个用于存放书名的类，这个系统采用的Vector作为容器，而且Vector使用Enumeration来遍历容器。
 * 但现在，我们其他模块没沟通清楚以为那个类使用的LinkedList来实现，
 * 使用的时候用的Iterator，这时，我们就需要适配器。
 */
public class TestUse {
    public static void main(String args[]) {
        BookAdapter books = new BookAdapter();
        books.add("think in java");
        books.add("c++ primer");
        books.add("伊索寓言");
        Iterator<String> iterator = books.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}