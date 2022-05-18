package Base.list;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * List的复制
 * 1、addAll，用List构造函数，循环取值再塞入等方式，均是将当前的List中存储的引用直接复制过去，
 * 那么对于复制后的List的修改，依然会通过引用去修改具体的值，
 * 会影响所有使用此引用的对象的值。
 * 被称作 浅拷贝
 * 2、List的深拷贝，就是不影响原值，复制出新的集合
 */
public class ListCopyList {
    public static void main(String[] args) {
        List<Map> list1;

        list1 = relise();
        List<Map> list2 = new ArrayList<>();
        list2.addAll(list1);
        list2.get(0).put("1", "2323");
        System.out.println("list2的测试：" + list1);


        list1 = relise();
        List<Map> list3 = new ArrayList<>(list1);
        list3.get(0).put("1", "4545");
        System.out.println("list3的测试：" + list1);

        list1 = relise();
        List<Map> list4 = new ArrayList<>();
        for (Map a : list1) {
            list4.add(a);
        }
        list4.get(0).put("1", "4545");
        System.out.println("list4的测试：" + list1);

        list1 = relise();
        List<Map> list5 = deepCopy(list1);
        list5.get(0).put("1", "4545");
        System.out.println("list5的测试：" + list1);

        List<ListCopyDemo> list7 = new ArrayList<>();
        ListCopyDemo listCopyDemo = new ListCopyDemo();
        list7.add(listCopyDemo);
        List<ListCopyDemo> list6 = new ArrayList<>();

        for (int i = 0; i < list7.size(); i += 1) {
            list6.add((ListCopyDemo) list7.get(i).clone());
        }

        list6.get(0).setName(new String[]{"s"});
        System.out.println("list7的测试：" + list7);
        System.out.println("list6的测试：" + list6);

    }

    public static <E> List<E> deepCopy(List<E> src) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            @SuppressWarnings("unchecked")
            List<E> dest = (List<E>) in.readObject();
            return dest;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<E>();
        }
    }

    public static List<Map> relise() {
        Map map = new HashMap();
        map.put("1", "1");
        map.put("2", "2");
        List<Map> list1 = new ArrayList<>();
        list1.add(map);
        System.out.println("list1: " + list1);
        return list1;
    }
}
