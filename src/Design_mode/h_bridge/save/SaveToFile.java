package Design_mode.h_bridge.save;

/**
 * 具体实现
 *
 * @author a
 */
public class SaveToFile implements ISaveData {
    @Override
    public void save(Object data) {
        System.out.println(data + " 存储到文件");
    }
}
