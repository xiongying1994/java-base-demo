package Design_mode.i_composite;

import java.util.Iterator;

/**
 * 组合模式
 * 组合模式组合多个对象形成树形结构以表示“整体-部分”的结构层次。它定义了如何将容器对象和叶子对象进行递归组合，使得客户在使用的过程中无须进行区分
 * 从某一个目录开始，先输出目录名，然后如果有目录，就递归进入下一级目录，如果没有目录，就输出文件列表
 * <p>
 * 目录和文件（Composite和Leaf --> 组合对象和叶子对象）实现了相同的接口，所以操作起来很方便，包括迭代
 * <p>
 * 在使用组合模式中需要注意一点也是组合模式最关键的地方：
 * 叶子对象和组合对象实现相同的接口。这就是组合模式能够将叶子节点和对象节点进行一致处理的原因。
 *
 * @author a
 */
public class TestUse {
    public static void main(String args[]) {
        // 根目录
        Component root = new Folder("root");
        Component folder1 = new Folder("java");
        Component folder2 = new Folder("c++");
        Component folder3 = new Folder("c#");
        Component file1 = new File("info.txt");

        // 添加一级目录 root/java/c++/c#/info.txt
        root.addFolder(folder1).addFolder(folder2).addFolder(folder3).addFile(file1);

        // root/java/info.java
        folder1.addFile(new File("info.java"));
        Iterator<Component> iterator = root.iterator();
        while (iterator.hasNext()) {
            Component component = iterator.next();
            if (component instanceof Folder) {
                System.out.print("folder：");
            } else {
                System.out.print("file：");
            }

            component.display();
        }
    }
}