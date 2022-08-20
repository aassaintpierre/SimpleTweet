package com.codepath.apps.restclienttemplate.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


@Parcel
public class Tweet {
    public Tweet(){}

    public long id;

    public String body;
    public String createdAt;
    public boolean likeBool;

    public User user;
    public String like, retweet;

    public Entities entities;

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        tweet.like = jsonObject.getString("favorite_count");
        tweet.retweet = jsonObject.getString("retweet_count");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.likeBool = jsonObject.getBoolean("favorited");
        tweet.entities = Entities.fromJson(jsonObject.getJSONObject("entities"));
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

    public String getRetweet() {
        return retweet;
    }

    public String getLike() {
        return body;
    }

    public User getUser() {
        return user;
    }
}
