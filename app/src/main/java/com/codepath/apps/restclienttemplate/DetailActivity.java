package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    ImageView star;
    ImageView retweet;
    ImageView share;
    ImageView comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtView = findViewById(R.id.tvname);
        tvView = findViewById(R.id.tvbody2);
        imaj = findViewById(R.id.imaj);
        star = findViewById(R.id.star);
        retweet = findViewById(R.id.retweet);
        comment = findViewById(R.id.comment);
        share = findViewById(R.id.share);

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("Tweet"));

        tvView.setText(tweet.getBody());
        txtView.setText(tweet.user.name);

        Glide.with(this)
                .load(tweet.user.profileImageUrl)
                .transform(new RoundedCorners(50))
                .into(imaj);


        star.setImageResource(R.drawable.ic_outline_star_border_24);
        share.setImageResource(R.drawable.ic_outline_share_24);
        comment.setImageResource(R.drawable.ic_outline_mode_comment_24);
        retweet.setImageResource(R.drawable.ic_repeat);
    }
}