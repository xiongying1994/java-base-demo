package Design_mode.x_chainofresponsibility;

/**
 * 责任链模式
 *
 * 在每个处理者中都需要提供设置下一节点的方法，每个处理者处理完成后，都会调用next的处理方法进行下一次处理
 * 有种递归的感觉（其实也没那么多相像），自己调用自己的方法，但是下一个自己是可以设置的，因此设置成同一处理者接口的不同的实现类
 * 这样子将很多个处理者拼接成一条责任链，调用第一个处理者的处理方法之后，按责任链依次往后进行执行
 */
public class TestUse {
    public static void main(String args[]) {
        Handler h1, h2, h3;
        h1 = new Handler1();
        h2 = new Handler2();
        h3 = new Handler3();
        h1.setNextHandler(h2);
        h2.setNextHandler(h3);
        System.out.println(h1.handleRequest(-1));
        System.out.println(h1.handleRequest(5));
        System.out.println(h1.handleRequest(9999));
    }
}
