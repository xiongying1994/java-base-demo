package Design_mode.r_observer;

/**
 * 观察者模式
 * 观察到 数据/状态 变化时，通知所有观察者；类似于监听器
 *
 * 核心：
 * 主题服务：可以添加/删除 观察者，提供推数据给观察者，以及更新数据到主题类的方法
 * 在数据变化（即更新数据）后，通知情况给相应的观察者
 *
 * 在推消息给观察者时，还可以提供部分推送，调用方法，按需取数等多种方式，就看实际情况了
 *
 * @author a
 */
public class TestUse {
    public static void main(String args[]) {
        //创建主题
        WeatherService service = WeatherService.instance;
        //添加观察者
        service.addClient(new ClientAndroidServer());
        service.addClient(new ClientIphoneServer());
        //更新主题
        service.updateWeather(new WeatherInfo(System.currentTimeMillis(), "多云"));
        service.updateWeather(new WeatherInfo(System.currentTimeMillis() + 1000 * 60 * 60 * 24, "多云转晴"));
        service.updateWeather(new WeatherInfo(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 2, "晴"));
    }
}
