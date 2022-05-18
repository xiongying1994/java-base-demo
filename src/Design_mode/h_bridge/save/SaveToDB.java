package Design_mode.h_bridge.save;

/**
 * 具体实现
 *
 * @author a
 */
public class SaveToDB implements ISaveData {
    @Override
    public void save(Object data) {
        System.out.println(data + " 存储到数据库");
    }
}
