package android.iut.jetpacklist.viewmodel;

import android.iut.jetpacklist.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("repos/{user}/{repo}")
    Call<Repository> getRepoDetail(@Path("user") String username, @Path("repo") String repo);

    @GET("users/{user}/repos")
    Call<List<Repository>> getRepoList(@Path("user") String username);
}
