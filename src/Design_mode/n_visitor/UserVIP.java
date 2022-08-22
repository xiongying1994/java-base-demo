package Design_mode.n_visitor;

/**
 * VIP用户，具体元素
 */
public class UserVIP implements User {
    String estimation;

    public UserVIP(String estimation) {
        this.estimation = estimation;
    }

    /**
     * 这个就是重点，第一次分派是调用accept()方法时根据接收者的实际类型来调用的.
     * <p>
     * 第二次分派就是通过visitor.visit(this)，传入静态类型，然后再visit()方法中
     * 反过来调用this本身的方法，就传出去了接收者 ！
     *
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    String getEstimation() {
        return estimation;
    }
}
