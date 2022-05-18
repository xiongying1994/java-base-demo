package Design_mode.l_flyweight.weather;

import java.util.HashMap;

/**
 * 享元工厂
 *
 * @author a
 */
public class WeatherFactory {
    private HashMap<IWeather, IWeather> weathers;

    public WeatherFactory() {
        weathers = new HashMap<IWeather, IWeather>();
    }

    public IWeather getFlyWeight(String weather, int temperature) {
        Weather objectWeather = new Weather(weather, temperature);
        IWeather flyweight = weathers.get(objectWeather);
        if (flyweight == null) {
            flyweight = objectWeather;
            weathers.put(objectWeather, flyweight);
        } else {
            // 方便gc回收
            objectWeather = null;
        }
        return flyweight;
    }

    public int getFlyweightSize() {
        return weathers.size();
    }
}