package com.android.apimultiscreen.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {


    private int id;
    private String name;
    @SerializedName("username")
    private String userName;
    private String email;

    public User(int id, String name, String userName, String email) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
    }

    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        userName = in.readString();
        email = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(userName);
        dest.writeString(email);
    }
}
