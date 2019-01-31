package maps.android.iut.org.mapsdemo;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final long ONE_MIN = 1000 * 60;
    private static final long TWO_MIN = ONE_MIN * 2;
    private static final long FIVE_MIN = ONE_MIN * 5;
    private static final long POLLING_FREQ = 1000 * 10;
    private static final float MIN_ACCURACY = 25.0f;
    private static final float MIN_LAST_READ_ACCURACY = 500.0f;
    private static final float MIN_DISTANCE = 10.0f;

    private GoogleMap mMap;
    private LocationManager mManager;
    private Location mBestReading;
    private LocationListener mLocationListener;

    @Override
    public boolean checkSelfPermissions() {
        return Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    @Override
    public void requestPermissions(Activity activity) {
        if ((Build.VERSION.SDK_INT >= 23) && (ContextCompat.checkSelfPermission(activity, "android.permission.ACCESS_FINE_LOCATION") != 0)) {
            int requestCode = 0;
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, requestCode);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //mBestReading = bestLastKnownLocation(MIN_LAST_READ_ACCURACY, FIVE_MIN);
        if (mBestReading != null) {
            updateDisplay(mBestReading);
        }
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (mBestReading == null || location.getAccuracy() < mBestReading.getAccuracy()) {
                    mBestReading = location;
                    updateDisplay(location);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }

    private void updateDisplay(Location location) {
        while (mMap == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Vous êtes ici !"));
    }

    private Location bestLastKnownLocation(float minAccuracy, long maxAge) throws SecurityException {
        Location bestResult = null;
        float bestAccuracy = Float.MAX_VALUE;
        long bestAge = Long.MAX_VALUE;
        List<String> providers = mManager.getAllProviders();
        for (String provider : providers) {
            Location location = mManager.getLastKnownLocation(provider);
            if (location != null) {
                float accuracy = location.getAccuracy();
                long time = location.getTime();
                if (accuracy < bestAccuracy) {
                    bestResult = location;
                    bestAccuracy = accuracy;
                    bestAge = time;
                }
            }
        }
        if (bestAccuracy > minAccuracy || (System.currentTimeMillis() - bestAge) > maxAge) {
            return null;
        }
        return bestResult;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null == mBestReading
                || mBestReading.getAccuracy() > MIN_LAST_READ_ACCURACY
                || mBestReading.getTime() < System.currentTimeMillis() - TWO_MIN) {
            //On s'enregistre pour les mises à jour
            if (mManager.getProvider(LocationManager.NETWORK_PROVIDER) != null) {
                try {
                    mManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, POLLING_FREQ, MIN_DISTANCE, mLocationListener);
                } catch (SecurityException se) {
                }
            }

        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
