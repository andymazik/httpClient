package http.entity;

public class Company {
    public String name;
    public String catchPhrase;
    public String bs;

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", catchPhrase='" + catchPhrase + '\'' +
                ", bs='" + bs + '\'' +
                '}';
    }
}
