package network.iut.org.networkdemo.json;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import network.iut.org.networkdemo.R;

public class JSONActivity extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        textView = (TextView) findViewById(R.id.textView);

        final Button button = (Button) findViewById(R.id.button);
        button.setText("JSON parsing");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpGetTask().execute();
            }
        });
    }

    private class HttpGetTask extends AsyncTask<Void, Void, List<Message>> {
        private static final String TAG = "JSON";

        @Override
        protected List<Message> doInBackground(Void... params) {
            JsonReader reader = null;
            try {
                InputStream is = getResources().getAssets().open("messages.json");
                reader = new JsonReader(new InputStreamReader(is));
                return readMessagesArray(reader);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
            return new ArrayList<Message>();
        }

        private List<Message> readMessagesArray(JsonReader reader) throws IOException {
            List<Message> messages = new ArrayList<Message>();
            reader.beginArray();
            while (reader.hasNext()) {
                messages.add(readMessage(reader));
            }
            reader.endArray();
            return messages;
        }

        private Message readMessage(JsonReader reader) throws IOException {
            long id = -1;
            String text = null;
            User user = null;
            List<Double> geo = null;
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("id")) {
                    id = reader.nextLong();
                } else if (name.equals("text")) {
                    text = reader.nextString();
                } else if (name.equals("geo") && reader.peek() != JsonToken.NULL) {
                    geo = readDoublesArray(reader);
                } else if (name.equals("user")) {
                    user = readUser(reader);
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            return new Message(id, text, user, geo);
        }

        private List readDoublesArray(JsonReader reader) throws IOException {
            List doubles = new ArrayList();
            reader.beginArray();
            while (reader.hasNext()) {
                doubles.add(reader.nextDouble());
            }
            reader.endArray();
            return doubles;
        }

        public User readUser(JsonReader reader) throws IOException {
            String username = null;
            int followersCount = -1;
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("name")) {
                    username = reader.nextString();
                } else if (name.equals("followers_count")) {
                    followersCount = reader.nextInt();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            return new User(username, followersCount);
        }

        @Override
        protected void onPostExecute(List<Message> messages) {
            String asText = "";
            for (Message m : messages) {
                asText += m.toString();
            }
            textView.setText(asText);
        }
    }
}
