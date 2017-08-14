package com.example.cliff.moviesfromthedb.Util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cliff.moviesfromthedb.Model.Movie;
import com.example.cliff.moviesfromthedb.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvTitle;
        public ImageView ivPoster;
        public TextView tvOverview;
        public TextView tvReleaseDate;
        public ProgressBar progressBar;


        public ViewHolder(final View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            ivPoster = (ImageView) itemView.findViewById(R.id.ivPoster);
            tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
            tvReleaseDate = (TextView) itemView.findViewById(R.id.tvReleaseDate);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    private List<Movie> movies;
    private Context context;

    public RecyclerViewAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.movie_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Movie movie = movies.get(position);

        TextView tvTitle = viewHolder.tvTitle;
        tvTitle.setText(movie.getTitle());

        final ProgressBar progressBar = viewHolder.progressBar;

        ImageView ivPoster = viewHolder.ivPoster;
        // Getting image with Picasso
        String baseImageURL = "http://image.tmdb.org/t/p/w185/"; // image size w185 best for mobile
        int placeholder = context.getResources().getIdentifier("@drawable/image_load_error", null, context.getPackageName());
        Picasso.with(context)
                .load(baseImageURL + movie.getPosterPath())
                .placeholder(placeholder)
                .error(placeholder)
                .into(ivPoster, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        progressBar.setVisibility(View.GONE);
                    }
                });

        TextView tvReleaseDate = viewHolder.tvReleaseDate;
        tvReleaseDate.setText("Release date: " + movie.getReleaseDate());

        TextView overview = viewHolder.tvOverview;
        overview.setText(movie.getOverview());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}