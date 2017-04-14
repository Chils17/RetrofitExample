package demo.webmyne.com.retrofitexample.api.model.login;

import java.io.Serializable;

/**
 * Created by vaibhavirana on 05-04-2017.
 */

public class User implements Serializable{
    private String UserId;
    private String DeviceId;
    private String Name;
    private String Email;
    private String MobileOS;
    private String SchemeName;
    private String ImageName;
    private String ImagePath;
    private String InsuredStatus;
    private String Mobile;
    private String Password;
    private String DOB;
    private String OTP;
    private String GCMToken;
    private String PolicyNumber;
    private int IsRegister;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobileOS() {
        return MobileOS;
    }

    public void setMobileOS(String mobileOS) {
        MobileOS = mobileOS;
    }

    public String getSchemeName() {
        return SchemeName;
    }

    public void setSchemeName(String schemeName) {
        SchemeName = schemeName;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getInsuredStatus() {
        return InsuredStatus;
    }

    public void setInsuredStatus(String insuredStatus) {
        InsuredStatus = insuredStatus;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public String getGCMToken() {
        return GCMToken;
    }

    public void setGCMToken(String GCMToken) {
        this.GCMToken = GCMToken;
    }

    public String getPolicyNumber() {
        return PolicyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        PolicyNumber = policyNumber;
    }

    public int getIsRegister() {
        return IsRegister;
    }

    public void setIsRegister(int isRegister) {
        IsRegister = isRegister;
    }
}
