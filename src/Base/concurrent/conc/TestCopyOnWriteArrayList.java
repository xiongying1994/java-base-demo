package Base.concurrent.conc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * List和Set
 * JUC集合包中的List和Set实现类包括:
 * (01) CopyOnWriteArrayList:相当于线程安全的ArrayList，实现了List接口。CopyOnWriteArrayList支持高并发的。
 * (02) CopyOnWriteArraySet:相当于线程安全的HashSet，它继承于AbstractSet类。CopyOnWriteArraySet内部包含一个CopyOnWriteArrayList对象，它是通过CopyOnWriteArrayList实现的。
 * (03) ConcurrentSkipListSet:是线程安全的有序的集合(相当于线程安全的TreeSet)；它继承于AbstractSet，并实现了NavigableSet接口。ConcurrentSkipListSet是通过ConcurrentSkipListMap实现的，它也支持并发。
 *
 * @author xiongying
 */
public class TestCopyOnWriteArrayList {

    private void test() {
        // 这种情况下就会抛出异常
//        List<Integer> copyList = Arrays.asList(new Integer[]{1, 2});

        // 1、初始化CopyOnWriteArrayList
        List<Integer> tempList = Arrays.asList(new Integer[]{1, 2});
        CopyOnWriteArrayList<Integer> copyList = new CopyOnWriteArrayList<>(tempList);

        // 2、模拟多线程对list进行读和写
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 1，2
        executorService.execute(new ReadThread(copyList));
        // 写3个 9  进去
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new WriteThread(copyList));
        // 1，2，9，9，9
        executorService.execute(new ReadThread(copyList));
        // 写个 9 进去
        executorService.execute(new WriteThread(copyList));
        // 1，2，9，9，9，9
        executorService.execute(new ReadThread(copyList));
        // 写个 9 进去
        executorService.execute(new WriteThread(copyList));

        System.out.println("copyList size:" + copyList.size());

        executorService.shutdown();
    }

    public static void main(String[] args) {
        new TestCopyOnWriteArrayList().test();
    }
}

class WriteThread implements Runnable {
    private List<Integer> list;

    public WriteThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        this.list.add(9);
    }
}

class ReadThread implements Runnable {
    private List<Integer> list;

    public ReadThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (Integer ele : list) {
            System.out.println("ReadThread:" + ele);
        }
    }
}
