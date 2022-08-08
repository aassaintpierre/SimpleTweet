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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        tvView = findViewById(R.id.tvView);
        txtView = findViewById(R.id.txtView);
        imaj = findViewById(R.id.imaj);

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweets"));

        tvView.setText(tweet.getBody());
        txtView.setText(tweet.getUser().getName());

        Glide.with(this)
                .load(tweet.user.profileImageUrl)
                .transform(new RoundedCorners(50))
                .into(imaj);
    }
}