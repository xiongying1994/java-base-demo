package Base.objectModify.entity;

import java.util.List;

public class Student {
    int age;
    String name;
    List<Integer> grade;
    Home home;
    List<Home> homeList;
    Long height;

    public Student(int age, String name, List<Integer> grade) {
        this.age = age;
        this.name = name;
        this.grade = grade;
    }

    public Student() {
        //如果担心，对象使用默认的无参构造创建后，直接使用对象内参数调用方法，抛出 空指针异常
        //可以在无参构造中，将参数初始化，来防止这种情景
//        this.name = new String();
//        this.grade = new ArrayList<>();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getGrade() {
        return grade;
    }

    public void setGrade(List<Integer> grade) {
        this.grade = grade;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public List<Home> getHomeList() {
        return homeList;
    }

    public void setHomeList(List<Home> homeList) {
        this.homeList = homeList;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", home=" + home +
                ", homeList=" + homeList +
                ", height=" + height +
                '}';
    }
}
