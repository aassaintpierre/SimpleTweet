package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener;
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.SimpleMainThreadMediaPlayerListener;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;

import org.parceler.Parcels;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {
    Context context;
    List<Tweet> tweets;

    //    pass in the context and list of tweets


    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);

    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    //    For each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        TweetsAdapter.ViewHolder viewHolder = new TweetsAdapter.ViewHolder(view, listener);


        return viewHolder;
    }

    //    Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//      get the data position
        Tweet tweet = tweets.get(position);


//        bind the tweet with view holder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear() {

        tweets.clear();
        notifyDataSetChanged();

    }

    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();

    }

    //Define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView date1;
        TextView tName;
        TextView tTime;
        ImageView tImage;
        TextView tRetweet;
        TextView tShare;
        TextView tStar;
        TextView twRetweet;

        RelativeLayout container;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener clickListener) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tName = itemView.findViewById(R.id.tName);
            date1 = itemView.findViewById(R.id.date1);
            tvScreenName = itemView.findViewById(R.id.tScreenName);
            tTime = itemView.findViewById(R.id.tTime);
            tImage = itemView.findViewById(R.id.tImage);
            tRetweet = itemView.findViewById(R.id.twRetweet);
            tShare = itemView.findViewById(R.id.twShare);
            tStar = itemView.findViewById(R.id.twStar);
            twRetweet = itemView.findViewById(R.id.twRetweet);
            container = itemView.findViewById(R.id.container);

        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText("@" + tweet.user.screenName);
            tName.setText(tweet.user.name);
            date1.setText(Tweet.getFormattedTime(tweet.getCreatedAt()));
            tTime.setText(tweet.createdAt);
            tRetweet.setText(tweet.retweet);
            twRetweet.setText(tweet.like);
            tStar.setText(tweet.like);

            Glide.with(context)
                    .load(tweet.user.profileImageUrl)
                    .transform(new CircleCrop())
                    .placeholder(R.drawable.loading)
                    .into(ivProfileImage);

            if (!tweet.entities.getMedia_Url().isEmpty()) {
                Glide.with(context)
                        .load(tweet.entities.media_Url)
                        .placeholder(R.drawable.loading)
                        .transform(new RoundedCorners(30))
                        .into(tImage);
            }



            tStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int like = Integer.parseInt(tweet.like);
                    if (!tweet.likeBool) {
                        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_star2);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        tStar.setCompoundDrawables(drawable, null, null, null);

                        like += 1;
                        tweet.like = String.valueOf(like);
                        tStar.setText(tweet.like);
                        tweet.likeBool = true;
                    } else {
                        like -= 1;
                        tweet.like = String.valueOf(like);
                        tStar.setText(tweet.like);
                        tweet.likeBool = false;

                        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_outline_star_border_24);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        tStar.setCompoundDrawables(drawable, null, null, null);
                    }

                }
            });


            twRetweet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int like = Integer.parseInt(tweet.like);
                    if (!tweet.likeBool) {
                        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.icon_repeat);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        twRetweet.setCompoundDrawables(drawable, null, null, null);

                        like += 1;
                        tweet.like = String.valueOf(like);
                        twRetweet.setText(tweet.like);
                        tweet.likeBool = true;
                    } else {
                        like -= 1;
                        tweet.like = String.valueOf(like);
                        twRetweet.setText(tweet.like);
                        tweet.likeBool = false;

                        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_repeat);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        twRetweet.setCompoundDrawables(drawable, null, null, null);
                    }

                }
            });

            tShare.setOnClickListener((View v) -> {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, tweet.getUrl());
                context.startActivity(Intent.createChooser(shareIntent, "Share link using"));
            });



            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("Tweet", Parcels.wrap(tweet));
                    context.startActivity(intent);
                }
            });


        }
        }

    }

