package Design_mode.h_bridge.save;

/**
 * 细化抽象
 *
 * @author a
 */
public class LocalSave extends AbstractSave {
    public LocalSave(ISaveData saveData) {
        super(saveData);
    }

    @Override
    public void save(Object data) {
        System.out.print("本地存储：");
        saveData.save(data);
    }
}