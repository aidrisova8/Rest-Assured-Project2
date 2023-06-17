package api.pojo;

public class RegisterSuccess {

    private Integer id;
    private String token;

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public RegisterSuccess(Integer id, String token) {
        this.id = id;
        this.token = token;


    }
}
