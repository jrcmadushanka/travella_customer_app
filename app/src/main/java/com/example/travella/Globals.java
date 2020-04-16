package com.example.travella;

import android.app.Application;

public class Globals extends Application {

    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
