package demo.webmyne.com.retrofitexample.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by chiragpatel on 14/03/18.
 */

public class TenderResponse {
    /**
     * message : sucess
     * count : 1
     * status : 1
     * data : [{"Type ID":1,"L1 Winner":"","Title":"Auction Id :87496,Auction Title :Demonstration e-Auction for Vendors for Training Purpose only,Start Date : 18/10/2017 15:00:00,End Date :30/06/2018 14:50:00"}]
     */

    private String message;
    private int count;
    private String status;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("Type ID")
        private int _$TypeID12; // FIXME check this code
        @SerializedName("L1 Winner")
        private String _$L1Winner193; // FIXME check this code
        private String Title;

        public int get_$TypeID12() {
            return _$TypeID12;
        }

        public void set_$TypeID12(int _$TypeID12) {
            this._$TypeID12 = _$TypeID12;
        }

        public String get_$L1Winner193() {
            return _$L1Winner193;
        }

        public void set_$L1Winner193(String _$L1Winner193) {
            this._$L1Winner193 = _$L1Winner193;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }
    }
}
