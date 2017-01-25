package com.codepath.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flixster.R;
import com.codepath.flixster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by abharath on 1/23/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie>{
    private final Context mContext;

    private static class ViewHolder {
        ImageView img;
        TextView name;
        TextView overview;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.lvMovieImage);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvTitleDetails);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.img.setImageResource(0);
        viewHolder.name.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverView());

        int orientation = mContext.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath())
                    .transform(new RoundedCornersTransformation(10, 10)).into(viewHolder.img);
 //           Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.img);
        } else {
            Picasso.with(getContext()).load(movie.getBackdropPath())
                    .transform(new RoundedCornersTransformation(10, 10)).into(viewHolder.img);
//            Picasso.with(getContext()).load(movie.getBackdropPath()).into(viewHolder.img);
        }


//        ImageView lvImage = (ImageView) convertView.findViewById(R.id.lvMovieImage);
//        lvImage.setImageResource(0);
//
//        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
//        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
//
//        tvTitle.setText(movie.getOriginalTitle());
//        tvOverview.setText(movie.getOverView());

//        int orientation = mContext.getResources().getConfiguration().orientation;
//        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
//            Picasso.with(getContext()).load(movie.getPosterPath()).into(lvImage);
//        } else {
//            Picasso.with(getContext()).load(movie.getBackdropPath()).into(lvImage);
//        }
        return convertView;
    }
}
