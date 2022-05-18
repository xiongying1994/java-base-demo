package Design_mode.f_prototype;

/**
 * 原型模式就是克隆对象，如果一个对象要经常使用，就使用原型模式去克隆对象
 * 说实话不知道有什么卵用
 * 涉及到了 浅拷贝与深拷贝 的概念，看项目需要选择简单的浅拷贝，还是深拷贝
 *
 * 常用的深拷贝方式：
 * 1、一个个对象属性赋值
 * 2、序列化，再反序列化
 * 3、转JSON，再转回来
 *
 * 使用 Object.clone 方法，需要继承Cloneable接口，这个方法默认是浅拷贝的
 * super.clone();
 * 或者你自己覆写，自定义克隆方法
 *
 *
 * Cloneable接口与序列化的Serializable接口很像，都属于是一个 标识 的接口，没有具体内容
 *
 */
public class APITestUse {
    public static void main(String args[]) throws CloneNotSupportedException {
        MyObject myObject = new MyObject();
        myObject.i = 500;
        MyObject myObjectClone = (MyObject) myObject.clone();
        System.out.println(myObjectClone.i);
    }
}

class MyObject implements Cloneable {
    int i;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}