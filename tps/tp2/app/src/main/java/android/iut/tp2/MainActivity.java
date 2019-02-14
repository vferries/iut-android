package android.iut.tp2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView repoName;
    private TextView repoDescription;
    private TextView repoCreatedAt;
    private TextView ownerName;
    private ImageView ownerAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repoName = findViewById(R.id.repoName);
        repoDescription = findViewById(R.id.repoDescription);
        repoCreatedAt = findViewById(R.id.repoCreatedAt);
        ownerName = findViewById(R.id.ownerName);
        ownerAvatar = findViewById(R.id.ownerAvatar);

        initRepoDetail();
    }

    private void initRepoDetail() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<Repository> repoDetail = service.getRepoDetail("facebook", "react");
        repoDetail.enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository> call, Response<Repository> response) {
                Repository repository = response.body();
                updateView(repository);
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                Log.e("RepoDetail", "An error occurred getting repo detail", t);
            }
        });
    }

    private void updateView(Repository repository) {
        repoName.setText(repository.getName());
        repoDescription.setText(repository.getDescription());
        repoCreatedAt.setText("Créé le " + repository.getCreated_at());
        ownerName.setText(repository.getOwner().getLogin());
        new DownloadImageTask().execute(repository.getOwner().getAvatar_url());
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        protected Bitmap doInBackground(String... urls) {
            String imageUrl = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(imageUrl).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap result) {
            ownerAvatar.setImageBitmap(result);
        }
    }
}
