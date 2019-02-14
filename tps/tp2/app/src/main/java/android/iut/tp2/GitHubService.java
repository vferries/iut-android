package android.iut.tp2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface GitHubService {
    @GET("repos/{user}/{repo}")
    Call<Repository> getRepoDetail(@Path("user") String username, @Path("repo") String repo);
}
