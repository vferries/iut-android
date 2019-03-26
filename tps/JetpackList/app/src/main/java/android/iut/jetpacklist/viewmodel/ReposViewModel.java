package android.iut.jetpacklist.viewmodel;

import android.iut.jetpacklist.model.Repository;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReposViewModel extends ViewModel {
    private MutableLiveData<List<Repository>> repos;
    private GitHubService service;

    public ReposViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GitHubService.class);
    }

    public LiveData<List<Repository>> getRepos() {
        if (repos == null) {
            repos = new MutableLiveData<>();
            repos.setValue(new ArrayList<Repository>());
        }
        return repos;
    }

    public void loadRepos(String userName) {
        service.getRepoList(userName).enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                repos.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                Log.e("ReposViewModel", "Error getting repos list from Github", t);
            }
        });
    }
}
