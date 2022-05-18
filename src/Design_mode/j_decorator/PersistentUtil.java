package Design_mode.j_decorator;

/**
 * 具体的被装饰者
 *
 * @author a
 */
public class PersistentUtil implements IPersistentUtil {
    @Override
    public void persistentMsg(String msg) {
        System.out.println(msg + " 存入文件");
    }
}