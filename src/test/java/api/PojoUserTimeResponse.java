package api;

public class PojoUserTimeResponse extends PojoUserTime{

    private String updatedAt;

    public PojoUserTimeResponse(String name, String job, String updatedAt) {
        super(name, job);
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
