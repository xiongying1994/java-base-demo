package Design_mode.h_bridge.save;

/**
 * 细化抽象
 *
 * @author a
 */
public class NetSave extends AbstractSave {
    public NetSave(ISaveData saveData) {
        super(saveData);
    }

    @Override
    public void save(Object data) {
        System.out.print("网络存储：");
        saveData.save(data);
    }
}