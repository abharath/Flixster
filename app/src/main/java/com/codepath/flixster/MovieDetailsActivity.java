package com.codepath.flixster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.codepath.flixster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;

/**
 * Created by abharath on 1/24/17.
 */

public class MovieDetailsActivity extends AppCompatActivity{
//    TextView tvTitle;
//    TextView tvOverview;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.item_movie_details);
//        int movieID = getIntent().getIntExtra("text", -1);
//        tvTitle = (TextView) findViewById(R.id.tvTitleDetails);
//        tvOverview = (TextView) findViewById(R.id.tvOverviewDetails);
//        String url = "https://api.themoviedb.org/3/movie/"+movieID+"?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
//        AsyncHttpClient client = new AsyncHttpClient();
//
//        client.get(url, new JsonHttpResponseHandler(){
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                JSONArray movieJsonResults = null;
//                try {
//                    movieJsonResults = response.getJSONArray("results");
//                    Movie.fromJsonArray(movieJsonResults);
//                    Log.d("DEBUG", movieJsonResults.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
//            }
//        });
//        tvTitle.setText("ABC");
//        tvOverview.setText("XYZ");
//    }
//
//    public void onSubmit(View v) {
//        // closes the activity and returns to first screen
//        this.finish();
//    }
}
