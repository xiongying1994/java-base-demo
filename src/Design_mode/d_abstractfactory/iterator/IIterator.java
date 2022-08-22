package Design_mode.d_abstractfactory.iterator;

/**
 * 抽象产品
 *
 * @param <T>
 */
public interface IIterator<T> {
    boolean hasNext();

    Object next();
}