package com.movie.keyalive.movieuiux.Api;

import com.movie.keyalive.movieuiux.Model.ResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/now_playing")
    Call<ResponseMovie> getNowPlaying(@Query("language") String language);

    @GET("movie/upcoming")
    Call<ResponseMovie> getUpComing(@Query("language") String language);


    @GET("search/movie")
    Call<ResponseMovie> getSearchMovie(@Query("query") String query, @Query("language") String language);
}