package android.iut.jetpacklist.ui.detail;

import android.app.Application;
import android.iut.jetpacklist.model.AppDatabase;
import android.iut.jetpacklist.model.Repository;
import android.iut.jetpacklist.model.RepositoryDao;
import android.iut.jetpacklist.viewmodel.GitHubService;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailViewModel extends AndroidViewModel {
    private GitHubService service;
    private RepositoryDao dao;
    private MutableLiveData<Repository> repository = new MutableLiveData<>();

    public DetailViewModel(@NonNull Application application) {
        super(application);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(GitHubService.class);

        //FIXME Database should be instanciated once only
        dao = Room.databaseBuilder(getApplication(),
                AppDatabase.class, "database-name")
                .allowMainThreadQueries()
                .build()
                .repositoryDao();

        publishDbValue();
        queryWithRetrofit();
    }

    private void queryWithRetrofit() {
        service.getRepoDetail("vferries", "iut-android").enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository> call, Response<Repository> response) {
                //Warning we are on UI thread here
                dao.insertAll(response.body());
                publishDbValue();
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                Log.e("DetailViewModel", "Error getting repo detail from Github", t);
            }
        });
    }

    private void publishDbValue() {
        repository.postValue(dao.findByName("iut-android"));
    }

    public LiveData<Repository> getRepository() {
        return repository;
    }
}
