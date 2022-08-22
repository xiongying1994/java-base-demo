package Design_mode.h_bridge.save;

/**
 * 抽象
 *
 * @author a
 */
public abstract class AbstractSave {
    ISaveData saveData;

    public AbstractSave(ISaveData saveData) {
        this.saveData = saveData;
    }

    public abstract void save(Object data);
}
