package Design_mode.n_visitor;

/**
 * 普通用户，具体元素
 */
public class UserOrdinary implements User {
    String estimation;

    public UserOrdinary(String estimation) {
        this.estimation = estimation;
    }

    /**
     * 核心方法
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
