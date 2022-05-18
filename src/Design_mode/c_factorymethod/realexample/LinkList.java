package Design_mode.c_factorymethod.realexample;

/**
 * 方便演示而实现的简陋的单向链表list
 * @author a
 * @param <T>
 */
public class LinkList<T> implements List<T> {
    /**
     * 存放的元素个数,会默认初始化为0
     */
    private int size;
    /**
     * 首节点，默认初始化为null
     */
    private Node<T> first;

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public boolean add(T t) {
        if (size == 0) {
            first = new Node<T>(t, null);
            size++;
            return true;
        }
        Node<T> node = first;
        while (node.next != null)
            node = node.next;
        node.next = new Node<T>(t, null);
        size++;
        return true;
    }

    /**
     * 链表节点
     * @param <T>
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 遍历器
     */
    private class MyIterator implements Iterator<T> {
        /**
         * 下一个节点
         */
        private Node<T> next;

        MyIterator() {
            next = first;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() {
            T data = next.data;
            next = next.next;
            return data;
        }
    }
}