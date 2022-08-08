package com.codepath.apps.restclienttemplate.models;

import android.text.format.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

//import java.io.Serializable;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
//import java.util.Locale;

@Parcel

public class Tweet {
    public Tweet(){}

//    sonje retire
//
//    private static final long serialVersionUID = 1875496677762975052L;
//    private static final String H = "h";
//    private static final String M = "m";
//    private static final String S = "s";
//    private static final String HOURS_AGO = " hours ago";
//    private static final String HOUR_AGO = " hour ago";
//    private static final String MINUTES_AGO = " minutes ago";
//    private static final String MINUTE_AGO = " minute ago";
//    private static final String SECONDS_AGO = " seconds ago";
//    private static final String SECOND_AGO = " second ago";


    public String body;
    public String createdAt;
    public long id;
    public User user;

//sonje retire
//    private static final String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
//    private static final SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
//
//    static{
//        sf.setLenient(true);
//    }


    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));

        }
        return tweets;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}












//    sonje retire

//    public String getBody() {
//        return body;
//    }
//
//    public long getid() {
//        return id;
//    }
//
//    public String getCreatedAt() {
//        long dateMillis = 0;
//        try {
//            dateMillis = sf.parse(createdAt).getTime();
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//        String str = (String) DateUtils.getRelativeTimeSpanString(dateMillis,
//                System.currentTimeMillis(),
//                1000L);
//        str = str.replace(SECOND_AGO, S);
//        str = str.replace(SECONDS_AGO, S);
//        str = str.replace(MINUTE_AGO, M);
//        str = str.replace(MINUTES_AGO, M);
//        str = str.replace(HOUR_AGO, H);
//        str = str.replace(HOURS_AGO, H);
//        return str;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    @Override
//    public String toString() {
//        return this.body + "-" + this.user.getScreenName();
//    }
//
//    public void setBody(String body) {
//        this.body = body;
//    }
//
//    public void setid(long id) {
//        this.id = id;
//    }
//
//    public void setCreatedAt(String createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
