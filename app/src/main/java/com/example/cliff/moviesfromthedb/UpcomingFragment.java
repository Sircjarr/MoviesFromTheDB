package com.example.cliff.moviesfromthedb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cliff.moviesfromthedb.Model.Movie;
import com.example.cliff.moviesfromthedb.Util.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

// This fragment contains the logic for setting up the movie RecyclerView with the data from the API calls

public class UpcomingFragment extends Fragment {

    private static final String TAG = "UpcomingFragment";

    private RecyclerView recyclerView;
    private final String upcomingURL = "https://api.themoviedb.org/3/movie/upcoming?api_key=787c4e4f4194b88bc3696eca661ecc82&language=en-US&page=1";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_fragment,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv);

        setupListWithData();

        return view;
    }

    public void setupListWithData() {

        // Request a JSONObject with Volley
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, getCallURL(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray ja = response.getJSONArray("results");

                            // Create the movieList
                            ArrayList<Movie> movieList = new ArrayList<>();
                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject movie = ja.getJSONObject(i);
                                movieList.add(new Movie(movie.getString("title"), movie.getString("poster_path"), movie.getString("overview"), movie.getString("release_date")));
                            }

                            // Set the custom adapter for the RecyclerView
                            RecyclerViewAdapter adapter = new RecyclerViewAdapter(movieList);
                            recyclerView.setAdapter(adapter);

                            // Determines when list items are no longer visible and can be reused
                            recyclerView.setLayoutManager((new LinearLayoutManager(getActivity())));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: " + error.getMessage());
                    }
                });

        // Make Volley API call by putting the JsonObjectRequest object in a RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsObjRequest);
    }

    // To be overridden by other fragments
    public String getCallURL() {
        return upcomingURL;
    }
}
