package Design_mode.n_visitor;

/**
 * 抽象访问者
 */
public interface Visitor {
    void visit(UserVIP user);

    void visit(UserOrdinary user);

    void visit(User user);
}