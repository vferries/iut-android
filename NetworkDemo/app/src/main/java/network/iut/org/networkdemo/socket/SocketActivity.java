package network.iut.org.networkdemo.socket;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import network.iut.org.networkdemo.R;

public class SocketActivity extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        textView = (TextView) findViewById(R.id.textView);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpGetTask().execute();
            }
        });
    }

    private class HttpGetTask extends AsyncTask<Void, Void, String> {
        private static final String HOST = "numbersapi.com";
        private static final String HTTP_GET_COMMAND = "GET /42 HTTP/1.1\n" +
                "Host: " + HOST + "\n" +
                "Connection: close\n\n";
        private static final String TAG = "Socket";

        @Override
        protected String doInBackground(Void... params) {
            Socket socket = null;
            String data = "";
            try {
                socket = new Socket(HOST, 80);
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                writer.println(HTTP_GET_COMMAND);
                data = readStream(socket.getInputStream());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != socket) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        Log.e(TAG, "IOException");
                    }
                }
            }
            return data;
        }

        private String readStream(InputStream inputStream) {
            BufferedReader reader = null;
            StringBuffer data = new StringBuffer();
            try {
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    data.append(line);
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException");
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(TAG, "IOException");
                    }
                }
            }
            return data.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            textView.setText(result);
        }
    }
}
