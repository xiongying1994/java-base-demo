package Design_mode.x_chainofresponsibility;

/**
 * 处理者
 *
 * @author a
 */
public interface Handler {
    int handleRequest(int n);

    void setNextHandler(Handler next);
}
