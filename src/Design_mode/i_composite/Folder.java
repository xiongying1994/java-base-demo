package Design_mode.i_composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Composite节点
 * 目录
 *
 * @author a
 */
public class Folder implements Component {
    private String name;
    private List<Component> files;
    private List<Component> folders;

    public Folder(String name) {
        this.name = name;
        files = new ArrayList<Component>();
        folders = new ArrayList<Component>();
    }

    @Override
    public void addFile(Component file) {
        files.add(file);
    }

    @Override
    public Component addFolder(Component folder) {
        folders.add(folder);
        return this;
    }

    @Override
    public void removeFile(Component file) {
        files.remove(file);
    }

    @Override
    public void removeFolder(Component folder) {
        folders.remove(folder);
    }

    @Override
    public List<Component> getFiles() {
        return files;
    }

    @Override
    public List<Component> getFolders() {
        return folders;
    }

    @Override
    public List<Component> getAll() {
        List<Component> all = new ArrayList<Component>(folders);
        all.addAll(files);
        return all;
    }

    @Override
    public Iterator<Component> iterator() {
        List<Component> all = new ArrayList<Component>();
        add(all, this);
        return all.iterator();
    }

    /**
     * 递归的思想
     * 递归 component 下的 Folders 目录
     *
     * @param all
     * @param component
     */
    private void add(List<Component> all, Component component) {
        if (component == null) {
            return;
        }
        // 添加目录名
        all.add(component);

        // 遍历当前 component下的所有 Folders               这个iterator()是List的迭代器，不是我们自定义的，不要想复杂了
        Iterator<Component> iterator = component.getFolders().iterator();
        while (iterator.hasNext()) {
            // 递归
            add(all, iterator.next());
        }
        // 添加所有文件
        all.addAll(component.getFiles());
    }

    @Override
    public void display() {
        System.out.println(name);
    }
}
