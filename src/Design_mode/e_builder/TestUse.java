package Design_mode.e_builder;

/**
 * 建造者模式
 * 一个建造者类，建造一个对象出来，这个对象的创作过程完全封装在建造者类当中，只产出对象
 *
 * @author a
 */
public class TestUse {
    public static void main(String args[]) {
        MyDate date = new MyDate();
        IDateBuilder builder;
        builder = new DateBuilder1(date).buildDate(2066, 3, 5);
        System.out.println(builder.date());
        builder = new DateBuilder2(date).buildDate(2066, 3, 5);
        System.out.println(builder.date());
    }
}