package api;

public class PojoUserTime {
    private String name;
    private  String job;

    public PojoUserTime(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
