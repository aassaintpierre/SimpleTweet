package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Entities {

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
            entities.media_Url = mediaArray.getJSONObject(0).getString("media_url_https");
            entities.type = mediaArray.getJSONObject(0).getString("type");
        }
        return entities;
    }

}
