package android.iut.battlenet;

import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

import java.io.IOException;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView textView = findViewById(R.id.textView);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        BnOAuth2Params bnOAuth2Params = getIntent().getExtras().getParcelable(BnConstants.BUNDLE_BNPARAMS);
        BnOAuth2Helper bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        try {
            textView.setText(bnOAuth2Helper.getAccessToken());
        } catch (IOException e) {
            Log.e("GROS FAIL", "Raté...", e);
        }
    }
}
