package com.codepath.apps.restclienttemplate.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@Entity
public class Entities {
    @PrimaryKey
    @ColumnInfo
    public long id_entities;

    public String media_Url, type;

    public Entities(){}

    public static Entities fromJson(JSONObject jsonObject) throws JSONException {
        Entities entities = new Entities();
        // if cover media is available
        if(!jsonObject.has("media")){
            entities.media_Url = "";
            entities.type = "";
        }else if(jsonObject.has("media")){
            final JSONArray mediaArray = jsonObject.getJSONArray("media");
            entities.id_entities = mediaArray.getJSONObject(0).getLong("id");
            entities.media_Url = mediaArray.getJSONObject(0).getString("media_url_https");
            entities.type = mediaArray.getJSONObject(0).getString("type");
        }
        return entities;
    }

    public static List<Entities> fromjsonTweetArray(List<Tweet> tweetsFromNetwork) {
        List<Entities>entities = new ArrayList<>();
        for (int i =0; i < tweetsFromNetwork.size(); i++){
            entities.add(tweetsFromNetwork.get(i).entities);
        }

        return entities;
    }
}
