package Design_mode.q_state;

/**
 * 状态模式
 *
 * 实现方式拆分到不同的状态类中，根据状态应用类中的状态判断方法，判断使用哪种具体状态类
 *
 * @author a
 */
public class TestUse {
    public static void main(String args[]) {
        String smallData = "小数据";
        String middleData = "介于小数据和大数据之间的数据";
        String bifgData = "这里就假定这是一个很大很大很大的数据";
        SaveDataController saveDataController = new SaveDataController();
        saveDataController.save(smallData);
        saveDataController.save(middleData);
        saveDataController.save(bifgData);
    }
}
