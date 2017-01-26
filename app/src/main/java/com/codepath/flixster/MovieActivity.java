package com.codepath.flixster;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.codepath.flixster.adapters.MovieArrayAdapter;
import com.codepath.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private MovieArrayAdapter movieAdapter;
    private ListView lvItems;
    private SwipeRefreshLayout swipeContainer;
    private static final String API_URL = "https://api.themoviedb.org/3/movie/now_playing";
    private static final String API_KEY = "api_key";
    private static final String API_KEY_VALUE = "a07e22bc18f5cb106bfe4cc1f83ad8ed";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
//        getSupportActionBar().hide();
        refreshList(0);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList(0);
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public void refreshList(int page) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder();
        String url = urlBuilder.addQueryParameter(API_KEY, API_KEY_VALUE).build().toString();
        Request request = new Request.Builder().url(url).build();

        lvItems = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvItems.setAdapter(movieAdapter);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject obj = new JSONObject(response.body().string());
                    final JSONArray movieJsonResults = obj.getJSONArray("results");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            movieAdapter.clear();
                            movies.addAll(Movie.fromJsonArray(movieJsonResults));
                            movieAdapter.notifyDataSetChanged();
                            swipeContainer.setRefreshing(false);
                            Log.d("DEBUG", movieJsonResults.toString());
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
