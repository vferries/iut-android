package android.iut.battlenet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.activities.BnOAuthAccessTokenActivity;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

public class OauthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BnOAuth2Params bnOAuth2Params = new BnOAuth2Params(
                "05770ae9ec30416c80a0173a4c42c43c",
                "Z32Sh6vcl6SkQGI7KAEiU9ZlTvYNk2ML",
                BnConstants.ZONE_EUROPE,
                "https://localhost",
                "HSMoonra",
                BnConstants.SCOPE_WOW
        );
        final Intent intent = new Intent(this, BnOAuthAccessTokenActivity.class);
        // Send BnOAuth2Params
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        // Send redirect Activity
        intent.putExtra(BnConstants.BUNDLE_REDIRECT_ACTIVITY, ResultActivity.class);
        startActivity(intent);
    }
}