package android.iut.jetpacklist;

import android.content.Intent;
import android.iut.jetpacklist.model.Repository;
import android.iut.jetpacklist.viewmodel.ReposViewModel;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {
    private MyAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

        final ReposViewModel model =
                ViewModelProviders.of(this).get(ReposViewModel.class);
        model.getRepos().observe(this, new Observer<List<Repository>>() {
            @Override
            public void onChanged(List<Repository> repos) {
                adapter.setRepositories(repos);
            }
        });

        final EditText editText = findViewById(R.id.editText);
        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editText.getText().toString();
                model.loadRepos(user);
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int itemPosition = recyclerView.indexOfChild(v);
        startActivity(new Intent(this, DetailActivity.class));
    }
}
