package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

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

    //    @SuppressLint("NotifyDataSetChanged")
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
        TextView tName;
        TextView tTime;
        ImageView tImage;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener clickListener) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tName = itemView.findViewById(R.id.tName);
            tvScreenName = itemView.findViewById(R.id.tScreenName);
            tTime = itemView.findViewById(R.id.tTime);
            tImage = itemView.findViewById(R.id.tImage);
            container = itemView.findViewById(R.id.container);

        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText("@" + tweet.user.screenName);
            tName.setText(tweet.user.name);
            tTime.setText(tweet.createdAt);

            Glide.with(context)
                    .load(tweet.user.profileImageUrl)
                    .into(ivProfileImage);

            if (!tweet.entities.media_Url.isEmpty()) {
                tImage.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(tweet.entities.media_Url)
                        .transform(new RoundedCorners(40))
                        .into(tImage);

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
}
