package demo.webmyne.com.retrofitexample.api.model.job;

/**
 * Created by vaibhavirana on 06-04-2017.
 */

public class JobRequest {

    /**
     * LanguageType : E
     * LastJobID : 0
     * UserID : 10023
     */

    private String LanguageType;
    private int LastJobID;
    private int UserID;

    public String getLanguageType() {
        return LanguageType;
    }

    public void setLanguageType(String LanguageType) {
        this.LanguageType = LanguageType;
    }

    public int getLastJobID() {
        return LastJobID;
    }

    public void setLastJobID(int LastJobID) {
        this.LastJobID = LastJobID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }
}
