package network.iut.org.networkdemo.xml;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import network.iut.org.networkdemo.R;
import network.iut.org.networkdemo.json.Message;
import network.iut.org.networkdemo.json.User;

public class XMLActivity extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        textView = (TextView) findViewById(R.id.textView);

        final Button button = (Button) findViewById(R.id.button);
        button.setText("XML Parsing");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpGetTask().execute();
            }
        });
    }

    private class HttpGetTask extends AsyncTask<Void, Void, List<Entry>> {
        private static final String TAG = "XML";

        @Override
        protected List<Entry> doInBackground(Void... params) {
            try {
                InputStream is = getResources().getAssets().open("feeds.xml");
                return new StackOverflowXmlParser().parse(is);
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            } catch (XmlPullParserException e) {
                Log.e(TAG, e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Entry> entries) {
            String asText = "";
            for (Entry entry : entries) {
                asText += entry.title + "\n" + entry.link + "\n" + entry.summary + "\n\n";
            }
            textView.setText(asText);
        }
    }
}
