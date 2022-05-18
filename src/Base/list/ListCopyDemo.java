package Base.list;

import java.util.Arrays;

/**
 * list复制Demo类
 */
public class ListCopyDemo implements Cloneable {
    public String name[];

    public ListCopyDemo() {
        name = new String[2];
    }

    @Override
    public Object clone() {
        ListCopyDemo o = null;
        try {
            o = (ListCopyDemo) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ListCopyDemo{" +
                "name=" + Arrays.toString(name) +
                '}';
    }
}

