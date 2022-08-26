package com.codepath.apps.restclienttemplate.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


@Parcel
@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId"))
public class Tweet {

    @PrimaryKey
    @ColumnInfo
    public long id;

    @ColumnInfo

    public String body;


    @ColumnInfo
    public String createdAt;

    @ColumnInfo
    public boolean likeBool;
    @ColumnInfo
    public long userId;
    @Ignore
    public User user;
    @ColumnInfo
    public String  retweet;

    @ColumnInfo
    public String like;
    @Ignore
    public Entities entities;
    @Ignore
    public Extended extended;

    public Tweet(){}

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        User user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.like = jsonObject.getString("favorite_count");
        tweet.retweet = jsonObject.getString("retweet_count");
        tweet.user = user;
        tweet.userId = user.id;
        tweet.likeBool = jsonObject.getBoolean("favorited");
        tweet.entities = Entities.fromJson(jsonObject.getJSONObject("entities"));

        if(jsonObject.has("extended_entities")){
            tweet.extended = Extended.fromJson(jsonObject.getJSONObject("extended_entities"));
        }else{
            tweet.extended = new Extended();
            tweet.extended.media_Url = "";
        }

        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));

        }
        return tweets;
    }

    public static String getFormattedTime(String createdAt) {
        return TimeFormatter.getTimeDifference(createdAt);
    }
    public static String getFormattedTime1(String createdAt) {
        return TimeFormatter.getTimeDifference(createdAt);
    }

    public long getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUrl() {
        return  "https://twitter.com" +user.screenName+ "/status/"+id;
    }
}
