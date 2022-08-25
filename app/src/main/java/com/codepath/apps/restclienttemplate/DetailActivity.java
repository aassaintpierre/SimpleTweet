package com.codepath.apps.restclienttemplate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener;
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;


import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {
    private   TextView tvView;
    private TextView txtView;
    private ImageView imaj;
    TextView name;
    TextView tvTime;
    ImageView postImg;
    TextView date;
    TextView star;
    TextView retweet;
    TextView share;
    TextView comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtView = findViewById(R.id.tvusrname);
        tvView = findViewById(R.id.tvbody2);
        imaj = findViewById(R.id.imaj);
        date = findViewById(R.id.date);
        star = findViewById(R.id.star);
        retweet = findViewById(R.id.retweet);
        comment = findViewById(R.id.comment);
        share = findViewById(R.id.retweet);
        name = findViewById(R.id.tvName);
        tvTime = findViewById(R.id.tvTime);
        postImg = findViewById(R.id.tvImage);

        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar2);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setLogo(R.mipmap.tw_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Twitter");


        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("Tweet"));

        tvView.setText(tweet.body);
        txtView.setText(tweet.user.name);
        name.setText(tweet.user.screenName);
        date.setText(Tweet.getFormattedTime1(tweet.getCreatedAt()));
        tvTime.setText(tweet.createdAt);
        retweet.setText(tweet.retweet);
        star.setText(tweet.like);

        Glide.with(this)
                .load(tweet.user.profileImageUrl)
                .transform(new CircleCrop())
                .placeholder(R.drawable.loading)
                .into(imaj);

//        if(!tweet.entities.media_Url.isEmpty()) {
//            postImg.setVisibility(View.VISIBLE);
//            Glide.with(this)
//                    .load(tweet.entities.media_Url)
//                    .transform(new RoundedCorners(30))
//                    .placeholder(R.drawable.loading)
//                    .into(postImg);
//        }

        if(tweet.likeBool){
            Drawable drawable = ContextCompat.getDrawable(DetailActivity.this, R.drawable.ic_star2);
            drawable.setBounds(0,0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            star.setCompoundDrawables(drawable, null, null, null);

        }

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int like =  Integer.parseInt(tweet.like);
                if(!tweet.likeBool){
                    Drawable drawable = ContextCompat.getDrawable(DetailActivity.this, R.drawable.ic_star2);
                    drawable.setBounds(0,0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    star.setCompoundDrawables(drawable, null, null, null);

                    like += 1;
                    tweet.like = String.valueOf(like);
                    star.setText(tweet.like);
                    tweet.likeBool = true;
                }else {
                    like -= 1;
                    tweet.like = String.valueOf(like);
                    star.setText(tweet.like);
                    tweet.likeBool = false;

                    Drawable drawable = ContextCompat.getDrawable(DetailActivity.this, R.drawable.ic_outline_star_border_24);
                    drawable.setBounds(0,0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    star.setCompoundDrawables(drawable, null, null, null);
                }

            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int back = R.id.homeAsUp;
        Intent intent = new Intent(DetailActivity.this,TimelineActivity.class);
        intent.putExtra("back",Parcels.wrap(back));
        DetailActivity.this.startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}

