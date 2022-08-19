package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;


import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {
    private   TextView tvView;
    private TextView txtView;
    private ImageView imaj;
    TextView name;
    TextView tvRetweet;
    TextView tvFavorite;
    TextView tvTime;
    ImageView postImg;
    ImageView star;
    ImageView retweet;
    ImageView share;
    ImageView comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtView = findViewById(R.id.tvusrname);
        tvView = findViewById(R.id.tvbody2);
        imaj = findViewById(R.id.imaj);
        star = findViewById(R.id.star);
        retweet = findViewById(R.id.retweet);
        comment = findViewById(R.id.comment);
        share = findViewById(R.id.share);
        name = findViewById(R.id.tvName);
        tvRetweet = findViewById(R.id.tvRetweet);
        tvFavorite = findViewById(R.id.tvFav);
        tvTime = findViewById(R.id.tvTime);
        postImg = findViewById(R.id.tvImage);

        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar2);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.tw_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Twitter");


        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("Tweet"));

        tvView.setText(tweet.getBody());
        txtView.setText(tweet.user.name);
        name.setText(tweet.user.screenName);
        tvRetweet.setText(tweet.getRetweet());
        tvFavorite.setText(tweet.getLike());
        tvTime.setText(tweet.getCreatedAt());

        Glide.with(this)
                .load(tweet.user.profileImageUrl)
                .transform(new RoundedCorners(50))
                .into(imaj);

        if(!tweet.entities.media_Url.isEmpty()) {
            postImg.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(tweet.entities.media_Url)
                    .transform(new RoundedCorners(30))
                    .into(postImg);
        }


        star.setImageResource(R.drawable.ic_outline_star_border_24);
        share.setImageResource(R.drawable.ic_outline_share_24);
        comment.setImageResource(R.drawable.ic_outline_mode_comment_24);
        retweet.setImageResource(R.drawable.ic_repeat);
    }
}