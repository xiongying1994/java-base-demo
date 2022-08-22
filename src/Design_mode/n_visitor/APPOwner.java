package Design_mode.n_visitor;

/**
 * 具体访问者
 * <p>
 * 根据被访问者的实现类，依次重载方法，将传入方法的接收者作为重载方法的参数
 * 间接实现了根据 接收者 决定调用的方法，即实现双重分派
 */
public class APPOwner implements Visitor {
    @Override
    public void visit(UserVIP user) {
        String estimation = user.getEstimation();
        if (estimation.length() > 5) {
            System.out.println("记录一条有效反馈：" + estimation);
        }
    }

    @Override
    public void visit(UserOrdinary user) {
        String estimation = user.getEstimation();
        if (estimation.length() > 10) {
            System.out.println("记录一条有效反馈：" + estimation);
        }
    }

    /**
     * 这个是我新添的
     * 这样的话，只要是 User 接口的子类方法，都可以使用这个访问者类，来实现一项统一类型的功能
     * 访问者模式，就是用来添加一个统一的能力，并且这个能力还可以在这一个类中进行汇总，不会散到各个不同
     * 的、大量的实现类中，显得非常复杂，修改起来也非常麻烦，统一到这一个类中，还能使用抽象，更加的
     * 方便快捷，一目了然。
     *
     * @param user
     */
    @Override
    public void visit(User user) {
        System.out.println("记录：lala");
    }
}