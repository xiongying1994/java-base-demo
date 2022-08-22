package Base.objectModify.entity;

public class Home {
    private String father;
    private String mother;
    private String sister;

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getSister() {
        return sister;
    }

    public void setSister(String sister) {
        this.sister = sister;
    }

    @Override
    public String toString() {
        return "Home{" +
                "father='" + father + '\'' +
                ", mother='" + mother + '\'' +
                ", sister='" + sister + '\'' +
                '}';
    }
}
