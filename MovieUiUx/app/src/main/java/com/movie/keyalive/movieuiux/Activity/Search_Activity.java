package  com.movie.keyalive.movieuiux;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

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

public class Search_Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<ListMovie> mMovieLists;
    private AdapterMovies mMoviesAdapter;
    private Call<ResponseMovie> mMovieResponse;
    private ApiClient mClient = new ApiClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_);


        mRecyclerView = findViewById(R.id.recyclerview_search);
        mMovieLists = new ArrayList<>();
        mMoviesAdapter = new AdapterMovies(getApplicationContext(),mMovieLists);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMoviesAdapter);

        String filmTitle = getIntent().getStringExtra("movie_title");
        loadData(filmTitle);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (mMovieResponse != null) mMovieResponse.cancel();
    }

    private void loadData(String filmTitle){
        mMovieResponse = mClient.getService().getSearchMovie(filmTitle, Language.getCountry());
        mMovieResponse.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                List<ListMovie> movies = response.body().getResults();
                mRecyclerView.setAdapter(new AdapterMovies(getApplicationContext(), movies));

            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {

            }
        });
    }
}
