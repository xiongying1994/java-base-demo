package Base.concurrent.automic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    static AtomicInteger ai = new AtomicInteger();

    public static void main(String[] args) {
    	//先输入自己，再自增
        System.out.println(ai.getAndIncrement());
        //输出自己
        System.out.println(ai.get());
        //先自增，再输出自己
        System.out.println(ai.incrementAndGet());
    }
}