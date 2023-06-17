package api;

public class PojoRegisterSuccess {

    private Integer id;
    private String token;

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public PojoRegisterSuccess(Integer id, String token) {
        this.id = id;
        this.token = token;


    }
}
