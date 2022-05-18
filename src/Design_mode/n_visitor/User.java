package Design_mode.n_visitor;

/**
 * 抽象元素
 */
public interface User {
    void accept(Visitor visitor);
}
