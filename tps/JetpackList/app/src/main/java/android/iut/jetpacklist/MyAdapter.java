package android.iut.jetpacklist;

import android.iut.jetpacklist.model.Repository;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private View.OnClickListener listener;

    public MyAdapter(View.OnClickListener listener) {
        this.listener = listener;
    }

    private List<Repository> repositories;

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
        notifyDataSetChanged();
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false);
        v.setOnClickListener(listener);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Repository repository = repositories.get(position);
        holder.forks.setText(repository.getForks_count().toString());
        holder.name.setText(repository.getName());
        holder.description.setText(repository.getDescription());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return repositories.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView forks;
        TextView name;
        TextView description;

        MyViewHolder(View v) {
            super(v);
            forks = v.findViewById(R.id.fork_count);
            name = v.findViewById(R.id.repoName);
            description = v.findViewById(R.id.repoDescription);
        }
    }
}