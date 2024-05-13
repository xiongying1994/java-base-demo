package Base.concurrent.conc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Map
 * (01)ConcurrentHashMap是线程安全的哈希表(相当于线程安全的HashMap)；
 *     它继承于AbstractMap类，并且实现接口。ConcurrentHashMap是通过“锁分段”来实现的，它支持并发。
 * (02)ConcurrentSkipListMap是线程安全的有序的哈希表(相当于线程安全的TreeMap);
 *     它继承于AbstractMap类，并且实现ConcurrentNavigableMap接口。ConcurrentSkipListMap是通过“跳表”来实现的，它支持并发。
 *
 * 并发情况测试
 *
 * @author xiongying
 */
public class MapTestConcurrent {

    private static Map<String, Object> map1 = new HashMap<String, Object>();
    private static Map<String, Object> map2 = new Hashtable<String, Object>();
    private static Map<String, Object> map3 = new ConcurrentHashMap<String, Object>();
    private static Map<String, Object> map4 = Collections.synchronizedMap(new HashMap<String, Object>());
    private static Map<String, Object> map5 = new ConcurrentSkipListMap<String, Object>();;

    private static Map<String, Object> map = map3;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                if (map.size() > 0) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        System.out.println(String.format("%s: %s", entry.getKey(), entry.getValue()));
                    }
                    map.clear();
                }
                try {
                    Thread.sleep((new Random().nextInt(10) + 1) * 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {

            for (int i = 1; i <= 100; i++) {
                map.put("key" + i, "value" + i);
                try {
                    Thread.sleep((new Random().nextInt(10) + 1) * 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
