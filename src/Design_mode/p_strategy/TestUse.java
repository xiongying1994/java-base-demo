package Design_mode.p_strategy;

/**
 * 策略模式
 * 在策略应用类中，通过传入具体策略接口，进行初始化，并在类方法中，调用接口中的方法
 * 同时提供策略切换方法
 *
 * 具体策略类继承策略接口，实现接口方法
 * 使用时就可以根据传入的不同策略，加载相同的方法，但是在策略类中的不同实现
 *
 */
public class TestUse {
    public static void main(String args[]) {
        Object data = "数据";
        ISaveData saveData = new SaveToRedis();
        // 初始化策略调用
        SaveClient client = new SaveClient(saveData);
        // 调用策略方法
        client.save(data);
        // 重新设置策略
        client.setSaveData(new SaveToFile());
        client.save(data);
    }
}
