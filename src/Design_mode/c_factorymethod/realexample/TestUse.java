package Design_mode.c_factorymethod.realexample;

/**
 * AarryList 与 LinkedList 就是工厂模式的一种体现
 * List接口是 工厂接口
 * AarryList与LinkedList 都继承了 List接口，List 继承了 Collection接口
 *
 * Java中的Collection接口的实现都能通过iterator()方法返回一个迭代器，
 *      而不同的实现的迭代器都在该实现中以内部类的方式对Iterator接口实现的，
 *      然后通过iterator()方法返回。那么，这个iterator()方法就是一种工厂方法。
 *
 * iterator接口是 商品接口
 *
 * 不同的工厂，不同的商品，可以使用同一个抽象的工厂接口和一个抽象的商品接口
 */
public class TestUse {
    public static void main(String args[]) {
        // 分别定义两种结构  -- 抽象工厂
        List<Integer> array = new ArrayList<>();
        List<Integer> link = new LinkList<>();
        // 添加数据
        for (int i = 1; i < 8; i++) {
            array.add(i);
            link.add(i);
        }
        // 获得迭代器  -- 抽象商品
        Iterator<Integer> ai = array.iterator();
        Iterator<Integer> li = link.iterator();
        // 遍历并输出
        while (ai.hasNext()) {
            System.out.print(ai.next());
        }

        System.out.println();
        while (li.hasNext()) {
            System.out.print(li.next());
        }
    }
}