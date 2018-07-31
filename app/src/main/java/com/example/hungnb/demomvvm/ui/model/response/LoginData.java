package com.example.hungnb.demomvvm.ui.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginData implements Parcelable {
    @Expose
    @SerializedName("LoginAttemptLeft")
    private int LoginAttemptLeft;
    @Expose
    @SerializedName("IsLoginSuccess")
    private boolean IsLoginSuccess;
    @Expose
    @SerializedName("LastLogin")
    private String LastLogin;
    @Expose
    @SerializedName("LastLoginSource")
    private String LastLoginSource;
    @Expose
    @SerializedName("PreferredLanguage")
    private String PreferredLanguage;
    @Expose
    @SerializedName("LastName")
    private String LastName;
    @Expose
    @SerializedName("FirstName")
    private String FirstName;
    @Expose
    @SerializedName("Salutation")
    private String Salutation;
    @Expose
    @SerializedName("SourceName")
    private String SourceName;
    @Expose
    @SerializedName("CustomerTypeID")
    private int CustomerTypeID;
    @Expose
    @SerializedName("PrimaryMobile")
    private String PrimaryMobile;
    @Expose
    @SerializedName("EmailID")
    private String EmailID;
    @Expose
    @SerializedName("RelationshipTypeID")
    private int RelationshipTypeID;
    @Expose
    @SerializedName("CustomerMDMGlobalID")
    private String CustomerMDMGlobalID;
    @Expose
    @SerializedName("isAccountActive")
    private boolean isAccountActive;
    @Expose
    @SerializedName("UserName")
    private String UserName;
    @Expose
    @SerializedName("CustomerID")
    private String CustomerID;

    public int getLoginAttemptLeft() {
        return LoginAttemptLeft;
    }

    public boolean isLoginSuccess() {
        return IsLoginSuccess;
    }

    public String getLastLogin() {
        return LastLogin;
    }

    public String getLastLoginSource() {
        return LastLoginSource;
    }

    public String getPreferredLanguage() {
        return PreferredLanguage;
    }

    public String getLastName() {
        return LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getSalutation() {
        return Salutation;
    }

    public String getSourceName() {
        return SourceName;
    }

    public int getCustomerTypeID() {
        return CustomerTypeID;
    }

    public String getPrimaryMobile() {
        return PrimaryMobile;
    }

    public String getEmailID() {
        return EmailID;
    }

    public int getRelationshipTypeID() {
        return RelationshipTypeID;
    }

    public String getCustomerMDMGlobalID() {
        return CustomerMDMGlobalID;
    }

    public boolean isAccountActive() {
        return isAccountActive;
    }

    public String getUserName() {
        return UserName;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.LoginAttemptLeft);
        dest.writeByte(this.IsLoginSuccess ? (byte) 1 : (byte) 0);
        dest.writeString(this.LastLogin);
        dest.writeString(this.LastLoginSource);
        dest.writeString(this.PreferredLanguage);
        dest.writeString(this.LastName);
        dest.writeString(this.FirstName);
        dest.writeString(this.Salutation);
        dest.writeString(this.SourceName);
        dest.writeInt(this.CustomerTypeID);
        dest.writeString(this.PrimaryMobile);
        dest.writeString(this.EmailID);
        dest.writeInt(this.RelationshipTypeID);
        dest.writeString(this.CustomerMDMGlobalID);
        dest.writeByte(this.isAccountActive ? (byte) 1 : (byte) 0);
        dest.writeString(this.UserName);
        dest.writeString(this.CustomerID);
    }

    public LoginData() {
    }

    protected LoginData(Parcel in) {
        this.LoginAttemptLeft = in.readInt();
        this.IsLoginSuccess = in.readByte() != 0;
        this.LastLogin = in.readString();
        this.LastLoginSource = in.readString();
        this.PreferredLanguage = in.readString();
        this.LastName = in.readString();
        this.FirstName = in.readString();
        this.Salutation = in.readString();
        this.SourceName = in.readString();
        this.CustomerTypeID = in.readInt();
        this.PrimaryMobile = in.readString();
        this.EmailID = in.readString();
        this.RelationshipTypeID = in.readInt();
        this.CustomerMDMGlobalID = in.readString();
        this.isAccountActive = in.readByte() != 0;
        this.UserName = in.readString();
        this.CustomerID = in.readString();
    }

    public static final Creator<LoginData> CREATOR = new Creator<LoginData>() {
        @Override
        public LoginData createFromParcel(Parcel source) {
            return new LoginData(source);
        }

        @Override
        public LoginData[] newArray(int size) {
            return new LoginData[size];
        }
    };
}
