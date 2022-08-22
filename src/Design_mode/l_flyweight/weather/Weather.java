package Design_mode.l_flyweight.weather;

/**
 * 具体享元
 * 这里我们使用HashMap来持有享元的引用，以为天气由具体天气情况和温度共同确定他们是否相同，
 * 我们需要使用两个值做key，但key只能是一个对象，所以最终我们选择这个对象来当key。
 * HashMap的Key是有限制的，必须正确提供hashCode()方法（HashMap以这个值为基础存取数据的）
 * 和equals()方法（HashMap通过key取值时判断Key是否相等会调用Key的这个方法）。
 * 下面的实现中就实现了这两个方法：
 *
 * @author a
 */
public class Weather implements IWeather {
    private String weather;
    private Integer temperature;

    public Weather(String weather, int temperature) {
        this.weather = weather;
        this.temperature = temperature;
    }

    @Override
    public void printWeather() {
        System.out.print("天气:" + weather);
        System.out.println("  温度:" + temperature);
    }

    @Override
    public boolean equals(Object obj) {
        Weather weather = (Weather) obj;
        return weather.weather.equals(this.weather) && weather.temperature == temperature;
    }

    @Override
    public int hashCode() {
        return (weather.hashCode() + temperature.hashCode()) / 2;
    }
}