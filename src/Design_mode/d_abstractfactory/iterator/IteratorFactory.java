package Design_mode.d_abstractfactory.iterator;
import java.util.Collection;
import java.util.Map;

/**
 * 具体工厂
 * @param <T>
 */
public class IteratorFactory<T> implements IIteratorFactory<T>{
	@Override
	public IteratorMap<T> iteratorMap(Map<T,Object> m) {
		return new IteratorMap<T>(m);
	}
	@Override
	public IteratorCollection<T> iteratorCollection(Collection<T> c) {
		return new IteratorCollection<T>(c);
	}
}