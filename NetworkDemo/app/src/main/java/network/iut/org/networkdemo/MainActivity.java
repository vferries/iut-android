package network.iut.org.networkdemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import network.iut.org.networkdemo.json.JSONActivity;
import network.iut.org.networkdemo.url.connection.HttpURLConnectionActivity;
import network.iut.org.networkdemo.socket.SocketActivity;
import network.iut.org.networkdemo.xml.XMLActivity;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = ((ListView)findViewById(android.R.id.list));
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"Socket", "HttpURLConnection", "JSON parsing", "XML parsing"}));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(this, SocketActivity.class);
                break;
            case 1:
                intent = new Intent(this, HttpURLConnectionActivity.class);
                break;
            case 2:
                intent = new Intent(this, JSONActivity.class);
                break;
            default:
                intent = new Intent(this, XMLActivity.class);
        }
        startActivity(intent);
    }
}
