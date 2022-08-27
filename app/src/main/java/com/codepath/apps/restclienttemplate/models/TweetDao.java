package com.codepath.apps.restclienttemplate.models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.TweetWithUser;

import java.util.List;

@Dao
public interface TweetDao {
    @Query("SELECT Tweet.body As tweet_body,Tweet.createdAt As tweet_createdAt, Tweet.id As tweet_id,Tweet.likeBool" +
            " As tweet_likeBool,Tweet.retweet As tweet_retweet,User.*, Entities.*"+
            " FROM Tweet, Entities INNER JOIN  User ON Tweet.userId = User.id ORDER BY Tweet.createdAt DESC LIMIT 5 ")
    List<TweetWithUser> recentItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(Tweet... tweets);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(User...users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(Entities...entities);

}
