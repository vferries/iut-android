package com.example.vincent.repodetail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class DummyGitHubService implements GitHubService {
    private Map<String, Repo> map = new HashMap<>();

    public DummyGitHubService() {
        User vf = new User();
        vf.setLogin("Vincent Ferries");
        vf.setAvatar_url("https://avatars.githubusercontent.com/u/2457031?v=3");
        User facebook = new User();
        facebook.setLogin("facebook");
        facebook.setAvatar_url("https://avatars.githubusercontent.com/u/69631?v=3");
        addRepository("iut-android", "Repository du cours pour l'iut Blagnac", "30/11/2015", 12, vf);
        addRepository("apprendre", "Des ressources pour apprendre le code", "31/11/2015", 3, vf);
        addRepository("react", "Framework JS so 2015", "32/11/2015", 12567, facebook);
    }

    private void addRepository(String name, String description, String created_at, int forks_count, User owner) {
        Repo repository = new Repo();
        repository.setName(name);
        repository.setCreated_at(created_at);
        repository.setForks_count(forks_count);
        repository.setDescription("Description du repository");
        repository.setOwner(owner);
        map.put(name, repository);
    }

    @Override
    public Call<Repo> getRepoDetail(@Path("user") String username, @Path("repo") String repoName) {
        return new DummyCall<>(map.get(username));
    }

    private class DummyCall<T> implements Call<T> {
        private T result;

        public DummyCall(T result) {
            this.result = result;
        }

        @Override
        public Response<T> execute() throws IOException {
            return Response.success(result);
        }

        @Override
        public void enqueue(Callback<T> callback) {
            Response response = Response.success(result);
            callback.onResponse(this, response);
        }

        @Override
        public boolean isExecuted() {
            return false;
        }

        @Override
        public void cancel() {

        }

        @Override
        public boolean isCanceled() {
            return false;
        }

        @Override
        public Call<T> clone() {
            return null;
        }

        @Override
        public Request request() {
            return null;
        }
    }
}