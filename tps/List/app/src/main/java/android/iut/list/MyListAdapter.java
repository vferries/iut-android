package android.iut.list;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class MyListAdapter extends ArrayAdapter<String> {
    private List<String> liste = new ArrayList<>();

    public MyListAdapter(Context context) {
        super(context, -1);
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
        liste.add("Premier");
        liste.add("Second");
        liste.add("Troisième");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.w("TEST", "getItem");

        TextView textView = new TextView(this.getContext());
        textView.setText(getItem(position));
        return textView;
    }


    @Override
    public int getCount() {
        return liste.size();
    }

    @Override
    public String getItem(int position) {
        return liste.get(position);
    }
}
