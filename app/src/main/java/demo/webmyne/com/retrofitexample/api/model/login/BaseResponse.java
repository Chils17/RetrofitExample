package demo.webmyne.com.retrofitexample.api.model.login;

/**
 * Created by vaibhavirana on 05-04-2017.
 */

public class BaseResponse {
    private int ResponseCode;
    private String ResponseMsg;
    private String ResponseMessage;

    public String getResponseMsg() {
        return ResponseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        ResponseMsg = responseMsg;
    }

    public int getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(int responseCode) {
        ResponseCode = responseCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }
}
