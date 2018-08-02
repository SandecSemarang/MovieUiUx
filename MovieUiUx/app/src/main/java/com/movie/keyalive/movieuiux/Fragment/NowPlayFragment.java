package com.movie.keyalive.movieuiux.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movie.keyalive.movieuiux.Adapter.AdapterMovies;
import com.movie.keyalive.movieuiux.Api.ApiClient;
import com.movie.keyalive.movieuiux.Language.Language;
import com.movie.keyalive.movieuiux.Model.ListMovie;
import com.movie.keyalive.movieuiux.Model.ResponseMovie;
import com.movie.keyalive.movieuiux.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<ListMovie> mMovieLists = new ArrayList<>();
    private AdapterMovies mMoviesAdapter;
    private Call<ResponseMovie> mMovieResponse;
    private ApiClient mClient = new ApiClient();
    private static final String TAG = NowPlayFragment.class.getSimpleName();

//    String getResults;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_play, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.recyclerview_now_playing);
        mMovieLists = new ArrayList<>();
        mMoviesAdapter = new AdapterMovies(getContext(),mMovieLists);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMoviesAdapter);
        mMoviesAdapter.notifyDataSetChanged();

        loadMoiveData();


    }

    private void loadMoiveData() {
        mMovieResponse = mClient.getService().getNowPlaying(Language.getCountry());
        mMovieResponse.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                Log.d(TAG, "onResponse: "+ response.message());
//                Log.d(TAG,"tess"+ response.body().getResults().get(0).getTitle());
                mMovieLists = response.body().getResults();
                mRecyclerView.setAdapter(new AdapterMovies(getContext(), mMovieLists));

            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {

            }
        });
    }

}
