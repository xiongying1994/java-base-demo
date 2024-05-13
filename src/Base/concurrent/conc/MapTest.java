package Base.concurrent.conc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Map
 * (01)ConcurrentHashMap是线程安全的哈希表(相当于线程安全的HashMap)；
 *     它继承于AbstractMap类，并且实现接口。ConcurrentHashMap是通过“锁分段”来实现的，它支持并发。
 *
 * (02)ConcurrentSkipListMap是线程安全的有序的哈希表(相当于线程安全的TreeMap);
 *     它继承于AbstractMap类，并且实现ConcurrentNavigableMap接口。ConcurrentSkipListMap是通过“跳表”来实现的，它支持并发。
 *
 * 简单测试
 *
 * @author xiongying
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, Object> map1 = new HashMap<String, Object>();
        writeMap(map1);
        printMap(map1);

        System.out.println();

        Map<String, Object> map2 = Collections.synchronizedMap(new TreeMap<String, Object>());
        writeMap(map2);
        printMap(map2);

        System.out.println();

        Map<String, Object> map3 = new ConcurrentHashMap<String, Object>();
        writeMap(map3);
        printMap(map3);

        System.out.println();

        Map<String, Object> map4 = new ConcurrentSkipListMap<String, Object>();
        writeMap(map4);
        printMap(map4);

    }

    private static void writeMap(Map<String, Object> map) {
        for (int i = 0; i < 10; i++) {
            map.put("key" + i, "value" + i);
        }
    }

    private static void printMap(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }
}
