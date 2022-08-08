package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.io.Serializable;

@Parcel

public class User {
    //    private static final long serialVersionUID = -423074706015661304L;
    public String name;
    public long id;
    public String screenName;
    public String profileImageUrl;

    public User(){}

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.name = jsonObject.getString("name");
        user.screenName = jsonObject.getString("screen_name");
        user.profileImageUrl = jsonObject.getString("profile_image_url_https");

        return user;
    }


//    sonje retire
//User.fromJson(...)
//    public static User fromJson(JSONObject jsonObject) {
//        User user = new User();
//        //extract values from json to populate the member variables
//        try{
//            user.name = jsonObject.getString("name");
//            user.id = jsonObject.getLong("id");
//            user.screenName = jsonObject.getString("screen_name");
//            user.profileImageUrl = jsonObject.getString("profile_image_url");
//        }catch(JSONException e){
//            e.printStackTrace();
//            return null;
//        }
//        return user;
//    }
//
////sonje retire
//
//
    public String getName() {
        return name;
    }

    public long getid() {
        return id;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setid(long id) {
        this.id = id;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

}



