package demo.webmyne.com.retrofitexample.api.model.login;

/**
 * Created by vaibhavirana on 05-04-2017.
 */

public class LoginRequest {

    /**
     * Password : 123456
     * IsRegister : 0
     * Mobile : 9601158411
     * GCMToken :
     * DeviceId :
     * MobileOS : Android
     */

    private String Password;
    private int IsRegister;
    private String Mobile;
    private String GCMToken;
    private String DeviceId;
    private String MobileOS;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getIsRegister() {
        return IsRegister;
    }

    public void setIsRegister(int IsRegister) {
        this.IsRegister = IsRegister;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getGCMToken() {
        return GCMToken;
    }

    public void setGCMToken(String GCMToken) {
        this.GCMToken = GCMToken;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String DeviceId) {
        this.DeviceId = DeviceId;
    }

    public String getMobileOS() {
        return MobileOS;
    }

    public void setMobileOS(String MobileOS) {
        this.MobileOS = MobileOS;
    }
}
