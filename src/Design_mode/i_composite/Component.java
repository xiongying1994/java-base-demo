package Design_mode.i_composite;

import java.util.Iterator;
import java.util.List;

/**
 * 抽象组件
 *
 * @author a
 */
public interface Component {
    void addFile(Component file);

    Component addFolder(Component folder);

    void removeFile(Component file);

    void removeFolder(Component folder);

    List<Component> getFiles();

    List<Component> getFolders();

    List<Component> getAll();

    Iterator<Component> iterator();

    void display();
}