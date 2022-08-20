package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Extended {

    public String media_Url;

    public Extended(){}

    public static Extended fromJson(JSONObject jsonObject) throws JSONException {
        Extended extended = new Extended();
        // if cover media is available
        if(!jsonObject.has("media")){
            extended.media_Url = "";
        }else if(jsonObject.has("media")){
            final JSONArray mediaArray = jsonObject.getJSONArray("media");
            extended.media_Url = mediaArray.getJSONObject(0).getString("url");
        }
        return extended;
    }
}
