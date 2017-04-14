package demo.webmyne.com.retrofitexample.api.model.login;

/**
 * Created by vaibhavirana on 05-04-2017.
 */

public class LoginResponse extends BaseResponse {
    private User Data;

    public User getData() {
        return Data;
    }

    public void setData(User data) {
        Data = data;
    }
}
